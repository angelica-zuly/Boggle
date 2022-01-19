package userInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Timer;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import core.Board;
import java.awt.font.TextAttribute;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class BoggleUi extends JFrame {

    private Board board;
    private JFrame frame;
    private int minutes = 3;
    private int seconds = 0;
    private int score = 0;
    private Timer timer;
    private JLabel timeLabel;
    private JTextPane textPane;
    private JLabel scoreLabel;
    private JTextField currentWordField;

    private JButton button0;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    private JButton button8;
    private JButton button9;
    private JButton button10;
    private JButton button11;
    private JButton button12;
    private JButton button13;
    private JButton button14;
    private JButton button15;

    private ArrayList<String> letterArray;
    private ArrayList<JButton> usedButtonArray;
    private ArrayList<String> dictionary;
    private Map<Integer, List<JButton>> buttonMap;

    /**
     * class constructor
     */
    public BoggleUi(Board newBoard,ArrayList<String> newDictionary) {
        board = newBoard;
        dictionary = newDictionary;
        usedButtonArray = new ArrayList<JButton>();
        frame = new JFrame("Boggle");

        //calling inner class
        initComponents();

        //creating hashmap to register all adjacent buttons for each die
        buttonMap = new HashMap<Integer, List<JButton>>();

        // Surrounding buttons for each die
        JButton[] buttonArray00 = {button1, button4, button5};
        JButton[] buttonArray01 = {button0, button2,button4,button5, button6};
        JButton[] buttonArray02 = {button1, button3,button5,button6, button7};
        JButton[] buttonArray03 = {button2, button6, button7};
        JButton[] buttonArray04 = {button0, button1, button5, button8, button9};
        JButton[] buttonArray05 = {button0, button1, button2, button4, button6, button8, button9, button10};
        JButton[] buttonArray06 = {button1, button2, button3, button5, button7, button9, button10, button11};
        JButton[] buttonArray07 = {button2, button3, button6, button10, button11};
        JButton[] buttonArray08 = {button4, button5, button9, button12, button13};
        JButton[] buttonArray09 = {button4, button5, button6, button8, button10, button12, button13, button14};
        JButton[] buttonArray10 = {button5, button6, button7, button9, button11, button13, button14, button15};
        JButton[] buttonArray11 = {button6, button7, button10, button14, button15};
        JButton[] buttonArray12 = {button8, button9, button13};
        JButton[] buttonArray13 = {button8, button9, button10, button12, button14};
        JButton[] buttonArray14 = {button9, button10, button11, button13, button15};
        JButton[] buttonArray15 = {button10, button11, button14};

        List<JButton> temp00 = new ArrayList<JButton>(Arrays.asList(buttonArray00));
        List<JButton> temp01 = new ArrayList<JButton>(Arrays.asList(buttonArray01));
        List<JButton> temp02 = new ArrayList<JButton>(Arrays.asList(buttonArray02));
        List<JButton> temp03 = new ArrayList<JButton>(Arrays.asList(buttonArray03));
        List<JButton> temp04 = new ArrayList<JButton>(Arrays.asList(buttonArray04));
        List<JButton> temp05 = new ArrayList<JButton>(Arrays.asList(buttonArray05));
        List<JButton> temp06 = new ArrayList<JButton>(Arrays.asList(buttonArray06));
        List<JButton> temp07 = new ArrayList<JButton>(Arrays.asList(buttonArray07));
        List<JButton> temp08 = new ArrayList<JButton>(Arrays.asList(buttonArray08));
        List<JButton> temp09 = new ArrayList<JButton>(Arrays.asList(buttonArray09));
        List<JButton> temp10 = new ArrayList<JButton>(Arrays.asList(buttonArray10));
        List<JButton> temp11 = new ArrayList<JButton>(Arrays.asList(buttonArray11));
        List<JButton> temp12 = new ArrayList<JButton>(Arrays.asList(buttonArray12));
        List<JButton> temp13 = new ArrayList<JButton>(Arrays.asList(buttonArray13));
        List<JButton> temp14 = new ArrayList<JButton>(Arrays.asList(buttonArray14));
        List<JButton> temp15 = new ArrayList<JButton>(Arrays.asList(buttonArray15));

        buttonMap.put(0, temp00);
        buttonMap.put(1, temp01);
        buttonMap.put(2, temp02);
        buttonMap.put(3, temp03);
        buttonMap.put(4, temp04);
        buttonMap.put(5, temp05);
        buttonMap.put(6, temp06);
        buttonMap.put(7, temp07);
        buttonMap.put(8, temp08);
        buttonMap.put(9, temp09);
        buttonMap.put(10, temp10);
        buttonMap.put(11, temp11);
        buttonMap.put(12, temp12);
        buttonMap.put(13, temp13);
        buttonMap.put(14, temp14);
        buttonMap.put(15, temp15);

    }

    /**
     *The inner class that contains all the main context for the UI
     */
    private void initComponents(){

        //Creating JFrame (using BorderLayout)
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);

        //Creating JMenuBar
        JMenu menu = new JMenu("Boggle");
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menu);
        //Adding items to the menu
        JMenuItem newGameItem = new JMenuItem("New Game");
        newGameItem.addActionListener(new ResetListener());
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
                int selectedOption = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to Exit?",
                        "Select an Option...",
                        JOptionPane.YES_NO_OPTION);
                if (selectedOption == JOptionPane.YES_OPTION){
                    System.exit(0);
                }
            }
        });
        menu.add(newGameItem);
        menu.add(exitItem);
        //Adding the menu to the main JFrame
        frame.add(menuBar, BorderLayout.NORTH);

        //Creating panel to hold dice buttons (using GridLayout)
        JPanel dicePanel = new JPanel(new GridLayout(4, 4, 6, 6));
        //Adding size to the grid panel
        dicePanel.setPreferredSize(new Dimension(800, 800));
        TitledBorder gridTitle = BorderFactory.createTitledBorder("Boggle Board");
        dicePanel.setBorder(gridTitle);
        gridTitle.setBorder(new LineBorder(Color.blue));

        //Creating 4x4 buttons for the dice button grid
        button0 = new JButton();
        button0.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent ev){
                    usedButtonArray.add(button0);
                    addLetterToWord(button0.getText());
                    enableAdjacentButtons(0);
                }
            });
        button1 = new JButton();
        button1.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent ev){
                    usedButtonArray.add(button1);
                    addLetterToWord(button1.getText());
                    enableAdjacentButtons(1);
                }
            });
        button2 = new JButton();
        button2.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent ev){
                    usedButtonArray.add(button2);
                    addLetterToWord(button2.getText());
                    enableAdjacentButtons(2);
                }
            });
        button3 = new JButton();
        button3.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent ev){
                    usedButtonArray.add(button3);
                    addLetterToWord(button3.getText());
                    enableAdjacentButtons(3);
                }
            });
        button4 = new JButton();
        button4.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent ev){
                    usedButtonArray.add(button4);
                    addLetterToWord(button4.getText());
                    enableAdjacentButtons(4);
                }
            });
        button5 = new JButton();
        button5.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent ev){
                    usedButtonArray.add(button5);
                    addLetterToWord(button5.getText());
                    enableAdjacentButtons(5);
                }
            });
        button6 = new JButton();
        button6.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent ev){
                    usedButtonArray.add(button6);
                    addLetterToWord(button6.getText());
                    enableAdjacentButtons(6);
                }
            });
        button7 = new JButton();
        button7.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent ev){
                    usedButtonArray.add(button7);
                    addLetterToWord(button7.getText());
                    enableAdjacentButtons(7);
                }
            });
        button8 = new JButton();
        button8.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent ev){
                    usedButtonArray.add(button8);
                    addLetterToWord(button8.getText());
                    enableAdjacentButtons(8);
                }
            });
        button9 = new JButton();
        button9.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent ev){
                    usedButtonArray.add(button9);
                    addLetterToWord(button9.getText());
                    enableAdjacentButtons(9);
                }
            });
        button10 = new JButton();
        button10.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent ev){
                    usedButtonArray.add(button10);
                    addLetterToWord(button10.getText());
                    enableAdjacentButtons(10);
                }
            });
        button11 = new JButton();
        button11.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent ev){
                    usedButtonArray.add(button11);
                    addLetterToWord(button11.getText());
                    enableAdjacentButtons(11);
                }
            });
        button12 = new JButton();
        button12.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent ev){
                    usedButtonArray.add(button12);
                    addLetterToWord(button12.getText());
                    enableAdjacentButtons(12);
                }
            });
        button13 = new JButton();
        button13.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent ev){
                    usedButtonArray.add(button13);
                    addLetterToWord(button13.getText());
                    enableAdjacentButtons(13);
                }
            });
        button14 = new JButton();
        button14.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent ev){
                    usedButtonArray.add(button14);
                    addLetterToWord(button14.getText());
                    enableAdjacentButtons(14);
                }
            });
        button15 = new JButton();
        button15.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent ev){
                    usedButtonArray.add(button15);
                    addLetterToWord(button15.getText());
                    enableAdjacentButtons(15);
                }
            });

        letterArray = board.shakeDice();
        //Adding the buttons to the grid
        dicePanel.add(button0);
        button0.setText(letterArray.get(0).toString());
        dicePanel.add(button1);
        button1.setText(letterArray.get(1).toString());
        dicePanel.add(button2);
        button2.setText(letterArray.get(2).toString());
        dicePanel.add(button3);
        button3.setText(letterArray.get(3).toString());
        dicePanel.add(button4);
        button4.setText(letterArray.get(4).toString());
        dicePanel.add(button5);
        button5.setText(letterArray.get(5).toString());
        dicePanel.add(button6);
        button6.setText(letterArray.get(6).toString());
        dicePanel.add(button7);
        button7.setText(letterArray.get(7).toString());
        dicePanel.add(button8);
        button8.setText(letterArray.get(8).toString());
        dicePanel.add(button9);
        button9.setText(letterArray.get(9).toString());
        dicePanel.add(button10);
        button10.setText(letterArray.get(10).toString());
        dicePanel.add(button11);
        button11.setText(letterArray.get(11).toString());
        dicePanel.add(button12);
        button12.setText(letterArray.get(12).toString());
        dicePanel.add(button13);
        button13.setText(letterArray.get(13).toString());
        dicePanel.add(button14);
        button14.setText(letterArray.get(14).toString());
        dicePanel.add(button15);
        button15.setText(letterArray.get(15).toString());

        //Adding the grid panel of buttons to the main JFrame
        frame.add(dicePanel, BorderLayout.CENTER);

        //Creating side panel for the games progress (using FlowLayout)
        JPanel progressPanel = new JPanel(new FlowLayout());
        //progressPanel.setLayout(new BoxLayout(progressPanel, BoxLayout.Y_AXIS));
        //Adding size to the panel
        progressPanel.setPreferredSize(new Dimension(200, 300));
        TitledBorder wordsTitle = BorderFactory.createTitledBorder("Enter Words Found");
        progressPanel.setBorder(wordsTitle);
        wordsTitle.setBorder(new LineBorder(Color.blue));

        //Creating TextPane for the progress panel
        textPane = new JTextPane();
        textPane.setText("");
        textPane.setEditable(false);
        //Adding a scrollbar to the TextPane
        JScrollPane scrollPane = new JScrollPane(textPane);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(180, 95));
        //scrollPane.setViewportView(textPane);
        //Adding the TextPane to the progress panel
        progressPanel.add(scrollPane);

        //Creating time panel for the progress panel
        JPanel timePanel = new JPanel();
        TitledBorder timeTitle = BorderFactory.createTitledBorder("Time Left");
        timePanel.setPreferredSize(new Dimension(180, 95));
        timePanel.setBorder(timeTitle);
        timeTitle.setBorder(new LineBorder(Color.blue));

        //Adding time panel to the side progress panel
        progressPanel.add(timePanel);

        //Creating time label for time panel
        timeLabel = new JLabel();
        timeLabel.setText("3:00");
        Font timeFont = new Font("Serif", Font.BOLD, 45);
        timeLabel.setFont(timeFont);
        timeLabel.setPreferredSize(new Dimension(95, 95));
        timePanel.add(timeLabel);
        timer = new Timer(1000, new TimerListener());
        timer.start();

        //Creating shake button for the side progress panel
        JButton shakeButton = new JButton("Shake Dice");
        shakeButton.addActionListener(new ResetListener());
        shakeButton.setBackground(Color.yellow);
        shakeButton.setPreferredSize(new Dimension(180,95));
        //Adding the button to the progress panel
        progressPanel.add(shakeButton);

        //Adding the side progress panel to the main JFrame
        frame.add(progressPanel, BorderLayout.EAST);

        //Creating bottom panel for current word (using FlowLayout)
        JPanel bottomPanel = new JPanel(new FlowLayout());
        TitledBorder bottomTitle = BorderFactory.createTitledBorder("Current Word");
        bottomPanel.setBorder(bottomTitle);
        bottomTitle.setBorder(new LineBorder(Color.blue));

        //Creating current word panel for the bottom panel
        JPanel currentWordPanel = new JPanel();
        TitledBorder currentWordTitle = BorderFactory.createTitledBorder("Current Word");
        currentWordPanel.setBorder(currentWordTitle);
        currentWordTitle.setBorder(new LineBorder(Color.blue));
        int WIDTH = 85;
        currentWordPanel.setPreferredSize(new Dimension(320, WIDTH));
        //Addind the current word panel to the bottom panel
        bottomPanel.add(currentWordPanel);

        //adding Label to current word panel
        currentWordField = new JTextField(15);
        currentWordField.setEditable(false);
        currentWordField.setText("");
        Font currentWordFont = new Font("Serif", Font.PLAIN, 25);
        currentWordField.setFont(currentWordFont);
        currentWordPanel.add(currentWordField);

        //creating a submit button for the bottom panel
        JButton submitButton = new JButton("Submit Word");
        submitButton.addActionListener(new SubmitListener());
        submitButton.setBackground(Color.green);
        submitButton.setPreferredSize(new Dimension(120, WIDTH));
        //Adding sumbit button to the bottom panel
        bottomPanel.add(submitButton);

        //creating score panel for the bottom panel
        JPanel scorePanel = new JPanel();
        TitledBorder scoreTitle = BorderFactory.createTitledBorder("Score");
        scorePanel.setBorder(scoreTitle);
        scoreTitle.setBorder(new LineBorder(Color.blue));
        scorePanel.setPreferredSize(new Dimension(100, WIDTH));
        //adding score panel to the bottom panel
        bottomPanel.add(scorePanel);
        //adding the bottom panel to the main JFrame
        frame.add(bottomPanel, BorderLayout.SOUTH);

        //adding Label to score
        scoreLabel = new JLabel();
        scoreLabel.setText(String.valueOf(score));
        Font scoreFont = new Font("Serif", Font.BOLD, 35);
        scoreLabel.setFont(scoreFont);
        scorePanel.add(scoreLabel);

        //Adding size to the main JFrame
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);


}

    /**
     * Method updating JTextField with the letter(s) from die
     * @param letter The current letter on each die
     */
    private void addLetterToWord(String letter) {
        String tempString = currentWordField.getText();
        tempString += letter;
        currentWordField.setText(tempString);
    }

    /**
     * Method enabling all the dice buttons on grid.
     * @param enable Flag to enable/disable dice buttons.
     */
    private void enableAllButtons(boolean enable) {
        button0.setEnabled(enable);
        button1.setEnabled(enable);
        button2.setEnabled(enable);
        button3.setEnabled(enable);
        button4.setEnabled(enable);
        button5.setEnabled(enable);
        button6.setEnabled(enable);
        button7.setEnabled(enable);
        button8.setEnabled(enable);
        button9.setEnabled(enable);
        button10.setEnabled(enable);
        button11.setEnabled(enable);
        button12.setEnabled(enable);
        button13.setEnabled(enable);
        button14.setEnabled(enable);
        button15.setEnabled(enable);
    }


    /**
     * Method to enable the buttons that are surrounding the
     * selected button.
     * @param buttonIndex The index representation of the button
     */
    private void enableAdjacentButtons(int buttonIndex) {
        // Disable all buttons
        enableAllButtons(false);

        // Enable adjacent buttons that have not been used
        ArrayList<JButton> adjacentButtons = (ArrayList<JButton>)buttonMap.get(buttonIndex);
        for (JButton button : adjacentButtons) {
            if (!usedButtonArray.contains(button)) {
                button.setEnabled(true);
            }
        }
    }

    /**
     * An ActionLisenter that validates if submitted word
     * is found in dictionary.
     */
    public class SubmitListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){

            enableAllButtons(true);
            //validating word
            boolean found = false;
            for (String word : dictionary) {
                if (word.equalsIgnoreCase(currentWordField.getText())) {
                    found = true;
                    break;
                }
            }
            if (found) {
                score++;
                scoreLabel.setText(String.valueOf(score));
                // Append currentWordField text to current textPane text.
                String tempString = textPane.getText() + currentWordField.getText() + "\n";
                textPane.setText(tempString);
            } else {
                JOptionPane.showMessageDialog(null, "Word not found in dictionary!");
            }
            // Clear the current word text field
            currentWordField.setText("");

            // Reset the used button array
            usedButtonArray.clear();
        }
    }

    /**
     * An ActionLisenter that resets the entire board and creates
     * a new Boggle game.
     */
    public class ResetListener implements ActionListener{
        @Override
         public void actionPerformed(ActionEvent e){

            enableAllButtons(true);
             letterArray = board.shakeDice();

            //Resetting the game dice
            button0.setText(letterArray.get(0).toString());
            button1.setText(letterArray.get(1).toString());
            button2.setText(letterArray.get(2).toString());
            button3.setText(letterArray.get(3).toString());
            button4.setText(letterArray.get(4).toString());
            button5.setText(letterArray.get(5).toString());
            button6.setText(letterArray.get(6).toString());
            button7.setText(letterArray.get(7).toString());
            button8.setText(letterArray.get(8).toString());
            button9.setText(letterArray.get(9).toString());
            button10.setText(letterArray.get(10).toString());
            button11.setText(letterArray.get(11).toString());
            button12.setText(letterArray.get(12).toString());
            button13.setText(letterArray.get(13).toString());
            button14.setText(letterArray.get(14).toString());
            button15.setText(letterArray.get(15).toString());

            //clearing rest of the game data
            score = 0;
            textPane.setText("");
            scoreLabel.setText("0");
            currentWordField.setText("");
            timeLabel.setText("3:00");
            frame.revalidate();
            frame.repaint();

            //Resetting the timer
            timer.stop();
            minutes = 3;
            seconds = 0;
            timer.start();
         }
     }

    /**
     * An ActionLisenter that starts a 3 min timer as
     * soon as the game starts.
     */
    public class TimerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (seconds == 0 && minutes == 0) {
                timer.stop();
                enableAllButtons(false);
                JOptionPane.showMessageDialog(null, "Gameover! You found [" + scoreLabel.getText() + "] words!");
            } else {
                if(seconds == 0) {
                    seconds = 59;
                    minutes--;
                } else {
                    seconds--;
                }
            }

            if(seconds < 10) {
                String strSeconds = "0" + String.valueOf(seconds);
                timeLabel.setText(String.valueOf(minutes) + ":" + strSeconds);
            } else {
                timeLabel.setText(String.valueOf(minutes) + ":" + String.valueOf(seconds));
            }
        }
    }

}
