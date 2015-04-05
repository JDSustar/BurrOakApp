package com.burroakapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JasonDesktop on 4/4/2015.
 */
public class DatabaseHelper extends SQLiteAssetHelper {
    private static final String DATABASE_NAME = "trailsManager.sqlite";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_TRAILS = "trails";
    private static final String TABLE_TRAIL_CHECKPOINTS = "trailCheckpoints";

    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public List<Trail> getTrails()
    {
        SQLiteDatabase db = getReadableDatabase();
        List<Trail> trailList = new ArrayList<Trail>();

        String query = "SELECT * FROM " + TABLE_TRAILS;

        Cursor c = db.rawQuery(query, null);

        if(c.moveToFirst())
        {
            do {
                Trail t = new Trail(c.getInt(0), c.getString(1), c.getDouble(2));
                trailList.add(t);
            } while (c.moveToNext());
        }

        return trailList;
    }

    public List<TrailCheckpoint> getCheckpointsForTrail(int trailId)
    {
        SQLiteDatabase db = getReadableDatabase();
        List<TrailCheckpoint> checkpoints = new ArrayList<TrailCheckpoint>();

        String query = "SELECT * FROM " + TABLE_TRAIL_CHECKPOINTS + " WHERE TrailId = " + trailId
                + "ORDER BY Order";

        Cursor c = db.rawQuery(query, null);

        if(c.moveToFirst())
        {
            do {
                TrailCheckpoint t = new TrailCheckpoint(
                        c.getInt(c.getColumnIndex("Order")),
                        c.getDouble(c.getColumnIndex("Latitude")),
                        c.getDouble(c.getColumnIndex("Longitude")),
                        trailId);
                checkpoints.add(t);
            } while (c.moveToNext());
        }

        return checkpoints;
    }
}
