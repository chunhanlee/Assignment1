package com.example.assignment1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

import com.google.gson.Gson;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewCounter extends MainPage implements OnClickListener {
	private static final String FILENAME = "file2.json";
	private EditText counterName;
	private String ncount;
	private FileOutputStream fos;
	private FileInputStream fis;
	private InputStreamReader isr;
	private BufferedReader br;
	private String cName;
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
		
		//http://stackoverflow.com/questions/10455630/pass-context-to-another-activity
		
		
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
			// took out individual file names
			//FILENAME = cName + ".txt";
			//ncount = cName + " Count:0 ";
			
			CounterModel newcounter = new CounterModel();
			newcounter.setCounterCount(0);
			newcounter.setCountDate(new Date(System.currentTimeMillis()));
			newcounter.setCounterName(cName);
			Gson gson = new Gson();
			String json = gson.toJson(newcounter);
			ReadWrite writing = new ReadWrite();
			Context c = getApplication();
			writing.saveInFile(json, FILENAME, c);
			/*
			//http://developer.android.com/training/basics/data-storage/files.html#DeleteFile
			try{
				if (cName != "counterList" || cName != null){
					fos = openFileOutput(FILENAME, Context.MODE_APPEND);
					fos.write(ncount.getBytes());
					fos.close();
					fos = openFileOutput("counterList.txt", Context.MODE_APPEND);
					//cName = " "+ cName +" Count:0 ";
					cName = cName + "\r\n";
					fos.write(cName.getBytes());
					fos.close();
					Intent intent1 = new Intent(arg0.getContext(), MainPage.class);
					startActivityForResult(intent1, 0);
					finish();
					break;
				}else{
					Toast.makeText(this, "Cannot create this counter name!", Toast.LENGTH_LONG).show();
					break;
				}
			}catch (IOException e) {
				e.printStackTrace();
			}
			*/
			/*
		case R.id.readB:
			counterName = (EditText) findViewById(R.id.counterName);
			cName = counterName.getText().toString();
			FILENAME = cName + ".txt";
			try {
				fis = openFileInput("counterList.txt");
				isr = new InputStreamReader(fis);	
				br = new BufferedReader(isr);
			
				String sLine = null;
				String out = "";
				while ((sLine = br.readLine())!=null){
					out += sLine;
				}
				Toast.makeText(this, out, Toast.LENGTH_LONG).show();
				} catch (IOException e) {
				e.printStackTrace();
			}
			*/
		}
		
		
	}		

}
