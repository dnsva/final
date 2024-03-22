package Game;

import javax.swing.*;
import java.awt.*;

import GameSlots.SlotInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeVillage {

    private static JFrame homeVillageFrame;

    public HomeVillage() {

        //init all classes here:
        new Shops.WeaponShop();
        //-----------

        homeVillageFrame = new JFrame("Home Village");
        homeVillageFrame.setTitle("By Anna Denisova");
        homeVillageFrame.setSize(GameVars.WINDOWWIDTH + GameVars.SIDEBARWIDTH, GameVars.WINDOWHEIGHT+310);
        homeVillageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        homeVillageFrame.setLocationRelativeTo(null);
        homeVillageFrame.getContentPane().setLayout(new BorderLayout());

        JPanel homeVillagePanel = new JPanel();
        homeVillagePanel.setLayout(new BoxLayout(homeVillagePanel, BoxLayout.Y_AXIS));


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

       // homeVillagePanel.add(Box.createVerticalGlue());
        homeVillagePanel.add(apothecaryButton);
        homeVillagePanel.add(armourShopButton);
        homeVillagePanel.add(foodMarket);
        homeVillagePanel.add(weaponShopButton);
        homeVillagePanel.add(dungeonButton);
        homeVillagePanel.add(exitButton);





        homeVillageFrame.add(homeVillagePanel, BorderLayout.WEST);
        homeVillageFrame.add(SideBar.getPanel(), BorderLayout.EAST);

        homeVillageFrame.setVisible(true);

    }

    public static void showHomeVillage(){
        homeVillageFrame.setVisible(true);
    }

    public static void hideHomeVillage(){

        homeVillageFrame.setVisible(false);

    }

    public static void main(String[] args){
        new GameSlots.SlotInfo();
        new HomeVillage();
    }

}
