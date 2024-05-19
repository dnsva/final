package Game;

//IMPORT ALL PACKAGES -----
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;   //-
import Dungeon.*;       //-
import java.awt.*;      //-
import Items.*;         //-
import Shops.*;         //-

//-------------------------

public class HomeVillage {

    public static JFrame homeVillageFrame = new JFrame();
    public static SideBar homeVillageSideBar;
    public static JPanel homeVillagePanel;

    public HomeVillage(){

        new Inventory(); //This is the display of the inventory. This has its JFrame
        new UseMedicine(); //This is the use medicine frame
        new EatFood(); //This is the eat food frame
        homeVillageSideBar = new SideBar(); //Create an instance for this Frame

        //LOAD IN USER BENEFITS BASED ON CHARACTER SELECTION
        loadUserBenefits(GameVars.characterType);

        new Shops.WeaponShop(); //Also construct the weapon shop
        new Shops.Apothecary(); //Also construct the apothecary shop
        new Shops.FoodMarket(); //Also construct the food market
        new Shops.ArmourShop(); //Also construct the armour shop
        //Add other shops here too
        //. . .

        new MapGUI(); //Create the map GUI for Map

        //LOAD IN USER BENEFITS BASED ON CHARACTER SELECTION
        //loadUserBenefits(GameVars.characterType);

        //--------------------------------------------------------------------------------

        homeVillageFrame = new JFrame("By Anna Denisova");
       // homeVillageFrame.setTitle("By Anna Denisova");
        homeVillageFrame.setSize(GameVars.WINDOWWIDTH + GameVars.SIDEBARWIDTH, GameVars.WINDOWHEIGHT);
        homeVillageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        homeVillageFrame.setLocationRelativeTo(null);
        homeVillageFrame.getContentPane().setLayout(new BorderLayout());

        homeVillagePanel = new JPanel();
        homeVillagePanel.setSize(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT);
        homeVillagePanel.setLayout(new BoxLayout(homeVillagePanel, BoxLayout.Y_AXIS));
        homeVillagePanel.setBackground(Color.decode("#C2F9BB"));

        JLabel titleLabel = new JLabel("Village");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        //titleLabel.setForeground(Color.white);

        homeVillagePanel.add(Box.createVerticalStrut(20)); //add space between
        homeVillagePanel.add(titleLabel);
        homeVillagePanel.add(Box.createVerticalStrut(20)); //add space between

        JButton apothecaryButton = new JButton("Apothecary");
        apothecaryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Shops.Apothecary.showApothecary();
                 hideHomeVillage();
            }
        });
        JButton armourShopButton = new JButton("Armour Shop");
        armourShopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Shops.ArmourShop.showArmourShop();
                hideHomeVillage();
            }
        });
        JButton foodMarket = new JButton("Food Market");
        foodMarket.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Shops.FoodMarket.showFoodMarket();
                hideHomeVillage();
            }
        });
        JButton weaponShopButton = new JButton("Weapon Shop");
        weaponShopButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {;
                Shops.WeaponShop.showWeaponShop();
                hideHomeVillage();
            }
        });
        JButton dungeonButton = new JButton("Dungeon");
        dungeonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MapGUI.showMapGUI();
                //hideHomeVillage();
            }
        });
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //GameSlots.SlotInfo.showInfoFrame();
                hideHomeVillage();
                //dispose of program/end program:
                System.exit(0);
            }
        });
        JButton saveGameButton = new JButton("Save Game");
        saveGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TO BE IMPLEMENTED
            }
        });

        JButton[] buttons = {apothecaryButton, armourShopButton, foodMarket, weaponShopButton, dungeonButton, exitButton, saveGameButton};
        for(JButton button : buttons){
            button.setAlignmentX(Component.CENTER_ALIGNMENT); //Align the button to center
            homeVillagePanel.add(button); //ADD ALL THE BUTTONS TO THE PANEL
            homeVillagePanel.add(Box.createVerticalStrut(10)); //create space in between each button
        }

        homeVillageFrame.add(homeVillagePanel, BorderLayout.CENTER);

        JSeparator separator = new JSeparator(SwingConstants.VERTICAL);
        homeVillageFrame.add(homeVillageSideBar.getPanel(), BorderLayout.EAST);

        //homeVillageFrame.pack();

    }

    public static void showHomeVillage(){
        homeVillageFrame.setVisible(true);
    }

    public static void hideHomeVillage(){
        homeVillageFrame.setVisible(false);
    }

    public static void loadUserBenefits(String name){

        if(name.equals("Wizard")){

            //Start game with sanity medicine

            GameVars.inventory.add(new SanityMedicine("Basket of Berries", 25, 25));

            //25% off all sanity potions
            for(int i = 0; i < Apothecary.medicineList.length; ++i){
                if(Apothecary.medicineList[i] instanceof SanityMedicine){
                    Apothecary.medicineList[i].price = (int)(Apothecary.medicineList[i].price * 0.75);
                }
            }

            //Default Attack Power: 10
            GameVars.playerAttackPower = 10;
            GameVars.fullAttackPower = 10;
            AnnaTools.Updater.updateAllSidePanels();

        }else if(name.equals("Mime")){
            /*
            "<p>&nbsp;- Useless and weak</p>" +
            "<p>&nbsp;- Gets 1 free apple</p>" +
            "<p>&nbsp;- Default attack power: 1</p>" +
             */

            GameVars.inventory.add(new Food("Apple", 0, 1));
            GameVars.playerAttackPower = 1;
            GameVars.fullAttackPower = 1;
            AnnaTools.Updater.updateAllSidePanels();

        }else if(name.equals("Warrior")){

            //Start game with axe
            GameVars.inventory.add(new Weapon("Axe",
                    15,
                    "For smashing enemies",
                    GameVars.difficultyLevel.equals("Easy") ? 15 : GameVars.difficultyLevel.equals("Medium") ? 10 : 5,
                    GameVars.difficultyLevel.equals("Easy") ? 15 : GameVars.difficultyLevel.equals("Medium") ? 25 : 30));

            //25% off all weapons
            for(int i = 0; i < WeaponShop.weaponList.length; ++i){
                WeaponShop.weaponList[i].price = (int)(WeaponShop.weaponList[i].price * 0.75);
            }

            //Default attack power: 15
            GameVars.playerAttackPower = 15;
            GameVars.fullAttackPower = 15;
            AnnaTools.Updater.updateAllSidePanels();

        }else if(name.equals("Doctor")){
            /*
            "<p>&nbsp;- Start game with basket of berries</p>" +
            "<p>&nbsp;- 25% off all healing potions</p>" +
            "<p>&nbsp;- Default attack power: 10</p>" +
             */

            GameVars.inventory.add(new HealingMedicine("Regeneration Pill", 25, 25));

            for(int i = 0; i < Apothecary.medicineList.length; ++i){
                if(Apothecary.medicineList[i] instanceof HealingMedicine){
                    Apothecary.medicineList[i].price = (int)(Apothecary.medicineList[i].price * 0.75);
                }
            }

            GameVars.playerAttackPower = 10;
            GameVars.fullAttackPower = 10;
            AnnaTools.Updater.updateAllSidePanels();

        }else if(name.equals("Farmer")) {
            //Start game with all the food
            GameVars.inventory.add(new Food("Carrots", 1, 5));
            GameVars.inventory.add(new Food("Cucumbers", 1, 5));
            GameVars.inventory.add(new Food("Tomatoes", 1, 5));
            GameVars.inventory.add(new Food("Strawberries", 2, 10));
            GameVars.inventory.add(new Food("Apples", 2, 10));
            GameVars.inventory.add(new Food("Peaches", 2, 10));
            GameVars.inventory.add(new Food("Chicken", 5, 20));
            GameVars.inventory.add(new Food("Pig", 10, 30));
            GameVars.inventory.add(new Food("Cow", 15, 40));
            GameVars.inventory.add(new Food("Dragon", 99, 99));

            //Default attack power
            GameVars.playerAttackPower = 5;
            GameVars.fullAttackPower = 5;
            AnnaTools.Updater.updateAllSidePanels();
        }
    }

}
