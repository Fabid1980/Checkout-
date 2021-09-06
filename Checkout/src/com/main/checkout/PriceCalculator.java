package com.main.checkout;

import java.util.HashMap;
import java.util.Map;

/**
 * Price calculator for items.
 */
public class PriceCalculator {

    private Map<String, PricingRule> pricingRules = new HashMap<>();

    //To calculate price ater looking at the items pricing rules
    public double calculateTotalItemsPrice(Map<String, Integer> items) {
        double totalPrice = 0;

        for (Map.Entry<String, Integer> itemEntry : items.entrySet()) {
            String item = itemEntry.getKey();
            if (pricingRules.containsKey(item)) {
                totalPrice += calculateTotalItemPrice(item, itemEntry.getValue());
            } else {
                throw new IllegalStateException("no pricing rule found for item " + item);
            }
        }

        return totalPrice;
    }

    //To calculate price for items
    private double calculateTotalItemPrice(String item, int itemCount) {
        PricingRule pricingRule = pricingRules.get(item);

        return pricingRule.calculatePrice(itemCount);
    }

    //To add new items 
    public void registerPricingRule(String item, PricingRule pricingRule) {
        pricingRules.put(item, pricingRule);
    }
}
