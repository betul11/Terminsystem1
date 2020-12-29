package com.example.terminsystem1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.sql.SQLException;
import java.util.ArrayList;

public class studentMyAppointmentsActivity extends AppCompatActivity {

    //student appointments
    RecyclerView studentAppointmentsRecycler;
    studentAppointmentsAdapter studentAppointmentsAdapter;
    RecyclerView.LayoutManager studentAppointmentsLayoutManager;
    static final String EXTRA_NAME = "studentEmail";
    ArrayList<appointment> myAppointments = new ArrayList<>();
    TextView emptyText;

    database db = new database();
    String userStudentEmail;
    int studentID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_my_appointments);

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

        if(myAppointments.isEmpty()){
            emptyText = findViewById(R.id.no_appointments_to_show);
            emptyText.setVisibility(View.VISIBLE);

        }
        else
        {
        studentAppointmentsRecycler = findViewById(R.id.studentMyAppointmentsRecycler);
        studentAppointmentsRecycler.setVisibility(View.VISIBLE);
        studentAppointmentsLayoutManager = new LinearLayoutManager(this);
        studentAppointmentsAdapter = new studentAppointmentsAdapter(myAppointments);
        studentAppointmentsRecycler.setLayoutManager(studentAppointmentsLayoutManager);
        studentAppointmentsRecycler.setAdapter(studentAppointmentsAdapter);
        }



    }
}