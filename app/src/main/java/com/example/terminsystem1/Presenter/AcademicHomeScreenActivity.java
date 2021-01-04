package com.example.terminsystem1.Presenter;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.terminsystem1.Models.Database.database;
import com.example.terminsystem1.Models.appointment;
import com.example.terminsystem1.R;
import com.example.terminsystem1.Presenter.Adapters.academicAppointmentsAdapter;

import java.sql.SQLException;
import java.util.ArrayList;

/* Github link:
 * https://github.com/betul11/Terminsystem1
 * */
public class AcademicHomeScreenActivity extends AppCompatActivity {
    RecyclerView academicAppointmentsRecycler;
    com.example.terminsystem1.Presenter.Adapters.academicAppointmentsAdapter academicAppointmentsAdapter;
    RecyclerView.LayoutManager academicAppointmentsLayoutManager;
    TextView emptyText;
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
            //get academic's appointments list
            myAppointments = db.getAcademicAppointments(academicID);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //if the list is empty, display the empty list message
        if(myAppointments.isEmpty()){

            emptyText = findViewById(R.id.no_appointments_to_display);
            emptyText.setVisibility(View.VISIBLE);


        }else {
            // lists all appointments as a CardView inflated in a RecyclerView
            academicAppointmentsRecycler = findViewById(R.id.academicAppointmentsRecycler);
            academicAppointmentsRecycler.setVisibility(View.VISIBLE);
            academicAppointmentsLayoutManager = new LinearLayoutManager(this);
            academicAppointmentsAdapter = new academicAppointmentsAdapter(myAppointments);
            academicAppointmentsRecycler.setLayoutManager(academicAppointmentsLayoutManager);
            academicAppointmentsRecycler.setAdapter(academicAppointmentsAdapter);

            academicAppointmentsAdapter.setOnItemClickListener(new academicAppointmentsAdapter.onItemClickListener() {
                @Override
                public void onItemClick(int position) throws SQLException {
                  // not used for now but can be implemented later
                }

                @Override
                public void onAcceptClick(int position) throws SQLException, ClassNotFoundException {
                    //when "accept" icon is clicked
                    ImageView acceptRequest = findViewById(R.id.image_accept);
                    appointment acceptedAppointment = myAppointments.get(position);
                    int acceptedAppointmentID = acceptedAppointment.getAppointmentID();
                    // change the clicked appointment's status to "accepted":
                    db.acceptRequest(acceptedAppointmentID);
                    // hide the "accept" icon:
                    acceptRequest.setImageResource(0);


                    academicAppointmentsAdapter.notifyItemChanged(position);
                    academicAppointmentsAdapter.notifyDataSetChanged();

                }

                @Override
                public void onDenyClick(int position) throws SQLException, ClassNotFoundException {
                    //when "delete" icon is clicked
                    appointment deniedAppointment = myAppointments.get(position);
                    int deniedAppointmentID = deniedAppointment.getAppointmentID();
                    db.denyRequest(deniedAppointmentID);
                    // remove the denied request from the list:
                    myAppointments.remove(position);
                    academicAppointmentsAdapter.notifyItemRemoved(position);


                }


            });
        }



    }

}