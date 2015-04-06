package com.burroakapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;


public class SaveNote extends ActionBarActivity {

    private float inputLatitude;
    private float inputLongitude;
    private int trailId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_note);

        Intent intent = getIntent();
        trailId = intent.getIntExtra("TRAILID", -1);
        inputLatitude = intent.getFloatExtra("NOTELATITUDE", 0);
        inputLongitude = intent.getFloatExtra("NOTELONGITUDE", 0);

        Button saveButton = (Button) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = (EditText) findViewById(R.id.editText);

                TrailNote tn = new TrailNote(
                        trailId,
                        new Date(),
                        editText.getText().toString(),
                        inputLatitude,
                        inputLongitude
                );

                DatabaseHelper databaseHelper = new DatabaseHelper(SaveNote.this);
                databaseHelper.writeTrailNote(tn);

                SaveNote.this.finish();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_save_note, menu);
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
