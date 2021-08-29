package com.company;

/**
 * An interface for cards which have a special move
 *
 * @author hosna
 * @version 2021.4
 */
interface Actionable{

    /**
     * An abstract method to do action of one card
     *
     * @param player player who use this card
     * @param game   the game the player is playing
     */
    public abstract void move(Player player, Game game);

}
