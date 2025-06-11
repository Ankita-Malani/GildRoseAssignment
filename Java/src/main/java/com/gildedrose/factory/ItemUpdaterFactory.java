package com.gildedrose.factory;

import com.gildedrose.constants.ItemConstants;
import com.gildedrose.model.Item;
import com.gildedrose.service.*;
import com.gildedrose.constants.ItemExceptionCodeConstants;

public class ItemUpdaterFactory {

    public static ItemUpdateService getUpdater(Item item) {
        if(item != null) {
            return switch (item.name) {
                case ItemConstants.AGED_BRIE_ITEM_NAME -> new AgedBrieUpdateServiceImpl();
                case ItemConstants.BACKSTAGE_ITEM_NAME -> new BackstagePassUpdateServiceImpl();
                case ItemConstants.SULFURAS_ITEM_NAME -> new SulfurasUpdateServiceImpl();
                default -> new DefaultUpdateServiceImpl();
            };
        } else {
            throw new NullPointerException(ItemExceptionCodeConstants.ITEM_NOT_NULL_EXCEPTION);
        }
    }
}
