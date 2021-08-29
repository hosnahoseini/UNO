package com.company;

/**
 * A class for card 7
 *
 * @author hosna
 * @version 2021.4
 */
public class Seven extends Card implements Actionable{
    /**
     * Constructor
     *
     * @param color set color
     */
    public Seven(String color) {
        super(color);
        setScore(10);
        setNum("7");
    }

    /**
     * punish next player for 2 card if he/she doesn't have 7
     *
     * @param player player who use this card
     * @param game   the game the player is playing
     */
    @Override
    public void move(Player player, Game game) {
        game.setPunishment7(game.getPunishment7() + 2);
        System.out.println("Punishment increased to: " + game.getPunishment7());
    }
}
