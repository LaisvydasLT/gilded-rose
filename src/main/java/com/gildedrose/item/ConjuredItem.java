package com.gildedrose.item;

public class ConjuredItem extends UpdatableItem {
	
	public ConjuredItem(Item item) {
		super(item);
	}

	@Override
	public void updateQuality() {
		decreaseQuality();
		decreaseQuality();
		sellIn--;
		if (sellIn < 0) {
			decreaseQuality();
			decreaseQuality();
		}
	}
	
}
