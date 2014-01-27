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
	private String counterName;
	private int counterCount;
	private List<Date> countDateList = new ArrayList<Date>();
	
	public int getCounterCount() {
		return counterCount;
	}
	public void setCounterCount(int counterCount) {
		this.counterCount = counterCount;
	}
	public String getCounterName() {
		return counterName;
	}
	public void setCounterName(String counterName) {
		this.counterName = counterName;
	}
	public List<Date> getCounterDateList() {
		return countDateList;
	}
	public void setCountDateList(Date cDate, List<Date> list) {
		list.add(cDate);
		countDateList = list;
	}

}