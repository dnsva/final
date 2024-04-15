package Game;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import java.awt.*;

public class Inventory extends JFrame {
    private ArrayList<String> healthItems = new ArrayList<>();
    private ArrayList<String> weaponItems = new ArrayList<>();
    private ArrayList<String> armourItems = new ArrayList<>();

    private JTextArea healthInventoryItemsTextArea;
    private JTextArea weaponInventoryItemsTextArea;
    private JTextArea armourInventoryItemsTextArea;

    String currentHealthItem = "DFSDFSDF";
    String currentWeaponItem = "nothign";
    String currentArmourItem = "fefefe";

    JLabel currentHealthItemLabel = new JLabel();
    JLabel currentWeaponItemLabel = new JLabel();
    JLabel currentArmourItemLabel = new JLabel();

    public Inventory() {
        //this.healthItems = healthItems;
       // this.weaponItems = weaponItems;
        //this.armourItems = armourItems;
        weaponItems.add("Sword");
        weaponItems.add("Axe");
        weaponItems.add("Bow");

        armourItems.add("Helmet");
        armourItems.add("Chestplate");
        armourItems.add("Leggings");

        setTitle("Inventory");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(1, 3));

        currentHealthItemLabel.setText("Current Health Item: " + currentHealthItem);
        currentWeaponItemLabel.setText("Current Weapon Item: " + currentWeaponItem);
        currentArmourItemLabel.setText("Current Armour Item: " + currentArmourItem);

        healthInventoryItemsTextArea = new JTextArea("Healtasdasßh ItemAAAs:\n");
        weaponInventoryItemsTextArea = new JTextArea("Weap))ASdaon Items:\n");
        armourInventoryItemsTextArea = new JTextArea("Armour Items:\n");

        JButton healthButton = new JButton("Select Health Item");
        healthButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectItem(healthItems, "Health");
            }
        });


        JButton weaponButton = new JButton("Select Weapon Item");
        weaponButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectItem(weaponItems, "Weapon");
            }
        });


        JButton armourButton = new JButton("Select Armour Item");
        armourButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectItem(armourItems, "Armour");
            }
        });

        panel.add(healthInventoryItemsTextArea);
        panel.add(weaponInventoryItemsTextArea);
        panel.add(armourInventoryItemsTextArea);
        add(panel, BorderLayout.CENTER);

        JPanel selectPanel = new JPanel(new GridLayout(1, 3));

        selectPanel.add(currentHealthItemLabel);
        selectPanel.add(currentWeaponItemLabel);
        selectPanel.add(currentArmourItemLabel);
        add(selectPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
        buttonPanel.add(healthButton);
        buttonPanel.add(weaponButton);
        buttonPanel.add(armourButton);
        add(buttonPanel, BorderLayout.SOUTH);

        updateItemsList();
        setVisible(true);
    }



    private void updateCurrentSelectedItems(){

        currentHealthItemLabel.setText("Current Health Item: " + currentHealthItem);
        currentWeaponItemLabel.setText("Current Weapon Item: " + currentWeaponItem);
        currentArmourItemLabel.setText("Current Armour Item: " + currentArmourItem);

    }

    private void selectItem(ArrayList<String> items, String itemType) {
        if (items.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Cannot select " + itemType + " item because there are none.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        String selectedItem = (String) JOptionPane.showInputDialog(
                this,
                "Select " + itemType + " Item:",
                "Select Item",
                JOptionPane.PLAIN_MESSAGE,
                null,
                items.toArray(),
                null);
                //(itemType.equals("Health") ? currentHealthItem : itemType.equals("Weapon") ? currentWeaponItem : currentArmourItem));

        // Update selected item in the respective inventory
        switch (itemType) {
            case "Health":
                //healthLabel.setText("Health Items:\n" + (selectedItem != null ? selectedItem : "No active item in this column"));
                if(selectedItem != null) {
                    currentHealthItem = selectedItem;
                    updateCurrentSelectedItems();
                }
                break;
            case "Weapon":
                //weaponLabel.setText("Weapon Items:\n" + (selectedItem != null ? selectedItem : "No active item in this column"));
                if(selectedItem != null){
                    currentWeaponItem = selectedItem;
                    updateCurrentSelectedItems();
                }

                break;
            case "Armour":
                //armourLabel.setText("Armour Items:\n" + (selectedItem != null ? selectedItem : "No active item in this column"));
                if(selectedItem != null){
                    currentArmourItem = selectedItem;
                    updateCurrentSelectedItems();
                }

                break;
        }
    }


    private void updateItemsList() {
        healthInventoryItemsTextArea.setText("Health Items:\n" + (healthItems.isEmpty() ? "No active item in this column" : String.join("\n", healthItems)));
        weaponInventoryItemsTextArea.setText("Weapon Items:\n" + (weaponItems.isEmpty() ? "No active item in this column" : String.join("\n", weaponItems)));
        armourInventoryItemsTextArea.setText("Armour Items:\n" + (armourItems.isEmpty() ? "No active item in this column" : String.join("\n", armourItems)));
    }
}


/*

import javax.swing.*;
import java.awt.*;

import GameSlots.SlotInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Inventory {

    public static JFrame InventoryFrame;

    public Inventory(){



    }

    public static void showInventory(){


    }

    public static void hideInventory(){

    }

}
*/