/*
Summary Page class for Assignment1 app.
Summary Page is called from the counter page activity.
The summary page aggregates all the associated data regarding
the counter's counts.
    
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;

/**
 * Summary Page activity is called from the counter page activity
 * and summarizes all of the counts associated with the counter
 * @author Chun-Han Lee
 *
 */
public class SummaryPage extends Activity implements OnClickListener{

	// Declare all needed variables 
	int curCount = 0;
	String countersName;
	private List<String> outlist = new ArrayList<String>();
	private ListView lv;
	static final String FILENAME = "file2.json";
	
	/**
	 * Called when the activity is first created. Instantiate
	 * methods create the button and checks for a file to read 
	 * dates if there are existing dates associated to the counter.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.summary_page);

		// Setup button for the back
		Button back = (Button) findViewById(R.id.button1);
		back.setOnClickListener(this);
		
		// Get the counter name 
		Intent i = getIntent();
		countersName = i.getStringExtra("CounterName");
		
		// Instantiate the class 
		ReadWrite reader = new ReadWrite();
		Context context = getApplication();

		// Read the associated file
		//  Parse out the date of the associated counter
		List<CounterModel> counters = (List<CounterModel>) reader.loadFromFile(FILENAME,context);
		List<Date> datelist = new ArrayList<Date>();
		for (int it =0; it< counters.size(); it++){
			CounterModel a = counters.get(it);
			if (a.getCounterName().equals(countersName)){
				
				datelist = a.getCounterDateList();
			}
		}
		
		// Call on methods of each statistics to display on page
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

		
		// Setup adapter to populate the list with the summary statistics
		//http://stackoverflow.com/questions/8833514/populate-listview-with-dynamic-array
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, outlist);
		lv = (ListView) findViewById(R.id.listView2);
		lv.setAdapter(adapter);
		((BaseAdapter) adapter).notifyDataSetChanged();
	}

	/**
	 * When clicker is clicked activity will transition to
	 * back to the counter activity page with the associated counter name
	 * passed on. 
	 */
	@Override
	public void onClick(View arg0) {
		String dTP = countersName;
		Intent intent2 = new Intent(arg0.getContext(), CounterActivity.class);
		intent2.putExtra("CounterName", dTP);
		startActivityForResult(intent2, 0);
		finish();
	}

}