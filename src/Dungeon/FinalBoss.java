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
import static Game.GameOver.checkGameOver;

public class FinalBoss {

    public static JFrame finalBossFrame; //this is the frame
    public static SideBar finalBossSideBar = new SideBar(); //this is the sidebar at the side
    public static JPanel currentPanel; //this is the panel in the center
    public static boolean complete = false; //this is used to check if the quest is complete

    public FinalBoss() { //CONSTRUCTOR FOR SETUP

       // new Fonts(); // for testing only

        finalBossFrame = new JFrame("By Anna Denisova"); //create a new frame
        finalBossFrame.setSize(GameVars.WINDOWWIDTH + GameVars.SIDEBARWIDTH, GameVars.WINDOWHEIGHT); //set the size of the frame
        finalBossFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //set the default close operation
        finalBossFrame.setLocationRelativeTo(null); //set the location of the frame
        finalBossFrame.getContentPane().setLayout(new BorderLayout()); //set the layout of the frame. center east west etc

        currentPanel = new JPanel(); //the main stuff
        finalBossSideBar = new SideBar(); //ok...
        finalBossFrame.add(finalBossSideBar.getPanel(), BorderLayout.EAST); //add the sidebar to the east
        finalBossFrame.add(currentPanel, BorderLayout.CENTER);  //add the main stuff to the center

        exposition(); //START THE LEVEL
    }

    public static void showFinalBoss() { //show the frame
        finalBossFrame.setVisible(true); //set the frame to visible
    }

    public static void hideFinalBoss() { //hide the frame
        dealWithMapLevelCompletion(50); //deal with the map level completion
        finalBossFrame.setVisible(false); //set the frame to invisible
        MapGUI.showMapGUI(); //show the map
    }

    public static void exposition() { //the start and the intro
        currentPanel.removeAll(); //remove all the stuff from the panel
        currentPanel.setSize(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT); //set the size of the panel
        currentPanel.setBackground(Color.decode("#000000")); //set the background color to BLACK

        JPanel expositionPanel = new JPanel(); //create a new panel
        expositionPanel.setLayout(new BoxLayout(expositionPanel, BoxLayout.Y_AXIS)); //set the layout of the panel
        expositionPanel.setBackground(Color.decode("#000000")); //set the background color of the panel to black again

        JLabel expositionTitle1 = new JLabel("FINAL BOSS:"); //TITLE PART 1
        /* The following code sets the font the color
        * alignment and size of the title part */
        expositionTitle1.setFont(Fonts.curse_of_the_zombie);
        expositionTitle1.setForeground(Color.white);
        expositionTitle1.setAlignmentX(Component.CENTER_ALIGNMENT);
        expositionTitle1.setPreferredSize(new Dimension(GameVars.WINDOWWIDTH, 150));

        JLabel expositionTitle2 = new JLabel("THE GHOST"); //TITLE PART 2
        /* The following code sets the font the color
         * alignment and size of the title part */
        expositionTitle2.setFont(Fonts.curse_of_the_zombie);
        expositionTitle2.setForeground(Color.white);
        expositionTitle2.setAlignmentX(Component.CENTER_ALIGNMENT);
        expositionTitle2.setPreferredSize(new Dimension(GameVars.WINDOWWIDTH, 150));

        JEditorPane expositionDesc = new JEditorPane(); //THE DESCRIPTION MONOLOGUE THING
        expositionDesc.setOpaque(true); //set the pane to opaque
        expositionDesc.setBorder(BorderFactory.createLineBorder(Color.decode("#000000"), 5)); //set the border
        expositionDesc.setBackground(Color.black); //set the background color
        expositionDesc.setEditable(false); //set the pane to not editable
        expositionDesc.setFocusable(false); //set the pane to not focusable
        expositionDesc.setContentType("text/html"); //set the content type to html
        expositionDesc.setMargin(new Insets(15, 15, 15, 15)); //set the margin
        //The following code sets the text in html style
        expositionDesc.setText(
                "<html><body style='font-size: 20px; color:white; padding: 10px; font-family: Arial'>" +
                        "<br><b>Ghost: </b>How did you end up here? I <br>wasn’t expecting you to get this far…<br>" +
                        "</body></html>"
        );
        //These are random tests and comments i dont even know
        //JTextArea expositionDesc = new JTextArea("How did you end up here? I wasn’t expecting you to get this far…");
        //expositionDesc.setFont(new Font("Arial", Font.PLAIN, 25));
        //expositionDesc.setForeground(Color.white);
        //expositionDesc.setBackground(Color.decode("#000000"));
        //margin inset:
       // expositionDesc.setMargin(new Insets(20, 20, 20, 20));
        //expositionDesc.setLineWrap(true);
        //expositionDesc.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton okButton = new JButton("OK"); //pretty self explanatory
        okButton.setPreferredSize(new Dimension(GameVars.WINDOWWIDTH / 5, 50)); //set the size of the button
        okButton.setAlignmentX(Component.CENTER_ALIGNMENT); //set the alignment of the button
        okButton.addActionListener(new ActionListener() { //add an action listener
            public void actionPerformed(ActionEvent e) { //when the button is pressed
                surpriseAttack(); //go to the surprise attack window frame panel thing
            }
        });

        /*
        what even is this
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

        expositionPanel.add(Box.createVerticalStrut(20)); //ADD SPACE
        expositionPanel.add(expositionTitle1); //the title part 1
        expositionPanel.add(expositionTitle2); //the title part 2

        currentPanel.setLayout(new GridLayout(3, 1)); //3 rows 1 col
        expositionTitle1.setAlignmentX(Component.CENTER_ALIGNMENT); //center the title
        expositionTitle1.setHorizontalAlignment(SwingConstants.CENTER); //center the title
        expositionTitle2.setAlignmentX(Component.CENTER_ALIGNMENT); //center the title
        expositionTitle2.setHorizontalAlignment(SwingConstants.CENTER); //center the title
        currentPanel.add(expositionPanel); //add the panel (row 1)
        currentPanel.add(expositionDesc); //add the description (row 2)
        currentPanel.add(okButton); //add the button (row 3)

        currentPanel.revalidate(); //revalidate the panel
        currentPanel.repaint(); //repaint the panel
    }

    public static void surpriseAttack() { //the fight
        currentPanel.removeAll(); //remove all the stuff from the panel
        currentPanel.setSize(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT); //set the size of the panel
        currentPanel.setBackground(Color.decode("#000000")); //set the background color to black

        JPanel surprisePanel = new JPanel(); //main code goes here
        surprisePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0)); //set the layout of the panel
        surprisePanel.setBackground(Color.decode("#000000")); //black

        JLabel surpriseTitle = new JLabel("SURPRISE ATTACK"); //the title
        surpriseTitle.setFont(Fonts.curse_of_the_zombie); //set the font
        surpriseTitle.setForeground(Color.white); //set the color
        surpriseTitle.setAlignmentX(Component.CENTER_ALIGNMENT); //center the title
        surpriseTitle.setHorizontalAlignment(SwingConstants.CENTER); //center the title
        surpriseTitle.setPreferredSize(new Dimension(GameVars.WINDOWWIDTH, 180)); // Adjusted size for spacing
        surpriseTitle.setBorder(new EmptyBorder(40, 0, 20, 0)); // Add bottom empty border for spacing

        JEditorPane desc1 = new JEditorPane(); //the description
        desc1.setEditable(false); //set the pane to not editable
        desc1.setFocusable(false); //set the pane to not focusable
        desc1.setContentType("html"); //set the content type to html
        desc1.setOpaque(true); //set the pane to opaque
        desc1.setBorder(BorderFactory.createLineBorder(Color.decode("#000000"), 20)); //set the border
        desc1.setBackground(Color.black); //set the background color
        desc1.setContentType("text/html"); //set the content type to html
        desc1.setPreferredSize(new Dimension(GameVars.WINDOWWIDTH, 200)); // Adjusted size to accommodate all buttons
        desc1.setAlignmentX(Component.CENTER_ALIGNMENT); //center the description
        /*html formatting of text: */
        desc1.setText(
                "<html><body style='font-size: 15px; padding: 10px; color: white; font-family: Arial'>" +
                        "Five skeletons come running at you. Four of them regenerate immediately after any attack." +
                        "The fifth special skeleton is not invincible and is the one you need to find and defeat. <br><br>" +
                        "Each skeleton has an attack power of 40." +
                        "</body></html>"
        );
        desc1.setBorder(new EmptyBorder(0, 0, 40, 0)); // Add bottom empty border for spacing

        JPanel buttonPanel = new JPanel(); //the buttons
        buttonPanel.setLayout(new GridLayout(2, 1)); // Use FlowLayout with spacing
        buttonPanel.setBackground(Color.decode("#000000")); //black
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT); //center the buttons

        JPanel buttonPanel1 = new JPanel(); //the first row of buttons
        buttonPanel1.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10)); // Use FlowLayout with spacing
        buttonPanel1.setBackground(Color.decode("#000000")); //black
        buttonPanel1.setAlignmentX(Component.CENTER_ALIGNMENT); //center the buttons

        JPanel buttonPanel2 = new JPanel(); //the second row of buttons
        buttonPanel2.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10)); // Use FlowLayout with spacing
        buttonPanel2.setBackground(Color.decode("#000000")); //black
        buttonPanel2.setAlignmentX(Component.CENTER_ALIGNMENT); //center the buttons

        for (int i = 1; i <= 5; i++) { //for loop
            int buttonNumber = i; //set the button number
            String buttonName = ""; //make a string for button names
            if(i == 1) buttonName = "Wizard Bones"; //set the button name
            else if(i == 2) buttonName = "Warrior Bones"; //set the button name
            else if(i == 3) buttonName = "Doctor Bones"; //set the button name
            else if(i == 4) buttonName = "Mime Bones"; //set the button name
            else if(i == 5) buttonName = "Farmer Bones"; //set the button name
            JButton button = new JButton(buttonName); //create a new button
            button.setPreferredSize(new Dimension(150, 50));  // Set smaller preferred size
            button.addActionListener(new ActionListener() { //add an action listener
                public void actionPerformed(ActionEvent e) { //when the button is pressed
                    handleButtonPress(buttonNumber); //handle the button press
                }
            });

            if(i >= 1 && i <= 3) buttonPanel1.add(button); //first row
            else buttonPanel2.add(button); //second row
        }

        buttonPanel.add(buttonPanel1); //add the first row of buttons
        buttonPanel.add(buttonPanel2); //add the second row of buttons

        surprisePanel.add(surpriseTitle); //add the title
        surprisePanel.add(desc1); //add the description
        surprisePanel.add(buttonPanel); //add the buttons

        currentPanel.setLayout(new BorderLayout()); //set the layout of the panel
        currentPanel.add(surprisePanel, BorderLayout.CENTER); //add the panel to the center

        currentPanel.revalidate(); //revalidate the panel
        currentPanel.repaint(); //repaint the panel
    }

    public static void handleButtonPress(int buttonNumber) { //4 is special the others are the same
        if (buttonNumber == 4) { //!!!!
            fightSpecialSkeleton(); //fight the special skeleton
        } else { //if 1 2 3 or 5
            GameVars.health -= Math.max(0, (40 - GameVars.fullDefensePower)); //decrease health
            checkGameOver(); //check if the game is over
            AnnaTools.Updater.updateAllSidePanels(); //update all side panels
            //the following code shows the loss screen pop-up
            JOptionPane.showMessageDialog(finalBossFrame, "Wrong one! You lose " + Math.max(0, (40 - GameVars.fullDefensePower)) + " health.", "Fight", JOptionPane.INFORMATION_MESSAGE); //show the message
        }
    }

    public static void fightSpecialSkeleton() { //the fight
        currentPanel.removeAll(); //remove all the stuff from the panel
        currentPanel.setSize(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT); //set the size of the panel
        currentPanel.setBackground(Color.decode("#000000")); //set the background color to black

        JPanel fightPanel = new JPanel(); //the main content place
        fightPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0)); // Set FlowLayout and center components with vertical spacing
        fightPanel.setBackground(Color.decode("#000000")); //black

        JLabel fightTitle = new JLabel("FIGHT THE"); //the title part 1
        /* the following code adds formatting */
        fightTitle.setFont(Fonts.curse_of_the_zombie);
        fightTitle.setForeground(Color.white);
        fightTitle.setPreferredSize(new Dimension(GameVars.WINDOWWIDTH, 110));
        fightTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        fightTitle.setHorizontalAlignment(SwingConstants.CENTER);
        fightTitle.setBorder(new EmptyBorder(20, 0, 0, 0)); // Add bottom empty border for spacing

        JLabel fightTitle1 = new JLabel("MIME"); //the title part 2
        /* the following code adds formatting */
        fightTitle1.setFont(Fonts.curse_of_the_zombie);
        fightTitle1.setForeground(Color.white);
        fightTitle1.setPreferredSize(new Dimension(GameVars.WINDOWWIDTH, 90));
        fightTitle1.setAlignmentX(Component.CENTER_ALIGNMENT);
        fightTitle1.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel fightTitle2 = new JLabel("SKELETON"); //the title part 3
        /* the following code adds formatting */
        fightTitle2.setFont(Fonts.curse_of_the_zombie);
        fightTitle2.setForeground(Color.white);
        fightTitle2.setPreferredSize(new Dimension(GameVars.WINDOWWIDTH, 130));
        fightTitle2.setAlignmentX(Component.CENTER_ALIGNMENT);
        fightTitle2.setHorizontalAlignment(SwingConstants.CENTER);
        fightTitle2.setBorder(new EmptyBorder(0, 0, 40, 0)); // Add bottom empty border for spacing

        JLabel healthLabel = new JLabel("Mime Skeleton Health: 100"); //the health label
        /* the following code adds formatting */
        healthLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        healthLabel.setForeground(Color.white);
        healthLabel.setPreferredSize(new Dimension(GameVars.WINDOWWIDTH, 130));
        healthLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        healthLabel.setHorizontalAlignment(SwingConstants.CENTER);
        healthLabel.setBorder(new EmptyBorder(0, 0, 40, 0)); // Add bottom empty border for spacing

        JButton attackButton = new JButton("ATTACK"); //A BUTTON TO ATTACK
        attackButton.setPreferredSize(new Dimension(200, 50)); // Adjust button width
        attackButton.addActionListener(new ActionListener() { //alright
            int skeletonHealth = 100; //set the health of the skeleton to 100 to start
            public void actionPerformed(ActionEvent e) { //on click
                if (!GameVars.currWeapon.attackMiss()) { //if the attack does not miss
                    skeletonHealth -= GameVars.fullAttackPower; //decrease the health of the skeleton
                    JOptionPane.showMessageDialog(finalBossFrame, "You attacked the skeleton and dealt " + GameVars.fullAttackPower + " damage.", "Fight", JOptionPane.INFORMATION_MESSAGE); //show the message
                } else {
                    //pop up saying you missed
                    JOptionPane.showMessageDialog(finalBossFrame, "You tried to attack but you missed.", "Fight", JOptionPane.INFORMATION_MESSAGE);
                }

                if (skeletonHealth <= 0) { //if the skeleton health is less than or equal to 0
                    rewardScreen(); //go to the reward screen
                } else { //if the skeleton health is not less than or equal to 0
                    healthLabel.setText("Mime Skeleton Health: " + skeletonHealth); //set the health label
                    //retailate
                    JOptionPane.showMessageDialog(finalBossFrame, "In response to your attack, the skeleton attacks! You lose " + Math.max(0, (40 - GameVars.fullDefensePower)) + " health.", "Fight", JOptionPane.INFORMATION_MESSAGE);
                }
                GameVars.health -= Math.max(0, (40 - GameVars.fullDefensePower)); //decrease the health
                checkGameOver(); //check if the game is over
                AnnaTools.Updater.updateAllSidePanels(); //update all side panels
            }
        });

        fightPanel.add(fightTitle);     //add the title
        fightPanel.add(fightTitle1);    //add the title
        fightPanel.add(fightTitle2);    //add the title
        fightPanel.add(healthLabel);    //add the health label
        fightPanel.add(attackButton);   //add the attack button

        currentPanel.setLayout(new BorderLayout()); //set the layout of the panel
        currentPanel.add(fightPanel, BorderLayout.CENTER); //add the panel to the center

        currentPanel.revalidate(); //revalidate the panel
        currentPanel.repaint(); //repaint the panel
    }


    public static void rewardScreen() { //finally
        currentPanel.removeAll(); //remove all the stuff from the panel
        currentPanel.setSize(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT); //set the size of the panel
        currentPanel.setBackground(Color.decode("#000000")); //black

        JPanel rewardPanel = new JPanel(); //default flow layout
        rewardPanel.setBackground(Color.decode("#000000")); //black

        JLabel rewardTitle = new JLabel("REWARD"); //title
        rewardTitle.setFont(Fonts.curse_of_the_zombie); //custom font
        rewardTitle.setForeground(Color.white); //white text
        rewardTitle.setAlignmentX(Component.CENTER_ALIGNMENT); //centered
        rewardTitle.setHorizontalAlignment(SwingConstants.CENTER); //centered
        rewardTitle.setPreferredSize(new Dimension(GameVars.WINDOWWIDTH, 150)); // Adjusted size for spacing

        JEditorPane rewardText = new JEditorPane(); //the text
        rewardText.setEditable(false); //not editable
        rewardText.setFocusable(false); //not focusable
        rewardText.setOpaque(true); //opaque
        rewardText.setBorder(BorderFactory.createLineBorder(Color.decode("#000000"), 20)); //border
        rewardText.setBackground(Color.black); //black
        rewardText.setContentType("text/html"); //html
        rewardText.setAlignmentX(Component.CENTER_ALIGNMENT);  //centered
        rewardText.setText( //html text
                "<html><body style='font-size: 20px; padding: 10px; color: white; font-family: Arial'>" +
                        "I played this game once and I used to be just like you. But, I wasn't able " +
                        "to survive so I became a ghost. You were able to defeat all quests so I reward " +
                        "you with the following: <br><br> " +
                        "<div style='text-align:center;'> $10,000 <br> A Limb Transplant <br> An Ancient Mushroom <br> </div>" +
                        "</body></html>"
        );
        rewardText.setPreferredSize(new Dimension(GameVars.WINDOWWIDTH, 350)); // Adjusted size for spacing

        //THE FOLLOWING CODE ADDS REWARDS TO USER BALANCE AND INVENTORY
        GameVars.balance += 10000;
        GameVars.inventory.add(new HealingMedicine("Limb Transplant", 50, 50)); //limb transplant
        GameVars.inventory.add(new SanityMedicine("Ancient Mushroom", 30, -5)); //ancient mushroom

        AnnaTools.Updater.updateAllSidePanels(); //yay

        JButton exitButton = new JButton("EXIT"); //to leave
        exitButton.setPreferredSize(new Dimension(GameVars.WINDOWWIDTH, 50)); //ok
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT); //center
        exitButton.addActionListener(new ActionListener() { //on click
            public void actionPerformed(ActionEvent e) { //on click
                hideFinalBoss(); //hide the final boss
            }
        });

       // rewardPanel.add(Box.createVerticalStrut(20));
        rewardPanel.add(rewardTitle); //add the title part 1
       // rewardPanel.add(Box.createVerticalStrut(20));
        rewardPanel.add(rewardText); //add the title part 2
       // rewardPanel.add(Box.createVerticalStrut(10));
        rewardPanel.add(exitButton); //to exit

        currentPanel.setLayout(new BorderLayout()); //set the layout of the panel
        currentPanel.add(rewardPanel, BorderLayout.CENTER); //add the panel to the center

        currentPanel.revalidate(); //revalidate the panel
        currentPanel.repaint(); //repaint the panel
    }

    /* TESTING
    public static void main(String[] args) {
        FinalBoss finalBoss = new FinalBoss();
        finalBoss.showFinalBoss();
    }*/
}
