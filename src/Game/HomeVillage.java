package Game;

//IMPORT ALL PACKAGES -----
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;       //-
import java.util.*;     //-
import javax.swing.*;   //-
import Dungeon.*;       //-
import java.awt.*;      //-
import Items.*;         //-
import Monsters.*;      //-
import Shops.*;         //-
import AnnaTools.*;     //-
//-------------------------

public class HomeVillage {

    public static JFrame homeVillageFrame = new JFrame();
    public static SideBar homeVillageSideBar;
    public static JPanel homeVillagePanel;

    public HomeVillage(){

        new Inventory(); //This is the display of the inventory. This has its JFrame
        homeVillageSideBar = new SideBar(); //Create an instance for this Frame

        new Shops.WeaponShop(); //Also construct the weapon shop
        //Add other shops here too
        //. . .

        new MapGUI(); //Create the map GUI for Map

        //--------------------------------------------------------------------------------

        homeVillageFrame = new JFrame("By Anna Denisova");
       // homeVillageFrame.setTitle("By Anna Denisova");
        homeVillageFrame.setSize(GameVars.WINDOWWIDTH + GameVars.SIDEBARWIDTH, GameVars.WINDOWHEIGHT);
        homeVillageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        homeVillageFrame.setLocationRelativeTo(null);
        homeVillageFrame.getContentPane().setLayout(new BorderLayout());

        homeVillagePanel = new JPanel();
        homeVillagePanel.setSize(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT);
        homeVillagePanel.setLayout(new BoxLayout(homeVillagePanel, BoxLayout.Y_AXIS));
        homeVillagePanel.setBackground(Color.decode("#C2F9BB"));

        JLabel titleLabel = new JLabel("Village");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        //titleLabel.setForeground(Color.white);

        homeVillagePanel.add(Box.createVerticalStrut(20)); //add space between
        homeVillagePanel.add(titleLabel);
        homeVillagePanel.add(Box.createVerticalStrut(20)); //add space between

        JButton apothecaryButton = new JButton("Apothecary");
        apothecaryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TO BE IMPLEMENTED
                //Shops.Apothecary.showApothecary();
                // hideHomeVillage();
            }
        });
        JButton armourShopButton = new JButton("Armour Shop");
        armourShopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TO BE IMPLEMENTED
                //Shops.ArmourShop.showArmourShop();
                // hideHomeVillage();
            }
        });
        JButton foodMarket = new JButton("Food Market");
        foodMarket.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TO BE IMPLEMENTED
                //Shops.FoodMarket.showFoodMarket();
                // hideHomeVillage();
            }
        });
        JButton weaponShopButton = new JButton("Weapon Shop");
        weaponShopButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {;
                Shops.WeaponShop.showWeaponShop();
                hideHomeVillage();
            }
        });
        JButton dungeonButton = new JButton("Dungeon");
        dungeonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MapGUI.showMapGUI();
                //hideHomeVillage();
            }
        });
        JButton exitButton = new JButton("Exit");
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
                //TO BE IMPLEMENTED
            }
        });

        JButton[] buttons = {apothecaryButton, armourShopButton, foodMarket, weaponShopButton, dungeonButton, exitButton, saveGameButton};
        for(JButton button : buttons){
            button.setAlignmentX(Component.CENTER_ALIGNMENT); //Align the button to center
            homeVillagePanel.add(button); //ADD ALL THE BUTTONS TO THE PANEL
            homeVillagePanel.add(Box.createVerticalStrut(10)); //create space in between each button
        }

        homeVillageFrame.add(homeVillagePanel, BorderLayout.CENTER);

        JSeparator separator = new JSeparator(SwingConstants.VERTICAL);
        homeVillageFrame.add(homeVillageSideBar.getPanel(), BorderLayout.EAST);

        //homeVillageFrame.pack();

    }

    public static void showHomeVillage(){
        homeVillageFrame.setVisible(true);
    }

    public static void hideHomeVillage(){
        homeVillageFrame.setVisible(false);
    }


}
