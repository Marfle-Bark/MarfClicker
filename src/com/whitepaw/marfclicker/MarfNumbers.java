package com.whitepaw.marfclicker;

public abstract class MarfNumbers {

	// only setting these to zero for testing
	private static int alltime = 0;
	private static int bank = 0;
	private static int income = 0;

	public static int getAlltime() { return alltime; }
	public static int getBank() { return bank; }
	public static String getBankString() { return String.valueOf(bank) + " Marfs"; }
	public static int getIncome() { return income; }
	public static String getIncomeString() { return String.valueOf(income) + "/second"; }
	
	public static void setAlltime(int input) { alltime = input; }
	public static void setBank(int input) { bank = input; }
	public static void setIncome(int input) { income = input; }
	
	public static void applyIncome() { bank += income; }
	public static void addToBank(int input) { bank += input; }
}
