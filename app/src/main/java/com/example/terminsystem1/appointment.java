package com.example.terminsystem1;

import java.text.DateFormat;

public class appointment {

    private int appointmentID;
    private int appointmentAcademicID ;
    private int appointmentStudentID ;
    private  String appointmentStatus;
    private DateFormat appointmentDate;
    private int appointmentTime;

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

    public DateFormat getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(DateFormat appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public int getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(int appointmentTime) {
        this.appointmentTime = appointmentTime;
    }
}
