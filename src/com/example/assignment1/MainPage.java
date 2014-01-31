/*
Main Page class for Assignment1 app.
Main Page is called once the application is first launched.
The user will be able to either add a counter or select a 
existing counter from the list view. 
    
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
//import java.util.Date;
import java.util.List;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

/**
 * MainPage is called when a application is first launched
 * user can add a counter or select a existing counter to 
 * increment.
 * @author Chun-Han Lee
 *
 */
public class MainPage extends Activity implements OnClickListener {

	// Declared all needed variables
	private ListView lv;
	private List<String> outlist = new ArrayList<String>();
	static final String FILENAME = "file2.json";
	
	/**
	 * Called when the activity is first created. Instantiate
	 * methods create the button and checks for a file to read 
	 * if there are existing counters saved
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_page);
		
		// Look for the button clicker and set onclicklistener
		Button button = (Button) findViewById(R.id.clicker);
		button.setOnClickListener(this);
		
		// Set up the array adapter and populate the list view to list 
		//  all the adapters 
		//http://stackoverflow.com/questions/8833514/populate-listview-with-dynamic-array
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, outlist);
		lv = (ListView) findViewById(R.id.list1);
		lv.setAdapter(adapter);
		((BaseAdapter) adapter).notifyDataSetChanged();

		// Set up the onitemclicklistener so that one a counter is clicked on the list
		//  then transition to the counter activity with the associated information 
		//  about the counter while passing in the counter name. 
		//http://www.androidhive.info/2011/10/android-listview-tutorial/
		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id){
				String countersname = ((TextView) view).getText().toString();
				String[] countNames = countersname.split("\\s+");
				Intent i = new Intent(getApplicationContext(), CounterActivity.class);
				i.putExtra("CounterName", countNames[0]);
				startActivity(i);
			}
		});
	}

	/**
	 * When clicker is clicked activity will transition to
	 * adding the new counter page.
	 */
	@Override
	public void onClick(View arg0) {
		Intent intent = new Intent(arg0.getContext(), NewCounter.class);
		startActivityForResult(intent, 0);
	}
	
	/**
	 * When activity is about to resume clear old list
	 * and order the counter and prepare the list view 
	 * to populate the new list of counters 
	 */
	@Override
	protected void onResume(){
		
		// Clear old list
		outlist.clear();
		
		// Instantiate class and set variables
		ReadWrite reader = new ReadWrite();
		Context context = getApplication();
		ReadWrite order = new ReadWrite();
		List<CounterModel> counters = (List<CounterModel>) reader.loadFromFile(FILENAME ,context);
		
		// Order the counters
		outlist = order.orderedCounters(counters);
		
		// Populate the list with the new set of counters
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, outlist);
		lv = (ListView) findViewById(R.id.list1);
		lv.setAdapter(adapter);
		((BaseAdapter) adapter).notifyDataSetChanged();
		super.onResume();
		
		
	}
}
