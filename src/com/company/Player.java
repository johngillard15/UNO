package com.company;

public class Player {
    public String name;
    Hand hand;

    public Player(String name){
        this.name = name;
        hand = new Hand();
    }

    public Player addPlayer(String name){
        return new Player(name);
    }
}
