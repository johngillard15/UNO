package com.UNO;

import com.utilities.ANSI;

public class UnoCardGUI {

    public static String getColorCard(String color, String value){
        String ANSI_COLOR = ANSI.getCode(color);

        String face = value;
        String topCorrector = "";
        String middle;
        String midCorrector;
        String botCorrector = "";

        // each face needs different formatting to fit within the bounds of the card
        switch(value){
            case "SKIP" -> { // ⃠
                face = " ⃠ ";
                middle = "  " + face;
                midCorrector = "  ";
            }
            case "REVERSE" -> { // 🔁
                face = "\uD83D\uDD01" + " ";
                middle = "   " + face;
                midCorrector = " ";
            }
            case "DRAW_TWO" -> {
                face = "+2";
                middle = "▮▮";
                midCorrector = "  ";
            }
            default -> {
                if (value.equals("6") || value.equals("9"))
                    middle = " ̲" + face;
                else
                    middle = " " + face;
                topCorrector = " ";
                midCorrector = "  ";
                botCorrector = " ";
            }
        }

        String top = face + topCorrector;
        String mid = middle + midCorrector;
        String bot = botCorrector + face;

        String cardFace = String.format(
                ANSI_COLOR + "╭─────────╮\n" + ANSI.RESET +
                ANSI_COLOR + "│%s       │\n" + ANSI.RESET +
                ANSI_COLOR + "│         │\n" + ANSI.RESET +
                ANSI_COLOR + "│   %s  │\n" + ANSI.RESET +
                ANSI_COLOR + "│         │\n" + ANSI.RESET +
                ANSI_COLOR + "│       %s│\n" + ANSI.RESET +
                ANSI_COLOR + "╰─────────╯" + ANSI.RESET,
                top, mid, bot);

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
