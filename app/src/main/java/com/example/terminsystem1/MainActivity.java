package com.example.terminsystem1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;

import java.sql.SQLException;


public class MainActivity extends AppCompatActivity {
    Button academicLogin;
    Button studentLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

    }

    public void studentLogin(View view) throws SQLException, ClassNotFoundException {

           Intent i = new Intent(this, studentLoginActivity.class);
                    startActivity(i);


    }

    public void academicLogin(View view) throws SQLException, ClassNotFoundException {

        Intent i = new Intent(this, academicLoginActivity.class);
        startActivity(i);


    }
}