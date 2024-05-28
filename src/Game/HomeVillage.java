package Game;

//IMPORT ALL PACKAGES -----
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;   //-

import AnnaTools.Fonts;
import Dungeon.*;       //-
import java.awt.*;      //-
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import Items.*;         //-
import Shops.*;         //-

import static Game.GameOver.checkGameOver;
import static Game.GameVars.isGhost;


//-------------------------

public class HomeVillage {

    public static JFrame homeVillageFrame = new JFrame();
    public static SideBar homeVillageSideBar = new SideBar();
    public static JPanel homeVillagePanel;

    public HomeVillage(){

        runTimerThread();
        new AnnaTools.Fonts();

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

        new MapGUI(); //Create the map GUI for Map


        //--------------------------------------------------------------------------------

        homeVillageFrame = new JFrame("By Anna Denisova");
       // homeVillageFrame.setTitle("By Anna Denisova");
        homeVillageFrame.setSize(GameVars.WINDOWWIDTH + GameVars.SIDEBARWIDTH, GameVars.WINDOWHEIGHT);
        homeVillageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        homeVillageFrame.setLocationRelativeTo(null);
        homeVillageFrame.getContentPane().setLayout(new BorderLayout());

        homeVillagePanel = new JPanel();
        homeVillagePanel.setSize(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT);
        //homeVillagePanel.setLayout(new BoxLayout(homeVillagePanel, BoxLayout.Y_AXIS));
        homeVillagePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        homeVillagePanel.setBackground(Color.decode("#C2F9BB"));

        JLabel titleLabel = new JLabel("Village");
        titleLabel.setFont(Fonts.Pamela);
        titleLabel.setPreferredSize(new Dimension(GameVars.WINDOWWIDTH, 100));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        //titleLabel.setForeground(Color.white);

        homeVillagePanel.add(Box.createVerticalStrut(20)); //add space between
        homeVillagePanel.add(titleLabel);
        homeVillagePanel.add(Box.createVerticalStrut(20)); //add space between

        JPanel buttonPanel = new JPanel(new GridLayout(7, 1));
        buttonPanel.setSize(GameVars.WINDOWWIDTH-50, GameVars.WINDOWHEIGHT-150);
        buttonPanel.setPreferredSize(new Dimension(GameVars.WINDOWWIDTH-50, GameVars.WINDOWHEIGHT-150));
        buttonPanel.setBackground(Color.decode("#C2F9BB"));

        JButton apothecaryButton = new JButton("Apothecary");
        buttonPanel.add(apothecaryButton);
        apothecaryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!isGhost){
                    Shops.Apothecary.showApothecary();
                    hideHomeVillage();
                }else{
                    JOptionPane.showMessageDialog(null, "You are a ghost and cannot use the apothecary.");
                }

            }
        });
        JButton armourShopButton = new JButton("Armour Shop");
        buttonPanel.add(armourShopButton);
        armourShopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!isGhost) {
                    Shops.ArmourShop.showArmourShop();
                    hideHomeVillage();
                }else{
                    JOptionPane.showMessageDialog(null, "You are a ghost and cannot use the armour shop.");

                }
            }
        });
        JButton foodMarket = new JButton("Food Market");
        buttonPanel.add(foodMarket);
        foodMarket.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!isGhost){
                    Shops.FoodMarket.showFoodMarket();
                    hideHomeVillage();
                }else{
                    JOptionPane.showMessageDialog(null, "You are a ghost and cannot use the food market.");
                }
            }
        });
        JButton weaponShopButton = new JButton("Weapon Shop");
        buttonPanel.add(weaponShopButton);
        weaponShopButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!isGhost){
                    Shops.WeaponShop.showWeaponShop();
                    hideHomeVillage();
                }else{
                    JOptionPane.showMessageDialog(null, "You are a ghost and cannot use the weapon shop.");
                }
            }
        });
        JButton dungeonButton = new JButton("Dungeon");
        buttonPanel.add(dungeonButton);
        dungeonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!isGhost){
                    MapGUI.showMapGUI();
                    //hideHomeVillage();
                }else{
                    JOptionPane.showMessageDialog(null, "You are a ghost and cannot enter the dungeon.");
                }

            }
        });
        JButton exitButton = new JButton("Exit");
        buttonPanel.add(exitButton);
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
        buttonPanel.add(saveGameButton);
        saveGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TO BE IMPLEMENTED
            }
        });

        JButton[] buttons = {apothecaryButton, armourShopButton, foodMarket, weaponShopButton, dungeonButton, exitButton, saveGameButton};
        for(JButton button : buttons){
            button.setAlignmentX(Component.CENTER_ALIGNMENT); //Align the button to center
            //homeVillagePanel.add(button); //ADD ALL THE BUTTONS TO THE PANEL
            //homeVillagePanel.add(Box.createVerticalStrut(10)); //create space in between each button
        }


        homeVillagePanel.add(buttonPanel);

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

    public static void runTimerThread() {
        // Ensure GUI updates happen on the Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Initial actions when the GUI is first shown
                GameVars.day++; // times goes by literally
                if(!isGhost){
                    GameVars.hunger += 5; // you get hungrier
                    checkGameOver();
                }
                System.out.println("SOMETHING HAPPENENENDNNDN");
                AnnaTools.Updater.updateAllSidePanels(); // OK
            }
        });

        // Create and start the periodic task in a separate thread
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        Runnable task = new Runnable() {
            @Override
            public void run() {
                // Update GameVars and print message every second
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        GameVars.day++; // times goes by literally
                        if(!isGhost){
                            GameVars.hunger += 5; // you get hungrier
                            checkGameOver();
                        }
                        System.out.println("SOMETHING HAPPENENENDNNDN");
                        AnnaTools.Updater.updateAllSidePanels(); // OK
                    }
                });
            }
        };

        scheduler.scheduleAtFixedRate(task, 1, 1, TimeUnit.MINUTES);

        // Add a shutdown hook to gracefully stop the scheduler on JVM exit
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Shutting down scheduler...");
                scheduler.shutdown();
                try {
                    if (!scheduler.awaitTermination(5, TimeUnit.SECONDS)) {
                        System.out.println("Forcing shutdown...");
                        scheduler.shutdownNow();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    scheduler.shutdownNow();
                }
                System.out.println("Scheduler shut down.");
            }
        }));
    }


}
