package com.gildedrose;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.gildedrose.factory.UpdatableItemFactory;
import com.gildedrose.item.Item;
import com.gildedrose.item.UpdatableItem;

class GildedRose {

	Item[] items;

	public GildedRose(Item[] items) {
		this.items = items;
	}

	public void updateQuality() {
		List<Item> itemList = Arrays.asList(items);
		List<UpdatableItem> updatableItems = itemList.stream().map(item -> UpdatableItemFactory.getUpdatableItem(item))
				.collect(Collectors.toList());
		updatableItems.parallelStream().forEach(item -> item.updateQuality());
		items = (Item[]) updatableItems.toArray(new Item[updatableItems.size()]);
	}

}