package Game;

//IMPORT ALL PACKAGES -----
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;       //-
import java.util.*;     //-
import java.awt.*;      //-
import javax.swing.*;   //-
import javax.swing.border.EmptyBorder;

import Dungeon.*;       //-
import Items.*;         //-
import Monsters.*;      //-
import Shops.*;         //-
import AnnaTools.*;     //-
//-------------------------


/*

Design

SideBarPannel - local, field variable

constructor initializes window with sanity, health, etc.

updatePanel() - a field method that updates the labels described above

 */
public class SideBar {

    JPanel sideBarPanel;
    JButton inventoryButton;
    JLabel healthLabel, sanityLabel, hungerLabel, balanceLabel, weaponLabel, armourLabel;

    public void updatePanel(){
        healthLabel.setText("Health: " + GameVars.health);
        sanityLabel.setText("Sanity: " + GameVars.sanity);
        hungerLabel.setText("Hunger: " + GameVars.hunger);
        balanceLabel.setText("Balance: " + GameVars.balance);
        weaponLabel.setText("Weapon: " + GameVars.currWeapon.name);
        armourLabel.setText("Armour: " + GameVars.currArmour.name);

        //DEBUGGING: ========================================
        System.out.println("Updated SideBar Panel!");
    }

    public JPanel getPanel(){
        return sideBarPanel;
    }

    public SideBar(){
        inventoryButton = new JButton("Inventory");

        inventoryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //NOT IMPLEMENTED YET
                //SHOULD BE LIKE SHOW INVENTORY OR SOMETHING
                Inventory.showInventory();
            }
        });

        sideBarPanel = new JPanel(new GridLayout(11, 1)); // Vertical layout
        sideBarPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        sideBarPanel.setSize(GameVars.SIDEBARWIDTH, GameVars.WINDOWHEIGHT);
        sideBarPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        healthLabel = new JLabel();
        healthLabel.setText(" Health:" + GameVars.health);
        healthLabel.setIcon(new ImageIcon("src/Game/Icons/Heart.png"));
        sanityLabel = new JLabel();
        sanityLabel.setText("Sanity: " + GameVars.sanity);
        sanityLabel.setIcon(new ImageIcon("src/Game/Icons/Bag.png"));
        hungerLabel = new JLabel();
        hungerLabel.setText("Hunger: " + GameVars.hunger);
        hungerLabel.setIcon(new ImageIcon("src/Game/Icons/Apple.png"));
        balanceLabel = new JLabel();
        balanceLabel.setText("Balance: " + GameVars.balance);
        balanceLabel.setIcon(new ImageIcon("src/Game/Icons/Golden Coin.png"));
        weaponLabel = new JLabel();
        weaponLabel.setText("Weapon: " + GameVars.currWeapon.name);
        weaponLabel.setIcon(new ImageIcon("src/Game/Icons/Rune Stone.png"));
        armourLabel = new JLabel();
        armourLabel.setText("Armour: " + GameVars.currArmour.name);
        armourLabel.setIcon(new ImageIcon("src/Game/Icons/Helm.png"));

        sideBarPanel.add(healthLabel);
        sideBarPanel.add(sanityLabel);
        sideBarPanel.add(hungerLabel);
        sideBarPanel.add(balanceLabel);
        sideBarPanel.add(weaponLabel);
        sideBarPanel.add(armourLabel);
        sideBarPanel.add(inventoryButton);

    }

}
