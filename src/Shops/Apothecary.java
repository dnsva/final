package Shops;

import Game.GameVars;
import Game.SideBar;
import Items.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static AnnaTools.Updater.updateAllSidePanels;

public class Apothecary {
    public static SideBar apothecarySideBar = new SideBar();

    public static Item[] medicineList = { //An array of all available medicines in the shop
            new HealingMedicine("Herb Bandage", 10, 10),
            new HealingMedicine("Regeneration Pill", 25, 25),
            new HealingMedicine("Limb Transplant", 50, 50),
            //To do maybe add NEVER LOSE HEALTH POTION
            new SanityMedicine("Green Tea", 10, 10),
            new SanityMedicine("Basket of Berries", 25, 25),
            new SanityMedicine("Ancient Mushroom", 30, -5)
            //TO do maybe add NEVER LOSE SANITY POTION
    };

    public static JFrame apothecaryFrame; //the most important thing in this entire file
    private JComboBox<String> apothecaryComboBox; //a combo box that displays available medicines

    public Apothecary() {

        //setup frame details
        apothecaryFrame = new JFrame("Apothecary Shop");
        apothecaryFrame.setTitle("By Anna Denisova");
        apothecaryFrame.setSize(GameVars.WINDOWWIDTH + GameVars.SIDEBARWIDTH, GameVars.WINDOWHEIGHT);
        apothecaryFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        apothecaryFrame.setLocationRelativeTo(null);
        apothecaryFrame.setLayout(new BorderLayout()); //Make the layout border

        // Create the main panel
        JPanel apothecaryPanel = new JPanel(new BorderLayout());
        apothecaryPanel.setSize(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT);

        //text
        JEditorPane namesAndDescriptions = new JEditorPane();
        namesAndDescriptions.setSize(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT);

        String nameAndDescriptionsString = "";

        namesAndDescriptions.setEditable(false);
        namesAndDescriptions.setFocusable(false); //nocursor
        namesAndDescriptions.setContentType("text/html");

        nameAndDescriptionsString += "<html><body style='font-family: PT Mono; font-size: 12px;'>" +
                "<div style='background-color: #9AD1D4;'>";
        nameAndDescriptionsString += "<br>&nbsp;&nbsp;------------------------------------------------&nbsp;<br> " +
                "&nbsp;|&nbsp;<b><u>AVAILABLE ITEMS</u></b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|<br>";  //string length - "| space and space | (4) dont count. this is equal to 59 here.
        //empty row after with the borders:
        nameAndDescriptionsString += "&nbsp;|" + returnStringWithSpaces("", 49) + "|&nbsp;<br>";

        for(int i = 0; i < medicineList.length; ++i){
            nameAndDescriptionsString += "&nbsp;|&nbsp;<b>" + (medicineList[i].name).toUpperCase() + returnStringWithSpaces(medicineList[i].name, 47-(((Integer) medicineList[i].price)).toString().length()-1 ) + "$" + medicineList[i].price + "&nbsp;</b>|&nbsp;<br>";

            if(medicineList[i] instanceof HealingMedicine){
                nameAndDescriptionsString += "&nbsp;|&nbsp;-&nbsp;Health Addition:&nbsp;" + ((HealingMedicine) medicineList[i]).healthAddition + returnStringWithSpaces("ok", 47-19) + "&nbsp;|&nbsp;<br>";
                //empty row after with the borders:
                nameAndDescriptionsString += "&nbsp;|" + returnStringWithSpaces("", 49) + "|&nbsp;<br>";
            } else {
                nameAndDescriptionsString += "&nbsp;|&nbsp;-&nbsp;Sanity Addition:&nbsp;" + ((SanityMedicine) medicineList[i]).sanityAddition + returnStringWithSpaces("ok", 47-19) + "&nbsp;|&nbsp;<br>";
                //empty row after with the borders:
                nameAndDescriptionsString += "&nbsp;|" + returnStringWithSpaces("", 49) + "|&nbsp;<br>";
            }

            if(i == 2){
                nameAndDescriptionsString += "&nbsp;|" + "°。°。°。°。°。°。°。°。°。°。°。°。°。°。°。°。°。°。°" + "|&nbsp;<br>";
                //empty row after with the borders:
                nameAndDescriptionsString += "&nbsp;|" + returnStringWithSpaces("", 49) + "|&nbsp;<br>";
            }
        }

        nameAndDescriptionsString += "&nbsp;&nbsp;------------------------------------------------ <br><br>";
        nameAndDescriptionsString += "</div></body></html>";
        namesAndDescriptions.setText(nameAndDescriptionsString);

        // Display available medicines in a combo box
        apothecaryComboBox = new JComboBox<>();
        for (Item medicine : medicineList) {
            apothecaryComboBox.addItem(medicine.name + " - $" + medicine.price);
        }

        // Create purchase button
        JButton purchaseButton = new JButton("Purchase");
        purchaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                purchaseMedicine();
            }
        });

        JButton exitButton = new JButton("Exit Shop");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hideApothecary();
            }
        });

        JPanel newPanel = new JPanel(new BorderLayout(20, 10));
        newPanel.add(apothecaryComboBox, BorderLayout.SOUTH);

        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.add(purchaseButton, BorderLayout.NORTH);
        buttonPanel.add(exitButton, BorderLayout.SOUTH);

        //the following code distributes nameAndDescriptions, balanceLabel and apothecaryComboBox and Purchase Medicine using getContentPane and BorderLayout.
        apothecaryPanel.add(namesAndDescriptions, BorderLayout.NORTH);
        apothecaryPanel.add(newPanel, BorderLayout.CENTER);
        apothecaryPanel.add(buttonPanel, BorderLayout.SOUTH);

        apothecaryFrame.add(apothecaryPanel, BorderLayout.WEST);
        apothecaryFrame.add(apothecarySideBar.getPanel(), BorderLayout.EAST);
    }

    private String returnStringWithSpaces(String string, int length) {
        int spaces = length - string.length();
        String newString = "";
        for (int i = 0; i < spaces-1; i++) { //minus 1 just because
            newString += "&nbsp;";
        }
        return newString;
    }

    private void purchaseMedicine() {
        // Get the selected medicine
        int selectedMedicineIndex = apothecaryComboBox.getSelectedIndex(); //the medicines are indexed from 0 to whatever
        if (selectedMedicineIndex == -1) { //ERROR CHECK HERE FOR IF NOTHING SELECTED
            JOptionPane.showMessageDialog(null, "Please select an item to purchase."); //err message
            return; //get out of fn
        }

        Item selectedMedicine = medicineList[selectedMedicineIndex]; //stores the medicine in an item object

        if (GameVars.balance >= selectedMedicine.price) { //Check if the player has enough balance to purchase the medicine
            GameVars.balance -= selectedMedicine.price; //Deduct the price from the balance

            if(selectedMedicine instanceof HealingMedicine){
                GameVars.inventory.add((HealingMedicine) selectedMedicine);
            } else if (selectedMedicine instanceof SanityMedicine){
                GameVars.inventory.add((SanityMedicine) selectedMedicine);
            }
            // Update the sidebar as now the inventory has changed and the balance has changed
            updateAllSidePanels();

            JOptionPane.showMessageDialog(null, "Purchase successful!"); //:)
        } else { //If not enough money
            JOptionPane.showMessageDialog(null, "Insufficient funds!"); //No purchase allowed
        }
    }

    public static void showApothecary() { //THIS SHOWS THE FRAME
        apothecaryFrame.setVisible(true); //ok
    }

    public static void hideApothecary() { //THIS HIDES THE FRAME
        Game.HomeVillage.showHomeVillage();
        apothecaryFrame.setVisible(false); //ok
    }

    public static void main(String[] args) {
        new Apothecary();
        showApothecary();
    }
}
