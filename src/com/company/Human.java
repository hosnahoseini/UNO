package com.company;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * A class for player which is human
 *
 * @author hosna
 * @version 2021.4
 */

public class Human extends Player {

    Scanner scanner = new Scanner(System.in);

    /**
     * Constructor
     *
     * @param name  name of player
     * @param cards list of card the player have at first
     */
    public Human(String name, ArrayList<Card> cards) {
        super(name, cards);
    }

    /**
     * the player choose a card and then put it if its valid
     *
     * @param previous previous card has been played
     * @return the card which has been put
     */
    @Override
    public Card putCard(Card previous) {

        while (true) {
            System.out.println("Enter index of card you want to play: ");
            int index = scanner.nextInt();
            if (validIndex(index)) {
                Card card = getCards().get(index - 1);
                if (card.isCompatible(previous) || card.getNum().equals("B")) {
                    removeCard(card);
                    return card;
                } else {
                    System.out.println("Invalid Card!, please try again");
                }
            } else
                System.out.println("Invalid index!, please try again");
        }
    }

    /**
     * the player choose a 7 card and then put it if its valid
     *
     * @param previous previous card has been played
     * @return the card which has been put
     */
    @Override
    public Card putSevenCard(Card previous) {

        while (true) {
            System.out.println("Enter index of card you want to play: ");
            int index = scanner.nextInt();
            if (validIndex(index)) {
                Card card = getCards().get(index - 1);
                if (card.getNum().equals("7")) {
                    removeCard(card);
                    return card;
                } else {
                    System.out.println("you should chose a 7 card, please try again");
                }
            } else
            System.out.println("Invalid index!, please try again");
        }

    }

    /**
     * the player choose a card with color same to forced color and put it if it's valid
     *
     * @param color forced color
     * @return the card which has been put
     */
    @Override
    public Card putForcedColorCard(String color) {

        while (true) {
            System.out.println("Enter index of card you want to play: ");
            int index = scanner.nextInt();
            if (validIndex(index)) {
                Card card = getCards().get(index - 1);
                if (card.getColor().equals(color) || card.getNum().equals("B")) {
                    removeCard(card);
                    return card;
                } else {
                    System.out.println("you should chose a " + color + " card, please try again");
                }
            }else
                System.out.println("Invalid index!, please try again");

        }
    }
}