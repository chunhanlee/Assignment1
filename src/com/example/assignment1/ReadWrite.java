package com.example.assignment1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import android.content.Context;

public class ReadWrite {
	private List<CounterModel> list2;
	public void saveInFile(String text, String filename, Context c){
		try {
			FileOutputStream fos = c.openFileOutput(filename, c.MODE_PRIVATE);
			fos.write(text.getBytes());
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<CounterModel> loadFromFile(String filename, Context c) {
		List<CounterModel> counters = new ArrayList<CounterModel>();
		list2 = counters;
		//LonelyTweetModel simpleClass = null;
		try {
			FileInputStream fis = c.openFileInput(filename);
			Gson gson = new Gson();
			//CounterModel rcounters = new CounterModel();
			//counters.add(gson.fromJson(new InputStreamReader(fis), CounterModel.class));
			//Object o = counters.get(0);
			//System.out.println("fromcounterlistinreadwrite"+((CounterModel) o).getCounterCount());
			//System.out.println("fromcounterlistinreadwrite"+((CounterModel) o).getCounterName());
			//System.out.println("fromcounterlistinreadwrite"+((CounterModel) o).getCountDate());
			
			//http://stackoverflow.com/questions/9598707/gson-throwing-expected-begin-object-but-was-begin-array
			JsonParser parser = new JsonParser();
			JsonArray jArray = parser.parse(new InputStreamReader(fis)).getAsJsonArray();
			for (JsonElement obj : jArray){
				CounterModel counter = gson.fromJson(obj, CounterModel.class);
				counters.add(counter);
			}
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return (List<CounterModel>) counters;
	}

}
