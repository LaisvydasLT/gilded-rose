package com.gildedrose.item;

public class AgedBrie extends UpdatableItem {
	
	public AgedBrie(Item item) {
		super(item);
	}

	@Override
	public void updateQuality() {
		increaseQuality();
		sellIn--;
		if (sellIn < 0) {
			increaseQuality();
		}
	}

}
