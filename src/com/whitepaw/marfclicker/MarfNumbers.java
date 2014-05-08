package com.whitepaw.marfclicker;

//import java.util.Calendar;

public abstract class MarfNumbers {
	
	private static int alltime = 0;
	private static int bank = 0;
	private static int income = 0;
//	private static Calendar start = Calendar.getInstance(); 

	//int accessors
	public static int getAlltime() {
		return alltime;
	}

	public static int getBank() {
		return bank;
	}

	public static int getIncome() {
		return income;
	}

	//String accessors
	public static String getBankString() {
		return String.valueOf(bank) + " Marfs";
	}
	
	public static String getIncomeString() {
		return String.valueOf(income) + "/second";
	}

	//direct mutators
	public static void setAlltime(int input) {
		alltime = input;
	}

	public static void setBank(int input) {
		bank = input;
	}

	public static void setIncome(int input) {
		income = input;
	}

	//increment mutators
	public static void applyIncome() {
		bank += income;
		alltime += income;
	}
	
	public static void increaseIncome(int input)
	{
		income += input;
	}

	public static void addToBank(int input) {
		bank += input;
		alltime += input;
	}
	
	public static void reset() {
		alltime = 0;
		bank = 0;
		income = 0;
	}
}
