package com.example.assignment1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
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
	private String oldcountersName;
	private File file;
	private File temp;
	private String delete;
	private BufferedReader reader;
	private PrintWriter writer;
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
			String dTP = oldcountersName;
			Intent intent = new Intent(this, CounterActivity.class);
			intent.putExtra("CounterName", dTP);
			startActivityForResult(intent, 0);
			finish();
			break;
		case R.id.saveButton:
			cName = counterName.getText().toString();
			ReadWrite save = new ReadWrite();
			Context c = getApplication();
			save.renameCounter("file2.json", c, cName, oldcountersName);
			Intent intent3 = new Intent(arg0.getContext(), CounterActivity.class);
			intent3.putExtra("CounterName", cName);
			startActivityForResult(intent3, 0);
			finish();
			Toast.makeText(this, "Counter Renamed!", Toast.LENGTH_LONG).show();
			break;
		case R.id.resetButton:
			ReadWrite reset = new ReadWrite();
			Context c1 = getApplication();
			reset.resetCounter("file2.json", c1, oldcountersName);
			Intent intent2 = new Intent(arg0.getContext(), CounterActivity.class);
			intent2.putExtra("CounterName", oldcountersName);
			startActivityForResult(intent2, 0);
			finish();
			Toast.makeText(this, "Counter Reset!", Toast.LENGTH_LONG).show();
			break;
		case R.id.deleteButton:

			ReadWrite delete = new ReadWrite();
			Context c2 = getApplication();
			delete.deleteCounter("file2.json", c2, oldcountersName);
			Intent intent4 = new Intent(arg0.getContext(), MainPage.class);
			startActivityForResult(intent4, 0);
			finish();
			Toast.makeText(this, "Counter Deleted!", Toast.LENGTH_LONG).show();
			break;
			
		}

		
		
	}		

}
