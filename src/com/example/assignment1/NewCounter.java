/*
New Counter class for Assignment 1 app.
New Counter activity was called from the main page 
and user is allow to input any kind of name for a counter
and save it.
    
    License GPLv3: GNU GPL Version 3
    <http://gnu.org/licenses/gpl.html>.
    
    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package com.example.assignment1;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

/**
 * NewCounter activity allows the user to create a new counter and save it to increment.
 * @author     Chun-Han Lee
 * @uml.dependency   supplier="com.example.assignment1.ReadWrite"
 */
public class NewCounter extends Activity implements OnClickListener {
	
	// Declare all needed variables
	static final String FILENAME = "file2.json";
	private EditText counterName;
	private String cName;

	/**
	 * Called when the activity is first created. Creates the buttons
	 * for the page. 
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_counter_page);
		
		// Set up button for save
		Button savebutton = (Button) findViewById(R.id.saveButton);
		savebutton.setOnClickListener(this);
		
		// Set up button for cancel
		Button cancelbutton = (Button) findViewById(R.id.cancelButton);
		cancelbutton.setOnClickListener(this);
	}

	/**
	 * Called once any of the button specified above is clicked
	 * Then catch which button was clicked to respond respectfully.
	 */
	@Override
	public void onClick(final View arg0) {
		
		// To catch the id of which button was clicked
		//http://stackoverflow.com/questions/3320115/android-onclicklistener-identify-a-button
		switch(arg0.getId())
		{
		
		// Cancel button transitions back to main page activity
		//  and finish this activity.
		case R.id.cancelButton:
			Intent intent = new Intent(arg0.getContext(), MainPage.class);
			startActivityForResult(intent, 0);
			finish();
			break;
			
		// Save button 
		case R.id.saveButton:
			
			// Parse the name of the counter entered 
			counterName = (EditText) findViewById(R.id.counterName);
			cName = counterName.getText().toString();
			cName = cName.replaceAll("\\s+", "");
			
			// Create the alert dialog if the counter already exists and 
			//  not saved successfully.
			//http://www.androidsnippets.com/prompt-user-input-with-an-alertdialog
			AlertDialog.Builder alert = new AlertDialog.Builder(this);
			alert.setTitle("Counter Already Exists");
			alert.setMessage("Unable to create this counter because it already exists!");
			alert.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
			  // Cancel.
			  }
			});
			
			// Create the alert dialog if the counter exists and successfully saved 
			AlertDialog.Builder alert2 = new AlertDialog.Builder(this);
			alert2.setTitle("Counter Created!");
			alert2.setMessage("Your new counter has been created!");
			alert2.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				// Ok to transition to the next page
				//http://stackoverflow.com/questions/14853325/how-to-dismiss-alertdialog-in-android
				Intent intent1 = new Intent(arg0.getContext(), MainPage.class);
				startActivityForResult(intent1, 0);
				finish();
			  }
			});
			
			// Create the alert dialog if there is no input as a name 
			AlertDialog.Builder alert3 = new AlertDialog.Builder(this);
			alert3.setTitle("No name specified!");
			alert3.setMessage("Counter not created! There is no name specified for the counter!");
			alert3.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				// Cancel.
			  }
			});
			
			// Instantiate the classes
			Context c = getApplication();
			ReadWrite checkcounter = new ReadWrite();
			ReadWrite newcount = new ReadWrite();
			boolean countexist = false;
			
			// Check if the counter exists
			countexist = checkcounter.checkCounterExist(FILENAME, c, cName);
			
			
			if (countexist == false){
				if (cName.equals("")){
					// Show unsuccessful alert dialog for no entry
					alert3.show();
				}else {
					// Counter does not exist and write the new counter
					// show corresponding alert
					newcount.newCounter(FILENAME, c, cName);
					alert2.show();
					break;
				}
			}else{
				// Show unsuccessful alert dialog for counter exists
				alert.show();
				
			}
		}
	}	
}
