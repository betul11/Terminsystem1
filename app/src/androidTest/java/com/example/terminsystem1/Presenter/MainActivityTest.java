package com.example.terminsystem1.Presenter;

import androidx.test.core.app.ActivityScenario;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.example.terminsystem1.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4ClassRunner.class)
public class MainActivityTest {

    @Before
    public void setUp() throws Exception {
        ActivityScenario activityScenario = ActivityScenario.launch(MainActivity.class);

    }

    @Test
    public void testMainActivityButtonsInView() {
        onView(withId(R.id.studentLoginButton)).check(matches(isDisplayed()));
        onView(withId(R.id.academicLoginButton)).check(matches(isDisplayed()));

    }


    @Test
    public void testNavigationToStudentLogin() {
        onView(withId(R.id.studentLoginButton)).perform(click());
        onView(withId(R.id.TextEmailAddress)).check(matches(isDisplayed()));

    }

    @Test
    public void testNavigationToAcademicLogin(){
        onView(withId(R.id.academicLoginButton)).perform(click());
        onView(withId(R.id.editTextTextPassword)).check(matches(isDisplayed()));
    }

    @After
    public void tearDown() throws Exception {
    }
}