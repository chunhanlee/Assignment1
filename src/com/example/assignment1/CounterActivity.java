/*
Counter class for Assignment1 app.
CounterActivity is called when a counter is selected
to either be incremented edited or summarized.
This activity uses the counter_page.xml to control
all the functionalities specified above.
    
    License GPLv3: GNU GPL Version 3
    <http://gnu.org/licenses/gpl.html>.
    
    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package com.example.assignment1;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class CounterActivity extends Activity implements OnClickListener {

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
		
		Button summary = (Button) findViewById(R.id.summaryButton);
		summary.setOnClickListener(this);
		
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
			finish();
			break;
		case R.id.summaryButton:
			String dTP1 = countersName;
			Intent intent3 = new Intent(this, SummaryPage.class);
			intent3.putExtra("CounterName", dTP1);
			startActivityForResult(intent3, 0);
			finish();
			break;
		case R.id.editButton:
			String dTP = countersName;
			Intent intent2 = new Intent(this, EditPage.class);
			intent2.putExtra("CounterName", dTP);
			startActivityForResult(intent2, 0);
			finish();
			break;
		case R.id.clicker:
			Context context = getApplication();
			ReadWrite getcounts = new ReadWrite();
			curCount = getcounts.getCount("file2.json", context, countersName);
			curCount++;
			ReadWrite setcounts = new ReadWrite();
			count.setText(String.valueOf(curCount));
			setcounts.setCount("file2.json", context, countersName, curCount);
			break;
		}
	}

}
