package com.example.assignment1;

import java.util.Date;


public class CounterModel {
	private String counterName;
	private int counterCount;
	private Date countDate;
	
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
	public Date getCountDate() {
		return countDate;
	}
	public void setCountDate(Date countDate) {
		this.countDate = countDate;
	}
	
}