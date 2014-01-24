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
	public List<Date> getCountDate() {
		return countDate;
	}
	public void setCountDate(Date cDate) {
		countDate.add(cDate);
	}
	public void nCounterModel(String name, int count, Date date){
		this.counterName = name;
		this.setCountDate(date);
		this.counterCount = count;
	}

}