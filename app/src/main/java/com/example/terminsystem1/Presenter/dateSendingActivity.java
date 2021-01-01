package com.example.terminsystem1.Presenter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.terminsystem1.Models.Database.database;
import com.example.terminsystem1.R;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class dateSendingActivity extends AppCompatActivity {
    DatePicker picker;
    EditText timeText;
    Button send_date_button;
    String time;
    static final String EXTRA_NAME = "studentEmail";
    static final String ACADEMIC_ID = "academicID";
    int academicID;
    String userStudentEmail;
    int userStudentID;

    database db = new database();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_sending);
        academicID = getIntent().getIntExtra(studentAppointmentActivity.ACADEMIC_ID,-1);
        userStudentEmail=getIntent().getStringExtra(studentAppointmentActivity.EXTRA_NAME);

        send_date_button = (Button) findViewById(R.id.send_date);
        picker=(DatePicker)findViewById(R.id.datePicker1);
        timeText= (EditText) findViewById(R.id.editTextTime);

        send_date_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   if(!timeText.getText().toString().isEmpty()){
                    String time = timeText.getText().toString();

               // }
                int day= picker.getDayOfMonth();
                int month = picker.getMonth();
                int year = picker.getYear();
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, day);
                if((calendar.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY) ||
                        (calendar.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY)){
                    Toast.makeText(dateSendingActivity.this, "The specified date is not a business day " , Toast.LENGTH_LONG).show();
                      return;
                }


                Date date = calendar.getTime();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                sdf.format(date);
                try {
                    userStudentID = db.getStudentIdByEmail(userStudentEmail);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                try {
                    int result = db.getAvailableAppointment(academicID,userStudentID,date,time);
                    if(result==1){

                        Toast.makeText(dateSendingActivity.this, "Appointment request is sent"  , Toast.LENGTH_LONG).show();

                    }else if(result==2){
                        Toast.makeText(dateSendingActivity.this, "No available appointments found! Try a different date or time"  , Toast.LENGTH_LONG).show();


                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

            }
        });



    }
}