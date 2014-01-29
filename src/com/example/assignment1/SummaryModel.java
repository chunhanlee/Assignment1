package com.example.assignment1;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SummaryModel {
	List<Date> dateList = new ArrayList<Date>();
	CounterModel dates = new CounterModel();
	Date tempdate = new Date();
	private int min;
	private int year;
	private int month;
	private int week;
	private int date;
	private int hour;
	public List<String> getCountYear(List<Date> counterdatelist){
		List<String> PerYear = new ArrayList<String>();
		//http://stackoverflow.com/questions/7182996/java-get-month-integer-from-date
		Calendar cal = Calendar.getInstance();
		List<Integer> yearlist = new ArrayList<Integer>();
		// Check for counters with no counts
		if (counterdatelist.isEmpty() || counterdatelist == null){
			PerYear.add("None");
			return PerYear;
		}
		for (int it =0; it< counterdatelist.size(); it++){
			tempdate = counterdatelist.get(it);
			cal.setTime(tempdate);
			year = cal.get(Calendar.YEAR);
			yearlist.add(year);
		}
		//http://www.mkyong.com/java/how-to-count-duplicated-items-in-java-list/
		Set<Integer> uniqueSet = new HashSet<Integer>(yearlist);
		for (Integer temp : uniqueSet) {
			PerYear.add("Year of "+String.valueOf(temp) + "-- " + String.valueOf(Collections.frequency(yearlist, temp)));
		}
		return PerYear;
	}
	public List<String> getCountMonth(List<Date> counterdatelist){
		List<String> PerMonth = new ArrayList<String>();
		//http://stackoverflow.com/questions/7182996/java-get-month-integer-from-date
		Calendar cal = Calendar.getInstance();
		List<String> monthlist = new ArrayList<String>();
		// Check for counters with no counts
		if (counterdatelist.isEmpty() || counterdatelist == null){
			PerMonth.add("None");
			return PerMonth;
		}
		for (int it =0; it< counterdatelist.size(); it++){
			tempdate = counterdatelist.get(it);
			cal.setTime(tempdate);
			year = cal.get(Calendar.YEAR);
			month = cal.get(Calendar.MONTH);
			monthlist.add(String.valueOf(year)+" "+String.valueOf(month));
		}
		//http://www.mkyong.com/java/how-to-count-duplicated-items-in-java-list/
		Set<String> uniqueSet = new HashSet<String>(monthlist);
		for (String temp : uniqueSet) {
			String tempmonthstr = getMonthString(temp);
			PerMonth.add("Month of "+ tempmonthstr + "-- " + String.valueOf(Collections.frequency(monthlist, temp)));
		}
		return PerMonth;
	}
	public List<String> getCountWeek(List<Date> counterdatelist){
		List<String> PerWeek = new ArrayList<String>();
		//http://stackoverflow.com/questions/7182996/java-get-month-integer-from-date
		Calendar cal = Calendar.getInstance();
		List<String> weeklist = new ArrayList<String>();
		// Check for counters with no counts
		if (counterdatelist.isEmpty() || counterdatelist == null){
			PerWeek.add("None");
			return PerWeek;
		}
		for (int it =0; it< counterdatelist.size(); it++){
			tempdate = counterdatelist.get(it);
			cal.setTime(tempdate);
			year = cal.get(Calendar.YEAR);
			month = cal.get(Calendar.MONTH);
			week = cal.get(Calendar.DAY_OF_WEEK_IN_MONTH);
			weeklist.add(String.valueOf(year)+" "+String.valueOf(month)+" "+ String.valueOf(week));
		}
		//http://www.mkyong.com/java/how-to-count-duplicated-items-in-java-list/
		Set<String> uniqueSet = new HashSet<String>(weeklist);
		for (String temp : uniqueSet) {
			String tempmonthstr = getMonthString(temp);
			String tempweekstr = getWeekString(temp);
			PerWeek.add(tempweekstr +" Week of " + tempmonthstr+"-- " + String.valueOf(Collections.frequency(weeklist, temp)));
		}
		return PerWeek;
		
	}
	public List<String> getCountDate(List<Date> counterdatelist){
		List<String> PerDate = new ArrayList<String>();
		//http://stackoverflow.com/questions/7182996/java-get-month-integer-from-date
		Calendar cal = Calendar.getInstance();
		List<String> datelist = new ArrayList<String>();
		// Check for counters with no counts
		if (counterdatelist.isEmpty() || counterdatelist == null){
			PerDate.add("None");
			return PerDate;
		}
		for (int it =0; it< counterdatelist.size(); it++){
			tempdate = counterdatelist.get(it);
			cal.setTime(tempdate);
			year = cal.get(Calendar.YEAR);
			month = cal.get(Calendar.MONTH);
			date = cal.get(Calendar.DATE);
			datelist.add(String.valueOf(year)+" "+String.valueOf(month)+" "+ String.valueOf(date));
		}
		//http://www.mkyong.com/java/how-to-count-duplicated-items-in-java-list/
		Set<String> uniqueSet = new HashSet<String>(datelist);
		for (String temp : uniqueSet) {
			String[] templist = temp.split(" ");
			String tempmonthstr = getMonthString(temp);
			String tempdatestr = templist[2];
			PerDate.add(tempmonthstr+" "+tempdatestr+ " -- " + String.valueOf(Collections.frequency(datelist, temp)));
		}
		return PerDate;
		
	}
	public List<String> getCountHour(List<Date> counterdatelist){
		List<String> PerHour = new ArrayList<String>();
		//http://stackoverflow.com/questions/7182996/java-get-month-integer-from-date
		Calendar cal = Calendar.getInstance();
		List<String> hourlist = new ArrayList<String>();
		// Check for counters with no counts
		if (counterdatelist.isEmpty() || counterdatelist == null){
			PerHour.add("None");
			return PerHour;
		}
		for (int it =0; it< counterdatelist.size(); it++){
			tempdate = counterdatelist.get(it);
			cal.setTime(tempdate);
			year = cal.get(Calendar.YEAR);
			month = cal.get(Calendar.MONTH);
			date = cal.get(Calendar.DATE);
			hour = cal.get(Calendar.HOUR_OF_DAY);
			hourlist.add(String.valueOf(year)+" "+String.valueOf(month)+" "+ String.valueOf(date)+ " "+String.valueOf(hour));
		}
		//http://www.mkyong.com/java/how-to-count-duplicated-items-in-java-list/
		Set<String> uniqueSet = new HashSet<String>(hourlist);
		for (String temp : uniqueSet) {
			String[] templist = temp.split(" ");
			String tempmonthstr = getMonthString(temp);
			String tempdatestr = templist[2];
			String temphourstr = getHourString(temp);
			PerHour.add(tempmonthstr+" "+tempdatestr+" "+temphourstr+ " -- " + String.valueOf(Collections.frequency(hourlist, temp)));
		}
		return PerHour;
		
	}
	public List<String> getCountMinut(List<Date> counterdatelist){
		List<String> PerMin = new ArrayList<String>();
		//http://stackoverflow.com/questions/7182996/java-get-month-integer-from-date
		Calendar cal = Calendar.getInstance();
		List<String> minlist = new ArrayList<String>();
		// Check for counters with no counts
		if (counterdatelist.isEmpty() || counterdatelist == null){
			PerMin.add("None");
			return PerMin;
		}
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
		return PerMin;
		
	}
	public String getMonthString(String mstr){
		String str = null;
		String[] templist = mstr.split(" ");
		String i = templist[1];
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
		return str;
	}
	public String getWeekString(String wstr){
		String str = null;
		String[] templist = wstr.split(" ");
		String i = templist[2];
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
		return str;
	}
	public String getHourString(String hstr){
		String str = null;
		String[] templist = hstr.split(" ");
		String i = templist[3];
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
		return str;
	}
}
