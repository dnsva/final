

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


public class Quest50{

    public static JFrame quest50Frame = new JFrame();
    public static SideBar quest50SideBar = new SideBar();
    public static boolean complete = false;

    public static JPanel currentPanel;

    public Quest50(){
        quest50Frame = new JFrame("By Anna Denisova");
        quest50Frame.setSize(GameVars.WINDOWWIDTH + GameVars.SIDEBARWIDTH, GameVars.WINDOWHEIGHT);
        quest50Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        quest50Frame.setLocationRelativeTo(null);
        quest50Frame.getContentPane().setLayout(new BorderLayout());

        currentPanel = new JPanel();
        quest50SideBar = new SideBar();
        quest50Frame.add(quest50SideBar.getPanel(), BorderLayout.EAST);
        quest50Frame.add(currentPanel, BorderLayout.CENTER);

       // encounter();
    }

    public static void showQuest50(){
        quest50Frame.setVisible(true);
        encounter();
    }
    public static void hideQuest50(){
        dealWithMapLevelCompletion(50);
        quest50Frame.setVisible(false);
        MapGUI.showMapGUI();
    }

    public static void encounter(){

        //new Fonts(); //FOR TESTING REMOVE THIS AFTER

        currentPanel.removeAll();
        currentPanel.setLayout(new GridLayout(3, 1));
        currentPanel.setSize(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT);
        currentPanel.setBackground(Color.decode("#FF0000"));

        JLabel encounterTitle = new JLabel("THE MASTER");
        encounterTitle.setFont(Fonts.BAD_GRUNGE);
        encounterTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        encounterTitle.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel encounterTitle2 = new JLabel("BAKER");
        encounterTitle2.setFont(Fonts.BAD_GRUNGE);
        encounterTitle2.setAlignmentX(Component.CENTER_ALIGNMENT);
        encounterTitle2.setHorizontalAlignment(SwingConstants.CENTER);

        JEditorPane encounterText = new JEditorPane();

        encounterText.setEditable(false);
        encounterText.setFocusable(false);
        encounterText.setContentType("text/html");

        encounterText.setText(

                "<html><body style='font-size: 15px; padding: 10px; font-family: Arial'>" +
                        "<b> BAKER:</b> WHAT ARE YOU DOING IN MY DUNGEON? GO BACK TO WHERE YOU CAME FROM! " +
                        "Actually, on second thought, I challenge you to a test. You can only pass me when you prove yourself. " +
                        "Answer incorrectly and you’ll regret ever coming here." +
                        "</body></html>"

        );

        //make room around text:
        encounterText.setMargin(new Insets(5,5,5,5));
        encounterText.setBorder(BorderFactory.createLineBorder(Color.decode("#FF0000"), 5));
        //create space around the text margin insets:


        JButton startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                questionOne();
            }
        });

        JPanel fullTitle = new JPanel();
        fullTitle.setLayout(new GridLayout(2, 1));
        fullTitle.add(encounterTitle);
        fullTitle.add(encounterTitle2);
        fullTitle.setBackground(Color.decode("#FF0000"));

        currentPanel.add(fullTitle);
        currentPanel.add(encounterText);
        currentPanel.add(startButton);

        currentPanel.revalidate();
        currentPanel.repaint();
    }

    public static void questionOne(){
        currentPanel.removeAll();
        currentPanel.setLayout(new GridLayout(3, 1));
        currentPanel.setSize(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT);
        currentPanel.setBackground(Color.decode("#F9B5AC"));

        JLabel questionOneTitle = new JLabel("     QUESTION 1");
        questionOneTitle.setFont(Fonts.BAD_GRUNGE);

       // questionOneTitle.setFont(new Font("Arial Unicode MS", Font.PLAIN, 30));

        JTextArea questionOneText = new JTextArea("\n     What is the main ingredient in bread?");

        questionOneText.setOpaque(true);
        //empty yellow border
        questionOneText.setBorder(BorderFactory.createLineBorder(Color.decode("#F9B5AC"), 5));
        questionOneText.setBackground(Color.white);
        questionOneText.setLineWrap(true);
        questionOneText.setWrapStyleWord(true);
        questionOneText.setFont(new Font("Arial Unicode MS", Font.PLAIN, 20));

        JButton optionOne = new JButton("Sugar");
        optionOne.setFont(new Font("Arial Unicode MS", Font.PLAIN, 20));
        JButton optionTwo = new JButton("Flour");
        optionTwo.setFont(new Font("Arial Unicode MS", Font.PLAIN, 20));
        JButton optionThree = new JButton("Salt");
        optionThree.setFont(new Font("Arial Unicode MS", Font.PLAIN, 20));
        JButton optionFour = new JButton("Milk");
        optionFour.setFont(new Font("Arial Unicode MS", Font.PLAIN, 20));

        optionOne.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                showIncorrectAns();
            }
        });

        optionTwo.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //THIS IS THE CORRECT ANSWER
                questionTwo(); //move on!
            }
        });

        optionThree.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                showIncorrectAns();
            }
        });

        optionFour.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                showIncorrectAns();
            }
        });

        currentPanel.add(questionOneTitle);
        currentPanel.add(questionOneText);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 2));
        buttonPanel.setBackground(Color.decode("#F9B5AC"));
        buttonPanel.add(optionOne);
        buttonPanel.add(optionTwo);
        buttonPanel.add(optionThree);
        buttonPanel.add(optionFour);
        currentPanel.add(buttonPanel);

        currentPanel.revalidate();
        currentPanel.repaint();
    }

    public static void questionTwo(){
        currentPanel.removeAll();
        currentPanel.setLayout(new GridLayout(3, 1));
        currentPanel.setSize(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT);
        currentPanel.setBackground(Color.decode("#F9B5AC"));

        JLabel questionTwoTitle = new JLabel("     QUESTION 2");
        questionTwoTitle.setFont(Fonts.BAD_GRUNGE);
        //questionTwoTitle.setFont(new Font("Arial Unicode MS", Font.PLAIN, 30));

        JTextArea questionTwoText = new JTextArea("\n     Which is NOT a type of pastry?");
        questionTwoText.setOpaque(true);
        //empty yellow border
        questionTwoText.setBorder(BorderFactory.createLineBorder(Color.decode("#F9B5AC"), 5));
        questionTwoText.setBackground(Color.white);
        questionTwoText.setLineWrap(true);
        questionTwoText.setWrapStyleWord(true);
        //questionTwoText.setBackground(Color.decode("#F4E285"));
        questionTwoText.setFont(new Font("Arial Unicode MS", Font.PLAIN, 20));
        JButton optionOne = new JButton("Croissant");
        optionOne.setFont(new Font("Arial Unicode MS", Font.PLAIN, 20));
        JButton optionTwo = new JButton("Baguette");
        optionTwo.setFont(new Font("Arial Unicode MS", Font.PLAIN, 20));
        JButton optionThree = new JButton("Danish");
        optionThree.setFont(new Font("Arial Unicode MS", Font.PLAIN, 20));
        JButton optionFour = new JButton("Eclair");
        optionFour.setFont(new Font("Arial Unicode MS", Font.PLAIN, 20));

        optionOne.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                showIncorrectAns();
            }
        });

        optionTwo.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //THIS IS THE CORRECT ANSWER
                questionThree(); //move on!
            }
        });

        optionThree.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                showIncorrectAns();
            }
        });

        optionFour.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                showIncorrectAns();
            }
        });

        currentPanel.add(questionTwoTitle);
        currentPanel.add(questionTwoText);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 2));
        buttonPanel.setBackground(Color.decode("#F9B5AC"));
        buttonPanel.add(optionOne);
        buttonPanel.add(optionTwo);
        buttonPanel.add(optionThree);
        buttonPanel.add(optionFour);
        currentPanel.add(buttonPanel);

        currentPanel.revalidate();
        currentPanel.repaint();
    }

    public static void questionThree(){
        currentPanel.removeAll();
        currentPanel.setLayout(new GridLayout(3, 1));
        currentPanel.setSize(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT);
        currentPanel.setBackground(Color.decode("#F9B5AC"));

        JLabel questionThreeTitle = new JLabel("     QUESTION 3");
        questionThreeTitle.setFont(Fonts.BAD_GRUNGE);
        //questionThreeTitle.setFont(new Font("Arial Unicode MS", Font.PLAIN, 30));

        JTextArea questionThreeText = new JTextArea("\n     What gives cake its fluffy texture?");
        questionThreeText.setLineWrap(true);
        questionThreeText.setOpaque(true);
        //empty yellow border
        questionThreeText.setBorder(BorderFactory.createLineBorder(Color.decode("#F9B5AC"), 5));
        questionThreeText.setBackground(Color.white);
        questionThreeText.setWrapStyleWord(true);
        //questionThreeText.setBackground(Color.decode("#F4E285"));
        questionThreeText.setFont(new Font("Arial Unicode MS", Font.PLAIN, 20));
        JButton optionOne = new JButton("Baking Soda");
        optionOne.setFont(new Font("Arial Unicode MS", Font.PLAIN, 20));
        JButton optionTwo = new JButton("Yeast");
        optionTwo.setFont(new Font("Arial Unicode MS", Font.PLAIN, 20));
        JButton optionThree = new JButton("Baking Powder");
        optionThree.setFont(new Font("Arial Unicode MS", Font.PLAIN, 20));
        JButton optionFour = new JButton("Vinegar");
        optionFour.setFont(new Font("Arial Unicode MS", Font.PLAIN, 20));

        optionOne.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                showIncorrectAns();
            }
        });

        optionTwo.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                showIncorrectAns();
            }
        });

        optionThree.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //THIS IS THE CORRECT ANSWER
                rewardScreen(); //move on!
            }
        });

        optionFour.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                showIncorrectAns();
            }
        });

        currentPanel.add(questionThreeTitle);
        currentPanel.add(questionThreeText);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 2));
        buttonPanel.setBackground(Color.decode("#F9B5AC"));
        buttonPanel.add(optionOne);
        buttonPanel.add(optionTwo);
        buttonPanel.add(optionThree);
        buttonPanel.add(optionFour);
        currentPanel.add(buttonPanel);

        currentPanel.revalidate();
        currentPanel.repaint();
    }

    public static void rewardScreen(){
        currentPanel.removeAll();
        currentPanel.setLayout(new GridLayout(3, 1));
        currentPanel.setSize(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT);
        currentPanel.setBackground(Color.decode("#F9B5AC"));

        JLabel rewardTitle = new JLabel("REWARD");
        rewardTitle.setFont(Fonts.BAD_GRUNGE);
        rewardTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        rewardTitle.setHorizontalAlignment(SwingConstants.CENTER);
        //rewardTitle.setFont(new Font("Arial Unicode MS", Font.PLAIN, 30));

        JTextArea rewardText = new JTextArea("                CAKE (-10 Hunger)\n                         50 coins");
        rewardText.setLineWrap(true);
        rewardText.setWrapStyleWord(true);
        rewardText.setBackground(Color.decode("#F9B5AC"));
        rewardText.setFont(new Font("Arial Unicode MS", Font.PLAIN, 30));

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                hideQuest50();
                //showdungeon();
            }
        });

        GameVars.inventory.add(new Food("Cake", 10, 10));
        GameVars.balance += 50;
        AnnaTools.Updater.updateAllSidePanels();
        complete = true;

        currentPanel.add(rewardTitle);
        currentPanel.add(rewardText);
        currentPanel.add(exitButton);

        currentPanel.revalidate();
        currentPanel.repaint();
    }

    public static String randomAttack(int damage){
        String[] attacks = {
                "You get hit with a rolling pin and lose " + damage + " health.",
                "You get hit with a pan and lose " + damage + " health.",
                "You are forced to eat a piece of bread and choke. You lose " + damage + " health."
        };
        return attacks[(int) (Math.random()*3)];
    }

    public static void showIncorrectAns(){
        int damage = (int) (Math.random()*10 + 10);
        JOptionPane.showMessageDialog(quest50Frame, randomAttack(damage), "Incorrect", JOptionPane.ERROR_MESSAGE);
        GameVars.health -= damage;
        AnnaTools.Updater.updateAllSidePanels();
    }

    public static void main(String[] args){
        Quest50 quest50 = new Quest50();
        quest50.showQuest50();

    }


}
