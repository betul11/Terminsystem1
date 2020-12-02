package com.example.terminsystem1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.terminsystem1.R;

public class studentAppointment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_appointment);
        Spinner facultySpinner = (Spinner) findViewById(R.id.facultySpinner);
        Spinner departmentSpinner = (Spinner) findViewById(R.id.departmentSpinner);
        Spinner academicSpinner = (Spinner) findViewById(R.id.academicSpinner);
        TextView date = (TextView) findViewById(R.id.appointmentDate);

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