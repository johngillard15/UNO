package com.company;

import java.util.Scanner;

public abstract class Game {
    private static final Scanner scan = new Scanner(System.in);

    public Game(){
        int numPlayers = getPlayerCount();
    }

    public static int getPlayerCount(){
        int numPlayers;
        boolean validNumber = false;
        do{
            System.out.print("\nHow many players will there be? ");
            numPlayers = Integer.parseInt(scan.nextLine());
            validNumber = numPlayers >= 1;

            if(!validNumber)
                System.out.println("You need to have at least 1 player. Please try again with a valid number.");
        }while(!validNumber);

        return numPlayers;
    }

    public static int getPlayerCount(final int MAX){
        final int MIN = 1;
        int numPlayers;
        boolean validNumber = false;
        do{
            System.out.print("\nHow many players will there be? ");
            numPlayers = Integer.parseInt(scan.nextLine());
            validNumber = numPlayers >= MIN && numPlayers <= MAX;

            if(!validNumber){
                System.out.printf("You cannot have %d player%s.\n", numPlayers, numPlayers != 1 ? "s" : "");
                System.out.printf("Please pick a number between %d and %d.\n", MIN, MAX);
            }
        }while(!validNumber);

        return numPlayers;
    }

    public static int getPlayerCount(final int MIN, final int MAX){
        int numPlayers;
        boolean validNumber = false;
        do{
            System.out.print("\nHow many players will there be? ");
            numPlayers = Integer.parseInt(scan.nextLine());
            validNumber = numPlayers >= MIN && numPlayers <= MAX;

            if(!validNumber){
                System.out.printf("You cannot have %d player%s.\n", numPlayers, numPlayers != 1 ? "s" : "");
                System.out.printf("Please pick a number between %d and %d.\n", MIN, MAX);
            }
        }while(!validNumber);

        return numPlayers;
    }
}
