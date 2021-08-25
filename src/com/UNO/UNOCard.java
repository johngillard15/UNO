package com.UNO;

import com.Card.Card;

public class UNOCard extends Card {
    public static final String[] COLORS = {"RED", "GREEN", "BLUE", "YELLOW", "WILD"};
    public static final String[] VALUES = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
            "SKIP", "REVERSE", "DRAW_TWO", "COLOR_CHANGE", "DRAW_FOUR"};

    public UNOCard(String color, String value) {
        super(color, value);
    }

    @Override
    public String getCardGUI(){
        if(suit.equals("WILD"))
            return UnoCardGUI.getWildCard(value);
        else
            return UnoCardGUI.getColorCard(suit, value);
    }
}
