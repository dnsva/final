package Shops;

import Game.GameVars;
import Game.SideBar;
import Items.Food;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import static AnnaTools.Updater.updateAllSidePanels;

public class FoodMarket {

    public static SideBar foodMarketSideBar = new SideBar();

    public Food[] foodList = { // An array of all available food in the shop
            new Food("Carrots", 1, 5),
            new Food("Cucumbers", 1, 5),
            new Food("Tomatoes", 1, 5),
            new Food("Strawberries", 2, 10),
            new Food("Apples", 2, 10),
            new Food("Peaches", 2, 10),
            new Food("Chicken", 5, 20),
            new Food("Pig", 10, 30),
            new Food("Cow", 15, 40),
            new Food("Dragon", 99, 99)
    };

    public static JFrame foodMarketFrame; // The most important thing in this entire file
    private JComboBox<String> foodMarketComboBox; // A combo box that displays available food
    private JTextField searchField;

    public FoodMarket() {

        // Setup frame details
        foodMarketFrame = new JFrame("Food Market");
        foodMarketFrame.setTitle("By Anna Denisova");
        foodMarketFrame.setSize(GameVars.WINDOWWIDTH + GameVars.SIDEBARWIDTH, GameVars.WINDOWHEIGHT);
        foodMarketFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        foodMarketFrame.setLocationRelativeTo(null);
        foodMarketFrame.setLayout(new BorderLayout()); // Make the layout border

        // Create the main panel
        JPanel foodMarketPanel = new JPanel(new BorderLayout());
        foodMarketPanel.setSize(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT);

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
                "&nbsp;|&nbsp;<b><u>AVAILABLE ITEMS</u></b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|<br>"; // String length - "| space and space | (4) don't count. This is equal to 59 here.
        // Empty row after with the borders:
        //nameAndDescriptionsString += "&nbsp;|" + returnStringWithSpaces("", 49) + "|&nbsp;<br>";

        for (int i = 0; i < foodList.length; ++i) {
            nameAndDescriptionsString += "&nbsp;|&nbsp;<b>" + (foodList[i].name).toUpperCase() + returnStringWithSpaces(foodList[i].name, 47 - (((Integer) foodList[i].price)).toString().length() - 1) + "$" + foodList[i].price + "&nbsp;</b>|&nbsp;<br>";

            nameAndDescriptionsString += "&nbsp;|&nbsp;-&nbsp;Nutrition:&nbsp;" + (foodList[i].hungerRestore) + returnStringWithSpaces("Nutrition", 47 - (((Integer) foodList[i].hungerRestore)).toString().length() - 4) + "&nbsp;|&nbsp;<br>";
            // Empty row after with the borders:
           // nameAndDescriptionsString += "&nbsp;|" + returnStringWithSpaces("", 49) + "|&nbsp;<br>";

            //if (i == 2 || i == 5 || i == 8) {
                //nameAndDescriptionsString += "&nbsp;|" + "°。°。°。°。°。°。°。°。°。°。°。°。°。°。°。°。°。°。°" + "|&nbsp;<br>";
               // nameAndDescriptionsString += "&nbsp;|" + "------------------------------------------------" + "|&nbsp;<br>";
                // Empty row after with the borders:
                //nameAndDescriptionsString += "&nbsp;|" + returnStringWithSpaces("", 49) + "|&nbsp;<br>";
            //}
        }

        nameAndDescriptionsString += "&nbsp;&nbsp;------------------------------------------------ <br><br>";
        nameAndDescriptionsString += "</div></body></html>";
        namesAndDescriptions.setText(nameAndDescriptionsString);

        // Search field
        searchField = new JTextField();
        searchField.setText("SEARCH HERE");
        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                filterFoodList();
            }
        });

        // Display available food in a combo box
        foodMarketComboBox = new JComboBox<>();
        for (Food foodItem : foodList) {
            foodMarketComboBox.addItem(foodItem.name + " - $" + foodItem.price);
        }

        // Create purchase button
        JButton purchaseButton = new JButton("Purchase");
        purchaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                purchaseFood();
            }
        });

        JButton exitButton = new JButton("Exit Market");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hideFoodMarket();
            }
        });

        JPanel newPanel = new JPanel(new BorderLayout());
        newPanel.add(searchField, BorderLayout.NORTH);
        newPanel.add(foodMarketComboBox, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.add(purchaseButton, BorderLayout.NORTH);
        buttonPanel.add(exitButton, BorderLayout.SOUTH);

        // The following code distributes nameAndDescriptions, balanceLabel and foodMarketComboBox and Purchase food using getContentPane and BorderLayout.
        foodMarketPanel.add(namesAndDescriptions, BorderLayout.NORTH);
        foodMarketPanel.add(newPanel, BorderLayout.CENTER);
        foodMarketPanel.add(buttonPanel, BorderLayout.SOUTH);

        foodMarketFrame.add(foodMarketPanel, BorderLayout.WEST);
        foodMarketFrame.add(foodMarketSideBar.getPanel(), BorderLayout.EAST);
    }

    private String returnStringWithSpaces(String string, int length) {
        int spaces = length - string.length();
        StringBuilder newString = new StringBuilder();
        for (int i = 0; i < spaces - 1; i++) { // Minus 1 just because
            newString.append("&nbsp;");
        }
        return newString.toString();
    }

    private void purchaseFood() {
        // Get the selected food
        int selectedFoodIndex = foodMarketComboBox.getSelectedIndex(); // The foods are indexed from 0 to whatever
        if (selectedFoodIndex == -1) { // ERROR CHECK HERE FOR IF NOTHING SELECTED
            JOptionPane.showMessageDialog(null, "Please select an item to purchase."); // Error message
            return; // Get out of function
        }

        Food selectedFoodItem = foodList[selectedFoodIndex]; // Stores the food in a food object

        if (GameVars.balance >= selectedFoodItem.price) { // Check if the player has enough balance to purchase the food
            GameVars.balance -= selectedFoodItem.price; // Deduct the price from the balance

            GameVars.inventory.add(selectedFoodItem);
            // Update the sidebar as now the inventory has changed and the balance has changed
            updateAllSidePanels();

            JOptionPane.showMessageDialog(null, "Purchase successful!"); // :)
        } else { // If not enough money
            JOptionPane.showMessageDialog(null, "Insufficient funds!"); // No purchase allowed
        }
    }

    private void filterFoodList() {
        String searchText = searchField.getText().toLowerCase();
        List<String> filteredItems = new ArrayList<>();

        for (Food foodItem : foodList) {
            if (foodItem.name.toLowerCase().contains(searchText)) {
                filteredItems.add(foodItem.name + " - $" + foodItem.price);
            }
        }

        foodMarketComboBox.setModel(new DefaultComboBoxModel<>(filteredItems.toArray(new String[0])));
    }

    public static void showFoodMarket() { // THIS SHOWS THE FRAME
        foodMarketFrame.setVisible(true); // OK
    }

    public static void hideFoodMarket() { // THIS HIDES THE FRAME
        Game.HomeVillage.showHomeVillage();
        foodMarketFrame.setVisible(false); // OK
    }

    public static void main(String[] args) {
        new FoodMarket();
        showFoodMarket();
    }
}
