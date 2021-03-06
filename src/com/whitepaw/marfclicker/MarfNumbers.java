//serves as the core data store for the game while MainActivity is active
//(this data is written out to SharedPreferences during onPause()
//and read back in during onResume())

package com.whitepaw.marfclicker;

import android.util.Log;

public abstract class MarfNumbers {

	private static int alltime = 0;
	private static int bank = 0;
	private static int income = 0;
	private static int byPaw = 0;
	private static int byIncome = 0;

	private static int puppies = 0;
	private static int huskies = 0;
	private static int roboskis = 0;

	// int accessors
	public static int getAlltime() {
		return alltime;
	}

	public static int getBank() {
		return bank;
	}

	public static int getIncome() {
		return income;
	}

	public static int getByPaw() {
		return byPaw;
	}

	public static int getByIncome() {
		return byIncome;
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

	// String accessors
	public static String getBankString() {
		return String.valueOf(bank) + " Marfs";
	}

	public static String getIncomeString() {
		return String.valueOf(income) + "/second";
	}

	// direct mutators
	public static void setAlltime(int input) {
		alltime = input;
	}

	public static void setBank(int input) {
		bank = input;
	}

	public static void setIncome(int input) {
		income = input;
	}

	public static void setByPaw(int input) {
		byPaw = input;
	}

	public static void setByIncome(int input) {
		byIncome = input;
	}

	public static void setPuppies(int input) {
		puppies = input;
	}

	public static void setHuskies(int input) {
		huskies = input;
	}

	public static void setRoboskis(int input) {
		roboskis = input;
	}

	// increment and other mutators
	public static void applyIncome() {
		bank += income;
		alltime += income;
		byIncome += income;
	}

	public static void increaseIncome(int input) {
		income += input;
	}

	public static void addToBank(int input, boolean fromHusky) {
		bank += input;
		alltime += input;
		if (fromHusky)
			byPaw++;
	}

	// purchase-related methods
	public static void buyPuppy(int price) {
		puppies++;
		increaseIncome(1);
		spendMarfs(price);
	}

	public static void buyHusky(int price) {
		huskies++;
		increaseIncome(3);
		spendMarfs(price);
	}

	public static void buyRoboski(int price) {
		roboskis++;
		increaseIncome(7);
		spendMarfs(price);
	}

	public static void spendMarfs(int price) {
		bank -= price;
	}

	public static void buySomething(ShopItem item) {
		switch (item.getClassification()) {
		case PUPPY:
			buyPuppy(item.getPrice());
			break;
		case HUSKY:
			buyHusky(item.getPrice());
			break;
		case ROBOSKI:
			buyRoboski(item.getPrice());
			break;
		default:
			Log.d("marf",
					"Something bad happened in the MarfNumbers.buySomething() method.");
			break;
		}

		item.buy();
	}

	// resets the state of MarfNumbers
	public static void reset() {
		alltime = 0;
		bank = 0;
		income = 0;
		byPaw = 0;
		byIncome = 0;

		puppies = 0;
		huskies = 0;
		roboskis = 0;
	}
}
