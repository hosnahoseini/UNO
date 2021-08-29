package com.company;

/**
 * A class for card A
 *
 * @author hosna
 * @version 2021.4
 */
public class A extends Card implements Actionable {
    /**
     * Constructor
     *
     * @param color set color
     */
    public A(String color) {
        super(color);
        setScore(11);
        setNum("A");
    }

    /**
     * jump form next player (turn + 1)
     *
     * @param player player who use this card
     * @param game   the game the player is playing
     */
    @Override
    public void move(Player player, Game game) {
        game.setTurn(game.getTurn() + 1);
        System.out.println("Jumped from next player!");
    }
}
