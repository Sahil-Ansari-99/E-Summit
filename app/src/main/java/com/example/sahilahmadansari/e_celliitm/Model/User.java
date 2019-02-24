package com.example.sahilahmadansari.e_celliitm.Model;

public class User {
    String username;
    String designation;
    String company;
    String id;

    public User(String username, String designation, String company, String id) {
        this.username = username;
        this.designation = designation;
        this.company = company;
        this.id=id;
    }

    public String getUsername() {
        return username;
    }

    public String getDesignation() {
        return designation;
    }

    public String getCompany() {
        return company;
    }

    public String getId() {
        return id;
    }
}
