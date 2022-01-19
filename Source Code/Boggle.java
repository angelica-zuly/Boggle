/*
    Assignment #6 - Boggle
    Angelica Longo
    UCFID: 3721265
 */
package boggle;

import core.Board;
import inputOutput.ReadDataFile;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import userInterface.BoggleUi;

/**
 *
 * @author angel
 */
public class Boggle {

    private static final String BOGGLE_DATA = "BoggleData.txt";
    private static final String BOGGLE_DICTIONARY = "Dictionary.txt";

    private static ArrayList<String> diceData = new ArrayList<String>();
    private ArrayList<String> dictionaryData = new ArrayList<String>();

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BoggleUi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(BoggleUi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(BoggleUi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(BoggleUi.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("Welcome to Boggle!");

        // Diplays popup dialog box
        JOptionPane.showMessageDialog(null, "Let's Play Boggle!");

        // Instantiate instances of class ReadDataFile
        ReadDataFile boggleLetterData = new ReadDataFile(BOGGLE_DATA);
        ReadDataFile dictionaryWordData = new ReadDataFile(BOGGLE_DICTIONARY);

        boggleLetterData.populateData();
        dictionaryWordData.populateData();

        Board newBoard = new Board(boggleLetterData.getData(), dictionaryWordData.getData());
        newBoard.populateDice();

        // Method call to randomize game Boggle Data
        diceData = newBoard.shakeDice();

        // Display # of objects in the ArrayList of
        //type String that stores the dictionary data
        System.out.println("There are " + dictionaryWordData.getData().size() + " entries in the dictionary");
        // Displays Boggle Board (Assignment 3)
        newBoard.displayGameData();

        BoggleUi ui = new BoggleUi(newBoard, dictionaryWordData.getData());
    }
}
