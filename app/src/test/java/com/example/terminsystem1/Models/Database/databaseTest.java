package com.example.terminsystem1.Models.Database;

import com.example.terminsystem1.Models.student;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class databaseTest {

    @Mock
    private Statement mockStatement;

    @Mock
    private ResultSet rs;

    @Mock
    Connection mockConnection;

    @Mock
    student user;
    @InjectMocks database db;


    /* Github link:
     * https://github.com/betul11/Terminsystem1
     * */

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        when(rs.first()).thenReturn(true);

    }

    @Test
    public void getStudentIdByEmailMethodTest() throws SQLException, ClassNotFoundException {
        when(mockConnection.createStatement()).thenReturn(mockStatement);
       // when(mockConnection.createStatement().executeQuery(Mockito.any())).thenReturn(2);
        String email = "e170503109@stud.tau.edu.tr";
        int id = 2;

        int result = db.getStudentIdByEmail(email);
        assertEquals(id, result);

    }

    @Test
    public void checkIfStudentPasswordIsCorrect() throws SQLException,ClassNotFoundException{
        String email = "e170503109@stud.tau.edu.tr";
        String password = "test";
        assertThat(db.checkIfStudentPasswordIsCorrect(email, password)).isTrue();

    }

    @Test
    public void studentLogin() throws SQLException, ClassNotFoundException {
        user = new student("e170503109@stud.tau.edu.tr", "test");
        rs = db.studentLogin(user);
        assertThat(rs.next());

    }

    @After
    public void tearDown() throws Exception {
    }
}