package com.gildedrose.service;

import com.gildedrose.constants.ItemExceptionCodeConstants;
import com.gildedrose.exception.ItemUpdateException;
import com.gildedrose.model.Item;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BackstagePassUpdateServiceImpl implements ItemUpdateService {

    @Override
    public void update(Item item) {
        log.info("BackstagePassUpdateServiceImpl :: update() :: started : Item :: {}", item);
        if (item == null) {
            throw new NullPointerException(ItemExceptionCodeConstants.ITEM_NOT_NULL_EXCEPTION);
        }
        try {
            log.info("Processing Items update");
            item.sellIn--;
            item.quality = calculateUpdatedQuality(item);
        } catch (Exception e) {
            log.error("Error updating Backstage Pass item: {}", item, e);
            throw new ItemUpdateException("Failed to update Backstage Pass item", e);
        }
        log.info("BackstagePassUpdateServiceImpl :: update() :: Finished : Item :: {}", item.toString());
    }

    private int calculateUpdatedQuality(Item item) {
        log.info("BackstagePassUpdateServiceImpl :: calculateUpdatedQuality() :: started : Item :: {}", item.toString());
        if (item.sellIn < 0)
            return 0;
        int delta = switch (item.sellIn) {
            case 0, 1, 2, 3, 4 -> 3;
            case 5, 6, 7, 8, 9 -> 2;
            default -> 1;
        };
        log.info("BackstagePassUpdateServiceImpl :: calculateUpdatedQuality() :: delta {}", delta);
        return Math.min(item.quality + delta, 50);
    }
}
