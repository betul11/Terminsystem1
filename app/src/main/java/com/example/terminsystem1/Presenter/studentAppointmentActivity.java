package com.example.terminsystem1.Presenter;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.terminsystem1.Models.Database.database;
import com.example.terminsystem1.Models.academic;
import com.example.terminsystem1.Models.department;
import com.example.terminsystem1.Models.faculty;
import com.example.terminsystem1.R;
import com.example.terminsystem1.Presenter.Adapters.academicAdapter;
import com.example.terminsystem1.Presenter.Adapters.departmentAdapter;
import com.example.terminsystem1.Presenter.Adapters.facultyAdapter;

import java.sql.SQLException;
import java.util.ArrayList;

/* Github link:
 * https://github.com/betul11/Terminsystem1
 * */
public class studentAppointmentActivity extends AppCompatActivity {
   //faculty
    RecyclerView facultiesRecycler;
    com.example.terminsystem1.Presenter.Adapters.facultyAdapter facultyAdapter;
    RecyclerView.LayoutManager facultyLayoutManager;

    //department
    RecyclerView departmentsRecycler;
    com.example.terminsystem1.Presenter.Adapters.departmentAdapter departmentAdapter;
    RecyclerView.LayoutManager departmentLayoutManager;

    //academic
    RecyclerView academicRecycler;
    com.example.terminsystem1.Presenter.Adapters.academicAdapter academicAdapter;
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


               
            }
        });











    }
}