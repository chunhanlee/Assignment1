package com.example.assignment1;


import java.util.ArrayList;
import java.util.Date;
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
		Context context = getApplication();

		List<CounterModel> counters = (List<CounterModel>) reader.loadFromFile("file2.json" ,context);
		List<Date> datelist = new ArrayList<Date>();
		for (int it =0; it< counters.size(); it++){
			CounterModel a = counters.get(it);
			if (a.getCounterName().equals(countersName)){
				
				datelist = a.getCounterDateList();
			}
		}
		SummaryModel summ = new SummaryModel();
		outlist.add("Counts per minute:");
		outlist.addAll(summ.getCountMinut(datelist));
		outlist.add("Counts per hour:");
		outlist.addAll(summ.getCountHour(datelist));
		outlist.add("Counts per day:");
		outlist.addAll(summ.getCountDate(datelist));
		outlist.add("Counts per month:");
		outlist.addAll(summ.getCountMonth(datelist));
		outlist.add("Counts per Year:");
		outlist.addAll(summ.getCountYear(datelist));

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
		String dTP = countersName;
		Intent intent2 = new Intent(arg0.getContext(), CounterActivity.class);
		intent2.putExtra("CounterName", dTP);
		startActivityForResult(intent2, 0);
		finish();
	}

}