package com.main.checkout;

// Pricing rule that calculates a constant price for each item.
public class ConstantPricingRule implements PricingRule {

    private double basePrice;

    public ConstantPricingRule(double basePrice) {
        this.basePrice = basePrice;
    }

    @Override
    public double calculatePrice(int itemCount) {
        return basePrice * itemCount;
    }
}
