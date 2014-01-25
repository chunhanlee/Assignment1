package com.example.assignment1;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class CounterModel {
	private String counterName;
	private int counterCount;
	private List<Date> countDate = new ArrayList<Date>();
	
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
	public List<Date> getCounterDate() {
		return countDate;
	}
	public void setCountDate(Date cDate, List<Date> list) {
		list.add(cDate);
		countDate = list;
	}

}