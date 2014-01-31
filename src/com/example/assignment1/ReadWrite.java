/*
ReadWrite class for Assignment 1 app.
Read write methods which most of which are called by
the different classes to save and read the counters data.
    
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

/**
 * Read write methods associated with reading and storing data
 * @author Chun-Han Lee
 *
 */
public class ReadWrite {
	
	/**
	 * Save in file method opens the file and serializes the counter object
	 * and stores the object information into the file
	 * @param counters
	 * @param filename
	 * @param c
	 */
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
	
	/**
	 * Load from file method deserialize objects and return a list of 
	 * counter model objects.
	 * @param filename
	 * @param c
	 * @return
	 */
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
	
	/**
	 * Get Count method returns the associated count to the counter 
	 * requested.
	 * @param fname
	 * @param c
	 * @param counterName
	 * @return
	 */
	public int getCount(String fname, Context c, String counterName){
		
		// Declare needed variables
		List<String> outlist = new ArrayList<String>();
		int count = 0;
		
		// Load file
		List<CounterModel> counters = loadFromFile(fname, c);
		
		// Create a list of all counters and their counts
		for (int i=0; i< counters.size(); i++){
			CounterModel a = counters.get(i);
			String cn = a.getCounterName();
			List<Date> b= a.getCounterDateList();
			String cc = String.valueOf(b.size());
			outlist.add(cn + "   Count: "+cc);
		}
		// Locate the counter 
		for (int i=0; i<outlist.size(); i++){
			String[] temp2 = outlist.get(i).split(" ");
			if (temp2[0].equals(counterName)){
				String[] temp = (outlist.get(i)).split(":");
				String temp1 = temp[1].replaceAll("\\s", "");
				count = Integer.valueOf(temp1);
			}
		}
		// Return the count value of the counter
		return count;
	}
	
	/**
	 * Set Count method sets the count of the associated counter
	 * to the current count.
	 * @param fname
	 * @param c
	 * @param counterName
	 * @param countVal
	 */
	public void setCount(String fname, Context c, String counterName, int countVal){
		
		// Declare all needed variables
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
		// Set count of counter
		CounterModel newcounter = new CounterModel();
		newcounter.setCounterCount(countVal);
		
		// Add date into the date list
		newcounter.setCountDateList(new Date(System.currentTimeMillis()), countersDate);
		
		// Add the name of counter
		newcounter.setCounterName(counterName);
		
		// Add the new counter object into a list
		outlist.add(newcounter);
		
		// Serialize object
		Gson gson = new Gson();
		String json = gson.toJson(outlist);
		ReadWrite writing = new ReadWrite();
		
		// Write the object into the file
		writing.saveInFile(json, fname, c);
	}

	/**
	 * Delete counter method deletes the associated counter 
	 * and all information related to the counter.
	 * @param fname
	 * @param c
	 * @param counterName
	 */
	public void deleteCounter(String fname, Context c, String counterName){
		
		// Create a list for counter objects
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
		
		// Serialize the object
		Gson gson = new Gson();
		String json = gson.toJson(outlist);
		ReadWrite writing = new ReadWrite();
		
		// Write the object back into the file
		writing.saveInFile(json, fname, c);
	}
	
	/**
	 * Reset Counter method resets the associated counter's count
	 * to zero and clears the date log. 
	 * @param fname
	 * @param c
	 * @param counterName
	 */
	public void resetCounter(String fname, Context c, String counterName){
		
		// Create a list counter objects 
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
		
		// Delete old counter add new counters information
		CounterModel newcounter = new CounterModel();
		newcounter.setCounterCount(0);
		newcounter.setCounterName(counterName);
		outlist.add(newcounter);
		
		// Serialize the object
		Gson gson = new Gson();
		String json = gson.toJson(outlist);
		ReadWrite writing = new ReadWrite();
		
		// Write the object into the file
		writing.saveInFile(json, fname, c);
	}
	
	/**
	 * Rename counter method renames the associated counter 
	 * while keeping the log the same as previous counter.
	 * @param fname
	 * @param c
	 * @param counterName
	 * @param oldcounterName
	 */
	public void renameCounter(String fname, Context c, String counterName, String oldcounterName){
		
		// Declare all need variables 
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
		// Recreate the counter with the same data while
		//  changing the name of the counter to the new name given
		CounterModel newcounter = new CounterModel();
		newcounter.setCounterCount(counterCount);
		newcounter.setCountDateList(new Date(System.currentTimeMillis()), countersDate);
		newcounter.setCounterName(counterName);
		outlist.add(newcounter);
		
		// Serialize the object
		Gson gson = new Gson();
		String json = gson.toJson(outlist);
		ReadWrite writing = new ReadWrite();
		
		// Write the object back to the file
		writing.saveInFile(json, fname, c);
	}

	/**
	 * New counter method creates a new counter with count 0
	 * @param fname
	 * @param c
	 * @param counterName
	 */
	public void newCounter(String fname, Context c, String counterName){
		
		// Create a counter object list 
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
		
		// Create the new counter object
		CounterModel newcounter = new CounterModel();
		newcounter.setCounterCount(0);
		newcounter.setCounterName(counterName);
		outlist.add(newcounter);
		
		// Serialize the object
		Gson gson = new Gson();
		String json = gson.toJson(outlist);
		ReadWrite writing = new ReadWrite();
		
		// Write the object into file
		writing.saveInFile(json, fname, c);
	}

	/**
	 * Check Counter Exist method checks if the counter name
	 * already exists in the file and returns a boolean.
	 * @param fname
	 * @param c
	 * @param counterName
	 * @return
	 */
	public boolean checkCounterExist(String fname, Context c, String counterName){
		
		// Declare all needed variables
		List<CounterModel> a = new ArrayList<CounterModel>();
		boolean bvalue = false;
		a = loadFromFile(fname, c);
		
		// Check if name exists if so return true
		for (int i=0; i< a.size(); i++){
			CounterModel b = a.get(i);
			String cn = b.getCounterName();
			if (cn.equals(counterName)){
				bvalue = true;
			}
		}
		
		// Return the boolean value 
		return bvalue;
	}
	
	/**
	 * Order counters method orders the counts of the counters
	 * by descending order return the sorted list of counters name and count
	 * 	//http://java2novice.com/java-collections-and-util/arraylist/sort-comparator/
	 * @param countList
	 * @return
	 */
	public List<String> orderedCounters(List<CounterModel> countList){
		
		// Sort the collection
		Collections.sort(countList, new OrderCount());
		List<String> newcountList = new ArrayList<String>();
		
		// Create a list of counters with associated counts
		for (int i=0; i< countList.size(); i++){
			CounterModel a = countList.get(i);
			String cn = a.getCounterName();
			List<Date> b= a.getCounterDateList();
			String cc = String.valueOf(b.size());
			newcountList.add(cn + "   Count: "+cc);
		}
		
		// Return the list of counters with associated counts
		return newcountList;
	}
}

/**
 * Order counter class uses comparator method checks and organize the 
 * counters in descending order.
 * @author Chun-Han Lee
 * //http://java2novice.com/java-collections-and-util/arraylist/sort-comparator/
 */
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

