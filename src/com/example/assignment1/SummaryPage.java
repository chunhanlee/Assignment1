package com.example.assignment1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class SummaryPage extends Activity implements OnClickListener{

	private TextView count;
	int curCount = 0;
	String countersName;

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
		Context context = getApplication();
		ReadWrite getcounts = new ReadWrite();
		// Get saved count for the counter 
		curCount = getcounts.getCount("file2.json", context, countersName);
		count.setText(""+curCount);
		
		Button back = (Button) findViewById(R.id.backButton);
		back.setOnClickListener(this);

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
			String dTP = countersName;
			Intent intent2 = new Intent(this, CounterActivity.class);
			intent2.putExtra("CounterName", dTP);
			startActivityForResult(intent2, 0);
			finish();
			break;
	}
}
}