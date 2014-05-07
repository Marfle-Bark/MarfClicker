package com.whitepaw.marfclicker;

public final class ShopItem {
	private final String description;
	private int price;
	private final int boost;

	public ShopItem(String description, int price, int boost) {
		this.description = description;
		this.price = price;
		this.boost = boost;
	}

	public String getDescription() {
		return description;
	}

	public int getPrice() {
		return price;
	}
	
	public int getBoost() {
		return boost;
	}
	
	public void buy() {
		price *= 1.5;
	}
}
