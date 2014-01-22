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
			counterName = (EditText) findViewById(R.id.counterName);
			cName = counterName.getText().toString();
			FILENAME = cName + ".txt";
			ncount = cName + " Count:0 ";
			
			
			try{
				fos = openFileOutput(FILENAME, Context.MODE_APPEND);
				fos.write(ncount.getBytes());
				fos.close();
				fos = openFileOutput("counterList.txt", Context.MODE_APPEND);
				//cName = " "+ cName +" Count:0 ";
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
			ContextWrapper c2 = new ContextWrapper(this);
			String d1path = c2.getFilesDir().getPath().toString() + "/"+ oldcountersName +".txt";
			File d1file = new File(d1path);
			d1file.delete();
			FILENAME = oldcountersName + ".txt";
			ncount = oldcountersName + " Count:0 ";
			try {
				fos = openFileOutput(FILENAME, Context.MODE_APPEND);
				fos.write(ncount.getBytes());
				fos.close();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			/*
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
			finish();
			break;
		case R.id.deleteButton:
			//http://stackoverflow.com/questions/13616876/getting-file-path-for-local-android-project-files
			//http://stackoverflow.com/questions/5360209/how-to-delete-a-specific-string-in-a-text-file
			//http://stackoverflow.com/questions/20391671/warning-do-not-hardcode-data-use-context-getfilesdir-getpath-instead
			//http://developer.android.com/training/basics/data-storage/files.html#DeleteFile
			delete = cName;
			ContextWrapper c = new ContextWrapper(this);
			String filepath = c.getFilesDir().getPath().toString() + "/counterList.txt";
			file = new File(filepath);
			

			ContextWrapper c1 = new ContextWrapper(this);
			String dpath = c1.getFilesDir().getPath().toString() + "/"+ FILENAME;
			File dfile = new File(dpath);
			try {
				temp = File.createTempFile("file", ".txt", file.getParentFile());
			} catch (IOException e) {
				e.printStackTrace();
			}
			String charset = "UTF-8";
			try {
				reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), charset));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			try {
				writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(temp), charset));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			try {
				for (String line; (line = reader.readLine()) !=null;){
					line = line.replace(delete, "");
					//line = line.replace("\r\n", null);
					writer.println(line);
				}
				reader.close();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			file.delete();
			temp.renameTo(file);
			dfile.delete();
			Intent intent4 = new Intent(arg0.getContext(), MainPage.class);
			startActivityForResult(intent4, 0);
			finish();
			Toast.makeText(this, "Counter Deleted!", Toast.LENGTH_LONG).show();
			break;
			
		}
		
		
	}		

}
