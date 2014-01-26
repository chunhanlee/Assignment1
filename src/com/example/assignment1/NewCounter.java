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

public class NewCounter extends Activity implements OnClickListener {
	static final String FILENAME = "file2.json";
	private EditText counterName;
	private String cName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_counter_page);
		
		Button savebutton = (Button) findViewById(R.id.saveButton);
		savebutton.setOnClickListener(this);
		
		Button cancelbutton = (Button) findViewById(R.id.cancelButton);
		cancelbutton.setOnClickListener(this);
		
		//counterName = (EditText) findViewById(R.id.counterName);
		//cName = counterName.getText().toString();
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
			Intent intent = new Intent(arg0.getContext(), MainPage.class);
			startActivityForResult(intent, 0);
			finish();
			break;
		case R.id.saveButton:
			counterName = (EditText) findViewById(R.id.counterName);
			cName = counterName.getText().toString();
			cName = cName.replaceAll("\\s+", "");
			
			//http://www.androidsnippets.com/prompt-user-input-with-an-alertdialog
			AlertDialog.Builder alert = new AlertDialog.Builder(this);
			alert.setTitle("Counter Already Exists");
			alert.setMessage("Unable to create this counter because it already exists!");
			alert.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
			  // Cancel.
			  }
			});
			
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

			Context c = getApplication();
			ReadWrite checkcounter = new ReadWrite();
			ReadWrite newcount = new ReadWrite();
			boolean countexist = false;
			countexist = checkcounter.checkCounterExist(FILENAME, c, cName);
			if (countexist == false){
				newcount.newCounter(FILENAME, c, cName);
				alert2.show();
				break;
			}else{
				alert.show();
			}
		}
	}		
}
