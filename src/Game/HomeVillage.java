package Game;

import javax.swing.*;
import java.awt.*;

import Dungeon.MapGUI;
import GameSlots.SlotInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeVillage {

    public static JFrame homeVillageFrame;

    public static SideBar homeVillageSideBar = new SideBar();

    public HomeVillage() {

        new Inventory(); //this is a frame, taken care of by sidebar
        // homeVillageSideBar = new SideBar(); //this is a panel. this is added to other frames as needed
        homeVillageSideBar = new SideBar();

        /* TEMPOARRYAYRYRY */
        GameVars.inventory.add(new Items.Weapon("Sword", 10, "sdfsdf", 10, 10));
        GameVars.inventory.add(new Items.Weapon("Axe", 10, "sdfsd", 10, 10));
        GameVars.inventory.add(new Items.Armour("Helmet", 10, 10));

        //init all necessary classes here:
        //new Shops.WeaponShop();
        //then also add all other shops here too

        new MapGUI(); //again, init all necessary classes


        //-----------

        homeVillageFrame = new JFrame("Home Village");
        homeVillageFrame.setTitle("By Anna Denisova");
        homeVillageFrame.setSize(GameVars.WINDOWWIDTH + GameVars.SIDEBARWIDTH, GameVars.WINDOWHEIGHT);
        homeVillageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        homeVillageFrame.setLocationRelativeTo(null);
        homeVillageFrame.getContentPane().setLayout(new BorderLayout());

        JPanel homeVillagePanel = new JPanel();
        homeVillagePanel.setLayout(new BoxLayout(homeVillagePanel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Village");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setForeground(Color.white);

        homeVillagePanel.add(Box.createVerticalStrut(20)); //add space between
        homeVillagePanel.add(titleLabel);
        homeVillagePanel.add(Box.createVerticalStrut(20)); //add space between
        homeVillagePanel.setBackground(Color.blue);


        JButton apothecaryButton = new JButton("Apothecary"); //NOT IMPLEMENTED
        JButton armourShopButton = new JButton("Armour Shop"); //NOT IMPLEMENTED
        JButton foodMarket = new JButton("Food Market"); //NOT IMPLEMENTED
        JButton weaponShopButton = new JButton("Weapon Shop"); //IMPLEMENTED
        weaponShopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //new Shops.WeaponShop();
                Shops.WeaponShop.showWeaponShop();
                hideHomeVillage();
            }
        });

        JButton dungeonButton = new JButton("Dungeon");
        dungeonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MapGUI.showMapGUI();
                hideHomeVillage();
            }
        });

        JButton exitButton = new JButton("Exit"); //IMPLEMENTED
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameSlots.SlotInfo.showInfoFrame();
                hideHomeVillage();
            }
        });

        JButton saveGameButton = new JButton("Save Game");
        saveGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //to be implemented
            }
        });

        JButton[] buttons = {apothecaryButton, armourShopButton, foodMarket, weaponShopButton, dungeonButton, exitButton, saveGameButton};
        for(JButton button : buttons){
            button.setAlignmentX(Component.CENTER_ALIGNMENT); //Align the button to center
            homeVillagePanel.add(button); //ADD ALL THE BUTTONS TO THE PANNEL
            homeVillagePanel.add(Box.createVerticalStrut(10)); //create space in between each button
        }

        homeVillagePanel.setPreferredSize(new Dimension(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT)); //idk what this changes

        homeVillageFrame.add(homeVillagePanel, BorderLayout.CENTER);

        homeVillageFrame.add(homeVillageSideBar.getPanel(), BorderLayout.EAST);

        homeVillageFrame.setPreferredSize(new Dimension(GameVars.WINDOWWIDTH + GameVars.SIDEBARWIDTH, GameVars.WINDOWHEIGHT));
        homeVillageFrame.pack();
       // homeVillageFrame.setVisible(true);
    }
    public static void showHomeVillage(){
        homeVillageFrame.setVisible(true);
    }

    public static void hideHomeVillage(){
        homeVillageFrame.setVisible(false);
    }

    public static void main(String[] args){
        //new GameSlots.SlotInfo();
       // new HomeVillage();
        //new SideBar();
       // showHomeVillage();
    }
}
