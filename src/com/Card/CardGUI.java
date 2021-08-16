package com.Card;

import com.UNO.UNOCard;
import com.Utilities.ANSI;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CardGUI {
    public static List<String> cards = new ArrayList<>();

    public CardGUI(){

    }

    public static void showCard(Card card){

    }

    public static void showHand(List<Card> hand){
        List<Scanner> scannerList = new ArrayList<>();
        for(Card card : hand){
            if(card.suit.equals("WILD"))
                scannerList.add(new Scanner(UnoCardGUI.getWildCard(card.suit)));
            else
                scannerList.add(new Scanner(UnoCardGUI.getColorCard(card.suit, card.value)));
        }

        while(scannerList.get(0).hasNextLine()){
            StringBuilder line = new StringBuilder();
            for(Scanner scanner : scannerList)
                line.append(scanner.nextLine()).append(" ");
            System.out.printf("%s\n", line);
        }

        for(Scanner scanner : scannerList)
            scanner.close();
    }

    public static void cardTest(){
        System.out.println(ANSI.YELLOW_BG + ANSI.BLACK + "Hello, World!" + ANSI.RESET);

        String VALUE = "+2";
        String TOP_CORRECTOR = "";
        String MIDDLE = "▮";
        String MID_CORRECTOR = "▮  ";
        String BOT_CORRECTOR = "";
        System.out.printf(
                ANSI.BLUE + """
                        ╭─────────╮
                        │%s%s       │
                        │         │
                        │   %s%s  │
                        │         │
                        │       %s%s│
                        ╰─────────╯
                        """ + ANSI.RESET, VALUE, TOP_CORRECTOR, MIDDLE, MID_CORRECTOR, BOT_CORRECTOR, VALUE);

        // 🔁
        VALUE = "\uD83D\uDD01" + " ";
        TOP_CORRECTOR = "";
        MIDDLE = "   " + VALUE;
        MID_CORRECTOR = " ";
        BOT_CORRECTOR = "";
        System.out.printf(
                ANSI.BLUE + """
                        ╭─────────╮
                        │%s%s       │
                        │         │
                        │   %s%s  │
                        │         │
                        │       %s%s│
                        ╰─────────╯
                        """ + ANSI.RESET, VALUE, TOP_CORRECTOR, MIDDLE, MID_CORRECTOR, BOT_CORRECTOR, VALUE);

        // ⃠
        VALUE = " ⃠ ";
        TOP_CORRECTOR = "";
        MIDDLE = "  " + VALUE;
        MID_CORRECTOR = "  ";
        BOT_CORRECTOR = "";
        System.out.printf(
                ANSI.BLUE + """
                        ╭─────────╮
                        │%s%s       │
                        │         │
                        │   %s%s  │
                        │         │
                        │       %s%s│
                        ╰─────────╯
                        """ + ANSI.RESET, VALUE, TOP_CORRECTOR, MIDDLE, MID_CORRECTOR, BOT_CORRECTOR, VALUE);

        VALUE = "9";
        TOP_CORRECTOR = " ";
        MIDDLE =  " " + VALUE;
        MID_CORRECTOR = "  ";
        BOT_CORRECTOR = " ";
        System.out.printf(
                ANSI.BLUE + """
                        ╭─────────╮
                        │%s%s       │
                        │         │
                        │   %s%s  │
                        │         │
                        │       %s%s│
                        ╰─────────╯
                        """ + ANSI.RESET, VALUE, TOP_CORRECTOR, MIDDLE, MID_CORRECTOR, BOT_CORRECTOR, VALUE);
    }
}
