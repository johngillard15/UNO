package com.player;

import com.card.Card;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    public List<Card> cards = new ArrayList<>();

    public Hand(){

    }

    public void addCard(String suit, String value){
        cards.add(Card.createCard(suit, value));
    }

    public void addCard(Card card){
        cards.add(card);
    }

    public void removeCard(){
        cards.remove(cards.size() - 1);
    }

    public void removeCard(int index){
        cards.remove(index);
    }

    public void removeCard(Card card){
        cards.remove(card);
    }

    public void sortBySuit(){
        cards.sort(new Card.SortBySuit());
    }

    public void sortByValue(){
        cards.sort(new Card.SortByValue());
    }
}
