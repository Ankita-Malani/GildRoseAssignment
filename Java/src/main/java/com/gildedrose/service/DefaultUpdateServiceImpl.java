package com.gildedrose.service;

import com.gildedrose.constants.ItemExceptionCodeConstants;
import com.gildedrose.exception.ItemUpdateException;
import com.gildedrose.model.Item;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DefaultUpdateServiceImpl implements ItemUpdateService {

    @Override
    public void update(Item item) {
        log.info("DefaultUpdateServiceImpl :: update() :: started : Item :: {}", item);

        if (item == null) {
            throw new NullPointerException(ItemExceptionCodeConstants.ITEM_NOT_NULL_EXCEPTION);
        }
        try {
            item.sellIn--;
            if (item.quality > 0) {
                item.quality = Math.max(0, item.quality - 1);
                if (item.sellIn < 0) {
                    item.quality = Math.max(0, item.quality - 1);
                }
            }
        } catch (Exception e) {
            log.error("Error updating Default item: {}", item, e);
            throw new ItemUpdateException("Failed to update Default item", e);
        }
        log.info("DefaultUpdateServiceImpl :: update() :: Ends : Item After update:: {}", item.toString());

    }
}
