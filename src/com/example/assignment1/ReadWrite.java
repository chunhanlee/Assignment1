package com.example.assignment1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import android.content.Context;

public class ReadWrite {

	public void saveInFile(String counters, String filename, Context c){
		try {
			FileOutputStream fos = c.openFileOutput(filename, Context.MODE_PRIVATE);
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
		try {
			FileInputStream fis = c.openFileInput(filename);
			Gson gson = new Gson();
			
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
			List<Date> b= a.getCounterDateList();
			System.out.println(b);
			String cc = String.valueOf(b.size());
			System.out.println(cc);
			outlist.add(cn + "   Count: "+cc);
		}
		// Locate the counter 
		for (int i=0; i<outlist.size(); i++){
			String[] temp2 = outlist.get(i).split(" ");
			System.out.println("temp"+temp2[0]);
			if (temp2[0].equals(counterName)){
				String[] temp = (outlist.get(i)).split(":");
				String temp1 = temp[1].replaceAll("\\s", "");
				count = Integer.valueOf(temp1);
			}
		}
		// Return the count value of the counter
		return count;
	}
	
	public void setCount(String fname, Context c, String counterName, int countVal){
		List<CounterModel> outlist = new ArrayList<CounterModel>();
		List<Date> countersDate = new ArrayList<Date>();
		// Load file
		List<CounterModel> counters = loadFromFile(fname, c);
		// Create a list of all counters and their counts
		for (int i=0; i< counters.size(); i++){
			CounterModel a = counters.get(i);
			outlist.add(a);
		}
		// Extract everything about the counter
		for (int i=0; i<outlist.size(); i++){
			CounterModel b = outlist.get(i);
			String name = b.getCounterName();
			if (name.equals(counterName)){
				CounterModel counter = outlist.get(i);
				countersDate = counter.getCounterDateList();
				outlist.remove(i);
			}
		}
		
		CounterModel newcounter = new CounterModel();
		newcounter.setCounterCount(countVal);
		newcounter.setCountDateList(new Date(System.currentTimeMillis()), countersDate);
		newcounter.setCounterName(counterName);
		outlist.add(newcounter);
		
		Gson gson = new Gson();
		//String json = gson.toJson(newcounter);
		String json = gson.toJson(outlist);
		//System.out.println("json string"+json);
		ReadWrite writing = new ReadWrite();
		
		writing.saveInFile(json, fname, c);
		
	}

	public void deleteCounter(String fname, Context c, String counterName){
		List<CounterModel> outlist = new ArrayList<CounterModel>();
		// Load file
		List<CounterModel> counters = loadFromFile(fname, c);
		// Create a list of all counters and their counts
		for (int i=0; i< counters.size(); i++){
			CounterModel a = counters.get(i);
			outlist.add(a);
		}
		// Extract everything about the counter
		for (int i=0; i<outlist.size(); i++){
			CounterModel b = outlist.get(i);
			String name = b.getCounterName();
			// Remove counter from list
			if (name.equals(counterName)){
				outlist.remove(i);
			}
		}
		Gson gson = new Gson();
		String json = gson.toJson(outlist);
		ReadWrite writing = new ReadWrite();
		writing.saveInFile(json, fname, c);
	}
	
	public void resetCounter(String fname, Context c, String counterName){
		List<CounterModel> outlist = new ArrayList<CounterModel>();
		// Load file
		List<CounterModel> counters = loadFromFile(fname, c);
		// Create a list of all counters and their counts
		for (int i=0; i< counters.size(); i++){
			CounterModel a = counters.get(i);
			outlist.add(a);
		}
		// Extract everything about the counter
		for (int i=0; i<outlist.size(); i++){
			CounterModel b = outlist.get(i);
			String name = b.getCounterName();
			if (name.equals(counterName)){
				outlist.remove(i);
			}
		}
		
		CounterModel newcounter = new CounterModel();
		newcounter.setCounterCount(0);
		newcounter.setCounterName(counterName);
		outlist.add(newcounter);
		
		Gson gson = new Gson();
		String json = gson.toJson(outlist);
		ReadWrite writing = new ReadWrite();
		
		writing.saveInFile(json, fname, c);
	}
	
	public void renameCounter(String fname, Context c, String counterName, String oldcounterName){
		List<CounterModel> outlist = new ArrayList<CounterModel>();
		List<Date> countersDate = new ArrayList<Date>();
		int counterCount = 0;
		counterName = counterName.replaceAll("\\s+", "");
		// Load file
		List<CounterModel> counters = loadFromFile(fname, c);
		// Create a list of all counters and their counts
		for (int i=0; i< counters.size(); i++){
			CounterModel a = counters.get(i);
			outlist.add(a);
		}
		// Extract everything about the counter
		for (int i=0; i<outlist.size(); i++){
			CounterModel b = outlist.get(i);
			String name = b.getCounterName();
			if (name.equals(oldcounterName)){
				CounterModel counter = outlist.get(i);
				counterCount = counter.getCounterCount();
				countersDate = counter.getCounterDateList();
				outlist.remove(i);
			}
		}
		
		CounterModel newcounter = new CounterModel();
		newcounter.setCounterCount(counterCount);
		newcounter.setCountDateList(new Date(System.currentTimeMillis()), countersDate);
		newcounter.setCounterName(counterName);
		outlist.add(newcounter);
		
		Gson gson = new Gson();
		String json = gson.toJson(outlist);
		ReadWrite writing = new ReadWrite();
		writing.saveInFile(json, fname, c);
	}

	public void newCounter(String fname, Context c, String counterName){
		List<CounterModel> outlist = new ArrayList<CounterModel>();
		// Load file
		List<CounterModel> counters = loadFromFile(fname, c);
		// Create a list of all counters and their counts
		for (int i=0; i< counters.size(); i++){
			CounterModel a = counters.get(i);
			outlist.add(a);
		}
		// Extract everything about the counter
		for (int i=0; i<outlist.size(); i++){
			CounterModel b = outlist.get(i);
			String name = b.getCounterName();
			if (name.equals(counterName)){
				outlist.remove(i);
			}
		}
		
		CounterModel newcounter = new CounterModel();
		newcounter.setCounterCount(0);
		newcounter.setCounterName(counterName);
		outlist.add(newcounter);
		
		Gson gson = new Gson();
		String json = gson.toJson(outlist);
		ReadWrite writing = new ReadWrite();
		
		writing.saveInFile(json, fname, c);
	}

	public boolean checkCounterExist(String fname, Context c, String counterName){
		List<CounterModel> a = new ArrayList<CounterModel>();
		boolean bvalue = false;
		a = loadFromFile(fname, c);
		for (int i=0; i< a.size(); i++){
			CounterModel b = a.get(i);
			String cn = b.getCounterName();
			if (cn.equals(counterName)){
				bvalue = true;
			}
		}
		return bvalue;
	}
	//http://java2novice.com/java-collections-and-util/arraylist/sort-comparator/
	public List<String> orderedCounters(List<CounterModel> countList){
		Collections.sort(countList, new OrderCount());
		List<String> newcountList = new ArrayList<String>();
		for (int i=0; i< countList.size(); i++){
			CounterModel a = countList.get(i);
			String cn = a.getCounterName();
			List<Date> b= a.getCounterDateList();
			String cc = String.valueOf(b.size());
			newcountList.add(cn + "   Count: "+cc);
		}
		return newcountList;
	}
	
}
//http://java2novice.com/java-collections-and-util/arraylist/sort-comparator/
class OrderCount implements Comparator<CounterModel>{

	@Override
	public int compare(CounterModel e1, CounterModel e2) {
		if(e1.getCounterCount() < e2.getCounterCount()){
			return 1;
		} else {
			return -1;
		}
	}
}

