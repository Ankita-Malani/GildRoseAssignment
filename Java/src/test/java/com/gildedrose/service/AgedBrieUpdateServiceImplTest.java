package com.gildedrose.service;

import com.gildedrose.constants.ItemConstants;
import com.gildedrose.constants.ItemExceptionCodeConstants;
import com.gildedrose.exception.ItemUpdateException;
import com.gildedrose.model.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AgedBrieUpdateServiceImplTest {

    private AgedBrieUpdateServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new AgedBrieUpdateServiceImpl();
    }

    @Test
    void testUpdate_IncreasesQualityBy1WhenSellInPositive() {
        Item item = new Item(ItemConstants.AGED_BRIE_ITEM_NAME, 10, 10);
        service.update(item);
        assertEquals(9, item.sellIn);
        assertEquals(11, item.quality);
    }

    @Test
    void testUpdate_IncreasesQualityBy2WhenSellInNegative() {
        Item item = new Item(ItemConstants.AGED_BRIE_ITEM_NAME, 0, 10);
        service.update(item);
        assertEquals(-1, item.sellIn);
        assertEquals(12, item.quality);
    }

    @Test
    void testUpdate_QualityDoesNotExceed50() {
        Item item = new Item(ItemConstants.AGED_BRIE_ITEM_NAME, 5, 49);
        service.update(item);
        assertEquals(4, item.sellIn);
        assertEquals(50, item.quality);
    }

    @Test
    void testUpdate_QualityMaxesAt50EvenAfterSellInPassed() {
        Item item = new Item(ItemConstants.AGED_BRIE_ITEM_NAME, 0, 49);
        service.update(item);
        assertEquals(-1, item.sellIn);
        assertEquals(50, item.quality);
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
        Item brokenItem = new Item(ItemConstants.AGED_BRIE_ITEM_NAME, 5, Integer.MAX_VALUE);
        assertDoesNotThrow(() -> service.update(brokenItem));
    }
}
