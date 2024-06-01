/*
    Frame

    List of all medicine from GameVars.inventory(an array of Items) instanceof HealingMedicine or SanityMedicine

    User selects which medicine and confirms

    Once confirmed, medicine perks by medicine.use() are added to overall GameVars stats and all sidebars are updated

    There is also an exit button at the end to leave this frame and back to whatever window

     */
package Game;

/*
name: Anna
date: May 31, 2024
title: Use Medicine
description:  This class creates the use medicine GUI.
*/

import Items.HealingMedicine;
import Items.Item;
import Items.SanityMedicine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UseMedicine {

    public static JFrame useMedicineFrame; //a small frame
    private static JComboBox<Item> medicineComboBox; //the different medicine options

    public UseMedicine() {
        useMedicineFrame = new JFrame("Use Medicine"); //title
        useMedicineFrame.setSize(400, 150); // Increased width to accommodate the image
        useMedicineFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //close setting
        useMedicineFrame.setLocationRelativeTo(null); //center

        // Set icon
        ImageIcon icon = new ImageIcon("src/Game/Icons/Green Potion 3.png");
        JLabel iconLabel = new JLabel(icon); // JLabel to display the icon

        JPanel mainPanel = new JPanel(); //main panel
        mainPanel.setLayout(new GridLayout(3, 1)); //grid layout

        //JPanel selectPanel = new JPanel(new GridLayout(1, 2)); // Panel to hold the combo box and icon

        JPanel selectPanel = new JPanel(new BorderLayout()); // Panel to hold the combo box and icon
        medicineComboBox = new JComboBox<>(); //initialize
        medicineComboBox.setPreferredSize(new Dimension(200, 30)); //set size
        medicineComboBox.setSize(200, 30); //set size
        medicineComboBoxUpdate(); //update the combo box
        selectPanel.add(iconLabel, BorderLayout.WEST); // Add the icon next to the combo box
        //selectPanel.add(selectLabel);
        selectPanel.add(medicineComboBox, BorderLayout.CENTER);


        JButton confirmButton = new JButton("Confirm"); //confirm medicine selection
        JButton exitButton = new JButton("Exit"); //exit the frame
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { //on click
                Item selectedMedicine = (Item) medicineComboBox.getSelectedItem(); //get the selected medicine
                if (selectedMedicine != null) { //if there is a medicine selected
                    selectedMedicine.use(); //use the medicine
                    GameVars.inventory.remove(selectedMedicine); //remove the medicine from the inventory
                    medicineComboBoxUpdate(); //update the combo box
                    AnnaTools.Updater.updateAllSidePanels(); //update the side panels
                    hideUseMedicine(); //hide the frame
                } else {
                    //Error message:
                    JOptionPane.showMessageDialog(null, "Please select a medicine.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hideUseMedicine(); //when exited
            }
        });

        mainPanel.add(selectPanel); //add the select panel
        mainPanel.add(confirmButton); //add the confirm button
        mainPanel.add(exitButton); //add the exit button

        useMedicineFrame.getContentPane().add(mainPanel); //add the main panel
        useMedicineFrame.pack();
    }

    public static void medicineComboBoxUpdate() { //update the combo box
        medicineComboBox.removeAllItems(); //remove all items
        for (Item item : GameVars.inventory) { //for all items in the inventory
            if (item instanceof HealingMedicine || item instanceof SanityMedicine) { //if the item is medicine
                medicineComboBox.addItem(item); //add the item to the combo box
            }
        }
    }

    public static void showUseMedicine(){ //show the frame
        medicineComboBoxUpdate(); //update the combo box
        useMedicineFrame.setVisible(true); //make the frame visible
    }

    public static void hideUseMedicine(){ //hide the frame
        useMedicineFrame.setVisible(false); //make the frame invisible
    }
    /* TESTING:
    public static void main(String[] args) {

        new UseMedicine();
        showUseMedicine();

    }*/
}