package com.example.sahilahmadansari.e_celliitm.Model;


import java.io.Serializable;

public class Speakers implements Serializable {
    String name;
    String designation;

    public Speakers(String name, String designation) {
        this.name = name;
        this.designation = designation;
    }

    public String getName() {
        return name;
    }

    public String getDesignation() {
        return designation;
    }


}
