package com.gildedrose.scheduling;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.gildedrose.GildedRose;
import com.gildedrose.item.Item;
import com.gildedrose.item.UpdatableItem;
import com.gildedrose.service.ItemService;

@Component
public class GildedRoseScheduler {

	@Autowired
	private ItemService itemService;

	private List<UpdatableItem> updatableItems;

	@Scheduled(cron = "0 0 0 * * ?")
	public void fetchItemsUpdateQualityAndSave() {
		updatableItems = itemService.findAll();
		// lots of unnecessary item conversions just because of the rule that i can't replace items property in GildedRose class :)
		GildedRose gildedRose = new GildedRose((Item[]) updatableItems.toArray(new Item[updatableItems.size()]));
		gildedRose.updateQuality();
		for (int i = 0; i < gildedRose.getItems().length; i++) {
			updatableItems.get(i).quality = gildedRose.getItems()[i].quality;
			updatableItems.get(i).sellIn = gildedRose.getItems()[i].sellIn;
		}
		itemService.saveAll(updatableItems);
	}

}
