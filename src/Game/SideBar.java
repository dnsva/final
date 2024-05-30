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

import static Game.GameVars.isGhost;
//-------------------------


/*

Design

SideBarPannel - local, field variable

constructor initializes window with sanity, health, etc.

updatePanel() - a field method that updates the labels described above

 */
public class SideBar {

    JPanel sideBarPanel;
    JButton inventoryButton, useMedicineButton, eatFoodButton, helpButton;
    public JLabel healthLabel, sanityLabel, hungerLabel, dayLabel, balanceLabel, weaponLabel, armourLabel, attackLabel, defenseLabel;

    public void updatePanel(){

        if(!isGhost){
            healthLabel.setText("Health: " + GameVars.health);
            sanityLabel.setText("Sanity: " + GameVars.sanity);
            hungerLabel.setText("Hunger: " + GameVars.hunger);
            balanceLabel.setText("Balance: " + GameVars.balance);
            weaponLabel.setText("Weapon: " + GameVars.currWeapon.name);
            armourLabel.setText("Armour: " + GameVars.currArmour.name);
            attackLabel.setText("Attack: " + GameVars.fullAttackPower);
            defenseLabel.setText("Defense: " + GameVars.fullDefensePower);
            dayLabel.setText("Day: " + GameVars.day);

            //DEBUGGING: ========================================
            System.out.println("Updated SideBar Panel!");
        }else{
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

    public JPanel getPanel(){
        return sideBarPanel;
    }

    public SideBar(){

        sideBarPanel = new JPanel(new GridLayout(13, 1)); // Vertical layout
        sideBarPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        sideBarPanel.setSize(GameVars.SIDEBARWIDTH, GameVars.WINDOWHEIGHT);
        sideBarPanel.setPreferredSize(new Dimension(GameVars.SIDEBARWIDTH, GameVars.WINDOWHEIGHT));
        sideBarPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        //sideBarPanel.setBackground(Color.decode("#C2F9BB"));

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
        weaponLabel.setIcon(new ImageIcon("src/Game/Icons/Iron Sword.png"));
        armourLabel = new JLabel();
        armourLabel.setText("Armour: " + GameVars.currArmour.name);
        armourLabel.setIcon(new ImageIcon("src/Game/Icons/Helm.png"));
        attackLabel = new JLabel();
        attackLabel.setText("Attack: " + GameVars.fullAttackPower);
        attackLabel.setIcon(new ImageIcon("src/Game/Icons/Golden Sword.png")); //FIX
        defenseLabel = new JLabel();
        defenseLabel.setText("Defense: " + GameVars.fullDefensePower);
        defenseLabel.setIcon(new ImageIcon("src/Game/Icons/Iron Shield.png")); //FIX
        dayLabel = new JLabel();
        dayLabel.setText("Day: " + GameVars.day);
        dayLabel.setIcon(new ImageIcon("src/Game/Icons/Scroll.png"));


        sideBarPanel.add(dayLabel);
        sideBarPanel.add(healthLabel);
        sideBarPanel.add(sanityLabel);
        sideBarPanel.add(hungerLabel);
        sideBarPanel.add(balanceLabel);
        sideBarPanel.add(weaponLabel);
        sideBarPanel.add(armourLabel);
        sideBarPanel.add(attackLabel);
        sideBarPanel.add(defenseLabel);

        inventoryButton = new JButton("Inventory");
        inventoryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Inventory.showInventory();
            }
        });

        sideBarPanel.add(inventoryButton);

        useMedicineButton = new JButton("Use Medicine");
        useMedicineButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                UseMedicine.showUseMedicine();
            }
        });

        sideBarPanel.add(useMedicineButton);

        eatFoodButton = new JButton("Eat Food");
        eatFoodButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EatFood.showEatFood();
            }
        });

        sideBarPanel.add(eatFoodButton);

        helpButton = new JButton("Help");
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
        sideBarPanel.add(helpButton);

    }

    public void setColor(String hex){
        sideBarPanel.setBackground(Color.decode(hex));
        updatePanel();
    }

}
