package com.main.checkout;

//Pricing rule interface to act as base for rules classes
public interface PricingRule {

    double calculatePrice(int itemCount);
}
