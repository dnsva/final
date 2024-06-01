package Dungeon;

/*
name: Anna
date: May 31, 2024
title: MapGUI
description:  This class creates the map GUI.
*/

//IMPORT ALL PACKAGES -----
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;      //-
import javax.swing.*;   //-
import Game.*;          //-
//-------------------------

public class MapGUI {

    private static JFrame mapFrame; //the frame with the ascii display AND sidebar
    public static SideBar mapGUISideBar = new SideBar(); //local sidebar
    boolean exitFlag; //this is used to check if the exit button has been pressed
    public static JEditorPane mapEditorPane, mapLegendEditorPane, mapTitleEditorPane; //the different displays

    public MapGUI() {

        mapFrame = new JFrame(); //initialize
        mapFrame.setTitle("By Anna Denisova"); //set title
        mapFrame.setSize(GameVars.WINDOWWIDTH + GameVars.SIDEBARWIDTH, GameVars.WINDOWHEIGHT); //set size
        mapFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //close setting
        mapFrame.setLocationRelativeTo(null); //center
        mapFrame.getContentPane().setLayout(new BorderLayout()); //make layout border
        mapFrame.setBackground(Color.black); //set background all black
        mapFrame.getContentPane().setBackground(Color.black); //set background all black

        JPanel mapAsciiPanel = new JPanel(); //new panel for all the ascii stuff
        mapAsciiPanel.setBackground(Color.black); //again, black background
        mapAsciiPanel.setLayout(new BoxLayout(mapAsciiPanel, BoxLayout.Y_AXIS)); //make everything go after the previous thing. vertical layout
        //mapAsciiPanel.setSize(600, GameVars.WINDOWHEIGHT);

       // JLabel titleLabel = new JLabel("Map");
       // titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
      //  titleLabel.setForeground(Color.white);

      //  mapAsciiPanel.add(Box.createVerticalStrut(20)); //add space ebtween
      //  mapAsciiPanel.add(titleLabel);
      //  mapAsciiPanel.add(Box.createVerticalStrut(20)); //add space ebtween
      //  mapAsciiPanel.setBackground(Color.blue);

        mapEditorPane = new JEditorPane(); //initiallize
        mapEditorPane.setContentType("text/html"); //make format be html
        mapEditorPane.setText(Map.getMapAsciiString()); //get the ascii map string from the map class
        mapEditorPane.setEditable(false); //cannot be editable
        mapEditorPane.setBackground(Color.black); //set background black as always

        mapLegendEditorPane = new JEditorPane(); //initialize
        mapLegendEditorPane.setContentType("text/html"); //make the format be html here too
        mapLegendEditorPane.setText(Map.getMapLegend()); //use map function again
        mapLegendEditorPane.setEditable(false); //cannot be editable
        mapLegendEditorPane.setBackground(Color.black); //set background black as always again

        mapTitleEditorPane = new JEditorPane(); //intialize
        mapTitleEditorPane.setContentType("text/html"); //make format be html...
        mapTitleEditorPane.setText(Map.getMapTitle()); //use map class function to get the ascii art
        mapTitleEditorPane.setEditable(false); //cannot be editable
        mapTitleEditorPane.setBackground(Color.black); //set panel background to be black

        mapAsciiPanel.add(mapTitleEditorPane); //add the title at the top
        mapAsciiPanel.add(mapEditorPane); //then the actual map
        mapAsciiPanel.add(mapLegendEditorPane); //and finally just the legend

        JButton exitButton = new JButton("Exit"); //for going back to main menu

        /*
        AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA

        DO THIS
        JButton b=new JButton(new ImageIcon("D:\\icon.png"));
         */
        exitButton.addActionListener(new ActionListener() { //when exit clicked
            @Override
            public void actionPerformed(ActionEvent e) { //on click override
                Game.HomeVillage.showHomeVillage(); //go back to the home village
                hideMapGUI(); //hide all the map stuff
            }
        });

        JButton upButton = new JButton("Up"); //button to go up
        upButton.setSize(100, 100); //size
        upButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Map.moveUp(); //go up
                updateMapGUIAscii(); //update display
            }
        });

        JButton downButton = new JButton("Down"); //button to go down
        downButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Map.moveDown(); //go down
                updateMapGUIAscii(); // update display
            }
        });

        JButton leftButton = new JButton("Left"); //button to go left
        leftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Map.moveLeft(); //move left
                updateMapGUIAscii(); //update display
            }
        });

        JButton rightButton = new JButton("Right"); //button to go right
        rightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Map.moveRight(); //go right
                updateMapGUIAscii(); //update display
            }
        });

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3)); //left, up/down, right
        buttonPanel.setBackground(Color.black); //black bg

        buttonPanel.add(leftButton);  //add it at the left

        JPanel buttonPanelUpDown = new JPanel(new GridLayout(2, 1)); //for up and down
        buttonPanelUpDown.setBackground(Color.black);
        buttonPanelUpDown.add(upButton); //up at the top
        buttonPanelUpDown.add(downButton); //down at the bottom

        buttonPanel.add(buttonPanelUpDown); //add up down collectively in col 2

        buttonPanel.add(rightButton); //add right in column 3

        //---------------------

        mapAsciiPanel.setPreferredSize(new Dimension(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT)); //size

        mapAsciiPanel.add(buttonPanel); //up down left right

        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT); //centered

        JPanel exitAndHelpPanel = new JPanel(new GridLayout(2, 1)); //exit is on top help is on the bottom

        JButton helpButton = new JButton("Help"); //specific to the dungeon
        helpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //create a pop up that says:
                JOptionPane.showMessageDialog(null, "Use the buttons to move.");
            }
        });
        //mapAsciiPanel.add(helpButton);
        helpButton.setAlignmentX(Component.CENTER_ALIGNMENT); //center
        helpButton.setHorizontalAlignment(SwingConstants.CENTER); //center

        exitAndHelpPanel.add(exitButton); //add the exit
        exitAndHelpPanel.add(helpButton); //add the help

        exitAndHelpPanel.setBackground(Color.black); //you know how it goes

        mapAsciiPanel.add(exitAndHelpPanel); //add these both to the entire panel

        mapFrame.add(mapAsciiPanel, BorderLayout.WEST); //all the ascii stuff goes left

        /* This makes all the sidebar labels white because the background on the sidebar will be black */
        mapGUISideBar.healthLabel.setForeground(Color.white);
        mapGUISideBar.sanityLabel.setForeground(Color.white);
        mapGUISideBar.hungerLabel.setForeground(Color.white);
        mapGUISideBar.balanceLabel.setForeground(Color.white);
        mapGUISideBar.weaponLabel.setForeground(Color.white);
        mapGUISideBar.armourLabel.setForeground(Color.white);
        mapGUISideBar.attackLabel.setForeground(Color.white);
        mapGUISideBar.defenseLabel.setForeground(Color.white);
        mapGUISideBar.dayLabel.setForeground(Color.white);

        mapGUISideBar.setColor("#000000"); //make the sidebar black
        mapGUISideBar.updatePanel(); //update to show changes

        mapFrame.add(mapGUISideBar.getPanel(), BorderLayout.EAST); //add the sidebar

        mapFrame.setPreferredSize(new Dimension(GameVars.WINDOWWIDTH + GameVars.SIDEBARWIDTH, GameVars.WINDOWHEIGHT)); //size
        //mapFrame.pack();
    }

    public static void showMapGUI(){ //show the frame
        mapFrame.setVisible(true); //show the frame
    }

    public static void hideMapGUI(){ //hide the frame
        mapFrame.setVisible(false); //hide the frame
    }

    public static void updateMapGUIAscii(){ //reset all the text with each move
        mapEditorPane.setText(Map.getMapAsciiString()); //just setText again
    }

    /* FOR TESTING
    public static void main(String[] args){
        //new GameSlots.SlotInfo();

        // new HomeVillage();
        //new SideBar();
        // showHomeVillage();

        new MapGUI();
        //Map m = new Map();
        //m.moveRight();
        //m.moveRight();
        //m.moveRight();
        //m.moveRight();
        updateMapGUIAscii();
        showMapGUI();

       // Map.quest10Object.showQuest10();

        //while(true){
          //  m.moveRight();
            //updateMapGUIAscii();
        //}
    }*/

}
