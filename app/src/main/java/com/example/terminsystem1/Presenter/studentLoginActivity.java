package com.example.terminsystem1.Presenter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.terminsystem1.Models.Database.database;
import com.example.terminsystem1.Models.student;
import com.example.terminsystem1.R;

import java.sql.ResultSet;
import java.sql.SQLException;

/* Github link:
 * https://github.com/betul11/Terminsystem1
 * */
public class studentLoginActivity extends AppCompatActivity {
    database db = new database();
    EditText eEmail;
    EditText ePassword;
    Button eLogin;
    static final String EXTRA_NAME = "studentEmail";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_login);


        eEmail = (EditText) findViewById(R.id.TextEmailAddress);           // Student E-Mail textID
        ePassword = (EditText) findViewById(R.id.editTextTextPassword);    // Student pass textID
        eLogin = (Button) findViewById(R.id.loginButton);
    }

    public void login(View view) throws SQLException, ClassNotFoundException {
        String inputEmail = eEmail.getText().toString();
        String inputPassword = ePassword.getText().toString();


        student newstudent = new student(inputEmail, inputPassword);

        ResultSet rs = db.studentLogin(newstudent);

        int counter = 0;
        try {
            while (rs.next()) {
                counter++;
            }
            if (counter == 1) {
                System.out.println("STUDENT FOUND");
                Toast.makeText(getApplicationContext(), "Redirecting...", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(this, StudentHomeScreenActivity.class);
                i.putExtra(EXTRA_NAME, inputEmail);
                startActivity(i);
            } else {
                System.out.println("STUDENT NOT FOUND");

                Toast.makeText(getApplicationContext(), "Please enter all the details correctly!", Toast.LENGTH_SHORT).show(); //(inputEmail.isEmpty() || inputPassword.isEmpty())
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}