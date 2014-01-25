package com.example.assignment1;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class SummaryPage extends Activity implements OnClickListener{

	int curCount = 0;
	String countersName;
	private List<String> outlist = new ArrayList<String>();
	private ListView lv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.summary_page);

		Button back = (Button) findViewById(R.id.button1);
		back.setOnClickListener(this);
		
		Intent i = getIntent();
		countersName = i.getStringExtra("CounterName");

		ReadWrite reader = new ReadWrite();
		ReadWrite order = new ReadWrite();
		Context context = getApplication();

		List<CounterModel> counters = (List<CounterModel>) reader.loadFromFile("file2.json" ,context);
		outlist = order.orderedCounters(counters);
		/*
		for (int i=0; i< counters.size(); i++){
			CounterModel a = counters.get(i);
			String cn = a.getCounterName();
			String cc = String.valueOf(a.getCounterCount());
			outlist.add(cn + "   Count: "+cc);
		}
		*/

		//http://stackoverflow.com/questions/8833514/populate-listview-with-dynamic-array
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, outlist);
		lv = (ListView) findViewById(R.id.listView2);
		lv.setAdapter(adapter);
		((BaseAdapter) adapter).notifyDataSetChanged();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View arg0) {
		switch(arg0.getId())
		{
		case R.id.button1:
			String dTP = countersName;
			Intent intent2 = new Intent(this, CounterActivity.class);
			intent2.putExtra("CounterName", dTP);
			startActivityForResult(intent2, 0);
			finish();
			break;
	}
}
}