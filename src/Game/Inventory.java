package Game;

//IMPORT ALL PACKAGES -----
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;       //-
import java.util.*;     //-
import javax.swing.*;   //-
import Dungeon.*;       //-
import Items.*;         //-
import java.awt.*;      //-
import Shops.*;         //-
import AnnaTools.*;     //-

import static Game.HomeVillage.homeVillageFrame;
import static Game.HomeVillage.homeVillageSideBar;
import static Shops.WeaponShop.weaponShopFrame;
import static Shops.WeaponShop.weaponShopSideBar;
//-------------------------

public class Inventory {

    private static JFrame inventoryFrame = new JFrame(); //the inventory frame
    private static ArrayList<Items.Item> weaponItems = new ArrayList<>(); //the weapon items
    private static ArrayList<Items.Item> armourItems = new ArrayList<>(); //the armour items
    private static JTextArea allInventoryItemsTextArea = new JTextArea(); //the text area for all the items

    // String currentHealthItem = "DFSDFSDF";
    static String currWeaponString = "none"; //the current weapon string
    static String currArmourString = "none"; //the current armour string

    //JLabel currentHealthItemLabel = new JLabel();
    static JLabel currentWeaponItemLabel = new JLabel(); //the current weapon label
    static JLabel currentArmourItemLabel = new JLabel(); //the current armour label


    public Inventory() {

        inventoryFrame = new JFrame("By Anna Denisova"); //initialize
        inventoryFrame.setSize(GameVars.WINDOWWIDTH + GameVars.SIDEBARWIDTH, GameVars.WINDOWHEIGHT); //set size
        inventoryFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //close setting
        inventoryFrame.setLocationRelativeTo(null); //center
        inventoryFrame.getContentPane().setLayout(new BorderLayout()); //make layout border


        //inventoryFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        findWeaponsAndArmour(); //this function will go through Gamevars.inventory and will
        //find all the weapons and armour and add them to the respective lists weaponItems and
        //armourItems

        //-----------------------

        //make the layouts grid and border:

        JPanel listPanel = new JPanel(new GridLayout(1, 1));
        JPanel currentItemPanel = new JPanel(new GridLayout(1, 2));
        JPanel selectionButtonPanel = new JPanel(new GridLayout(1, 2));
        JPanel buttonPanel = new JPanel(new BorderLayout());

        //---------------------------

        //these fns can also be used to just set the text straight away
        updateCurrentSelectedItems(); //this updates the little messages with what is currently selected
        updateItemsList(); //this updates the list of things in your inventory

        JButton weaponButton = new JButton("Select Weapon Item"); //button to select weapon
        weaponButton.addActionListener(new ActionListener() { //on click
            @Override
            public void actionPerformed(ActionEvent e) {
                selectItem( weaponItems, "Weapon");
            } //select weapon
        });

        JButton armourButton = new JButton("Select Armour Item"); //button to select armour
        armourButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectItem(armourItems, "Armour");
            }
        });

        JButton closeInventoryButton = new JButton("Close Inventory"); //button to close inventory
        closeInventoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hideInventory();
            }
        });


        JLabel inventoryTitleLabel = new JLabel("Inventory"); //label for the title
        inventoryTitleLabel.setHorizontalAlignment(SwingConstants.CENTER); //center
        inventoryTitleLabel.setVerticalAlignment(SwingConstants.CENTER); //center
        inventoryTitleLabel.setFont(new Font("Arial", Font.BOLD, 20)); //set font
        inventoryTitleLabel.setOpaque(true); //set opaque
        inventoryTitleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Consistent spacing
        inventoryTitleLabel.setBackground(Color.decode("#fb4d3d")); //TOMATO RED


        // Setup the current item panel
        currentItemPanel.add(currentWeaponItemLabel); //add the current weapon label
        currentItemPanel.add(currentArmourItemLabel); //add the current armour label
        currentItemPanel.setOpaque(true); //set opaque
        currentItemPanel.setBackground(Color.decode("#03cea4")); //MINT TEAL
        currentItemPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Consistent spacing

        // Create the panel that will hold the title, separator, and current item panel
        JPanel titleAndCurrItemPanel = new JPanel(new GridLayout(2, 1));
        titleAndCurrItemPanel.add(inventoryTitleLabel); // Add the title label to the title panel
        titleAndCurrItemPanel.add(currentItemPanel); // Add the current item panel to the title panel
        titleAndCurrItemPanel.setBackground(Color.decode("#0B2027")); //BLACK
        titleAndCurrItemPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10)); // Consistent spacing

        // Add titleAndCurrItemPanel to the frame
        inventoryFrame.add(titleAndCurrItemPanel, BorderLayout.NORTH);

        // Setup the list panel
        listPanel.add(allInventoryItemsTextArea); // Add the text area to the list panel
        allInventoryItemsTextArea.setEditable(false); // Prevents the text area from being edited
        allInventoryItemsTextArea.setFocusable(false); // Prevents the text area from being selected
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

    private static void selectItem(ArrayList<Item> items, String itemType) { //this function will be called when you click on the select weapon or select armour button
        if (items.isEmpty()) { //if there are no items
            JOptionPane.showMessageDialog(inventoryFrame,
                    "Cannot select " + itemType + " item because there are none.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE); //show error message
            return;
        }

        //make items strings too
        ArrayList<String> itemsString = new ArrayList<String>(); //initialize
        itemsString.clear(); ///just added this when debugging
        for(Item item : items){ //for all items
            itemsString.add(item.toString()); //convert them to strings and add them
        }

        String selectedItem = (String) JOptionPane.showInputDialog( //show the dialog
                inventoryFrame,
                "Select " + itemType + " Item:",
                "Select Item",
                JOptionPane.PLAIN_MESSAGE,
                null,
                itemsString.toArray(),
                null);

        // Update selected item in the respective inventory
        switch (itemType) { //switch on the item type
            case "Weapon": //if weapon
                if(selectedItem != null){ //if there is a selected item
                    currWeaponString = selectedItem; //set the current weapon string to the selected item
                    for(Item item : items){ //for all items
                        if(item.toString().equals(selectedItem)){ //if the item is the selected item
                            GameVars.currWeapon = (Weapon)item; //set the current weapon to the selected item
                            GameVars.fullAttackPower = ((Weapon) item).damage + GameVars.playerAttackPower; //set the full attack power
                        }
                    }
                    updateCurrentSelectedItems(); //update the current selected items
                }
                break;
            case "Armour": //if armour
                if(selectedItem != null){ //if there is a selected item
                    currArmourString = selectedItem; //set the current armour string to the selected item
                    for(Item item : items){ //for all items
                        if(item.toString().equals(selectedItem)){ //if the item is the selected item
                            GameVars.currArmour = (Armour)item; //set the current armour to the selected item
                            GameVars.fullDefensePower = ((Armour) item).damageSubtractorPercentage; //set the full defense power
                        }
                    }
                    updateCurrentSelectedItems(); //update the current selected items
                }
                break;
        }
    }

    private static void updateCurrentSelectedItems(){ //this function will update the little messages with what is currently selected
        currentWeaponItemLabel.setText("Current Weapon Item: " + currWeaponString); //set the current weapon item label
        currentArmourItemLabel.setText("Current Armour Item: " + currArmourString); //set the current armour item label
    }

    private static void updateItemsList() { //this function will update the list of things in your inventory
        String allItemsTEXT = "\n    Inventory Items:\n"; //initialize
        for (Items.Item item : GameVars.inventory) { //for all items in the inventory
            allItemsTEXT += "   - " + item + "\n"; //add the item to the text
        }
        allInventoryItemsTextArea.setText(allItemsTEXT); //set the text
    }

    private static void findWeaponsAndArmour() { //this function will go through Gamevars.inventory and will
        for (Item item : GameVars.inventory) { //for all items in the inventory
            if (item instanceof Weapon) { //if the item is a weapon
                weaponItems.add((Weapon)item); //add the item to the weapon items
            } else if (item instanceof Armour) { //if the item is armour
                armourItems.add((Armour)item); //add the item to the armour items
            }
        }
    }

    public static void showInventory(){ //show the inventory
        updateItemsList(); //update the items list
        findWeaponsAndArmour(); //find the weapons and armour
        inventoryFrame.setVisible(true); //set the frame visible
    }

    public static void hideInventory(){ //hide the inventory
        AnnaTools.Updater.updateAllSidePanels(); //update all side panels
        inventoryFrame.setVisible(false); //set the frame invisible
    }

    public static void addItem(Item item){ //add an item
        GameVars.inventory.add(item); //add the item
        AnnaTools.ItemInsertSort.insertSort(GameVars.inventory); //sort the inventory
        findWeaponsAndArmour(); //find the weapons and armour
        updateItemsList(); //update the items list
    }

    public static void removeItem(Item item){ //remove an item
        GameVars.inventory.remove(item); //remove the item
    }

}
