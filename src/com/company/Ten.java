package com.company;

/**
 * A class for card 10
 *
 * @author hosna
 * @version 2021.4
 */
public class Ten extends Card implements Actionable {
    /**
     * Constructor
     *
     * @param color set color
     */
    public Ten(String color) {
        super(color);
        setScore(10);
        setNum("10");
    }

    /**
     * change the direction of play
     *
     * @param player player who use this card
     * @param game   the game the player is playing
     */
    @Override
    public void move(Player player, Game game) {
        game.changeDirection();
        System.out.println("Direction changed");
    }
}
