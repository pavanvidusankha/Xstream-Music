package com.example.xstream.models;

public class User {
    private String uname;
    private long id;
    private String fname;
    private String lname;
    private String email;

    @Override
    public String toString() {
        return "User{" +
                "uname='" + uname + '\'' +
                ", id=" + id +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public User(String uname, long id, String fname, String lname, String email) {
        this.uname = uname;
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
    }

    public User() {
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User(String uname, String fname, String lname, String email) {
        this.uname = uname;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
    }
}
