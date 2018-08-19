package com.gildedrose;

import static org.junit.Assert.*;

import org.junit.Test;

import com.gildedrose.GildedRose;
import com.gildedrose.item.Item;

public class GildedRoseTest {
	
	@Test
    public void updateQuality_QualityIsZero_QualityStaysZero() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
        assertEquals(-1, app.items[0].sellIn);
    }
    
    @Test
    public void updateQuality_SimpleItem_LoseOneQuality() {
        Item[] items = new Item[] { new Item("foo", 4, 5) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(4, app.items[0].quality);
        assertEquals(3, app.items[0].sellIn);
    }
    
    @Test
    public void updateQuality_SimpleItemSellInLessThanOne_LoseTwoQuality() {
        Item[] items = new Item[] { new Item("foo", -1, 2) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
        assertEquals(-2, app.items[0].sellIn);
    }
    
    @Test
    public void updateQuality_AgedBrie_GainOneQuality() {
        Item[] items = new Item[] { new Item("Aged Brie", 10, 10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(11, app.items[0].quality);
        assertEquals(9, app.items[0].sellIn);
    }
    
    @Test
    public void updateQuality_AgedBrieSellInLessThanOne_GainTwoQuality() {
        Item[] items = new Item[] { new Item("Aged Brie", -1, 10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(12, app.items[0].quality);
        assertEquals(-2, app.items[0].sellIn);
    }
    
    @Test
    public void updateQuality_AgedBrieMaxQuality_QualityStaysTheSame() {
        Item[] items = new Item[] { new Item("Aged Brie", 5, 50) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, app.items[0].quality);
        assertEquals(4, app.items[0].sellIn);
    }
    
    @Test
    public void updateQuality_BackstagePassesSellInMoreThan11_GainOneQuality() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 12, 10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(11, app.items[0].quality);
        assertEquals(11, app.items[0].sellIn);
    }
    
    @Test
    public void updateQuality_BackstagePassesSellInBetween6And11_GainTwoQuality() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 10, 10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(12, app.items[0].quality);
        assertEquals(9, app.items[0].sellIn);
    }
    
    @Test
    public void updateQuality_BackstagePassesSellInLessThan6_GainThreeQuality() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 5, 10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(13, app.items[0].quality);
        assertEquals(4, app.items[0].sellIn);
    }
    
    @Test
    public void updateQuality_BackstagePassesSellInLessThanOne_QualityIsZero() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 0, 50) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
        assertEquals(-1, app.items[0].sellIn);
    }
    
    @Test
    public void updateQuality_Sulfuras_NothingChanged() {
    	int startingQuality = 80;
    	int startingSellIn = 0;
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", startingSellIn, startingQuality) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(startingQuality, app.items[0].quality);
        assertEquals(startingSellIn, app.items[0].sellIn);
    }
    
    @Test
    public void updateQuality_Conjured_LoseTwoQuality() {
        Item[] items = new Item[] { new Item("Conjured Mana Cake", 10, 20) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(18, app.items[0].quality);
        assertEquals(9, app.items[0].sellIn);
    }
    
    @Test
    public void updateQuality_ConjuredSellInLessThanOne_LoseFourQuality() {
        Item[] items = new Item[] { new Item("Conjured Mana Cake", 0, 20) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(16, app.items[0].quality);
        assertEquals(-1, app.items[0].sellIn);
    }

}

