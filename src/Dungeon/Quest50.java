package Dungeon;

/*
name: Anna
date: May 31, 2024
title: Quest 50
description: The quest with multiple choice questions. The master baker encounter.
*/

//IMPORT ALL PACKAGES -----
import java.awt.*;      //-
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;   //-
import Game.*;          //-
import Items.*;         //-
import AnnaTools.*;     //-

import static Dungeon.Map.dealWithMapLevelCompletion;
//-------------------------


public class Quest50{

    public static JFrame quest50Frame = new JFrame(); //initialize
    public static SideBar quest50SideBar = new SideBar(); //local sidebar
    public static boolean complete = false; //this is used to check if the exit button has been pressed

    public static JPanel currentPanel; //the current panel

    public Quest50(){
        quest50Frame = new JFrame("By Anna Denisova"); //set title
        quest50Frame.setSize(GameVars.WINDOWWIDTH + GameVars.SIDEBARWIDTH, GameVars.WINDOWHEIGHT); //set size
        quest50Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //close setting
        quest50Frame.setLocationRelativeTo(null); //center
        quest50Frame.getContentPane().setLayout(new BorderLayout()); //make layout border

        currentPanel = new JPanel(); //initialize
        quest50SideBar = new SideBar(); //initialize
        quest50Frame.add(quest50SideBar.getPanel(), BorderLayout.EAST); //add sidebar
        quest50Frame.add(currentPanel, BorderLayout.CENTER); //add current panel

       // encounter();
    }

    public static void showQuest50(){ //Function
        quest50Frame.setVisible(true); //show frame
        encounter(); //show encounter
    }
    public static void hideQuest50(){ //Function
        dealWithMapLevelCompletion(50); //check if level is complete
        quest50Frame.setVisible(false); //hide frame
        MapGUI.showMapGUI(); //show map
    }

    public static void encounter(){

        //new Fonts(); //FOR TESTING REMOVE THIS AFTER

        currentPanel.removeAll(); //remove all
        currentPanel.setLayout(new GridLayout(3, 1)); //set layout
        currentPanel.setSize(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT); //set size
        currentPanel.setBackground(Color.decode("#FF0000")); //set background

        JLabel encounterTitle = new JLabel("THE MASTER"); //initialize
        encounterTitle.setFont(Fonts.BAD_GRUNGE); //set font to bad grunge
        encounterTitle.setAlignmentX(Component.CENTER_ALIGNMENT); //center
        encounterTitle.setHorizontalAlignment(SwingConstants.CENTER); //center

        JLabel encounterTitle2 = new JLabel("BAKER"); //initialize
        encounterTitle2.setFont(Fonts.BAD_GRUNGE); //set font to bad grunge
        encounterTitle2.setAlignmentX(Component.CENTER_ALIGNMENT); //center
        encounterTitle2.setHorizontalAlignment(SwingConstants.CENTER); //center

        JEditorPane encounterText = new JEditorPane(); //initialize

        encounterText.setEditable(false); //cannot be edited
        encounterText.setFocusable(false); //cannot be focused
        encounterText.setContentType("text/html"); //set content type to html

        encounterText.setText( //set text

                "<html><body style='font-size: 15px; padding: 10px; font-family: Arial'>" +
                        "<b> BAKER:</b> WHAT ARE YOU DOING IN MY DUNGEON? GO BACK TO WHERE YOU CAME FROM! " +
                        "Actually, on second thought, I challenge you to a test. You can only pass me when you prove yourself. " +
                        "Answer incorrectly and you’ll regret ever coming here." +
                        "</body></html>"

        );

        //make room around text:
        encounterText.setMargin(new Insets(5,5,5,5));
        encounterText.setBorder(BorderFactory.createLineBorder(Color.decode("#FF0000"), 5)); //set border

        JButton startButton = new JButton("Start"); //initialize
        startButton.addActionListener(new ActionListener(){ //action listener
            public void actionPerformed(ActionEvent e){ //action performed
                questionOne(); //move on!
            }
        });

        JPanel fullTitle = new JPanel(); //initialize
        fullTitle.setLayout(new GridLayout(2, 1)); //set layout
        fullTitle.add(encounterTitle); //add title
        fullTitle.add(encounterTitle2); //add title
        fullTitle.setBackground(Color.decode("#FF0000")); //set background

        currentPanel.add(fullTitle); //add title
        currentPanel.add(encounterText); //add text
        currentPanel.add(startButton); //add button

        currentPanel.revalidate(); //revalidate
        currentPanel.repaint(); //repaint
    }

    public static void questionOne(){
        currentPanel.removeAll(); // Remove all components from the current panel
        currentPanel.setLayout(new GridLayout(3, 1)); // Set the layout of the current panel to a 3x1 grid
        currentPanel.setSize(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT); // Set the size of the current panel
        currentPanel.setBackground(Color.decode("#F9B5AC")); // Set the background color of the current panel to a peach color

        JLabel questionOneTitle = new JLabel("     QUESTION 1"); // Create a label with text "QUESTION 1"
        questionOneTitle.setFont(Fonts.BAD_GRUNGE); // Set the font of the label

        // questionOneTitle.setFont(new Font("Arial Unicode MS", Font.PLAIN, 30));

        JTextArea questionOneText = new JTextArea("\n     What is the main ingredient in bread?"); // Create a text area with the question

        questionOneText.setOpaque(true); // Make the text area opaque
        //empty yellow border
        questionOneText.setBorder(BorderFactory.createLineBorder(Color.decode("#F9B5AC"), 5)); // Set the border color to peach
        questionOneText.setBackground(Color.white); // Set the background color of the text area to white
        questionOneText.setLineWrap(true); // Enable line wrap
        questionOneText.setWrapStyleWord(true); // Wrap lines at word boundaries
        questionOneText.setFont(new Font("Arial Unicode MS", Font.PLAIN, 20)); // Set the font of the text area

        // Create buttons for each answer option
        JButton optionOne = new JButton("Sugar");
        optionOne.setFont(new Font("Arial Unicode MS", Font.PLAIN, 20));
        JButton optionTwo = new JButton("Flour");
        optionTwo.setFont(new Font("Arial Unicode MS", Font.PLAIN, 20));
        JButton optionThree = new JButton("Salt");
        optionThree.setFont(new Font("Arial Unicode MS", Font.PLAIN, 20));
        JButton optionFour = new JButton("Milk");
        optionFour.setFont(new Font("Arial Unicode MS", Font.PLAIN, 20));

        // Add action listeners to each button
        optionOne.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                showIncorrectAns(); // Show incorrect answer screen if option one is selected
            }
        });

        optionTwo.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //THIS IS THE CORRECT ANSWER
                questionTwo(); // Move to the next question if option two is selected
            }
        });

        optionThree.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                showIncorrectAns(); // Show incorrect answer screen if option three is selected
            }
        });

        optionFour.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                showIncorrectAns(); // Show incorrect answer screen if option four is selected
            }
        });

        currentPanel.add(questionOneTitle); // Add the question title to the current panel
        currentPanel.add(questionOneText); // Add the question text to the current panel

        JPanel buttonPanel = new JPanel(new GridLayout(2, 2)); // Create a new panel for the buttons with a 2x2 grid layout
        buttonPanel.setBackground(Color.decode("#F9B5AC")); // Set the background color of the button panel to peach
        buttonPanel.add(optionOne); // Add option one button to the button panel
        buttonPanel.add(optionTwo); // Add option two button to the button panel
        buttonPanel.add(optionThree); // Add option three button to the button panel
        buttonPanel.add(optionFour); // Add option four button to the button panel
        currentPanel.add(buttonPanel); // Add the button panel to the current panel

        currentPanel.revalidate(); // Revalidate the current panel
        currentPanel.repaint(); // Repaint the current panel
    }
    public static void questionTwo(){
        currentPanel.removeAll(); // Remove all components from the current panel
        currentPanel.setLayout(new GridLayout(3, 1)); // Set the layout of the current panel to a 3x1 grid
        currentPanel.setSize(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT); // Set the size of the current panel
        currentPanel.setBackground(Color.decode("#F9B5AC")); // Set the background color of the current panel to a peach color

        JLabel questionTwoTitle = new JLabel("     QUESTION 2"); // Create a label with text "QUESTION 2"
        questionTwoTitle.setFont(Fonts.BAD_GRUNGE); // Set the font of the label
        //questionTwoTitle.setFont(new Font("Arial Unicode MS", Font.PLAIN, 30));

        JTextArea questionTwoText = new JTextArea("\n     Which is NOT a type of pastry?"); // Create a text area with the question
        questionTwoText.setOpaque(true); // Make the text area opaque
        //empty yellow border
        questionTwoText.setBorder(BorderFactory.createLineBorder(Color.decode("#F9B5AC"), 5)); // Set the border color to peach
        questionTwoText.setBackground(Color.white); // Set the background color of the text area to white
        questionTwoText.setLineWrap(true); // Enable line wrap
        questionTwoText.setWrapStyleWord(true); // Wrap lines at word boundaries
        //questionTwoText.setBackground(Color.decode("#F4E285"));
        questionTwoText.setFont(new Font("Arial Unicode MS", Font.PLAIN, 20)); // Set the font of the text area

        // Create buttons for each answer option
        JButton optionOne = new JButton("Croissant");
        optionOne.setFont(new Font("Arial Unicode MS", Font.PLAIN, 20));
        JButton optionTwo = new JButton("Baguette");
        optionTwo.setFont(new Font("Arial Unicode MS", Font.PLAIN, 20));
        JButton optionThree = new JButton("Danish");
        optionThree.setFont(new Font("Arial Unicode MS", Font.PLAIN, 20));
        JButton optionFour = new JButton("Eclair");
        optionFour.setFont(new Font("Arial Unicode MS", Font.PLAIN, 20));

        // Add action listeners to each button
        optionOne.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                showIncorrectAns(); // Show incorrect answer screen if option one is selected
            }
        });

        optionTwo.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //THIS IS THE CORRECT ANSWER
                questionThree(); // Move to the next question if option two is selected
            }
        });

        optionThree.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                showIncorrectAns(); // Show incorrect answer screen if option three is selected
            }
        });

        optionFour.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                showIncorrectAns(); // Show incorrect answer screen if option four is selected
            }
        });

        currentPanel.add(questionTwoTitle); // Add the question title to the current panel
        currentPanel.add(questionTwoText); // Add the question text to the current panel

        JPanel buttonPanel = new JPanel(new GridLayout(2, 2)); // Create a new panel for the buttons with a 2x2 grid layout
        buttonPanel.setBackground(Color.decode("#F9B5AC")); // Set the background color of the button panel to peach
        buttonPanel.add(optionOne); // Add option one button to the button panel
        buttonPanel.add(optionTwo); // Add option two button to the button panel
        buttonPanel.add(optionThree); // Add option three button to the button panel
        buttonPanel.add(optionFour); // Add option four button to the button panel
        currentPanel.add(buttonPanel); // Add the button panel to the current panel

        currentPanel.revalidate(); // Revalidate the current panel
        currentPanel.repaint(); // Repaint the current panel
    }

    public static void questionThree(){
        currentPanel.removeAll(); // Remove all components from the current panel
        currentPanel.setLayout(new GridLayout(3, 1)); // Set the layout of the current panel to a 3x1 grid
        currentPanel.setSize(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT); // Set the size of the current panel
        currentPanel.setBackground(Color.decode("#F9B5AC")); // Set the background color of the current panel to a peach color

        JLabel questionThreeTitle = new JLabel("     QUESTION 3"); // Create a label with text "QUESTION 3"
        questionThreeTitle.setFont(Fonts.BAD_GRUNGE); // Set the font of the label
        //questionThreeTitle.setFont(new Font("Arial Unicode MS", Font.PLAIN, 30));

        JTextArea questionThreeText = new JTextArea("\n     What gives cake its fluffy texture?"); // Create a text area with the question
        questionThreeText.setLineWrap(true); // Enable line wrap
        questionThreeText.setOpaque(true); // Make the text area opaque
        //empty yellow border
        questionThreeText.setBorder(BorderFactory.createLineBorder(Color.decode("#F9B5AC"), 5)); // Set the border color to peach
        questionThreeText.setBackground(Color.white); // Set the background color of the text area to white
        questionThreeText.setWrapStyleWord(true); // Wrap lines at word boundaries
        //questionThreeText.setBackground(Color.decode("#F4E285"));
        questionThreeText.setFont(new Font("Arial Unicode MS", Font.PLAIN, 20)); // Set the font of the text area

        // Create buttons for each answer option
        JButton optionOne = new JButton("Baking Soda");
        optionOne.setFont(new Font("Arial Unicode MS", Font.PLAIN, 20));
        JButton optionTwo = new JButton("Yeast");
        optionTwo.setFont(new Font("Arial Unicode MS", Font.PLAIN, 20));
        JButton optionThree = new JButton("Baking Powder");
        optionThree.setFont(new Font("Arial Unicode MS", Font.PLAIN, 20));
        JButton optionFour = new JButton("Vinegar");
        optionFour.setFont(new Font("Arial Unicode MS", Font.PLAIN, 20));

        // Add action listeners to each button
        optionOne.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                showIncorrectAns(); // Show incorrect answer screen if option one is selected
            }
        });

        optionTwo.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                showIncorrectAns(); // Show incorrect answer screen if option two is selected
            }
        });

        optionThree.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //THIS IS THE CORRECT ANSWER
                rewardScreen(); // Move to the reward screen if option three is selected
            }
        });

        optionFour.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                showIncorrectAns(); // Show incorrect answer screen if option four is selected
            }
        });

        currentPanel.add(questionThreeTitle); // Add the question title to the current panel
        currentPanel.add(questionThreeText); // Add the question text to the current panel

        JPanel buttonPanel = new JPanel(new GridLayout(2, 2)); // Create a new panel for the buttons with a 2x2 grid layout
        buttonPanel.setBackground(Color.decode("#F9B5AC")); // Set the background color of the button panel to peach
        buttonPanel.add(optionOne); // Add option one button to the button panel
        buttonPanel.add(optionTwo); // Add option two button to the button panel
        buttonPanel.add(optionThree); // Add option three button to the button panel
        buttonPanel.add(optionFour); // Add option four button to the button panel
        currentPanel.add(buttonPanel); // Add the button panel to the current panel

        currentPanel.revalidate(); // Revalidate the current panel
        currentPanel.repaint(); // Repaint the current panel
    }

    public static void rewardScreen(){
        currentPanel.removeAll(); //remove all components from the current panel
        currentPanel.setLayout(new GridLayout(3, 1)); //set the layout of the current panel to a 3x1 grid
        currentPanel.setSize(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT); //set the size of the current panel
        currentPanel.setBackground(Color.decode("#F9B5AC")); //set the background color of the current panel to a peach color

        JLabel rewardTitle = new JLabel("REWARD"); //create a label with text "REWARD"
        rewardTitle.setFont(Fonts.BAD_GRUNGE); //set the font of the label
        rewardTitle.setAlignmentX(Component.CENTER_ALIGNMENT); //align the label horizontally at the center
        rewardTitle.setHorizontalAlignment(SwingConstants.CENTER); //set horizontal alignment to center
        //rewardTitle.setFont(new Font("Arial Unicode MS", Font.PLAIN, 30));

        JTextArea rewardText = new JTextArea("                CAKE (-10 Hunger)\n                         50 coins"); //create a text area with the reward details
        rewardText.setLineWrap(true); //enable line wrap
        rewardText.setWrapStyleWord(true); //wrap lines at word boundaries
        rewardText.setBackground(Color.decode("#F9B5AC")); //set the background color of the text area to peach
        rewardText.setFont(new Font("Arial Unicode MS", Font.PLAIN, 30)); //set the font of the text area

        JButton exitButton = new JButton("Exit"); //create an exit button
        exitButton.addActionListener(new ActionListener(){ //add action listener to the exit button
            public void actionPerformed(ActionEvent e){ //define action to be performed when button is clicked
                hideQuest50(); //hide quest 50
                //showdungeon(); //show the dungeon
            }
        });

        //add rewards to inventory and update balance
        GameVars.inventory.add(new Food("Cake", 10, 10)); //add cake to inventory with hunger -10
        GameVars.balance += 50; //add 50 coins to balance
        AnnaTools.Updater.updateAllSidePanels(); //update all side panels
        complete = true; //set complete flag to true

        currentPanel.add(rewardTitle); //add the reward title to the current panel
        currentPanel.add(rewardText); //add the reward text to the current panel
        currentPanel.add(exitButton); //add the exit button to the current panel

        currentPanel.revalidate(); //revalidate the current panel
        currentPanel.repaint(); //repaint the current panel
    }


    public static String randomAttack(int damage){ //A random attack back
        String[] attacks = { //an array of all possible options
                "You get hit with a rolling pin and lose " + damage + " health.",
                "You get hit with a pan and lose " + damage + " health.",
                "You are forced to eat a piece of bread and choke. You lose " + damage + " health."
        };
        return attacks[(int) (Math.random()*3)]; //randomly generate
    }

    public static void showIncorrectAns(){ //Function
        int damage = (int) (Math.random()*10 + 10); //randomly generate an attack between 10 and 20
        JOptionPane.showMessageDialog(quest50Frame, randomAttack(damage), "Incorrect", JOptionPane.ERROR_MESSAGE); //show the attack
        GameVars.health -= damage; //decrease health
        AnnaTools.Updater.updateAllSidePanels(); //update all side panels
    }

    /* TESTING:
    public static void main(String[] args){
        Quest50 quest50 = new Quest50();
        quest50.showQuest50();

    }*/


}
