package com.uno;

import com.card.CardGUI;
import com.utilities.ANSI;
import com.utilities.CLI;
import com.card.Card;
import com.card.Deck;
import com.game.Game;
import com.player.Player;
import com.utilities.Input;

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
 * @version 1.2.5
 */

public class UNO extends Game {
    public final int STARTING_CARDS;
    protected final Deck deck;
    private final List<Player> ranking = new ArrayList<>();
    private int currentPlayer = 0;
    private Card currentCard;
    private String newColor = "";

    public UNO(){
        super(2);

        STARTING_CARDS = 7;
        deck = new Deck();

        setup();
    }

    protected void setup() {
        addPlayers();

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
                    if (value.equals("0") || value.equals("COLOR CHANGE") || value.equals("DRAW FOUR")) continue;
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
            deck.pile.add(new UNOCard("WILD", "COLOR CHANGE"));
            deck.pile.add(new UNOCard("WILD", "DRAW FOUR"));
        }

        deck.shuffle();

        currentCard = deck.deal();
        if(currentCard.suit.equals("WILD"))
            newColor = UNOCard.COLORS[(int) (Math.random() * 4)];
        else
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

        startingOrder.sort(new UNOCard.SortByValue().reversed());

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

            if(!playableHand(activePlayer))
                drawUntilPlayable(activePlayer);
            else
                turnActive = playerOptions(activePlayer);
        }while(turnActive);

        checkUNO(activePlayer);
    }

    private boolean playerOptions(Player activePlayer){
        System.out.println("\nOPTIONS:");
        System.out.println("1. Play card");
        System.out.println("2. Sort hand by " + ANSI.BLUE + "Color" + ANSI.RESET);
        System.out.println("3. Sort hand by " + ANSI.PURPLE + "Value" + ANSI.RESET);

        return switch (Input.getInt(1, 3)) {
            case 1 -> {
                playCard(activePlayer);
                yield false;
            }
            case 2 -> {
                CLI.cls();
                System.out.println("\nSorting by Color...");
                activePlayer.hand.cards.sort(new UNOCard.SortBySuit());
                yield true;
            }
            case 3 -> {
                CLI.cls();
                System.out.println("\nSorting by Value...");
                activePlayer.hand.cards.sort(new UNOCard.SortByValue());
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

        System.out.println("\nYour current hand cannot match the current card." +
                "\nYou need to draw until you get a playable card.");

        boolean canUseCard;
        do{
            System.out.println("\nDrawing card...");
            CLI.pause();

            draw(activePlayer);

            System.out.println("New card:");
            Card newCard = theseCards.get(theseCards.size() - 1);
            CardGUI.showCard(newCard);

            canUseCard = playableCard(newCard);
            if(canUseCard){
                System.out.println("Nice! Now back to the game.");
                CLI.pause();
            }
            else
                System.out.println("Nope. Try again.");
        }while(!canUseCard);
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

        int choice = Input.getInt(1, listNum) - 1;

        discard(currentCard);

        Card newCard = currentCard = playableCards.get(choice);
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

        int choice = Input.getInt(1, listNum) - 1;
        newColor = UNOCard.COLORS[choice];
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
            case "DRAW TWO" -> {
                System.out.println("\nDRAW TWO!");
                System.out.printf("%s has to draw 2 cards!\n", players.get(nextPlayer).name);

                for(int i = 0; i < 2; i++)
                    draw(players.get(nextPlayer));

                currentPlayer = ++nextPlayer % players.size();

                CLI.pause();
            }
            case "DRAW FOUR" -> {
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
                    players.size() - 1, players.size() - 1 == 1 ? "" : "s");
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

        final String RADIO = "\uD83D\uDCFB";
        final String COOL_DUDE = "???(??????_???)???";
        System.out.printf("\t%s?????? %s\n", RADIO, COOL_DUDE);
    }
}
