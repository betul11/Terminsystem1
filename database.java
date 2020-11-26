package com.example.terminsystem1;
import java.sql.*;
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






}
