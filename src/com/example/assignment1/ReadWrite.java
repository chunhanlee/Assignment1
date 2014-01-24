package com.example.assignment1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import android.content.Context;

public class ReadWrite {
	private List<CounterModel> list2;
	public void saveInFile(String counters, String filename, Context c){
		try {
			FileOutputStream fos = c.openFileOutput(filename, c.MODE_PRIVATE);
			fos.write(counters.getBytes());
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<CounterModel> loadFromFile(String filename, Context c) {
		List<CounterModel> counters = new ArrayList<CounterModel>();
		//list2 = counters;
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
			fis.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return (List<CounterModel>) counters;
	}
	public int getCount(String fname, Context c, String counterName){
		List<String> outlist = new ArrayList<String>();
		int count = 0;
		// Load file
		List<CounterModel> counters = loadFromFile(fname, c);
		// Create a list of all counters and their counts
		for (int i=0; i< counters.size(); i++){
			CounterModel a = counters.get(i);
			String cn = a.getCounterName();
			String cc = String.valueOf(a.getCounterCount());
			outlist.add(cn + "   Count: "+cc);
		}
		// Locate the counter 
		for (int i=0; i<outlist.size(); i++){
			if (outlist.get(i).contains(counterName)){
				String[] temp = (outlist.get(i)).split(":");
				String temp1 = temp[1].replaceAll("\\s", "");
				System.out.println("stringlist" + temp);
				System.out.println("temp1" + temp1);
				count = Integer.valueOf(temp1);
			}
		}
		// Return the count value of the counter
		return count;
	}
	
	public void setCount(String fname, Context c, String counterName, int countVal){
		List<CounterModel> outlist = new ArrayList<CounterModel>();
		//String countersName; 
		//int counterCount = 0;
		List<Date> countersDate = new ArrayList<Date>();
		// Load file
		List<CounterModel> counters = loadFromFile(fname, c);
		// Create a list of all counters and their counts
		for (int i=0; i< counters.size(); i++){
			CounterModel a = counters.get(i);
			//String cn = a.getCounterName();
			//String cc = String.valueOf(a.getCounterCount());
			outlist.add(a);
		}
		// Extract everything about the counter
		for (int i=0; i<outlist.size(); i++){
			CounterModel b = outlist.get(i);
			String name = b.getCounterName();
			if (name.equals(counterName)){
				CounterModel counter = outlist.get(i);
				//countersName = counter.getCounterName();
				//counterCount = counter.getCounterCount();
				countersDate = counter.getCounterDate();
				outlist.remove(i);
			}
		}
		
		CounterModel newcounter = new CounterModel();
		newcounter.setCounterCount(countVal);
		newcounter.setCountDate(new Date(System.currentTimeMillis()), countersDate);
		newcounter.setCounterName(counterName);
		outlist.add(newcounter);
		
		Gson gson = new Gson();
		//String json = gson.toJson(newcounter);
		String json = gson.toJson(outlist);
		//System.out.println("json string"+json);
		ReadWrite writing = new ReadWrite();
		
		writing.saveInFile(json, "file2.json", c);
		
	}

}
