package com.burroakapp.test;

import com.burroakapp.MainActivity;
import com.robotium.solo.*;
import android.test.ActivityInstrumentationTestCase2;


public class HikingTest extends ActivityInstrumentationTestCase2<MainActivity> {
  	private Solo solo;
  	
  	public HikingTest() {
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
        //Click on Hiking Trails
		solo.clickOnView(solo.getView(com.burroakapp.R.id.hikingButton));
        //Wait for activity: 'com.burroakapp.TrailSelection'
		assertTrue("com.burroakapp.TrailSelection is not found!", solo.waitForActivity(com.burroakapp.TrailSelection.class));
        //Assert that: 'Hiking Trails' is shown
		assertTrue("'Hiking Trails' is not shown!", solo.waitForView(solo.getView(com.burroakapp.R.id.textView2)));
        //Click on Lakeview - 3.5 mi.
		solo.clickOnView(solo.getView(android.R.id.text1));
        //Wait for activity: 'com.burroakapp.TrailMap'
		assertTrue("com.burroakapp.TrailMap is not found!", solo.waitForActivity(com.burroakapp.TrailMap.class));
        //Click on Save Note
		solo.clickOnView(solo.getView(com.burroakapp.R.id.save_note));
        //Wait for activity: 'com.burroakapp.SaveNote'
		assertTrue("com.burroakapp.SaveNote is not found!", solo.waitForActivity(com.burroakapp.SaveNote.class));
        //Click on Empty Text View
		solo.clickOnView(solo.getView(com.burroakapp.R.id.editText));
        //Enter the text: 'Test'
		solo.clearEditText((android.widget.EditText) solo.getView(com.burroakapp.R.id.editText));
		solo.enterText((android.widget.EditText) solo.getView(com.burroakapp.R.id.editText), "Test");
        //Click on Save Note
		solo.clickOnView(solo.getView(com.burroakapp.R.id.saveButton));
        //Click on Save Note
		solo.clickOnView(solo.getView(com.burroakapp.R.id.save_note));
        //Wait for activity: 'com.burroakapp.SaveNote'
		assertTrue("com.burroakapp.SaveNote is not found!", solo.waitForActivity(com.burroakapp.SaveNote.class));
        //Assert that: 'Trail Name' is shown
		assertTrue("'Trail Name' is not shown!", solo.waitForView(solo.getView(com.burroakapp.R.id.trailLabel)));
        //Click on ImageView
		solo.clickOnView(solo.getView(android.widget.ImageButton.class, 0));
        //Click on ImageView
		solo.clickOnView(solo.getView(android.widget.ImageButton.class, 0));
        //Wait for activity: 'com.burroakapp.MainActivity'
		assertTrue("com.burroakapp.MainActivity is not found!", solo.waitForActivity(com.burroakapp.MainActivity.class));
	}
}
