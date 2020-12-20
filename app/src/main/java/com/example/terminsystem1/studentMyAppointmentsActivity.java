package com.example.terminsystem1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.sql.SQLException;
import java.util.ArrayList;

public class studentMyAppointmentsActivity extends AppCompatActivity {

    //student appointments
    RecyclerView studentAppointmentsRecycler;
    studentAppointmentsAdapter studentAppointmentsAdapter;
    RecyclerView.LayoutManager studentAppointmentsLayoutManager;
    database db = new database();
    String userStudentEmail;
    int studentID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_my_appointments);
        ArrayList<appointment> myAppointments = new ArrayList<>();
       userStudentEmail = getIntent().getStringExtra(StudentHomeScreenActivity.EXTRA_NAME);
        try {
            studentID = db.getStudentIdByEmail(userStudentEmail);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            myAppointments = db.getStudentAppointments(studentID);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        studentAppointmentsRecycler = findViewById(R.id.studentMyAppointmentsRecycler);
        studentAppointmentsLayoutManager = new LinearLayoutManager(this);
        studentAppointmentsAdapter = new studentAppointmentsAdapter(myAppointments);



    }
}