package com.Card;

public class CardGUI {
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_RESET = "\u001B[0m";

    public static void cardTest(){
        System.out.println(ANSI_BLUE_BACKGROUND + "Hello, World!" + ANSI_RESET);

        String TWO = "2";
        System.out.printf(
                """
                        ╭─────────╮
                        │%s        │
                        │         │
                        │    %s    │
                        │         │
                        │        %s│
                        ╰─────────╯
                        """, TWO, TWO, TWO);

        String SIX = "6";
        String SIX_LINE = "̲6";
        String SIX_DOWN = "9";
        System.out.printf(
                """
                        ╭─────────╮
                        │%s        │
                        │         │
                        │    %s    │
                        │         │
                        │        %s│
                        ╰─────────╯
                        """, SIX, SIX_LINE, SIX_DOWN);

        String NINE = "9";
        String NINE_LINE = "̲9"; // has a "combining low line" or "non-spacing underscore"
        String NINE_DOWN = "6";
        System.out.printf(
                """
                        ╭─────────╮
                        │%s        │
                        │         │
                        │    %s    │
                        │         │
                        │        %s│
                        ╰─────────╯
                        """, NINE, NINE_LINE, NINE_DOWN);

        String SKIP = " ⃠"; // uses a thin space
        System.out.printf(
                """
                        ╭─────────╮
                        │%s        │
                        │         │
                        │    %s     │
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
                        │    %s    │
                        │         │
                        │       %s│
                        ╰─────────╯
                        """, REVERSE, REVERSE, REVERSE);
    }
}
