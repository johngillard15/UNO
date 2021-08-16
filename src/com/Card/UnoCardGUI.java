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

        String TOP_CORRECTOR = "";
        String MIDDLE;
        String MID_CORRECTOR;
        String BOT_CORRECTOR = "";

        // each face needs different formatting to fit within the bounds of the card
        switch(value){
            case "SKIP" -> { // ⃠
                value = " ⃠ ";
                MIDDLE = "  " + value;
                MID_CORRECTOR = "  ";
            }
            case "REVERSE" -> { // 🔁
                value = "\uD83D\uDD01" + " ";
                MIDDLE = "   " + value;
                MID_CORRECTOR = " ";
            }
            case "DRAW_TWO" -> {
                value = "+2";
                MIDDLE = "▮";
                MID_CORRECTOR = "▮  ";
            }
            default -> {
                if (value.equals("6") || value.equals("9"))
                    MIDDLE = " ̲" + value;
                else
                    MIDDLE = " " + value;
                TOP_CORRECTOR = " ";
                MID_CORRECTOR = "  ";
                BOT_CORRECTOR = " ";
            }
        }

        String cardFace = String.format(
                ANSI_COLOR + "╭─────────╮\n" + ANSI.RESET +
                ANSI_COLOR + "│%s%s       │\n" + ANSI.RESET +
                ANSI_COLOR + "│         │\n" + ANSI.RESET +
                ANSI_COLOR + "│   %s%s  │\n" + ANSI.RESET +
                ANSI_COLOR + "│         │\n" + ANSI.RESET +
                ANSI_COLOR + "│       %s%s│\n" + ANSI.RESET +
                ANSI_COLOR + "╰─────────╯" + ANSI.RESET,
                value, TOP_CORRECTOR, MIDDLE, MID_CORRECTOR, BOT_CORRECTOR, value);

        return cardFace;
    }

    public static String getWildCard(String value){
        String CORNER = ANSI.WHITE + (value.equals("DRAW_FOUR") ? "+4" : "  ") + ANSI.RESET + ANSI.BLACK;
        String D4_TOP = ANSI.BLUE + "▮ " + ANSI.RESET + ANSI.BLACK;
        String D4_LEFT = ANSI.RED + "▮" + ANSI.RESET + ANSI.BLACK;
        String D4_RIGHT = ANSI.GREEN + "▮  " + ANSI.RESET + ANSI.BLACK;
        String D4_BOT = ANSI.YELLOW + "▮ " + ANSI.RESET + ANSI.BLACK;

        String WILD_CARD = String.format(
                ANSI.BLACK + "╭─────────╮\n" + ANSI.RESET +
                ANSI.BLACK + "│%s       │\n" + ANSI.RESET +
                ANSI.BLACK + "│    %s   │\n" + ANSI.RESET +
                ANSI.BLACK + "│   %s%s  │\n" + ANSI.RESET +
                ANSI.BLACK + "│    %s   │\n" + ANSI.RESET +
                ANSI.BLACK + "│       %s│\n" + ANSI.RESET +
                ANSI.BLACK + "╰─────────╯" + ANSI.RESET,
                CORNER, D4_TOP, D4_LEFT, D4_RIGHT, D4_BOT, CORNER);

        return WILD_CARD;
    }
}
