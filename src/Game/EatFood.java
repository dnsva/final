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

    public static JFrame eatFoodFrame;
    private static JComboBox<Item> foodComboBox;

    public EatFood() {
        eatFoodFrame = new JFrame("Eat Food");
        eatFoodFrame.setSize(400, 150); // Adjusted width to accommodate the icon
        eatFoodFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        eatFoodFrame.setLocationRelativeTo(null);

        // Set icon
        ImageIcon icon = new ImageIcon("src/Game/Icons/86_roastedchicken_dish.png");
        JLabel iconLabel = new JLabel(icon); // JLabel to display the icon

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 1));

        JPanel selectPanel = new JPanel(new BorderLayout()); // Panel to hold the combo box and icon
        foodComboBox = new JComboBox<>();
        foodComboBox.setPreferredSize(new Dimension(200, 30));
        foodComboBox.setSize(200, 30);
        foodComboBoxUpdate();
        selectPanel.add(iconLabel, BorderLayout.WEST); // Add the icon next to the combo box
        selectPanel.add(foodComboBox, BorderLayout.CENTER);

        JButton confirmButton = new JButton("Confirm");
        JButton exitButton = new JButton("Exit");
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Item selectedFood = (Item) foodComboBox.getSelectedItem();
                if (selectedFood != null) {
                    ((Food) selectedFood).eat();
                    GameVars.inventory.remove(selectedFood);
                    foodComboBoxUpdate();
                    AnnaTools.Updater.updateAllSidePanels();
                    hideEatFood();
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a food item.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hideEatFood();
            }
        });

        mainPanel.add(selectPanel);
        mainPanel.add(confirmButton);
        mainPanel.add(exitButton);

        eatFoodFrame.getContentPane().add(mainPanel);
        eatFoodFrame.pack();
    }

    public static void foodComboBoxUpdate() {
        foodComboBox.removeAllItems();
        for (Item item : GameVars.inventory) {
            if (item instanceof Food) {
                foodComboBox.addItem(item);
            }
        }
    }

    public static void showEatFood(){
        foodComboBoxUpdate();
        eatFoodFrame.setVisible(true);
    }

    public static void hideEatFood(){
        eatFoodFrame.setVisible(false);
    }

    public static void main(String[] args) {
        new EatFood();
        showEatFood();
    }
}
