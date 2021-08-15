package com.company;

import java.util.Comparator;

public class Card {
    protected String suit;
    protected String value;

    public Card(String suit, String value){
        this.suit = suit;
        this.value = value;
    }

    public static Card createCard(String suit, String value){
        return new Card(suit, value);
    }

    protected static class SortBySuit implements Comparator<Card> {
        @Override
        public int compare(Card cardA, Card cardB){
            return cardA.suit.compareTo(cardB.suit);
        }

//        @Override
//        public Comparator<Card> reversed(){
//            return Comparator.super.reversed();
//        }
    }
    protected static class SortByValue implements Comparator<Card> {
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