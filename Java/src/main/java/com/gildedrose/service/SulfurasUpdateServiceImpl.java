package com.gildedrose.service;

import com.gildedrose.model.Item;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SulfurasUpdateServiceImpl implements ItemUpdateService{

    @Override
    public void update(Item item) {
        log.info("SulfurasUpdateServiceImpl :: update() :: started : Item :: {}", item.toString());
        // Legendary item, does not age or degrade
    }
}
