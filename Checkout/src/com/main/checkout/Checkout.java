package com.main.checkout;

import java.util.HashMap;
import java.util.Map;

//Checkout to scan items and calculate price
 
public class Checkout {

    private PriceCalculator order;
    private Map<String, Integer> items = new HashMap<>();

    //Constructor
    public Checkout(PriceCalculator priceCalculator) {
        this.order = priceCalculator;
    }

    //To scan items
    public void scan(String item) {
        if (!items.containsKey(item)) {
            items.put(item, 0);
        }

        items.put(item, items.get(item) + 1);
    }

    //To calculate price
    public double calculateTotalItemsPrice() {
        return order.calculateTotalItemsPrice(items);
    }

    //To clear all items
    public void clearAllItems() {
        items.clear();
    }

}
