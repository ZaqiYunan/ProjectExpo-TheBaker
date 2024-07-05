package com.warkop;
public class User {
    private String userID;
    private String role;
    private String namaLengkap;
    private String email;
    private String password;

    // Constructor
    public User(String userID, String role, String namaLengkap, String email, String password) {
        this.userID = userID;
        this.role = role;
        this.namaLengkap = namaLengkap;
        this.email = email;
        this.password = password;
    }

    // Getters and setters
    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
