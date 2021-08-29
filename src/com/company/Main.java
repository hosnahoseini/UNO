package com.company;

import java.util.Scanner;

/**
 * Main class to execute game
 *
 * @author hosna
 * @version 2021.4
 */
public class Main {

    public static void main(String[] args) {
        int numberOfPlayers;
        char opponentType;

        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter number of players: (3,4,5)");
            numberOfPlayers = scanner.nextInt();
            System.out.println("Do you want to play with human or bot? (h/b)");
            opponentType = scanner.next().charAt(0);
            if (numberOfPlayers < 6 && numberOfPlayers > 2 && (opponentType == 'h' || opponentType == 'b'))
                break;
            System.out.println("Invalid");
        }

        Game game = new Game(numberOfPlayers, opponentType);
        game.play();

    }
}
