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

class BackstagePassUpdateServiceImplTest {

    private BackstagePassUpdateServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new BackstagePassUpdateServiceImpl();
    }

    @Test
    void testUpdate_ItemSellInAbove10_IncreasesQualityBy1() {
        Item item = new Item(ItemConstants.BACKSTAGE_ITEM_NAME, 15, 20);
        service.update(item);
        assertEquals(14, item.sellIn);
        assertEquals(21, item.quality);
    }

    @Test
    void testUpdate_ItemSellInBetween10And6_IncreasesQualityBy2() {
        Item item = new Item(ItemConstants.BACKSTAGE_ITEM_NAME, 10, 25);
        service.update(item);
        assertEquals(9, item.sellIn);
        assertEquals(27, item.quality);
    }

    @Test
    void testUpdate_ItemSellInBetween5And0_IncreasesQualityBy3() {
        Item item = new Item(ItemConstants.BACKSTAGE_ITEM_NAME, 5, 30);
        service.update(item);
        assertEquals(4, item.sellIn);
        assertEquals(33, item.quality);
    }

    @Test
    void testUpdate_ItemSellInZeroOrLess_QualityBecomesZero() {
        Item item = new Item(ItemConstants.BACKSTAGE_ITEM_NAME, 0, 40);
        service.update(item);
        assertEquals(-1, item.sellIn);
        assertEquals(0, item.quality);
    }

    @Test
    void testUpdate_QualityNeverExceeds50() {
        Item item = new Item(ItemConstants.BACKSTAGE_ITEM_NAME, 5, 49);
        service.update(item);
        assertEquals(4, item.sellIn);
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
        Item brokenItem = new Item(ItemConstants.BACKSTAGE_ITEM_NAME, 5, Integer.MAX_VALUE);
        assertDoesNotThrow(() -> service.update(brokenItem));
    }
}
