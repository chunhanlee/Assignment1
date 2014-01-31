/*
CounterModel class for Assignment1 app.
Aggregate functions and data for a single counter.
    
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

/**
 * Counter object with the basic functional properties.
 * The name of the counter, the number of counts of the counter,
 * and a list of entries of when the counter was clicked on.
 * 
 * @author Chun-Han Lee
 *
 */
public class CounterModel {
	// Declare all needed variables of the counter model
	private String counterName;
	private int counterCount;
	private List<Date> countDateList = new ArrayList<Date>();
	
	/**
	 * Getter for the number of counts for the counter
	 * @return  counts for the specific counter
	 */
	public int getCounterCount() {
		return counterCount;
	}
	
	/**
	 * Setter for the number of counts for the counter
	 * @param counterCount  set the counts for the counter
	 */
	public void setCounterCount(int counterCount) {
		this.counterCount = counterCount;
	}
	
	/**
	 * Getter for the counter's name
	 * @return  counters name
	 */
	public String getCounterName() {
		return counterName;
	}
	
	/**
	 * Setter for the counter's name
	 * @param counterName  set the counter's name
	 */
	public void setCounterName(String counterName) {
		this.counterName = counterName;
	}
	
	/**
	 * Getter for the list of dates the counter was pressed
	 * @return a list of dates
	 */
	public List<Date> getCounterDateList() {
		return countDateList;
	}
	
	/**
	 * Setter for the the new dates the counter was pressed
	 * @param cDate
	 * @param list
	 *  increase the date list by the new pressed dates
	 */
	public void setCountDateList(Date cDate, List<Date> list) {
		list.add(cDate);
		countDateList = list;
	}


}