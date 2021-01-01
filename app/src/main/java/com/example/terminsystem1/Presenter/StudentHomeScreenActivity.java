package com.example.terminsystem1.Presenter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.terminsystem1.Models.Database.database;
import com.example.terminsystem1.R;

import java.sql.SQLException;

public class StudentHomeScreenActivity extends AppCompatActivity {
    String studentEmail;
    Button searchAppointmentButton, myAppointmentsButton, changePasswordButton;
    static final String EXTRA_NAME = "studentEmail";
    Button savePassword;
    EditText oldPass,newPass;
    database db = new database();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home_screen);
        studentEmail = getIntent().getStringExtra(studentLoginActivity.EXTRA_NAME);
        searchAppointmentButton = (Button) findViewById(R.id.termin_create_button);
        myAppointmentsButton = (Button) findViewById(R.id.Studenttermin_list_button);
        changePasswordButton = (Button) findViewById(R.id.Studentpassword_settings_button);
    }

    public void searchAppointment(View view) {
        Intent i = new Intent(this, studentAppointmentActivity.class);


        i.putExtra(EXTRA_NAME, studentEmail);
        startActivity(i);

    }

    public void listStudentAppointments(View view) {
        Intent i = new Intent(this, studentMyAppointmentsActivity.class);
        i.putExtra(EXTRA_NAME, studentEmail);
        startActivity(i);

    }

    public void changeStudentPassword(View view) {
        setContentView(R.layout.change_student_password);

    }

    public void saveNewStudentPassword(View view) throws SQLException, ClassNotFoundException {
        oldPass = findViewById(R.id.oldPassText);
        newPass = findViewById(R.id.newPassText);
        savePassword = findViewById(R.id.change_password_Button);
        boolean checker = db.checkIfStudentPasswordIsCorrect(studentEmail,oldPass.getText().toString());

        if (checker==true){
            db.changeStudentPassword(studentEmail,newPass.getText().toString());
            Toast.makeText(getApplicationContext(), "password is changed successfully", Toast.LENGTH_SHORT).show(); //(inputEmail.isEmpty() || inputPassword.isEmpty())
            setContentView(R.layout.activity_student_home_screen);

        } else{
            Toast.makeText(getApplicationContext(), "Old password is incorrect!", Toast.LENGTH_SHORT).show(); //(inputEmail.isEmpty() || inputPassword.isEmpty())

        }


    }
}