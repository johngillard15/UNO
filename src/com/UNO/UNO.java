package com.UNO;

import com.Card.CardGUI;
import com.Card.UnoCardGUI;
import com.Utilities.ANSI;
import com.Utilities.CLI;
import com.Card.Card;
import com.Card.Deck;
import com.Game.Game;
import com.Player.Player;
import com.Utilities.InputValidator;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>com.UNO</h1>
 *
 * <p>My com.UNO card game</p>
 *
 * <br>
 *
 * @since 13/8/2021
 * @author John Gillard
 * @version 15/8/2021
 */

// TODO: handle skips, reverse, draws, wilds
// TODO: check for wild cards in playable method
// TODO: implement wild card features
// TODO: do more shuffles

public class UNO extends Game {
    protected Deck deck = new Deck();
    private final int STARTING_CARDS = 7;
    private final int MIN_PLAYERS = 2;
    List<Player> ranking = new ArrayList<>();
    private Card currentCard;

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

        currentCard = deck.deal();
    }

    private void dealStartingHands(){
        for(Player player : players){
            for(int i = 0; i < STARTING_CARDS; i++)
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

        do{
            round();
        }while(players.size() > 1);

        displayResults();
    }

    protected void round(){
        for(Player player : players){
            turn(player);

            if(players.size() == 1) break;

            checkCurrentCard();
        }
    }

    protected void turn(Player activePlayer){
        String name = activePlayer.name;

        CLI.cls();
        System.out.printf("- [P%d] %s%s turn -\n",
                players.indexOf(activePlayer) + 1, name, name.charAt(name.length() - 1) == 's' ? "'" : "'s");
        CLI.pause();

        boolean turnActive = true;
        do{
            System.out.println("\n- Current Card -");
            CardGUI.showCard(currentCard);

            System.out.println("- Your hand -");
            CardGUI.showHand(activePlayer.hand.cards);

            if(!playableHand(activePlayer)) {
                drawUntilPlayable(activePlayer);
                continue;
            }

            turnActive = playerOptions(activePlayer);
        }while(turnActive);

        checkUNO(activePlayer);
    }

    private boolean playerOptions(Player activePlayer){
        System.out.println("\nOPTIONS:");
        System.out.println("1. Play card");
        System.out.println("2. Sort hand by " + ANSI.BLUE + "Color" + ANSI.RESET);
        System.out.println("3. Sort hand by " + ANSI.PURPLE + "Value" + ANSI.RESET);

        String input;
        do{
            System.out.print("choice: ");
            input = scan.nextLine();
        }while(!InputValidator.validateInt(input));

        int choice = Integer.parseInt(input);
        switch (choice) {
            case 1 -> playCard(activePlayer);
            case 2 -> {
                activePlayer.hand.sortBySuit();
                return true;
            }
            case 3 -> {
                activePlayer.hand.sortByValue();
                return true;
            }
        }

        return false;
    }

    private boolean playableHand(Player activePlayer){
        for(Card card : activePlayer.hand.cards){
            if(playableCard(card))
                return true;
        }

        return false;
    }

    private boolean playableCard(Card card){
        return card.suit.equals(currentCard.suit) || card.value.equals(currentCard.value);
    }

    private void drawUntilPlayable(Player activePlayer){
        List<Card> theseCards = activePlayer.hand.cards;

        System.out.println("\nYour current hand cannot match the current card.\n" +
                "You need to draw until you get a playable card.");

        do{
            System.out.println("\nDrawing card...");
            CLI.pause();

            draw(activePlayer);

            System.out.println("New card:");
            Card newCard = theseCards.get(theseCards.size() - 1);
            CardGUI.showCard(newCard);

            if(playableCard(newCard)){
                System.out.println("Nice! Now back to the game.");
                CLI.pause();
            }
            else
                System.out.println("Nope. Try again.");
        }while(!playableHand(activePlayer));
    }

    private void playCard(Player activePlayer){
        List<Card> playableCards = new ArrayList<>();

        System.out.println("\nWhich card would you like to play?");

        int listNum = 0;
        for(Card card : activePlayer.hand.cards){
            if(playableCard(card)){
                String ANSI_COLOR = UnoCardGUI.getAnsiCode(card.suit);
                System.out.printf("%d. " + ANSI_COLOR + "%s" + ANSI.RESET + " %s\n", ++listNum, card.suit, card.value);
                playableCards.add(card);
            }
        }

        String input;
        do{
            System.out.print("choice: ");
            input = scan.nextLine();
        }while(!InputValidator.validateInt(input));

        int choice = Integer.parseInt(input) - 1;

        discard(currentCard);

        Card newCard = currentCard = playableCards.get(choice);
        activePlayer.hand.cards.remove(newCard);
    }

    private void checkCurrentCard(){
        if(currentCard.value.equals("SKIP")){
            System.out.println("SKIP!");
            CLI.pause();
        }
        else if(currentCard.value.equals("REVERSE")){
            System.out.println("REVERSE!");
            CLI.pause();
        }
        else if(currentCard.value.equals("DRAW_TWO")){
            System.out.println("DRAW TWO!");
            CLI.pause();
        }
        else if(currentCard.value.equals("DRAW_FOUR")){
            System.out.println("DRAW FOUR!");
            CLI.pause();
        }
    }

    private void checkUNO(Player activePlayer){
        int cardsLeft = activePlayer.hand.cards.size() ;

        if(cardsLeft == 1){
            System.out.printf("\nUNO!\n\n" +
                    "Look out! %s has 1 card left!\n", activePlayer.name);
            CLI.pause();
        }
        else if(cardsLeft == 0){
            System.out.printf("%s has used up all their cards! Only %d player%s left!\n", activePlayer.name,
                    players.size() - 1, players.size() == 1 ? "" : "'s");
            playerFinished(activePlayer);
        }
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

        System.out.printf("\nCongratulations to %s for winning the game! You are an com.UNO master!!!!1111!!1!!!\n",
                ranking.get(0));
    }
}
