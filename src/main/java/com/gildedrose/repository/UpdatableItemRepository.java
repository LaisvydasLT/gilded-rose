package com.gildedrose.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.gildedrose.item.UpdatableItem;

@Repository
public interface UpdatableItemRepository extends ElasticsearchRepository<UpdatableItem, String> {

}
