package com.company;

/**
 * A class for card balck seven
 *
 * @author hosna
 * @version 2021.4
 */

public class BlackSeven extends Card implements Actionable{

    /**
     * Constructor
     *
     * @param color color of card
     */
    public BlackSeven(String color) {
        super(color);
        setScore(10);
        setNum("7");
    }

    /**
     * punish next player for 4 card if he/she doesn't have 7
     *
     * @param player player who use this card
     * @param game   the game the player is playing
     */
    @Override
    public void move(Player player, Game game) {
        game.setPunishment7(game.getPunishment7() + 4);
        System.out.println("Punishment increased to: " + game.getPunishment7());
    }
}
