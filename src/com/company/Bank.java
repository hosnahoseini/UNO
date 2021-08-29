package com.company;

import java.util.ArrayList;
import java.util.Random;

/**
 * A class for bank of cards
 *
 * @author hosna
 * @version 2021.4
 */
public class Bank {
    ArrayList<Card> cards;

    /**
     * Constructor
     * add cards to bank
     */
    public Bank() {
        cards = new ArrayList<>();

        String[] colors = new String[]{"blue", "red", "black", "green"};
        for (String color : colors) {

            cards.add(new Card("3", color, 3));
            cards.add(new Card("4", color, 4));
            cards.add(new Card("5", color, 5));
            cards.add(new Card("6", color, 6));
            cards.add(new Card("9", color, 9));
            cards.add(new Card("C", color, 12));
            cards.add(new Card("D", color, 13));
            cards.add(new Two(color));
            cards.add(new Eight(color));
            cards.add(new Ten(color));
            cards.add(new A(color));
            cards.add(new B(color));
        }

        cards.add(new Seven("blue"));
        cards.add(new Seven("red"));
        cards.add(new Seven("green"));
        cards.add(new BlackSeven("black"));

    }

    /**
     * remove a card from bank
     *
     * @param card card to br removed
     */
    public void removeCard(Card card) {
        cards.remove(card);
    }

    /**
     * get a random card from bank
     *
     * @return random card
     */
    public Card getRandomCard() {
        Random random = new Random();
        Card card = cards.get(random.nextInt(cards.size()));
        removeCard(card);
        return card;
    }

    /**
     * gat a random card which doesn't have move
     *
     * @return card which has no action
     */
    public Card getRandomNormalCard() {
        Random random = new Random();
        while (true) {
            Card card = cards.get(random.nextInt(cards.size()));
            if (!(card instanceof Actionable)) {
                removeCard(card);
                return card;
            }
        }
    }

    /**
     * get set of card contain 7 random cards
     *
     * @return set of random card
     */
    public ArrayList<Card> getCardSet() {
        ArrayList<Card> cardSet = new ArrayList<>();
        for (int i = 0; i < 7; i++)
            cardSet.add(getRandomCard());
        return cardSet;
    }

    /**
     * add a new card to bank
     * @param card new card
     */
    public void addCard(Card card){
        cards.add(card);
    }
}
