package com.gildedrose.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gildedrose.item.UpdatableItem;
import com.gildedrose.repository.UpdatableItemRepository;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private UpdatableItemRepository updatableItemRepository;

	@Override
	public UpdatableItem save(UpdatableItem item) {
		return updatableItemRepository.save(item);
	}

	@Override
	public void delete(UpdatableItem item) {
		updatableItemRepository.delete(item);
	}

	@Override
	public UpdatableItem findOne(String id) {
		return updatableItemRepository.findOne(id);
	}

	@Override
	public List<UpdatableItem> findAll() {
		List<UpdatableItem> items = new ArrayList<UpdatableItem>();
		updatableItemRepository.findAll().forEach(items::add);
		return items;
	}

}
