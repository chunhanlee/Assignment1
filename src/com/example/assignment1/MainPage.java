package com.example.assignment1;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class MainPage extends Activity implements OnClickListener {
	private ListView lv;
	private FileInputStream fis;
	private InputStreamReader isr;
	private BufferedReader br;
	private List<String> outlist = new ArrayList<String>();
	private String cName;
	private FileOutputStream fos;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_page);
		
		Button button = (Button) findViewById(R.id.clicker);
		button.setOnClickListener(this);
		System.out.println(new Date());
		/*
		Intent intent1 = getIntent();
		dataToCollect = intent1.getStringExtra("KeyToAccessData");
		if (dataToCollect != ""){
			Toast.makeText(this, dataToCollect, Toast.LENGTH_LONG).show();
		}
		*/
		//lv = (ListView) findViewById(R.id.listView1);
		//strArr = new ArrayList<String>();
		//for (int i = 0; i <2; i++){
		//	strArr.add("Row:" + dataToCollect);
		//}
		//adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, strArr);
		//lv.setAdapter(adapter);
		//adapter.notifyDataSetChanged();
		
		try {
			// Copied parts of code from lonelytweeter
			fis = openFileInput("counterList.txt");
			isr = new InputStreamReader(fis);	
			br = new BufferedReader(isr);
		
			String sLine = br.readLine();

			while (sLine!=null){
				//System.out.println(sLine);
				System.out.println(sLine);
				if (sLine != ""){
					outlist.add(sLine);
					sLine = br.readLine();
				//System.out.println(out);
				}
			System.out.println(outlist);
			//http://stackoverflow.com/questions/5520693/in-java-remove-empty-elements-from-a-list-of-strings
			outlist.removeAll(Collections.singleton(""));
			outlist.removeAll(Collections.singleton(null));
			}
			//Toast.makeText(this, (CharSequence) outlist, Toast.LENGTH_LONG).show();
			} catch (IOException e) {
				try{
					fos = openFileOutput("counterList.txt", Context.MODE_APPEND);
					//cName = " "+ cName +"Count:0 ";
					cName = "";
					fos.write(cName.getBytes());
					fos.close();
				}catch (IOException e1) {
					e1.printStackTrace();
				}
		}

		/*
		for (int i1 = 0; i1 < counterArray.length; i1++){
			System.out.println(counterArray[i1]);
		}
		*/
		//http://stackoverflow.com/questions/8833514/populate-listview-with-dynamic-array
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, outlist);
		lv = (ListView) findViewById(R.id.list1);
		lv.setAdapter(adapter);
		((BaseAdapter) adapter).notifyDataSetChanged();
		
		//http://www.androidhive.info/2011/10/android-listview-tutorial/
		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id){
				String countersname = ((TextView) view).getText().toString();
				Intent i = new Intent(getApplicationContext(), CounterActivity.class);
				i.putExtra("CounterName", countersname);
				startActivity(i);
				finish();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View arg0) {
		Intent intent = new Intent(arg0.getContext(), NewCounter.class);
		startActivityForResult(intent, 0);
		finish();
	}

}
