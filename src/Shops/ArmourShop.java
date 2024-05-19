package Shops;

import Game.GameVars;
import Game.SideBar;
import Items.Armour;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static AnnaTools.Updater.updateAllSidePanels;

public class ArmourShop {
    public static SideBar armourShopSideBar = new SideBar();

    //format: GameVars.difficultyLevel.equals("Easy") ? 30 : GameVars.difficultyLevel.equals("Medium") ? 20 : 15,
    public Armour[] armourList = { // An array of all available armour in the shop
            new Armour("Bronze", 20, GameVars.difficultyLevel.equals("Easy") ? 20 : GameVars.difficultyLevel.equals("Medium") ? 10 : 5),
            new Armour("Silver", 50, GameVars.difficultyLevel.equals("Easy") ? 30 : GameVars.difficultyLevel.equals("Medium") ? 20 : 15),
            new Armour("Diamond", 100, GameVars.difficultyLevel.equals("Easy") ? 40 : GameVars.difficultyLevel.equals("Medium") ? 30 : 25)
    };

    public static JFrame armourShopFrame; // The most important thing in this entire file
    private JComboBox<String> armourComboBox; // A combo box that displays available armours

    public ArmourShop() {

        // Setup frame details
        armourShopFrame = new JFrame("Armour Shop");
        armourShopFrame.setTitle("By Anna Denisova");
        armourShopFrame.setSize(GameVars.WINDOWWIDTH + GameVars.SIDEBARWIDTH, GameVars.WINDOWHEIGHT);
        armourShopFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        armourShopFrame.setLocationRelativeTo(null);
        armourShopFrame.setLayout(new BorderLayout()); // Make the layout border

        // Create the main panel
        JPanel armourShopPanel = new JPanel(new BorderLayout());
        armourShopPanel.setSize(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT);

        // Text
        JEditorPane namesAndDescriptions = new JEditorPane();
        namesAndDescriptions.setSize(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT);

        String nameAndDescriptionsString = "";

        namesAndDescriptions.setEditable(false);
        namesAndDescriptions.setFocusable(false); // No cursor
        namesAndDescriptions.setContentType("text/html");

        nameAndDescriptionsString += "<html><body style='font-family: PT Mono; font-size: 12px;'>" +
                "<div style='background-color: #9AD1D4;'>";
        nameAndDescriptionsString += "<br>&nbsp;&nbsp;------------------------------------------------&nbsp;<br> " +
                "&nbsp;|&nbsp;<b><u>AVAILABLE ITEMS</u></b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|<br>";  // string length - "| space and space | (4) don't count. this is equal to 59 here.
        // Empty row after with the borders:
        nameAndDescriptionsString += "&nbsp;|" + returnStringWithSpaces("", 49) + "|&nbsp;<br>";
        nameAndDescriptionsString += "&nbsp;|" + returnStringWithSpaces("", 49) + "|&nbsp;<br>";


        for(int i = 0; i < armourList.length; ++i){
            nameAndDescriptionsString += "&nbsp;|&nbsp;<b>" + (armourList[i].name).toUpperCase() + returnStringWithSpaces(armourList[i].name, 47-(((Integer) armourList[i].price)).toString().length()-1 ) + "$" + armourList[i].price + "&nbsp;</b>|&nbsp;<br>";
            nameAndDescriptionsString += "&nbsp;|&nbsp;-&nbsp;Damage Reduction:&nbsp;" + armourList[i].damageSubtractorPercentage + "%" + returnStringWithSpaces("ok", 47-21) + "&nbsp;|&nbsp;<br>";
            // Empty row after with the borders:
            nameAndDescriptionsString += "&nbsp;|" + returnStringWithSpaces("", 49) + "|&nbsp;<br>";
            nameAndDescriptionsString += "&nbsp;|" + returnStringWithSpaces("", 49) + "|&nbsp;<br>";
        }

        //extra empty limes to fill up the panel:
        nameAndDescriptionsString += "&nbsp;|" + returnStringWithSpaces("", 49) + "|&nbsp;<br>";
        nameAndDescriptionsString += "&nbsp;|" + returnStringWithSpaces("", 49) + "|&nbsp;<br>";
        nameAndDescriptionsString += "&nbsp;|" + returnStringWithSpaces("", 49) + "|&nbsp;<br>";
        nameAndDescriptionsString += "&nbsp;|" + returnStringWithSpaces("", 49) + "|&nbsp;<br>";
        nameAndDescriptionsString += "&nbsp;|" + returnStringWithSpaces("", 49) + "|&nbsp;<br>";
        nameAndDescriptionsString += "&nbsp;|" + returnStringWithSpaces("", 49) + "|&nbsp;<br>";
        nameAndDescriptionsString += "&nbsp;|" + returnStringWithSpaces("", 49) + "|&nbsp;<br>";
        nameAndDescriptionsString += "&nbsp;&nbsp;------------------------------------------------ <br><br>";
        nameAndDescriptionsString += "</div></body></html>";
        namesAndDescriptions.setText(nameAndDescriptionsString);

        // Display available armours in a combo box
        armourComboBox = new JComboBox<>();
        for (Armour armour : armourList) {
            armourComboBox.addItem(armour.name + " - $" + armour.price);
        }

        // Create purchase button
        JButton purchaseButton = new JButton("Purchase");
        purchaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                purchaseArmour();
            }
        });

        JButton exitButton = new JButton("Exit Shop");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hideArmourShop();
            }
        });

        JPanel newPanel = new JPanel(new BorderLayout(20, 10));
        newPanel.add(armourComboBox, BorderLayout.SOUTH);

        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.add(purchaseButton, BorderLayout.NORTH);
        buttonPanel.add(exitButton, BorderLayout.SOUTH);

        // The following code distributes nameAndDescriptions, balanceLabel and armourComboBox and Purchase Armour using getContentPane and BorderLayout.
        armourShopPanel.add(namesAndDescriptions, BorderLayout.NORTH);
        armourShopPanel.add(newPanel, BorderLayout.CENTER);
        armourShopPanel.add(buttonPanel, BorderLayout.SOUTH);

        armourShopFrame.add(armourShopPanel, BorderLayout.WEST);
        armourShopFrame.add(armourShopSideBar.getPanel(), BorderLayout.EAST);
    }

    private String returnStringWithSpaces(String string, int length) {
        int spaces = length - string.length();
        String newString = "";
        for (int i = 0; i < spaces-1; i++) { // Minus 1 just because
            newString += "&nbsp;";
        }
        return newString;
    }

    private void purchaseArmour() {
        // Get the selected armour
        int selectedArmourIndex = armourComboBox.getSelectedIndex(); // The armours are indexed from 0 to whatever
        if (selectedArmourIndex == -1) { // ERROR CHECK HERE FOR IF NOTHING SELECTED
            JOptionPane.showMessageDialog(null, "Please select an armour to purchase."); // Error message
            return; // Get out of function
        }

        Armour selectedArmour = armourList[selectedArmourIndex]; // Stores the armour in an armour object

        // Check if the selected armour is already in the inventory
        if (GameVars.inventory.contains(selectedArmour)) {
            JOptionPane.showMessageDialog(null, "You already own this armour."); // Error message for duplicate purchase
            return; // Get out of function
        }

        if (GameVars.balance >= selectedArmour.price) { // Check if the player has enough balance to purchase the armour
            GameVars.balance -= selectedArmour.price; // Deduct the price from the balance
            GameVars.inventory.add(selectedArmour); // Add the purchased armour to the inventory

            // Update the sidebar as now the inventory has changed and the balance has changed
            updateAllSidePanels();

            JOptionPane.showMessageDialog(null, "Purchase successful!"); // :)
        } else { // If not enough money
            JOptionPane.showMessageDialog(null, "Insufficient funds!"); // No purchase allowed
        }
    }


    public static void showArmourShop() { // THIS SHOWS THE FRAME
        armourShopFrame.setVisible(true); // OK
    }

    public static void hideArmourShop() { // THIS HIDES THE FRAME
        Game.HomeVillage.showHomeVillage();
        armourShopFrame.setVisible(false); // OK
    }

    public static void main(String[] args) {
        new ArmourShop();
        showArmourShop();
    }
}
