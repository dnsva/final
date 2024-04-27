package Shops;

import Items.Weapon;
import Game.GameVars;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*

public class WeaponShopV1 {

    Weapon[] weaponList = {

            new Items.Weapon("name", 10, "desc", 10, 25),

            new Items.Weapon("Dagger",
                    10,
                    "Finely crafted by a local master",
                    GameVars.difficulyLevel.equals("Easy") ? 15 : GameVars.difficulyLevel.equals("Medium") ? 10 : 5,
                    GameVars.difficulyLevel.equals("Easy") ? 20 : GameVars.difficulyLevel.equals("Medium") ? 30 : 40
            ),

            new Items.Weapon("Axe",
                    15,
                    "For smashing enemies",
                    GameVars.difficulyLevel.equals("Easy") ? 15 : GameVars.difficulyLevel.equals("Medium") ? 10 : 5,
                    GameVars.difficulyLevel.equals("Easy") ? 15 : GameVars.difficulyLevel.equals("Medium") ? 25 : 30),
            new Items.Weapon("Katana",
                    50,
                    "Imported from Japanese masters",
                    GameVars.difficulyLevel.equals("Easy") ? 30 : GameVars.difficulyLevel.equals("Medium") ? 20 : 15,
                    GameVars.difficulyLevel.equals("Easy") ? 20 : GameVars.difficulyLevel.equals("Medium") ? 30 : 40
                    ),
            new Items.Weapon("Lightning Bow & Arrow",
                    100,
                    "Shoots lightning arrows and electrocutes enemies",
                    GameVars.difficulyLevel.equals("Easy") ? 35 : GameVars.difficulyLevel.equals("Medium") ? 30 : 25,
                    GameVars.difficulyLevel.equals("Easy") ? 5 : GameVars.difficulyLevel.equals("Medium") ? 10 : 25
                    ),
            new Items.Weapon("Scythe",
                    1000,
                    "Harvests souls",
                    100,
                    90)
    };

    //character balance is retrieved from using Game.GameVars.balance !!!!!

    double balance = Game.GameVars.balance;
    private static JPanel weaponShopPanel;
    private static JFrame weaponShopFrame;

    JTextArea namesAndDescriptions;
    private JLabel balanceLabel;
    private JComboBox<String> weaponComboBox;
    JButton purchaseButton;
    private JTextArea weaponDescriptionTextArea;
    //Make a constructor that sets up the JFrame and panels.
    //You only need to make this once which is why is a constructor.

    public WeaponShopV1() {

        // Create the main frame
        weaponShopPanel = new JPanel(new BorderLayout());

        weaponShopFrame = new JFrame("Weapon Shop");
        //weaponShopFrame.setVisible(true);
        weaponShopFrame.setTitle("By Anna Denisova");
        weaponShopPanel.setSize(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT);
        weaponShopFrame.setSize(GameVars.WINDOWWIDTH + GameVars.SIDEBARWIDTH, GameVars.WINDOWHEIGHT+290);
        weaponShopFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        weaponShopFrame.setLocationRelativeTo(null);
        weaponShopFrame.getContentPane().setLayout(new BorderLayout()); //Make the layout border


        // Create a panel
        //JPanel panel = new JPanel();

        //text
        namesAndDescriptions = new JTextArea();
        namesAndDescriptions.setSize(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT + 130);

        String nameAndDescriptionsString = "";

        namesAndDescriptions.setEditable(false);
        //namesAndDescriptions.setOpaque(false); // Make it transparent like a label
        namesAndDescriptions.setLineWrap(true); // Enable line wrapping
        namesAndDescriptions.setFocusable(false); //nocursor
        namesAndDescriptions.setFont(new Font("Courier", Font.PLAIN, 14));
        //namesAndDescriptions.setColumns(WINDOWWIDTH);

        nameAndDescriptionsString += " ------------------------------------------------------------ " +
                "| AVAILABLE ITEMS                                            |";  //string length - "| space and space | (4) dont count. this is equal to 59 here.

        for(int i = 0; i  < weaponList.length; ++i){
            nameAndDescriptionsString += "| " + (weaponList[i].name).toUpperCase() + returnStringWithSpaces(weaponList[i].name, 59-(((Integer)weaponList[i].price)).toString().length()-1 ) + "$" + weaponList[i].price + " |" +
                    "| - " + weaponList[i].description + returnStringWithSpaces(weaponList[i].description, 59-2) + " |" +
                    "| - Damage: " + weaponList[i].damage + returnStringWithSpaces(((Integer)weaponList[i].damage).toString(), 59-10) + " |" +
                    "| - Miss Rate: " + weaponList[i].missPercentage + "%" + returnStringWithSpaces(((Integer)weaponList[i].missPercentage).toString(), 59-14) + " |";
        }

        nameAndDescriptionsString += " ------------------------------------------------------------ ";

        namesAndDescriptions.setText(nameAndDescriptionsString);
        namesAndDescriptions.setBackground(Color.pink);

        //panel.add(namesAndDescriptions);

        // Display balance
        balanceLabel = new JLabel("Balance: $" + balance);
        balanceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        //panel.add(balanceLabel);

        // Display available weapons in a combo box
        weaponComboBox = new JComboBox<>();
        for (Weapon weapon : weaponList) {
            weaponComboBox.addItem(weapon.name + " - $" + weapon.price);
        }
        //panel.add(weaponComboBox);

        // Create purchase button
        purchaseButton = new JButton("Purchase");
        purchaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                purchaseWeapon();
            }
        });
        //panel.add(purchaseButton);


        // Add panel to the frame
        //weaponShopFrame.add(panel);

        JPanel newPanel = new JPanel(new BorderLayout(20, 10));
        newPanel.add(balanceLabel, BorderLayout.CENTER);
        newPanel.add(weaponComboBox, BorderLayout.SOUTH);
        //rewrite the prev 3 lines so that the balanceLabel is ABOVE weaponCombobox

       // newPannel.setLayout(new BoxLayout(newPannel, BoxLayout.Y_AXIS));


        //the following code distributes nameAndDescriptions, balanceLabel and weaponComboBox and Purchase Weapon using getContentPane and BorderLayout.
        weaponShopPanel.add(namesAndDescriptions, BorderLayout.NORTH);
        weaponShopPanel.add(newPanel, BorderLayout.CENTER);
        //weaponShopFrame.getContentPane().add(weaponComboBox, BorderLayout.CENTER);
        weaponShopPanel.add(purchaseButton, BorderLayout.SOUTH);

        weaponShopFrame.getContentPane().add(weaponShopPanel, BorderLayout.WEST);

        weaponShopFrame.getContentPane().add(Game.SideBar.getPanel(), BorderLayout.EAST);
       // weaponShopFrame.getContentPane().add(newPannel, BorderLayout.WEST);


        //weaponShopFrame.getContentPane().add(panel, BorderLayout.CENTER);
       // weaponShopFrame.getContentPane().add(balanceLabel, BorderLayout.SOUTH);

        weaponShopFrame.setVisible(true);


    }

    private String returnStringWithSpaces(String string, int length) {
        int spaces = length - string.length();
        String newString = "";
        for (int i = 0; i < spaces-1; i++) { //minus 1 just ebcause
            newString += " ";
        }
        return newString;
    }
    private void purchaseWeapon() {
        // Get the selected weapon
        int selectedWeaponIndex = weaponComboBox.getSelectedIndex();
        if (selectedWeaponIndex == -1) {
            JOptionPane.showMessageDialog(null, "Please select a weapon to purchase.");
            return;
        }
        Weapon selectedWeapon = weaponList[selectedWeaponIndex];

        // Check if the player has enough balance to purchase the weapon
        if (balance >= selectedWeapon.price) {
            // Deduct the price from the balance
            balance -= selectedWeapon.price;

            // Add the purchased weapon to the inventory
            GameVars.inventory.add(selectedWeapon);

            // Update balance label
            updateBalanceLabel();
            JOptionPane.showMessageDialog(null, "Purchase successful!");
        } else {
            JOptionPane.showMessageDialog(null, "Insufficient funds!");
        }
    }

    private void updateBalanceLabel() {
        balanceLabel.setText("Balance: $" + balance);
    }

    public static void showWeaponShop() {
        weaponShopFrame.setVisible(true);
    }

    public static void hideWeaponShop() {
        weaponShopFrame.setVisible(false);
    }

    public static void main(String[] args) {
        // Example usage
       // double initialBalance = 500; // Initial balance of the player
        new WeaponShopV1();
        showWeaponShop();
    }
}
*/
