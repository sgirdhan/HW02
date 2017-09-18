package com.example.sharangirdhani.homework02;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.sharangirdhani.homework02.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    public static final String CONTACT_ARRAY_KEY = "CONTACTARRAY";
    public static final String CONTACT_KEY = "CONTACT";
    public static final String REQUEST_TYPE = "REQUEST_TYPE";

    public static final int REQUEST_ADD = 1;
    public static final int REQUEST_DELETE = 2;
    public static final int REQUEST_EDIT = 3;
    public static final int REQUEST_SHOW = 4;

    public ArrayList<Contact> contacts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

//        Contact contact1 = new Contact("Sharan", "Girdhani", "UNCC", "sgirdhan@uncc.edu", "9803377381", "www.sharangirdhani.com", "My Address",  "sharan", "www.facebook.com", "www.twitter.com", "skype", "youtube", null);
//        Contact contact2 = new Contact("Salman", "Mujtaba", "UNCC", "sgirdhan@uncc.edu", "9803377381", "www.sharangirdhani.com", "My Address", "sharan", "www.facebook.com", "www.twitter.com", "skype", "youtube", null);
//        contacts.add(contact1);
//        contacts.add(contact2);

        binding.buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AddContactActivity.class);
                startActivityForResult(intent, REQUEST_ADD);
            }
        });

        binding.buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(contacts.size() > 0) {
                    Intent intent = new Intent(MainActivity.this, ContactListActivity.class);
                    intent.putExtra(REQUEST_TYPE, REQUEST_EDIT);
                    intent.putExtra(CONTACT_ARRAY_KEY, contacts);
                    startActivityForResult(intent, REQUEST_EDIT);
                }
                else {
                    Toast.makeText(MainActivity.this, "No Contacts to edit", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(contacts.size() > 0) {
                    Intent intent = new Intent(MainActivity.this,ContactListActivity.class);
                    intent.putExtra(REQUEST_TYPE, REQUEST_DELETE);
                    intent.putExtra(CONTACT_ARRAY_KEY,contacts);
                    startActivityForResult(intent,REQUEST_DELETE);
                }
                else {
                    Toast.makeText(MainActivity.this, "No Contacts to delete", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.buttonDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(contacts.size() > 0) {
                    Intent intent = new Intent(MainActivity.this, ContactListActivity.class);
                    intent.putExtra(REQUEST_TYPE, REQUEST_SHOW);
                    intent.putExtra(CONTACT_ARRAY_KEY, contacts);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(MainActivity.this, "No Contacts to show", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.buttonFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Handle Activity Results
        if (data != null && requestCode == REQUEST_ADD){
            Toast.makeText(this,getResources().getString(R.string.toast_contact_added_label),Toast.LENGTH_SHORT).show();
            contacts.add((Contact) data.getSerializableExtra(CONTACT_KEY));
        } else if (data != null && requestCode == REQUEST_DELETE){
            Toast.makeText(this,getResources().getString(R.string.toast_contact_deleted_label),Toast.LENGTH_SHORT).show();
            contacts = (ArrayList<Contact>) data.getSerializableExtra(CONTACT_ARRAY_KEY);
        } else if (data != null && requestCode == REQUEST_EDIT){
            Toast.makeText(this,getResources().getString(R.string.toast_contact_updated_label),Toast.LENGTH_SHORT).show();
            contacts = (ArrayList<Contact>) data.getSerializableExtra(CONTACT_ARRAY_KEY);
        }
    }
}
