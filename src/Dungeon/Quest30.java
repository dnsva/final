package Dungeon;

//IMPORT ALL PACKAGES -----
import java.io.*;       //-
import java.util.*;     //-
import java.awt.*;      //-
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;   //-
import Game.*;          //-
import Items.*;         //-
import Monsters.*;      //-
import Shops.*;         //-
import AnnaTools.*;     //-

import static Dungeon.Map.dealWithMapLevelCompletion;
//-------------------------

public class Quest30 {

    public static JFrame quest30Frame = new JFrame();
    public static SideBar quest30SideBar = new SideBar();
    public static boolean complete = false;

    public static JPanel currentPanel;

    public Quest30() {
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
        currentPanel.setLayout(new GridLayout(3, 1));
        currentPanel.setSize(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT);
        currentPanel.setBackground(Color.decode("#C2F9BB"));

        JLabel expositionTitle = new JLabel("A DUDE THAT SPEAKS WITH ANIMALS");

        JTextArea expositionText = new JTextArea("A harmless looking guy walks up to you looking for directions. As this is your first time in this dungeon, you explain that you don’t know. For some reason, he gets really mad. He starts speaking a weird language. Turns out, he decided to call for his goblin friend to kill you. You have no choice but to fight back.");
        expositionText.setLineWrap(true);
        expositionText.setWrapStyleWord(true);
        expositionText.setBackground(Color.decode("#C2F9BB"));

        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                goblinFight();
            }
        });

        currentPanel.add(expositionTitle);
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
        currentPanel.setLayout(new GridLayout(4, 1));
        currentPanel.setSize(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT);
        currentPanel.setBackground(Color.decode("#C2F9BB"));

        JLabel fightTitle = new JLabel("Defeat the " + enemy + ". " + enemy + " has an attack power of " + attackPower + ".");
        fightTitle.setFont(new Font("Arial Unicode MS", Font.PLAIN, 20));

        JLabel healthLabel = new JLabel("Current " + enemy + " Health: " + health);
        healthLabel.setFont(new Font("Arial Unicode MS", Font.PLAIN, 20));

        JButton attackButton = new JButton("ATTACK");
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
                    healthLabel.setText("Current " + enemy + " Health: " + enemyHealth);
                    JOptionPane.showMessageDialog(quest30Frame, "In response to your attack, the " + enemy + " attacks! You lose " + Math.max(0, (attackPower - GameVars.fullDefensePower)) + " health.", "Fight", JOptionPane.INFORMATION_MESSAGE);
                }
                AnnaTools.Updater.updateAllSidePanels();
            }
        });

        currentPanel.add(fightTitle);
        currentPanel.add(healthLabel);
        currentPanel.add(new JLabel(""));
        currentPanel.add(attackButton);

        currentPanel.revalidate();
        currentPanel.repaint();
    }

    public static void rewardScreen(int reward) {
        currentPanel.removeAll();
        currentPanel.setLayout(new GridLayout(3, 1));
        currentPanel.setSize(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT);
        currentPanel.setBackground(Color.decode("#C2F9BB"));

        JLabel rewardTitle = new JLabel("REWARD");
        rewardTitle.setFont(new Font("Arial Unicode MS", Font.PLAIN, 30));

        JTextArea rewardText;
        if (reward > 0) {
            rewardText = new JTextArea("REWARD, GAME SAVED. You get a balance of " + reward + " added to your balance!");
            GameVars.balance += reward;
            AnnaTools.Updater.updateAllSidePanels();
        } else {
            rewardText = new JTextArea("NO REWARD, GAME SAVED.");
            AnnaTools.Updater.updateAllSidePanels();
        }
        rewardText.setLineWrap(true);
        rewardText.setWrapStyleWord(true);
        rewardText.setBackground(Color.decode("#C2F9BB"));
        rewardText.setFont(new Font("Arial Unicode MS", Font.PLAIN, 20));

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                hideQuest30();
            }
        });

        complete = true;
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
