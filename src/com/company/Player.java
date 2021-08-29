package com.company;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

/**
 * An abstract class for player
 *
 * @author hosna
 * @version 2021.4
 */
public abstract class Player {
    private String name;
    private int score;
    private ArrayList<Card> cards;

    /**
     * Constructor
     *
     * @param name  name of player
     * @param cards list of card the player have at first
     */
    public Player(String name, ArrayList<Card> cards) {
        this.name = name;
        this.cards = cards;
    }

    /**
     * add a card
     *
     * @param card new card
     */
    public void addCard(Card card) {
        cards.add(card);
        score += card.getScore();
    }

    /**
     * remove a card
     *
     * @param card a card to be removed
     */
    public void removeCard(Card card) {
        cards.remove(card);
        score -= card.getScore();
    }

    /**
     * calculate score of player
     *
     * @return total score
     */
    public int calculateScore() {
        score = 0;
        for (Card card : cards)
            score += card.getScore();
        return score;
    }

    /**
     * get a random card from player's card
     *
     * @return random card
     */
    public Card getRandomCard() {
        Card card = null;
        Random random = new Random();
        card = cards.get(random.nextInt(cards.size()));
        removeCard(card);
        return card;
    }

    /**
     * check if the player has possible card to play regarding to previous card has being played
     *
     * @param previous previous card has being played
     * @return true if player has any possible card to play
     */
    public boolean havePossibleCard(Card previous) {
        for (Card card : cards)
            if (card.isCompatible(previous) || card.getNum().equals("B"))
                return true;
        return false;
    }

    /**
     * check if the player has card with forced color card
     *
     * @param color forced color(which has happened because of B)
     * @return true if there is a card with same color to forced color
     */
    public boolean haveForcedColorCard(String color) {
        for (Card card : cards)
            if (card.getColor().equals(color) || card.getNum().equals("B"))
                return true;
        return false;
    }

    /**
     * check if the player have 7
     *
     * @return true if there is a 7 card
     */
    public boolean haveSeven() {
        for (Card card : cards)
            if (card.getNum().equals("7"))
                return true;
        return false;
    }

    /**
     * put a suitable card regarding to previous card
     *
     * @param previous previous card has been played
     * @return the card which has been put
     */
    public abstract Card putCard(Card previous);

    /**
     * put a 7 card
     *
     * @param previous previous card has been played
     * @return the card which has been put
     */
    public abstract Card putSevenCard(Card previous);

    /**
     * put a card with color same to forced color
     *
     * @param color forced color
     * @return the card which has been put
     */
    public abstract Card putForcedColorCard(String color);

    /**
     * check if the input index for card id valid or not
     *
     * @param index input index
     * @return true if is valid
     */
    public boolean validIndex(int index) {
        if (index > 0 && index <= cards.size())
            return true;
        return false;
    }

    /**
     * show all cards of player graphically
     */
    public void showCards() {

        System.out.println("Your cards:");
        for (Card card : cards)
            System.out.print(Color.colorToAscii(card.getColor()) + "┌────────" + Color.RESET);
        System.out.print(Color.colorToAscii(cards.get(cards.size() - 1).getColor()) + "─┐" + Color.RESET);
        System.out.println();
        for (Card card : cards) {
            System.out.print(Color.colorToAscii(card.getColor()));
            System.out.printf("│  %2s    ", card.getNum());
            System.out.print(Color.RESET);
        }
        System.out.print(Color.colorToAscii(cards.get(cards.size() - 1).getColor()) + " │" + Color.RESET);
        System.out.println();

        for (int i = 0; i < 3; i++) {
            for (Card card : cards)
                System.out.print(Color.colorToAscii(card.getColor()) + "│        " + Color.RESET);
            System.out.print(Color.colorToAscii(cards.get(cards.size() - 1).getColor()) + " │" + Color.RESET);
            System.out.println();
        }
        for (Card card : cards)
            System.out.print(Color.colorToAscii(card.getColor()) + "└────────" + Color.RESET);
        System.out.print(Color.colorToAscii(cards.get(cards.size() - 1).getColor()) + "─┘" + Color.RESET);
        System.out.println();
        for (int i = 1; i <= cards.size(); i++)
            System.out.print("    " + i + "    ");
        System.out.println();
    }

    /**
     * get all cards of player
     *
     * @return cards of player
     */
    public ArrayList<Card> getCards() {
        return cards;
    }

    /**
     * get player name
     *
     * @return player name
     */
    public String getName() {
        return name;
    }

    /**
     * get player score
     *
     * @return player score
     */
    public int getScore() {
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

}
