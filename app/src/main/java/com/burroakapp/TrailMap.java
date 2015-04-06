package com.burroakapp;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.List;


public class TrailMap extends ActionBarActivity implements OnMapReadyCallback {

    private GoogleMap map;
    private List<TrailCheckpoint> checkpoints;
    private int trailId;

    // Used for location services
    protected GoogleApiClient mGoogleApiClient;
    protected android.location.Location mCurrentLocation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trail_map);

        Intent intent = getIntent();
        trailId = intent.getIntExtra("TRAILID", -1);

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        new LoadMarkers().execute();
    }

    private class LoadMarkers extends AsyncTask<Void, Void, List<TrailNote>>{

        @Override
        protected List<TrailNote> doInBackground(Void... params) {
            DatabaseHelper databaseHelper = new DatabaseHelper(TrailMap.this);
            List<TrailNote> notes = databaseHelper.getTrailNotes(trailId);

            return notes;
        }

        protected void onPostExecute(List<TrailNote> notes) {
            for(TrailNote note : notes)
            {
                map.addMarker(new MarkerOptions().snippet(note.get_note()).title(DatabaseHelper.DATE_FORMAT.format(note.get_date()))
                        .position(new LatLng(note.get_latitude(), note.get_longitude())));
            }
        }
    }

    protected synchronized void buildGoogleApiClient() {
        // Build API Client, add callbacks as needed
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .build();
    }

    private void plotCheckpoints()
    {
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(checkpoints.get(0)._latitude, checkpoints.get(0)._longitude), 15));

        for(int i = 0; i < checkpoints.size() - 1; i++)
        {
            TrailCheckpoint cp1 = checkpoints.get(i);
            TrailCheckpoint cp2 = checkpoints.get(i + 1);

            map.addPolyline((new PolylineOptions()).add(new LatLng(cp1._latitude, cp1._longitude), new LatLng(cp2._latitude, cp2._longitude)).width(5).color(Color.BLUE).geodesic(true));
            //map.addMarker(new MarkerOptions().title(Integer.toString(i)).position(new LatLng(cp1._latitude, cp1._longitude)));
        }
    }

    @Override
    public void onMapReady(GoogleMap map) {

        DatabaseHelper db = new DatabaseHelper(this);
        Trail trail = db.getTrail(trailId);
        this.checkpoints = trail.get_checkpoints(this);

        buildGoogleApiClient();

        this.map = map;
        map.setMyLocationEnabled(true);

        mCurrentLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);

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
        if (id == R.id.save_note) {
            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

            // Create a criteria object to retrieve provider
            Criteria criteria = new Criteria();

            // Get the name of the best provider
            String provider = locationManager.getBestProvider(criteria, true);

            // Get Current Location
            Location myLocation = locationManager.getLastKnownLocation(provider);

            Intent intent = new Intent(this, SaveNote.class);
            intent.putExtra("TRAILID", trailId);
            intent.putExtra("NOTELATITUDE", (float)myLocation.getLatitude());
            intent.putExtra("NOTELONGITUDE", (float)myLocation.getLongitude());

            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
