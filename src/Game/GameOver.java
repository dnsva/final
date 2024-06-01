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
    public static void checkGameOver() { //check if the game is over

        if (GameVars.sanity <= 0 || GameVars.health <= 0 || GameVars.hunger >= 100) { //if sanity is 0 or health is 0 or hunger is 100

            GameVars.isGhost = true; //set the player to be a ghost

            //set everything invisible:
            /* The following code sets everything invisible */
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

            JFrame gameOverFrame = new JFrame("By Anna Denisova"); //initialize
            gameOverFrame.setSize(GameVars.WINDOWWIDTH + GameVars.SIDEBARWIDTH, GameVars.WINDOWHEIGHT); //set size
            gameOverFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //close setting
            gameOverFrame.setLocationRelativeTo(null); //center
            gameOverFrame.getContentPane().setLayout(new BorderLayout()); //make layout border

            JPanel gameOverPanel = new JPanel(); //new panel
            gameOverPanel.setLayout(new BoxLayout(gameOverPanel, BoxLayout.Y_AXIS)); //vertical layout
            gameOverPanel.setBackground(Color.decode("#2C5530")); //set background color

            JLabel gameOverLabel = new JLabel("Game Over"); //label
            gameOverLabel.setFont(Fonts.panicFont); //set font
            gameOverLabel.setForeground(Color.WHITE); //set color
            gameOverLabel.setAlignmentX(Component.CENTER_ALIGNMENT); //center
            gameOverLabel.setBorder(BorderFactory.createEmptyBorder(120, 10, 50, 10)); // More room for the title
            gameOverPanel.add(gameOverLabel); //add to panel

            String gameOverDesc = "You have died because "; //description
            if (GameVars.sanity <= 0) { //if sanity is 0
                gameOverDesc += "you have lost your sanity."; //add to description
            } else if (GameVars.health <= 0) { //if health is 0
                gameOverDesc += "you have lost your health."; //add to description
            } else if (GameVars.hunger >= 100) { //if hunger is 100
                gameOverDesc += "you have starved to death."; //add to description
            }
            JLabel gameOverLabel2 = new JLabel(gameOverDesc); //label
            gameOverLabel2.setForeground(Color.WHITE); //set color
            gameOverLabel2.setFont(new Font("Arial", Font.PLAIN, 20)); //set font
            gameOverLabel2.setBorder(BorderFactory.createEmptyBorder(30, 10, 30, 10)); //add space
            gameOverLabel2.setAlignmentX(Component.CENTER_ALIGNMENT); //center
            gameOverPanel.add(gameOverLabel2); //add to panel

            JLabel ghostLabel = new JLabel("You are now a ghost."); //label
            ghostLabel.setForeground(Color.WHITE); //set color
            ghostLabel.setFont(new Font("Arial", Font.PLAIN, 20)); //set font
            ghostLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); //add space
            ghostLabel.setAlignmentX(Component.CENTER_ALIGNMENT); //center
            gameOverPanel.add(ghostLabel); //add to panel

            JButton okButton = new JButton("OK"); //button
            okButton.setAlignmentX(Component.CENTER_ALIGNMENT); //center
            okButton.addActionListener(new ActionListener() { //on click
                @Override
                public void actionPerformed(ActionEvent e) { //on click
                    gameOverFrame.setVisible(false); //hide the frame
                    HomeVillage.showHomeVillage(); //show the home village
                }
            });

            // Add vertical glue to ensure even spacing
            gameOverPanel.add(Box.createVerticalGlue()); //add space
            gameOverPanel.add(okButton); //add button
            gameOverPanel.add(Box.createVerticalGlue()); //add space

            gameOverFrame.add(gameOverPanel, BorderLayout.CENTER); //add panel
            gameOverFrame.setVisible(true); //set visible
        }
    }
}
