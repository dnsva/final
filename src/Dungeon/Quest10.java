package Dungeon;

//IMPORT ALL PACKAGES -----
import java.awt.*;      //-
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;   //-
import Game.*;          //-

import static Dungeon.Map.dealWithMapLevelCompletion;
//-------------------------

public class Quest10 {

    public static JFrame quest10Frame = new JFrame();
    public static SideBar quest10SideBar = new SideBar();
    public static boolean complete = false;

    public static JPanel currentPanel;
    public static String selectedSide;

    public Quest10() {
        quest10Frame = new JFrame("By Anna Denisova");
        quest10Frame.setSize(GameVars.WINDOWWIDTH + GameVars.SIDEBARWIDTH, GameVars.WINDOWHEIGHT);
        quest10Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        quest10Frame.setLocationRelativeTo(null);
        quest10Frame.getContentPane().setLayout(new BorderLayout());

        currentPanel = new JPanel();
        quest10SideBar = new SideBar();
        quest10Frame.add(quest10SideBar.getPanel(), BorderLayout.EAST);
        quest10Frame.add(currentPanel, BorderLayout.CENTER);


    }

    public static void showQuest10() {
        introduction();
        quest10Frame.setVisible(true);
    }

    public static void hideQuest10() {
        dealWithMapLevelCompletion(10);
        quest10Frame.setVisible(false);
        MapGUI.showMapGUI();
    }

    public static void introduction() {
        currentPanel.removeAll();
        currentPanel.setLayout(new GridLayout(3, 1));
        currentPanel.setSize(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT);
        currentPanel.setBackground(Color.decode("#C2F9BB"));

        JLabel introTitle = new JLabel("SideQuest: Coin Obsessed Gambler");
        JTextArea introText = new JTextArea("If it lands on your side, you win $100, if it doesn’t, you lose $20. Accept?");
        introText.setLineWrap(true);
        introText.setWrapStyleWord(true);
        introText.setBackground(Color.decode("#C2F9BB"));

        JButton yesButton = new JButton("Yes");
        yesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pickSide();
            }
        });

        JButton noButton = new JButton("No");
        noButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                hideQuest10();
            }
        });

        currentPanel.add(introTitle);
        currentPanel.add(introText);
        currentPanel.add(yesButton);
        currentPanel.add(noButton);

        currentPanel.revalidate();
        currentPanel.repaint();
    }

    public static void pickSide() {
        currentPanel.removeAll();
        currentPanel.setLayout(new GridLayout(3, 1));
        currentPanel.setSize(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT);
        currentPanel.setBackground(Color.decode("#C2F9BB"));

        JLabel pickSideTitle = new JLabel("Pick Side");
        JRadioButton headsButton = new JRadioButton("Heads");
        JRadioButton tailsButton = new JRadioButton("Tails");

        ButtonGroup group = new ButtonGroup();
        group.add(headsButton);
        group.add(tailsButton);

        JButton confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (headsButton.isSelected()) {
                    selectedSide = "heads";
                } else if (tailsButton.isSelected()) {
                    selectedSide = "tails";
                }
                rollResult();
            }
        });

        currentPanel.add(pickSideTitle);
        currentPanel.add(headsButton);
        currentPanel.add(tailsButton);
        currentPanel.add(confirmButton);

        currentPanel.revalidate();
        currentPanel.repaint();
    }

    public static void rollResult() {
        currentPanel.removeAll();
        currentPanel.setLayout(new GridLayout(3, 1));
        currentPanel.setSize(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT);
        currentPanel.setBackground(Color.decode("#C2F9BB"));

        JLabel resultTitle = new JLabel("Result");
        String result = Math.random() < 0.5 ? "heads" : "tails";
        JLabel resultLabel = new JLabel("The coin landed on: " + result);

        JTextArea resultText;
        if (selectedSide.equals(result)) {
            resultText = new JTextArea("You won $100!");
            GameVars.balance += 100;
            AnnaTools.Updater.updateAllSidePanels();
        } else {
            resultText = new JTextArea("You lost $20.");
            GameVars.balance -= 20;
            AnnaTools.Updater.updateAllSidePanels();
        }

        resultText.setLineWrap(true);
        resultText.setWrapStyleWord(true);
        resultText.setBackground(Color.decode("#C2F9BB"));

        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                hideQuest10();
            }
        });

        currentPanel.add(resultTitle);
        currentPanel.add(resultLabel);
        currentPanel.add(resultText);
        currentPanel.add(okButton);

        currentPanel.revalidate();
        currentPanel.repaint();
    }

    public static void main(String[] args) {
        Quest20 quest20 = new Quest20();
        quest20.showQuest20();
    }
}
