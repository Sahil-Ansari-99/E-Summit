package com.example.sahilahmadansari.e_celliitm.Model;

public class AgendaModel {
    String title;
    String time;
    String duration;
    String venue;

    public AgendaModel(String title, String time, String duration, String venue) {
        this.title = title;
        this.time = time;
        this.duration = duration;
        this.venue = venue;
    }

    public String getTitle() {
        return title;
    }

    public String getTime() {
        return time;
    }

    public String getDuration() {
        return duration;
    }

    public String getVenue() {
        return venue;
    }
}
