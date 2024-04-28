package Game;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import java.awt.*;

import Items.*;
import AnnaTools.*;

//this class does not store the inventoy list. the list is stored in gameVars
//this class is just a window that displays the inventory
//however, this class is also used to add stuff to the gamevars list
public class Inventory {



    private static Frame inventoryFrame = new JFrame();
    private ArrayList<Items.Item> weaponItems = new ArrayList<>();
    private ArrayList<Items.Item> armourItems = new ArrayList<>();

    private JTextArea healthInventoryItemsTextArea = new JTextArea();
    private JTextArea weaponInventoryItemsTextArea = new JTextArea();
    private JTextArea armourInventoryItemsTextArea = new JTextArea();
    private JTextArea allInventoryItemsTextArea = new JTextArea();

   // String currentHealthItem = "DFSDFSDF";
    String currWeaponString = null;
    String currArmourString = null;

    //JLabel currentHealthItemLabel = new JLabel();
    JLabel currentWeaponItemLabel = new JLabel();
    JLabel currentArmourItemLabel = new JLabel();

    public Inventory() {

        //for temp items:
        new GameVars();

        inventoryFrame.setTitle("Inventory");
        inventoryFrame.setLocationRelativeTo(null);
        inventoryFrame.setSize(GameVars.WINDOWWIDTH + GameVars.SIDEBARWIDTH, GameVars.WINDOWHEIGHT+310);
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

        currentItemPanel.add(currentWeaponItemLabel);
        currentItemPanel.add(currentArmourItemLabel);
        inventoryFrame.add(currentItemPanel, BorderLayout.NORTH);

        listPanel.add(allInventoryItemsTextArea);
        inventoryFrame.add(listPanel, BorderLayout.CENTER);

        selectionButtonPanel.add(weaponButton);
        selectionButtonPanel.add(armourButton);

        buttonPanel.add(selectionButtonPanel, BorderLayout.NORTH);
        buttonPanel.add(closeInventoryButton, BorderLayout.SOUTH);

        inventoryFrame.add(buttonPanel, BorderLayout.SOUTH);

        updateItemsList();
    }

    private void selectItem(ArrayList<Item> items, String itemType) {
        if (items.isEmpty()) {
            JOptionPane.showMessageDialog(inventoryFrame,
                    "Cannot select " + itemType + " item because there are none.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        //make items strings too
        ArrayList<String> itemsString = new ArrayList<String>();
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
                        }
                    }
                    updateCurrentSelectedItems();
                }
                break;
        }
    }

    private void updateCurrentSelectedItems(){
        currentWeaponItemLabel.setText("Current Weapon Item: " + currWeaponString);
        currentArmourItemLabel.setText("Current Armour Item: " + currArmourString);



        //-----------------------------------------------

    }

    private void updateItemsList() {
        String allItemsTEXT = "Inventory Items:\n";
        for (Items.Item item : GameVars.inventory) {
            allItemsTEXT += item + "\n";
        }
        allInventoryItemsTextArea.setText(allItemsTEXT);
    }

    private void findWeaponsAndArmour() {
        for (Item item : GameVars.inventory) {
            if (item instanceof Weapon) {
                weaponItems.add((Weapon)item);
            } else if (item instanceof Armour) {
                armourItems.add((Armour)item);
            }
        }
    }

    public static void showInventory(){
        inventoryFrame.setVisible(true);
    }

    public static void hideInventory(){
        inventoryFrame.setVisible(false);

        //------------------UPDATE ALL THE SIDE BARS------
        HomeVillage.homeVillageSideBar.updatePanel();
        Shops.WeaponShop.weaponShopSideBar.updatePanel();

    }

    //Methods used by other classes:

    public static void addItem(Item item){
        GameVars.inventory.add(item);
        AnnaTools.ItemInsertSort.insertSort(GameVars.inventory);
    }

    public static void removeItem(Item item){
        GameVars.inventory.remove(item);
    }
}
