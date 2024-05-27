package Game;

//IMPORT ALL PACKAGES -----
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import Dungeon.*;
import java.awt.*;

import AnnaTools.Fonts;
import Shops.*;

public class GameOver {
    public static void checkGameOver() {

        if (GameVars.sanity <= 0 || GameVars.health <= 0 || GameVars.hunger >= 100) {

            GameVars.isGhost = true;

            //set everything invisible:
            Quest10.hideQuest10();
            Quest20.hideQuest20();
            Quest30.hideQuest30();
            Quest40.hideQuest40();
            Quest50.hideQuest50();
            FinalBoss.hideFinalBoss();
            WeaponShop.hideWeaponShop();
            ArmourShop.hideArmourShop();
            Apothecary.hideApothecary();
            FoodMarket.hideFoodMarket();
            MapGUI.hideMapGUI();
            HomeVillage.hideHomeVillage();

            //create a frame that says game over and after OK set home village visible

            JFrame gameOverFrame = new JFrame("By Anna Denisova");
            gameOverFrame.setSize(GameVars.WINDOWWIDTH + GameVars.SIDEBARWIDTH, GameVars.WINDOWHEIGHT);
            gameOverFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            gameOverFrame.setLocationRelativeTo(null);
            gameOverFrame.getContentPane().setLayout(new BorderLayout());

            JPanel gameOverPanel = new JPanel();
            gameOverPanel.setLayout(new BoxLayout(gameOverPanel, BoxLayout.Y_AXIS));
            gameOverPanel.setBackground(Color.decode("#2C5530"));

            JLabel gameOverLabel = new JLabel("Game Over");
            gameOverLabel.setFont(Fonts.panicFont);
            gameOverLabel.setForeground(Color.WHITE);
            gameOverLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            gameOverLabel.setBorder(BorderFactory.createEmptyBorder(120, 10, 50, 10)); // More room for the title
            gameOverPanel.add(gameOverLabel);

            String gameOverDesc = "You have died because ";
            if (GameVars.sanity <= 0) {
                gameOverDesc += "you have lost your sanity.";
            } else if (GameVars.health <= 0) {
                gameOverDesc += "you have lost your health.";
            } else if (GameVars.hunger >= 100) {
                gameOverDesc += "you have starved to death.";
            }
            JLabel gameOverLabel2 = new JLabel(gameOverDesc);
            gameOverLabel2.setForeground(Color.WHITE);
            gameOverLabel2.setFont(new Font("Arial", Font.PLAIN, 20));
            gameOverLabel2.setBorder(BorderFactory.createEmptyBorder(30, 10, 30, 10));
            gameOverLabel2.setAlignmentX(Component.CENTER_ALIGNMENT);
            gameOverPanel.add(gameOverLabel2);

            JLabel ghostLabel = new JLabel("You are now a ghost.");
            ghostLabel.setForeground(Color.WHITE);
            ghostLabel.setFont(new Font("Arial", Font.PLAIN, 20));
            ghostLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            ghostLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            gameOverPanel.add(ghostLabel);

            JButton okButton = new JButton("OK");
            okButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            okButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    gameOverFrame.setVisible(false);
                    HomeVillage.showHomeVillage();
                }
            });

            // Add vertical glue to ensure even spacing
            gameOverPanel.add(Box.createVerticalGlue());
            gameOverPanel.add(okButton);
            gameOverPanel.add(Box.createVerticalGlue());

            gameOverFrame.add(gameOverPanel, BorderLayout.CENTER);
            gameOverFrame.setVisible(true);
        }
    }
}
