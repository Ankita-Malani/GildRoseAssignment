package com.gildedrose.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class Item {

    private String name;
    private int sellIn;
    private int quality;

}
