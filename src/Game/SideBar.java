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

    JLabel healthLabel = new JLabel(), sanityLabel = new JLabel(), hungerLabel = new JLabel(),
            balanceLabel = new JLabel(), poisonedLabel = new JLabel(), paralyzedLabel = new JLabel(),
            confusedLabel = new JLabel(), weaponLabel = new JLabel(), armourLabel = new JLabel();

    JButton inventoryButton = new JButton("Inventory");

    public SideBar(){
        //hey = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel hi = new JLabel("<html><b><u>T</u>wo</b><br> <br> lines</html>");

        hi.setText("<html><img src='https://example.com/image.jpg'>hello</html>");
        //hey.add(hi);
       // JTextArea hello = new JTextArea("Hi from Game.SideBar!!!!!");
       // hey.add(hello);

       //hi.setText("Sdfsdfsdf");

        sideBarPanel = new JPanel(new GridLayout(11, 1)); // Vertical layout

        healthLabel.setText("SDLFJSLDFK");

        //File f = new File("Game/Icons/Apple.png");

        healthLabel.setText(" Health:" + GameVars.health);
        healthLabel.setIcon(new ImageIcon("src/Game/Icons/Heart.png"));
        healthLabel.setBorder(new EmptyBorder(0, 20, 0, 20));

       // ImageIcon icon = new ImageIcon("src/Game/Icons/Apple.png");

        // Assuming your label is named "sanityLabel"

        sanityLabel.setIcon(new ImageIcon("src/Game/Icons/Bag.png"));
        sanityLabel.setText("Sanity: " + GameVars.sanity);

        hungerLabel.setText("Hunger: " + GameVars.hunger);
        hungerLabel.setIcon(new ImageIcon("src/Game/Icons/Apple.png"));

        balanceLabel.setText("Balance: " + GameVars.balance);
        poisonedLabel.setText("Poisoned: " + GameVars.isPoisoned);
        paralyzedLabel.setText("Paralyzed: " + GameVars.isParalyzed);
        confusedLabel.setText("Confused: " + GameVars.isConfused);
        weaponLabel.setText("Weapon: " + GameVars.currWeapon);
        armourLabel.setText("Armour: " + GameVars.currArmour);

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
        sideBarPanel.add(poisonedLabel);
        sideBarPanel.add(paralyzedLabel);
        sideBarPanel.add(confusedLabel);
        sideBarPanel.add(weaponLabel);
        sideBarPanel.add(armourLabel);

        sideBarPanel.add(inventoryButton);

       // sideBarPanel.add(healthLabel);

       // sideBarPanel.add( new JLabel("<html> <h1>Heading</h1> </html>"));
        //sideBarPanel.add( new JLabel("<html> <h2>Sub-heading</h2> </html>"));
        //sideBarPanel.add( new JLabel("<html> <p>Paragraph</p> </html>"));
        //sideBarPanel.add( new JLabel("<html> <p>Another paragraph</p> </html>"));
        //sideBarPanel.add( new JLabel("<html> <p>Another paragraph</p> </html>"));


      //  sideBarPanel.add(hi);

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
    //for the interface and so that one panel can be used in two places


}
