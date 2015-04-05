package com.burroakapp;

import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.List;


public class TrailMap extends ActionBarActivity implements OnMapReadyCallback {

    private GoogleMap map;
    List<TrailCheckpoint> checkpoints;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trail_map);

        Intent intent = getIntent();
        Integer trailId = intent.getIntExtra("TRAILID", -1);

        DatabaseHelper db = new DatabaseHelper(this);
        Trail trail = db.getTrail(trailId);
        this.checkpoints = trail.get_checkpoints(this);

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private void plotCheckpoints()
    {
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(checkpoints.get(0)._latitude, checkpoints.get(0)._longitude), 15));

        for(int i = 0; i < checkpoints.size() - 1; i++)
        {
            TrailCheckpoint cp1 = checkpoints.get(i);
            TrailCheckpoint cp2 = checkpoints.get(i + 1);

            map.addPolyline((new PolylineOptions()).add(new LatLng(cp1._latitude, cp1._longitude), new LatLng(cp2._latitude, cp2._longitude)).width(5).color(Color.BLUE).geodesic(true));
            map.addMarker(new MarkerOptions().title(Integer.toString(i)).position(new LatLng(cp1._latitude, cp1._longitude)));
        }
    }

    @Override
    public void onMapReady(GoogleMap map) {
        this.map = map;
        map.setMyLocationEnabled(true);

        plotCheckpoints();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_trail_map, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
