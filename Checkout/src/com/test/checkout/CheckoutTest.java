package com.test.checkout;

import org.junit.Before;
import org.junit.Test;

import com.main.checkout.Checkout;
import com.main.checkout.ConstantPricingRule;
import com.main.checkout.DiscountPricingRule;
import com.main.checkout.PriceCalculator;

import static org.junit.Assert.*;

public class CheckoutTest {

    private Checkout checkout;

    @Before
    public void setUp() throws Exception {
        PriceCalculator priceCalculator = new PriceCalculator();
        priceCalculator.registerPricingRule("A", new DiscountPricingRule(50, 30, 3));
        priceCalculator.registerPricingRule("B", new DiscountPricingRule(30, 15, 2));
        priceCalculator.registerPricingRule("C", new ConstantPricingRule(20));
        priceCalculator.registerPricingRule("D", new ConstantPricingRule(15));

        checkout = new Checkout(priceCalculator);
    }

  
    @Test
    public void shouldCalculateTotalItemsPrice() throws Exception {
        assertEquals(0, price(""), 0);
        assertEquals(50, price("A"), 0);
        assertEquals(80, price("AB"), 0);
        assertEquals(115, price("CDBA"), 0);
        assertEquals(160, price("AAAB"), 0);
        assertEquals(175, price("AAABB"), 0);
        assertEquals(190, price("AAABBD"), 0);
        assertEquals(190, price("DABABA"), 0);
    }

    @Test
    public void shouldCalculateTotalItemsPriceIncrementally() throws Exception {
        assertEquals(0, price(""), 0);
        checkout.scan("A");
        assertEquals(50, checkout.calculateTotalItemsPrice(), 0);
        checkout.scan("B");
        assertEquals(80, checkout.calculateTotalItemsPrice(), 0);
        checkout.scan("A");
        assertEquals(130, checkout.calculateTotalItemsPrice(), 0);
        checkout.scan("A");
        assertEquals(160, checkout.calculateTotalItemsPrice(), 0);
        checkout.scan("B");
        assertEquals(175, checkout.calculateTotalItemsPrice(), 0);
    }

    private double price(String items) {
        // Loop through the items and scan them
        for (int i = 0; i < items.length(); i++) {
            char item = items.charAt(i);
            checkout.scan("" + item);
        }

        double totalItemsPrice = checkout.calculateTotalItemsPrice();
        checkout.clearAllItems();
        return totalItemsPrice;
    }

}