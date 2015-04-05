package com.burroakapp;

import android.content.Context;

import java.util.*;

/**
 * Created by JasonDesktop on 4/4/2015.
 */
public class Trail {

    int _id;
    String _name;
    double _length;

    List<TrailCheckpoint> _checkpoints;

    public Trail()
    {}

    public Trail(int id, String name, double length)
    {
        this._id = id;
        this._name = name;
        this._length = length;
    }

    public int get_id() {
        return _id;
    }

    public String get_name() {
        return _name;
    }

    public double get_length() {
        return _length;
    }

    public List<TrailCheckpoint> get_checkpoints(Context context)
    {
        if(_checkpoints == null)
        {
            DatabaseHelper db = new DatabaseHelper(context);
            _checkpoints = db.getCheckpointsForTrail(this._id);
        }

        return _checkpoints;
    }

    @Override
    public String toString()
    {
        return _name + " - " + _length + " mi.";
    }
}
