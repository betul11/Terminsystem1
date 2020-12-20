package com.example.terminsystem1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class studentAppointmentActivity extends AppCompatActivity {
   //faculty
    RecyclerView facultiesRecycler;
    facultyAdapter facultyAdapter;
    RecyclerView.LayoutManager facultyLayoutManager;

    //department
    RecyclerView departmentsRecycler;
    departmentAdapter departmentAdapter;
    RecyclerView.LayoutManager departmentLayoutManager;

    //academic
    RecyclerView academicRecycler;
    academicAdapter academicAdapter;
    RecyclerView.LayoutManager academicLayoutManager;

    database db = new database();
    ArrayList<department> departments = new ArrayList<department>();
    ArrayList<faculty> faculties = new ArrayList<>();
    ArrayList<academic> academics = new ArrayList<>();

    String userStudentEmail;
    static final String EXTRA_NAME = "studentEmail";
    static final String ACADEMIC_ID = "academicID";






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_appointment);
        userStudentEmail = getIntent().getStringExtra(StudentHomeScreenActivity.EXTRA_NAME);


        try {
            faculties = db.getAllFaculties();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //faculties list
        facultiesRecycler = findViewById(R.id.searchAppointmentRecycler);
       // facultiesRecycler.setHasFixedSize(true);
        facultyLayoutManager = new LinearLayoutManager(this);
        facultyAdapter = new facultyAdapter(faculties);

        facultiesRecycler.setLayoutManager(facultyLayoutManager);
        facultiesRecycler.setAdapter(facultyAdapter);


        facultyAdapter.setOnItemClickListener(new facultyAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position) throws SQLException {

                departments = db.getRelevantDepartment(faculties.get(position).getFacultyID());
                //departments list

                departmentsRecycler = findViewById(R.id.searchAppointmentRecycler);
                //departmentsRecycler.setHasFixedSize(true);

                departmentAdapter = new departmentAdapter(departments);
                departmentLayoutManager = new LinearLayoutManager(getApplicationContext());

                departmentsRecycler.setLayoutManager(departmentLayoutManager);
                departmentsRecycler.setAdapter(departmentAdapter);


                departmentAdapter.setOnItemClickListener(new departmentAdapter.onItemClickListener() {
                    @Override
                    public void onItemClick(int position) throws SQLException, ClassNotFoundException {

                        academics = db.getRelevantAcademic(departments.get(position).getDepartmentID());
                        //academics list

                        academicRecycler = findViewById(R.id.searchAppointmentRecycler);
                        academicRecycler.setHasFixedSize(true);
                        academicLayoutManager = new LinearLayoutManager(getApplicationContext());
                        academicAdapter = new academicAdapter(academics);
                        academicRecycler.setLayoutManager(academicLayoutManager);
                        academicRecycler.setAdapter(academicAdapter);
                        academicAdapter.setOnItemClickListener(new academicAdapter.onItemClickListener() {
                            @Override
                            public void onItemClick(int position) throws SQLException, ClassNotFoundException {
                                        int academicID = academics.get(position).getAcademicID();
                                        Intent intent = new Intent(studentAppointmentActivity.this, dateSendingActivity.class);
                                        intent.putExtra(EXTRA_NAME, userStudentEmail);
                                        intent.putExtra(ACADEMIC_ID,academicID );
                                        startActivity(intent);




                            }
                        });

                    }
                });


               
                //departmentAdapter.notifyItemChanged(position);
            }
        });











    }
}