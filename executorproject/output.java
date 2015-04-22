package com.burroakapp.test;

import com.burroakapp.MainActivity;
import com.robotium.solo.*;
import android.test.ActivityInstrumentationTestCase2;


public class EventTest extends ActivityInstrumentationTestCase2<MainActivity> {
  	private Solo solo;
  	
  	public EventTest() {
		super(MainActivity.class);
  	}

  	public void setUp() throws Exception {
        super.setUp();
		solo = new Solo(getInstrumentation());
		getActivity();
  	}
  
   	@Override
   	public void tearDown() throws Exception {
        solo.finishOpenedActivities();
        super.tearDown();
  	}
  
	public void testRun() {
        //Wait for activity: 'com.burroakapp.MainActivity'
		solo.waitForActivity(com.burroakapp.MainActivity.class, 2000);
        //Click on Events
		solo.clickOnView(solo.getView(com.burroakapp.R.id.eventsButton));
        //Wait for activity: 'com.burroakapp.EventInformation'
		assertTrue("com.burroakapp.EventInformation is not found!", solo.waitForActivity(com.burroakapp.EventInformation.class));
        //Assert that: 'Upcoming Events:' is shown
		assertTrue("'Upcoming Events:' is not shown!", solo.waitForText(java.util.regex.Pattern.quote("Upcoming Events:"), 1, 20000, true, true));
        //Click on Pollinator Weekend 2015! May 15, 2015 9:00 am 7:30 pm
		solo.clickInList(3, 0);
	}
}
