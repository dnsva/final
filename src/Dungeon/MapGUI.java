package Dungeon;

//IMPORT ALL PACKAGES -----
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;       //-
import java.util.*;     //-
import java.awt.*;      //-
import javax.swing.*;   //-
import Game.*;          //-
import Items.*;         //-
import Monsters.*;      //-
import Shops.*;         //-
import AnnaTools.*;     //-
//-------------------------

public class MapGUI {


    private static JFrame mapFrame;
    public static SideBar mapGUISideBar = new SideBar();
    boolean exitFlag; //this is used to check if the exit button has been pressed
    public static JEditorPane mapEditorPane, mapLegendEditorPane, mapTitleEditorPane;

    public MapGUI() {

        mapFrame = new JFrame("Dungeon Map");
        mapFrame.setTitle("By Anna Denisova");
        mapFrame.setSize(GameVars.WINDOWWIDTH + GameVars.SIDEBARWIDTH, GameVars.WINDOWHEIGHT);
        mapFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mapFrame.setLocationRelativeTo(null);
        mapFrame.getContentPane().setLayout(new BorderLayout());
        mapFrame.setBackground(Color.black);
        mapFrame.getContentPane().setBackground(Color.black);

        JPanel mapAsciiPanel = new JPanel();
        mapAsciiPanel.setBackground(Color.black);
        mapAsciiPanel.setLayout(new BoxLayout(mapAsciiPanel, BoxLayout.Y_AXIS));
        //mapAsciiPanel.setSize(600, GameVars.WINDOWHEIGHT);

       // JLabel titleLabel = new JLabel("Map");
       // titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
      //  titleLabel.setForeground(Color.white);

      //  mapAsciiPanel.add(Box.createVerticalStrut(20)); //add space ebtween
      //  mapAsciiPanel.add(titleLabel);
      //  mapAsciiPanel.add(Box.createVerticalStrut(20)); //add space ebtween
      //  mapAsciiPanel.setBackground(Color.blue);


        mapEditorPane = new JEditorPane();
        mapEditorPane.setContentType("text/html");
        mapEditorPane.setText(Map.getMapAsciiString());
        mapEditorPane.setEditable(false);
        mapEditorPane.setBackground(Color.black);

        mapLegendEditorPane = new JEditorPane();
        mapLegendEditorPane.setContentType("text/html");
        mapLegendEditorPane.setText(Map.getMapLegend());
        mapLegendEditorPane.setEditable(false);
        mapLegendEditorPane.setBackground(Color.black);

        mapTitleEditorPane = new JEditorPane();
        mapTitleEditorPane.setContentType("text/html");
        mapTitleEditorPane.setText(Map.getMapTitle());
        mapTitleEditorPane.setEditable(false);
        mapTitleEditorPane.setBackground(Color.black);

        mapAsciiPanel.add(mapTitleEditorPane);
        mapAsciiPanel.add(mapEditorPane);
        mapAsciiPanel.add(mapLegendEditorPane);

        JButton exitButton = new JButton("Exit");

        /*
        AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA

        DO THIS
        JButton b=new JButton(new ImageIcon("D:\\icon.png"));
         */
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game.HomeVillage.showHomeVillage();
                hideMapGUI();

            }
        });

        JButton upButton = new JButton("Up");
        upButton.setSize(100, 100);
        upButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Map.moveUp();
                updateMapGUIAscii();
            }
        });

        JButton downButton = new JButton("Down");
        downButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Map.moveDown();
                updateMapGUIAscii();
            }
        });

        JButton leftButton = new JButton("Left");
        leftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Map.moveLeft();
                updateMapGUIAscii();
            }
        });

        JButton rightButton = new JButton("Right");
        rightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Map.moveRight();
                updateMapGUIAscii();
            }
        });

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
        buttonPanel.setBackground(Color.black);

        buttonPanel.add(leftButton);

        JPanel buttonPanelUpDown = new JPanel(new GridLayout(2, 1));
        buttonPanelUpDown.setBackground(Color.black);
        buttonPanelUpDown.add(upButton);
        buttonPanelUpDown.add(downButton);

        buttonPanel.add(buttonPanelUpDown);

        //rightButton.setSize(100, 100);
        buttonPanel.add(rightButton);

       // buttonPanel.add(exitButton);

        //---------------------

        mapAsciiPanel.setPreferredSize(new Dimension(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT));

        mapAsciiPanel.add(buttonPanel);

        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mapAsciiPanel.add(exitButton);


        mapFrame.add(mapAsciiPanel, BorderLayout.WEST);


        //make the sidebar labels white for this one
        mapGUISideBar.healthLabel.setForeground(Color.white);
        mapGUISideBar.sanityLabel.setForeground(Color.white);
        mapGUISideBar.hungerLabel.setForeground(Color.white);
        mapGUISideBar.balanceLabel.setForeground(Color.white);
        mapGUISideBar.weaponLabel.setForeground(Color.white);
        mapGUISideBar.armourLabel.setForeground(Color.white);
        mapGUISideBar.attackLabel.setForeground(Color.white);
        mapGUISideBar.defenseLabel.setForeground(Color.white);
        mapGUISideBar.dayLabel.setForeground(Color.white);



        mapGUISideBar.setColor("#000000");
        mapGUISideBar.updatePanel();

        mapFrame.add(mapGUISideBar.getPanel(), BorderLayout.EAST);

        mapFrame.setPreferredSize(new Dimension(GameVars.WINDOWWIDTH + GameVars.SIDEBARWIDTH, GameVars.WINDOWHEIGHT));
        //mapFrame.pack();
    }

    public static void showMapGUI(){
        mapFrame.setVisible(true);
    }

    public static void hideMapGUI(){

        mapFrame.setVisible(false);

    }

    public static void updateMapGUIAscii(){
        mapEditorPane.setText(Map.getMapAsciiString());
    }
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
    }

}
