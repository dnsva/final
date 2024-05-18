/*
    Frame

    List of all medicine from GameVars.inventory(an array of Items) instanceof HealingMedicine or SanityMedicine

    User selects which medicine and confirms

    Once confirmed, medicine perks by medicine.use() are added to overall GameVars stats and all sidebars are updated

    There is also an exit button at the end to leave this frame and back to whatever window

     */
package Game;

import Items.HealingMedicine;
import Items.Item;
import Items.SanityMedicine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UseMedicine {

    public static JFrame useMedicineFrame;
    private static JComboBox<Item> medicineComboBox;

    public UseMedicine() {
        useMedicineFrame = new JFrame("Use Medicine");
        useMedicineFrame.setSize(400, 150); // Increased width to accommodate the image
        useMedicineFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        useMedicineFrame.setLocationRelativeTo(null);

        // Set icon
        ImageIcon icon = new ImageIcon("src/Game/Icons/Green Potion 3.png");
        JLabel iconLabel = new JLabel(icon); // JLabel to display the icon

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 1));

        //JPanel selectPanel = new JPanel(new GridLayout(1, 2)); // Panel to hold the combo box and icon

        JPanel selectPanel = new JPanel(new BorderLayout()); // Panel to hold the combo box and icon
        medicineComboBox = new JComboBox<>();
        medicineComboBox.setPreferredSize(new Dimension(200, 30));
        medicineComboBox.setSize(200, 30);
        medicineComboBoxUpdate();
        selectPanel.add(iconLabel, BorderLayout.WEST); // Add the icon next to the combo box
        //selectPanel.add(selectLabel);
        selectPanel.add(medicineComboBox, BorderLayout.CENTER);


        JButton confirmButton = new JButton("Confirm");
        JButton exitButton = new JButton("Exit");
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Item selectedMedicine = (Item) medicineComboBox.getSelectedItem();
                if (selectedMedicine != null) {
                    selectedMedicine.use();
                    GameVars.inventory.remove(selectedMedicine);
                    medicineComboBoxUpdate();
                    AnnaTools.Updater.updateAllSidePanels();
                    hideUseMedicine();
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a medicine.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hideUseMedicine();
            }
        });

        mainPanel.add(selectPanel);
        mainPanel.add(confirmButton);
        mainPanel.add(exitButton);

        useMedicineFrame.getContentPane().add(mainPanel);
        useMedicineFrame.pack();
    }

    public static void medicineComboBoxUpdate() {
        medicineComboBox.removeAllItems();
        for (Item item : GameVars.inventory) {
            if (item instanceof HealingMedicine || item instanceof SanityMedicine) {
                medicineComboBox.addItem(item);
            }
        }
    }

    public static void showUseMedicine(){
        medicineComboBoxUpdate();
        useMedicineFrame.setVisible(true);
    }

    public static void hideUseMedicine(){
        useMedicineFrame.setVisible(false);
    }
    public static void main(String[] args) {

        new UseMedicine();
        showUseMedicine();

    }
}