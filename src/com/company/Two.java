package com.company;

import java.util.Scanner;

/**
 * A class for card 2
 *
 * @author hosna
 * @version 2021.4
 */
public class Two extends Card implements Actionable{
    /**
     * Constructor
     *
     * @param color set color
     */
    public Two(String color) {
        super(color);
        setScore(2);
        setNum("2");
    }

    /**
     * the player can give one random card to a player
     *
     * @param player player who use this card
     * @param game   the game the player is playing
     */
    @Override
    public void move(Player player, Game game) {
        Card card = player.getRandomCard();
        if(card != null) {
            Player destPlayer = null;
            Scanner scanner = new Scanner(System.in);

            if (player instanceof Bot)
                destPlayer = game.getRandomPlayer();
            else {
                while (true) {
                    System.out.println("You can give one of your card to a player: ");
                    game.showPlayers();
                    int index = scanner.nextInt() - 1;

                    if (game.getPlayers().get(index).equals(player)
                            || index < 0
                            || index > game.getPlayers().size() - 1)
                        System.out.println("Invalid");
                    else {
                        destPlayer = game.getPlayers().get(index);
                        break;
                    }
                }
            }

            System.out.println(player.getName() + " get card (" + card + ") to player " + destPlayer.getName());
            player.removeCard(card);
            destPlayer.addCard(card);
        }
    }
}
