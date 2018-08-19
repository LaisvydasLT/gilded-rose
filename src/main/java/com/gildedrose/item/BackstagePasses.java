package com.gildedrose.item;

public class BackstagePasses extends UpdatableItem {

	public BackstagePasses(Item item) {
		super(item);
	}
	
	@Override
	public void updateQuality() {
		increaseQuality();
		if (sellIn < 11) {
			increaseQuality();
	    }
		if (sellIn < 6) {
			increaseQuality();
	    }
		sellIn--;
		if (sellIn < 0) {
			quality = 0;
		}
	}
	
	
}
