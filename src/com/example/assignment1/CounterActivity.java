package com.example.assignment1;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class CounterActivity extends Activity implements OnClickListener {

	private TextView count;
	int curCount = 0;
	private String countersName;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.counter_page);
		
		count = (TextView) findViewById(R.id.countView);
		Button click = (Button) findViewById(R.id.clicker);
		Intent i = getIntent();
		countersName = i.getStringExtra("CounterName");
		click.setText(countersName);
		click.setOnClickListener(this);
		
		Button back = (Button) findViewById(R.id.backButton);
		back.setOnClickListener(this);
		/*
		Button summary = (Button) findViewById(R.id.summaryButton);
		summary.setOnClickListener(this);
		*/
		Button edit = (Button) findViewById(R.id.editButton);
		edit.setOnClickListener(this);
		
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
		case R.id.backButton:
			Intent intent = new Intent(arg0.getContext(), MainPage.class);
			startActivityForResult(intent, 0);
			break;
		//case R.id.summaryButton:
			
		case R.id.editButton:
			String dTP = countersName;
			Intent intent2 = new Intent(this, EditPage.class);
			intent2.putExtra("CounterName", dTP);
			startActivityForResult(intent2, 0);
			break;
		case R.id.clicker:
			curCount++;
			count.setText(String.valueOf(curCount));
			break;
		}
	}

}
