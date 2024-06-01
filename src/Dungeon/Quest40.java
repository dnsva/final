package Dungeon;

//IMPORT ALL PACKAGES -----
import java.io.*;       //-
import java.util.*;     //-
import java.awt.*;      //-
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;   //-
import Game.*;          //-
import AnnaTools.*;     //-

import static Dungeon.Map.dealWithMapLevelCompletion;
import static Game.GameOver.checkGameOver;
//-------------------------

public class Quest40 {

    public static JFrame quest40Frame = new JFrame(); //the frame with the display AND sidebar
    public static SideBar quest40SideBar = new SideBar(); //local sidebar
    public static boolean complete = false; //this is used to check if the quest has been completed

    public static JPanel currentPanel; //the current panel

    public Quest40() {

        quest40Frame = new JFrame("By Anna Denisova"); //initialize and set title
        quest40Frame.setSize(GameVars.WINDOWWIDTH + GameVars.SIDEBARWIDTH, GameVars.WINDOWHEIGHT); //set size
        quest40Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //close setting
        quest40Frame.setLocationRelativeTo(null); //center
        quest40Frame.getContentPane().setLayout(new BorderLayout()); //make layout border

        currentPanel = new JPanel(); //initialize
        quest40SideBar = new SideBar(); //initialize
        quest40Frame.add(quest40SideBar.getPanel(), BorderLayout.EAST); //add sidebar
        quest40Frame.add(currentPanel, BorderLayout.CENTER); //add current panel

        exposition(); //call exposition
    }

    public static void showQuest40() { //show the quest
        quest40Frame.setVisible(true); //make it visible
    }

    public static void hideQuest40() { //hide the quest
        dealWithMapLevelCompletion(40); //check if the map level is complete
        quest40Frame.setVisible(false); //make it invisible
        MapGUI.showMapGUI(); //show the map
    }

    public static void exposition() {
        currentPanel.removeAll(); // Remove all components from the current panel
        //currentPanel.setLayout(new GridLayout(3, 1)); // Set the layout of the current panel to a 3x1 grid (commented out)
        currentPanel.setSize(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT); // Set the size of the current panel
        currentPanel.setBackground(Color.decode("#FF0000")); // Set the background color of the current panel to red

        JPanel expositionTitlePanel = new JPanel(new GridLayout(2, 1)); // Create a new panel with a 2x1 grid layout
        JLabel expositionTitle = new JLabel("YOUR WORK"); // Create a label with text "YOUR WORK"
        JLabel expositionTitle2 = new JLabel("BOSS"); // Create a label with text "BOSS"
        expositionTitle.setFont(Fonts.pepperoni_pizza); // Set the font of the first label
        expositionTitle.setHorizontalAlignment(SwingConstants.CENTER); // Center the text horizontally
        expositionTitle.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the text
        expositionTitle2.setFont(Fonts.pepperoni_pizza); // Set the font of the second label
        expositionTitle2.setHorizontalAlignment(SwingConstants.CENTER); // Center the text horizontally
        expositionTitle2.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the text
        expositionTitlePanel.add(expositionTitle); // Add the first label to the panel
        expositionTitlePanel.add(expositionTitle2); // Add the second label to the panel
        expositionTitle.setBackground(Color.decode("#FF0000")); // Set the background color of the first label to red
        expositionTitle2.setBackground(Color.decode("#FF0000")); // Set the background color of the second label to red
        expositionTitlePanel.setBackground(Color.decode("#FF0000")); // Set the background color of the panel to red
        expositionTitlePanel.setSize(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT/3); // Set the size of the panel
        expositionTitlePanel.setPreferredSize(new Dimension(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT/3)); // Set the preferred size of the panel

        JEditorPane expositionText = new JEditorPane(); // Create a new editor pane

        expositionText.setOpaque(true); // Make the editor pane opaque
        //empty border (red)
        expositionText.setBorder(BorderFactory.createLineBorder(Color.decode("#FF0000"), 5)); // Set the border color to red
        expositionText.setBackground(Color.white); // Set the background color of the editor pane to white
        expositionText.setEditable(false); // Make the editor pane non-editable
        expositionText.setFocusable(false); // Make the editor pane non-focusable
        expositionText.setContentType("text/html"); // Set the content type to HTML
        expositionText.setMargin(new Insets(5,5,5,5)); // Set the margin

        expositionText.setText( // Set the text of the editor pane
                "<html><body style='font-size: 15px; padding: 10px; font-family: Arial'>" +
                        "<b> MANAGER:</b> Where is your tie? I don’t see it. You work in" +
                        " an important facility and you need a tie." +
                        "</body></html>"
        );

        expositionText.setSize(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT/3); // Set the size of the editor pane
        expositionText.setPreferredSize(new Dimension(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT/3-100)); // Set the preferred size of the editor pane

        JButton option1 = new JButton("Stop it, you’re annoying and I’m not talking to you. Bye."); // Create a button with text "Stop it, you’re annoying and I’m not talking to you. Bye."
        JButton option2 = new JButton("I lost it."); // Create a button with text "I lost it."
        JButton option3 = new JButton("Bob took it."); // Create a button with text "Bob took it."
        option1.setPreferredSize(new Dimension(GameVars.WINDOWWIDTH, 83)); // Set the preferred size of the first button

        option1.addActionListener(new ActionListener() { // Add an action listener to the first button
            public void actionPerformed(ActionEvent e) { // Define the action to be performed
                responseOne(); // Call the responseOne method
            }
        });

        option2.addActionListener(new ActionListener() { // Add an action listener to the second button
            public void actionPerformed(ActionEvent e) { // Define the action to be performed
                responseTwo(); // Call the responseTwo method
            }
        });

        option3.addActionListener(new ActionListener() { // Add an action listener to the third button
            public void actionPerformed(ActionEvent e) { // Define the action to be performed
                responseThree(); // Call the responseThree method
            }
        });

        currentPanel.add(expositionTitlePanel); // Add the title panel to the current panel
        currentPanel.add(expositionText); // Add the editor pane to the current panel

        JPanel buttonPanel = new JPanel(new GridLayout(3, 1)); // Create a new panel with a 3x1 grid layout
        buttonPanel.setBackground(Color.decode("#FF0000")); // Set the background color of the button panel to red
        buttonPanel.add(option1); // Add the first button to the button panel
        buttonPanel.add(option2); // Add the second button to the button panel
        buttonPanel.add(option3); // Add the third button to the button panel
        currentPanel.add(buttonPanel); // Add the button panel to the current panel

        currentPanel.revalidate(); // Revalidate the current panel
        currentPanel.repaint(); // Repaint the current panel
    }

    public static void responseOne() { //the first possibility
        showResponse("Who do you think you are? I’m the manager around here and you can’t go on disrespecting me. I’m literally in charge of your salary and your place here. Get a tie and get back to work.");
    }

    public static void responseTwo() { //the second possibility
        showResponse("What? You’re so irresponsible. Get a new tie and get back to work. I won’t let this slide next time.");
    }

    public static void responseThree() { //the third possibility (fight)
        currentPanel.removeAll(); // Remove all components from the current panel
        currentPanel.setLayout(new GridLayout(3, 1)); // Set the layout of the current panel to a 3x1 grid
        currentPanel.setSize(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT); // Set the size of the current panel
        currentPanel.setBackground(Color.decode("#E3F2FD")); // Set the background color of the current panel to a light blue

        JLabel responseTitle = new JLabel("WHAT"); // Create a label with text "WHAT"
        responseTitle.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the text
        responseTitle.setHorizontalAlignment(SwingConstants.CENTER); // Center the text horizontally
        responseTitle.setFont(Fonts.pepperoni_pizza); // Set the font of the label

        JEditorPane responseText = new JEditorPane(); // Create a new editor pane

        responseText.setOpaque(true); // Make the editor pane opaque
        responseText.setBorder(BorderFactory.createLineBorder(Color.decode("#E3F2FD"), 5)); // Set the border color to light blue
        responseText.setBackground(Color.white); // Set the background color of the editor pane to white
        responseText.setEditable(false); // Make the editor pane non-editable
        responseText.setFocusable(false); // Make the editor pane non-focusable
        responseText.setContentType("text/html"); // Set the content type to HTML
        responseText.setMargin(new Insets(5,5,5,5)); // Set the margin

        responseText.setText( // Set the text of the editor pane
                "<html><body style='font-size: 15px; padding: 10px; font-family: Arial'>" +
                        "<b> MANAGER: </b>" + "WHY DID YOU LET HIM TAKE IT???? PROTECT YOUR TIE LIKE YOU ARE PROTECTING YOUR FIRST BORN. TAKE IT BACK NOW. " +
                        "</body></html>"
        );

        JButton okButton = new JButton("OK"); // Create a button with text "OK"
        okButton.addActionListener(new ActionListener() { // Add an action listener to the button
            public void actionPerformed(ActionEvent e) { //FIGHT BOB!!!!!!!
                //======================================================================================================
                currentPanel.removeAll(); // Remove all components from the current panel
                currentPanel.setLayout(new GridLayout(4, 1)); // Set the layout of the current panel to a 4x1 grid
                currentPanel.setSize(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT); // Set the size of the current panel
                currentPanel.setBackground(Color.decode("#E3F2FD")); // Set the background color of the current panel to a light blue

                JLabel fightTitle = new JLabel("DEFEAT BOB"); // Create a label with text "DEFEAT BOB"
                fightTitle.setFont(Fonts.pepperoni_pizza); // Set the font of the label
                fightTitle.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the text
                fightTitle.setHorizontalAlignment(SwingConstants.CENTER); // Center the text horizontally

                JEditorPane fightDescription = new JEditorPane(); // Create a new editor pane
                fightDescription.setOpaque(true); // Make the editor pane opaque
                fightDescription.setBorder(BorderFactory.createLineBorder(Color.decode("#E3F2FD"), 5)); // Set the border color to light blue
                fightDescription.setBackground(Color.white); // Set the background color of the editor pane to white
                fightDescription.setEditable(false); // Make the editor pane non-editable
                fightDescription.setFocusable(false); // Make the editor pane non-focusable
                fightDescription.setContentType("text/html"); // Set the content type to HTML
                fightDescription.setMargin(new Insets(5,5,5,5)); // Set the margin
                fightDescription.setText( // Set the text of the editor pane
                        "<html><body style='font-size: 15px; padding: 10px; font-family: Arial'>" +
                                "<br>You find Bob wearing your tie. He knows he has done something wrong. Defeat Bob." +
                                "</body></html>"
                );

                JLabel bobHealthLabel = new JLabel("Bob's Health: 100"); // Create a label with text "Bob's Health: 100"
                bobHealthLabel.setFont(new Font("Arial Unicode MS", Font.PLAIN, 30)); // Set the font of the label
                bobHealthLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center the text horizontally
                bobHealthLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the text
                bobHealthLabel.setBackground(Color.white); // Set the background color of the label to white
                bobHealthLabel.setBorder(BorderFactory.createLineBorder(Color.decode("#E3F2FD"), 5)); // Set the border color to light blue
                bobHealthLabel.setOpaque(true); // Make the label opaque

                JButton attackButton = new JButton("ATTACK"); // Create a button with text "ATTACK"
                attackButton.addActionListener(new ActionListener() {
                    int bobHealth = 100; // Initialize Bob's health to 100

                    public void actionPerformed(ActionEvent e) { // Define the action to be performed

                        if(!GameVars.currWeapon.attackMiss()){ // Check if the attack misses
                            bobHealth -= GameVars.fullAttackPower; // Subtract the attack power from Bob's health
                            JOptionPane.showMessageDialog(quest40Frame, "You attacked Bob and dealt " + GameVars.fullAttackPower + " damage.", "Fight", JOptionPane.INFORMATION_MESSAGE); // Display a message with the damage dealt
                        }else{
                            JOptionPane.showMessageDialog(quest40Frame, "You tried to attack but you missed.", "Fight", JOptionPane.INFORMATION_MESSAGE); // Display a message if the attack misses
                        }

                        GameVars.health -= Math.max(0, (20 - GameVars.fullDefensePower)); // Subtract the defense power from the player's health
                        AnnaTools.Updater.updateAllSidePanels(); // Update all side panels

                        if (bobHealth <= 0) { // Check if Bob's health is less than or equal to 0
                            rewardScreen(10); // Call the rewardScreen method with a parameter of 10
                        } else {
                            bobHealthLabel.setText("Bob's Health: " + bobHealth); // Update the text of Bob's health label
                            JOptionPane.showMessageDialog(quest40Frame, "In response to your attack, Bob attacks! You lose " + Math.max(0,(20 - GameVars.fullDefensePower)) + " health.", "Fight", JOptionPane.INFORMATION_MESSAGE); // Display a message with the damage dealt by Bob
                        }
                        checkGameOver(); // Check if the game is over
                        AnnaTools.Updater.updateAllSidePanels(); // Update all side panels
                    }
                });

                currentPanel.add(fightTitle); // Add the fight title to the current panel
                currentPanel.add(fightDescription); // Add the fight description to the current panel
                currentPanel.add(bobHealthLabel); // Add Bob's health label to the current panel
                currentPanel.add(attackButton); // Add the attack button to the current panel

                currentPanel.revalidate(); // Revalidate the current panel
                currentPanel.repaint(); // Repaint the current panel
                //======================================================================================================
            }
        });

        currentPanel.add(responseTitle); // Add the response title to the current panel
        currentPanel.add(responseText); // Add the response text to the current panel
        currentPanel.add(okButton); // Add the OK button to the current panel

        currentPanel.revalidate(); // Revalidate the current panel
        currentPanel.repaint(); // Repaint the current panel
    }

    public static void showResponse(String text) {
        currentPanel.removeAll(); // Remove all components from the current panel
        currentPanel.setLayout(new GridLayout(3, 1)); // Set the layout of the current panel to a 3x1 grid
        currentPanel.setSize(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT); // Set the size of the current panel
        currentPanel.setBackground(Color.decode("#E3F2FD")); // Set the background color of the current panel to a light blue

        JLabel responseTitle = new JLabel("NOPE"); // Create a label with text "NOPE"
        responseTitle.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the text
        responseTitle.setHorizontalAlignment(SwingConstants.CENTER); // Center the text horizontally
        responseTitle.setFont(Fonts.pepperoni_pizza); // Set the font of the label

        JEditorPane responseText = new JEditorPane(); // Create a new editor pane

        responseText.setOpaque(true); // Make the editor pane opaque
        //empty border (red)
        responseText.setBorder(BorderFactory.createLineBorder(Color.decode("#E3F2FD"), 5)); // Set the border color to light blue
        responseText.setBackground(Color.white); //for the actual box
        responseText.setEditable(false); // Make the editor pane non-editable
        responseText.setFocusable(false); // Make the editor pane non-focusable
        responseText.setContentType("text/html"); // Set the content type to HTML
        responseText.setMargin(new Insets(5,5,5,5)); // Set the margin

        responseText.setText( // Set the text of the editor pane
                "<html><body style='font-size: 15px; padding: 10px; font-family: Arial'>" +
                        "<b> MANAGER: </b>" + text +
                        "</body></html>"

        );

        JButton okButton = new JButton("OK"); // Create a button with text "OK"
        okButton.addActionListener(new ActionListener() { // Add an action listener to the button
            public void actionPerformed(ActionEvent e) { // Define the action to be performed
                rewardScreen(0); // Call the rewardScreen method with a parameter of 0 because nothing was won
            }
        });

        currentPanel.add(responseTitle); // Add the response title to the current panel
        currentPanel.add(responseText); // Add the response text to the current panel
        currentPanel.add(okButton); // Add the OK button to the current panel

        currentPanel.revalidate(); // Revalidate the current panel
        currentPanel.repaint(); // Repaint the current panel
    }

    public static void rewardScreen(int reward) {
        currentPanel.removeAll(); // Remove all components from the current panel
        currentPanel.setLayout(new GridLayout(3, 1)); // Set the layout of the current panel to a 3x1 grid
        currentPanel.setSize(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT); // Set the size of the current panel
        currentPanel.setBackground(Color.decode("#E3F2FD")); // Set the background color of the current panel to a light blue

        JLabel rewardTitle = new JLabel("REWARD"); // Create a label with text "REWARD"
        rewardTitle.setFont(Fonts.pepperoni_pizza); // Set the font of the label
        rewardTitle.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the text
        rewardTitle.setHorizontalAlignment(SwingConstants.CENTER); // Center the text horizontally

        JTextArea rewardText = new JTextArea(); // Create a new text area
        if (reward > 0) { // Check if the reward is greater than 0
            rewardText = new JTextArea("\n     You get a balance of " + reward + " added to your balance!"); // Set the text for a positive reward
            GameVars.balance += reward; // Add the reward to the balance
            AnnaTools.Updater.updateAllSidePanels(); // Update all side panels
        } else {
            rewardText = new JTextArea("\n     YOU LOSE 20 SANITY."); // Set the text for a negative reward
            GameVars.sanity -= 20; // Subtract 20 from the sanity
            AnnaTools.Updater.updateAllSidePanels(); // Update all side panels
        }
        rewardText.setLineWrap(true); // Enable line wrap
        rewardText.setWrapStyleWord(true); // Wrap lines at word boundaries
        rewardText.setBackground(Color.white); // Set the background color of the text area to white
        rewardText.setBorder(BorderFactory.createLineBorder(Color.decode("#E3F2FD"), 5)); // Set the border color to light blue
        rewardText.setFont(new Font("Arial Unicode MS", Font.PLAIN, 20)); // Set the font of the text area

        JButton exitButton = new JButton("Exit"); // Create a button with text "Exit"
        exitButton.addActionListener(new ActionListener() { // Add an action listener to the button
            public void actionPerformed(ActionEvent e) {
                hideQuest40(); // Call the hideQuest40 method when the button is clicked
            }
        });

        complete = true; // Set the complete flag to true
        currentPanel.add(rewardTitle); // Add the reward title to the current panel
        currentPanel.add(rewardText); // Add the reward text to the current panel
        currentPanel.add(exitButton); // Add the exit button to the current panel

        currentPanel.revalidate(); // Revalidate the current panel
        currentPanel.repaint(); // Repaint the current panel
    }

    /* TESTING
    public static void main(String[] args) {
        Quest40 quest40 = new Quest40();
        quest40.showQuest40();
    }*/
}
