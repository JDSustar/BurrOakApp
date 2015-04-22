package com.burroakapp.test;

import com.burroakapp.MainActivity;
import com.robotium.solo.*;
import android.test.ActivityInstrumentationTestCase2;


public class MarinaTest extends ActivityInstrumentationTestCase2<MainActivity> {
  	private Solo solo;
  	
  	public MarinaTest() {
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
        //Click on Marina Information
		solo.clickOnView(solo.getView(com.burroakapp.R.id.marinaButton));
        //Wait for activity: 'com.burroakapp.MarinaInformation'
		assertTrue("com.burroakapp.MarinaInformation is not found!", solo.waitForActivity(com.burroakapp.MarinaInformation.class));
        //Assert that: 'Marina Information' is shown
		assertTrue("'Marina Information' is not shown!", solo.waitForView(solo.getView(com.burroakapp.R.id.marinaLabel)));
        //Assert that: 'Rentals are available at Dock 1 and 4   APRIL 15th through OCTOBER 1' is shown
		assertTrue("'Rentals are available at Dock 1 and 4   APRIL 15th through OCTOBER 1' is not shown!", solo.waitForView(solo.getView(com.burroakapp.R.id.marinaInformationTextView)));
        //Assert that: 'ImageView' is shown
		assertTrue("'ImageView' is not shown!", solo.waitForView(solo.getView(com.burroakapp.R.id.imageView)));
        //Click on ImageView
		solo.clickOnView(solo.getView(android.widget.ImageButton.class, 0));
        //Wait for activity: 'com.burroakapp.MainActivity'
		assertTrue("com.burroakapp.MainActivity is not found!", solo.waitForActivity(com.burroakapp.MainActivity.class));
	}
}
