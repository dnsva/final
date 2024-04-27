package Game;

import javax.swing.*;
import java.awt.*;

import GameSlots.SlotInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeVillage {

    private static JFrame homeVillageFrame;
    public static SideBar homeVillageSideBar = new SideBar();

    public HomeVillage() {

        new Inventory(); //this is a frame, taken care of by sidebar

        // homeVillageSideBar = new SideBar(); //this is a panel. this is added to other frames as needed

        //init all necessary classes here:
        //new Shops.WeaponShop();
        //then also add all other shops here too


        //-----------

        homeVillageFrame = new JFrame("Home Village");
        homeVillageFrame.setTitle("By Anna Denisova");
        homeVillageFrame.setSize(GameVars.WINDOWWIDTH + GameVars.SIDEBARWIDTH, GameVars.WINDOWHEIGHT+310);
        homeVillageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        homeVillageFrame.setLocationRelativeTo(null);
        homeVillageFrame.getContentPane().setLayout(new BorderLayout());

        JPanel homeVillagePanel = new JPanel();
        homeVillagePanel.setLayout(new BoxLayout(homeVillagePanel, BoxLayout.Y_AXIS));
        //homeVillagePanel.setSize(600, GameVars.WINDOWHEIGHT);

        JLabel titleLabel = new JLabel("Village");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setForeground(Color.white);

        homeVillagePanel.add(Box.createVerticalStrut(20)); //add space ebtween
        homeVillagePanel.add(titleLabel);
        homeVillagePanel.add(Box.createVerticalStrut(20)); //add space ebtween
        homeVillagePanel.setBackground(Color.blue);


        JButton apothecaryButton = new JButton("Apothecary");
        JButton armourShopButton = new JButton("Armour Shop");
        JButton foodMarket = new JButton("Food Market");
        JButton weaponShopButton = new JButton("Weapon Shop");
        weaponShopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //new Shops.WeaponShop();
                Shops.WeaponShop.showWeaponShop();
                hideHomeVillage();
            }
        });

        JButton dungeonButton = new JButton("Dungeon");

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameSlots.SlotInfo.showInfoFrame();
                hideHomeVillage();
            }
        });

        JButton[] buttons = {apothecaryButton, armourShopButton, foodMarket, weaponShopButton, dungeonButton, exitButton};
        for(JButton button : buttons){
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            homeVillagePanel.add(button);
            homeVillagePanel.add(Box.createVerticalStrut(10));
        }

        homeVillagePanel.setPreferredSize(new Dimension(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT));

        homeVillageFrame.add(homeVillagePanel, BorderLayout.WEST);

        homeVillageFrame.add(homeVillageSideBar.getPanel(), BorderLayout.EAST);
        //homeVillageFrame.add(SideBar.sideBarPanel, BorderLayout.EAST);

        //1!//homeVillageFrame.add(SideBar.getPanel(), BorderLayout.CENTER);

        homeVillageFrame.setPreferredSize(new Dimension(GameVars.WINDOWWIDTH + GameVars.SIDEBARWIDTH, GameVars.WINDOWHEIGHT+310));
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
