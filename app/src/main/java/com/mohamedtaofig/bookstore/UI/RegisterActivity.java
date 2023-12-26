package com.mohamedtaofig.bookstore.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mohamedtaofig.bookstore.R;

public class RegisterActivity extends AppCompatActivity {
    private EditText nameField,passwordField,emailField,confirmPasswordField;
    private Button submit;
    private SharedPreferences sharedPreferences;
    private static final String MYKEY = "secret";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nameField = findViewById(R.id.register_name_field);
        passwordField = findViewById(R.id.register_password_field);
        emailField = findViewById(R.id.register_email_field);

        submit = findViewById(R.id.register_submit);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences = getSharedPreferences(MYKEY,0);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("name",nameField.getText().toString());
                editor.putString("email",emailField.getText().toString());
                editor.putString("password",passwordField.getText().toString().trim());
                editor.commit();


                if (TextUtils.isEmpty(nameField.getText().toString())){
                    nameField.setError("الرجاء ادخال الاسم");
                    return;
                }
                if (TextUtils.isEmpty(emailField.getText().toString())){
                    nameField.setError("الرجاء ادخال البريد الالكتروني");
                    return;
                }
                startActivity(new Intent(RegisterActivity.this,MainActivity.class));
            }
        });


    }
}