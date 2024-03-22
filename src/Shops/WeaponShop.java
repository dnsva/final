package Shops;

import Items.Weapon;
import Game.GameVars;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.*;


public class WeaponShop {

    Weapon[] weaponList = {

            new Items.Weapon("name", 100, "desc", 10, 25),
            new Items.Weapon("Axe", 150, "A heavy axe for smashing enemies", 15, 20),
            new Items.Weapon("Sword", 200, "A sharp sword for slicing enemies", 20, 15)
    };

    //character balance is retrieved from using Game.GameVars.balance !!!!!

    double balance = Game.GameVars.balance;
    private static JPanel weaponShopPanel;
    private static JFrame weaponShopFrame;

    JTextArea namesAndDescriptions;
    private JLabel balanceLabel;
    private JComboBox<String> weaponComboBox;
    JButton purchaseButton;
    private JTextArea weaponDescriptionTextArea;
    //Make a constructor that sets up the JFrame and panels.
    //You only need to make this once which is why is a constructor.

    public WeaponShop() {

        // Create the main frame
        weaponShopPanel = new JPanel(new BorderLayout());

        weaponShopFrame = new JFrame("Weapon Shop");
        weaponShopFrame.setVisible(true);
        weaponShopFrame.setTitle("By Anna Denisova");
        weaponShopPanel.setSize(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT+200);
        weaponShopFrame.setSize(GameVars.WINDOWWIDTH + GameVars.SIDEBARWIDTH, GameVars.WINDOWHEIGHT+200);
        weaponShopFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        weaponShopFrame.setLocationRelativeTo(null);
        weaponShopFrame.getContentPane().setLayout(new BorderLayout()); //Make the layout border


        // Create a panel
        //JPanel panel = new JPanel();

        //text
        namesAndDescriptions = new JTextArea();
        namesAndDescriptions.setSize(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT + 130);

        String nameAndDescriptionsString = "";

        namesAndDescriptions.setEditable(false);
        //namesAndDescriptions.setOpaque(false); // Make it transparent like a label
        namesAndDescriptions.setLineWrap(true); // Enable line wrapping
        namesAndDescriptions.setFocusable(false); //nocursor
        namesAndDescriptions.setFont(new Font("Courier", Font.PLAIN, 14));
        //namesAndDescriptions.setColumns(WINDOWWIDTH);

        nameAndDescriptionsString += " -------------------------------------------------------------" +
                "| WIZARD                                                     |" +
                "| - Start game with [curse/potion name]                      |" +
                "| - 25% off all monster curses                               |" +
                "| - Default attack power: 10                                 |" +
                "| MIME                                                       |" +
                "| - Useless and weak                                         |" +
                "| - Gets 1 free apple                                        |" +
                "| - Default attack power: 1                                  |" +
                "| WARRIOR                                                    |" +
                "| - Start game with [weapon name]                            |" +
                "| - 25% off all weapons                                      |" +
                "| - Default attack power: 15                                 |" +
                "| DOCTOR                                                     |" +
                "| - Start game with [healing potion]                         |" +
                "| - 25% off all healing potions                              |" +
                "| - Default attack power: 10                                 |" +
                "| FARMER                                                     |" +
                "| - Can't fight                                              |" +
                "| - Start game with all the food                             |" +
                "| - Default attack power: 0                                  |" +
                " -------------------------------------------------------------";


        namesAndDescriptions.setText(nameAndDescriptionsString);
        namesAndDescriptions.setBackground(Color.pink);

        //panel.add(namesAndDescriptions);

        // Display balance
        balanceLabel = new JLabel("Balance: $" + balance);
        //panel.add(balanceLabel);

        // Display available weapons in a combo box
        weaponComboBox = new JComboBox<>();
        for (Weapon weapon : weaponList) {
            weaponComboBox.addItem(weapon.name + " - $" + weapon.price);
        }
        //panel.add(weaponComboBox);

        // Create purchase button
        purchaseButton = new JButton("Purchase");
        purchaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                purchaseWeapon();
            }
        });
        //panel.add(purchaseButton);


        // Add panel to the frame
        //weaponShopFrame.add(panel);

            /*
        JTextArea namesAndDescriptions;
        private JLabel balanceLabel;
        private JComboBox<String> weaponComboBox;
        JButton purchaseButton;
        */

        JPanel newPannel = new JPanel();
        newPannel.add(balanceLabel);
        newPannel.add(weaponComboBox);
        //rewrite the prev 3 lines so that the balanceLabel is ABOVE weaponCombobox
        newPannel.setLayout(new BoxLayout(newPannel, BoxLayout.Y_AXIS));


        //the following code distributes nameAndDescriptions, balanceLabel and weaponComboBox and Purchase Weapon using getContentPane and BorderLayout.
        weaponShopPanel.add(namesAndDescriptions, BorderLayout.NORTH);
        weaponShopPanel.add(newPannel, BorderLayout.CENTER);
        //weaponShopFrame.getContentPane().add(weaponComboBox, BorderLayout.CENTER);
        weaponShopPanel.add(purchaseButton, BorderLayout.SOUTH);

        weaponShopFrame.getContentPane().add(weaponShopPanel, BorderLayout.WEST);
       // weaponShopFrame.getContentPane().add(newPannel, BorderLayout.WEST);


        //weaponShopFrame.getContentPane().add(panel, BorderLayout.CENTER);
       // weaponShopFrame.getContentPane().add(balanceLabel, BorderLayout.SOUTH);

        weaponShopFrame.setVisible(true);


    }

    private void purchaseWeapon() {
        // Get the selected weapon
        int selectedWeaponIndex = weaponComboBox.getSelectedIndex();
        if (selectedWeaponIndex == -1) {
            JOptionPane.showMessageDialog(null, "Please select a weapon to purchase.");
            return;
        }
        Weapon selectedWeapon = weaponList[selectedWeaponIndex];

        // Check if the player has enough balance to purchase the weapon
        if (balance >= selectedWeapon.price) {
            // Deduct the price from the balance
            balance -= selectedWeapon.price;

            // Add the purchased weapon to the inventory
            GameVars.inventory.add(selectedWeapon);

            // Update balance label
            updateBalanceLabel();
            JOptionPane.showMessageDialog(null, "Purchase successful!");
        } else {
            JOptionPane.showMessageDialog(null, "Insufficient funds!");
        }
    }

    private void updateBalanceLabel() {
        balanceLabel.setText("Balance: $" + balance);
    }

    public static void main(String[] args) {
        // Example usage
       // double initialBalance = 500; // Initial balance of the player
        new WeaponShop();
    }
}
