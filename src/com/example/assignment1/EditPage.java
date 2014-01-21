package com.example.assignment1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditPage extends Activity implements OnClickListener {
	
	private String FILENAME;
	private EditText counterName;
	private String ncount;
	private FileOutputStream fos;
	private FileInputStream fis;
	private InputStreamReader isr;
	private BufferedReader br;
	private String cName;
	private String countersName;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_counter_page);
		

		Button savebutton = (Button) findViewById(R.id.saveButton);
		savebutton.setOnClickListener(this);
		
		Button cancelbutton = (Button) findViewById(R.id.cancelButton);
		cancelbutton.setOnClickListener(this);
		
		Button resetButton = (Button) findViewById(R.id.resetButton);
		resetButton.setOnClickListener(this);
		
		Button deleteButton = (Button) findViewById(R.id.deleteButton);
		deleteButton.setOnClickListener(this);
		
		Intent i = getIntent();
		countersName = i.getStringExtra("CounterName");
		
		//http://stackoverflow.com/questions/4590957/how-to-set-text-in-an-edittext
		counterName = (EditText) findViewById(R.id.counterName);
		counterName.setText(countersName, TextView.BufferType.EDITABLE);
		cName = counterName.getText().toString();
		FILENAME = cName + ".txt";
		
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
			FILENAME = cName + ".txt";
			ncount = cName + " Count:0 ";
			
			//http://developer.android.com/training/basics/data-storage/files.html#DeleteFile
			try{
				fos = openFileOutput(FILENAME, Context.MODE_APPEND);
				fos.write(ncount.getBytes());
				fos.close();
				fos = openFileOutput("counterList.txt", Context.MODE_APPEND);
				cName = " "+ cName +" Count:0 ";
				fos.write(cName.getBytes());
				fos.close();
			}catch (IOException e) {
				e.printStackTrace();
			}
			
			Intent intent1 = new Intent(arg0.getContext(), MainPage.class);
			startActivityForResult(intent1, 0);
			finish();
			break;
		case R.id.resetButton:
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
			finish();
			break;
		case R.id.deleteButton:
			///
			finish();
			break;
			
		}
		
		
	}		

}
