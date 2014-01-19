package com.example.assignment1;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class MainPage extends Activity implements OnClickListener {
	private ListView lv;
	//private ArrayList<String> strArr;
	//private ArrayAdapter<String> adapter;
	private FileInputStream fis;
	private InputStreamReader isr;
	private BufferedReader br;
	private String[] counterArray;
	private String out = "";
	String dataToCollect;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_page);
		
		Button button = (Button) findViewById(R.id.clicker);
		
		button.setOnClickListener(this);
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
			fis = openFileInput("counterList.txt");
			isr = new InputStreamReader(fis);	
			br = new BufferedReader(isr);
		
			String sLine = null;

			while ((sLine = br.readLine())!=null){
				out += sLine;
			}
			Toast.makeText(this, out, Toast.LENGTH_LONG).show();
			} catch (IOException e) {
			e.printStackTrace();
		}
		counterArray = out.split(" ");
		for (int i1 = 0; i1 < counterArray.length; i1++){
			System.out.println(counterArray[i1]);
		}
		ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, counterArray);
		ListView cList = (ListView) findViewById(R.id.listView1);
		cList.setAdapter(adapter);
		((BaseAdapter) adapter).notifyDataSetChanged();
		
			/*
		SharedPreferences counterList = getSharedPreferences("counters.txt", MODE_WORLD_READABLE);
		String cName = counterList.getString("CounterName", "hey3");
		String cCount = counterList.getString("CountVal", "");
		System.out.println(cName);
		System.out.println(cCount);
		*/
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(arg0.getContext(), NewCounter.class);
		startActivityForResult(intent, 0);
	}

}
