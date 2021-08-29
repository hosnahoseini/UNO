package com.company;

/**
 * A class for card 8
 *
 * @author hosna
 * @version 2021.4
 */

public class Eight extends Card implements Actionable {
    /**
     * Constructor
     *
     * @param color set color
     */
    public Eight(String color) {
        super(color);
        setScore(8);
        setNum("8");
    }

    /**
     * give another chance for playing to player
     *
     * @param player player who use this card
     * @param game   the game the player is playing
     */
    @Override
    public void move(Player player, Game game) {
        game.setTurn(game.getTurn() - 1);
        System.out.println(player.getName() + " can play again");
    }
}
