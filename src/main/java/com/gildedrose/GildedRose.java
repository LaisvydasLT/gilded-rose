package com.gildedrose;

class GildedRose {
	
	private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    private static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    private static final String AGED_BRIE = "Aged Brie";
	
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
    	for (Item item : items) {
            if (!item.name.equals(AGED_BRIE) && !item.name.equals(BACKSTAGE_PASSES)) {
                if (item.quality > 0 && !item.name.equals(SULFURAS)) {
                	item.quality--;
                }
            } else if (item.quality < 50) {
                    item.quality++;

                    if (item.name.equals(BACKSTAGE_PASSES) && item.quality < 50) {
                    	if (item.sellIn < 6) {
                        	item.quality += 2;
                        } else if (item.sellIn < 11) {
                        	item.quality++;
                        }
                    }
            }

            if (!item.name.equals(SULFURAS)) {
                item.sellIn--;
            }

            if (item.sellIn < 0) {
                if (!item.name.equals(AGED_BRIE)) {
                    if (!item.name.equals(BACKSTAGE_PASSES)) {
                        if (item.quality > 0 && !item.name.equals(SULFURAS)) {
                        	item.quality--;
                        }
                    } else {
                        item.quality = 0;
                    }
                } else if (item.quality < 50) {
                        item.quality++;
                }
            }
        }
    }
}