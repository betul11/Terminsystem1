package com.example.terminsystem1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        Intent intent = new Intent(this, studentLoginActivity.class);
        startActivity(intent);
    }

    public void openLoginAcademic() {
        Intent intent = new Intent(this, academicLoginActivity.class);
        startActivity(intent);
    }
}