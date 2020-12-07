package com.example.terminsystem1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class studentMyAppointmentsActivity extends AppCompatActivity {

    //student appointments
    RecyclerView studentAppointmentsRecycler;
    studentAppointmentsAdapter studentAppointmentsAdapter;
    RecyclerView.LayoutManager studentAppointmentsLayoutManager;
database db = new database();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_my_appointments);
        ArrayList<appointment> myAppointments = new ArrayList<>();
       // myAppointments = db.getStudentAppointments();

    }
}