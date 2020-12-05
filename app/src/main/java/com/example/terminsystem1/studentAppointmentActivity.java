package com.example.terminsystem1;

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
    RecyclerView facultiesRecycler;
    facultyAdapter facultyAdapter;
    RecyclerView.LayoutManager facultyLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_appointment);

        ArrayList<faculty> faculties = new ArrayList<>();
        /*faculties.add(new faculty("faculty1"));
        faculties.add(new faculty("faculty2"));
        faculties.add(new faculty("faculty3"));
        faculties.add(new faculty("faculty4"));*/



        database db = new database();


        try {
            faculties = db.getAllFaculties();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        facultiesRecycler = findViewById(R.id.searchAppointmentRecycler);
        facultiesRecycler.setHasFixedSize(true);
        facultyLayoutManager = new LinearLayoutManager(this);
        facultyAdapter = new facultyAdapter(faculties);

        facultiesRecycler.setLayoutManager(facultyLayoutManager);
        facultiesRecycler.setAdapter(facultyAdapter);
        ArrayList<faculty> finalFaculties = faculties;
        facultyAdapter.setOnItemClickListener(new facultyAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position) {


                finalFaculties.get(position).openDepartments("department1");
                facultyAdapter.notifyItemChanged(position);
            }
        });



    }
}