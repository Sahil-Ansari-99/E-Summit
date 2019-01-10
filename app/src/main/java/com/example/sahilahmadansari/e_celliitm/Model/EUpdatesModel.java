package com.example.sahilahmadansari.e_celliitm.Model;

public class EUpdatesModel {

    public String title;
    public String pubDate;
    public String content;
    public String link;
    public Long upvotes;

    public EUpdatesModel(String title, String pubDate, String content, String link, Long upvotes) {
        this.title = title;
        this.pubDate = pubDate;
        this.content = content;
        this.link = link;
        this.upvotes = upvotes;
    }

    public String getTitle() {
        return title;
    }

    public String getPubDate() {
        return pubDate;
    }

    public String getContent() {
        return content;
    }

    public String getLink() {
        return link;
    }

    public Long getUpvotes() {
        return upvotes;
    }
}
