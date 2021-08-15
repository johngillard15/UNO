package UNO;

import com.company.Card;
import com.company.Deck;
import Game.Game;
import com.company.Player;

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

    public UNO(){
        setup(MIN_PLAYERS);
    }

    @Override
    protected void setup(int MIN_PLAYERS, int MAX_PLAYERS) {
        super.setup(MIN_PLAYERS, MAX_PLAYERS);
        createUNOCardDeck();
    }

    private void createUNOCardDeck(){ // FIXME: 8/13/2021 lol
        // create 2 cards of a value for each color, excluding zero and wild cards
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

        System.out.println("deck.pile.size() : " + deck.pile.size());
    }

    public void draw(){

    }

    public void play(){
        System.out.println(deck);
    }

    protected void round() {

    }

    protected void turn(Player activePlayer) {

    }

    protected void displayResults() {

    }
}
