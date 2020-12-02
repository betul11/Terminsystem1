package com.example.terminsystem1;

public class Const {
    //tables names
    public static final String STUDENTS_TABLE = "students";
    public static final String ACADEMIC_TABLE = "akademiemitglied";
    public static final String FACULTY_TABLE = "fakultaet";
    public static final String APPOINTMENTS_TABLE = "termin";
    public static final String DEPARTMENTS_TABLE = "students";



    //students table column names
    public static final String STUDENT_ID = "studentid";
    public static final String STUDENT_NAME = "studentName";
    public static final String STUDENT_NUMBER = "studentenNummer";
    public static final String STUDENT_EMAIL = "email";
    public static final String STUDENT_ID_NUMBER = "identifikationsnummer";
    public static final String STUDENT_PASSWORD = "passwort";
    public static final String STUDENT_DEPARTMENT_ID = "abteilungID";

    //department (abteilung) table column names
    public static final String DEPARTMENT_ID = "abteilungID";
    public static final String DEPARTMENT_NAME = "abteilungName";
    public static final String DEPARTMENT_FACULTY_ID = "fakultaetid";


    //academics table column names
    public static final String ACADEMIC_ID = "akademiemitgliedID";
    public static final String ACADEMIC_NAME = "name";
    public static final String ACADEMIC_NUMBER = "mitarbeiternummer";
    public static final String ACADEMIC_EMAIL = "email";
    public static final String ACADEMIC_ID_NUMBER = "identifikationsNummer";
    public static final String ACADEMIC_PASSWORD = "passwort";
    public static final String ACADEMIC_DEPARTMENT_ID = "abteilungid";


    //faculties table column names

    public static final String FACULTY_ID = "fakultaetID";
    public static final String FACULTY_NAME = "fakultaetName";


    //appointments table column names

    public static final String APPOINTMENT_ID = "terminID";
    public static final String APPOINTMENT_ACADEMIC_ID = "akademimitgliedID";
    public static final String APPOINTMENT_STUDENT_ID = "studentID";
    public static final String APPOINTMENT_STATUS = "status";
    public static final String APPOINTMENT_DATE = "datum";
    public static final String APPOINTMENT_TIME = "zeit";











}
