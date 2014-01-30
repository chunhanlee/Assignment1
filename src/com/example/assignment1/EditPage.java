/*
EditPage class for Assignment1 app.
Editpage activity is called when the user
is on the counter page and wishes to either
rename, delete or reset the associated counter.
    
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
import android.widget.TextView;

/**
 * EditPage activity is called when the user 
 * would like to rename, delete or reset the counter 
 * they have chosen
 * @author Chun-Han Lee
 *
 */
public class EditPage extends Activity implements OnClickListener {
	
	// Declared all needed variables 
	/**
	 * @uml.property  name="counterName"
	 * @uml.associationEnd  
	 */
	private EditText counterName;
	/**
	 * @uml.property  name="cName"
	 */
	private String cName;
	/**
	 * @uml.property  name="oldcountersName"
	 */
	private String oldcountersName;
	static final String FILENAME = "file2.json";
	
	/**
	 * Called when the activity is first created. Receives 
	 * intent from the counter activity to create the editable
	 * counter name for the user. 
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_page);
		
		// Button for Save
		Button savebutton = (Button) findViewById(R.id.saveButton);
		savebutton.setOnClickListener(this);
		
		// Button for Cancel
		Button cancelbutton = (Button) findViewById(R.id.cancelButton);
		cancelbutton.setOnClickListener(this);
		
		// Button for reset
		Button resetButton = (Button) findViewById(R.id.resetButton);
		resetButton.setOnClickListener(this);
		
		// Button for delete
		Button deleteButton = (Button) findViewById(R.id.deleteButton);
		deleteButton.setOnClickListener(this);
		
		// Get the name of the counter chosen set as old counter name
		Intent i = getIntent();
		oldcountersName = i.getStringExtra("CounterName");
		
		// Allow the counter name to be editable for user to change the name
		//http://stackoverflow.com/questions/4590957/how-to-set-text-in-an-edittext
		counterName = (EditText) findViewById(R.id.counterName);
		counterName.setText(oldcountersName, TextView.BufferType.EDITABLE);
		
	}

	/**
	 * Called once any of the button specified above is clicked
	 * Then catch which button was clicked to respond respectfully.
	 */
	
	@Override
	public void onClick(final View arg0) {
		
		//http://stackoverflow.com/questions/3320115/android-onclicklistener-identify-a-button
		switch(arg0.getId())
		{
		
		// Cancel button transitions back to the counter activity page 
		//  and finishes this activity while passing in the name of the
		//  original counter back to the activity.
		case R.id.cancelButton:
			String dTP = oldcountersName;
			Intent intent = new Intent(this, CounterActivity.class);
			intent.putExtra("CounterName", dTP);
			startActivityForResult(intent, 0);
			finish();
			break;
			
		// Save button checks to see if the counter name already exists
		//  if so prompt user that they cannot move on because the rename
		//  was unsuccessful. If the rename was successful then the old counter
		//  name will be replaced with the new counter name and the data would
		//  still be the same as the old counter. 
		//  Then transitions back to the counter activity page and finishes
		//  this activity while passing the new name of the counter to the activity.
		case R.id.saveButton:
			
			// Parse old counter name
			// Instantiated classes and declared variables
			cName = counterName.getText().toString();
			cName = cName.replaceAll("\\s+", "");
			ReadWrite save = new ReadWrite();
			Context c = getApplication();
			ReadWrite checkcounter = new ReadWrite();
			boolean countexist = false;
			
			// Set up the alert message if the counter already exists
			//  no action is taken
			//http://www.androidsnippets.com/prompt-user-input-with-an-alertdialog
			AlertDialog.Builder alert = new AlertDialog.Builder(this);
			alert.setTitle("Counter Already Exists");
			alert.setMessage("Unable to rename this counter because it already exists!");
			alert.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
			  // Cancel.
			  }
			});
			
			// Set up the alert message if the counter does not exist
			//  transition back to counter activity page initiated.
			AlertDialog.Builder alert2 = new AlertDialog.Builder(this);
			alert2.setTitle("Counter Renamed!");
			alert2.setMessage("Your counter has been renamed!");
			alert2.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				
				// Ok button to transition to next page
				// passing the counter name back to counter activity page 
				Intent intent3 = new Intent(arg0.getContext(), CounterActivity.class);
				intent3.putExtra("CounterName", cName);
				startActivityForResult(intent3, 0);
				
				//http://stackoverflow.com/questions/14853325/how-to-dismiss-alertdialog-in-android
				finish();
			  }
			});
			
			
			// Check if counter already exists
			countexist = checkcounter.checkCounterExist(FILENAME, c, cName);
			if (countexist == false){
				// If counter does not exist then rename the old counter name to new counter name
				//  show alert message to transition back to counter activity page.
				save.renameCounter(FILENAME, c, cName, oldcountersName);
				alert2.show();
			}else{
				// Else if counter exists then show alert message for no action
				alert.show();
			}
			break;
			
		// Reset button resets the count of the counter back to zero and 
		//  clearing the data log of all the dates in which the counts were 
		//  logged. 
		//  Then transitions back to the counter activity page while passing 
		//  in the old counter name.
		case R.id.resetButton:

			// Instantiated classes and declared variables
			final ReadWrite reset = new ReadWrite();
			final Context c1 = getApplication();
			
			// Set up the alert message if the user for sure would like to reset the counter
			//  A choice of either "Ok" or "Cancel" is given to the user.
			AlertDialog.Builder alert3 = new AlertDialog.Builder(this);
			alert3.setTitle("Resetting Counter");
			alert3.setMessage("Are you sure you would like to reset this counter?");
			alert3.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
				
			//  "Ok" will delete the counter and recreated the counter setting the count to 0
				public void onClick(DialogInterface dialog, int whichButton) {
					reset.resetCounter(FILENAME, c1, oldcountersName);
					Intent intent2 = new Intent(arg0.getContext(), CounterActivity.class);
					intent2.putExtra("CounterName", oldcountersName);
					startActivityForResult(intent2, 0);
					finish();
				 }
			});
			
			//  "Cancel" will allow the user to stay on the Editpage activity.
			alert3.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
				 public void onClick(DialogInterface dialog, int whichButton) {
				    // Canceled.
				 }
			});
			
			// Show the alert message.
			alert3.show();
			break;
			
		// Delete button deletes all the data of the counter and the counter itself
		//  then transitions back to the main activity page.
		case R.id.deleteButton:
			
			// Instantiated classes and declared variables
			final ReadWrite delete = new ReadWrite();
			final Context c2 = getApplication();
			
			// Set up the alert message if the user for sure would like to delete the counter
			//  A choice of either "Ok" or "Cancel" is given to the user.
			//http://www.androidsnippets.com/prompt-user-input-with-an-alertdialog
			AlertDialog.Builder alert4 = new AlertDialog.Builder(this);
			alert4.setTitle("Delete Counter");
			alert4.setMessage("Are you sure you would like to delete this counter?");
			alert4.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
				
				//  "Ok" will delete the counter and the associated data and
				//   transition back to the main activity page.
				public void onClick(DialogInterface dialog, int whichButton) {
					delete.deleteCounter(FILENAME, c2, oldcountersName);
					Intent intent4 = new Intent(arg0.getContext(), MainPage.class);
					startActivityForResult(intent4, 0);
					finish();
				 }
			});
			
			//  "Cancel" will allow the user to stay on the Editpage activity.
			alert4.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
				 public void onClick(DialogInterface dialog, int whichButton) {
				    // Canceled.
				 }
			});
			
			// Show the alert message.
			alert4.show();
			break;
		}
	}		
}
