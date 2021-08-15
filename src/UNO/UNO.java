package UNO;

import Utilities.CLI;
import com.company.Card;
import com.company.Deck;
import Game.Game;
import com.company.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>UNO</h1>
 *
 * <p>My UNO card game</p>
 *
 * <br>
 *
 * @since 13/8/2021
 * @author John Gillard
 * @version 15/8/2021
 */

public class UNO extends Game {
    protected Deck deck = new Deck();
    private final int STARTING_CARDS = 7;
    private final int MIN_PLAYERS = 2;
    List<Player> ranking = new ArrayList<>();

    public UNO(){
        setup(MIN_PLAYERS);
    }

    @Override
    protected void setup(int MIN_PLAYERS, int MAX_PLAYERS) {
        super.setup(MIN_PLAYERS, MAX_PLAYERS);
        createUNOCardDeck();
        dealStartingHands();
    }

    private void createUNOCardDeck(){
        // create 2 cards of each value for each color, excluding zero and wild cards
        for(int i = 0; i < 2; i++){
            for(String color : UNOCard.COLORS){
                if(color.equals("WILD")) break;
                for(String value : UNOCard.VALUES){
                    if (value.equals("0") || value.equals("COLOR_CHANGE") || value.equals("DRAW_FOUR")) continue;
                    deck.pile.add(new UNOCard(color, value));
                }
            }
        }
        // create 1 zero card for each color
        for(String color : UNOCard.COLORS){
            if(color.equals("WILD")) break;
            deck.pile.add(new UNOCard(color, "0"));
        }
        // create 4 of each wild card
        for(int i = 0; i < 4; i++){
            deck.pile.add(new UNOCard("WILD", "COLOR_CHANGE"));
            deck.pile.add(new UNOCard("WILD", "DRAW_FOUR"));
        }

        deck.shuffle();
    }

    private void dealStartingHands(){
        for(Player player : players){
            for(int i = 0; i < 7; i++)
                draw(player);
        }
    }

    private void draw(Player activePlayer){
        activePlayer.hand.addCard(deck.deal());
    }

    private void discard(Card card){
        deck.discard(card);
    }

    public void play(){
        System.out.println("\n--- Welcome to UNO! Have fun! ---");
        CLI.pause();

        for(Player player : players){
            System.out.println(player.name);
            System.out.println(player.hand.cards);
            System.out.println("Sorted by Suit:");
            player.hand.sortBySuit();
            System.out.println(player.hand.cards);
            System.out.println("Sorted by Value:");
            player.hand.sortByValue();
            System.out.println(player.hand.cards);
        }

//        do{
//            round();
//        }while(players.size() > 1);
//        displayResults();
    }

    protected void round(){

    }

    protected void turn(Player activePlayer){

    }

    private void playerFinished(Player player){
        players.remove(player);
        ranking.add(player);
    }

    protected void displayResults(){
        System.out.println("That's it for this game! here are the results:");

        int rank = 0;
        for(Player player : ranking)
            System.out.printf("\t%d. %s\n", ++rank, player.name);

        System.out.printf("\nCongratulations to %s for winning the game! You are an UNO master!!!!1111!!1!!!\n",
                ranking.get(0));
    }
}
