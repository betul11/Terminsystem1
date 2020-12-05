package com.example.terminsystem1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

       Button studentLoginButton = (Button) findViewById(R.id.student_login_button);

        Button academicLoginButton = (Button) findViewById(R.id.academic_login_button);

        studentLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


               openLoginStudent();

            }
        });

        academicLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLoginAcademic();
            }
        });
    }

    public void openLoginStudent() {
        Intent intent = new Intent(this, studentAppointmentActivity.class);
        startActivity(intent);
    }


    public void openLoginAcademic() {
        Intent intent = new Intent(this, academicLoginActivity.class);
        startActivity(intent);
    }
}