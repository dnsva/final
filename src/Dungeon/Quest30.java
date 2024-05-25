package Dungeon;

//IMPORT ALL PACKAGES -----
import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import Game.*;
import Items.*;
import Monsters.*;
import Shops.*;
import AnnaTools.*;

import static Dungeon.Map.dealWithMapLevelCompletion;
//-------------------------

public class Quest30 {

    public static JFrame quest30Frame = new JFrame();
    public static SideBar quest30SideBar = new SideBar();
    public static boolean complete = false;

    public static JPanel currentPanel;

    public Quest30() {

//        new Fonts(); //FOR TESTING

        quest30Frame = new JFrame("By Anna Denisova");
        quest30Frame.setSize(GameVars.WINDOWWIDTH + GameVars.SIDEBARWIDTH, GameVars.WINDOWHEIGHT);
        quest30Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        quest30Frame.setLocationRelativeTo(null);
        quest30Frame.getContentPane().setLayout(new BorderLayout());

        currentPanel = new JPanel();
        quest30SideBar = new SideBar();
        quest30Frame.add(quest30SideBar.getPanel(), BorderLayout.EAST);
        quest30Frame.add(currentPanel, BorderLayout.CENTER);

        exposition();
    }

    public static void showQuest30() {
        quest30Frame.setVisible(true);
    }

    public static void hideQuest30() {
        dealWithMapLevelCompletion(30);
        quest30Frame.setVisible(false);
        MapGUI.showMapGUI();
    }

    public static void exposition() {
        currentPanel.removeAll();
        currentPanel.setSize(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT);
        currentPanel.setBackground(Color.decode("#C2F9BB"));

        JPanel expositionTitlePanel = new JPanel(new GridLayout(2, 1));
        JLabel expositionTitle = new JLabel("A Guy That Speaks");
        expositionTitle.setFont(Fonts.dTheHero);
        expositionTitle.setHorizontalAlignment(SwingConstants.CENTER);
        expositionTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel expositionTitle2 = new JLabel("With Animals");
        expositionTitle2.setFont(Fonts.dTheHero);
        expositionTitle2.setHorizontalAlignment(SwingConstants.CENTER);
        expositionTitle2.setAlignmentX(Component.CENTER_ALIGNMENT);

        expositionTitlePanel.add(expositionTitle);
        expositionTitlePanel.add(expositionTitle2);
        expositionTitlePanel.setBackground(Color.decode("#C2F9BB"));
        expositionTitlePanel.setSize(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT / 3);
        expositionTitlePanel.setPreferredSize(new Dimension(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT / 3));

        JEditorPane expositionText = new JEditorPane();
        expositionText.setOpaque(true);
        expositionText.setBorder(BorderFactory.createLineBorder(Color.decode("#C2F9BB"), 5));
        expositionText.setBackground(Color.white);
        expositionText.setEditable(false);
        expositionText.setFocusable(false);
        expositionText.setContentType("text/html");
        expositionText.setMargin(new Insets(5, 5, 5, 5));
        expositionText.setText(
                "<html><body style='font-size: 15px; padding: 10px; font-family: Arial'>" +
                        "A harmless looking guy walks up to you looking for directions. As this is your first time in this dungeon, you explain that you don’t know. For some reason, he gets really mad. He starts speaking a weird language. Turns out, he decided to call for his goblin friend to kill you. You have no choice but to fight back." +
                        "</body></html>"
        );

        JButton okButton = new JButton("OK");
        okButton.setPreferredSize(new Dimension(GameVars.WINDOWWIDTH, 50));
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                goblinFight();
            }
        });

        currentPanel.setLayout(new GridLayout(3, 1));
        currentPanel.add(expositionTitlePanel);
        currentPanel.add(expositionText);
        currentPanel.add(okButton);

        currentPanel.revalidate();
        currentPanel.repaint();
    }

    public static void goblinFight() {
        fight("Goblin", 30, 100, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                phaseTwo();
            }
        });
    }

    public static void phaseTwo() {
        fight("Guy", 20, 100, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                rewardScreen(10);
            }
        });
    }

    public static void fight(String enemy, int attackPower, int health, ActionListener onVictory) {
        currentPanel.removeAll();
        currentPanel.setSize(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT);
        currentPanel.setBackground(Color.decode("#C2F9BB"));

        JLabel fightTitle = new JLabel("FIGHT!");
        fightTitle.setFont(Fonts.dTheHero);
        fightTitle.setHorizontalAlignment(SwingConstants.CENTER);
        fightTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel fightDescription = new JLabel("   Defeat the " + enemy + ". He has as an attack power of " + attackPower + ".");
        fightDescription.setBackground(Color.white);
        fightDescription.setOpaque(true);
        fightDescription.setBorder(BorderFactory.createLineBorder(Color.decode("#C2F9BB"), 5));
        fightDescription.setFont(new Font("Arial", Font.PLAIN, 20));

        JEditorPane healthLabel = new JEditorPane();
        healthLabel.setOpaque(true);
        healthLabel.setBorder(BorderFactory.createLineBorder(Color.decode("#C2F9BB"), 5));
        healthLabel.setBackground(Color.white);
        healthLabel.setEditable(false);
        healthLabel.setFocusable(false);
        healthLabel.setContentType("text/html");
        healthLabel.setMargin(new Insets(5, 5, 5, 5));
        healthLabel.setText("<html><body style='font-size: 20px; padding: 10px; font-family: Arial'>" +
                "<br> Current " + enemy + " Health: " + health +
                "</body></html>");

        JButton attackButton = new JButton("ATTACK");
        attackButton.setPreferredSize(new Dimension(GameVars.WINDOWWIDTH, 50));
        attackButton.addActionListener(new ActionListener() {
            int enemyHealth = health;

            public void actionPerformed(ActionEvent e) {
                if (!GameVars.currWeapon.attackMiss()) {
                    enemyHealth -= GameVars.fullAttackPower;
                    JOptionPane.showMessageDialog(quest30Frame, "You attacked the " + enemy + " and dealt " + GameVars.fullAttackPower + " damage.", "Fight", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(quest30Frame, "You tried to attack but you missed.", "Fight", JOptionPane.INFORMATION_MESSAGE);
                }

                GameVars.health -= Math.max(0, (attackPower - GameVars.fullDefensePower));
                AnnaTools.Updater.updateAllSidePanels();

                if (enemyHealth <= 0) {
                    onVictory.actionPerformed(e);
                } else {
                    healthLabel.setText("<html><body style='font-size: 20px; padding: 10px; font-family: Arial'>" +
                            "Current " + enemy + " Health: " + enemyHealth +
                            "</body></html>");
                    JOptionPane.showMessageDialog(quest30Frame, "In response to your attack, the " + enemy + " attacks! You lose " + Math.max(0, (attackPower - GameVars.fullDefensePower)) + " health.", "Fight", JOptionPane.INFORMATION_MESSAGE);
                }
                AnnaTools.Updater.updateAllSidePanels();
            }
        });

        currentPanel.setLayout(new GridLayout(4, 1));
        currentPanel.add(fightTitle);
        currentPanel.add(fightDescription);
        currentPanel.add(healthLabel);
        //currentPanel.add(new JLabel("")); // Placeholder for spacing
        currentPanel.add(attackButton);

        currentPanel.revalidate();
        currentPanel.repaint();
    }

    public static void rewardScreen(int reward) {
        currentPanel.removeAll();
        currentPanel.setSize(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT);
        currentPanel.setBackground(Color.decode("#C2F9BB"));

        JLabel rewardTitle = new JLabel("REWARD");
        rewardTitle.setFont(Fonts.dTheHero);
        rewardTitle.setHorizontalAlignment(SwingConstants.CENTER);
        rewardTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JEditorPane rewardText = new JEditorPane();
        rewardText.setOpaque(true);
        rewardText.setBorder(BorderFactory.createLineBorder(Color.decode("#C2F9BB"), 5));
        rewardText.setBackground(Color.white);
        rewardText.setEditable(false);
        rewardText.setFocusable(false);
        rewardText.setContentType("text/html");
        rewardText.setMargin(new Insets(5, 5, 5, 5));
        if (reward > 0) {
            rewardText.setText(
                    "<html><body style='font-size: 20px; padding: 10px; font-family: Arial'>" +
                            "REWARD, GAME SAVED. You get a balance of " + reward + " added to your balance!" +
                            "</body></html>"
            );
            GameVars.balance += reward;
            AnnaTools.Updater.updateAllSidePanels();
        } else {
            rewardText.setText(
                    "<html><body style='font-size: 20px; padding: 10px; font-family: Arial'>" +
                            "NO REWARD, GAME SAVED." +
                            "</body></html>"
            );
            AnnaTools.Updater.updateAllSidePanels();
        }

        JButton exitButton = new JButton("Exit");
        exitButton.setPreferredSize(new Dimension(GameVars.WINDOWWIDTH, 50));
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                hideQuest30();
            }
        });

        complete = true;
        currentPanel.setLayout(new GridLayout(3, 1));
        currentPanel.add(rewardTitle);
        currentPanel.add(rewardText);
        currentPanel.add(exitButton);

        currentPanel.revalidate();
        currentPanel.repaint();
    }

    public static void main(String[] args) {
        Quest30 quest30 = new Quest30();
        quest30.showQuest30();
    }
}
