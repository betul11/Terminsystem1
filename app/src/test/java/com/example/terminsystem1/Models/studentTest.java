package com.example.terminsystem1.Models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class studentTest {
    student student1, student2;

    @Before
    public void setUp() throws Exception {
        student1 = new student("test@gmail.com","passwooord");

    }


    @Test
    public void studentWithDifferentEmailAndPasswordReturnsFalse() {
        student2 = new student("test@test.com","hello");
        assertThat(student1.equals(student2)).isFalse();
    }

    @After
    public void tearDown() throws Exception {
    }
}
