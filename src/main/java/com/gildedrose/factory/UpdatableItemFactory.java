package com.gildedrose.factory;

import com.gildedrose.item.AgedBrie;
import com.gildedrose.item.BackstagePasses;
import com.gildedrose.item.Item;
import com.gildedrose.item.Sulfuras;
import com.gildedrose.item.UpdatableItem;

public class UpdatableItemFactory {
	
	private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    private static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    private static final String AGED_BRIE = "Aged Brie";

	public static UpdatableItem getUpdatableItem(Item item){
	      if(item.name.equals(SULFURAS)){
	         return new Sulfuras(item);
	      } else if(item.name.equals(BACKSTAGE_PASSES)){
	         return new BackstagePasses(item);
	      } else if(item.name.equals(AGED_BRIE)){
	         return new AgedBrie(item);
	      }
	      return new UpdatableItem(item);
	   }
	
}
