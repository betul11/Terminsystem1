package com.example.terminsystem1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class StudentHomeScreenActivity extends AppCompatActivity {
    String studentEmail;
    Button searchAppointmentButton, myAppointmentsButton, changePasswordButton;
    static final String EXTRA_NAME = "studentEmail";

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

    }
}