package com.example.sharangirdhani.homework02;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class ShowContactActivity extends AppCompatActivity {
    Contact contact;
    Bitmap bitmap;
    ImageView imgButton;
    TextView fullName, company, phone, email, url, address, nickname, birthday, facebookUrl, twitterUrl, skype, youtube;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_contact);

        if (getIntent().getExtras() != null && getIntent().getExtras().containsKey(ContactListActivity.INTENT_KEY)) {
            contact = (Contact) getIntent().getExtras().getSerializable(ContactListActivity.INTENT_KEY);
        }
        else {
            // Error handling
            Log.e("hw2","Show Activity called without any intent. This should not happen");
            finish();
        }

//        try {
//            bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), Uri.parse(contact.getAvatar()));
//        } catch (IOException e) {
//            bitmap = null;
//            e.printStackTrace();
//        }

        imgButton = (ImageView) findViewById(R.id.imageContact);
        fullName = (TextView) findViewById(R.id.display_fullName);
        company = (TextView) findViewById(R.id.display_company);
        phone = (TextView) findViewById(R.id.display_phone);
        email = (TextView) findViewById(R.id.display_email);
        url = (TextView) findViewById(R.id.display_url);
        address = (TextView) findViewById(R.id.display_address);
        nickname = (TextView) findViewById(R.id.display_nickname);
        birthday = (TextView) findViewById(R.id.display_birthday);
        facebookUrl = (TextView) findViewById(R.id.display_facebookUrl);
        twitterUrl = (TextView) findViewById(R.id.display_twitterUrl);
        skype = (TextView) findViewById(R.id.display_skype);
        youtube = (TextView) findViewById(R.id.display_youtube);

        // Set all values
        Bitmap bitmap = null;
        try {
            Log.d("demo", contact.getAvatar());
            bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), Uri.parse(contact.getAvatar()));
        } catch (IOException e) {}

        imgButton.setImageBitmap(bitmap);
        //imgButton.setImageBitmap(bitmap);
        fullName.setText(contact.getFirstName() + " " + contact.getLastName());
        company.setText(contact.getCompany());
        phone.setText(contact.getPhone());
        email.setText(contact.getEmail());
        url.setText(contact.getUrl());
        address.setText(contact.getAddress());
        nickname.setText(contact.getNickname());
        //birthday.setText(Contact.dateFormat.format(contact.getBirthdate()));
        facebookUrl.setText(contact.getFacebookUrl());
        twitterUrl.setText(contact.getTwitterUrl());
        skype.setText(contact.getSkype());
        youtube.setText(contact.getYoutube());


        // Making all URLs clickable
        url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urlString = contact.getUrl();
                if (!urlString.startsWith("http://") && !urlString.startsWith("https://"))
                    urlString = "http://" + urlString;

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlString));
                startActivity(intent);
            }
        });

        facebookUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urlString = contact.getFacebookUrl();
                if (!urlString.startsWith("http://") && !urlString.startsWith("https://"))
                    urlString = "http://" + urlString;

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlString));
                startActivity(intent);
            }
        });

        skype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urlString = contact.getSkype();
                if (!urlString.startsWith("http://") && !urlString.startsWith("https://"))
                    urlString = "http://" + urlString;

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlString));
                startActivity(intent);
            }
        });

        twitterUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urlString = contact.getTwitterUrl();
                if (!urlString.startsWith("http://") && !urlString.startsWith("https://"))
                    urlString = "http://" + urlString;

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlString));
                startActivity(intent);
            }
        });

        youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urlString = contact.getYoutube();
                if (!urlString.startsWith("http://") && !urlString.startsWith("https://"))
                    urlString = "http://" + urlString;

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlString));
                startActivity(intent);
            }
        });
    }
}
