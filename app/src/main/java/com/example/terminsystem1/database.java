package com.example.terminsystem1;

import android.os.AsyncTask;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class database extends AsyncTask<String,Void,String> {
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {

        String dbHost = "pharzan.c6enfwnyrdpp.eu-central-1.rds.amazonaws.com";
        String dbPort = "3306";
        String dbUser = "beatle";
        String dbPass = "beidas";

        String connectionString = "jdbc:mysql://" + dbHost + ":"
                + dbPort + "/batool";
        Class.forName("com.mysql.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        return dbConnection;
    }

    public database() {
    }

    @Override
    protected String doInBackground(String... strings) {
        return null;
    }

    public ArrayList<department> getRelevantDepartment(int facultyId) throws SQLException {
        String query = "SELECT * FROM " + Const.DEPARTMENTS_TABLE + " WHERE " + Const.DEPARTMENT_FACULTY_ID + " = ?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = getDbConnection().prepareStatement(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        preparedStatement.setInt(1, facultyId);
        ResultSet resultSet = preparedStatement.executeQuery();
        ArrayList<department> departments = new ArrayList<>();

        while (resultSet.next()) {
            departments.add(new department(resultSet.getInt(Const.DEPARTMENT_ID),
                    resultSet.getString(Const.DEPARTMENT_NAME),
                    resultSet.getInt(Const.DEPARTMENT_FACULTY_ID)));
        }


        return departments;
    }


    public ResultSet studentLogin(student user) throws SQLException, ClassNotFoundException {
        System.out.println("INSIDE DATABASE CLASS");
            String query = "SELECT * FROM " + Const.STUDENTS_TABLE + " WHERE " + Const.STUDENT_EMAIL + "= ? AND " +
                    Const.STUDENT_PASSWORD + "= ?";

            PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());

            ResultSet resultSet = preparedStatement.executeQuery();


            return resultSet;
        }


        public ArrayList<faculty> getAllFaculties () throws SQLException, ClassNotFoundException {
            String query = "SELECT * FROM fakultaet";
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<faculty> faculties = new ArrayList<>();
            while (resultSet.next()) {
                faculties.add(new faculty(resultSet.getInt(Const.FACULTY_ID), resultSet.getString(Const.FACULTY_NAME)));
            }

            return faculties;
        }


        public ArrayList<academic> getRelevantAcademic ( int departmentID) throws
        SQLException, ClassNotFoundException {
            String query = "SELECT * FROM " + Const.ACADEMIC_TABLE + " WHERE " +
                    Const.ACADEMIC_DEPARTMENT_ID + " = ?";

            PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
            preparedStatement.setInt(1, departmentID);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<academic> academics = new ArrayList<>();

            while (resultSet.next()) {
                academics.add(new academic(resultSet.getInt(Const.ACADEMIC_ID),
                        resultSet.getString(Const.ACADEMIC_NAME)
                ));
            }

            return academics;
        }

        public ArrayList<appointment> getStudentAppointments ( int studentID) throws
        SQLException, ClassNotFoundException {
            String query = "SELECT * FROM " + Const.APPOINTMENTS_TABLE + " WHERE " + Const.APPOINTMENT_STUDENT_ID +
                    " = ?";
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
            preparedStatement.setInt(1, studentID);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<appointment> studentAppointments = new ArrayList<>();

            while (resultSet.next()) {
                studentAppointments.add(new appointment(resultSet.getInt(Const.APPOINTMENT_ID),
                        resultSet.getInt(Const.APPOINTMENT_ACADEMIC_ID),
                        resultSet.getInt(Const.APPOINTMENT_STUDENT_ID),
                        resultSet.getString(Const.APPOINTMENT_STATUS),
                        resultSet.getDate(Const.APPOINTMENT_DATE),
                        resultSet.getInt(Const.APPOINTMENT_TIME)

                ));
            }


            return studentAppointments;
        }

        public academic getAcademic ( int academicID) throws SQLException, ClassNotFoundException {

            String query = "SELECT * FROM " + Const.ACADEMIC_TABLE + " WHERE " + Const.ACADEMIC_ID + " = ?";
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
            preparedStatement.setInt(1, academicID);
            ResultSet rs = preparedStatement.executeQuery();
            academic academic = new academic(rs.getInt(Const.ACADEMIC_ID),
                    rs.getString(Const.ACADEMIC_NAME));

            return academic;

        }

    public ResultSet academicLogin(academic newacademic) throws SQLException, ClassNotFoundException {

        String query = "SELECT * FROM " + Const.ACADEMIC_TABLE + " WHERE " + Const.ACADEMIC_EMAIL + " = ? AND "+
                Const.ACADEMIC_PASSWORD+ " = ?";
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
        preparedStatement.setString(1, newacademic.getEmail());
        preparedStatement.setString(2, newacademic.getPassword());
        ResultSet rs = preparedStatement.executeQuery();
       return rs;


    }

    /*
    public ArrayList<appointment> getAvailableAppointments(int academicID, Date date) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM "+ Const.ACADEMIC_TABLE+ " WHERE "+
                Const.ACADEMIC_DEPARTMENT_ID+ " = ?";

        PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
        preparedStatement.setInt(1, departmentID);
        ResultSet resultSet = preparedStatement.executeQuery();
        ArrayList<academic> academics = new ArrayList<>();

        while(resultSet.next()){
            academics.add(new academic(resultSet.getInt(Const.ACADEMIC_ID),
                    resultSet.getString(Const.ACADEMIC_NAME)
            )) ;
        }

        return academics;
    }*/


    }

