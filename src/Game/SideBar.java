package Game;


import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.*;


public class SideBar {


    /*
    public static JPanel sideBarPanel;// = new JPanel();

    static JLabel healthLabel, sanityLabel, hungerLabel, balanceLabel,
            poisonedLabel, paralyzedLabel, confusedLabel, weaponLabel, armourLabel;
    public SideBar(){

        sideBarPanel = new JPanel();
        sideBarPanel.setLayout(new GridLayout(11, 1)); // Vertical layout

        JTextArea hello = new JTextArea("Hi from Game.SideBar!!!!!");
        sideBarPanel.add(hello);

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
        JButton inventoryButton = new JButton("Inventory");
        inventoryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle inventory button click event
                // Code for inventory button functionality goes here
                // For example, open inventory window or perform some action
                System.out.println("Inventory button clicked");
            }
        });

        // Add components to panel
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
    }

    public static void updatePanel(){
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
    public static JPanel getPanel(){
        return sideBarPanel;
    }

    public static void main(String[] args) {
        //THIS WORKS v
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 500);
        frame.add(new SideBar().sideBarPanel);
        frame.setVisible(true);
    }

     */

    static JPanel hey;

    static JPanel sideBarPanel;

    public SideBar(){
        //hey = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel hi = new JLabel("<html><b><u>T</u>wo</b><br> <br> lines</html>");

        hi.setText("<html><img src='https://example.com/image.jpg'>hello</html>");
        //hey.add(hi);
       // JTextArea hello = new JTextArea("Hi from Game.SideBar!!!!!");
       // hey.add(hello);

       //hi.setText("Sdfsdfsdf");

        sideBarPanel = new JPanel();

        sideBarPanel.add( new JLabel("<html> <h1>Heading</h1> </html>"));
        sideBarPanel.add( new JLabel("<html> <h2>Sub-heading</h2> </html>"));
        sideBarPanel.add( new JLabel("<html> <p>Paragraph</p> </html>"));
        sideBarPanel.add( new JLabel("<html> <p>Another paragraph</p> </html>"));
        sideBarPanel.add( new JLabel("<html> <p>Another paragraph</p> </html>"));


      //  sideBarPanel.add(hi);

    }

    public static JPanel getPanel(){

       // JPanel hey = new JPanel();

      //  hey = new JPanel();

       // JTextArea hello = new JTextArea("Hi from Game.SideBar!!!!!");
       // hey.add(hello);

       // return hey;

        return sideBarPanel;

    }

}
