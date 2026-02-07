package com.coffeeshop.smart_queue.model;

public enum DrinkType {

    COLD_BREW("Cold Brew", 1, 120),
    ESPRESSO("Espresso", 2, 150),
    AMERICANO("Americano", 2, 140),
    CAPPUCCINO("Cappuccino", 4, 180),
    LATTE("Latte", 4, 200),
    SPECIALTY("Specialty (Mocha)", 6, 250);

    private final String displayName;
    private final int prepTimeInMinutes;
    private final int price;

    DrinkType(String displayName, int prepTimeInMinutes, int price) {
        this.displayName = displayName;
        this.prepTimeInMinutes = prepTimeInMinutes;
        this.price = price;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getPrepTimeInMinutes() {
        return prepTimeInMinutes*1 ;
    }

    public int getPrice() {
        return price;
    }
}
