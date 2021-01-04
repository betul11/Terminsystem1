package com.example.terminsystem1.Presenter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.terminsystem1.Models.Database.database;
import com.example.terminsystem1.Models.academic;
import com.example.terminsystem1.Presenter.AcademicHomeScreenActivity;
import com.example.terminsystem1.R;

import java.sql.ResultSet;
import java.sql.SQLException;

/* Github link:
 * https://github.com/betul11/Terminsystem1
 * */
public class academicLoginActivity extends AppCompatActivity {
    database db = new database();
    EditText eEmail;
    EditText ePassword;
    Button eLogin;
    static final String EXTRA_NAME = "academicEmail";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.academic_login);

        eEmail = (EditText) findViewById(R.id.TextEmailAddress);           // Student E-Mail textID
        ePassword = (EditText) findViewById(R.id.editTextTextPassword);    // Student pass textID
        eLogin = (Button) findViewById(R.id.loginButton);
    }

    public void login(View view) throws SQLException, ClassNotFoundException {
        String inputEmail = eEmail.getText().toString();
        String inputPassword = ePassword.getText().toString();

        academic newacademic = new academic(inputEmail, inputPassword);
        ResultSet rs = db.academicLogin(newacademic);
        int counter = 0;

        try {
            while (rs.next()) {
                counter++;
            }
            if (counter == 1) {
                // entered if an academic who has the email and password specified by the user was found
                // meaning that the login information were correct


                String redirecting = getString(R.string.Redirecting);
                Toast.makeText(getApplicationContext(), redirecting, Toast.LENGTH_SHORT).show();
                Intent i = new Intent(this, AcademicHomeScreenActivity.class);
                i.putExtra(EXTRA_NAME, inputEmail);

                startActivity(i);
            } else {
                // entered if the no such academic user was found

                String detailsCorrectly = getString(R.string.detailsCorrectly);
                // error message is displayed
                Toast.makeText(getApplicationContext(),detailsCorrectly , Toast.LENGTH_SHORT).show();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}