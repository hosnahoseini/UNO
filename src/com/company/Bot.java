package com.company;

import java.util.ArrayList;

/**
 * A class for player which is bot
 *
 * @author hosna
 * @version 2021.4
 */
public class Bot extends Player {

    /**
     * Constructor
     *
     * @param name  name of player
     * @param cards list of card the player have at first
     */
    public Bot(String name, ArrayList<Card> cards) {
        super(name, cards);
    }

    /**
     * the bot choose a random valid card and then put it
     * trying to put actionable card if its possible
     *
     * @param previous previous card has been played
     * @return the card which has been put
     */
    @Override
    public Card putCard(Card previous) {
        for (Card card : getCards())
            if (card.isCompatible(previous) && card instanceof Actionable) {
                removeCard(card);
                return card;
            }

        for (Card card : getCards())
            if (card.isCompatible(previous)) {
                removeCard(card);
                return card;
            }
        return null;
    }

    /**
     * the bot choose a 7 card and then put it
     * trying to put actionable card if its possible
     *
     * @param previous previous card has been played
     * @return the card which has been put
     */
    @Override
    public Card putSevenCard(Card previous) {
        for (Card card : getCards())
            if (card.getNum().equals("7") && card instanceof Actionable)
                return card;

        for (Card card : getCards())
            if (card.getNum().equals("7")) {
                removeCard(card);
                return card;
            }

        return null;
    }

    /**
     * the bot choose a card with color same to forced color and put it if it's valid
     * trying to put actionable card if its possible
     * 
     * @param color forced color
     * @return the card which has been put
     */
    @Override
    public Card putForcedColorCard(String color) {
        for (Card card : getCards())
            if ((card.getColor().equals(color) || card.getNum().equals("B")) && card instanceof Actionable)
                return card;

        for (Card card : getCards())
            if (card.getColor().equals(color))
                return card;

        return null;
    }


}
