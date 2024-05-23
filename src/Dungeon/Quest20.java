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

public class Quest20 {

    public static JFrame quest20Frame = new JFrame();
    public static SideBar quest20SideBar = new SideBar();
    public static boolean complete = false;

    public static JPanel currentPanel;
    public static int currentQuestion = 0;
    public static int sanityLossPerIncorrect = 20;
    public static int rewardPerCorrect = 20;

    public Quest20() {

       // new Fonts(); //ONLY FOR TSETING

        quest20Frame = new JFrame("By Anna Denisova");
        quest20Frame.setSize(GameVars.WINDOWWIDTH + GameVars.SIDEBARWIDTH, GameVars.WINDOWHEIGHT);
        quest20Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        quest20Frame.setLocationRelativeTo(null);
        quest20Frame.getContentPane().setLayout(new BorderLayout());

        currentPanel = new JPanel();
        quest20SideBar = new SideBar();
        quest20Frame.add(quest20SideBar.getPanel(), BorderLayout.EAST);
        quest20Frame.add(currentPanel, BorderLayout.CENTER);

        teacherIntroduction();
    }

    public static void showQuest20() {
        quest20Frame.setVisible(true);
    }

    public static void hideQuest20() {
        dealWithMapLevelCompletion(20);
        quest20Frame.setVisible(false);
        MapGUI.showMapGUI();
    }

    public static void teacherIntroduction() {
        currentPanel.removeAll();
        currentPanel.setSize(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT);
        currentPanel.setBackground(Color.decode("#FF0000"));

        JLabel title = new JLabel("A VERY");
        title.setFont(Fonts.Corrupted_File);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setBackground(Color.decode("#FF0000"));

        JLabel title2 = new JLabel("PREDICTABLE");
        title2.setFont(Fonts.Corrupted_File);
        title2.setHorizontalAlignment(SwingConstants.CENTER);
        title2.setAlignmentX(Component.CENTER_ALIGNMENT);
        title2.setBackground(Color.decode("#FF0000"));

        JLabel title3 = new JLabel("TEACHER");
        title3.setFont(Fonts.Corrupted_File);
        title3.setHorizontalAlignment(SwingConstants.CENTER);
        title3.setAlignmentX(Component.CENTER_ALIGNMENT);
        title3.setBackground(Color.decode("#FF0000"));

        JPanel titlePanel = new JPanel(new GridLayout(3, 1));
        titlePanel.add(title);
        titlePanel.add(title2);
        titlePanel.add(title3);
        titlePanel.setBackground(Color.decode("#FF0000"));
        titlePanel.setPreferredSize(new Dimension(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT / 3));

        JEditorPane introText = new JEditorPane();
        introText.setOpaque(true);
        introText.setBorder(BorderFactory.createLineBorder(Color.decode("#FF0000"), 5));
        introText.setBackground(Color.white);
        introText.setEditable(false);
        introText.setFocusable(false);
        introText.setContentType("text/html");
        introText.setSize(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT / 3 + 100);
        introText.setPreferredSize(new Dimension(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT / 3 + 100));
        introText.setMargin(new Insets(5, 5, 5, 5));
        introText.setText(
                "<html><body style='font-size: 15px; padding: 10px; font-family: Arial'>" +
                        "<b>Teacher:</b> If you don’t answer all these questions I'm about to give you, your life will be ruined," +
                        "your reputation will be destroyed, and you will have no future. To proceed you must answer all of them." +
                        " You will get get some cash for every question you answer correctly. For every incorrect answer, you might go insane." +
                        "</body></html>"
        );

        JButton okButton = new JButton("OK");
        okButton.setPreferredSize(new Dimension(GameVars.WINDOWWIDTH, 50));
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nextQuestion();
            }
        });

        currentPanel.setLayout(new GridLayout(3, 1));
        currentPanel.add(titlePanel);
        currentPanel.add(introText);
        currentPanel.add(okButton);

        currentPanel.revalidate();
        currentPanel.repaint();
    }

    public static void nextQuestion() {
        switch (currentQuestion) {
            case 0: question1(); break;
            case 1: question2(); break;
            case 2: question3(); break;
            case 3: question4(); break;
            case 4: question5(); break;
            default: showRewardScreen(); break;
        }
        currentQuestion++;
    }

    public static void question1() {
        askQuestion("\nNUMBER 1", "1 + 1 =", 10, "It was binary and the answer was 10.");
    }

    public static void question2() {
        askQuestion("NUMBER 2", "101 + 1 =", 110, "It was binary again and the answer was 110.");
    }

    public static void question3() {
        askQuestion("NUMBER 3", "101 + 11 =", 1000, "It was binary AGAIN and the answer was 1000.");
    }

    public static void question4() {
        askQuestion("NUMBER 4", "10 + 10 =", 20, "20. Did you think it would always be binary?");
    }

    public static void question5() {
        askQuestion("NUMBER 5", "123456 + 789 =", 124245, "124245!");
    }

    public static void askQuestion(String prompt, String equation, int correctAnswer, String incorrectMessage) {
        currentPanel.removeAll();
        currentPanel.setSize(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT);
        currentPanel.setBackground(Color.decode("#A3BCF9"));

        JLabel questionTitle = new JLabel(prompt);
        questionTitle.setFont(Fonts.Corrupted_File);
        questionTitle.setHorizontalAlignment(SwingConstants.CENTER);
        questionTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        //questionTitle.setPreferredSize(new Dimension(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT/3-100));
        //questionTitle.setSize(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT/3-100);

       // JPanel inputPanel = new JPanel(new GridLayout(2, 1));
        //inputPanel.setBackground(Color.decode("#C2F9BB"));

        JLabel equationLabel = new JLabel(equation);
        equationLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        equationLabel.setBackground(Color.decode("#A3BCF9"));
        equationLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        equationLabel.setHorizontalAlignment(SwingConstants.CENTER);
        //equationLabel.setHorizontalAlignment(SwingConstants.CENTER);
        //equationLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField answerField = new JTextField();
        answerField.setHorizontalAlignment(SwingConstants.CENTER);
        answerField.setFont(new Font("Arial", Font.PLAIN, 20));


        //inputPanel.add(equationLabel);
       //inputPanel.add(answerField);
        //inputPanel.setPreferredSize(new Dimension(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT/3));
        //inputPanel.setSize(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT/3);

        JLabel feedbackLabel = new JLabel("");
        feedbackLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        feedbackLabel.setHorizontalAlignment(SwingConstants.CENTER);
        feedbackLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        //feedbackLabel.setPreferredSize(new Dimension(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT/3));
        //feedbackLabel.setSize(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT/3);

        JButton enterButton = new JButton("Enter");
        JButton nextButton = new JButton("Next");
        //nextButton.setPreferredSize(new Dimension(GameVars.WINDOWWIDTH, 50));
       // enterButton.setPreferredSize(new Dimension(GameVars.WINDOWWIDTH, 50));
        nextButton.setEnabled(false);
        enterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int userAnswer = Integer.parseInt(answerField.getText().trim());
                    if (userAnswer == correctAnswer) {
                        GameVars.balance += rewardPerCorrect;
                        feedbackLabel.setText("Correct! You get $" + rewardPerCorrect);
                    } else {
                        GameVars.sanity -= sanityLossPerIncorrect;
                        feedbackLabel.setText("Incorrect. The answer was " + correctAnswer + " [-" + sanityLossPerIncorrect + " sanity]");
                    }
                } catch (NumberFormatException ex) {
                    feedbackLabel.setText("Please enter a valid number.");
                }
                AnnaTools.Updater.updateAllSidePanels();
                nextButton.setEnabled(true);
                nextButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        nextQuestion();
                    }
                });

            }
        });

        currentPanel.setLayout(new GridLayout(6,  1));
        currentPanel.add(questionTitle);
       // currentPanel.add(new JLabel("")); // Placeholder for spacing
        currentPanel.add(equationLabel);
        currentPanel.add(answerField);
        currentPanel.add(feedbackLabel);
        currentPanel.add(enterButton);
        currentPanel.add(nextButton);

        currentPanel.revalidate();
        currentPanel.repaint();
    }

    public static void showRewardScreen() {
        currentPanel.removeAll();
        currentPanel.setSize(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT);
        currentPanel.setBackground(Color.decode("#A3BCF9"));

        JLabel rewardTitle = new JLabel("REWARD");
        rewardTitle.setFont(Fonts.pepperoni_pizza);
        rewardTitle.setHorizontalAlignment(SwingConstants.CENTER);
        rewardTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JEditorPane rewardText = new JEditorPane();
        rewardText.setOpaque(true);
        rewardText.setBorder(BorderFactory.createLineBorder(Color.decode("#A3BCF9"), 5));
        rewardText.setBackground(Color.white);
        rewardText.setEditable(false);
        rewardText.setFocusable(false);
        rewardText.setContentType("text/html");
        rewardText.setMargin(new Insets(5, 5, 5, 5));
        rewardText.setText(
                "<html><body style='font-size: 20px; padding: 10px; font-family: Arial'>" +
                        "You have completed the quiz! Your game is saved." +
                        "</body></html>"
        );

        JButton exitButton = new JButton("Exit");
        exitButton.setPreferredSize(new Dimension(GameVars.WINDOWWIDTH, 50));
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                hideQuest20();
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
        Quest20 quest20 = new Quest20();
        quest20.showQuest20();
    }
}
