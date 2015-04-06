package com.burroakapp;

import java.util.Date;

/**
 * Created by JasonDesktop on 4/5/2015.
 */
public class TrailNote {
    private int _trailId;
    private Date _date;
    private String _note;
    private float _latitude;
    private float _longitude;

    public TrailNote(int trailId, Date date, String note, float longitude, float latitude) {
        this._longitude = longitude;
        this._latitude = latitude;
        this._note = note;
        this._date = date;
        this._trailId = trailId;
    }

    public float get_longitude() {
        return _longitude;
    }

    public int get_trailId() {
        return _trailId;
    }

    public Date get_date() {
        return _date;
    }

    public String get_note() {
        return _note;
    }

    public float get_latitude() {
        return _latitude;
    }

    public void set_note(String _note) {
        this._note = _note;
    }
}
