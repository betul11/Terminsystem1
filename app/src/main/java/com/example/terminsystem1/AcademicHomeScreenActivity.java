package com.example.terminsystem1;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.SQLException;
import java.util.ArrayList;

public class AcademicHomeScreenActivity extends AppCompatActivity {
    RecyclerView academicAppointmentsRecycler;
    academicAppointmentsAdapter academicAppointmentsAdapter;
    RecyclerView.LayoutManager academicAppointmentsLayoutManager;
    ArrayList<appointment> myAppointments = new ArrayList<>();
    database db = new database();
    String userAcademicEmail;
    int academicID;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academic_home_screen);
        userAcademicEmail = getIntent().getStringExtra(academicLoginActivity.EXTRA_NAME);
        try {
            academicID = db.getAcademicIdByEmail(userAcademicEmail);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        try {
            myAppointments = db.getAcademicAppointments(academicID);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        academicAppointmentsRecycler = findViewById(R.id.academicAppointmentsRecycler);
        academicAppointmentsLayoutManager = new LinearLayoutManager(this);
        academicAppointmentsAdapter = new academicAppointmentsAdapter(myAppointments);
        academicAppointmentsRecycler.setLayoutManager(academicAppointmentsLayoutManager);
        academicAppointmentsRecycler.setAdapter(academicAppointmentsAdapter);

        academicAppointmentsAdapter.setOnItemClickListener(new academicAppointmentsAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position) throws SQLException {

            }

            @Override
            public void onAcceptClick(int position) throws SQLException, ClassNotFoundException {
                ImageView acceptRequest = findViewById(R.id.image_accept);
                appointment acceptedAppointment = myAppointments.get(position);
                int acceptedAppointmentID = acceptedAppointment.getAppointmentID();
                db.acceptRequest(acceptedAppointmentID);
                acceptRequest.setImageResource(0);


                academicAppointmentsAdapter.notifyItemChanged(position);
                academicAppointmentsAdapter.notifyDataSetChanged();

            }

            @Override
            public void onDenyClick(int position) throws SQLException, ClassNotFoundException{
                appointment deniedAppointment = myAppointments.get(position);
                int deniedAppointmentID = deniedAppointment.getAppointmentID();
                db.denyRequest(deniedAppointmentID);
                myAppointments.remove(position);
                academicAppointmentsAdapter.notifyItemRemoved(position);


            }


        });



    }

}