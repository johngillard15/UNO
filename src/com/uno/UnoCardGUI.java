package com.uno;

import com.utilities.ANSI;

public class UnoCardGUI {

    public static String getColorCard(String color, String value){
        String ANSI_COLOR = ANSI.getCode(color);
        String formattedCard =
                ANSI_COLOR + "╭─────────╮\n" + ANSI.RESET +
                ANSI_COLOR + "│%s│\n" + ANSI.RESET +
                ANSI_COLOR + "│         │\n" + ANSI.RESET +
                ANSI_COLOR + "│%5s│\n" + ANSI.RESET +
                ANSI_COLOR + "│         │\n" + ANSI.RESET +
                ANSI_COLOR + "│%9s│\n" + ANSI.RESET +
                ANSI_COLOR + "╰─────────╯" + ANSI.RESET;

        String face = value;
        String top;
        String mid;
        String bot;

        switch(value){
            case "SKIP" -> {
                face = " ⃠ ";// ⃠
                top = String.format("%-10s", face);
                mid = String.format("  %6s%3s ", face, "");
                bot = String.format("%10s", face);
            }
            case "REVERSE" -> {
                face = "\uD83D\uDD01";//🔁
                top = String.format("%-9s ", face);
                mid = String.format("     %s%3s  ", face, "");
                bot = String.format("%9s ", face);
            }
            case "DRAW_TWO" -> {
                face = "+2";
                top = String.format("%-9s", face);
                mid = String.format("%5s%2s  ", "▮▮", "");
                bot = String.format("%9s", face);
            }
            default -> {
                if(value.equals("6") || value.equals("9")){
                    face = "̲" + face;
                    top = String.format("%-10s", face);
                    mid = String.format("%6s%4s", face, "");
                    bot = String.format("%10s", face);
                }
                else {
                    top = String.format("%-9s", face);
                    mid = String.format("%5s%4s", face, "");
                    bot = String.format("%9s", face);
                }
            }
        }

        String cardFace = String.format(formattedCard,
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
