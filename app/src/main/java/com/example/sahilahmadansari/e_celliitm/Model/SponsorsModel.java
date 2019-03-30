package com.example.sahilahmadansari.e_celliitm.Model;

public class SponsorsModel {
    String url;
    String name;
    String title;

    public SponsorsModel(String url, String name, String title) {
        this.url = url;
        this.name = name;
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }
}
