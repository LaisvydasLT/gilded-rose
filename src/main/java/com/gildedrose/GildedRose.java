package com.gildedrose;

class GildedRose {

	private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
	private static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
	private static final String AGED_BRIE = "Aged Brie";

	private static final int MAX_QUALITY = 50;
	private static final int MIN_QUALITY = 0;

	Item[] items;

	public GildedRose(Item[] items) {
		this.items = items;
	}

	public void updateQuality() {
		for (Item item : items) {
			switch (item.name) {
			case AGED_BRIE: {
				updateAgedBrie(item);
				break;
			}
			case BACKSTAGE_PASSES: {
				updateBackstagePasses(item);
				break;
			}
			case SULFURAS: {
				break;
			}
			default: {
				updateOtherItem(item);
			}
			}
		}
	}

	private void updateAgedBrie(Item item) {
		increaseQuality(item);
		item.sellIn--;
		if (item.sellIn < 0) {
			increaseQuality(item);
		}
	}

	private void updateBackstagePasses(Item item) {
		increaseQuality(item);
		if (item.sellIn < 11) {
			increaseQuality(item);
		}
		if (item.sellIn < 6) {
			increaseQuality(item);
		}
		item.sellIn--;
		if (item.sellIn < 0) {
			item.quality = 0;
		}
	}

	private void updateOtherItem(Item item) {
		decreaseQuality(item);
		item.sellIn--;
		if (item.sellIn < 0) {
			decreaseQuality(item);
		}
	}

	public void increaseQuality(Item item) {
		if (item.quality < MAX_QUALITY) {
			item.quality++;
		}
	}

	public void decreaseQuality(Item item) {
		if (item.quality > MIN_QUALITY) {
			item.quality--;
		}
	}
}