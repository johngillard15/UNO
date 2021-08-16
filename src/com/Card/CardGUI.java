package com.Card;

import com.Utilities.ANSI;

import java.util.ArrayList;
import java.util.List;

public class CardGUI {
    public static List<String> cards = new ArrayList<>();

    public CardGUI(){

    }

    public static void showCard(Card card){

    }

    public static void showHand(List<Card> hand){

    }

    public static void cardTest(){
        System.out.println(ANSI.YELLOW_BG + ANSI.BLACK + "Hello, World!" + ANSI.RESET);

        String ONE = "1";
        System.out.printf(
                ANSI.RED + """
                        ╭─────────╮
                        │%s        │
                        │         │
                        │    %s    │
                        │         │
                        │        %s│
                        ╰─────────╯
                        """ + ANSI.RESET, ONE, ONE, ONE);

        String TWO = "2";
        System.out.printf(
                ANSI.BLUE + """
                        ╭─────────╮
                        │%s        │
                        │         │
                        │    %s    │
                        │         │
                        │        %s│
                        ╰─────────╯
                        """ + ANSI.RESET, TWO, TWO, TWO);

        String SIX = "6";
        String SIX_LINE = "̲6";
        String SIX_TURNED = "9";
        System.out.printf(
                ANSI.GREEN + """
                        ╭─────────╮
                        │%s        │
                        │         │
                        │    %s    │
                        │         │
                        │        %s│
                        ╰─────────╯
                        """ + ANSI.RESET, SIX, SIX_LINE, SIX_TURNED);

        String NINE = "9";
        String NINE_LINE = "̲9"; // has a "combining low line" or "non-spacing underscore"
        String NINE_TURNED = "6";
        System.out.printf(
                ANSI.YELLOW + """
                        ╭─────────╮
                        │%s        │
                        │         │
                        │    %s    │
                        │         │
                        │        %s│
                        ╰─────────╯
                        """ + ANSI.RESET, NINE, NINE_LINE, NINE_TURNED);

        String SKIP = " ⃠"; // uses a thin space
        System.out.printf(
                """
                        ╭─────────╮
                        │%s        │
                        │         │
                        │    %s      │
                        │         │
                        │       %s │
                        ╰─────────╯
                        """, SKIP, SKIP, SKIP);

        String REVERSE = "\uD83D\uDD01 ";
        System.out.printf(
                """
                        ╭─────────╮
                        │%s       │
                        │         │
                        │    %s     │
                        │         │
                        │       %s│
                        ╰─────────╯
                        """, REVERSE, REVERSE, REVERSE);

        String DRAW_FOUR = ANSI.WHITE + "+4" + ANSI.RESET + ANSI.BLACK; // uses hair space
        System.out.printf(
                ANSI.BLACK + """
                        ╭─────────╮
                        │%s       │
                        │         │
                        │    %s     │
                        │         │
                        │       %s│
                        ╰─────────╯
                        """ + ANSI.RESET, DRAW_FOUR, DRAW_FOUR, DRAW_FOUR);

        String D4_TOP = ANSI.BLUE + "▮ " + ANSI.RESET + ANSI.BLACK;
        String D4_LEFT = ANSI.RED + "▮" + ANSI.RESET + ANSI.BLACK;
        String D4_RIGHT = ANSI.GREEN + "▮  " + ANSI.RESET + ANSI.BLACK;
        String D4_BOT = ANSI.YELLOW + "▮ " + ANSI.RESET + ANSI.BLACK;
        System.out.printf(
                ANSI.BLACK + """
                        ╭─────────╮
                        │%s       │
                        │    %s   │
                        │   %s%s  │
                        │    %s   │
                        │       %s│
                        ╰─────────╯
                        """ + ANSI.RESET, DRAW_FOUR, D4_TOP, D4_LEFT, D4_RIGHT, D4_BOT, DRAW_FOUR);

        String CC_TOP = ANSI.BLUE + "▮ " + ANSI.RESET + ANSI.BLACK;
        String CC_LEFT = ANSI.RED + "▮" + ANSI.RESET + ANSI.BLACK;
        String CC_RIGHT = ANSI.GREEN + "▮  " + ANSI.RESET + ANSI.BLACK;
        String CC_BOT = ANSI.YELLOW + "▮ " + ANSI.RESET + ANSI.BLACK;
        System.out.printf(
                ANSI.BLACK + """
                        ╭─────────╮
                        │         │
                        │    %s   │
                        │   %s%s  │
                        │    %s   │
                        │         │
                        ╰─────────╯
                        """ + ANSI.RESET, CC_TOP, CC_LEFT, CC_RIGHT, CC_BOT);

        String DRAW_TWO = "+2";
        String D2_LEFT = "▮";
        String D2_RIGHT = "▮  ";
        System.out.printf(
                ANSI.RED + """
                        ╭─────────╮
                        │%s       │
                        │         │
                        │   %s%s  │
                        │         │
                        │       %s│
                        ╰─────────╯
                        """ + ANSI.RESET, DRAW_TWO, D2_LEFT, D2_RIGHT, DRAW_TWO);
    }
}
