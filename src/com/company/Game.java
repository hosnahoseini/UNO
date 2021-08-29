package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

/**
 * A class for game
 *
 * @author hosna
 * @version 2021.4
 */
public class Game {

    private Bank bank;
    private Card playCard;              //card which is on ground:)
    private int numberOfPlayer;
    private ArrayList<Player> players;
    private int turn;
    private String direction = "clockwise";
    private String forcedColor = "";    //color forced to be played next turn
    private int punishment7 = 0;        //number of cards as punishment for 7s

    /**
     * constructor
     *
     * @param numberOfPlayer number of players
     * @param opponentType   typr of players (bot / human)
     */
    public Game(int numberOfPlayer, char opponentType) {
        this.numberOfPlayer = numberOfPlayer;
        bank = new Bank();
        players = new ArrayList<>();
        initializePlayers(numberOfPlayer, opponentType);
        playCard = bank.getRandomNormalCard();
        turn = 0;
    }

    /**
     * get name and card set to players
     *
     * @param numberOfPlayer number of players
     * @param opponentType   typr of players (bot / human)
     */
    public void initializePlayers(int numberOfPlayer, char opponentType) {
        Scanner scanner = new Scanner(System.in);
        if (opponentType == 'b') {
            System.out.println("Enter your name: ");
            addPlayer(new Human(scanner.next(), bank.getCardSet()));
            for (int i = 1; i < numberOfPlayer; i++)
                addPlayer(new Bot("bot" + i, bank.getCardSet()));
        } else {
            for (int i = 0; i < numberOfPlayer; i++) {
                while (true) {
                    System.out.println("Enter your name: ");
                    Human human = new Human(scanner.next(), bank.getCardSet());
                    if(!players.contains(human)) {
                        addPlayer(human);
                        break;
                    }else
                        System.out.println("Repeated name! Try another name");
                }
            }
        }
    }

    /**
     * wait
     *
     * @param ms time to wait(milli seconds)
     */
    public static void wait(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * play game
     */
    public void play() {
        playCard = bank.getRandomNormalCard();      //first card

        while (gameEnded() == null) {
            Player player = players.get(turn);      //player who want to play
            printInitInfo(player);
            System.out.println("-------------------------STATUS--------------------------");

            Card card = playIfPossible(player);
            if (card == null)
                card = punishment(player);

            playCard = card != null ? card : playCard;

            if (card != null && card instanceof Actionable) {
                System.out.print("» ");
                ((Actionable) card).move(player, this);
                wait(1500);
            }

            turn = (turn + 1) % numberOfPlayer;
            System.out.println("Next turn");
            System.out.println("--------------------------------------------------------");
            wait(2000);
        }

        printScores();
    }

    /**
     * print initial info of each turn
     *
     * @param player player who wants to play this turn
     */
    public void printInitInfo(Player player) {
        System.out.println("╔═══════════════════════════╦══════════════════════════╗");
        System.out.printf("║ turn: %-9s\t\t\t║ direction: %-14s║\n", player.getName(), direction);
        System.out.println("╚═══════════════════════════╩══════════════════════════╝");

        System.out.print("Number of cards for each player:   ");
        for (Player playerCounter : players) {
            System.out.print(playerCounter.getName() + " : " + playerCounter.getCards().size() + " | ");
        }

        if (forcedColor != "")
            System.out.print(Color.colorToAscii(forcedColor) + "\nNext card should be " + forcedColor + Color.RESET);

        System.out.println("\nPlay Card is:");
        playCard.print();
    }

    /**
     * playb a turn if the player have any possible move
     *
     * @param player player who wants to play
     * @return card which has been played or null if no card has been played
     */
    public Card playIfPossible(Player player) {
        Card card = null;

        if (player instanceof Human)
            player.showCards();

        if (playCard.getNum().equals("7")) {

            //when the play card is seven and the punishment hasn't payed yet
            if (punishment7 != 0 && player.haveSeven())
                card = player.putSevenCard(playCard);

            //when player card is 7 and the punishment has been payed before
            else if (playCard.getNum().equals("7")
                    && punishment7 == 0
                    && player.havePossibleCard(playCard))
                card = player.putCard(playCard);

        } else {

            //no especial case just put a proper card
            if (forcedColor.equals("") && player.havePossibleCard(playCard))
                card = player.putCard(playCard);

            //when the play card is B and the player should put a card with special color
            else if ((!forcedColor.equals("")) && player.haveForcedColorCard(forcedColor)) {
                card = player.putForcedColorCard(forcedColor);
                forcedColor = "";
            }

        }

        if (card != null) {
            System.out.println(player.getName() + " put card -> " + card);
            bank.addCard(card);
            card.print();
        } else
            System.out.println("Did not put any card");

        return card;
    }

    /**
     * punishment for the chance in which player couldn't put card
     *
     * @param player player who has played
     * @return if player got a second chance and a new card return new card
     */
    public Card punishment(Player player) {
        Card card = null;
        //normal punishment for normal cards
        if (punishment7 == 0) {
            System.out.println(player.getName() + " doesn't have suitable card so he/she gets another card, maybe it helps");
            Card newCard = bank.getRandomCard();
            wait(1000);
            player.addCard(newCard);
            System.out.println(player.getName() + " has another chance with new card ( " + newCard + " )");
            card = secondChance(player);
        }

        //punishment for 7
        else {
            System.out.println(player.getName() + " doesn't have seven so he/she has punished for " + punishment7 + " cards");
            for (int i = 0; i < punishment7; i++)
                player.addCard(bank.getRandomCard());
            punishment7 = 0;
            wait(1000);
        }
        wait(1000);
        return card;
    }

    /**
     * second chance which is given to player if he/she has been punished
     *
     * @param player player who is playing
     * @return card which has been played or null if no card has been played
     */
    public Card secondChance(Player player) {
        Card card = null;
        card = playIfPossible(player);
        wait(1000);
        if (card == null) {
            System.out.println("It can't help:(");
            wait(1000);
        }
        return card;
    }

    /**
     * print score and winner at end of game
     */
    public void printScores() {
        System.out.println("------------------------THE END-------------------------");
        System.out.println("Winner is: " + gameEnded().getName());
        System.out.println("------scores------");
        System.out.printf("%-10s | %5s\n", "name", "score");
        sortPlayer();
        for (Player player : players)
            System.out.printf("%-10s : %5d\n", player.getName(), player.getScore());

    }

    /**
     * sort player by score
     */
    public void sortPlayer() {
        boolean sorted = false;
        Player temp;
        while (!sorted) {
            sorted = true;
            for (int i = 0; i < players.size() - 1; i++) {
                if (players.get(i).getScore() > players.get(i + 1).getScore()) {
                    temp = players.get(i);
                    players.set(i, players.get(i + 1));
                    players.set(i + 1, temp);
                    sorted = false;
                }
            }
        }
    }

    /**
     * get a random player
     *
     * @return random player
     */
    public Player getRandomPlayer() {
        Random random = new Random();
        Player player = players.get(random.nextInt(players.size()));
        return player;
    }

    /**
     * show all players
     */
    public void showPlayers() {
        int index = 1;
        for (Player player : players)
            System.out.println(index++ + ")" + player.getName());
    }

    /**
     * check if the game ended or not
     *
     * @return true if the game ended
     */
    public Player gameEnded() {
        for (Player player : players)
            if (player.calculateScore() == 0)
                return player;
        return null;
    }

    /**
     * chenge direction of playing
     */
    public void changeDirection() {
        direction = direction.equals("clockwise") ? "anticlockwise" : "clockwise";
        //reverse order of players and adjust turn
        turn = numberOfPlayer - turn - 1;
        Collections.reverse(players);
    }

    /**
     * add a player to game
     *
     * @param player new player
     */
    public void addPlayer(Player player) {
        players.add(player);
    }

    /**
     * set turn
     *
     * @param turn turn
     */
    public void setTurn(int turn) {
        this.turn = turn;
    }

    /**
     * get turn
     *
     * @return turn
     */
    public int getTurn() {
        return turn;
    }

    /**
     * get number of cards as punishment for 7s
     *
     * @return number of cards as punishment for 7s
     */
    public int getPunishment7() {
        return punishment7;
    }

    /**
     * set number of cards as punishment for 7s
     *
     * @param punishment7 number of cards as punishment for 7s
     */
    public void setPunishment7(int punishment7) {
        this.punishment7 = punishment7;
    }

    /**
     * set a color as forced color for next turn
     *
     * @param forcedColor color as forced color for next turn
     */
    public void setForcedColor(String forcedColor) {
        this.forcedColor = forcedColor;
    }

    /**
     * get color as forced color for next turn
     *
     * @return color as forced color for next turn
     */
    public String getForcedColor() {
        return forcedColor;
    }

    /**
     * get all the players
     *
     * @return players
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }
}
