package com.company;

public class Player {
    public String name;
    public Hand hand;

    public Player(String name){
        this.name = name;
        hand = new Hand();
    }

    public static Player addPlayer(String name){
        return new Player(name);
    }
}
