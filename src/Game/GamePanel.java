package Game;

//gpt
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel {
    // Labels for displaying stats
    private JLabel healthLabel, sanityLabel, hungerLabel, balanceLabel,
            poisonedLabel, paralyzedLabel, confusedLabel, weaponLabel, armourLabel;

    // Button for inventory
    private JButton inventoryButton;

    public GamePanel() {
        setLayout(new GridLayout(10, 1)); // Vertical layout

        // Initialize labels for stats
        healthLabel = new JLabel("Health: " + GameVars.health);
        sanityLabel = new JLabel("Sanity: " + GameVars.sanity);
        hungerLabel = new JLabel("Hunger: " + GameVars.hunger);
        balanceLabel = new JLabel("Balance: " + GameVars.balance);
        poisonedLabel = new JLabel("Poisoned: " + GameVars.isPoisoned);
        paralyzedLabel = new JLabel("Paralyzed: " + GameVars.isParalyzed);
        confusedLabel = new JLabel("Confused: " + GameVars.isConfused);
        weaponLabel = new JLabel("Weapon: " + GameVars.currWeapon);
        armourLabel = new JLabel("Armour: " + GameVars.currArmour);

        // Initialize inventory button
        inventoryButton = new JButton("Inventory");
        inventoryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle inventory button click event
                // Code for inventory button functionality goes here
                // For example, open inventory window or perform some action
                System.out.println("Inventory button clicked");
            }
        });

        // Add components to panel
        add(healthLabel);
        add(sanityLabel);
        add(hungerLabel);
        add(balanceLabel);
        add(poisonedLabel);
        add(paralyzedLabel);
        add(confusedLabel);
        add(weaponLabel);
        add(armourLabel);
        add(inventoryButton);
    }

    // Method to update stat labels when values change
    public void updateStats() {
        healthLabel.setText("Health: " + GameVars.health);
        sanityLabel.setText("Sanity: " + GameVars.sanity);
        hungerLabel.setText("Hunger: " + GameVars.hunger);
        balanceLabel.setText("Balance: " + GameVars.balance);
        poisonedLabel.setText("Poisoned: " + GameVars.isPoisoned);
        paralyzedLabel.setText("Paralyzed: " + GameVars.isParalyzed);
        confusedLabel.setText("Confused: " + GameVars.isConfused);
        weaponLabel.setText("Weapon: " + GameVars.currWeapon);
        armourLabel.setText("Armour: " + GameVars.currArmour);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Game Panel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GamePanel panel = new GamePanel();
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }
}
