package com.example.terminsystem1;

public class academic {
    //academics table column names
    private int academicID;
    private String academicName;
    private String academicNo;
    private String email ;
    private String academicIdNo;

    public academic(int academicID, String academicName) {
        this.academicID = academicID;
        this.academicName = academicName;
    }

    public int getAcademicID() {
        return academicID;
    }

    public void setAcademicID(int academicID) {
        this.academicID = academicID;
    }

    public String getAcademicName() {
        return academicName;
    }

    public void setAcademicName(String academicName) {
        this.academicName = academicName;
    }

    public String getAcademicNo() {
        return academicNo;
    }

    public void setAcademicNo(String academicNo) {
        this.academicNo = academicNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAcademicIdNo() {
        return academicIdNo;
    }

    public void setAcademicIdNo(String academicIdNo) {
        this.academicIdNo = academicIdNo;
    }

    public String getAcademicPassword() {
        return academicPassword;
    }

    public void setAcademicPassword(String academicPassword) {
        this.academicPassword = academicPassword;
    }

    public String getAcademicDepartmentId() {
        return academicDepartmentId;
    }

    public void setAcademicDepartmentId(String academicDepartmentId) {
        this.academicDepartmentId = academicDepartmentId;
    }

    private  String academicPassword ;
    private  String academicDepartmentId;
}
