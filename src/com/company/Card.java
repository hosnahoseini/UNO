package com.company;

/**
 * A class to store a card
 *
 * @author hosna
 * @version 2021.4
 */

public class Card {

    private String num;
    private String color;
    private int score;

    /**
     * Constructor
     *
     * @param color color of card(num and score will add in subclasses)
     */
    public Card(String color) {
        this.color = color;
    }

    /**
     * Constructor
     * @param num num or sign of card
     * @param color color
     * @param score score
     */
    public Card(String num, String color, int score) {
        this.num = num;
        this.color = color;
        this.score = score;
    }

    /**
     * check if this card is compatible with previous one(color or number is same?)
     *
     * @param previous the card we want to check if tis card is compatible with
     * @return true if these two card have same color or number or both
     */
    public boolean isCompatible(Card previous) {

        if (color.equals(previous.getColor()) ||
                num.equals(previous.getNum()))
            return true;

        return false;
    }

    /**
     * print a card graphically
     */
    public void print() {
        System.out.println(Color.colorToAscii(color) + "┌─────────┐" + Color.RESET);
        System.out.print(Color.colorToAscii(color));
        System.out.printf("│  %2s     │", num);
        System.out.print(Color.RESET + "\n");
        for (int i = 0; i < 3; i++)
            System.out.println(Color.colorToAscii(color) + "│         │" + Color.RESET);
        System.out.println(Color.colorToAscii(color) + "└─────────┘" + Color.RESET);

    }

    /**
     * info of card
     *
     * @return String which contains info of card
     */
    @Override
    public String toString() {
        return "num: " + num +
                ", color: " + color;
    }

    /**
     * get score of card
     *
     * @return score
     */
    public int getScore() {
        return score;
    }

    /**
     * get number (2-10 or A,B,C,D) of card
     *
     * @return number of card
     */
    public String getNum() {
        return num;
    }

    /**
     * get color of card
     *
     * @return color of card
     */
    public String getColor() {
        return color;
    }

    /**
     * set number of card
     *
     * @param num number of card
     */
    public void setNum(String num) {
        this.num = num;
    }

    /**
     * set card score
     *
     * @param score card score
     */
    public void setScore(int score) {
        this.score = score;
    }


}
    