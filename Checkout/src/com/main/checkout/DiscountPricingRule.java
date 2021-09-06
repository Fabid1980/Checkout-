package com.main.checkout;

// Pricing rule that calculates a discount price for every n-th item.
public class DiscountPricingRule implements PricingRule {

    private double basePrice;
    private double discountPrice;
    private int discountInterval;

    //To check if the discount price is greater than the normal price
    public DiscountPricingRule(double basePrice, double discountPrice, int discountInterval) {
        if (discountPrice > basePrice) {
            throw new IllegalArgumentException("discount price " + discountPrice
                    + " must not be greater than the base price " + basePrice);
        }

        this.basePrice = basePrice;
        this.discountPrice = discountPrice;
        this.discountInterval = discountInterval;
    }

    //To calculate the price of the items after applying the discount
    @Override
    public double calculatePrice(int itemCount) {
        int discountItemCount = itemCount / discountInterval;
        return discountItemCount * discountPrice +  (itemCount - discountItemCount) * basePrice;
    }
}
