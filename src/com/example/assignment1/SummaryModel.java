/*
Summary Model class for Assignment 1 app.
Aggregate functions and data for a single counter regarding the date
in which each count was logged into the application.
    
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
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Dates of each counter object with the basic functional properties.
 * The associated year, month, week, day, hour, min of each log for a 
 * specific counter.
 * @author Chun-Han Lee
 *
 */
public class SummaryModel {

	// Declare all needed variables 
	List<Date> dateList = new ArrayList<Date>();
	CounterModel dates = new CounterModel();
	Date tempdate = new Date();
	private int min;
	private int year;
	private int month;
	private int week;
	private int date;
	private int hour;
	
	/**
	 * Get count year method takes in a date list of a 
	 * associated counter and returns the list of all the years
	 * the counter has been clicked.
	 * @param counterdatelist
	 * @return
	 */
	public List<String> getCountYear(List<Date> counterdatelist){
		
		// Declare all needed variables 
		List<String> PerYear = new ArrayList<String>();
		//http://stackoverflow.com/questions/7182996/java-get-month-integer-from-date
		Calendar cal = Calendar.getInstance();
		List<Integer> yearlist = new ArrayList<Integer>();
		
		// Check for counters with no counts
		//  Return "none" as count
		if (counterdatelist.isEmpty() || counterdatelist == null){
			PerYear.add("None");
			return PerYear;
		}
		// Extract each year for the date and stores it in a list
		for (int it =0; it< counterdatelist.size(); it++){
			tempdate = counterdatelist.get(it);
			cal.setTime(tempdate);
			year = cal.get(Calendar.YEAR);
			yearlist.add(year);
		}
		
		// Looks at all the years of the associated counter and append the count
		//  into a list of strings
		//http://www.mkyong.com/java/how-to-count-duplicated-items-in-java-list/
		Set<Integer> uniqueSet = new HashSet<Integer>(yearlist);
		for (Integer temp : uniqueSet) {
			PerYear.add("Year of "+String.valueOf(temp) + "-- " + String.valueOf(Collections.frequency(yearlist, temp)));
		}
		
		// Return the list of counts 
		return PerYear;
	}
	
	/**
	 * Get count month method takes in a date list of a 
	 * associated counter and returns the list of all the years 
	 * and months the counter has been clicked.
	 * @param counterdatelist
	 * @return
	 */
	public List<String> getCountMonth(List<Date> counterdatelist){
		
		// Declared all needed variables 
		List<String> PerMonth = new ArrayList<String>();
		//http://stackoverflow.com/questions/7182996/java-get-month-integer-from-date
		Calendar cal = Calendar.getInstance();
		List<String> monthlist = new ArrayList<String>();
		
		// Check for counters with no counts
		//  Return "none" as count
		if (counterdatelist.isEmpty() || counterdatelist == null){
			PerMonth.add("None");
			return PerMonth;
		}
		
		// Extract the year and the month of all the dates for the counter
		//  and stores them in a list
		for (int it =0; it< counterdatelist.size(); it++){
			tempdate = counterdatelist.get(it);
			cal.setTime(tempdate);
			year = cal.get(Calendar.YEAR);
			month = cal.get(Calendar.MONTH);
			monthlist.add(String.valueOf(year)+" "+String.valueOf(month));
		}
		
		// Looks at all the years and months of the associated counter and append the count
		//  into a list of strings
		//http://www.mkyong.com/java/how-to-count-duplicated-items-in-java-list/
		Set<String> uniqueSet = new HashSet<String>(monthlist);
		for (String temp : uniqueSet) {
			String tempmonthstr = getMonthString(temp);
			PerMonth.add("Month of "+ tempmonthstr + "-- " + String.valueOf(Collections.frequency(monthlist, temp)));
		}
		
		// Return the list of counts
		return PerMonth;
	}
	
	/**
	 * Get count week method takes in a date list of a 
	 * associated counter and return the list of all the years, month
	 * and week the counter has been clicked.
	 * @param counterdatelist
	 * @return
	 */
	public List<String> getCountWeek(List<Date> counterdatelist){
		
		// Declare all needed variables 
		List<String> PerWeek = new ArrayList<String>();
		//http://stackoverflow.com/questions/7182996/java-get-month-integer-from-date
		Calendar cal = Calendar.getInstance();
		List<String> weeklist = new ArrayList<String>();
		
		// Check for counters with no counts
		//  Returns "none" as count
		if (counterdatelist.isEmpty() || counterdatelist == null){
			PerWeek.add("None");
			return PerWeek;
		}
		
		// Extract the year, the month, and the week of all the dates for the counter
		//  and stores them in a list
		for (int it =0; it< counterdatelist.size(); it++){
			tempdate = counterdatelist.get(it);
			cal.setTime(tempdate);
			year = cal.get(Calendar.YEAR);
			month = cal.get(Calendar.MONTH);
			week = cal.get(Calendar.DAY_OF_WEEK_IN_MONTH);
			weeklist.add(String.valueOf(year)+" "+String.valueOf(month)+" "+ String.valueOf(week));
		}
		
		// Looks at all the years, months and weeks of the associated counter and append the count
		//  into a list of strings
		//http://www.mkyong.com/java/how-to-count-duplicated-items-in-java-list/
		Set<String> uniqueSet = new HashSet<String>(weeklist);
		for (String temp : uniqueSet) {
			String tempmonthstr = getMonthString(temp);
			String tempweekstr = getWeekString(temp);
			PerWeek.add(tempweekstr +" Week of " + tempmonthstr+"-- " + String.valueOf(Collections.frequency(weeklist, temp)));
		}
		
		// Return the list of counts 
		return PerWeek;
	}
	
	/**
	 * Get count date method takes in a date list of a 
	 * associated counter and return the list of all the years, months,
	 * week, and date the counter has been clicked.
	 * @param counterdatelist
	 * @return
	 */
	public List<String> getCountDate(List<Date> counterdatelist){
		
		// Declare all needed variables 
		List<String> PerDate = new ArrayList<String>();
		//http://stackoverflow.com/questions/7182996/java-get-month-integer-from-date
		Calendar cal = Calendar.getInstance();
		List<String> datelist = new ArrayList<String>();
		
		// Check for counters with no counts
		//  Return "none"as a count
		if (counterdatelist.isEmpty() || counterdatelist == null){
			PerDate.add("None");
			return PerDate;
		}
		
		// Extract the year, the month, the week and the date of all the dates for the counter
		//  and stores them in a list
		for (int it =0; it< counterdatelist.size(); it++){
			tempdate = counterdatelist.get(it);
			cal.setTime(tempdate);
			year = cal.get(Calendar.YEAR);
			month = cal.get(Calendar.MONTH);
			date = cal.get(Calendar.DATE);
			datelist.add(String.valueOf(year)+" "+String.valueOf(month)+" "+ String.valueOf(date));
		}
		
		// Looks at all the years, months, weeks, and dates of the associated counter and append the count
		//  into a list of strings
		//http://www.mkyong.com/java/how-to-count-duplicated-items-in-java-list/
		Set<String> uniqueSet = new HashSet<String>(datelist);
		for (String temp : uniqueSet) {
			String[] templist = temp.split(" ");
			String tempmonthstr = getMonthString(temp);
			String tempdatestr = templist[2];
			PerDate.add(tempmonthstr+" "+tempdatestr+ " -- " + String.valueOf(Collections.frequency(datelist, temp)));
		}
		
		// Return the list of counts
		return PerDate;
	}
	
	/**
	 * Get count hour method takes in a date list of a 
	 * associated counter and return the list of all the years, months,
	 * week, date, and hour the counter has been clicked.
	 * @param counterdatelist
	 * @return
	 */
	public List<String> getCountHour(List<Date> counterdatelist){
		
		// Declare all needed variables 
		List<String> PerHour = new ArrayList<String>();
		//http://stackoverflow.com/questions/7182996/java-get-month-integer-from-date
		Calendar cal = Calendar.getInstance();
		List<String> hourlist = new ArrayList<String>();
		
		// Check for counters with no counts
		//  Returns "none" as count
		if (counterdatelist.isEmpty() || counterdatelist == null){
			PerHour.add("None");
			return PerHour;
		}
		
		// Extract the year, the month, the week, the date and the hour of all the dates for the counter
		//  and stores them in a list
		for (int it =0; it< counterdatelist.size(); it++){
			tempdate = counterdatelist.get(it);
			cal.setTime(tempdate);
			year = cal.get(Calendar.YEAR);
			month = cal.get(Calendar.MONTH);
			date = cal.get(Calendar.DATE);
			hour = cal.get(Calendar.HOUR_OF_DAY);
			hourlist.add(String.valueOf(year)+" "+String.valueOf(month)+" "+ String.valueOf(date)+ " "+String.valueOf(hour));
		}
		
		// Looks at all the years, months, weeks, dates and hour of the associated counter and append the count
		//  into a list of strings
		//http://www.mkyong.com/java/how-to-count-duplicated-items-in-java-list/
		Set<String> uniqueSet = new HashSet<String>(hourlist);
		for (String temp : uniqueSet) {
			String[] templist = temp.split(" ");
			String tempmonthstr = getMonthString(temp);
			String tempdatestr = templist[2];
			String temphourstr = getHourString(temp);
			PerHour.add(tempmonthstr+" "+tempdatestr+" "+temphourstr+ " -- " + String.valueOf(Collections.frequency(hourlist, temp)));
		}
		
		// Return the list of counts 
		return PerHour;		
	}
	
	/**
	 * Get count hour method takes in a date list of a 
	 * associated counter and return the list of all the years, months, 
	 * week, date, hour, and minute the counter has been clicked.
	 * @param counterdatelist
	 * @return
	 */
	public List<String> getCountMinut(List<Date> counterdatelist){
		
		// Declare all needed variables 
		List<String> PerMin = new ArrayList<String>();
		//http://stackoverflow.com/questions/7182996/java-get-month-integer-from-date
		Calendar cal = Calendar.getInstance();
		List<String> minlist = new ArrayList<String>();
		
		// Check for counters with no counts
		if (counterdatelist.isEmpty() || counterdatelist == null){
			PerMin.add("None");
			return PerMin;
		}
		
		// Extract the year, the month, the week, the date, the hour and the minute of all the dates for the counter
		//  and stores them in a list
		for (int it =0; it< counterdatelist.size(); it++){
			tempdate = counterdatelist.get(it);
			cal.setTime(tempdate);
			year = cal.get(Calendar.YEAR);
			month = cal.get(Calendar.MONTH);
			date = cal.get(Calendar.DATE);
			hour = cal.get(Calendar.HOUR_OF_DAY);
			min = cal.get(Calendar.MINUTE);
			minlist.add(String.valueOf(year)+" "+String.valueOf(month)+" "+ String.valueOf(date)+ " "+String.valueOf(hour) + " "+String.valueOf(min));
		}
		
		// Looks at all the years, months, weeks, dates, hour and minute of the associated counter and append the count
		//  into a list of strings
		//http://www.mkyong.com/java/how-to-count-duplicated-items-in-java-list/
		Set<String> uniqueSet = new HashSet<String>(minlist);
		for (String temp : uniqueSet) {
			String[] templist = temp.split(" ");
			String tempmonthstr = getMonthString(temp);
			String tempdatestr = templist[2];
			String temphourstr = getHourString(temp);
			String tempminustr = templist[4];
			String temphourstr2 = null;
			if (tempminustr.length()>1){
				temphourstr2 = temphourstr.replace("00", tempminustr);
			}else{
				temphourstr2 = temphourstr.replace("00", "0"+tempminustr);
			}
			
			PerMin.add(tempmonthstr+" "+tempdatestr+" "+temphourstr2+ " -- " + String.valueOf(Collections.frequency(minlist, temp)));
		}
		
		// Return the list of counts 
		return PerMin;
	}
	
	/**
	 * Get month string method takes in a string parse the string
	 * return the three letter month of the associated date. 
	 * @param mstr
	 * @return
	 */
	public String getMonthString(String mstr){
		// Parse string 
		String str = null;
		String[] templist = mstr.split(" ");
		String i = templist[1];
		
		// Check what integer is associated with the month
		if (i.equals("0")){
			str = "Jan";
		}
		if (i.equals("1")){
			str = "Feb";
		}
		if (i.equals("2")){
			str = "Mar";
		}
		if (i.equals("3")){
			str = "Apr";
		}
		if (i.equals("4")){
			str = "May";
		}
		if (i.equals("5")){
			str = "Jun";
		}
		if (i.equals("6")){
			str = "Jul";
		}
		if (i.equals("7")){
			str = "Aug";
		}
		if (i.equals("8")){
			str = "Sep";
		}
		if (i.equals("9")){
			str = "Oct";
		}
		if (i.equals("10")){
			str = "Nov";
		}
		if (i.equals("11")){
			str = "Dec";
		}
		
		// Returned associated string
		return str;
	}
	
	/**
	 * Get week string method takes in a string 
	 * parse the string return the associated week of the 
	 * given date
	 * @param wstr
	 * @return
	 */
	public String getWeekString(String wstr){
		
		// Parse the string 
		String str = null;
		String[] templist = wstr.split(" ");
		String i = templist[2];
		
		// Check the associated week with the date
		if (i.equals("1")){
			str = "1st";
		}
		if (i.equals("2")){
			str = "2nd";
		}
		if (i.equals("3")){
			str = "3rd";
		}
		if (i.equals("4")){
			str = "4th";
		}
		
		// Return associated string 
		return str;
	}
	
	/**
	 * Get hour string method takes in a string 
	 * parse the string return the associated hour of the 
	 * given date 
	 * @param hstr
	 * @return
	 */
	public String getHourString(String hstr){
		
		// Parse the given string
		String str = null;
		String[] templist = hstr.split(" ");
		String i = templist[3];
		
		// Check the associated hours on a 24 hour clock
		if (i.equals("1") || i.equals("13")){
			if (i.equals("1")){
				str = "1:00 AM";
			}else{
				str = "1:00 PM";
			}
		}
		if (i.equals("2") || i.equals("14")){
			if (i.equals("2")){
				str = "2:00 AM";
			}else{
				str = "2:00 PM";
			}
		}
		if (i.equals("3") || i.equals("15")){
			if (i.equals("3")){
				str = "3:00 AM";
			}else{
				str = "3:00 PM";
			}
		}
		if (i.equals("4") || i.equals("16")){
			if (i.equals("4")){
				str = "4:00 AM";
			}else{
				str = "4:00 PM";
			}
		}
		if (i.equals("5") || i.equals("17")){
			if (i.equals("5")){
				str = "5:00 AM";
			}else{
				str = "5:00 PM";
			}
		}
		if (i.equals("6") || i.equals("18")){
			if (i.equals("6")){
				str = "6:00 AM";
			}else{
				str = "6:00 PM";
			}
		}
		if (i.equals("7") || i.equals("19")){
			if (i.equals("7")){
				str = "7:00 AM";
			}else{
				str = "7:00 PM";
			}
		}
		if (i.equals("8") || i.equals("20")){
			if (i.equals("8")){
				str = "8:00 AM";
			}else{
				str = "8:00 PM";
			}
		}
		if (i.equals("9") || i.equals("21")){
			if (i.equals("9")){
				str = "9:00 AM";
			}else{
				str = "9:00 PM";
			}
		}
		if (i.equals("10") || i.equals("22")){
			if (i.equals("10")){
				str = "10:00 AM";
			}else{
				str = "10:00 PM";
			}
		}
		if (i.equals("11") || i.equals("23")){
			if (i.equals("11")){
				str = "11:00 AM";
			}else{
				str = "11:00 PM";
			}
		}
		if (i.equals("0") || i.equals("12")){
			if (i.equals("0")){
				str = "12:00 AM";
			}else{
				str = "12:00 PM";
			}
		}
		
		// Return associated string
		return str;
	}
}
