package com.burroakapp;

/**
 * Created by Jim on 4/3/2015.
 */
public class RSSItem {
    private String title;
    private String date;
    private String startTime;
    private String endTime;
    private String link;
    private String location;
    private String description;

    public RSSItem(String title, String date, String startTime, String endTime, String link, String location, String description){
        this.title = title;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.link = link;
        this.location = location;
        this.description = description;
    }
}
