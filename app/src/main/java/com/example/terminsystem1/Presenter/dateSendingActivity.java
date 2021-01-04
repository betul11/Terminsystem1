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

/* Github link:
 * https://github.com/betul11/Terminsystem1
 * */
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
               String timetext = timeText.getText().toString();

               // check if time input is empty:
              if(timetext.matches("")){
                  String timeMissing = getString(R.string.time_missing);
                  Toast.makeText(dateSendingActivity.this, timeMissing, Toast.LENGTH_LONG).show();
                  return;

              }
                int day= picker.getDayOfMonth();
                int month = picker.getMonth();
                int year = picker.getYear();
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, day);

                // show error message if the specified day is Saturday or Sunday:
                if((calendar.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY) ||
                        (calendar.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY)){
                    String notBusinessDay = getString(R.string.not_business_day);
                    Toast.makeText(dateSendingActivity.this, notBusinessDay , Toast.LENGTH_LONG).show();
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
                      //entered if no other appointments with the same date and time exists

                        String requestSent = getString(R.string.appointment_request_sent);

                        Toast.makeText(dateSendingActivity.this, requestSent , Toast.LENGTH_LONG).show();

                    }else if(result==2){
                        //entered if an appointment with the same date and time already exits.
                        String noAvailableAppointment = getString(R.string.no_available_appointment);
                        Toast.makeText(dateSendingActivity.this, noAvailableAppointment , Toast.LENGTH_LONG).show();


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