package com.epam.storozhuk.command;

public enum CommandActions {
    GET_PRODUCTS_LIST("1", "Get products list"),
    ADD_NEW_PRODUCT("2", "Add new product"),
    REMOVE_PRODUCT("3", "Remove product"),
    ADD_PRODUCT_TO_BASKET("4", "Add product to the ordering basket"),
    GET_PRODUCTS_FROM_BASKET("5", "Get elements from the basket"),
    MAKE_ORDER("6", "Make an order"),
    BUY_BASKET("7", "Buy products from basket"),
    GET_LAST_FIVE_BASKET_PRODUCTS("8", "Get last five products added to basket"),
    GET_ORDERS("9", "Get list of made orders"),
    GET_ORDERS_IN_RANGE("10", "Get orders in specified date range"),
    GET_ORDERS_OF_DATE_EARLIER_THAN("11",
            "Get order nearest to the input date"),
    RANDOM_REFLECTION_INPUT("12", "Create random product via reflection"),
    USER_REFLECTION_INPUT("13", "Create product via user's reflection input"),
    EXIT_PROGRAM("0", "Exit the program");

    private String commandNumber;
    private String commandDescription;

    CommandActions(String commandNumber, String commandDescription) {
        this.commandNumber = commandNumber;
        this.commandDescription = commandDescription;
    }

    public String toString() {
        return String.format("%s - %s", this.commandNumber, this.commandDescription);
    }
}
