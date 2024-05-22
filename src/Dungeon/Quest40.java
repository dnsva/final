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

public class Quest40 {

    public static JFrame quest40Frame = new JFrame();
    public static SideBar quest40SideBar = new SideBar();
    public static boolean complete = false;

    public static JPanel currentPanel;

    public Quest40() {
        quest40Frame = new JFrame("By Anna Denisova");
        quest40Frame.setSize(GameVars.WINDOWWIDTH + GameVars.SIDEBARWIDTH, GameVars.WINDOWHEIGHT);
        quest40Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        quest40Frame.setLocationRelativeTo(null);
        quest40Frame.getContentPane().setLayout(new BorderLayout());

        currentPanel = new JPanel();
        quest40SideBar = new SideBar();
        quest40Frame.add(quest40SideBar.getPanel(), BorderLayout.EAST);
        quest40Frame.add(currentPanel, BorderLayout.CENTER);

        exposition();
    }

    public static void showQuest40() {
        quest40Frame.setVisible(true);
    }

    public static void hideQuest40() {
        dealWithMapLevelCompletion(40);
        quest40Frame.setVisible(false);
        MapGUI.showMapGUI();
    }

    public static void exposition() {
        currentPanel.removeAll();
        currentPanel.setLayout(new GridLayout(3, 1));
        currentPanel.setSize(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT);
        currentPanel.setBackground(Color.decode("#C2F9BB"));

        JLabel expositionTitle = new JLabel("YOUR WORK BOSS");
        JLabel imageLabel = new JLabel(new ImageIcon("managerTieGlasses.png"));

        JTextArea expositionText = new JTextArea("Manager: Where is YOUR tie? I don’t see it. You work in an important facility and you need a tie.");
        expositionText.setLineWrap(true);
        expositionText.setWrapStyleWord(true);
        expositionText.setBackground(Color.decode("#C2F9BB"));

        JButton option1 = new JButton("Stop it, you’re annoying and I’m not talking to you. Bye.");
        JButton option2 = new JButton("I lost it.");
        JButton option3 = new JButton("Bob took it.");

        option1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                responseOne();
            }
        });

        option2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                responseTwo();
            }
        });

        option3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                responseThree();
            }
        });

        currentPanel.add(expositionTitle);
        currentPanel.add(imageLabel);
        currentPanel.add(expositionText);

        JPanel buttonPanel = new JPanel(new GridLayout(3, 1));
        buttonPanel.setBackground(Color.decode("#C2F9BB"));
        buttonPanel.add(option1);
        buttonPanel.add(option2);
        buttonPanel.add(option3);
        currentPanel.add(buttonPanel);

        currentPanel.revalidate();
        currentPanel.repaint();
    }

    public static void responseOne() {
        showResponse("Manager: Who do you think you are? I’m the manager around here and you can’t go on disrespecting me. I’m literally in charge of your salary and your place here. Get a tie and get back to work.", -20);
    }

    public static void responseTwo() {
        showResponse("Manager: What? You’re so irresponsible. Get a new tie and get back to work. I won’t let this slide next time.", 0);
    }

    public static void responseThree() {
        currentPanel.removeAll();
        currentPanel.setLayout(new GridLayout(3, 1));
        currentPanel.setSize(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT);
        currentPanel.setBackground(Color.decode("#C2F9BB"));

        JLabel responseTitle = new JLabel("Manager: WHY DID YOU LET HIM TAKE IT???? PROTECT YOUR TIE LIKE YOU ARE PROTECTING YOUR FIRST BORN. TAKE IT BACK NOW.");
        responseTitle.setFont(new Font("Arial Unicode MS", Font.PLAIN, 20));

        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fightBob();
            }
        });

        currentPanel.add(responseTitle);
        currentPanel.add(new JLabel(""));
        currentPanel.add(okButton);

        currentPanel.revalidate();
        currentPanel.repaint();
    }

    public static void fightBob() {
        currentPanel.removeAll();
        currentPanel.setLayout(new GridLayout(4, 1));
        currentPanel.setSize(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT);
        currentPanel.setBackground(Color.decode("#C2F9BB"));

        JLabel fightTitle = new JLabel("Defeat Bob. You find Bob and see that he is wearing your tie. What do you do?");
        fightTitle.setFont(new Font("Arial Unicode MS", Font.PLAIN, 20));

        JLabel bobHealthLabel = new JLabel("Current Bob Health: 100");
        bobHealthLabel.setFont(new Font("Arial Unicode MS", Font.PLAIN, 20));

        JButton attackButton = new JButton("ATTACK");
        attackButton.addActionListener(new ActionListener() {
            int bobHealth = 100;

            public void actionPerformed(ActionEvent e) {

                if(!GameVars.currWeapon.attackMiss()){
                    bobHealth -= GameVars.fullAttackPower;
                    JOptionPane.showMessageDialog(quest40Frame, "You attacked Bob and dealt " + GameVars.fullAttackPower + " damage.", "Fight", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(quest40Frame, "You tried to attack but you missed.", "Fight", JOptionPane.INFORMATION_MESSAGE);
                }

                GameVars.health -= Math.max(0, (20 - GameVars.fullDefensePower));
                AnnaTools.Updater.updateAllSidePanels();

                if (bobHealth <= 0) {
                    rewardScreen(10);
                } else {
                    bobHealthLabel.setText("Current Bob Health: " + bobHealth);
                    JOptionPane.showMessageDialog(quest40Frame, "In response to your attack, Bob attacks! You lose " + Math.max(0,(20 - GameVars.fullDefensePower)) + " health.", "Fight", JOptionPane.INFORMATION_MESSAGE);
                }
                AnnaTools.Updater.updateAllSidePanels();
            }
        });

        currentPanel.add(fightTitle);
        currentPanel.add(bobHealthLabel);
        currentPanel.add(new JLabel(""));
        currentPanel.add(attackButton);

        currentPanel.revalidate();
        currentPanel.repaint();
    }

    public static void showResponse(String text, int sanityChange) {
        currentPanel.removeAll();
        currentPanel.setLayout(new GridLayout(3, 1));
        currentPanel.setSize(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT);
        currentPanel.setBackground(Color.decode("#C2F9BB"));

        JLabel responseTitle = new JLabel(text);
        responseTitle.setFont(new Font("Arial Unicode MS", Font.PLAIN, 20));

        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //GameVars.sanity += sanityChange;
                //AnnaTools.Updater.updateAllSidePanels();
                rewardScreen(0);
            }
        });

        currentPanel.add(responseTitle);
        currentPanel.add(new JLabel(""));
        currentPanel.add(okButton);

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
            rewardText = new JTextArea("NO REWARD, GAME SAVED. YOU LOSE 20 SANITY.");
            GameVars.sanity -= 20;
            AnnaTools.Updater.updateAllSidePanels();
        }
        rewardText.setLineWrap(true);
        rewardText.setWrapStyleWord(true);
        rewardText.setBackground(Color.decode("#C2F9BB"));
        rewardText.setFont(new Font("Arial Unicode MS", Font.PLAIN, 20));

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                hideQuest40();
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
        Quest40 quest40 = new Quest40();
        quest40.showQuest40();
    }
}
