package com.example.sahilahmadansari.e_celliitm.Model;

public class SpeakersModel {
    String name;
    String url;
    String designation;
    String linkedin;

    public SpeakersModel(String name, String url, String designation, String linkedin) {
        this.name = name;
        this.url = url;
        this.designation = designation;
        this.linkedin = linkedin;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getDesignation() {
        return designation;
    }

    public String getLinkedin() {
        return linkedin;
    }
}
