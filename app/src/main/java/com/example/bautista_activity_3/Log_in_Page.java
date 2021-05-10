package com.example.bautista_activity_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Log_in_Page extends AppCompatActivity {

    EditText text_username, text_pass;
    Button button_login, button_signup;
    DATABASE DB;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in__page);

        text_username = (EditText) findViewById(R.id.txt_login);
        text_pass = (EditText) findViewById(R.id.txt_password2);
        button_login = (Button) findViewById(R.id.btn_login);
        button_signup = (Button) findViewById(R.id.btn_signup);
        DB = new DATABASE(this);

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = text_username.getText().toString();
                String pass = text_pass.getText().toString();
                if(user.equals("")||pass.equals(""))
                    Toast.makeText(Log_in_Page.this, "Not all fields are entered", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass = DB.checkusernamepassword(user,pass);
                    if(checkuserpass==true){
                        Toast.makeText(Log_in_Page.this, "Login Success", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),Home_Page.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(Log_in_Page.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        button_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }
}