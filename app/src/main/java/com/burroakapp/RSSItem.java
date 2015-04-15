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

    public String getTitle() {
        return title;
    }

    public void setTitle(String givenTitle) {
        title = givenTitle;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String givenDate) {
        date = givenDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String givenStartTime) {
        startTime = givenStartTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String givenEndTime) {
        endTime = givenEndTime;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String givenLink) {
        link = givenLink;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String givenLocation) {
        location = givenLocation;
    }

    public String getDescription() {
        return title;
    }

    public void setDescription(String givenDescription) {
        description = givenDescription;
    }
}
