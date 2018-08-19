package com.gildedrose.scheduling;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.gildedrose.GildedRose;
import com.gildedrose.item.Item;
import com.gildedrose.item.UpdatableItem;
import com.gildedrose.service.ItemService;

@Component
public class GildedRoseScheduler {

	private static final Log LOG = LogFactory.getLog(GildedRoseScheduler.class);

	@Autowired
	private ItemService itemService;

	private List<UpdatableItem> updatableItems;

	@EventListener(ApplicationReadyEvent.class)
	public void initData() {
		LOG.info("Initializing starting data");
		List<UpdatableItem> items = new ArrayList<UpdatableItem>();
		items.add(new UpdatableItem("+5 Dexterity Vest", 10, 20));
		items.add(new UpdatableItem("Aged Brie", 2, 0));
		items.add(new UpdatableItem("Elixir of the Mongoose", 5, 7));
		items.add(new UpdatableItem("Sulfuras, Hand of Ragnaros", 0, 80));
		items.add(new UpdatableItem("Sulfuras, Hand of Ragnaros", -1, 80));
		items.add(new UpdatableItem("Backstage passes to a TAFKAL80ETC concert", 15, 20));
		items.add(new UpdatableItem("Backstage passes to a TAFKAL80ETC concert", 10, 49));
		items.add(new UpdatableItem("Backstage passes to a TAFKAL80ETC concert", 5, 49));
		items.add(new UpdatableItem("Conjured Mana Cake", 3, 6));
		itemService.saveAll(items);
		LOG.info("Data initialized");
	}

	//@Scheduled(fixedDelay = 60000)
	@Scheduled(cron = "0 0 0 * * ?")
	public void fetchItemsUpdateQualityAndSave() {
		LOG.info("Fetching items from index");
		updatableItems = itemService.findAll();
		// lots of unnecessary item conversions just because of the rule that i can't
		// replace items property in GildedRose class :)
		LOG.info("Updating item quality");
		GildedRose gildedRose = new GildedRose((Item[]) updatableItems.toArray(new Item[updatableItems.size()]));
		gildedRose.updateQuality();
		for (int i = 0; i < gildedRose.getItems().length; i++) {
			updatableItems.get(i).quality = gildedRose.getItems()[i].quality;
			updatableItems.get(i).sellIn = gildedRose.getItems()[i].sellIn;
		}
		LOG.info("Saving items to index");
		itemService.saveAll(updatableItems);
		LOG.info("Item quality update completed");
		printItemInfo();
	}
	
	public void printItemInfo() {
		System.out.println("-------- " + new Date() + " --------");
		System.out.println("name, sellIn, quality");
		for (UpdatableItem item : updatableItems) {
			System.out.println(item);
		}
		System.out.println();
	}

}
