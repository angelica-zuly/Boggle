package core;

import java.util.ArrayList;
import java.util.Random;

public class Die implements IDie {

    private ArrayList<String> sideOfDice = new ArrayList<String>(NUMBER_OF_SIDES);
    private Random rand;
    /* DEBUGGING!:varable 'number' puts index on each die */
    private int number;

    public Die(int i) {
        rand = new Random(System.currentTimeMillis());
        //number = i;
    }

    public void addLetter(String letter) {
        sideOfDice.add(letter);
    }

    public void displayLetters() {
        for (String letter : sideOfDice) {
            System.out.print(letter + " ");
        }
    }

    public String rollDie() {
        /* randomly select 1 of the 6 letters to use as game data */  
        int pick = rand.nextInt(NUMBER_OF_SIDES);

        /* gets letter based on ArrayList index */
        String letter = sideOfDice.get(pick);

        //return number + ":" + letter;
        return letter;
    }

}
