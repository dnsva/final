package Dungeon;

// IMPORT ALL PACKAGES -----
import java.awt.*;      //-
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;   //-
import Game.*;          //-
import AnnaTools.*;     //-

import static Dungeon.Map.currGlobalRow;
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

       // new Fonts(); //FOR TESTING ONLy

        currentPanel.removeAll();
        currentPanel.setSize(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT);
        currentPanel.setBackground(Color.decode("#C2F9BB"));

        JPanel titlePanel = new JPanel(new GridLayout(3, 1));

        JLabel title = new JLabel("Coin");
        title.setFont(Fonts.panicFont);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel title2 = new JLabel("Obsessed");
        title2.setFont(Fonts.panicFont);
        title2.setHorizontalAlignment(SwingConstants.CENTER);
        title2.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel title3 = new JLabel("Gambler");
        title3.setFont(Fonts.panicFont);
        title3.setHorizontalAlignment(SwingConstants.CENTER);
        title3.setAlignmentX(Component.CENTER_ALIGNMENT);

        titlePanel.add(title);
        titlePanel.add(title2);
        titlePanel.add(title3);

        titlePanel.setBackground(Color.decode("#FF0000"));
        titlePanel.setSize(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT / 3);
        titlePanel.setPreferredSize(new Dimension(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT / 3));

        JEditorPane introText = new JEditorPane();
        introText.setOpaque(true);
        introText.setBorder(BorderFactory.createLineBorder(Color.decode("#FF0000"), 5));
        introText.setBackground(Color.white);
        introText.setEditable(false);
        introText.setFocusable(false);
        introText.setContentType("text/html");
        introText.setMargin(new Insets(5, 5, 5, 5));
        introText.setText(
                "<html><body style='font-size: 15px; padding: 10px; font-family: Arial'>" +
                        "<br><b>Gambler: </b> If this coin lands on your side, you win $100, if it doesn’t, you lose $20. Accept?" +
                        "</body></html>"
        );

        JButton yesButton = new JButton("Yes");
        yesButton.setPreferredSize(new Dimension(GameVars.WINDOWWIDTH, 50));
        yesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pickSide();
            }
        });

        JButton noButton = new JButton("No");
        noButton.setPreferredSize(new Dimension(GameVars.WINDOWWIDTH, 50));
        noButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                hideQuest10();
            }
        });

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        buttonPanel.add(yesButton);
        buttonPanel.add(noButton);
        buttonPanel.setBackground(Color.decode("#FF0000"));

        JPanel introAndButtonPanel = new JPanel(new GridLayout(2, 1));
        introAndButtonPanel.add(introText);
        introAndButtonPanel.add(buttonPanel);

        currentPanel.setLayout(new GridLayout(2, 1));
        currentPanel.add(titlePanel);
        currentPanel.add(introAndButtonPanel);

        currentPanel.revalidate();
        currentPanel.repaint();
    }

    public static void pickSide() {
        currentPanel.removeAll();
        currentPanel.setSize(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT);
        currentPanel.setBackground(Color.decode("#5448C8"));

        JLabel pickSideTitle = new JLabel("Pick Side");
        pickSideTitle.setFont(Fonts.panicFont);
        pickSideTitle.setHorizontalAlignment(SwingConstants.CENTER);
        pickSideTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        //pickSideTitle.setPreferredSize(new Dimension(GameVars.WINDOWWIDTH, 200));
       // pickSideTitle.setSize(GameVars.WINDOWWIDTH, 200);

        JRadioButton headsButton = new JRadioButton("Heads");
        headsButton.setFont(new Font("Arial Black", Font.PLAIN, 30));
        headsButton.setHorizontalAlignment(SwingConstants.CENTER);
        headsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        //headsButton.setPreferredSize(new Dimension(GameVars.WINDOWWIDTH, 100));
        JRadioButton tailsButton = new JRadioButton("Tails");
        tailsButton.setFont(new Font("Arial Black", Font.PLAIN, 30));
        tailsButton.setHorizontalAlignment(SwingConstants.CENTER);
        tailsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        //tailsButton.setPreferredSize(new Dimension(GameVars.WINDOWWIDTH, 100));

        headsButton.setSelected(true);

        ButtonGroup group = new ButtonGroup();
        group.add(headsButton);
        group.add(tailsButton);

        JPanel buttonOptionPanel = new JPanel();
        buttonOptionPanel.setLayout(new GridLayout(1, 2));
        buttonOptionPanel.setBackground(Color.decode("#6967d6"));
        buttonOptionPanel.setBorder(BorderFactory.createLineBorder(Color.decode("#5448C8"), 5));

        //buttonOptionPanel.setPreferredSize(new Dimension(GameVars.WINDOWWIDTH, 50));
        buttonOptionPanel.add(headsButton);
        buttonOptionPanel.add(tailsButton);

        JButton confirmButton = new JButton("Confirm");
        //confirmButton.setBorderPainted(false);
        confirmButton.setOpaque(true);
        confirmButton.setBorder(BorderFactory.createLineBorder(Color.decode("#5448C8"), 5));
        confirmButton.setBackground(Color.decode("#6967d6"));
        //confirmButton.setPreferredSize(new Dimension(GameVars.WINDOWWIDTH, 50));
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

        //currentPanel.setLayout(new GridLayout(3, 1));
        currentPanel.setLayout(new GridLayout(3, 1));
        currentPanel.add(pickSideTitle);
        currentPanel.add(buttonOptionPanel);
        currentPanel.add(confirmButton);

        currentPanel.revalidate();
        currentPanel.repaint();
    }

    public static void rollResult() {
        currentPanel.removeAll();
        currentPanel.setSize(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT);
        currentPanel.setBackground(Color.decode("#5448C8"));

        JLabel resultTitle = new JLabel("Result");
        resultTitle.setFont(Fonts.panicFont);
        resultTitle.setHorizontalAlignment(SwingConstants.CENTER);
        resultTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        String result = Math.random() < 0.5 ? "heads" : "tails";
        JLabel resultLabel = new JLabel("The coin landed on: " + result);
        resultLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        resultLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JEditorPane resultText = new JEditorPane();
        resultText.setOpaque(true);
        resultText.setBorder(BorderFactory.createLineBorder(Color.decode("#5448C8"), 5));
        resultText.setBackground(Color.white);
        resultText.setEditable(false);
        resultText.setFocusable(false);
        resultText.setContentType("text/html");
        resultText.setMargin(new Insets(5, 5, 5, 5));

        if (selectedSide.equals(result)) {
            resultText.setText("<html><body style='font-size: 20px; padding: 10px; font-family: Arial'>You won $100!</body></html>");
            GameVars.balance += 100;
        } else {
            resultText.setText("<html><body style='font-size: 20px; padding: 10px; font-family: Arial'>You lost $20.</body></html>");
            GameVars.balance -= 20;
        }

        AnnaTools.Updater.updateAllSidePanels();

        JButton okButton = new JButton("OK");
        okButton.setPreferredSize(new Dimension(GameVars.WINDOWWIDTH, 50));
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                hideQuest10();
            }
        });

        currentPanel.setLayout(new GridLayout(4, 1));
        currentPanel.add(resultTitle);
        currentPanel.add(resultLabel);
        currentPanel.add(resultText);
        currentPanel.add(okButton);

        currentPanel.revalidate();
        currentPanel.repaint();
    }

    public static void main(String[] args) {
        Quest10 quest10 = new Quest10();
        quest10.showQuest10();
    }
}
