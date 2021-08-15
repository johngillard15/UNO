package com.Card;

import java.util.*;

public class Deck {
    //public Deque<Card> pile = new ArrayDeque<>();
    public List<Card> pile = new ArrayList<>();

    public Deck(){

    }

    public void shuffle(){
        Collections.shuffle(pile);
    }

    public Card deal(){
        return pile.remove(0);
    }

    public void discard(Card card){
        pile.add(card);
    }

    @Override
    public String toString() {
        return "Deck{" +
                "pile=" + pile +
                '}';
    }
}
