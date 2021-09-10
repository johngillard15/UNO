package com.card;

import java.util.Comparator;

public class Card {
    public String suit;
    public String value;

    public Card(String suit, String value){
        this.suit = suit;
        this.value = value;
    }

    public static Card createCard(String suit, String value){
        return new Card(suit, value);
    }

    public String getCardGUI(){
        return ""; // do something here once i make a standard deck of cards
    }

    public static class SortBySuit implements Comparator<Card> {
        @Override
        public int compare(Card cardA, Card cardB){
            return cardA.suit.compareTo(cardB.suit);
        }
    }
    public static class SortByValue implements Comparator<Card> {
        @Override
        public int compare(Card cardA, Card cardB){
            return cardA.value.compareTo(cardB.value);
        }
    }

    @Override
    public String toString() {
        return "Card{" +
                "suit='" + suit + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}