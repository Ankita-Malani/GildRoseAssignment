package com.gildedrose;

import com.gildedrose.factory.ItemUpdaterFactory;
import com.gildedrose.model.Item;
import com.gildedrose.service.ItemUpdateService;

class GildedRoseMain {

    Item[] items;

    public GildedRoseMain(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            ItemUpdateService updater = ItemUpdaterFactory.getUpdater(item);
            updater.update(item);
        }
    }
}
