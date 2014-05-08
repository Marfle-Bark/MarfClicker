package com.whitepaw.marfclicker;

public final class ShopItem {
	private final String description;
	private int price;
	private int initial_price;
	private final int boost;
	private final classes classification;

	public static enum classes {
		PUPPY, HUSKY, ROBOSKI
	}

	public ShopItem(ShopItem.classes classification, int price, int boost) {
		this.classification = classification;
		this.price = price;
		this.initial_price = price;
		this.boost = boost;

		switch (this.classification) {
		case PUPPY:
			this.description = "Puppy";
			break;
		case HUSKY:
			this.description = "Husky";
			break;
		case ROBOSKI:
			this.description = "RoboDoge";
			break;
		default:
			this.description = "Broken :c";
			break;
		}
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

	public classes getClassification() {
		return this.classification;
	}

	public void setPrice(int input) {
		this.price = input;
		this.initial_price = input;
	}

	public void buy() {
		if(price == 1)
			price = 2;
		else
			price *= 1.5;
	}

	public void reset() {
		price = initial_price;
	}
}
