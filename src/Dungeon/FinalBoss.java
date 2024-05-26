package Dungeon;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Game.*;
import AnnaTools.*;
import Items.HealingMedicine;
import Items.SanityMedicine;

import static Dungeon.Map.dealWithMapLevelCompletion;

public class FinalBoss {

    public static JFrame finalBossFrame;
    public static SideBar finalBossSideBar = new SideBar();
    public static JPanel currentPanel;
    public static boolean complete = false;

    public FinalBoss() {

        new Fonts(); // for testing only

        finalBossFrame = new JFrame("By Anna Denisova");
        finalBossFrame.setSize(GameVars.WINDOWWIDTH + GameVars.SIDEBARWIDTH, GameVars.WINDOWHEIGHT);
        finalBossFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        finalBossFrame.setLocationRelativeTo(null);
        finalBossFrame.getContentPane().setLayout(new BorderLayout());

        currentPanel = new JPanel();
        finalBossSideBar = new SideBar();
        finalBossFrame.add(finalBossSideBar.getPanel(), BorderLayout.EAST);
        finalBossFrame.add(currentPanel, BorderLayout.CENTER);

        exposition();
    }

    public static void showFinalBoss() {
        finalBossFrame.setVisible(true);
    }

    public static void hideFinalBoss() {
        dealWithMapLevelCompletion(50);
        finalBossFrame.setVisible(false);
        MapGUI.showMapGUI();
    }

    public static void exposition() {
        currentPanel.removeAll();
        currentPanel.setSize(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT);
        currentPanel.setBackground(Color.decode("#000000"));

        JPanel expositionPanel = new JPanel();
        expositionPanel.setLayout(new BoxLayout(expositionPanel, BoxLayout.Y_AXIS));
        expositionPanel.setBackground(Color.decode("#000000"));

        JLabel expositionTitle1 = new JLabel("FINAL BOSS:");
        expositionTitle1.setFont(Fonts.curse_of_the_zombie);
        expositionTitle1.setForeground(Color.white);
        expositionTitle1.setAlignmentX(Component.CENTER_ALIGNMENT);
        expositionTitle1.setPreferredSize(new Dimension(GameVars.WINDOWWIDTH, 150));

        JLabel expositionTitle2 = new JLabel("THE GHOST");
        expositionTitle2.setFont(Fonts.curse_of_the_zombie);
        expositionTitle2.setForeground(Color.white);
        expositionTitle2.setAlignmentX(Component.CENTER_ALIGNMENT);
        expositionTitle2.setPreferredSize(new Dimension(GameVars.WINDOWWIDTH, 150));


        JEditorPane expositionDesc = new JEditorPane();
        expositionDesc.setOpaque(true);
        expositionDesc.setBorder(BorderFactory.createLineBorder(Color.decode("#000000"), 5));
        expositionDesc.setBackground(Color.black);
        expositionDesc.setEditable(false);
        expositionDesc.setFocusable(false);
        expositionDesc.setContentType("text/html");
        expositionDesc.setMargin(new Insets(15, 15, 15, 15));
        expositionDesc.setText(
                "<html><body style='font-size: 20px; color:white; padding: 10px; font-family: Arial'>" +
                        "<br><b>Ghost: </b>How did you end up here? I <br>wasn’t expecting you to get this far…<br>" +
                        "</body></html>"
        );
        //JTextArea expositionDesc = new JTextArea("How did you end up here? I wasn’t expecting you to get this far…");
        //expositionDesc.setFont(new Font("Arial", Font.PLAIN, 25));
        //expositionDesc.setForeground(Color.white);
        //expositionDesc.setBackground(Color.decode("#000000"));
        //margin inset:
       // expositionDesc.setMargin(new Insets(20, 20, 20, 20));
        //expositionDesc.setLineWrap(true);
        //expositionDesc.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton okButton = new JButton("OK");
        //okButton.setOpaque(true);
        //okButton.setBackground(Color.decode("#000000"));
        //okButton.setForeground(Color.white);
        //okButton.setBorderPainted(false);
        //okButton.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        okButton.setPreferredSize(new Dimension(GameVars.WINDOWWIDTH / 5, 50));
        okButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                surpriseAttack();
            }
        });

        /*
        expositionPanel.add(Box.createVerticalStrut(20));
        expositionPanel.add(expositionTitle1);
        expositionPanel.add(Box.createVerticalStrut(20));
        expositionPanel.add(expositionTitle2);
        expositionPanel.add(Box.createVerticalStrut(20));
        expositionPanel.add(expositionDesc);
        expositionPanel.add(Box.createVerticalStrut(20));
        expositionPanel.add(okButton);
        */

        //currentPanel.setLayout(new BorderLayout());
        //currentPanel.add(expositionPanel, BorderLayout.CENTER);

        expositionPanel.add(Box.createVerticalStrut(20));
        expositionPanel.add(expositionTitle1);
        expositionPanel.add(expositionTitle2);

        currentPanel.setLayout(new GridLayout(3, 1));
        expositionTitle1.setAlignmentX(Component.CENTER_ALIGNMENT);
        expositionTitle1.setHorizontalAlignment(SwingConstants.CENTER);
        expositionTitle2.setAlignmentX(Component.CENTER_ALIGNMENT);
        expositionTitle2.setHorizontalAlignment(SwingConstants.CENTER);
        currentPanel.add(expositionPanel);
        //currentPanel.add(expositionTitle2);
        currentPanel.add(expositionDesc);
        currentPanel.add(okButton);

        currentPanel.revalidate();
        currentPanel.repaint();
    }

    public static void surpriseAttack() {
        currentPanel.removeAll();
        currentPanel.setSize(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT);
        currentPanel.setBackground(Color.decode("#000000"));

        JPanel surprisePanel = new JPanel();
        surprisePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        surprisePanel.setBackground(Color.decode("#000000"));

        JLabel surpriseTitle = new JLabel("SURPRISE ATTACK");
        surpriseTitle.setFont(Fonts.curse_of_the_zombie);
        surpriseTitle.setForeground(Color.white);
        surpriseTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        surpriseTitle.setHorizontalAlignment(SwingConstants.CENTER);
        surpriseTitle.setPreferredSize(new Dimension(GameVars.WINDOWWIDTH, 180)); // Adjusted size for spacing
        surpriseTitle.setBorder(new EmptyBorder(40, 0, 20, 0));

        JEditorPane desc1 = new JEditorPane();
        desc1.setEditable(false);
        desc1.setFocusable(false);
        desc1.setContentType("html");
        desc1.setOpaque(true);
        desc1.setBorder(BorderFactory.createLineBorder(Color.decode("#000000"), 20));
        desc1.setBackground(Color.black);
        desc1.setContentType("text/html");
        desc1.setPreferredSize(new Dimension(GameVars.WINDOWWIDTH, 200)); // Adjusted size to accommodate all buttons
        desc1.setAlignmentX(Component.CENTER_ALIGNMENT);
        desc1.setText(
                "<html><body style='font-size: 15px; padding: 10px; color: white; font-family: Arial'>" +
                        "Five skeletons come running at you. Four of them regenerate immediately after any attack." +
                        "The fifth special skeleton is not invincible and is the one you need to find and defeat. <br><br>" +
                        "Each skeleton has an attack power of 40." +
                        "</body></html>"
        );
        desc1.setBorder(new EmptyBorder(0, 0, 40, 0)); // Add bottom empty border for spacing

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 1)); // Use FlowLayout with spacing
        buttonPanel.setBackground(Color.decode("#000000"));
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttonPanel1 = new JPanel();
        buttonPanel1.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10)); // Use FlowLayout with spacing
        buttonPanel1.setBackground(Color.decode("#000000"));
        buttonPanel1.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttonPanel2 = new JPanel();
        buttonPanel2.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10)); // Use FlowLayout with spacing
        buttonPanel2.setBackground(Color.decode("#000000"));
        buttonPanel2.setAlignmentX(Component.CENTER_ALIGNMENT);

        for (int i = 1; i <= 5; i++) {
            int buttonNumber = i;
            String buttonName = "";
            if(i == 1) buttonName = "Wizard Bones";
            else if(i == 2) buttonName = "Warrior Bones";
            else if(i == 3) buttonName = "Doctor Bones";
            else if(i == 4) buttonName = "Mime Bones";
            else if(i == 5) buttonName = "Farmer Bones";
            JButton button = new JButton(buttonName);
            button.setPreferredSize(new Dimension(150, 50));  // Set smaller preferred size
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    handleButtonPress(buttonNumber);
                }
            });

            if(i >= 1 && i <= 3) buttonPanel1.add(button); //first row
            else buttonPanel2.add(button); //second row
        }

        buttonPanel.add(buttonPanel1);
        buttonPanel.add(buttonPanel2);

        surprisePanel.add(surpriseTitle);
        surprisePanel.add(desc1);
        surprisePanel.add(buttonPanel);

        currentPanel.setLayout(new BorderLayout());
        currentPanel.add(surprisePanel, BorderLayout.CENTER);

        currentPanel.revalidate();
        currentPanel.repaint();
    }








    public static void handleButtonPress(int buttonNumber) {
        if (buttonNumber == 4) {
            fightSpecialSkeleton();
        } else {
            GameVars.health -= Math.max(0, (40 - GameVars.fullDefensePower));
            AnnaTools.Updater.updateAllSidePanels();
            JOptionPane.showMessageDialog(finalBossFrame, "Wrong one! You lose " + Math.max(0, (40 - GameVars.fullDefensePower)) + " health.", "Fight", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void fightSpecialSkeleton() {
        currentPanel.removeAll();
        currentPanel.setSize(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT);
        currentPanel.setBackground(Color.decode("#000000"));

        JPanel fightPanel = new JPanel();
        fightPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0)); // Set FlowLayout and center components with vertical spacing
        fightPanel.setBackground(Color.decode("#000000"));

        JLabel fightTitle = new JLabel("FIGHT THE");
        fightTitle.setFont(Fonts.curse_of_the_zombie);
        fightTitle.setForeground(Color.white);
        fightTitle.setPreferredSize(new Dimension(GameVars.WINDOWWIDTH, 110));
        fightTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        fightTitle.setHorizontalAlignment(SwingConstants.CENTER);
        fightTitle.setBorder(new EmptyBorder(20, 0, 0, 0)); // Add bottom empty border for spacing

        JLabel fightTitle1 = new JLabel("MIME");
        fightTitle1.setFont(Fonts.curse_of_the_zombie);
        fightTitle1.setForeground(Color.white);
        fightTitle1.setPreferredSize(new Dimension(GameVars.WINDOWWIDTH, 90));
        fightTitle1.setAlignmentX(Component.CENTER_ALIGNMENT);
        fightTitle1.setHorizontalAlignment(SwingConstants.CENTER);
       // fightTitle1.setBorder(new EmptyBorder(0, 0, 20, 0)); // Add bottom empty border for spacing

        JLabel fightTitle2 = new JLabel("SKELETON");
        fightTitle2.setFont(Fonts.curse_of_the_zombie);
        fightTitle2.setForeground(Color.white);
        fightTitle2.setPreferredSize(new Dimension(GameVars.WINDOWWIDTH, 130));
        fightTitle2.setAlignmentX(Component.CENTER_ALIGNMENT);
        fightTitle2.setHorizontalAlignment(SwingConstants.CENTER);
        fightTitle2.setBorder(new EmptyBorder(0, 0, 40, 0)); // Add bottom empty border for spacing

        JLabel healthLabel = new JLabel("Mime Skeleton Health: 100");
        healthLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        healthLabel.setForeground(Color.white);
        healthLabel.setPreferredSize(new Dimension(GameVars.WINDOWWIDTH, 130));
        healthLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        healthLabel.setHorizontalAlignment(SwingConstants.CENTER);
        healthLabel.setBorder(new EmptyBorder(0, 0, 40, 0)); // Add bottom empty border for spacing

        JButton attackButton = new JButton("ATTACK");
        attackButton.setPreferredSize(new Dimension(200, 50)); // Adjust button width
        attackButton.addActionListener(new ActionListener() {
            int skeletonHealth = 100;

            public void actionPerformed(ActionEvent e) {
                if (!GameVars.currWeapon.attackMiss()) {
                    skeletonHealth -= GameVars.fullAttackPower;
                    JOptionPane.showMessageDialog(finalBossFrame, "You attacked the skeleton and dealt " + GameVars.fullAttackPower + " damage.", "Fight", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(finalBossFrame, "You tried to attack but you missed.", "Fight", JOptionPane.INFORMATION_MESSAGE);
                }

                GameVars.health -= Math.max(0, (40 - GameVars.fullDefensePower));
                AnnaTools.Updater.updateAllSidePanels();

                if (skeletonHealth <= 0) {
                    rewardScreen();
                } else {
                    healthLabel.setText("Mime Skeleton Health: " + skeletonHealth);
                    JOptionPane.showMessageDialog(finalBossFrame, "In response to your attack, the skeleton attacks! You lose " + Math.max(0, (40 - GameVars.fullDefensePower)) + " health.", "Fight", JOptionPane.INFORMATION_MESSAGE);
                }
                AnnaTools.Updater.updateAllSidePanels();
            }
        });

        fightPanel.add(fightTitle);
        fightPanel.add(fightTitle1);
        fightPanel.add(fightTitle2);
        fightPanel.add(healthLabel);
        fightPanel.add(attackButton);

        currentPanel.setLayout(new BorderLayout());
        currentPanel.add(fightPanel, BorderLayout.CENTER);

        currentPanel.revalidate();
        currentPanel.repaint();
    }


    public static void rewardScreen() {
        currentPanel.removeAll();
        currentPanel.setSize(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT);
        currentPanel.setBackground(Color.decode("#000000"));

        JPanel rewardPanel = new JPanel();
        //rewardPanel.setLayout(new BoxLayout(rewardPanel, BoxLayout.Y_AXIS));
        rewardPanel.setBackground(Color.decode("#000000"));

        JLabel rewardTitle = new JLabel("REWARD");
        rewardTitle.setFont(Fonts.curse_of_the_zombie);
        rewardTitle.setForeground(Color.white);
        rewardTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        rewardTitle.setHorizontalAlignment(SwingConstants.CENTER);
        rewardTitle.setPreferredSize(new Dimension(GameVars.WINDOWWIDTH, 150));

        JEditorPane rewardText = new JEditorPane();
        rewardText.setEditable(false);
        rewardText.setFocusable(false);
        rewardText.setContentType("html");
        rewardText.setOpaque(true);
        rewardText.setBorder(BorderFactory.createLineBorder(Color.decode("#000000"), 20));
        rewardText.setBackground(Color.black);
        rewardText.setContentType("text/html");
        rewardText.setAlignmentX(Component.CENTER_ALIGNMENT);
        rewardText.setText(
                "<html><body style='font-size: 20px; padding: 10px; color: white; font-family: Arial'>" +
                        "I played this game once and I used to be just like you. But, I wasn't able " +
                        "to survive so I became a ghost. You were able to defeat all quests so I reward " +
                        "you with the following: <br><br> " +
                        "<div style='text-align:center;'> $10,000 <br> A Limb Transplant <br> An Ancient Mushroom <br> </div>" +
                        "</body></html>"
        );
        rewardText.setPreferredSize(new Dimension(GameVars.WINDOWWIDTH, 350));


        GameVars.balance += 10000;
        GameVars.inventory.add(new HealingMedicine("Limb Transplant", 50, 50)); //limb transplant
        GameVars.inventory.add(new SanityMedicine("Ancient Mushroom", 30, -5)); //ancient mushroom

        AnnaTools.Updater.updateAllSidePanels();

        JButton exitButton = new JButton("EXIT");
        exitButton.setPreferredSize(new Dimension(GameVars.WINDOWWIDTH, 50));
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                hideFinalBoss();
            }
        });

       // rewardPanel.add(Box.createVerticalStrut(20));
        rewardPanel.add(rewardTitle);
       // rewardPanel.add(Box.createVerticalStrut(20));
        rewardPanel.add(rewardText);
       // rewardPanel.add(Box.createVerticalStrut(10));
        rewardPanel.add(exitButton);

        currentPanel.setLayout(new BorderLayout());
        currentPanel.add(rewardPanel, BorderLayout.CENTER);

        currentPanel.revalidate();
        currentPanel.repaint();
    }

    public static void main(String[] args) {
        FinalBoss finalBoss = new FinalBoss();
        finalBoss.showFinalBoss();
    }
}
