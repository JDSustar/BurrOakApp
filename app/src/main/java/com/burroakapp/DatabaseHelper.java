package com.burroakapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by JasonDesktop on 4/4/2015.
 */
public class DatabaseHelper extends SQLiteAssetHelper {
    private static final String DATABASE_NAME = "trailsManager.sqlite";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_TRAILS = "trails";
    private static final String TABLE_TRAIL_CHECKPOINTS = "trailCheckpoints";
    private static final String TABLE_NOTES = "notes";

    public static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");


    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public Trail getTrail(int id)
    {
        SQLiteDatabase db = getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_TRAILS + " WHERE Id = " + id;

        Cursor c = db.rawQuery(query, null);

        if(c.moveToFirst())
        {
            return new Trail(id, c.getString(1), c.getDouble(2));
        }

        return null;
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
                + " ORDER BY 'Order'";

        Cursor c = db.rawQuery(query, null);

        if(c.moveToFirst())
        {
            do {
                TrailCheckpoint t = new TrailCheckpoint(
                        c.getInt(c.getColumnIndex("Order")),
                        c.getFloat(c.getColumnIndex("Latitude")),
                        c.getFloat(c.getColumnIndex("Longitude")),
                        trailId);
                checkpoints.add(t);
            } while (c.moveToNext());
        }

        return checkpoints;
    }

    public List<TrailNote> getTrailNotes(int trailId)
    {
        SQLiteDatabase db = getReadableDatabase();
        List<TrailNote> notes = new ArrayList<TrailNote>();

        String query = "SELECT * FROM " + TABLE_NOTES + " WHERE TrailId = " + trailId;

        Cursor c = db.rawQuery(query, null);

        if(c.moveToFirst())
        {
            do {
                Date date;

                try
                {
                    date = DATE_FORMAT.parse(c.getString(c.getColumnIndex("Date")));
                }
                catch (ParseException e)
                {
                    date = new Date();
                }

                TrailNote t = new TrailNote(
                        c.getInt(c.getColumnIndex("TrailId")),
                        date,
                        c.getString(c.getColumnIndex("Note")),
                        c.getFloat(c.getColumnIndex("Latitude")),
                        c.getFloat(c.getColumnIndex("Longitude")));

                notes.add(t);
            } while (c.moveToNext());
        }

        return notes;
    }

    public void writeTrailNote(TrailNote note)
    {
        ContentValues values = new ContentValues();
        values.put("TrailId", note.get_trailId());
        values.put("Date", DATE_FORMAT.format(note.get_date()));
        values.put("Note", note.get_note());
        values.put("Latitude", note.get_latitude());
        values.put("Longitude", note.get_longitude());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_NOTES, null, values);
        db.close();
    }
}
