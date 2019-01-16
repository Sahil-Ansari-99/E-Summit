package com.example.sahilahmadansari.e_celliitm.Model;

public class AnnounceModel {
    String title;
    String description;

    public AnnounceModel() {
    }

    public AnnounceModel(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
