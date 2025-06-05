package com.gildedrose;

import com.gildedrose.model.Item;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseMainTest {

    @Test
    void foo() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRoseMain app = new GildedRoseMain(items);
        app.updateQuality();
        assertEquals("foo", app.items[0].name);
    }
}
