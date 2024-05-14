package Shops;

import Game.GameVars;
import Game.SideBar;
import Items.Weapon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WeaponShop {

    public static SideBar weaponShopSideBar = new SideBar();

    Weapon[] weaponList = { //An array of all available weapons in the shop
            new Weapon("name", 10, "desc", 10, 25),
            new Weapon("Dagger",
                    10,
                    "Finely crafted by a local master",
                    GameVars.difficulyLevel.equals("Easy") ? 15 : GameVars.difficulyLevel.equals("Medium") ? 10 : 5,
                    GameVars.difficulyLevel.equals("Easy") ? 20 : GameVars.difficulyLevel.equals("Medium") ? 30 : 40),
            new Weapon("Axe",
                    15,
                    "For smashing enemies",
                    GameVars.difficulyLevel.equals("Easy") ? 15 : GameVars.difficulyLevel.equals("Medium") ? 10 : 5,
                    GameVars.difficulyLevel.equals("Easy") ? 15 : GameVars.difficulyLevel.equals("Medium") ? 25 : 30),
            new Weapon("Katana",
                    50,
                    "Imported from Japanese masters",
                    GameVars.difficulyLevel.equals("Easy") ? 30 : GameVars.difficulyLevel.equals("Medium") ? 20 : 15,
                    GameVars.difficulyLevel.equals("Easy") ? 20 : GameVars.difficulyLevel.equals("Medium") ? 30 : 40
                    ),
            new Weapon("Lightning Bow & Arrow",
                    100,
                    "Shoots lightning arrows and electrocutes enemies",
                    GameVars.difficulyLevel.equals("Easy") ? 35 : GameVars.difficulyLevel.equals("Medium") ? 30 : 25,
                    GameVars.difficulyLevel.equals("Easy") ? 5 : GameVars.difficulyLevel.equals("Medium") ? 10 : 25
                    ),
            new Weapon("Scythe",
                    1000,
                    "Harvests souls",
                    100,
                    90)
    };

    //double balance = GameVars.balance; //character balance is retrieved from using Game.GameVars.balance !!!!!
    private static JFrame weaponShopFrame; //the most important thing in this entire file
    /* -> */ private JComboBox<String> weaponComboBox; //a combo box that displays available weapons

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
        JTextArea namesAndDescriptions = new JTextArea();
        namesAndDescriptions.setSize(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT);

        String nameAndDescriptionsString = "";

        namesAndDescriptions.setEditable(false);
        namesAndDescriptions.setLineWrap(true); // Enable line wrapping
        namesAndDescriptions.setFocusable(false); //nocursor
        namesAndDescriptions.setFont(new Font("Courier", Font.PLAIN, 14));

        nameAndDescriptionsString += " ------------------------------------------------------------ " +
                "| AVAILABLE ITEMS                                            |";  //string length - "| space and space | (4) dont count. this is equal to 59 here.

        for(int i = 0; i  < weaponList.length; ++i){
            nameAndDescriptionsString += "| " + (weaponList[i].name).toUpperCase() + returnStringWithSpaces(weaponList[i].name, 59-(((Integer)weaponList[i].price)).toString().length()-1 ) + "$" + weaponList[i].price + " |" +
                    "| - " + weaponList[i].description + returnStringWithSpaces(weaponList[i].description, 59-2) + " |" +
                    "| - Damage: " + weaponList[i].damage + returnStringWithSpaces(((Integer)weaponList[i].damage).toString(), 59-10) + " |" +
                    "| - Miss Rate: " + weaponList[i].missPercentage + "%" + returnStringWithSpaces(((Integer)weaponList[i].missPercentage).toString(), 59-14) + " |";
        }

        nameAndDescriptionsString += " ------------------------------------------------------------ ";

        namesAndDescriptions.setText(nameAndDescriptionsString);
        namesAndDescriptions.setBackground(Color.pink);

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
            newString += " ";
        }
        return newString;
    }
    private void purchaseWeapon() {
        // Get the selected weapon
        int selectedWeaponIndex = weaponComboBox.getSelectedIndex(); //the weapons are indexed from 0 to whatever

        if (selectedWeaponIndex == -1) { //ERROR CHECK HERE FOR IF NOTHING SELECTED
            JOptionPane.showMessageDialog(null, "Please select a weapon to purchase."); //err message
            return; //get out of fn
        }

        Weapon selectedWeapon = weaponList[selectedWeaponIndex]; //stores the weapon in a weapon object

        if (GameVars.balance >= selectedWeapon.price) { //Check if the player has enough balance to purchase the weapon
            GameVars.balance -= selectedWeapon.price; //Deduct the price from the balance
            GameVars.inventory.add(selectedWeapon); //Add the purchased weapon to the inventory

          //  System.out.println("INVENTORY: ");
          //  for(int i = 0; i < GameVars.inventory.size(); ++i){
          //      System.out.println(GameVars.inventory.get(i).name);
          // }

            //YOU NEED TO UPDATE THE SIDEBAR AS NOW THE INVENTORY HAS CHANGED AND THE BALANCE HAS CHANGED
            weaponShopSideBar.updatePanel(); //Update the sidebar
            weaponShopFrame.getContentPane().add(weaponShopSideBar.getPanel(), BorderLayout.EAST);
            //-----------------------------------
            JOptionPane.showMessageDialog(null, "Purchase successful!"); //:)
        } else { //If not enough money
            JOptionPane.showMessageDialog(null, "Insufficient funds!"); //No purchase allowed
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
