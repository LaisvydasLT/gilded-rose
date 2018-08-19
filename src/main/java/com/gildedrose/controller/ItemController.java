package com.gildedrose.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gildedrose.item.UpdatableItem;
import com.gildedrose.service.ItemService;

@RestController
@RequestMapping("/items")
public class ItemController {
	
	@Autowired
	ItemService itemService;
	
	@GetMapping
	@ResponseBody
	public List<UpdatableItem> getAllItems() {
		return itemService.findAll();
	}
	
}
