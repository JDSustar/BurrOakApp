package com.burroakapp;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Jim on 4/3/2015.
 */
public class RSSItem implements Comparable{
    public static DateFormat DATE_FORMAT = new SimpleDateFormat("MMMM dd, yyyy");

    private String title;
    private Date date;
    private String startTime;
    private String endTime;
    private String link;
    private String location;
    private String description;

    public RSSItem(String title, String date, String startTime, String endTime, String link, String location, String description){
        this.title = title;

        try {
            this.date = DATE_FORMAT.parse(date);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }

        this.startTime = startTime;
        this.endTime = endTime;
        this.link = link;
        this.location = location;
        this.description = description;

        if(startTime.compareTo("All Day") == 0)
        {
            this.endTime = " ";
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String givenTitle) {
        title = givenTitle;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date givenDate) {
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

    @Override
    public int compareTo(Object another) {
        RSSItem anotherItem = (RSSItem)another;

        if(anotherItem == null)
        {
            throw new ClassCastException();
        }

        return this.getDate().compareTo(anotherItem.getDate());
    }
}
