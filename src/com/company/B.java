package com.company;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * A class for card B
 *
 * @author hosna
 * @version 2021.4
 */
public class B extends Card implements Actionable {
    /**
     * Constructor
     *
     * @param color color of card
     */
    public B(String color) {
        super(color);
        setScore(12);
        setNum("B");
    }

    /**
     * force the next move color to bea specific one
     *
     * @param player player who use this card
     * @param game   the game the player is playing
     */
    @Override
    public void move(Player player, Game game) {
        String[] colors = new String[]{"blue", "red", "black", "green"};
        if (player instanceof Human) {
            while (true) {
                System.out.println("what color you want to be played?(blue, red, black, green)");
                Scanner scanner = new Scanner(System.in);
                String color = scanner.next();
                if(color .equals(colors[0]) || color .equals(colors[1])
                || color .equals(colors[2]) || color .equals(colors[3])) {
                    game.setForcedColor(color);
                    break;
                }
                System.out.println("invalid color");
            }
        } else {
            Random random = new Random();
            game.setForcedColor(colors[random.nextInt(3)]);
        }
        System.out.println("The next color should be: " + game.getForcedColor());

    }
}
