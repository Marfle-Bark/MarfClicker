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

	public ShopItem(ShopItem.classes classification) {
		this.classification = classification;

		switch (this.classification) {
		case PUPPY:
			this.description = "Puppy";
			this.price = 1;
			this.initial_price = 1;
			this.boost = 1;
			break;
		case HUSKY:
			this.description = "Husky";
			this.price = 100;
			this.initial_price = 100;
			this.boost = 3;
			break;
		case ROBOSKI:
			this.description = "RoboDoge";
			this.price = 1000;
			this.initial_price = 1000;
			this.boost = 7;
			break;
		default:
			this.description = "Broken :c";
			this.price = 99999;
			this.initial_price = 99999;
			this.boost = 0;
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