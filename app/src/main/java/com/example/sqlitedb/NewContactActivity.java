package com.example.sqlitedb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NewContactActivity extends AppCompatActivity {


    EditText ContactName, ContactMobile, ContactEmail;
    Context context = this;
    SQLiteDatabase sqLiteDatabase;
    UserDbHelper userDbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);

        ContactName =  findViewById(R.id.et_contact_name);
        ContactMobile =  findViewById(R.id.et_mobile_number);
        ContactEmail =  findViewById(R.id.et_email_address);
    }

    public void saveInformationInsertActivity(View view)
    {

        String name = ContactName.getText().toString();
        String mob = ContactMobile.getText().toString();
        String email = ContactEmail.getText().toString();
        userDbHelper = new UserDbHelper(context);
        sqLiteDatabase = userDbHelper.getWritableDatabase();
        userDbHelper.addInformations(name,mob,email,sqLiteDatabase);
        Toast.makeText(getApplicationContext(), "Data Saved", Toast.LENGTH_SHORT).show();
        userDbHelper.close();
        finish();
    }
}