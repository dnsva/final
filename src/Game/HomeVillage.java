package Game;

//IMPORT ALL PACKAGES -----
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;   //-

import AnnaTools.Fonts;
import Dungeon.*;       //-
import java.awt.*;      //-
import java.io.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import GameSlots.SlotInfo;
import Items.*;         //-
import Shops.*;         //-

import static Game.GameOver.checkGameOver;
import static Game.GameVars.isGhost;
import static Game.GameVars.slotNameLocal;
import static GameSlots.SlotInfo.*;


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


        /* LOAD IN ALL DATA */
        //In the GameVars.slotName + txt file, if there is a fifth line that means that there
        //has been already saved data. If not, nothing is changed and we proceed as normal.
        //If there is data, we load in the data and update the GameVars variables accordingly.
        File file = new File(SAVE_FILE_PATH);
        if (!file.exists()) {
            System.out.println("File does not exist.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            int lineCount = 0;
            while ((line = reader.readLine()) != null) {
                lineCount++;
                if (lineCount == 5) {
                    // Load data
                    loadGameData(file);
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


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
                saveGameData(new File(SAVE_FILE_PATH));
                JOptionPane.showMessageDialog(null, "Game saved successfully.");
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

    private void saveGameData(File file) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(slotNameLocal + "\n" + GameVars.difficultyLevel + "\n" + GameVars.characterType + "\n" + SlotInfo.slotCreationDate + "\n");
            // Save Dungeon.Map data
            for(int i = 0; i < 5; ++i){
                writer.write(arrayToString(Dungeon.Map.map[i]));
                writer.newLine();
            }
            writer.write(Integer.toString(Dungeon.Map.playerRow));
            writer.newLine();
            writer.write(Integer.toString(Dungeon.Map.playerCol));
            writer.newLine();
            writer.write(Integer.toString(Dungeon.Map.prevPlayerRow));
            writer.newLine();
            writer.write(Integer.toString(Dungeon.Map.prevPlayerCol));
            writer.newLine();

            // Save quest completion data
            writer.write(Boolean.toString(Dungeon.FinalBoss.complete));
            writer.newLine();
            writer.write(Boolean.toString(Dungeon.Quest10.complete));
            writer.newLine();
            writer.write(Boolean.toString(Dungeon.Quest20.complete));
            writer.newLine();
            writer.write(Boolean.toString(Dungeon.Quest30.complete));
            writer.newLine();
            writer.write(Boolean.toString(Dungeon.Quest40.complete));
            writer.newLine();
            writer.write(Boolean.toString(Dungeon.Quest50.complete));
            writer.newLine();

            // Save GameVars data
            StringBuilder inventoryBuilder = new StringBuilder();
            for (Item item : GameVars.inventory) {
                if (item instanceof Food) {
                    inventoryBuilder.append("Food;")
                            .append(item.name).append(";")
                            .append(((Food) item).price).append(";")
                            .append(((Food) item).hungerRestore).append(",");
                } else if (item instanceof HealingMedicine) {
                    inventoryBuilder.append("HealingMedicine;")
                            .append(item.name).append(";")
                            .append(((HealingMedicine) item).price).append(";")
                            .append(((HealingMedicine) item).healthAddition).append(",");
                } else if (item instanceof SanityMedicine) {
                    inventoryBuilder.append("SanityMedicine;")
                            .append(item.name).append(";")
                            .append(((SanityMedicine) item).price).append(";")
                            .append(((SanityMedicine) item).sanityAddition).append(",");
                } else if (item instanceof Weapon) {
                    inventoryBuilder.append("Weapon;")
                            .append(item.name).append(";")
                            .append(((Weapon) item).price).append(";")
                            .append(((Weapon) item).description).append(";")
                            .append(((Weapon) item).damage).append(";")
                            .append(((Weapon) item).missPercentage).append(",");
                } else if (item instanceof Armour) {
                    inventoryBuilder.append("Armour;")
                            .append(item.name).append(";")
                            .append(((Armour) item).price).append(";")
                            .append(((Armour) item).damageSubtractorPercentage).append(",");
                }else if(item instanceof DungeonKey){
                    inventoryBuilder.append("Key;")
                            .append(item.name).append(",");
                }
            }
            writer.write(inventoryBuilder.toString());
            writer.newLine();

            writer.write(Boolean.toString(GameVars.isGhost));
            writer.newLine();
            writer.write(Integer.toString(GameVars.health));
            writer.newLine();
            writer.write(Integer.toString(GameVars.sanity));
            writer.newLine();
            writer.write(Integer.toString(GameVars.hunger));
            writer.newLine();
            writer.write(Integer.toString(GameVars.balance));
            writer.newLine();
            writer.write(Integer.toString(GameVars.day));
            writer.newLine();
            writer.write(Integer.toString(GameVars.playerAttackPower));
            writer.newLine();
            writer.write(Integer.toString(GameVars.fullAttackPower));
            writer.newLine();
            writer.write(Integer.toString(GameVars.fullDefensePower));
            writer.newLine();

            writer.write("Weapon;" + GameVars.currWeapon.name + ";" + ((Weapon) GameVars.currWeapon).price + ";" + ((Weapon) GameVars.currWeapon).description + ";" + ((Weapon) GameVars.currWeapon).damage + ";" + ((Weapon) GameVars.currWeapon).missPercentage );
            writer.newLine();
            writer.write("Armour;" + GameVars.currArmour.name + ";" + ((Armour) GameVars.currArmour).price + ";" + ((Armour) GameVars.currArmour).damageSubtractorPercentage );
            writer.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadGameData(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            //skip first 4 lines as they were already taken care of in GameSlots
            reader.readLine();
            reader.readLine();
            reader.readLine();
            reader.readLine();
            // Load Dungeon.Map data
            Dungeon.Map.map[0] = stringToArray(reader.readLine());
            Dungeon.Map.map[1] = stringToArray(reader.readLine());
            Dungeon.Map.map[2] = stringToArray(reader.readLine());
            Dungeon.Map.map[3] = stringToArray(reader.readLine());
            Dungeon.Map.map[4] = stringToArray(reader.readLine());

            Dungeon.Map.playerRow = Integer.parseInt(reader.readLine());
            Dungeon.Map.playerCol = Integer.parseInt(reader.readLine());
            Dungeon.Map.prevPlayerRow = Integer.parseInt(reader.readLine());
            Dungeon.Map.prevPlayerCol = Integer.parseInt(reader.readLine());

            MapGUI.updateMapGUIAscii();

            // Load quest completion data
            Dungeon.FinalBoss.complete = Boolean.parseBoolean(reader.readLine());
            Dungeon.Quest10.complete = Boolean.parseBoolean(reader.readLine());
            Dungeon.Quest20.complete = Boolean.parseBoolean(reader.readLine());
            Dungeon.Quest30.complete = Boolean.parseBoolean(reader.readLine());
            Dungeon.Quest40.complete = Boolean.parseBoolean(reader.readLine());
            Dungeon.Quest50.complete = Boolean.parseBoolean(reader.readLine());

            // Load GameVars data
            GameVars.inventory.clear();
            String[] items = reader.readLine().split(",");
            for (String itemString : items) {
                String[] itemData = itemString.split(";");
                String itemType = itemData[0];
                switch (itemType) {
                    case "Food":
                        GameVars.inventory.add(new Food(itemData[1], Integer.parseInt(itemData[2]), Integer.parseInt(itemData[3])));
                        break;
                    case "HealingMedicine":
                        GameVars.inventory.add(new HealingMedicine(itemData[1], Integer.parseInt(itemData[2]), Integer.parseInt(itemData[3])));
                        break;
                    case "SanityMedicine":
                        GameVars.inventory.add(new SanityMedicine(itemData[1], Integer.parseInt(itemData[2]), Integer.parseInt(itemData[3])));
                        break;
                    case "Weapon":
                        GameVars.inventory.add(new Weapon(itemData[1], Integer.parseInt(itemData[2]), itemData[3], Integer.parseInt(itemData[4]), Integer.parseInt(itemData[5])));
                        break;
                    case "Armour":
                        GameVars.inventory.add(new Armour(itemData[1], Integer.parseInt(itemData[2]), Integer.parseInt(itemData[3])));
                        break;
                    case "Key":
                        GameVars.inventory.add(new DungeonKey(itemData[1]));
                        break;
                }
            }

            GameVars.isGhost = Boolean.parseBoolean(reader.readLine());
            GameVars.health = Integer.parseInt(reader.readLine());
            GameVars.sanity = Integer.parseInt(reader.readLine());
            GameVars.hunger = Integer.parseInt(reader.readLine());
            GameVars.balance = Integer.parseInt(reader.readLine());
            GameVars.day = Integer.parseInt(reader.readLine());
            GameVars.playerAttackPower = Integer.parseInt(reader.readLine());
            GameVars.fullAttackPower = Integer.parseInt(reader.readLine());
            GameVars.fullDefensePower = Integer.parseInt(reader.readLine());

            // Load current weapon and armour
            String[] currWeaponData = reader.readLine().split(";");
            GameVars.currWeapon = new Weapon(currWeaponData[1], Integer.parseInt(currWeaponData[2]), currWeaponData[3], Integer.parseInt(currWeaponData[4]), Integer.parseInt(currWeaponData[5]));

            String[] currArmourData = reader.readLine().split(";");
            GameVars.currArmour = new Armour(currArmourData[1], Integer.parseInt(currArmourData[2]), Integer.parseInt(currArmourData[3]));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Helper method to parse a string into a 2D int array
    public static int[] stringToArray(String str) {
         String[] line = str.split(" ");
         int[] array = new int[line.length];
          for (int j = 0; j < line.length; j++) {
              array[j] = Integer.parseInt(line[j]);
          }
        return array;
    }


    // Method to convert a 1D int array into a string
    public static String arrayToString(int[] array) {
        String str = "";
        for (int i = 0; i < array.length; i++) {
             str += array[i] + " ";
        }
        return str;
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
