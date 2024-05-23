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
        //currentPanel.setLayout(new GridLayout(3, 1));
        currentPanel.setSize(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT);
        currentPanel.setBackground(Color.decode("#FF0000"));

        JPanel expositionTitlePanel = new JPanel(new GridLayout(2, 1));
        JLabel expositionTitle = new JLabel("YOUR WORK");
        JLabel expositionTitle2 = new JLabel("BOSS");
        expositionTitle.setFont(Fonts.pepperoni_pizza);
        expositionTitle.setHorizontalAlignment(SwingConstants.CENTER);
        expositionTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        expositionTitle2.setFont(Fonts.pepperoni_pizza);
        expositionTitle2.setHorizontalAlignment(SwingConstants.CENTER);
        expositionTitle2.setAlignmentX(Component.CENTER_ALIGNMENT);
        expositionTitlePanel.add(expositionTitle);
        expositionTitlePanel.add(expositionTitle2);
        expositionTitle.setBackground(Color.decode("#FF0000")); //red
        expositionTitle2.setBackground(Color.decode("#FF0000")); //red
        expositionTitlePanel.setBackground(Color.decode("#FF0000")); //red
        expositionTitlePanel.setSize(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT/3);
        expositionTitlePanel.setPreferredSize(new Dimension(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT/3));

        JEditorPane expositionText = new JEditorPane();

        expositionText.setOpaque(true);
        //empty border (red)
        expositionText.setBorder(BorderFactory.createLineBorder(Color.decode("#FF0000"), 5));
        expositionText.setBackground(Color.white); //for the actual box
        expositionText.setEditable(false);
        expositionText.setFocusable(false);
        expositionText.setContentType("text/html");
        expositionText.setMargin(new Insets(5,5,5,5));

        expositionText.setText(

                "<html><body style='font-size: 15px; padding: 10px; font-family: Arial'>" +
                        "<b> MANAGER:</b> Where is your tie? I don’t see it. You work in" +
                        " an important facility and you need a tie." +
                        "</body></html>"

        );

        expositionText.setSize(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT/3);
        expositionText.setPreferredSize(new Dimension(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT/3-100));

        JButton option1 = new JButton("Stop it, you’re annoying and I’m not talking to you. Bye.");
        JButton option2 = new JButton("I lost it.");
        JButton option3 = new JButton("Bob took it.");
        option1.setPreferredSize(new Dimension(GameVars.WINDOWWIDTH, 83));


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

        currentPanel.add(expositionTitlePanel);
        currentPanel.add(expositionText);

        JPanel buttonPanel = new JPanel(new GridLayout(3, 1));
        buttonPanel.setBackground(Color.decode("#FF0000"));
        buttonPanel.add(option1);
        buttonPanel.add(option2);
        buttonPanel.add(option3);
        currentPanel.add(buttonPanel);

        currentPanel.revalidate();
        currentPanel.repaint();
    }

    public static void responseOne() {
        showResponse("Who do you think you are? I’m the manager around here and you can’t go on disrespecting me. I’m literally in charge of your salary and your place here. Get a tie and get back to work.");
    }

    public static void responseTwo() {
        showResponse("What? You’re so irresponsible. Get a new tie and get back to work. I won’t let this slide next time.");
    }

    public static void responseThree() {
        currentPanel.removeAll();
        currentPanel.setLayout(new GridLayout(3, 1));
        currentPanel.setSize(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT);
        currentPanel.setBackground(Color.decode("#E3F2FD"));


        JLabel responseTitle = new JLabel("WHAT");
        responseTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        responseTitle.setHorizontalAlignment(SwingConstants.CENTER);
        responseTitle.setFont(Fonts.pepperoni_pizza);

        JEditorPane responseText = new JEditorPane();

        responseText.setOpaque(true);
        responseText.setBorder(BorderFactory.createLineBorder(Color.decode("#E3F2FD"), 5));
        responseText.setBackground(Color.white); //for the actual box
        responseText.setEditable(false);
        responseText.setFocusable(false);
        responseText.setContentType("text/html");
        responseText.setMargin(new Insets(5,5,5,5));

        responseText.setText(

                "<html><body style='font-size: 15px; padding: 10px; font-family: Arial'>" +
                        "<b> MANAGER: </b>" + "WHY DID YOU LET HIM TAKE IT???? PROTECT YOUR TIE LIKE YOU ARE PROTECTING YOUR FIRST BORN. TAKE IT BACK NOW. " +
                        "</body></html>"
        );


        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { //FIGHT BOB!!!!!!!
                //======================================================================================================
                currentPanel.removeAll();
                currentPanel.setLayout(new GridLayout(4, 1));
                currentPanel.setSize(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT);
                currentPanel.setBackground(Color.decode("#E3F2FD"));

                JLabel fightTitle = new JLabel("DEFEAT BOB");
                fightTitle.setFont(Fonts.pepperoni_pizza);
                fightTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
                fightTitle.setHorizontalAlignment(SwingConstants.CENTER);

                JEditorPane fightDescription = new JEditorPane();
                fightDescription.setOpaque(true);
                fightDescription.setBorder(BorderFactory.createLineBorder(Color.decode("#E3F2FD"), 5));
                fightDescription.setBackground(Color.white); //for the actual box
                fightDescription.setEditable(false);
                fightDescription.setFocusable(false);
                fightDescription.setContentType("text/html");
                fightDescription.setMargin(new Insets(5,5,5,5));
                fightDescription.setText(

                        "<html><body style='font-size: 15px; padding: 10px; font-family: Arial'>" +
                                "<br>You find Bob wearing your tie. He knows he has done something wrong. Defeat Bob." +
                                "</body></html>"

                );

                JLabel bobHealthLabel = new JLabel("Bob's Health: 100");
                bobHealthLabel.setFont(new Font("Arial Unicode MS", Font.PLAIN, 30));
                bobHealthLabel.setHorizontalAlignment(SwingConstants.CENTER);
                bobHealthLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                bobHealthLabel.setBackground(Color.white);
                bobHealthLabel.setBorder(BorderFactory.createLineBorder(Color.decode("#E3F2FD"), 5));
                bobHealthLabel.setOpaque(true);


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
                            bobHealthLabel.setText("Bob's Health: " + bobHealth);
                            JOptionPane.showMessageDialog(quest40Frame, "In response to your attack, Bob attacks! You lose " + Math.max(0,(20 - GameVars.fullDefensePower)) + " health.", "Fight", JOptionPane.INFORMATION_MESSAGE);
                        }
                        AnnaTools.Updater.updateAllSidePanels();
                    }
                });

                currentPanel.add(fightTitle);
                currentPanel.add(fightDescription);
                currentPanel.add(bobHealthLabel);
                currentPanel.add(attackButton);

                currentPanel.revalidate();
                currentPanel.repaint();
                //======================================================================================================
            }
        });

        currentPanel.add(responseTitle);
        currentPanel.add(responseText);
        currentPanel.add(okButton);

        currentPanel.revalidate();
        currentPanel.repaint();
    }



    public static void showResponse(String text) {
        currentPanel.removeAll();
        currentPanel.setLayout(new GridLayout(3, 1));
        currentPanel.setSize(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT);
        currentPanel.setBackground(Color.decode("#E3F2FD"));

        JLabel responseTitle = new JLabel("NOPE");
        responseTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        responseTitle.setHorizontalAlignment(SwingConstants.CENTER);
        responseTitle.setFont(Fonts.pepperoni_pizza);

        JEditorPane responseText = new JEditorPane();

        responseText.setOpaque(true);
        //empty border (red)
        responseText.setBorder(BorderFactory.createLineBorder(Color.decode("#E3F2FD"), 5));
        responseText.setBackground(Color.white); //for the actual box
        responseText.setEditable(false);
        responseText.setFocusable(false);
        responseText.setContentType("text/html");
        responseText.setMargin(new Insets(5,5,5,5));

        responseText.setText(

                "<html><body style='font-size: 15px; padding: 10px; font-family: Arial'>" +
                        "<b> MANAGER: </b>" + text +
                        "</body></html>"

        );

        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                rewardScreen(0);
            }
        });

        currentPanel.add(responseTitle);
        currentPanel.add(responseText);
        currentPanel.add(okButton);

        currentPanel.revalidate();
        currentPanel.repaint();
    }

    public static void rewardScreen(int reward) {
        currentPanel.removeAll();
        currentPanel.setLayout(new GridLayout(3, 1));
        currentPanel.setSize(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT);
        currentPanel.setBackground(Color.decode("#E3F2FD"));

        JLabel rewardTitle = new JLabel("REWARD");
        rewardTitle.setFont(Fonts.pepperoni_pizza);
        rewardTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        rewardTitle.setHorizontalAlignment(SwingConstants.CENTER);

        JTextArea rewardText = new JTextArea();
        if (reward > 0) {
            rewardText = new JTextArea("\n     You get a balance of " + reward + " added to your balance!");
            GameVars.balance += reward;
            AnnaTools.Updater.updateAllSidePanels();
        } else {
            rewardText = new JTextArea("\n     YOU LOSE 20 SANITY.");
            GameVars.sanity -= 20;
            AnnaTools.Updater.updateAllSidePanels();
        }
        rewardText.setLineWrap(true);
        rewardText.setWrapStyleWord(true);
        rewardText.setBackground(Color.white);
        rewardText.setBorder(BorderFactory.createLineBorder(Color.decode("#E3F2FD"), 5));
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
