package com.example.terminsystem1;

public class student {

        private int student_id ;
        private String student_name ;
        private int student_nummer;
        private String Email ;
        private int identifikation_nummer;
        private String password;
        private int abteilung_id;

    public student(String email, String password) {
        this.Email = email;
        this.password = password;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public int getStudent_nummer() {
        return student_nummer;
    }

    public void setStudent_nummer(int student_nummer) {
        this.student_nummer = student_nummer;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public int getIdentifikation_nummer() {
        return identifikation_nummer;
    }

    public void setIdentifikation_nummer(int identifikation_nummer) {
        this.identifikation_nummer = identifikation_nummer;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAbteilung_id() {
        return abteilung_id;
    }

    public void setAbteilung_id(int abteilung_id) {
        this.abteilung_id = abteilung_id;
    }
}
