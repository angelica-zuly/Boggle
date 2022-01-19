package core;

import static core.IDie.NUMBER_OF_SIDES;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author angel
 */
public class Board implements IBoard {

    private ArrayList<String> data; //stores dice data
    private ArrayList<String> dictionary; //stores dictonary data
    private ArrayList<Die> gameDice; //stores 16 instances game die
    private ArrayList<String> gameData; //stores the game data
    private Random rand;

    /**
     * Custom constructor to Board
     * @param data
     * @param dictionary
     */
    public Board(ArrayList<String> data, ArrayList<String> dictionary) {
        this.data = data;
        this.dictionary = dictionary;

        gameDice = new ArrayList<Die>(IBoard.NUMBER_OF_DICE);
        gameData = new ArrayList<String>();
        /* Delcaring new random object setting time (in milisecs) as the seed */
        rand = new Random(System.currentTimeMillis());
    }

    /**
     *
     */
    public void populateDice() {
        /*loop through 16 dice*/
        for (int i = 0; i < NUMBER_OF_DICE; i++) {
            /*adding 6 letters to the Die ArrayLists*/
            String dieString = data.get(i);
            String[] sides = dieString.split(" ");

            Die die = new Die(i);

            /*loop through 6 sides of the die*/
            for (int j = 0; j < NUMBER_OF_SIDES; j++) {
                die.addLetter(sides[j]);
            }

            /*
            // Alternative for loop
            for (String side : sides) {
                die.addLetter(side);
            }
             */
            //Add each die to the ArrayList<Die>
            gameDice.add(die);

            System.out.print("Die " + i + ": ");
            /*Displaying the letters of each die on separate row*/
            die.displayLetters();
            System.out.println();
        }
    }

    /**
     * Method that returns the list of strings of dice letters.
     * @return List of strings of dice letters
     */
    public ArrayList<String> shakeDice() {

        //removing previously added gameData
        gameData.clear();

        ArrayList<Die> usedDice = new ArrayList<Die>();
        Die die;
        /*DEBUGGING!: variable 'count' shows # of attempts to find unused dice*/
        int count = 0;

        // loop through 16 dice
        for (int i = 0; i < NUMBER_OF_DICE; i++) {

            do {
                // Randomly select 1 of the 16 dice WHILE die
                // are still in the usedDice ArrayList
                int pick = rand.nextInt(NUMBER_OF_DICE);
                die = gameDice.get(pick);
                //count++;

            } while (usedDice.contains(die));

            // Keeps track of used dice by ading to ArrayList
            usedDice.add(die);
            // rollDie() returns a String
            String sideSelection = die.rollDie();
            // Store the letter in the ArrayList gameData
            gameData.add(sideSelection);
        }


        //System.out.println("Angelica gameData SIZE = " + gameData.size());
        return gameData;
    }

    /* loop through ArrayList gameData */

    /**
     *
     */

    public void displayGameData() {
        System.out.println("Boggle Board:");
        for (int i = 0; i < gameData.size(); i++) {
            System.out.print(gameData.get(i) + " ");
            /* print new line after fourth letter */
            if (i % 4 == 3) {
                System.out.println();
            }
        }
    }

    /**
     *
     * @return
     */
    public ArrayList<String> getGameData() {
        return gameData;
    }

}
