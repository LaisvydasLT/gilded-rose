package com.gildedrose.service;

import java.util.List;

import com.gildedrose.item.UpdatableItem;

public interface ItemService {
	
	UpdatableItem save(UpdatableItem item);

    void delete(UpdatableItem item);

    UpdatableItem findOne(String id);

    List<UpdatableItem> findAll();

}
