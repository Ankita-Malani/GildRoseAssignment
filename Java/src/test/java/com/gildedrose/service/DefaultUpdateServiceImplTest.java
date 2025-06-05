package com.gildedrose.service;

import com.gildedrose.constants.ItemExceptionCodeConstants;
import com.gildedrose.exception.ItemUpdateException;
import com.gildedrose.model.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DefaultUpdateServiceImplTest {

    private DefaultUpdateServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new DefaultUpdateServiceImpl();
    }

    @Test
    void testUpdate_NormalDecrement() {
        Item item = new Item("Normal Item", 10, 20);
        service.update(item);
        assertEquals(9, item.sellIn);
        assertEquals(19, item.quality);
    }

    @Test
    void testUpdate_QualityDoesNotGoBelowZero() {
        Item item = new Item("Normal Item", 10, 0);
        service.update(item);
        assertEquals(9, item.sellIn);
        assertEquals(0, item.quality);
    }

    @Test
    void testUpdate_QualityDegradesTwiceAfterSellIn() {
        Item item = new Item("Normal Item", 0, 10);
        service.update(item);
        assertEquals(-1, item.sellIn);
        assertEquals(8, item.quality);
    }

    @Test
    void testUpdate_QualityDoesNotGoNegativeWhenDoubleDegrading() {
        Item item = new Item("Normal Item", 0, 1);
        service.update(item);
        assertEquals(-1, item.sellIn);
        assertEquals(0, item.quality);
    }

    @Test
    void testUpdate_NullItem_ThrowsException() {
        NullPointerException ex = assertThrows(NullPointerException.class, () -> {
            service.update(null);
        });
        assertEquals(ItemExceptionCodeConstants.ITEM_NOT_NULL_EXCEPTION, ex.getMessage());
    }

    @Test
    void testUpdate_ExceptionDuringProcessing_IsWrapped() {
        Item brokenItem = new Item("Broken Item", 5, Integer.MAX_VALUE);
        assertDoesNotThrow(() -> service.update(brokenItem));
    }
}
