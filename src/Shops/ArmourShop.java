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
    public static SideBar armourShopSideBar = new SideBar(); // The sidebar for the armour shop

    //format: GameVars.difficultyLevel.equals("Easy") ? 30 : GameVars.difficultyLevel.equals("Medium") ? 20 : 15,
    public Armour[] armourList = { // An array of all available armour in the shop
            new Armour("Bronze", 20, GameVars.difficultyLevel.equals("Easy") ? 10 : GameVars.difficultyLevel.equals("Medium") ? 5 : 3),
            new Armour("Silver", 50, GameVars.difficultyLevel.equals("Easy") ? 20 : GameVars.difficultyLevel.equals("Medium") ? 10 : 5),
            new Armour("Diamond", 100, GameVars.difficultyLevel.equals("Easy") ? 30 : GameVars.difficultyLevel.equals("Medium") ? 15 : 8)
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

        String nameAndDescriptionsString = ""; // Create string to store all the names and descriptions

        namesAndDescriptions.setEditable(false);
        namesAndDescriptions.setFocusable(false); // No cursor
        namesAndDescriptions.setContentType("text/html");

        /* The following code writes HTML formatted descriptions
         * for the namesAndDescriptionsString this is code to display
         * all possible items to buy*/
        nameAndDescriptionsString += "<html><body style='font-family: PT Mono; font-size: 12px;'>" +
                "<div style='background-color: #9AD1D4;'>";
        nameAndDescriptionsString += "<br>&nbsp;&nbsp;------------------------------------------------&nbsp;<br> " +
                "&nbsp;|&nbsp;<b><u>AVAILABLE ITEMS</u></b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|<br>";  // string length - "| space and space | (4) don't count. this is equal to 59 here.
        // Empty row after with the borders:
        nameAndDescriptionsString += "&nbsp;|" + returnStringWithSpaces("", 49) + "|&nbsp;<br>";
        nameAndDescriptionsString += "&nbsp;|" + returnStringWithSpaces("", 49) + "|&nbsp;<br>";

        for(int i = 0; i < armourList.length; ++i){
            nameAndDescriptionsString += "&nbsp;|&nbsp;<b>" + (armourList[i].name).toUpperCase() + returnStringWithSpaces(armourList[i].name, 47-(((Integer) armourList[i].price)).toString().length()-1 ) + "$" + armourList[i].price + "&nbsp;</b>|&nbsp;<br>";
            nameAndDescriptionsString += "&nbsp;|&nbsp;-&nbsp;Damage Reduction:&nbsp;" + armourList[i].damageSubtractorPercentage + returnStringWithSpaces("ok", 47-20) + "&nbsp;|&nbsp;<br>";
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
        // End of HTML formatted descriptions ---------------------------------------------

        // Display available armours in a combo box
        armourComboBox = new JComboBox<>(); // Initialize
        for (Armour armour : armourList) { // For all armours in the armour list
            armourComboBox.addItem(armour.name + " - $" + armour.price); // Add the armour to the combo box
        }

        // Create purchase button
        JButton purchaseButton = new JButton("Purchase"); // Confirm armour selection
        purchaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                purchaseArmour();
            } // On click
        });

        JButton exitButton = new JButton("Exit Shop"); // Exit the frame
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hideArmourShop();
            } // Hide the frame
        });

        JPanel newPanel = new JPanel(new BorderLayout(20, 10)); // Add spacing between
        newPanel.add(armourComboBox, BorderLayout.SOUTH); // Add the combo box to the panel

        JPanel buttonPanel = new JPanel(new BorderLayout()); // Create a panel for the buttons
        buttonPanel.add(purchaseButton, BorderLayout.NORTH); // Add the purchase button
        buttonPanel.add(exitButton, BorderLayout.SOUTH); // Add the exit button

        // The following code distributes nameAndDescriptions, balanceLabel and armourComboBox and Purchase Armour using getContentPane and BorderLayout.
        armourShopPanel.add(namesAndDescriptions, BorderLayout.NORTH);
        armourShopPanel.add(newPanel, BorderLayout.CENTER);
        armourShopPanel.add(buttonPanel, BorderLayout.SOUTH);

        armourShopFrame.add(armourShopPanel, BorderLayout.WEST);
        armourShopFrame.add(armourShopSideBar.getPanel(), BorderLayout.EAST);
    }

    private String returnStringWithSpaces(String string, int length) { // THIS RETURNS HTML SPACES
        int spaces = length - string.length(); // Length of the string
        String newString = ""; // Initialize
        for (int i = 0; i < spaces-1; i++) { // Minus 1 just because
            newString += "&nbsp;"; // Add a space
        }
        return newString; // Return the string
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

    //TESTING
    /*
    public static void main(String[] args) {
        new ArmourShop();
        showArmourShop();
    }

     */
}
