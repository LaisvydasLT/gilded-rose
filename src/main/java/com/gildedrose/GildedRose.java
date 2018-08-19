package com.gildedrose;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

class GildedRose {

	private static final Set<String> SPECIAL_ITEMS;
	private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
	private static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
	private static final String AGED_BRIE = "Aged Brie";

	private static final int MAX_QUALITY = 50;
	private static final int MIN_QUALITY = 0;

	static {
		SPECIAL_ITEMS = new HashSet<String>();
		SPECIAL_ITEMS.add(SULFURAS);
		SPECIAL_ITEMS.add(BACKSTAGE_PASSES);
		SPECIAL_ITEMS.add(AGED_BRIE);
	}

	Item[] items;

	public GildedRose(Item[] items) {
		this.items = items;
	}

	public void updateQuality() {
		List<Item> itemList = Arrays.asList(items);
		CompletableFuture.runAsync(
				() -> itemList.parallelStream().filter(i -> i.name.equals(AGED_BRIE)).forEach(i -> updateAgedBrie(i)));
		CompletableFuture.runAsync(() -> itemList.parallelStream().filter(i -> i.name.equals(BACKSTAGE_PASSES))
				.forEach(i -> updateBackstagePasses(i)));
		CompletableFuture.runAsync(() -> itemList.parallelStream().filter(i -> !SPECIAL_ITEMS.contains(i.name))
				.forEach(i -> updateOtherItem(i)));
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