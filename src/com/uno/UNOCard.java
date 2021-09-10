package com.uno;

import com.card.Card;

import java.util.Arrays;

public class UNOCard extends Card {
    public static final String[] COLORS = {"RED", "YELLOW", "GREEN", "BLUE", "WILD"};
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

    public static class SortBySuit extends Card.SortBySuit {
        @Override
        public int compare(Card cardA, Card cardB){
            return Arrays.asList(COLORS).indexOf(cardA.suit) - Arrays.asList(COLORS).indexOf(cardB.suit);
        }
    }
    public static class SortByValue extends Card.SortByValue {
        @Override
        public int compare(Card cardA, Card cardB){
            return Arrays.asList(VALUES).indexOf(cardA.value) - Arrays.asList(VALUES).indexOf(cardB.value);
        }
    }
}
