package com.whitepaw.marfclicker;

public abstract class MarfNumbers {
	
	private static int alltime = 0;
	private static int bank = 0;
	private static int income = 0;
	
	private static int puppies = 0;
	private static int huskies = 0;
	private static int roboskis = 0;

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
	
	public static int getPuppies() {
		return puppies;
	}
	
	public static int getHuskies() {
		return huskies;
	}
	
	public static int getRoboskis() {
		return roboskis;
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
	
	public static void incrementPuppies() {
		puppies++;
	}
	
	public static void incrementHuskies() {
		huskies++;
	}
	
	public static void incrementRoboskis() {
		roboskis++;
	}
	
	public static void reset() {
		alltime = 0;
		bank = 0;
		income = 0;
		
		puppies = 0;
		huskies = 0;
		roboskis = 0;
	}
}
