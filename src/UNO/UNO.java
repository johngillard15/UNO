package UNO;

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
 * @version 13/8/2021
 */

public class UNO extends Game {
    protected Deck deck = new Deck();
    private int STARTING_CARDS = 7;

    public UNO(){
        createUNOCardDeck();
    }

    private void createUNOCardDeck(){ // FIXME: 8/13/2021 lol
        for(String color : UNOCard.COLORS){
            for(String value : UNOCard.VALUES){

            }
        }
    }



    @Override
    public void play(){

    }

    @Override
    protected void round() {

    }

    @Override
    protected void turn(Player activePlayer) {

    }

    @Override
    protected void displayResults() {

    }
}
