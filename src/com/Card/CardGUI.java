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

    public static String getCard(Card card){
        if(card.suit.equals("WILD"))
            return UnoCardGUI.getWildCard(card.suit);
        else
            return UnoCardGUI.getColorCard(card.suit, card.value);

    }

    public static void showHand(List<Card> hand){
        List<Scanner> scannerList = new ArrayList<>();
        for(Card card : hand){
            scannerList.add(new Scanner(getCard(card)));
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
}
