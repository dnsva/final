package Game;


import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.File;
import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

import java.lang.*;


public class SideBar{
    JPanel hey;

    JPanel sideBarPanel;
    JButton inventoryButton = new JButton("Inventory");

    JLabel healthLabel = new JLabel(), sanityLabel = new JLabel(), hungerLabel = new JLabel(),
            balanceLabel = new JLabel(), poisonedLabel = new JLabel(), paralyzedLabel = new JLabel(),
            confusedLabel = new JLabel(), weaponLabel = new JLabel(), armourLabel = new JLabel();



    public SideBar(){

        sideBarPanel = new JPanel(new GridLayout(11, 1)); // Vertical layout
        sideBarPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        healthLabel.setText(" Health:" + GameVars.health);
        healthLabel.setIcon(new ImageIcon("src/Game/Icons/Heart.png"));
       // healthLabel.setBorder(new EmptyBorder(0, 20, 0, 20));

        sanityLabel.setIcon(new ImageIcon("src/Game/Icons/Bag.png"));
        sanityLabel.setText("Sanity: " + GameVars.sanity);

        hungerLabel.setText("Hunger: " + GameVars.hunger);
        hungerLabel.setIcon(new ImageIcon("src/Game/Icons/Apple.png"));

        balanceLabel.setText("Balance: " + GameVars.balance);
        balanceLabel.setIcon(new ImageIcon("src/Game/Icons/Golden Coin.png"));

        weaponLabel.setText("Weapon: " + GameVars.currWeapon);
        weaponLabel.setIcon(new ImageIcon("src/Game/Icons/Rune Stone.png"));

        armourLabel.setText("Armour: " + GameVars.currArmour);
        armourLabel.setIcon(new ImageIcon("src/Game/Icons/Helm.png"));

        poisonedLabel.setText("Poisoned: " + GameVars.isPoisoned);
        paralyzedLabel.setText("Paralyzed: " + GameVars.isParalyzed);
        confusedLabel.setText("Confused: " + GameVars.isConfused);


        // Initialize inventory button

        inventoryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Inventory.showInventory();
            }
        });
        sideBarPanel.add(healthLabel);
        sideBarPanel.add(sanityLabel);
        sideBarPanel.add(hungerLabel);
        sideBarPanel.add(balanceLabel);
        sideBarPanel.add(weaponLabel);
        sideBarPanel.add(armourLabel);
        sideBarPanel.add(poisonedLabel);
        sideBarPanel.add(paralyzedLabel);
        sideBarPanel.add(confusedLabel);

        sideBarPanel.add(inventoryButton);

        sideBarPanel.setPreferredSize(new Dimension(GameVars.SIDEBARWIDTH, GameVars.WINDOWHEIGHT));
        sideBarPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

    }

    public void updatePanel(){
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

    public JPanel getPanel(){
        return sideBarPanel;
    }
}
