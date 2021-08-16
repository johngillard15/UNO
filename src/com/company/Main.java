package com.company;

import com.Card.CardGUI;
import com.Card.UnoCardGUI;
import com.UNO.UNO;

public class Main {
    public static void main(String[] args) {
//        UNO uno = new UNO();
//        uno.play();

//        CardGUI.cardTest();
        System.out.println(UnoCardGUI.getWildCard("DRAW_FOUR"));
        System.out.println(UnoCardGUI.getWildCard("COLOR_CHANGE"));
        System.out.println(UnoCardGUI.getColorCard("RED", "6"));
        System.out.println(UnoCardGUI.getColorCard("BLUE", "8"));
    }
}
