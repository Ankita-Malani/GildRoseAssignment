package com.gildedrose.service;

import com.gildedrose.constants.ItemConstants;
import com.gildedrose.model.Item;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SulfurasUpdateServiceImplTest {

    @Test
    void testUpdate_NothingChanges() {
        Item item = new Item(ItemConstants.SULFURAS_ITEM_NAME, 0, 80);
        new SulfurasUpdateServiceImpl().update(item);
        assertEquals(0, item.sellIn);
        assertEquals(80, item.quality);
    }
}
