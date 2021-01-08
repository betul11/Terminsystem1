package com.example.terminsystem1.Presenter;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.contrib.RecyclerViewActions;

import com.example.terminsystem1.R;
import com.example.terminsystem1.ToastMatcher;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.SQLException;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


/* Github link:
 * https://github.com/betul11/Terminsystem1
 * */

@RunWith(MockitoJUnitRunner.class)
public class studentUserTests {

    @Before
    public void setUp() throws Exception {
        ActivityScenario activityScenarioMain = ActivityScenario.launch(MainActivity.class);


    }

    @Test
    public void testLogin() throws SQLException, ClassNotFoundException {
        //click "student login" on main activity
        onView(withId(R.id.studentLoginButton)).perform(click());
        // type in student email
        onView(withId(R.id.TextEmailAddress)).perform(clearText(),typeText("e170503109@stud.tau.edu.tr"));
        // type in password
        onView(withId(R.id.editTextTextPassword)).perform(clearText(),typeText("test"));
        // click on login button
        onView(withId(R.id.loginButton)).perform(click());
        // check if "take an appointment button" which belongs to the next activity is displayed
        onView(withId(R.id.termin_create_button)).check(matches(isDisplayed()));
    }

    @Test
    public void testIfStudentAppointmentsAreInView(){
        /* The RecyclerView which displays the student's appointments is invisible
        * if no upcoming appointments existed, that's why checking for its visibility verifies wether
        * they are being listed or not */

        // NOTE: verify that at least one appointment with the student's Id exist before performing this test

        //click "student login" on main activity
        onView(withId(R.id.studentLoginButton)).perform(click());
        // type in student email
        onView(withId(R.id.TextEmailAddress)).perform(clearText(),typeText("e170503109@stud.tau.edu.tr"));
        // type in password
        onView(withId(R.id.editTextTextPassword)).perform(clearText(),typeText("test"));
        // click on login button
        onView(withId(R.id.loginButton)).perform(click());
        // check if "take an appointment button" which belongs to the next activity is displayed
        onView(withId(R.id.termin_create_button)).check(matches(isDisplayed()));
        // click on "My Appointments"
        onView(withId(R.id.Studenttermin_list_button)).perform(click());
        // check if the appointments recyclerView is in view
        onView(withId(R.id.studentMyAppointmentsRecycler)).check(matches(isDisplayed()));

    }

    @Test
    public void testSendValidAppointmentRequest() throws SQLException, ClassNotFoundException {
            /* Date and time input must be changed with valid new input on every run */
        // click "student login" on main activity
        onView(withId(R.id.studentLoginButton)).perform(click());
        // type in student email
        onView(withId(R.id.TextEmailAddress)).perform(clearText(),typeText("e170503109@stud.tau.edu.tr"));
        // type in password
        onView(withId(R.id.editTextTextPassword)).perform(clearText(),typeText("test"));
        // click on login button
        onView(withId(R.id.loginButton)).perform(click());
        // check if "take an appointment button" is displayed
         onView(withId(R.id.termin_create_button)).check(matches(isDisplayed()));
         // click on "take an appointment" button
        onView(withId(R.id.termin_create_button)).perform(click());
        // click on position 0 in RecyclerView (Fakultaet für Naturwissenschaft)
        onView(withId(R.id.searchAppointmentRecycler))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));
        // click on position 1 in RecyclerView (Molekulare Biotechnologie)
        onView(withId(R.id.searchAppointmentRecycler))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
        // click on position 2 in RecyclerView
        onView(withId(R.id.searchAppointmentRecycler))
                .perform(RecyclerViewActions.actionOnItemAtPosition(3, click()));
        // pick appointment's date
        onView(withId(R.id.datePicker1)).perform(PickerActions.setDate(2021, 01, 12));
        // fill in appointment's time
        onView(withId(R.id.editTextTime))
                .perform(clearText(),typeText("15:30"));
        // close keyboard
        closeSoftKeyboard();
        // click on send
        onView(withId(R.id.send_date))
                .perform(click());
        // check if "appointment request sent" toast is in view
        onView(withText(R.string.appointment_request_sent)).inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));




    }


}