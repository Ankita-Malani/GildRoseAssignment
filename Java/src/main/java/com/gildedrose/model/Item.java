package com.gildedrose.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class Item {

    public String name;
    public int sellIn;
    public int quality;

}
