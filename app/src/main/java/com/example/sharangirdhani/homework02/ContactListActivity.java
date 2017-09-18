package com.example.sharangirdhani.homework02;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class ContactListActivity extends AppCompatActivity {
    private ArrayList<Contact> contacts;
    final static String INTENT_KEY = "INTENT_KEY";
    final static String INTENT_ARRAY_KEY = "INTENT_ARRAY_KEY";

    int request_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_contact_list);
        contacts = new ArrayList<>();


        if (getIntent().getExtras() != null) {
            // Get the Contacts array from intent
            if(getIntent().getExtras().containsKey(MainActivity.CONTACT_ARRAY_KEY)) {
                contacts = (ArrayList<Contact>) getIntent().getExtras().getSerializable(MainActivity.CONTACT_ARRAY_KEY);
            }
            else {
                // Error handling
                Log.e("hw2","ContactList Activity called without the contacts arraylist. This should not happen");
                finish();
            }

            if(getIntent().getExtras().containsKey(MainActivity.REQUEST_TYPE)) {
                request_type = getIntent().getExtras().getInt(MainActivity.REQUEST_TYPE);
            }
            else {
                // Error handling
                Log.e("hw2","ContactList Activity called without the request type. This should not happen");
                finish();
            }
        }
        else {
            // Error handling
            Log.e("hw2","ContactList Activity called without any intent. This should not happen");
            finish();
        }

        ScrollView scroll = new ScrollView(ContactListActivity.this);
        scroll.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        LinearLayout linearLayout_main = new LinearLayout(ContactListActivity.this);
        linearLayout_main.setOrientation(LinearLayout.VERTICAL);
        linearLayout_main.setGravity(Gravity.LEFT);
        int i = 0;
        for (final Contact cnt : contacts) {

            final LinearLayout linearLayout = new LinearLayout(ContactListActivity.this);
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            linearLayout.setPadding(5, 5 , 5, 5);
            ImageView imageView = new ImageView(ContactListActivity.this);
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), Uri.parse(cnt.getAvatar()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            imageView.setImageBitmap(bitmap);

            //imageView.setImageResource(R.drawable.add);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(100, 100);
            layoutParams.setMargins(25,5,100,5);
            imageView.setLayoutParams(layoutParams);
            linearLayout.addView(imageView);

            TextView textView = new TextView(ContactListActivity.this);
            textView.setText(cnt.getFirstName() + " " + cnt.getLastName() + "\n" + cnt.getPhone());
            textView.setTextColor(Color.parseColor("Black"));

            linearLayout.addView(textView);
            linearLayout.setTag(i);
            i++;

            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(request_type == MainActivity.REQUEST_EDIT) {
                        Intent intent = new Intent(ContactListActivity.this, EditContactActivity.class);
                        intent.putExtra(INTENT_KEY, (Integer) linearLayout.getTag());
                        intent.putExtra(INTENT_ARRAY_KEY, contacts);
                        startActivityForResult(intent, MainActivity.REQUEST_EDIT);
                    }
                    else if(request_type == MainActivity.REQUEST_DELETE) {
                        // AlertDialogueBox Code
                        final int selectedContact = (int) linearLayout.getTag();

                        AlertDialog.Builder builder = new AlertDialog.Builder(ContactListActivity.this);
                        builder.setTitle("Delete Contact : " + cnt.getFirstName() + " " + cnt.getLastName())
                        .setMessage("Are you sure?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Log.e("hw2", "Clicked Yes");
                                deleteContact(selectedContact);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Log.e("hw2", "Clicked No");
                            }
                        }).show();
                    }
                    else if(request_type == MainActivity.REQUEST_SHOW) {
                        Intent intent = new Intent(ContactListActivity.this, ShowContactActivity.class);
                        intent.putExtra(INTENT_KEY, cnt);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(ContactListActivity.this, "Problem with the request type",Toast.LENGTH_SHORT).show();
                    }
                }
            });



            linearLayout_main.addView(linearLayout);
        }


        scroll.addView(linearLayout_main);
        setContentView(scroll);
    }

    public void deleteContact(int index) {
        // Delete the selected Contact object
        if (index >= 0) {
            contacts.remove(index);
            Intent intent = new Intent();
            intent.putExtra(MainActivity.CONTACT_ARRAY_KEY, contacts);
            setResult(1, intent);
            finish();
        } else {
            Toast.makeText(this,getString(R.string.no_contact_lable),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Handle Activity Results
        if (data != null && requestCode == MainActivity.REQUEST_DELETE){
            Toast.makeText(this,getResources().getString(R.string.toast_contact_deleted_label),Toast.LENGTH_SHORT).show();
            contacts = (ArrayList<Contact>) data.getSerializableExtra(INTENT_ARRAY_KEY);
            Intent intent = new Intent();
            intent.putExtra(MainActivity.CONTACT_ARRAY_KEY, contacts);
            setResult(1, intent);
            finish();
        } else if (data != null && requestCode == MainActivity.REQUEST_EDIT){
            Toast.makeText(this,getResources().getString(R.string.toast_contact_updated_label),Toast.LENGTH_SHORT).show();
            contacts = (ArrayList<Contact>) data.getSerializableExtra(INTENT_ARRAY_KEY);
            Intent intent = new Intent();
            intent.putExtra(MainActivity.CONTACT_ARRAY_KEY, contacts);
            setResult(1, intent);
            finish();
        }
    }
}
