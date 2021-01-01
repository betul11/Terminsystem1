package com.example.terminsystem1.Models;

import java.sql.Date;
import java.text.DateFormat;

public class appointment {

    private int appointmentID;
    private int appointmentAcademicID ;
    private int appointmentStudentID ;


    private  String appointmentStatus;
    private Date appointmentDate;
    private String appointmentTime;

    public appointment(int appointmentID, int appointmentAcademicID, int appointmentStudentID,
                       String appointmentStatus, Date appointmentDate, String appointmentTime) {
        this.appointmentID = appointmentID;
        this.appointmentAcademicID = appointmentAcademicID;
        this.appointmentStudentID = appointmentStudentID;
        this.appointmentStatus = appointmentStatus;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
    }

    public int getAppointmentID() {
        return appointmentID;
    }

    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }

    public int getAppointmentAcademicID() {
        return appointmentAcademicID;
    }

    public void setAppointmentAcademicID(int appointmentAcademicID) {
        this.appointmentAcademicID = appointmentAcademicID;
    }

    public int getAppointmentStudentID() {
        return appointmentStudentID;
    }

    public void setAppointmentStudentID(int appointmentStudentID) {
        this.appointmentStudentID = appointmentStudentID;
    }

    public String getAppointmentStatus() {
        return appointmentStatus;
    }

    public void setAppointmentStatus(String appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }
}
