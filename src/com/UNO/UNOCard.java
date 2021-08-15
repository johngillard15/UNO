package com.UNO;

import com.company.Card;

public class UNOCard extends Card {
    protected static final String[] COLORS = {"RED", "GREEN", "BLUE", "YELLOW", "WILD"};
    protected static final String[] VALUES = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
            "SKIP", "REVERSE", "DRAW_TWO", "COLOR_CHANGE", "DRAW_FOUR"};

    public UNOCard(String color, String value) {
        super(color, value);
    }
}
