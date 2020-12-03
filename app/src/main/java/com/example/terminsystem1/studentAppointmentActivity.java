package com.example.terminsystem1;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class studentAppointmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_appointment);
        Spinner facultySpinner = (Spinner) findViewById(R.id.facultySpinner);
        Spinner departmentSpinner = (Spinner) findViewById(R.id.departmentSpinner);
        Spinner academicSpinner = (Spinner) findViewById(R.id.academicSpinner);
        TextView date = (TextView) findViewById(R.id.appointmentDate);
        database db = new database();
        List<String> faculties = null;
        try {
            faculties = db.getAllFaculties();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        ArrayAdapter<String> facultyAdapter= new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,faculties);

        facultyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        facultySpinner.setAdapter(facultyAdapter);

/*


        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        facultySpinner.setAdapter(adapter);

*/

    }
}