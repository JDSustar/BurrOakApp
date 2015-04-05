package com.burroakapp;

/**
 * Created by JasonDesktop on 4/4/2015.
 */
public class TrailCheckpoint {
    int _order;
    int _trailId;
    float _longitude;
    float _latitude;

    public TrailCheckpoint()
    {

    }

    public TrailCheckpoint(int order, float latitude, float longitude, int trailId) {
        this._order = order;
        this._latitude = latitude;
        this._longitude = longitude;
        this._trailId = trailId;
    }

    public int get_order() {
        return _order;
    }

    public int get_trailId() {
        return _trailId;
    }

    public float get_longitude() {
        return _longitude;
    }

    public float get_latitude() {
        return _latitude;
    }
}
