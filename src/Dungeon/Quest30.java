package Dungeon;

//IMPORT ALL PACKAGES -----
import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import Game.*;
import AnnaTools.*;

import static Dungeon.Map.dealWithMapLevelCompletion;
import static Game.GameOver.checkGameOver;
//-------------------------

public class Quest30 {

    public static JFrame quest30Frame = new JFrame();
    public static SideBar quest30SideBar = new SideBar();
    public static boolean complete = false;

    public static JPanel currentPanel;

    public Quest30() {

//        new Fonts(); //FOR TESTING

        quest30Frame = new JFrame("By Anna Denisova"); //title
        quest30Frame.setSize(GameVars.WINDOWWIDTH + GameVars.SIDEBARWIDTH, GameVars.WINDOWHEIGHT); //size
        quest30Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //set closing setting
        quest30Frame.setLocationRelativeTo(null); //location in middle
        quest30Frame.getContentPane().setLayout(new BorderLayout()); //layout

        currentPanel = new JPanel(); //initialize
        quest30SideBar = new SideBar(); //initialize
        quest30Frame.add(quest30SideBar.getPanel(), BorderLayout.EAST); //set to the side
        quest30Frame.add(currentPanel, BorderLayout.CENTER); //set location to other side

        exposition(); //first window with the exposition call
    }

    public static void showQuest30() { //show the frame
        quest30Frame.setVisible(true); //show the frame
    }

    public static void hideQuest30() { //hide the frame
        dealWithMapLevelCompletion(30); //use the map class function
        quest30Frame.setVisible(false); //make the frame invisible
        MapGUI.showMapGUI(); //make the map visible
    }

    public static void exposition() { //the first "window" encountered
        currentPanel.removeAll(); //clear everything off
        currentPanel.setSize(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT); //set the size
        currentPanel.setBackground(Color.decode("#C2F9BB")); //make it light green

        JPanel expositionTitlePanel = new JPanel(new GridLayout(2, 1)); //2 rows, 1 col
        JLabel expositionTitle = new JLabel("A Guy That Speaks"); //title
        expositionTitle.setFont(Fonts.dTheHero); //custom font for quest 30
        expositionTitle.setHorizontalAlignment(SwingConstants.CENTER); //center the text
        expositionTitle.setAlignmentX(Component.CENTER_ALIGNMENT); //center the text

        JLabel expositionTitle2 = new JLabel("With Animals"); //title after the first title
        expositionTitle2.setFont(Fonts.dTheHero); //custom font for quest 30
        expositionTitle2.setHorizontalAlignment(SwingConstants.CENTER); //center the text
        expositionTitle2.setAlignmentX(Component.CENTER_ALIGNMENT); //center the text

        expositionTitlePanel.add(expositionTitle); //row 1
        expositionTitlePanel.add(expositionTitle2); //row 2
        expositionTitlePanel.setBackground(Color.decode("#C2F9BB")); //light green background of course
        expositionTitlePanel.setSize(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT / 3); //set the size
        expositionTitlePanel.setPreferredSize(new Dimension(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT / 3)); //set the size

        JEditorPane expositionText = new JEditorPane(); //this is the monologue
        expositionText.setOpaque(true); //for color visibility
        expositionText.setBorder(BorderFactory.createLineBorder(Color.decode("#C2F9BB"), 5)); //light green outline
        expositionText.setBackground(Color.white); //white background
        expositionText.setEditable(false); //not editable
        expositionText.setFocusable(false); //not clickable
        expositionText.setContentType("text/html"); //for html formatting
        expositionText.setMargin(new Insets(5, 5, 5, 5)); //so that there is space around the text
        /* the following code outlines the text in html formatting */
        expositionText.setText(
                "<html><body style='font-size: 15px; padding: 10px; font-family: Arial'>" +
                        "A harmless looking guy walks up to you looking for directions. As this is your first time in this dungeon, you explain that you don’t know. For some reason, he gets really mad. He starts speaking a weird language. Turns out, he decided to call for his goblin friend to kill you. You have no choice but to fight back." +
                        "</body></html>"
        );

        JButton okButton = new JButton("OK"); //for moving on
        okButton.setPreferredSize(new Dimension(GameVars.WINDOWWIDTH, 50)); //set the size
        okButton.addActionListener(new ActionListener() { //when the OK button is pressed...
            public void actionPerformed(ActionEvent e) { //on button press... (this is an override)
                goblinFight(); //go to the goblin fight
            }
        });

        currentPanel.setLayout(new GridLayout(3, 1)); //3 rows, 1 col
        currentPanel.add(expositionTitlePanel); //add the title panel
        currentPanel.add(expositionText); //add the monologue
        currentPanel.add(okButton); //add the OK button

        currentPanel.revalidate(); //refresh the panel
        currentPanel.repaint(); //refresh the panel
    }

    public static void goblinFight() { //the goblin fight
        fight("Goblin", 30, 100, new ActionListener() { //call the fight function
            public void actionPerformed(ActionEvent e) { //on victory...
                phaseTwo(); //go to phase two
            }
        });
    }

    public static void phaseTwo() { //phase two fight
        fight("Guy", 20, 100, new ActionListener() { //call the fight function
            public void actionPerformed(ActionEvent e) { //on victory...
                rewardScreen(10); //show the reward screen with a reward of 10
            }
        });
    }

    public static void fight(String enemy, int attackPower, int health, ActionListener onVictory) { //make a fight based off params
        currentPanel.removeAll(); //clear everything off
        currentPanel.setSize(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT); //set the size
        currentPanel.setBackground(Color.decode("#C2F9BB")); //set the background color light green

        JLabel fightTitle = new JLabel("FIGHT!"); //the fight title
        fightTitle.setFont(Fonts.dTheHero); //custom font for quest 30
        fightTitle.setHorizontalAlignment(SwingConstants.CENTER); //center the text
        fightTitle.setAlignmentX(Component.CENTER_ALIGNMENT); //center the text

        JLabel fightDescription = new JLabel("   Defeat the " + enemy + ". He has as an attack power of " + attackPower + "."); //the fight description
        fightDescription.setBackground(Color.white); //white background
        fightDescription.setOpaque(true); //for color visibility
        fightDescription.setBorder(BorderFactory.createLineBorder(Color.decode("#C2F9BB"), 5)); //light green outline
        fightDescription.setFont(new Font("Arial", Font.PLAIN, 20)); //set the font. make it normal not custom

        JEditorPane healthLabel = new JEditorPane(); //the health label
        healthLabel.setOpaque(true); //for color visibility
        healthLabel.setBorder(BorderFactory.createLineBorder(Color.decode("#C2F9BB"), 5)); //light green outline
        healthLabel.setBackground(Color.white); //white background
        healthLabel.setEditable(false); //not editable
        healthLabel.setFocusable(false); //not clickable
        healthLabel.setContentType("text/html"); //for html formatting
        healthLabel.setMargin(new Insets(5, 5, 5, 5)); //so that there is space around the text
        healthLabel.setText("<html><body style='font-size: 20px; padding: 10px; font-family: Arial'>" +
                "<br> Current " + enemy + " Health: " + health +
                "</body></html>"); //set the text

        JButton attackButton = new JButton("ATTACK"); //the attack button
        attackButton.setPreferredSize(new Dimension(GameVars.WINDOWWIDTH, 50)); //set the size
        attackButton.addActionListener(new ActionListener() { //when the attack button is pressed...
            int enemyHealth = health; //initialize enemy health

            public void actionPerformed(ActionEvent e) { //on attack press
                if (!GameVars.currWeapon.attackMiss()) { //calculate if miss
                    enemyHealth -= GameVars.fullAttackPower; //if not missed damage the enemy
                    //message:
                    JOptionPane.showMessageDialog(quest30Frame, "You attacked the " + enemy + " and dealt " + GameVars.fullAttackPower + " damage.", "Fight", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    //message:
                    JOptionPane.showMessageDialog(quest30Frame, "You tried to attack but you missed.", "Fight", JOptionPane.INFORMATION_MESSAGE);
                }

                GameVars.health -= Math.max(0, (attackPower - GameVars.fullDefensePower)); //reduce health
                AnnaTools.Updater.updateAllSidePanels(); //update sidebar

                if (enemyHealth <= 0) { //if enemy health is less than or equal to 0
                    onVictory.actionPerformed(e); //call the on victory function
                } else {
                    healthLabel.setText("<html><body style='font-size: 20px; padding: 10px; font-family: Arial'>" +
                            "Current " + enemy + " Health: " + enemyHealth +
                            "</body></html>"); //the enemy attacks
                    //response message:
                    JOptionPane.showMessageDialog(quest30Frame, "In response to your attack, the " + enemy + " attacks! You lose " + Math.max(0, (attackPower - GameVars.fullDefensePower)) + " health.", "Fight", JOptionPane.INFORMATION_MESSAGE);
                }
                checkGameOver(); //check if you died from the enemny
                AnnaTools.Updater.updateAllSidePanels(); //update all stats
            }
        });

        currentPanel.setLayout(new GridLayout(4, 1)); //4 rows, 1 col
        currentPanel.add(fightTitle); //add the fight title
        currentPanel.add(fightDescription); //add the fight description
        currentPanel.add(healthLabel); //add the health label
        currentPanel.add(attackButton); //add the attack button

        currentPanel.revalidate(); //refresh the panel
        currentPanel.repaint(); //refresh the panel
    }

    public static void rewardScreen(int reward) { //YAY!!!!!!!
        currentPanel.removeAll(); //clear everything off
        currentPanel.setSize(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT); //set the size
        currentPanel.setBackground(Color.decode("#C2F9BB")); //set the background color light green

        JLabel rewardTitle = new JLabel("REWARD"); //the reward title
        rewardTitle.setFont(Fonts.dTheHero); //custom font for quest 30
        rewardTitle.setHorizontalAlignment(SwingConstants.CENTER); //center the text
        rewardTitle.setAlignmentX(Component.CENTER_ALIGNMENT); //center the text

        JEditorPane rewardText = new JEditorPane(); //the reward text
        rewardText.setOpaque(true); //for color visibility
        rewardText.setBorder(BorderFactory.createLineBorder(Color.decode("#C2F9BB"), 5)); //light green outline
        rewardText.setBackground(Color.white); //white background
        rewardText.setEditable(false); //not editable
        rewardText.setFocusable(false); //not clickable
        rewardText.setContentType("text/html"); //for html formatting
        rewardText.setMargin(new Insets(5, 5, 5, 5)); //so that there is space around the text
        if (reward > 0) { //if there is a reward
            rewardText.setText(
                    "<html><body style='font-size: 20px; padding: 10px; font-family: Arial'>" +
                            "REWARD, GAME SAVED. You get a balance of " + reward + " added to your balance!" +
                            "</body></html>"
            ); //set the text
            GameVars.balance += reward; //add the reward to the balance
            AnnaTools.Updater.updateAllSidePanels(); //update the sidebar
        } else {
            rewardText.setText(
                    "<html><body style='font-size: 20px; padding: 10px; font-family: Arial'>" +
                            "NO REWARD, GAME SAVED." +
                            "</body></html>"
            ); //set the text
            AnnaTools.Updater.updateAllSidePanels(); //update the sidebar
        }

        JButton exitButton = new JButton("Exit"); //the exit button
        exitButton.setPreferredSize(new Dimension(GameVars.WINDOWWIDTH, 50)); //set the size
        exitButton.addActionListener(new ActionListener() { //when the exit button is pressed...
            public void actionPerformed(ActionEvent e) { //on exit press
                hideQuest30(); //hide the quest 30
            }
        });

        complete = true;    //set the quest to complete
        currentPanel.setLayout(new GridLayout(3, 1)); //3 rows, 1 col
        currentPanel.add(rewardTitle); //add the reward title
        currentPanel.add(rewardText); //add the reward text
        currentPanel.add(exitButton); //add the exit button

        currentPanel.revalidate(); //refresh the panel
        currentPanel.repaint(); //refresh the panel
    }

    /* TESTING
    public static void main(String[] args) {
        Quest30 quest30 = new Quest30();
        quest30.showQuest30();
    }*/
}
