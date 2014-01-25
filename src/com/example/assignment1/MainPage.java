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
import android.content.ClipData.Item;
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
	//private List<String> outlist1 = new ArrayList<String>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_page);
		
		Button button = (Button) findViewById(R.id.clicker);
		button.setOnClickListener(this);

		ReadWrite reader = new ReadWrite();
		ReadWrite order = new ReadWrite();
		Context context = getApplication();
		List<List<String>> listOfList = new ArrayList<List<String>>();
		//listOfList.add(new ArrayList<String>());
		//listOfList.get(0).add("");

		List<CounterModel> counters = (List<CounterModel>) reader.loadFromFile("file2.json" ,context);
		outlist = order.orderedCounters(counters);
		for (int i=0; i< counters.size(); i++){
			CounterModel a = counters.get(i);
			String cn = a.getCounterName();
			String cc = String.valueOf(a.getCounterCount());
			outlist.add(cn + "   Count: "+cc);
		}
		

		//http://stackoverflow.com/questions/8833514/populate-listview-with-dynamic-array
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, outlist);
		lv = (ListView) findViewById(R.id.list1);
		lv.setAdapter(adapter);
		((BaseAdapter) adapter).notifyDataSetChanged();

		//http://www.androidhive.info/2011/10/android-listview-tutorial/
		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id){
				String countersname = ((TextView) view).getText().toString();
				String[] countNames = countersname.split("\\s+");
				Intent i = new Intent(getApplicationContext(), CounterActivity.class);
				i.putExtra("CounterName", countNames[0]);
				startActivity(i);
				//finish();
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
		//finish();
	}
	
	@Override
	protected void onResume(){
		outlist.clear();
		ReadWrite reader = new ReadWrite();
		Context context = getApplication();
		ReadWrite order = new ReadWrite();
		List<CounterModel> counters = (List<CounterModel>) reader.loadFromFile("file2.json" ,context);
		outlist = order.orderedCounters(counters);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, outlist);
		lv = (ListView) findViewById(R.id.list1);
		lv.setAdapter(adapter);
		((BaseAdapter) adapter).notifyDataSetChanged();
		super.onResume();
	}
	

}
