package com.gildedrose.service;

import com.gildedrose.constants.ItemExceptionCodeConstants;
import com.gildedrose.exception.ItemUpdateException;
import com.gildedrose.model.Item;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AgedBrieUpdateServiceImpl implements ItemUpdateService {

    @Override
    public void update(Item item) {
        log.info("AgedBrieUpdateServiceImpl :: update() :: started : Item :: {}", item);

        if (item == null) {
            throw new NullPointerException(ItemExceptionCodeConstants.ITEM_NOT_NULL_EXCEPTION);
        }
        try {
            item.sellIn--;
            if (item.quality < 50) {
                item.quality++;
                if (item.sellIn < 0 && item.quality < 50) {
                    item.quality++;
                }
            }
        } catch (Exception e) {
            log.error("Error updating AgedBrie item: {}", item, e);
            throw new ItemUpdateException("Failed to updating AgedBrie item", e);
        }
        log.info("AgedBrieUpdateServiceImpl :: update() :: End : Item :: {}", item.toString());
    }
}
