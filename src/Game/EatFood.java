/*
    List of all foods from GameVars.inventory instanceof Food

    User selects which food and confirms

    Once confirmed, Food.eat() is called and all sidebars are updated

     */
package Game;

import Items.Food;
import Items.Item;

import javax.swing.*;
        import java.awt.*;
        import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EatFood {

    public static JFrame eatFoodFrame; //a small frame
    private static JComboBox<Item> foodComboBox; //the different food options

    public EatFood() {
        eatFoodFrame = new JFrame("Eat Food"); //title
        eatFoodFrame.setSize(400, 150); // Adjusted width to accommodate the icon
        eatFoodFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //close setting
        eatFoodFrame.setLocationRelativeTo(null); //center

        // Set icon
        ImageIcon icon = new ImageIcon("src/Game/Icons/86_roastedchicken_dish.png"); //yum
        JLabel iconLabel = new JLabel(icon); // JLabel to display the icon

        JPanel mainPanel = new JPanel(); //main panel
        mainPanel.setLayout(new GridLayout(3, 1)); //grid layout

        JPanel selectPanel = new JPanel(new BorderLayout()); // Panel to hold the combo box and icon
        foodComboBox = new JComboBox<>(); //initialize
        foodComboBox.setPreferredSize(new Dimension(200, 30)); //set size
        foodComboBox.setSize(200, 30); //set size
        foodComboBoxUpdate(); //update the combo box
        selectPanel.add(iconLabel, BorderLayout.WEST); // Add the icon next to the combo box
        selectPanel.add(foodComboBox, BorderLayout.CENTER); //add the combo box

        JButton confirmButton = new JButton("Confirm"); //confirm food selection
        JButton exitButton = new JButton("Exit"); //exit the frame
        confirmButton.addActionListener(new ActionListener() { //if confirm button is pressed
            @Override
            public void actionPerformed(ActionEvent e) { //on click
                Item selectedFood = (Item) foodComboBox.getSelectedItem(); //get the selected food
                if (selectedFood != null) { //if there is a food selected
                    ((Food) selectedFood).eat(); //eat the food
                    GameVars.inventory.remove(selectedFood); //remove the food from the inventory
                    foodComboBoxUpdate(); //update the combo box
                    AnnaTools.Updater.updateAllSidePanels(); //update the side panels
                    hideEatFood(); //hide the frame
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a food item.", "Error", JOptionPane.ERROR_MESSAGE); //if no food selected
                }
            }
        });

        exitButton.addActionListener(new ActionListener() { //if exit button is pressed
            @Override
            public void actionPerformed(ActionEvent e) {
                hideEatFood();
            } //hide the frame
        });

        mainPanel.add(selectPanel); //add the select panel
        mainPanel.add(confirmButton); //add the confirm button
        mainPanel.add(exitButton); //add the exit button

        eatFoodFrame.getContentPane().add(mainPanel); //add the main panel
        eatFoodFrame.pack(); //pack so that everything is its smallest possible size
    }

    public static void foodComboBoxUpdate() { //update the combo box
        foodComboBox.removeAllItems(); //remove all items
        for (Item item : GameVars.inventory) { //for all items in the inventory
            if (item instanceof Food) { //if the item is food
                foodComboBox.addItem(item); //add the item to the combo box
            }
        }
    }

    public static void showEatFood(){ //show the frame
        foodComboBoxUpdate(); //update the combo box
        eatFoodFrame.setVisible(true); //make the frame visible
    }

    public static void hideEatFood(){ //hide the frame
        eatFoodFrame.setVisible(false); //make the frame invisible
    }

    /* TESTING
    public static void main(String[] args) {
        new EatFood();
        showEatFood();
    }*/
}
