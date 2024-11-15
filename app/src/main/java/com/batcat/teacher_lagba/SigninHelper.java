package com.batcat.teacher_lagba;

public class SigninHelper {
    String name;
    String mail;
    String pass;
    String role;
    public SigninHelper() {
    }

    public SigninHelper(String name, String mail, String pass, String role) {
        this.name = name;
        this.mail = mail;
        this.pass = pass;
        this.role = role;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}


