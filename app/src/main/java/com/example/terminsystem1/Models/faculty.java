package com.example.terminsystem1.Models;

public class faculty {

    private int facultyID;
    private String facultyName;

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public faculty(int facultyID, String facultyName) {
        this.facultyID = facultyID;
        this.facultyName = facultyName;
    }

    public int getFacultyID(){
        return facultyID;
    }

    public void openDepartments (String department){

        facultyName = department;

    }


}
