package com.gildedrose.item;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;


@Document(indexName = "gildedrose", type = "items")
public class UpdatableItem extends Item {
	
	private static final int MAX_QUALITY = 50;
	private static final int MIN_QUALITY = 0;
	
	@Id
	private String id;
	
	public UpdatableItem() {
		super(null, 0, 0);
	}
	
	public UpdatableItem(String name, int sellIn, int quality, String id) {
		super(name, sellIn, quality);
		this.id = id;
	}
	
	public UpdatableItem(String name, int sellIn, int quality) {
		super(name, sellIn, quality);
	}

	public UpdatableItem(Item item) {
		super(item.name, item.sellIn, item.quality);
	}

	public void updateQuality() {
		decreaseQuality();
		sellIn--;
		if (sellIn < 0) {
			decreaseQuality();
		}
	}

	public void increaseQuality() {
		if (quality < MAX_QUALITY) {
			quality++;
		}
	}

	public void decreaseQuality() {
		if (quality > MIN_QUALITY) {
			quality--;
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
