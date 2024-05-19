package Shops;

import Game.GameVars;
import Game.SideBar;
import Items.Weapon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static AnnaTools.Updater.updateAllSidePanels;

public class WeaponShop {

    public static SideBar weaponShopSideBar = new SideBar();

    public static Weapon[] weaponList = { //An array of all available weapons in the shop
            new Weapon("Dagger",
                    10,
                    "Finely crafted by a local master",
                    GameVars.difficultyLevel.equals("Easy") ? 15 : GameVars.difficultyLevel.equals("Medium") ? 10 : 5,
                    GameVars.difficultyLevel.equals("Easy") ? 20 : GameVars.difficultyLevel.equals("Medium") ? 30 : 40),
            new Weapon("Axe",
                    15,
                    "For smashing enemies",
                    GameVars.difficultyLevel.equals("Easy") ? 15 : GameVars.difficultyLevel.equals("Medium") ? 10 : 5,
                    GameVars.difficultyLevel.equals("Easy") ? 15 : GameVars.difficultyLevel.equals("Medium") ? 25 : 30),
            new Weapon("Katana",
                    50,
                    "Imported from Japan",
                    GameVars.difficultyLevel.equals("Easy") ? 30 : GameVars.difficultyLevel.equals("Medium") ? 20 : 15,
                    GameVars.difficultyLevel.equals("Easy") ? 20 : GameVars.difficultyLevel.equals("Medium") ? 30 : 40
                    ),
            new Weapon("Lightning Bow & Arrow",
                    100,
                    "Shoots lightning arrows and electrocutes enemies",
                    GameVars.difficultyLevel.equals("Easy") ? 35 : GameVars.difficultyLevel.equals("Medium") ? 30 : 25,
                    GameVars.difficultyLevel.equals("Easy") ? 5 : GameVars.difficultyLevel.equals("Medium") ? 10 : 25
                    ),
            new Weapon("Scythe",
                    1000,
                    "Harvests souls",
                    100,
                    90)
    };
    public static JFrame weaponShopFrame; //the most important thing in this entire file
    private JComboBox<String> weaponComboBox; //a combo box that displays available weapons

    public WeaponShop() {

        //setup frame details
        weaponShopFrame = new JFrame("Weapon Shop");
        weaponShopFrame.setTitle("By Anna Denisova");
        weaponShopFrame.setSize(GameVars.WINDOWWIDTH + GameVars.SIDEBARWIDTH, GameVars.WINDOWHEIGHT);
        weaponShopFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        weaponShopFrame.setLocationRelativeTo(null);
        weaponShopFrame.setLayout(new BorderLayout()); //Make the layout border

        // Create the main panel
        JPanel weaponShopPanel = new JPanel(new BorderLayout());
        weaponShopPanel.setSize(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT);

        //text
        JEditorPane namesAndDescriptions = new JEditorPane();
        namesAndDescriptions.setSize(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT);

        String nameAndDescriptionsString = "";

        namesAndDescriptions.setEditable(false);
        namesAndDescriptions.setFocusable(false); //nocursor
        namesAndDescriptions.setContentType("text/html");

        nameAndDescriptionsString += "<html><body style='font-family: PT Mono; font-size: 10.5px;'>" +
                "<div style='background-color: #9AD1D4;'>";
        nameAndDescriptionsString += "<br>&nbsp;&nbsp;------------------------------------------------------------&nbsp;<br> " +
                "&nbsp;|&nbsp;<b><u>AVAILABLE ITEMS</u></b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|<br>";  //string length - "| space and space | (4) dont count. this is equal to 59 here.

        for(int i = 0; i  < weaponList.length; ++i){
            nameAndDescriptionsString += "&nbsp;|&nbsp;<b>" + (weaponList[i].name).toUpperCase() + returnStringWithSpaces(weaponList[i].name, 59-(((Integer)weaponList[i].price)).toString().length()-1 ) + "$" + weaponList[i].price + "&nbsp;</b>|&nbsp;<br>" +
                    "&nbsp;|&nbsp;-&nbsp;" + weaponList[i].description + returnStringWithSpaces(weaponList[i].description, 59-2) + "&nbsp;|&nbsp;<br>" +
                    "&nbsp;|&nbsp;-&nbsp;Damage:&nbsp;" + weaponList[i].damage + returnStringWithSpaces(((Integer)weaponList[i].damage).toString(), 59-10) + "&nbsp;|&nbsp;<br>" +
                    "&nbsp;|&nbsp;-&nbsp;Miss Rate:&nbsp;" + weaponList[i].missPercentage + "%" + returnStringWithSpaces(((Integer)weaponList[i].missPercentage).toString(), 59-14) + "&nbsp;|&nbsp;<br>";
            if(i != weaponList.length - 1)
                    // Empty row after with the borders:
                    nameAndDescriptionsString += "&nbsp;|" + returnStringWithSpaces("", 61) + "|&nbsp;<br>";
        }

        nameAndDescriptionsString += "&nbsp;&nbsp;------------------------------------------------------------ <br><br>";
        nameAndDescriptionsString += "</div></body></html>";
        namesAndDescriptions.setText(nameAndDescriptionsString);

        // Display available weapons in a combo box
        weaponComboBox = new JComboBox<>();
        for (Weapon weapon : weaponList) {
            weaponComboBox.addItem(weapon.name + " - $" + weapon.price);
        }

        // Create purchase button
        JButton purchaseButton = new JButton("Purchase");
        purchaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                purchaseWeapon();
            }
        });

        JButton exitButton = new JButton("Exit Shop");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hideWeaponShop();
            }
        });

        JPanel newPanel = new JPanel(new BorderLayout(20, 10));
        newPanel.add(weaponComboBox, BorderLayout.SOUTH);

        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.add(purchaseButton, BorderLayout.NORTH);
        buttonPanel.add(exitButton, BorderLayout.SOUTH);

        //the following code distributes nameAndDescriptions, balanceLabel and weaponComboBox and Purchase Weapon using getContentPane and BorderLayout.
        weaponShopPanel.add(namesAndDescriptions, BorderLayout.NORTH);
        weaponShopPanel.add(newPanel, BorderLayout.CENTER);
        weaponShopPanel.add(buttonPanel, BorderLayout.SOUTH);

        weaponShopFrame.add(weaponShopPanel, BorderLayout.WEST);
        weaponShopFrame.add(weaponShopSideBar.getPanel(), BorderLayout.EAST);
    }

    private String returnStringWithSpaces(String string, int length) {
        int spaces = length - string.length();
        String newString = "";
        for (int i = 0; i < spaces-1; i++) { //minus 1 just ebcause
            newString += "&nbsp;";
        }
        return newString;
    }
    private void purchaseWeapon() {
        // Get the selected weapon
        int selectedWeaponIndex = weaponComboBox.getSelectedIndex(); // The weapons are indexed from 0 to whatever
        if (selectedWeaponIndex == -1) { // ERROR CHECK HERE FOR IF NOTHING SELECTED
            JOptionPane.showMessageDialog(null, "Please select a weapon to purchase."); // Error message
            return; // Get out of function
        }

        Weapon selectedWeapon = weaponList[selectedWeaponIndex]; // Stores the weapon in a weapon object

        // Check if the selected weapon is already in the inventory
        if (GameVars.inventory.contains(selectedWeapon)) {
            JOptionPane.showMessageDialog(null, "You already own this weapon."); // Error message for duplicate purchase
            return; // Get out of function
        }

        if (GameVars.balance >= selectedWeapon.price) { // Check if the player has enough balance to purchase the weapon
            GameVars.balance -= selectedWeapon.price; // Deduct the price from the balance
            GameVars.inventory.add(selectedWeapon); // Add the purchased weapon to the inventory

            // Update the sidebar as now the inventory has changed and the balance has changed
            updateAllSidePanels();

            JOptionPane.showMessageDialog(null, "Purchase successful!"); // :)
        } else { // If not enough money
            JOptionPane.showMessageDialog(null, "Insufficient funds!"); // No purchase allowed
        }
    }


    public static void showWeaponShop() { //THIS SHOWS THE FRAME
        weaponShopFrame.setVisible(true); //ok
    }

    public static void hideWeaponShop() { //THIS HIDES THE FRAME
        Game.HomeVillage.showHomeVillage();
        weaponShopFrame.setVisible(false); //ok
    }

    public static void main(String[] args) {
       // new WeaponShop();
       // showWeaponShop();
    }
}
