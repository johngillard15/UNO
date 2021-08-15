package com.company;

import java.util.Comparator;

public class Card {
    protected String suit;
    protected int value;

    public Card(String suit, int value){
        this.suit = suit;
        this.value = value;
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
            return cardA.value - cardB.value;
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