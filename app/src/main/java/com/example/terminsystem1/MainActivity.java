package com.example.terminsystem1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.sql.SQLException;

import java.sql.ResultSet;


public class MainActivity extends AppCompatActivity {
    database db = new database();
    EditText eEmail;
    EditText ePassword;
    Button eLogin;
    RadioGroup radioGroup;
    RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        eEmail = (EditText) findViewById(R.id.TextEmailAddress);           // Student E-Mail textID
        ePassword = (EditText) findViewById(R.id.editTextTextPassword);    // Student pass textID
        eLogin = (Button) findViewById(R.id.loginButton);
        radioGroup = findViewById(R.id.radioAcademicStudent);


    }

    public void login(View view) throws SQLException, ClassNotFoundException {
        int radioID = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioID);
        String radio = (String) radioButton.getText();
        String inputEmail = eEmail.getText().toString();
        String inputPassword = ePassword.getText().toString();
        if(radio == "Student") {
            student newstudent = new student(inputEmail, inputPassword);

            ResultSet rs = db.studentLogin(newstudent);

            int counter = 0;
            try {
                while (rs.next()) {
                    counter++;
                }
                if (counter == 1) {
                    Toast.makeText(getApplicationContext(), "Redirecting...", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(this, StudentHomeScreenActivity.class);
                    i.putExtra("studentEmail", inputEmail);
                    startActivity(i);
                } else {
                    Toast.makeText(getApplicationContext(), "Please enter all the details correctly!", Toast.LENGTH_SHORT).show(); //(inputEmail.isEmpty() || inputPassword.isEmpty())
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else if(radio== "Academic"){
            academic newacademic = new academic(inputEmail, inputPassword);
            ResultSet rs = db.academicLogin(newacademic);
            int counter = 0;

            try {
                while (rs.next()) {
                    counter++;
                }
                if (counter == 1) {
                    Toast.makeText(getApplicationContext(), "Redirecting...", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(this, StudentHomeScreenActivity.class);
                    i.putExtra("academicEmail", inputEmail);
                    startActivity(i);
                } else {
                    Toast.makeText(getApplicationContext(), "Please enter all the details correctly!", Toast.LENGTH_SHORT).show(); //(inputEmail.isEmpty() || inputPassword.isEmpty())
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }



        }

    }
}