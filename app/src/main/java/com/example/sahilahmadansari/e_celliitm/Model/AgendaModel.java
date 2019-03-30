package com.example.sahilahmadansari.e_celliitm.Model;

import java.io.Serializable;
import java.util.List;

public class AgendaModel implements Serializable {
    String title;
    String time;
    String description;
    String venue;
    List<Speakers> speakers;

    public AgendaModel(String title, String time, String description, String venue, List<Speakers> speakers) {
        this.title = title;
        this.time = time;
        this.description = description;
        this.venue = venue;
        this.speakers = speakers;
    }

    public String getTitle() {
        return title;
    }

    public String getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }

    public String getVenue() {
        return venue;
    }

    public List<Speakers> getSpeakers() {
        return speakers;
    }
}
