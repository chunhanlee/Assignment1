package com.example.assignment1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;

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
import android.widget.Toast;

public class NewCounter extends Activity implements OnClickListener {
	static final String FILENAME = "file2.json";
	private EditText counterName;
	private String ncount;
	private FileOutputStream fos;
	private FileInputStream fis;
	private InputStreamReader isr;
	private BufferedReader br;
	private String cName;
	private List<Date> dates = new ArrayList<Date>(); 
	private List<CounterModel> counterlist = new ArrayList<CounterModel>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_counter_page);
		
		Button savebutton = (Button) findViewById(R.id.saveButton);
		savebutton.setOnClickListener(this);
		
		Button cancelbutton = (Button) findViewById(R.id.cancelButton);
		cancelbutton.setOnClickListener(this);
		
		counterName = (EditText) findViewById(R.id.counterName);
		cName = counterName.getText().toString();
		//FILENAME = cName + ".txt";
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View arg0) {
		
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
			cName = cName.replaceAll("\\s", "");
			
			//http://www.androidsnippets.com/prompt-user-input-with-an-alertdialog
			AlertDialog.Builder alert = new AlertDialog.Builder(this);
			alert.setTitle("Counter Already Exists");
			alert.setMessage("Unable to create this counter because it already exists!");
			alert.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
			  // Cancel.
				dialog.cancel();
			  }
			});
			
			AlertDialog.Builder alert2 = new AlertDialog.Builder(this);
			alert2.setTitle("Counter Created!");
			alert2.setMessage("Your new counter has been created!");

			Context c = getApplication();
			ReadWrite checkcounter = new ReadWrite();
			ReadWrite newcount = new ReadWrite();
			boolean countexist = false;
			countexist = checkcounter.checkCounterExist(FILENAME, c, cName);
			if (countexist == false){
				newcount.newCounter(FILENAME, c, cName);
				alert2.show();
				//http://stackoverflow.com/questions/14853325/how-to-dismiss-alertdialog-in-android
				//((DialogInterface) alert2).dismiss();
				Intent intent1 = new Intent(arg0.getContext(), MainPage.class);
				startActivityForResult(intent1, 0);
				finish();
				break;
			}else{
				alert.show();
			}
		}
	}		
}
