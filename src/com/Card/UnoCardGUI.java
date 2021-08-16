package com.Card;

import com.Utilities.ANSI;

public class UnoCardGUI {
    public static String getAnsiCode(String color){
        return switch(color){
            case "WHITE" -> ANSI.WHITE;
            case "RED" -> ANSI.RED;
            case "BLUE" -> ANSI.BLUE;
            case "GREEN" -> ANSI.GREEN;
            case "YELLOW" -> ANSI.YELLOW;
            default -> ANSI.BLACK;
        };
    }
    public static String getColorCard(String color, String value){
        String ANSI_COLOR = getAnsiCode(color);

        String corner = value;
        if(value.equals("6") || value.equals("9"))
            value = "̲" + value;

        String cardFace = String.format(
                ANSI_COLOR + """
                        ╭─────────╮
                        │%s        │
                        │         │
                        │    %s    │
                        │         │
                        │        %s│
                        ╰─────────╯
                        """ + ANSI.RESET, corner, value, corner);

        return cardFace;
    }

    public static String getWildCard(String value){
        String CORNER = ANSI.WHITE + (value.equals("DRAW_FOUR") ? "+4" : "  ") + ANSI.RESET + ANSI.BLACK;
        String D4_TOP = ANSI.BLUE + "▮ " + ANSI.RESET + ANSI.BLACK;
        String D4_LEFT = ANSI.RED + "▮" + ANSI.RESET + ANSI.BLACK;
        String D4_RIGHT = ANSI.GREEN + "▮  " + ANSI.RESET + ANSI.BLACK;
        String D4_BOT = ANSI.YELLOW + "▮ " + ANSI.RESET + ANSI.BLACK;

        String WILD_CARD = String.format(
                ANSI.BLACK + """
                        ╭─────────╮
                        │%s       │
                        │    %s   │
                        │   %s%s  │
                        │    %s   │
                        │       %s│
                        ╰─────────╯
                        """ + ANSI.RESET, CORNER, D4_TOP, D4_LEFT, D4_RIGHT, D4_BOT, CORNER);

        return WILD_CARD;
    }
}
