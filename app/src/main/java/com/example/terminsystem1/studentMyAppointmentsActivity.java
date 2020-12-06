package com.example.terminsystem1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;

public class studentMyAppointmentsActivity extends AppCompatActivity {
database db = new database();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_my_appointments);
        ArrayList<appointment> myAppointments = new ArrayList<>();
       // myAppointments = db.getStudentAppointments();

    }
}