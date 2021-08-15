package Game;

import Utilities.InputValidator;
import com.company.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * <p>Provides a template for a text based console game.</p>
 *
 * @since 13/8/2021
 * @author John
 * @version 15/8/2021
 */

public abstract class Game {
    protected static final Scanner scan = new Scanner(System.in);
    protected List<Player> players = new ArrayList<>();
    protected static final int MIN_PLAYERS = 1;
    protected static final int MAX_PLAYERS = -1;

    protected void setup(){
        setup(MIN_PLAYERS, MAX_PLAYERS);
    }
    protected void setup(int MIN){
        setup(MIN, MAX_PLAYERS);
    }
    protected void setup(int MIN_PLAYERS, int MAX_PLAYERS){
        addPlayers(getPlayerCount(MIN_PLAYERS, MAX_PLAYERS));
    }

    protected void addPlayers(int numPlayers){
        do{
            System.out.printf("\nPlayer %d, what is your name? ", players.size() + 1);
            String name = scan.nextLine().trim();

            players.add(Player.addPlayer(name));

            System.out.printf("Hello, %s.\n", name);
        }while(players.size() < numPlayers);
    }

    protected static int getPlayerCount(final int MIN, final int MAX){
        int numPlayers;
        boolean validNumber;
        do{
            String input;
            do{
                System.out.print("\nHow many players will there be? ");
                input = scan.nextLine();

                if(InputValidator.validateInt(input)) break;

                System.out.println("That is not a valid number value. Please try again.");
            }while(true);

            numPlayers = Integer.parseInt(input);

            if(MAX == -1)
                validNumber = MIN >= 1 && numPlayers >= MIN;
            else
                validNumber = MIN >= 1 && numPlayers >= MIN && numPlayers <= MAX;

            if(!validNumber){
                System.out.printf("You cannot have %d player%s.\n", numPlayers, numPlayers != 1 ? "s" : "");
                if(MAX == -1)
                    System.out.printf("You need to have at least %d player%s.\n",
                            MIN, MIN == 1 ? "" : "s");
                else
                    System.out.printf("Please pick a number between %d and %d.\n", MIN, MAX);
            }
        }while(!validNumber);

        return numPlayers;
    }

    public abstract void play();
    protected abstract void round();
    protected abstract void turn(Player activePlayer);
    protected abstract void displayResults();
}
