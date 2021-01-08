package com.example.terminsystem1.Models.Database;

import com.example.terminsystem1.Models.academic;
import com.example.terminsystem1.Models.appointment;
import com.example.terminsystem1.Models.department;
import com.example.terminsystem1.Models.faculty;
import com.example.terminsystem1.Models.student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/* Github link:
 * https://github.com/betul11/Terminsystem1
 * */
public class database  {
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



    public ArrayList<department> getRelevantDepartment(int facultyId) throws SQLException {
        //returns a certain faculty's departments using its ID for search
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

    public int getStudentIdByEmail (String email) throws SQLException, ClassNotFoundException {

        String query = "SELECT * FROM " + Const.STUDENTS_TABLE + " WHERE " + Const.STUDENT_EMAIL + "= ? ";
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
        preparedStatement.setString(1, email);
        int studentID = 100;
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) {

             studentID = rs.getInt(Const.STUDENT_ID);
        }

        return studentID;



    }
    public int getAcademicIdByEmail(String email) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM " + Const.ACADEMIC_TABLE + " WHERE " + Const.ACADEMIC_EMAIL + "= ? ";
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
        preparedStatement.setString(1, email);
        int academicID = 500;
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) {

            academicID = rs.getInt(Const.ACADEMIC_ID);
        }
        return academicID;


    }




    public ResultSet studentLogin(student user) throws SQLException, ClassNotFoundException {
            String query = "SELECT * FROM " + Const.STUDENTS_TABLE + " WHERE " + Const.STUDENT_EMAIL + "= ? AND " +
                    Const.STUDENT_PASSWORD + "= ?";

            PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());

            ResultSet resultSet = preparedStatement.executeQuery();


            return resultSet;
        }

    public int sendAppointmentRequest(int academicID,int studentID, Date date, String time) throws SQLException, ClassNotFoundException {
       //CREATE AN APPOINTMENT WITH THE STATUS: waiting, AT A SPECIFIC TIME
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        String query = "INSERT INTO "+Const.APPOINTMENTS_TABLE+ " (`akademimitgliedID`, `studentID`, `status`, `datum`, `zeit`) " +
                "VALUES ( ?, ?, 'waiting', ?, ?)";
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
        preparedStatement.setInt(1, academicID);

        preparedStatement.setInt(2, studentID);
        preparedStatement.setDate(3, sqlDate);
        preparedStatement.setString(4, time);

        int result = preparedStatement.executeUpdate();
        return result;


    }

    public int getAvailableAppointment (int academicID,int studentID, Date date, String time) throws SQLException, ClassNotFoundException {
       java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        String query = "SELECT * FROM " + Const.APPOINTMENTS_TABLE + " WHERE " +
                Const.APPOINTMENT_DATE + " = ? AND " + Const.APPOINTMENT_TIME + "= ?";
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
        preparedStatement.setDate(1, sqlDate);
        preparedStatement.setString(2, time);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (!resultSet.next()) {
            //NO APPOINTMENTS IN THE DATABASE, CREATE AN APPOINTMENT WITH THE STATUS: waiting
            // equivalent of sending a request
            sendAppointmentRequest(academicID, studentID, date, time);
            return 1;
            }

         else {

            return 2;
            }


    }

        public ArrayList<faculty> getAllFaculties () throws SQLException, ClassNotFoundException {
        // list faculties
            String query = "SELECT * FROM fakultaet";
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<faculty> faculties = new ArrayList<>();
            while (resultSet.next()) {
                faculties.add(new faculty(resultSet.getInt(Const.FACULTY_ID), resultSet.getString(Const.FACULTY_NAME)));
            }

            return faculties;
        }


        public ArrayList<academic> getRelevantAcademic (int departmentID) throws
        SQLException, ClassNotFoundException {
       /* Get the academics related to a certain department from the academics table
       using its ID as a foreign key
        */
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

        public ArrayList<appointment> getStudentAppointments (int studentID) throws
        SQLException, ClassNotFoundException {
        // get all student's upcoming appointments starting current time in the default time zone.
            Calendar calendar = Calendar.getInstance();

            Date date = calendar.getTime();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.format(date);
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());


            String query = "SELECT * FROM " + Const.APPOINTMENTS_TABLE + " WHERE " + Const.APPOINTMENT_STUDENT_ID +
                    " = ? AND "+ Const.APPOINTMENT_DATE + "> ?";
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
            preparedStatement.setInt(1, studentID);
            preparedStatement.setDate(2, sqlDate);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<appointment> studentAppointments = new ArrayList<>();

            while (resultSet.next()) {
                studentAppointments.add(new appointment(resultSet.getInt(Const.APPOINTMENT_ID),
                        resultSet.getInt(Const.APPOINTMENT_ACADEMIC_ID),
                        resultSet.getInt(Const.APPOINTMENT_STUDENT_ID),
                        resultSet.getString(Const.APPOINTMENT_STATUS),
                        resultSet.getDate(Const.APPOINTMENT_DATE),
                        resultSet.getString(Const.APPOINTMENT_TIME)

                ));
            }


            return studentAppointments;
        }


    public String getAcademicName ( int academicID) throws SQLException, ClassNotFoundException {

        String query = "SELECT * FROM " + Const.ACADEMIC_TABLE + " WHERE " + Const.ACADEMIC_ID + " = ?";
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
        preparedStatement.setInt(1, academicID);
        ResultSet rs = preparedStatement.executeQuery();
        String academicName= "database";
        while (rs.next()) {

            academicName = rs.getString(Const.ACADEMIC_NAME);
        }

        return academicName;

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

    public ArrayList<appointment> getAcademicAppointments(int academicID) throws SQLException, ClassNotFoundException {
       /* get all academic's requested or accepted upcoming appointments
       starting current time in the default time zone.
        */

        Calendar calendar = Calendar.getInstance();

        Date date = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.format(date);
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());


        String query = "SELECT * FROM " + Const.APPOINTMENTS_TABLE + " WHERE " + Const.APPOINTMENT_ACADEMIC_ID +
                " = ? AND "+ Const.APPOINTMENT_DATE + "> ? AND ("+Const.APPOINTMENT_STATUS+" = 'waiting' OR "+
        Const.APPOINTMENT_STATUS+ " = 'accepted' )";


        PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
        preparedStatement.setInt(1, academicID);
        preparedStatement.setDate(2, sqlDate);
        ResultSet resultSet = preparedStatement.executeQuery();
        ArrayList<appointment> academicAppointments = new ArrayList<>();

        while (resultSet.next()) {
            academicAppointments.add(new appointment(resultSet.getInt(Const.APPOINTMENT_ID),
                    resultSet.getInt(Const.APPOINTMENT_ACADEMIC_ID),
                    resultSet.getInt(Const.APPOINTMENT_STUDENT_ID),
                    resultSet.getString(Const.APPOINTMENT_STATUS),
                    resultSet.getDate(Const.APPOINTMENT_DATE),
                    resultSet.getString(Const.APPOINTMENT_TIME)

            ));
        }


        return academicAppointments;

    }

    public String getStudentName(int studentID) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM " + Const.STUDENTS_TABLE + " WHERE " + Const.STUDENT_ID + " = ?";
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
        preparedStatement.setInt(1, studentID);
        ResultSet rs = preparedStatement.executeQuery();
        String studentName= "database";
        while (rs.next()) {

            studentName = rs.getString(Const.STUDENT_NAME);
        }

        return studentName;
    }

    public int getStudentNumber(int studentID) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM " + Const.STUDENTS_TABLE + " WHERE " + Const.STUDENT_ID + " = ?";
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
        preparedStatement.setInt(1, studentID);
        ResultSet rs = preparedStatement.executeQuery();
        int studentNumber= 200;
        while (rs.next()) {

            studentNumber = rs.getInt(Const.STUDENT_NUMBER);
        }

        return studentNumber;
    }

    public void acceptRequest(int acceptedAppointmentID) throws SQLException, ClassNotFoundException {
        // change a certain appointment's status from "waiting" to "accepted"
        String query = "UPDATE " + Const.APPOINTMENTS_TABLE +" SET "+ Const.APPOINTMENT_STATUS  + " = 'accepted' WHERE "
                + Const.APPOINTMENT_ID + " = ?";
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
        preparedStatement.setInt(1, acceptedAppointmentID);
        int rs = preparedStatement.executeUpdate();
        System.out.println("accepted");


    }

    public void denyRequest(int deniedAppointmentID) throws SQLException, ClassNotFoundException {
        // change a certain appointment's status from "waiting" to "denied"

        String query = "UPDATE " + Const.APPOINTMENTS_TABLE +" SET "+ Const.APPOINTMENT_STATUS  + " = 'denied' WHERE "
                + Const.APPOINTMENT_ID + " = ?";
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
        preparedStatement.setInt(1, deniedAppointmentID);
        int rs = preparedStatement.executeUpdate();
        System.out.println("denied");
    }


    public void changeStudentPassword(String studentEmail, String newPass) throws SQLException, ClassNotFoundException {
        String query = "UPDATE " + Const.STUDENTS_TABLE +" SET "+ Const.STUDENT_PASSWORD  + " = ? WHERE "
                + Const.STUDENT_EMAIL + " = ?";
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
        preparedStatement.setString(1, newPass);
        preparedStatement.setString(2, studentEmail);
        int rs = preparedStatement.executeUpdate();


    }

    public boolean checkIfStudentPasswordIsCorrect(String studentEmail, String oldPass) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM "+Const.STUDENTS_TABLE+ " WHERE "+Const.STUDENT_EMAIL+ " = ? AND "+
                Const.STUDENT_PASSWORD + " = ? ";
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
        preparedStatement.setString(1, studentEmail);
        preparedStatement.setString(2, oldPass);
        ResultSet rs = preparedStatement.executeQuery();
        if(!rs.next()){
            return false;
        }
        return true;
    }
}

