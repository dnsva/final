package Dungeon;

import Game.GameVars;
import Game.Inventory;
import Game.SideBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MapGUI {


    private static JFrame mapFrame;
    public static SideBar mapGUISideBar = new SideBar();
    boolean exitFlag; //this is used to check if the exit button has been pressed
    public static JEditorPane mapEditorPane;

    public MapGUI() {

        mapFrame = new JFrame("Dungeon Map");
        mapFrame.setTitle("By Anna Denisova");
        mapFrame.setSize(GameVars.WINDOWWIDTH + GameVars.SIDEBARWIDTH, GameVars.WINDOWHEIGHT);
        mapFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mapFrame.setLocationRelativeTo(null);
        mapFrame.getContentPane().setLayout(new BorderLayout());

        JPanel mapAsciiPanel = new JPanel();
        mapAsciiPanel.setLayout(new BoxLayout(mapAsciiPanel, BoxLayout.Y_AXIS));
        //mapAsciiPanel.setSize(600, GameVars.WINDOWHEIGHT);

        JLabel titleLabel = new JLabel("Map");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setForeground(Color.white);

        mapAsciiPanel.add(Box.createVerticalStrut(20)); //add space ebtween
        mapAsciiPanel.add(titleLabel);
        mapAsciiPanel.add(Box.createVerticalStrut(20)); //add space ebtween
        mapAsciiPanel.setBackground(Color.blue);


        mapEditorPane = new JEditorPane();
        mapEditorPane.setContentType("text/html");
        mapEditorPane.setText(Map.getMapAsciiString());
        mapEditorPane.setEditable(false);
        mapAsciiPanel.add(mapEditorPane);

        JButton exitButton = new JButton("Exit");

        /*
        AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA

        DO THIS
        JButton b=new JButton(new ImageIcon("D:\\icon.png"));
         */
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Game.HomeVillage.showHomeVillage(); //UNCOMMENT THSI ALTER
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

        buttonPanel.add(leftButton);

        JPanel buttonPanelUpDown = new JPanel(new GridLayout(2, 1));
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

        //mapFrame.add(exitButton, BorderLayout.SOUTH);

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
        Map m = new Map();
        m.moveRight();
        m.moveRight();
        m.moveRight();
        m.moveRight();
        updateMapGUIAscii();
        showMapGUI();

        //while(true){
          //  m.moveRight();
            //updateMapGUIAscii();
        //}
    }

}
