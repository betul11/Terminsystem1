package com.example.terminsystem1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class database {
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {

        String dbHost="pharzan.c6enfwnyrdpp.eu-central-1.rds.amazonaws.com";
        String dbPort="3306";
        String dbUser="beatle";
        String dbPass="beidas";

        String connectionString = "jdbc:mysql://"+ dbHost+":"
                + dbPort +"/batool";
        Class.forName("com.mysql.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString,dbUser,dbPass);
        return dbConnection;
    }

    public database() {
    }
/*
    public ResultSet getStudent (student user){
        ResultSet resultSet = null;
        if(!user.getEmail().equals("")){
            String query = "SELECT * FROM "+ Const.STUDENTS_TABLE+ " WHERE "+ Const.STUDENT_EMAIL+ "=?";
            try {
                PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
                preparedStatement.setString(1, user.getEmail());

                resultSet = preparedStatement.executeQuery();



            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }else{
            System.out.println("Please enter your correct login information!");
        }

        return resultSet;
    }*/


    public ArrayList<faculty> getAllFaculties() throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM fakultaet";
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        ArrayList<faculty> faculties = new ArrayList<>();
        while(resultSet.next()){
            faculties.add(new faculty(resultSet.getString(Const.FACULTY_NAME)))  ;
        }

        return faculties;
    }




}
