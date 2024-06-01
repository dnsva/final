package Game;

/*
name: Anna
date: May 31, 2024
title: Side Bar
description: Deals with the sidebar panel and updating it
*/

//IMPORT ALL PACKAGES -----
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;       //-
import java.util.*;     //-
import java.awt.*;      //-
import javax.swing.*;   //-
import javax.swing.border.EmptyBorder;


import static Game.GameVars.isGhost;
//-------------------------


/*

Design

SideBarPannel - local, field variable

constructor initializes window with sanity, health, etc.

updatePanel() - a field method that updates the labels described above

 */
public class SideBar {

    JPanel sideBarPanel; //the panel
    JButton inventoryButton, useMedicineButton, eatFoodButton, helpButton; //all the buttons

    //ALL THE LABELS:
    public JLabel healthLabel, sanityLabel, hungerLabel, dayLabel, balanceLabel, weaponLabel, armourLabel, attackLabel, defenseLabel;

    public void updatePanel(){ //this sets the text of everything

        if(!isGhost){ //if not a ghost

            /* The following code sets the text of everything.
            Its pretty self explanatory code.
             */
            healthLabel.setText("Health: " + GameVars.health + "/100");
            sanityLabel.setText("Sanity: " + GameVars.sanity + "/100");
            hungerLabel.setText("Hunger: " + GameVars.hunger + "/100");
            balanceLabel.setText("Balance: " + GameVars.balance);
            weaponLabel.setText("Weapon: " + GameVars.currWeapon.name);
            armourLabel.setText("Armour: " + GameVars.currArmour.name);
            attackLabel.setText("Attack: " + GameVars.fullAttackPower);
            defenseLabel.setText("Defense: " + GameVars.fullDefensePower);
            dayLabel.setText("Day: " + GameVars.day);

            //DEBUGGING: ========================================
            //System.out.println("Updated SideBar Panel!");
        }else{
            /* The user is a ghost so everything is just default vaules*/
            healthLabel.setText("Health: 0");
            sanityLabel.setText("Sanity: 0");
            hungerLabel.setText("Hunger: 0");
            balanceLabel.setText("Balance: 0");
            weaponLabel.setText("Weapon: none");
            armourLabel.setText("Armour: none");
            attackLabel.setText("Attack: 0");
            defenseLabel.setText("Defense: 0");
            dayLabel.setText("Day: " + GameVars.day);
        }

    }

    public JPanel getPanel(){ //used in other classes
        return sideBarPanel; //return the field
    }

    public SideBar(){ //constructor

        sideBarPanel = new JPanel(new GridLayout(13, 1)); // Vertical layout
        sideBarPanel.setBorder(new EmptyBorder(20, 20, 20, 20)); // Add padding
        sideBarPanel.setSize(GameVars.SIDEBARWIDTH, GameVars.WINDOWHEIGHT); // Set size
        sideBarPanel.setPreferredSize(new Dimension(GameVars.SIDEBARWIDTH, GameVars.WINDOWHEIGHT)); // Set size
        sideBarPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        //sideBarPanel.setBackground(Color.decode("#C2F9BB"));

        //health label initialization and icon setting
        healthLabel = new JLabel();
        healthLabel.setText(" Health:" + GameVars.health + "/100");
        healthLabel.setIcon(new ImageIcon("src/Game/Icons/Heart.png"));
        //sanity label initialization and icon setting
        sanityLabel = new JLabel();
        sanityLabel.setText("Sanity: " + GameVars.sanity + "/100");
        sanityLabel.setIcon(new ImageIcon("src/Game/Icons/Bag.png"));
        //hunger label initialization and icon setting
        hungerLabel = new JLabel();
        hungerLabel.setText("Hunger: " + GameVars.hunger + "/100");
        hungerLabel.setIcon(new ImageIcon("src/Game/Icons/Apple.png"));
        //balance label initialization and icon setting
        balanceLabel = new JLabel();
        balanceLabel.setText("Balance: " + GameVars.balance);
        balanceLabel.setIcon(new ImageIcon("src/Game/Icons/Golden Coin.png"));
        //weapon label initialization and icon setting
        weaponLabel = new JLabel();
        weaponLabel.setText("Weapon: " + GameVars.currWeapon.name);
        weaponLabel.setIcon(new ImageIcon("src/Game/Icons/Iron Sword.png"));
        //armour label initialization and icon setting
        armourLabel = new JLabel();
        armourLabel.setText("Armour: " + GameVars.currArmour.name);
        armourLabel.setIcon(new ImageIcon("src/Game/Icons/Helm.png"));
        //attack label initialization and icon setting
        attackLabel = new JLabel();
        attackLabel.setText("Attack: " + GameVars.fullAttackPower);
        attackLabel.setIcon(new ImageIcon("src/Game/Icons/Golden Sword.png")); //FIX
        //defense label initialization and icon setting
        defenseLabel = new JLabel();
        defenseLabel.setText("Defense: " + GameVars.fullDefensePower);
        defenseLabel.setIcon(new ImageIcon("src/Game/Icons/Iron Shield.png")); //FIX
        //day label initialization and icon setting
        dayLabel = new JLabel();
        dayLabel.setText("Day: " + GameVars.day);
        dayLabel.setIcon(new ImageIcon("src/Game/Icons/Scroll.png"));

        /* The following code adds all the labels to the panel */
        sideBarPanel.add(dayLabel);
        sideBarPanel.add(healthLabel);
        sideBarPanel.add(sanityLabel);
        sideBarPanel.add(hungerLabel);
        sideBarPanel.add(balanceLabel);
        sideBarPanel.add(weaponLabel);
        sideBarPanel.add(armourLabel);
        sideBarPanel.add(attackLabel);
        sideBarPanel.add(defenseLabel);

        inventoryButton = new JButton("Inventory"); //initialize
        inventoryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Inventory.showInventory(); //inventoy button
            }
        });

        sideBarPanel.add(inventoryButton); //add to panel

        useMedicineButton = new JButton("Use Medicine"); //initialize
        useMedicineButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                UseMedicine.showUseMedicine(); //use medicine button
            }
        });

        sideBarPanel.add(useMedicineButton); //add to panel

        eatFoodButton = new JButton("Eat Food"); //initialize
        eatFoodButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EatFood.showEatFood(); //eat food button
            }
        });

        sideBarPanel.add(eatFoodButton); //add to panel

        helpButton = new JButton("Help"); //initialize
        helpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //create a pop up that says:
                /*
                Beat the final boss to win the game. Use the shops to buy items and equip armour and weapons from the inventory.
                If your health or sanity reaches 0, or your hunger reaches 100, you will die and become a ghost.
                 */
                JOptionPane.showMessageDialog(null, "Beat the final boss to win the game. Use \n" +
                                                                           "the shops to buy items and equip armour and \n" +
                                                                           "weapons from the inventory. If your health or \n" +
                                                                           "sanity reaches 0, or your hunger reaches 100, \n" +
                                                                           "you will die and become a ghost.");
            }
        });
        sideBarPanel.add(helpButton); //add to panel

    }

    public void setColor(String hex){ //set the color of the panel
        sideBarPanel.setBackground(Color.decode(hex)); //set the color
        updatePanel(); //update the panel
    }

}
