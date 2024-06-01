package Dungeon;

// IMPORT ALL PACKAGES -----
import java.awt.*;      //-
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;   //-
import Game.*;          //-
import AnnaTools.*;     //-

import static Dungeon.Map.dealWithMapLevelCompletion;
//-------------------------

public class Quest10 {

    public static JFrame quest10Frame = new JFrame(); //the frame
    public static SideBar quest10SideBar = new SideBar(); //the sidebar
    public static boolean complete = false; //whether completed or not

    public static JPanel currentPanel; //the panel with the information on it that changes
    public static String selectedSide; //heads or tails

    public Quest10() {
        quest10Frame = new JFrame("By Anna Denisova"); //title
        quest10Frame.setSize(GameVars.WINDOWWIDTH + GameVars.SIDEBARWIDTH, GameVars.WINDOWHEIGHT); //size
        quest10Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //for closing
        quest10Frame.setLocationRelativeTo(null); //center
        quest10Frame.getContentPane().setLayout(new BorderLayout()); //set layout

        currentPanel = new JPanel(); //initialize
        quest10SideBar = new SideBar(); //initialize
        quest10Frame.add(quest10SideBar.getPanel(), BorderLayout.EAST); //set to be located at the side
        quest10Frame.add(currentPanel, BorderLayout.CENTER); //set to be located in the center
    }

    public static void showQuest10() { //show frame
        introduction(); //go for the intro
        quest10Frame.setVisible(true); //make the frame visible
    }

    public static void hideQuest10() { //hide everything
        dealWithMapLevelCompletion(10); //check
        quest10Frame.setVisible(false); //set frame invisible
        MapGUI.showMapGUI(); //show the map again
    }

    public static void introduction() { //the initial window

       // new Fonts(); //FOR TESTING ONLy

        currentPanel.removeAll(); //remove everything
        currentPanel.setSize(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT); //window size
        currentPanel.setBackground(Color.decode("#C2F9BB")); //set color

        JPanel titlePanel = new JPanel(new GridLayout(3, 1)); //for different components evenly sized

        JLabel title = new JLabel("Coin"); //title part 1
        //following code formats
        title.setFont(Fonts.panicFont);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel title2 = new JLabel("Obsessed"); //title part 2
        //following code formats and aligns
        title2.setFont(Fonts.panicFont);
        title2.setHorizontalAlignment(SwingConstants.CENTER);
        title2.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel title3 = new JLabel("Gambler"); //title part 3
        //following code formats and aligns
        title3.setFont(Fonts.panicFont);
        title3.setHorizontalAlignment(SwingConstants.CENTER);
        title3.setAlignmentX(Component.CENTER_ALIGNMENT);

        titlePanel.add(title); //add the first part
        titlePanel.add(title2); //the second part
        titlePanel.add(title3); //and the third part

        titlePanel.setBackground(Color.decode("#FF0000")); //make it RED
        titlePanel.setSize(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT / 3); //set the size
        titlePanel.setPreferredSize(new Dimension(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT / 3)); //set the size

        JEditorPane introText = new JEditorPane(); //for the text
        introText.setOpaque(true); //for colors
        introText.setBorder(BorderFactory.createLineBorder(Color.decode("#FF0000"), 5)); //red border
        introText.setBackground(Color.white); //white box with black text
        introText.setEditable(false); //cannot be edited
        introText.setFocusable(false); //cannot be focused on
        introText.setContentType("text/html"); //editor type
        introText.setMargin(new Insets(5, 5, 5, 5)); //text border
        introText.setText( //this code sets the text of the monologue/ description
                "<html><body style='font-size: 15px; padding: 10px; font-family: Arial'>" +
                        "<br><b>Gambler: </b> If this coin lands on your side, you win $100, if it doesn’t, you lose $20. Accept?" +
                        "</body></html>"
        );

        JButton yesButton = new JButton("Yes"); //yes - play
        yesButton.setPreferredSize(new Dimension(GameVars.WINDOWWIDTH, 50)); //button size
        yesButton.addActionListener(new ActionListener() { //on click
            public void actionPerformed(ActionEvent e) {
                pickSide();
            } //go to the next window
        });

        JButton noButton = new JButton("No"); //if refusal to play
        noButton.setPreferredSize(new Dimension(GameVars.WINDOWWIDTH, 50)); //set button size
        noButton.addActionListener(new ActionListener() { //if clicked
            public void actionPerformed(ActionEvent e) {
                hideQuest10();
            } //Close the window/exit
        });

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2)); //for two buttons
        buttonPanel.add(yesButton); //yes
        buttonPanel.add(noButton); //no
        buttonPanel.setBackground(Color.decode("#FF0000")); //make bkg red

        JPanel introAndButtonPanel = new JPanel(new GridLayout(2, 1)); //for text and buttons
        introAndButtonPanel.add(introText); //add the text
        introAndButtonPanel.add(buttonPanel); //add the buttons

        currentPanel.setLayout(new GridLayout(2, 1)); //for the thing above and title
        currentPanel.add(titlePanel); //add title
        currentPanel.add(introAndButtonPanel); //add the thing above

        currentPanel.revalidate(); //update panel
        currentPanel.repaint(); //update panel
    }

    public static void pickSide() { //window for user to select heads or tails
        currentPanel.removeAll(); //clear panel
        currentPanel.setSize(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT); //set new size
        currentPanel.setBackground(Color.decode("#5448C8")); //make it purple

        JLabel pickSideTitle = new JLabel("Pick Side"); //text
        pickSideTitle.setFont(Fonts.panicFont); //custom font
        pickSideTitle.setHorizontalAlignment(SwingConstants.CENTER); //center
        pickSideTitle.setAlignmentX(Component.CENTER_ALIGNMENT); //center
        //pickSideTitle.setPreferredSize(new Dimension(GameVars.WINDOWWIDTH, 200));
       // pickSideTitle.setSize(GameVars.WINDOWWIDTH, 200);

        JRadioButton headsButton = new JRadioButton("Heads"); //option 1
        headsButton.setFont(new Font("Arial Black", Font.PLAIN, 30)); //set size and font
        headsButton.setHorizontalAlignment(SwingConstants.CENTER); //center
        headsButton.setAlignmentX(Component.CENTER_ALIGNMENT); //center
        //headsButton.setPreferredSize(new Dimension(GameVars.WINDOWWIDTH, 100));
        JRadioButton tailsButton = new JRadioButton("Tails"); //option 2
        tailsButton.setFont(new Font("Arial Black", Font.PLAIN, 30)); //set size and font
        tailsButton.setHorizontalAlignment(SwingConstants.CENTER); //center
        tailsButton.setAlignmentX(Component.CENTER_ALIGNMENT); //center
        //tailsButton.setPreferredSize(new Dimension(GameVars.WINDOWWIDTH, 100));

        headsButton.setSelected(true); //as a default set heads to be selected

        ButtonGroup group = new ButtonGroup(); //button group makes it so that only
        //one button is selected at once in the group. This is so that heads and tails
        //cannot be selected at the same time
        group.add(headsButton); //add the heads
        group.add(tailsButton); //add the tails

        JPanel buttonOptionPanel = new JPanel(); //a panel with the two buttons
        buttonOptionPanel.setLayout(new GridLayout(1, 2)); //2 cols, side by side
        buttonOptionPanel.setBackground(Color.decode("#6967d6")); //purple background
        buttonOptionPanel.setBorder(BorderFactory.createLineBorder(Color.decode("#5448C8"), 5)); //purple border

        //buttonOptionPanel.setPreferredSize(new Dimension(GameVars.WINDOWWIDTH, 50));
        buttonOptionPanel.add(headsButton); //add heads
        buttonOptionPanel.add(tailsButton); //add tails

        JButton confirmButton = new JButton("Confirm"); //confirmation of choice
        //confirmButton.setBorderPainted(false);
        confirmButton.setOpaque(true); //for colors
        confirmButton.setBorder(BorderFactory.createLineBorder(Color.decode("#5448C8"), 5)); //for purple (slightly lighter) border
        confirmButton.setBackground(Color.decode("#6967d6")); //for purple background
        //confirmButton.setPreferredSize(new Dimension(GameVars.WINDOWWIDTH, 50));
        confirmButton.addActionListener(new ActionListener() { //on confirmation click
            public void actionPerformed(ActionEvent e) {
                if (headsButton.isSelected()) { //if heads
                    selectedSide = "heads"; //heads is selected
                } else if (tailsButton.isSelected()) { //if tails
                    selectedSide = "tails"; //tails is selected
                }
                rollResult(); //uses randomness
            }
        });

        //currentPanel.setLayout(new GridLayout(3, 1));
        currentPanel.setLayout(new GridLayout(3, 1)); //display
        currentPanel.add(pickSideTitle); //add first thing
        currentPanel.add(buttonOptionPanel); //second thing
        currentPanel.add(confirmButton); //and third thing

        currentPanel.revalidate(); //refresh panel
        currentPanel.repaint(); //refresh panel
    }

    public static void rollResult() { //roll the result
        currentPanel.removeAll(); //recreate panel
        currentPanel.setSize(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT); //size
        currentPanel.setBackground(Color.decode("#5448C8")); //make it purple

        JLabel resultTitle = new JLabel("Result"); //title
        resultTitle.setFont(Fonts.panicFont); //font
        /* the following code aligns */
        resultTitle.setHorizontalAlignment(SwingConstants.CENTER);
        resultTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        String result = Math.random() < 0.5 ? "heads" : "tails"; //randomly generate with equal chance
        JLabel resultLabel = new JLabel("The coin landed on: " + result); //result
        resultLabel.setFont(new Font("Arial", Font.PLAIN, 20)); //font and size
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER); //center
        resultLabel.setAlignmentX(Component.CENTER_ALIGNMENT); //center

        JEditorPane resultText = new JEditorPane(); //new display
        resultText.setOpaque(true); //for colors
        resultText.setBorder(BorderFactory.createLineBorder(Color.decode("#5448C8"), 5)); //border - purple
        resultText.setBackground(Color.white); //box - white
        resultText.setEditable(false); //not editable
        resultText.setFocusable(false); //not focuseable
        resultText.setContentType("text/html"); //html style
        resultText.setMargin(new Insets(5, 5, 5, 5)); //text border

        if (selectedSide.equals(result)) { //if correct
            resultText.setText("<html><body style='font-size: 20px; padding: 10px; font-family: Arial'>You won $100!</body></html>");
            GameVars.balance += 100; //WIN
        } else { //if incorrect
            resultText.setText("<html><body style='font-size: 20px; padding: 10px; font-family: Arial'>You lost $20.</body></html>");
            GameVars.balance -= 20; //LOSS - chance to go into debt
        }

        AnnaTools.Updater.updateAllSidePanels(); //update everything for new balance

        JButton okButton = new JButton("OK"); //alright
        okButton.setPreferredSize(new Dimension(GameVars.WINDOWWIDTH, 50)); //button size
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                hideQuest10(); //exit
            }
        });

        currentPanel.setLayout(new GridLayout(4, 1)); //add all components into panell
        currentPanel.add(resultTitle); //result
        currentPanel.add(resultLabel); //result desc
        currentPanel.add(resultText); //result desc
        currentPanel.add(okButton); //confirmation ok

        currentPanel.revalidate(); //refresh panel
        currentPanel.repaint(); //refresh panel
    }

    /* TESTING :
    public static void main(String[] args) {
        Quest10 quest10 = new Quest10();
        quest10.showQuest10();
    }*/
}
