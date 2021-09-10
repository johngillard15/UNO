package com.UNO;

import com.Card.CardGUI;
import com.utilities.ANSI;
import com.utilities.CLI;
import com.Card.Card;
import com.Card.Deck;
import com.Game.Game;
import com.Player.Player;
import com.utilities.InputValidator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.utilities.ANSI.getCode;

/**
 * <h1>UNO</h1>
 *
 * <p>My UNO card game</p>
 *
 * <br>
 *
 * @since 13/8/2021
 * @author John Gillard
 * @version 1.1.1
 */

// TODO: use updated Input class
// TODO: use index in comparator

public class UNO extends Game {
    private static final int MIN_PLAYERS = 2;
    private static final int STARTING_CARDS = 7;
    protected Deck deck = new Deck();
    private final List<Player> ranking = new ArrayList<>();
    private int currentPlayer = 0;
    private Card currentCard;
    private String newColor = "";

    public UNO(){
        setup(MIN_PLAYERS);
    }

    @Override
    protected void setup(int MIN_PLAYERS, int MAX_PLAYERS) {
        super.setup(MIN_PLAYERS, MAX_PLAYERS);

        createUNOCardDeck();

        determinePlayerOrder();

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
        if(currentCard.suit.equals("WILD")){
            do{
                discard(currentCard);
                currentCard = deck.deal();
            }while(!currentCard.suit.equals("WILD"));
        }

        newColor = currentCard.suit;
    }

    private void determinePlayerOrder(){
        List<Card> startingOrder = new ArrayList<>();
        for(Player player : players){
            Card card = deck.deal();
            startingOrder.add(card);
            player.hand.addCard(card);
        }

        System.out.println("\nLet's draw to see who goes first.\n");

        for(Player player : players){
            System.out.printf("%s's card:\n", player.name);
            CardGUI.showCard(player.hand.cards.get(0));
            CLI.pause();
        }

        startingOrder.sort(new Card.SortByValue().reversed());

        ranking.addAll(players);
        for(Player player : players){
            Card card = player.hand.cards.get(0);
            int newIndex = startingOrder.indexOf(card);

            ranking.set(newIndex, player);
        }

        players.clear();
        players.addAll(ranking);
        ranking.clear();

        System.out.println("Here's the play order:");
        int rank = 0;
        for(Player player : players)
            System.out.printf("\t%d. %s\n", ++rank, player.name);
        CLI.pause();
    }

    private void dealStartingHands(){
        for(Player player : players){
            while(player.hand.cards.size() < STARTING_CARDS)
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
        do{
            turn(players.get(currentPlayer));
            deck.shuffle();

            if(players.size() == 1)
                playerFinished(players.get(0));
            else
                checkCurrentCard();
        }while(players.size() > 1);
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
            if(currentCard.suit.equals("WILD")) {
                String ANSI_COLOR = getCode(newColor);
                System.out.printf("New Color: " + ANSI_COLOR + "%s" + ANSI.RESET + "\n", newColor);
            }

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

        int choice = getChoice(3);
        return switch (choice) {
            case 1 -> {
                playCard(activePlayer);
                yield false;
            }
            case 2 -> {
                CLI.cls();
                System.out.println("\nSorting by Color...");
                activePlayer.hand.sortBySuit();
                yield true;
            }
            case 3 -> {
                CLI.cls();
                System.out.println("\nSorting by Value...");
                activePlayer.hand.sortByValue();
                yield true;
            }
            default -> false;
        };
    }

    private boolean playableHand(Player activePlayer){
        for(Card card : activePlayer.hand.cards){
            if(playableCard(card))
                return true;
        }

        return false;
    }

    private boolean playableCard(Card card){
        return card.suit.equals("WILD") || card.suit.equals(newColor) || card.value.equals(currentCard.value);
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
                String ANSI_COLOR = (card.suit.equals("WILD") ? ANSI.BLACK : getCode(card.suit)) + card.suit + ANSI.RESET;
                System.out.printf("%d. %s %s\n", ++listNum, ANSI_COLOR, card.value);
                playableCards.add(card);
            }
        }

        int choice = getChoice(listNum);

        discard(currentCard);

        Card newCard = currentCard = playableCards.get(choice - 1);
        activePlayer.hand.cards.remove(newCard);

        if(currentCard.suit.equals("WILD"))
            wildCardPlayed();
        else
            newColor = currentCard.suit;
    }

    private void wildCardPlayed(){
        // TODO: make show hand method and call here and in turn

        System.out.println("\nWhat will the new color be?");

        int listNum = 0;
        for(String color : UNOCard.COLORS){
            if(color.equals("WILD")) break;

            String ANSI_COLOR = getCode(color) + color + ANSI.RESET;
            System.out.printf("%d. %s\n", ++listNum, ANSI_COLOR);
        }

        int choice = getChoice(listNum);

        newColor = switch(choice){
            case 1 -> "RED";
            case 2 -> "GREEN";
            case 3 -> "BLUE";
            case 4 -> "YELLOW";
            default -> throw new IllegalStateException("Unexpected color value: " + choice);
        };
    }

    private int getChoice(int max){
        String input;

        boolean validChoice;
        do{
            System.out.print("choice: ");
            input = scan.nextLine();

            validChoice = InputValidator.validateInt(input) && Integer.parseInt(input) >= 1 && Integer.parseInt(input) <= max;
        }while(!validChoice);

        return Integer.parseInt(input);
    }

    private void checkCurrentCard(){
        int nextPlayer = (currentPlayer + 1) % players.size();

        switch(currentCard.value){
            case "SKIP" -> {
                System.out.println("\nSKIP!");
                System.out.printf("%s misses their turn!\n", players.get(nextPlayer).name);

                if(players.size() > 2)
                    currentPlayer = ++nextPlayer % players.size();

                CLI.pause();
            }
            case "REVERSE" -> {
                System.out.println("\nREVERSE!");
                System.out.printf("%s has reversed the player order!\n", players.get(currentPlayer).name);

                if(players.size() == 2){
                    System.out.printf("Since there are only 2 players left, %s will just be skipped!\n",
                            players.get(nextPlayer).name);
                }
                else{
                    Player thisPlayer = players.get(currentPlayer);
                    Collections.reverse(players);
                    currentPlayer = players.indexOf(thisPlayer);

                    currentPlayer = ++currentPlayer % players.size();
                }

                CLI.pause();
            }
            case "DRAW_TWO" -> {
                System.out.println("\nDRAW TWO!");
                System.out.printf("%s has to draw 2 cards!\n", players.get(nextPlayer).name);

                for(int i = 0; i < 2; i++)
                    draw(players.get(nextPlayer));

                currentPlayer = ++nextPlayer % players.size();

                CLI.pause();
            }
            case "DRAW_FOUR" -> {
                System.out.println("\nDRAW FOUR!");
                System.out.printf("%s has to draw 4 cards!\n", players.get(nextPlayer).name);

                for(int i = 0; i < 4; i++)
                    draw(players.get(nextPlayer));

                currentPlayer = ++nextPlayer % players.size();

                CLI.pause();
            }

            default -> currentPlayer = ++currentPlayer % players.size();
        }
    }

    private void checkUNO(Player activePlayer){
        int cardsLeft = activePlayer.hand.cards.size() ;

        if(cardsLeft == 1){
            System.out.printf("\nUNO!\n\n" +
                    "Look out! %s has 1 card left!\n", activePlayer.name);
        }
        else if(cardsLeft == 0){
            System.out.printf("\n%s has used up all their cards! Only %d player%s left!\n", activePlayer.name,
                    players.size() - 1, players.size() - 1 == 1 ? "" : "'s");
            playerFinished(activePlayer);
        }

        CLI.pause();
    }

    private void playerFinished(Player player){
        players.remove(player);
        ranking.add(player);
    }

    protected void displayResults(){
        System.out.println("\nThat's it for this game! here are the results:");

        int rank = 0;
        for(Player player : ranking)
            System.out.printf("\t%d. %s\n", ++rank, player.name);

        System.out.printf("\nCongratulations to %s for winning the game! You are an UNO master!!!!1111!!1!!!\n",
                ranking.get(0).name);
    }
}
