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

    public static JEditorPane mapEditorPane;

    public MapGUI() {

        mapFrame = new JFrame("Dungeon Map");
        mapFrame.setTitle("By Anna Denisova");
        mapFrame.setSize(GameVars.WINDOWWIDTH + GameVars.SIDEBARWIDTH, GameVars.WINDOWHEIGHT+310);
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
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game.HomeVillage.showHomeVillage();
                hideMapGUI();
            }
        });

        mapAsciiPanel.setPreferredSize(new Dimension(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT));

        mapFrame.add(mapAsciiPanel, BorderLayout.WEST);

        mapFrame.add(mapGUISideBar.getPanel(), BorderLayout.EAST);

        mapFrame.setPreferredSize(new Dimension(GameVars.WINDOWWIDTH + GameVars.SIDEBARWIDTH, GameVars.WINDOWHEIGHT+310));
        mapFrame.pack();
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
    }

}
