package Game;

//IMPORT ALL PACKAGES -----
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;       //-
import java.util.*;     //-
import javax.swing.*;   //-
import Dungeon.*;       //-
import Items.*;         //-
import Monsters.*;      //-
import java.awt.*;      //-
import Shops.*;         //-
import AnnaTools.*;     //-

import static Game.HomeVillage.homeVillageFrame;
import static Game.HomeVillage.homeVillageSideBar;
import static Shops.WeaponShop.weaponShopFrame;
import static Shops.WeaponShop.weaponShopSideBar;
//-------------------------

public class Inventory {

    private static JFrame inventoryFrame = new JFrame();
    private static ArrayList<Items.Item> weaponItems = new ArrayList<>();
    private static ArrayList<Items.Item> armourItems = new ArrayList<>();
    private static JTextArea allInventoryItemsTextArea = new JTextArea();

    // String currentHealthItem = "DFSDFSDF";
    static String currWeaponString = "none";
    static String currArmourString = "none";

    //JLabel currentHealthItemLabel = new JLabel();
    static JLabel currentWeaponItemLabel = new JLabel();
    static JLabel currentArmourItemLabel = new JLabel();


    public Inventory() {

        inventoryFrame = new JFrame("By Anna Denisova");
        inventoryFrame.setSize(GameVars.WINDOWWIDTH + GameVars.SIDEBARWIDTH, GameVars.WINDOWHEIGHT);
        inventoryFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inventoryFrame.setLocationRelativeTo(null);
        inventoryFrame.getContentPane().setLayout(new BorderLayout());


        //inventoryFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        findWeaponsAndArmour(); //this function will go through Gamevars.inventory and will
        //find all the weapons and armour and add them to the respective lists weaponItems and
        //armourItems

        //-----------------------

        JPanel listPanel = new JPanel(new GridLayout(1, 1));
        JPanel currentItemPanel = new JPanel(new GridLayout(1, 2));
        JPanel selectionButtonPanel = new JPanel(new GridLayout(1, 2));
        JPanel buttonPanel = new JPanel(new BorderLayout());

        //---------------------------

        //these fns can also be used to just set the text straight away
        updateCurrentSelectedItems(); //this updates the little messages with what is currently selected
        updateItemsList(); //this updates the list of things in your inventory

        JButton weaponButton = new JButton("Select Weapon Item");
        weaponButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectItem( weaponItems, "Weapon");
            }
        });

        JButton armourButton = new JButton("Select Armour Item");
        armourButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectItem(armourItems, "Armour");
            }
        });

        JButton closeInventoryButton = new JButton("Close Inventory");
        closeInventoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hideInventory();
            }
        });


        JLabel inventoryTitleLabel = new JLabel("Inventory");
        inventoryTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        inventoryTitleLabel.setVerticalAlignment(SwingConstants.CENTER);
        inventoryTitleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        inventoryTitleLabel.setOpaque(true);
        inventoryTitleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        inventoryTitleLabel.setBackground(Color.decode("#fb4d3d")); //TOMATO RED


// Setup the current item panel
        currentItemPanel.add(currentWeaponItemLabel);
        currentItemPanel.add(currentArmourItemLabel);
        currentItemPanel.setOpaque(true);
        currentItemPanel.setBackground(Color.decode("#03cea4")); //MINT TEAL
        currentItemPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Consistent spacing

// Create the panel that will hold the title, separator, and current item panel
        JPanel titleAndCurrItemPanel = new JPanel(new GridLayout(2, 1));
        titleAndCurrItemPanel.add(inventoryTitleLabel);
        titleAndCurrItemPanel.add(currentItemPanel);
        titleAndCurrItemPanel.setBackground(Color.decode("#0B2027")); //BLACK
        titleAndCurrItemPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10)); // Consistent spacing

// Add titleAndCurrItemPanel to the frame
        inventoryFrame.add(titleAndCurrItemPanel, BorderLayout.NORTH);

// Setup the list panel
        listPanel.add(allInventoryItemsTextArea);
        allInventoryItemsTextArea.setEditable(false);
        allInventoryItemsTextArea.setFocusable(false);
        allInventoryItemsTextArea.setOpaque(true);
        allInventoryItemsTextArea.setBackground(Color.decode("#03cea4")); //MINT TEAL
        listPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10)); // Consistent spacing
        listPanel.setBackground(Color.decode("#0B2027")); //BLACK
        inventoryFrame.add(listPanel, BorderLayout.CENTER);

// Setup the button panel
        selectionButtonPanel.add(weaponButton);
        selectionButtonPanel.add(armourButton);
        selectionButtonPanel.setBackground(Color.decode("#0B2027")); //BLACK
        buttonPanel.add(selectionButtonPanel, BorderLayout.NORTH);
        buttonPanel.add(closeInventoryButton, BorderLayout.SOUTH);
        buttonPanel.setBackground(Color.decode("#0B2027")); //BLACK
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Consistent spacing
        inventoryFrame.add(buttonPanel, BorderLayout.SOUTH);



    }

    private static void selectItem(ArrayList<Item> items, String itemType) {
        if (items.isEmpty()) {
            JOptionPane.showMessageDialog(inventoryFrame,
                    "Cannot select " + itemType + " item because there are none.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        //make items strings too
        ArrayList<String> itemsString = new ArrayList<String>();
        itemsString.clear(); ///just added this when debugging
        for(Item item : items){
            itemsString.add(item.toString());
        }

        String selectedItem = (String) JOptionPane.showInputDialog(
                inventoryFrame,
                "Select " + itemType + " Item:",
                "Select Item",
                JOptionPane.PLAIN_MESSAGE,
                null,
                itemsString.toArray(),
                null);

        // Update selected item in the respective inventory
        switch (itemType) {
            case "Weapon":
                if(selectedItem != null){
                    currWeaponString = selectedItem;
                    for(Item item : items){
                        if(item.toString().equals(selectedItem)){
                            GameVars.currWeapon = (Weapon)item;
                            GameVars.fullAttackPower = ((Weapon) item).damage + GameVars.playerAttackPower;
                        }
                    }
                    updateCurrentSelectedItems();
                }
                break;
            case "Armour":
                if(selectedItem != null){
                    currArmourString = selectedItem;
                    for(Item item : items){
                        if(item.toString().equals(selectedItem)){
                            GameVars.currArmour = (Armour)item;
                            GameVars.fullDefensePower = ((Armour) item).damageSubtractorPercentage;
                        }
                    }
                    updateCurrentSelectedItems();
                }
                break;
        }
    }

    private static void updateCurrentSelectedItems(){
        currentWeaponItemLabel.setText("Current Weapon Item: " + currWeaponString);
        currentArmourItemLabel.setText("Current Armour Item: " + currArmourString);
    }

    private static void updateItemsList() {
        String allItemsTEXT = "\n    Inventory Items:\n";
        for (Items.Item item : GameVars.inventory) {
            allItemsTEXT += "   - " + item + "\n";
        }
        allInventoryItemsTextArea.setText(allItemsTEXT);
    }

    private static void findWeaponsAndArmour() {
        for (Item item : GameVars.inventory) {
            if (item instanceof Weapon) {
                weaponItems.add((Weapon)item);
            } else if (item instanceof Armour) {
                armourItems.add((Armour)item);
            }
        }
    }

    public static void showInventory(){
        updateItemsList();
        findWeaponsAndArmour();
        inventoryFrame.setVisible(true);
    }

    public static void hideInventory(){
        AnnaTools.Updater.updateAllSidePanels();
        inventoryFrame.setVisible(false);
    }

    public static void addItem(Item item){
        GameVars.inventory.add(item);
        AnnaTools.ItemInsertSort.insertSort(GameVars.inventory);
        findWeaponsAndArmour();
        updateItemsList();
    }

    public static void removeItem(Item item){
        GameVars.inventory.remove(item);
    }

}
