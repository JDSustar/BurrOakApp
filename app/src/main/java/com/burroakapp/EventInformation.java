package com.burroakapp;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class EventInformation extends ListActivity {
    ArrayList<String> headlines;
    ArrayList<String> dates;
    ArrayList<String> startTimes;
    ArrayList<String> endTimes;
    ArrayList<String> locations;
    ArrayList<String> descriptions;
    ArrayList<String> links;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_information);
        headlines = new ArrayList<String>();
        dates = new ArrayList<String>();
        startTimes = new ArrayList<String>();
        endTimes = new ArrayList<String>();
        locations = new ArrayList<String>();
        descriptions = new ArrayList<String>();
        links = new ArrayList<String>();
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        parseRSSFeed("http://burroak.org/feed/my-calendar-rss");

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_event_information, menu);
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

    public void parseRSSFeed(String givenURL)
    {
        // Initializing instance variables

        try {
            URL url = new URL(givenURL);

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(false);
            XmlPullParser xpp = factory.newPullParser();

            // Get the XML from an input stream
            xpp.setInput(getInputStream(url), "UTF_8");

            boolean insideItem = false;

            // Returns the type of current event: START_TAG, END_TAG, etc..
            int eventType = xpp.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_TAG) {

                    if (xpp.getName().equals("item")) {
                        insideItem = true;
                    } else if (xpp.getName().equals("title")) {
                        if (insideItem)
                            if (xpp.next() == XmlPullParser.TEXT) {
                                headlines.add(xpp.getText()); //extract the headline
                            }

                    } else if (xpp.getName().equals("link")) {
                        if (insideItem)
                            if (xpp.next() == XmlPullParser.TEXT) {
                                links.add(xpp.getText()); //extract the link of article
                            }
                    }
                    else if (xpp.getName().equals("description")) {
                        if (insideItem)
                            if (xpp.next() == XmlPullParser.TEXT) {
                                descriptions.add(xpp.getText()); //extract the link of article
                            }
                    }
                    else if (xpp.getName().equals("date")) {
                        if (insideItem)
                            if (xpp.next() == XmlPullParser.TEXT) {
                                dates.add(xpp.getText()); //extract the link of article
                            }
                    }
                    else if (xpp.getName().equals("startTime")) {
                        if (insideItem)
                            if (xpp.next() == XmlPullParser.TEXT) {
                                startTimes.add(xpp.getText()); //extract the link of article
                            }
                    }
                    else if (xpp.getName().equals("endTime")) {
                        if (insideItem)
                            if (xpp.next() == XmlPullParser.TEXT) {
                                endTimes.add(xpp.getText()); //extract the link of article
                            }
                    }
                    else if (xpp.getName().equals("location")) {
                        if (insideItem)
                            if (xpp.next() == XmlPullParser.TEXT) {
                                locations.add(xpp.getText()); //extract the link of article
                            }
                    }
                } else if (eventType == XmlPullParser.END_TAG && xpp.getName().equals("item")) {
                    insideItem = false;
                }

                eventType = xpp.next(); //move to next element
            }

        }catch (MalformedURLException e) {
             e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }

        // Binding data
        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, headlines);

        ListView lv = getListView();
        lv.setAdapter(adapter);


    }
    public InputStream getInputStream(URL url) {
        try {
            return url.openConnection().getInputStream();
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Uri uri = Uri.parse((String) links.get(position));
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

}
