package com.example.assignment1;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EditPage extends Activity implements OnClickListener {
	
	//private String FILENAME;
	private EditText counterName;
	private String cName;
	private String oldcountersName;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_page);
		

		Button savebutton = (Button) findViewById(R.id.saveButton);
		savebutton.setOnClickListener(this);
		
		Button cancelbutton = (Button) findViewById(R.id.cancelButton);
		cancelbutton.setOnClickListener(this);
		
		Button resetButton = (Button) findViewById(R.id.resetButton);
		resetButton.setOnClickListener(this);
		
		Button deleteButton = (Button) findViewById(R.id.deleteButton);
		deleteButton.setOnClickListener(this);
		
		Intent i = getIntent();
		oldcountersName = i.getStringExtra("CounterName");
		
		//http://stackoverflow.com/questions/4590957/how-to-set-text-in-an-edittext
		counterName = (EditText) findViewById(R.id.counterName);
		counterName.setText(oldcountersName, TextView.BufferType.EDITABLE);
		//cName = counterName.getText().toString();
		//FILENAME = cName + ".txt";
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(final View arg0) {
		
		//http://stackoverflow.com/questions/3320115/android-onclicklistener-identify-a-button
		switch(arg0.getId())
		{
		case R.id.cancelButton:
			String dTP = oldcountersName;
			Intent intent = new Intent(this, CounterActivity.class);
			intent.putExtra("CounterName", dTP);
			startActivityForResult(intent, 0);
			finish();
			break;
		case R.id.saveButton:
			cName = counterName.getText().toString();
			cName = cName.replaceAll("\\s+", "");
			ReadWrite save = new ReadWrite();
			Context c = getApplication();
			ReadWrite checkcounter = new ReadWrite();
			boolean countexist = false;
			
			//http://www.androidsnippets.com/prompt-user-input-with-an-alertdialog
			AlertDialog.Builder alert = new AlertDialog.Builder(this);
			alert.setTitle("Counter Already Exists");
			alert.setMessage("Unable to rename this counter because it already exists!");
			alert.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
			  // Cancel.
			  }
			});
			
			AlertDialog.Builder alert2 = new AlertDialog.Builder(this);
			alert2.setTitle("Counter Renamed!");
			alert2.setMessage("Your counter has been renamed!");
			
			alert2.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
			  // Ok button to transition to next page
				Intent intent3 = new Intent(arg0.getContext(), CounterActivity.class);
				intent3.putExtra("CounterName", cName);
				startActivityForResult(intent3, 0);
				//http://stackoverflow.com/questions/14853325/how-to-dismiss-alertdialog-in-android
				finish();
			  }
			});
			
			
			// Check if counter already exists
			countexist = checkcounter.checkCounterExist("file2.json", c, cName);
			if (countexist == false){
				save.renameCounter("file2.json", c, cName, oldcountersName);
				alert2.show();

			}else{
				alert.show();
			}
			break;
		case R.id.resetButton:
			final ReadWrite reset = new ReadWrite();
			final Context c1 = getApplication();
			AlertDialog.Builder alert3 = new AlertDialog.Builder(this);
			alert3.setTitle("Resetting Counter");
			alert3.setMessage("Are you sure you would like to reset this counter?");
			alert3.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int whichButton) {
					reset.resetCounter("file2.json", c1, oldcountersName);
					Intent intent2 = new Intent(arg0.getContext(), CounterActivity.class);
					intent2.putExtra("CounterName", oldcountersName);
					startActivityForResult(intent2, 0);
					finish();
				 }
			});
			alert3.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
				 public void onClick(DialogInterface dialog, int whichButton) {
				    // Canceled.
				 }
			});
			alert3.show();
			break;
		case R.id.deleteButton:
			final ReadWrite delete = new ReadWrite();
			final Context c2 = getApplication();
			//http://www.androidsnippets.com/prompt-user-input-with-an-alertdialog
			AlertDialog.Builder alert4 = new AlertDialog.Builder(this);
			alert4.setTitle("Delete Counter");
			alert4.setMessage("Are you sure you would like to delete this counter?");
			alert4.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int whichButton) {
					delete.deleteCounter("file2.json", c2, oldcountersName);
					Intent intent4 = new Intent(arg0.getContext(), MainPage.class);
					startActivityForResult(intent4, 0);
					finish();
				 }
			});
			alert4.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
				 public void onClick(DialogInterface dialog, int whichButton) {
				    // Canceled.
				 }
			});
			alert4.show();
			break;
		}
	}		
}
