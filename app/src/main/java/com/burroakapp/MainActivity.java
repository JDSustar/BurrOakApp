package com.burroakapp;

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
import android.util.*;
import android.os.Debug;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }

        Log.d("Lifecycle", "App has been Created!");
        Debug.startMethodTracing();
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        Log.d("Lifecycle", "App has started!");

        int j = 1;
        for(int i = 0; i < 100000 ; i++)
        {
            j += j;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Lifecycle", "App has resumed!");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Lifecycle", "App has stopped!");
        Debug.stopMethodTracing();
    }

    @Override
    protected void onDestroy() {
        Log.d("Lifecycle", "App has be destroyed!");
        super.onDestroy();

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Lifecycle", "App has been paused!");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }
}
