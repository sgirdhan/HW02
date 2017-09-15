package com.example.sharangirdhani.homework02;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.sharangirdhani.homework02.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    public static final String CONTACT_ARRAY_KEY = "CONTACTARRAY";
    public static final String CONTACT_KEY = "CONTACT";

    public static final int REQUEST_ADD = 1;
    public static final int REQUEST_DELETE = 2;
    public static final int REQUEST_EDIT = 3;

    public ArrayList<Contact> contacts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

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
                Intent intent = new Intent(MainActivity.this,EditContactActivity.class);
                intent.putExtra(CONTACT_ARRAY_KEY,contacts);
                startActivityForResult(intent, REQUEST_EDIT);
            }
        });

        binding.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,DeleteContactActivity.class);
                intent.putExtra(CONTACT_ARRAY_KEY,contacts);
                startActivityForResult(intent,REQUEST_DELETE);
            }
        });

        binding.buttonDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ShowContactActivity.class);
                intent.putExtra(CONTACT_ARRAY_KEY,contacts);
                startActivity(intent);
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

    public void createNew(View view) {
        Intent intent = new Intent(MainActivity.this, CreateNewContact.class);
        startActivity(intent);

    }
}
