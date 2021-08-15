package com.company;

public class CardGUI {
    public static void cardTest(){
        final String ANSI_RED_BACKGROUND = "\u001B[41m";
        final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
        final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
        final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
        final String ANSI_RESET = "\u001B[0m";

        System.out.println(ANSI_BLUE_BACKGROUND + "Hello, World!" + ANSI_RESET);

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
