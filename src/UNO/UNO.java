package UNO;

import com.company.Deck;
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
 * @version 13/8/2021
 */

public class UNO {
    protected Deck deck = new Deck();
    protected List<Player> players = new ArrayList<>();

    public UNO(){
        createUNOCardDeck();
    }

    private void createUNOCardDeck(){
        for(String color : UNOCard.COLORS){
            for(String value : UNOCard.VALUES){

            }
        }
    }

    public void play(){

    }


}
