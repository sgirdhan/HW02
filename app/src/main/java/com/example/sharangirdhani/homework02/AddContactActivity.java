package com.example.sharangirdhani.homework02;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class AddContactActivity extends AppCompatActivity {
    //    private ActivityMainBinding binding;
    ImageButton img;
    EditText edtFirst;
    EditText edtLast;
    EditText edtCompany;
    EditText edtPhone;
    EditText edtURL;
    EditText edtAddress;
    EditText edtBirthdate;
    EditText edtNickname;
    EditText edtFacebook;
    EditText edtTwitter;
    EditText edtSkype;
    EditText edtYoutube;
    EditText edtEmail;
    String avatar;
    Contact contact;
    private static final int REQUEST_TAKE_PHOTO = 1;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    String mCurrentPhotoPath;
    Uri photoURI;
    int counterImage=0;
    boolean imageSelected;
    Calendar myCalendar;
    Date date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        intitialiseFields();
        date = null;
        imageSelected = false;
        counterImage=20;
        myCalendar = Calendar.getInstance();

        edtBirthdate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                DatePickerDialog dialog = new DatePickerDialog(AddContactActivity.this, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker arg0, int year, int month, int day) {
                        // TODO Auto-generated method stub
                        updateLabel(year, month+1, day);
                    }
                }, myCalendar.get(java.util.Calendar.YEAR), myCalendar.get(java.util.Calendar.MONTH), myCalendar.get(java.util.Calendar.DATE));


                myCalendar.set(1850,01,01);
                long miliS = myCalendar.getTimeInMillis();
                dialog.getDatePicker().setMinDate(miliS);
                dialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                dialog.show();
            }
        });
    }

    private void updateLabel(int year, int month, int day) {
        date = new Date(year, month, day);
        edtBirthdate.setText(String.valueOf(month)+"/"+String.valueOf(day)+"/"+String.valueOf(year));
    }

    private void intitialiseFields() {
        edtFirst = (EditText) findViewById(R.id.editTextName);
        edtLast = (EditText) findViewById(R.id.editTextLast);
        edtCompany = (EditText) findViewById(R.id.editTextCompany);
        edtPhone = (EditText) findViewById(R.id.editTextPhone);
        edtEmail = (EditText) findViewById(R.id.editTextEmail);
        edtURL = (EditText) findViewById(R.id.editTextURL);
        edtAddress  = (EditText) findViewById(R.id.editTextAddress);
        edtBirthdate = (EditText) findViewById(R.id.editTextBirthday);
        edtNickname = (EditText) findViewById(R.id.editTextNickname);
        edtFacebook = (EditText) findViewById(R.id.editTextFacebook);
        edtTwitter = (EditText) findViewById(R.id.editTextTwitter);
        edtSkype = (EditText) findViewById(R.id.editTextSkype);
        edtYoutube = (EditText) findViewById(R.id.editTextYoutube);
        img = (ImageButton)findViewById(R.id.imageButton);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bitmap bitmap = null;
            try {

                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), photoURI);
            } catch (IOException e) {
                e.printStackTrace();
            }
            img.setImageBitmap(bitmap);
            imageSelected = true;
        }

    }

    public void save(View view) {


        if (!isEmailValid(edtEmail.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Not a valid email!",
                    Toast.LENGTH_LONG).show();
        }
        else if (!isPhoneValid(edtPhone.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Not a valid Phone!",
                    Toast.LENGTH_LONG).show();
        } else if (!isMandatory(getFirst(), getLast(), getPhone())) {
            Toast.makeText(getApplicationContext(), "Please input the mandatory inputs",
                    Toast.LENGTH_LONG).show();
            //changes
        } else {
            if(!imageSelected){
                photoURI = Uri.parse("android.resource://com.example.sharangirdhani.homework02/drawable/dimage");
            }

            //change in constructor
            contact = new Contact(getFirst(), getLast(), getCompany(), getEmail(), getPhone(), getURL(), getAddress(), date, getNickName(), getFacebook(), getTwitter(), getSkype(), getYoutube(), photoURI.toString());
            Log.d("demo", contact.toString());
            Intent intent = new Intent();
            intent.putExtra(MainActivity.CONTACT_KEY, contact);
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    public void dispatch(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {

            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }

    }

    private File createImageFile() throws IOException {
        // Create an image file name

        String imageFileName = "JPEG_" + counterImage + "_";
        counterImage++;
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }


//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
//            Bundle extras = data.getExtras();
//            imageBitmap = (Bitmap) extras.get("data");
//            img = (ImageButton) findViewById(R.id.imageButton);
//
//            img.setImageBitmap(imageBitmap);
//        }
//    }

    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean isPhoneValid(String phone) {
        Pattern pattern = Pattern.compile("\\+?\\d{10,12}");
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }

    public boolean isMandatory(String first, String last, String phone){
        if(first.isEmpty() || first==null || last.isEmpty() || last == null || phone.isEmpty() || phone == null) {
            return false;
        }
        return true;
    }

    public String getFirst(){
        return edtFirst.getText().toString();
    }
    public String getLast(){
        return edtLast.getText().toString();
    }
    public String getPhone(){
        return edtPhone.getText().toString();
    }
    public String getYoutube(){
        return edtYoutube.getText().toString();
    }
    public String getCompany(){
        return edtCompany.getText().toString();
    }
    public String getSkype(){
        return edtSkype.getText().toString();
    }
    public String getAddress(){
        return edtAddress.getText().toString();
    }

    public String getFacebook(){
        return edtFacebook.getText().toString();
    }
    public String getNickName(){
        return edtNickname.getText().toString();
    }
    public String getEmail(){
        return edtEmail.getText().toString();
    }
    public String getURL(){
        return edtURL.getText().toString();
    }
    public String getTwitter(){
        return edtTwitter.getText().toString();
    }


}