package com.example.terminsystem1.Models;

public class department {
    private int departmentID;
    private String departmentName;
    private int departmentFacultyID;
    public int getDepartmentID(){
        return this.departmentID;
    }

    public String getDepartmentName(){
        return this.departmentName;
    }

    public department(int id, String department,int facultyID){
        this.departmentID = id;
        this.departmentName = department;
        this.departmentFacultyID = facultyID;

    }


}
