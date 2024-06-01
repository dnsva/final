package Game;

/*
name: Anna
date: May 31, 2024
title: Home Village
description: Basically the main menu
*/

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

    public static JFrame homeVillageFrame = new JFrame(); //the main frame
    public static SideBar homeVillageSideBar = new SideBar(); //the side bar
    public static JPanel homeVillagePanel; //the main panel

    public HomeVillage(){ //constructor

        runTimerThread(); //run the timer thread
        new AnnaTools.Fonts(); //initialize the fonts

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
        File file = new File(SAVE_FILE_PATH); //the file path
        if (!file.exists()) { //if the file does not exist
            System.out.println("File does not exist."); //print
            return; //return
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) { //try to read the file
            String line; //make a string for each line
            int lineCount = 0; //initialize the line count
            while ((line = reader.readLine()) != null) { //while there are lines
                lineCount++; //add to the line count
                if (lineCount == 5) {
                    // Load data
                    loadGameData(file); //load the data
                    break; //break
                }
            }
        } catch (IOException e) { //catch exception
            e.printStackTrace(); //print exception
        }


        //--------------------------------------------------------------------------------

        homeVillageFrame = new JFrame("By Anna Denisova"); //initialize
       // homeVillageFrame.setTitle("By Anna Denisova");
        homeVillageFrame.setSize(GameVars.WINDOWWIDTH + GameVars.SIDEBARWIDTH, GameVars.WINDOWHEIGHT); //set size
        homeVillageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //close setting
        homeVillageFrame.setLocationRelativeTo(null); //center
        homeVillageFrame.getContentPane().setLayout(new BorderLayout()); //make layout border

        homeVillagePanel = new JPanel(); //new panel
        homeVillagePanel.setSize(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT); //set size
        //homeVillagePanel.setLayout(new BoxLayout(homeVillagePanel, BoxLayout.Y_AXIS));
        homeVillagePanel.setLayout(new FlowLayout(FlowLayout.CENTER)); //center layout
        homeVillagePanel.setBackground(Color.decode("#C2F9BB")); //set background color

        JLabel titleLabel = new JLabel("Village"); //label
        titleLabel.setFont(Fonts.Pamela); //set font
        titleLabel.setPreferredSize(new Dimension(GameVars.WINDOWWIDTH, 100)); //set size
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT); //center
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER); //center
        //titleLabel.setForeground(Color.white);

        homeVillagePanel.add(Box.createVerticalStrut(20)); //add space between
        homeVillagePanel.add(titleLabel); //add the title label
        homeVillagePanel.add(Box.createVerticalStrut(20)); //add space between

        JPanel buttonPanel = new JPanel(new GridLayout(7, 1)); //new panel 7 rows 1 cols
        buttonPanel.setSize(GameVars.WINDOWWIDTH-50, GameVars.WINDOWHEIGHT-150); //set size
        buttonPanel.setPreferredSize(new Dimension(GameVars.WINDOWWIDTH-50, GameVars.WINDOWHEIGHT-150)); //set size
        buttonPanel.setBackground(Color.decode("#C2F9BB")); //set background color

        JButton apothecaryButton = new JButton("Apothecary"); //button
        buttonPanel.add(apothecaryButton); //add to panel
        apothecaryButton.addActionListener(new ActionListener() { //on click
            @Override
            public void actionPerformed(ActionEvent e) { //on click
                if(!isGhost){ //if not a ghost
                    Shops.Apothecary.showApothecary(); //show the apothecary
                    hideHomeVillage(); //hide the home village
                }else{
                    JOptionPane.showMessageDialog(null, "You are a ghost and cannot use the apothecary."); //if a ghost
                }

            }
        });
        JButton armourShopButton = new JButton("Armour Shop"); //button
        buttonPanel.add(armourShopButton); //add to panel
        armourShopButton.addActionListener(new ActionListener() { //on click
            @Override
            public void actionPerformed(ActionEvent e) { //on click
                if(!isGhost) { //if not a ghost
                    Shops.ArmourShop.showArmourShop(); //show the armour shop
                    hideHomeVillage(); //hide the home village
                }else{ //if a ghost
                    //message error:
                    JOptionPane.showMessageDialog(null, "You are a ghost and cannot use the armour shop.");
                }
            }
        });
        JButton foodMarket = new JButton("Food Market"); //button
        buttonPanel.add(foodMarket); //add to panel
        foodMarket.addActionListener(new ActionListener() { //on click
            @Override
            public void actionPerformed(ActionEvent e) { //on click
                if(!isGhost){ //if not a ghost
                    Shops.FoodMarket.showFoodMarket(); //show the food market
                    hideHomeVillage(); //hide the home village
                }else{
                    JOptionPane.showMessageDialog(null, "You are a ghost and cannot use the food market."); //if a ghost
                }
            }
        });
        JButton weaponShopButton = new JButton("Weapon Shop"); //button
        buttonPanel.add(weaponShopButton); //add to panel
        weaponShopButton.addActionListener(new ActionListener() { //on click
            public void actionPerformed(ActionEvent e) { //on click
                if(!isGhost){ //if not a ghost
                    Shops.WeaponShop.showWeaponShop(); //show the weapon shop
                    hideHomeVillage(); //hide the home village
                }else{
                    //error message:
                    JOptionPane.showMessageDialog(null, "You are a ghost and cannot use the weapon shop.");
                }
            }
        });
        JButton dungeonButton = new JButton("Dungeon"); //button
        buttonPanel.add(dungeonButton); //add to panel
        dungeonButton.addActionListener(new ActionListener() { //on click
            @Override
            public void actionPerformed(ActionEvent e) { //on click
                if(!isGhost){ //if not a ghost
                    MapGUI.showMapGUI();
                    //hideHomeVillage();
                }else{
                    //error message:
                    JOptionPane.showMessageDialog(null, "You are a ghost and cannot enter the dungeon.");
                }

            }
        });
        JButton exitButton = new JButton("Exit"); //button
        buttonPanel.add(exitButton); //add to panel
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //GameSlots.SlotInfo.showInfoFrame();
                hideHomeVillage(); //hide the home village
                //dispose of program/end program:
                System.exit(0);
            }
        });
        JButton saveGameButton = new JButton("Save Game"); //button
        buttonPanel.add(saveGameButton); //add to panel
        saveGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { //on click
                saveGameData(new File(SAVE_FILE_PATH)); //save the game data
                JOptionPane.showMessageDialog(null, "Game saved successfully."); //message
            }
        });

        JButton[] buttons = {apothecaryButton, armourShopButton, foodMarket, weaponShopButton, dungeonButton, exitButton, saveGameButton}; //all the buttons
        for(JButton button : buttons){ //for all buttons
            button.setAlignmentX(Component.CENTER_ALIGNMENT); //Align the button to center
            //homeVillagePanel.add(button); //ADD ALL THE BUTTONS TO THE PANEL
            //homeVillagePanel.add(Box.createVerticalStrut(10)); //create space in between each button
        }

        homeVillagePanel.add(buttonPanel); //add the button panel

        homeVillageFrame.add(homeVillagePanel, BorderLayout.CENTER); //add the panel to the frame

        JSeparator separator = new JSeparator(SwingConstants.VERTICAL); //separator
        homeVillageFrame.add(homeVillageSideBar.getPanel(), BorderLayout.EAST); //add the side bar

        //homeVillageFrame.pack();

    }

    private void saveGameData(File file) { //save the game data
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) { //try to write to the file
            writer.write(slotNameLocal + "\n" + GameVars.difficultyLevel + "\n" + GameVars.characterType + "\n" + SlotInfo.slotCreationDate + "\n"); //write the slot info
            // Save Dungeon.Map data
            for(int i = 0; i < 5; ++i){ //for the first 5 rows, save the 5 rows of the map array
                writer.write(arrayToString(Dungeon.Map.map[i])); //write the array to the file
                writer.newLine(); //new line
            }
            writer.write(Integer.toString(Dungeon.Map.playerRow)); //write the player row
            writer.newLine();
            writer.write(Integer.toString(Dungeon.Map.playerCol)); //write the player col
            writer.newLine();
            writer.write(Integer.toString(Dungeon.Map.prevPlayerRow)); //write the previous player row
            writer.newLine();
            writer.write(Integer.toString(Dungeon.Map.prevPlayerCol)); //write the previous player col
            writer.newLine();

            // Save quest completion data
            writer.write(Boolean.toString(Dungeon.FinalBoss.complete)); //write the final boss completion
            writer.newLine();
            writer.write(Boolean.toString(Dungeon.Quest10.complete)); //write the quest 10 completion
            writer.newLine();
            writer.write(Boolean.toString(Dungeon.Quest20.complete)); //write the quest 20 completion
            writer.newLine();
            writer.write(Boolean.toString(Dungeon.Quest30.complete)); //write the quest 30 completion
            writer.newLine();
            writer.write(Boolean.toString(Dungeon.Quest40.complete)); //write the quest 40 completion
            writer.newLine();
            writer.write(Boolean.toString(Dungeon.Quest50.complete)); //write the quest 50 completion
            writer.newLine();

            // Save GameVars data
            /* The following code constructs the inventory list as a string in a way that all
            * item constructor values are separated with semicolons and seperate items are
            * separated by commas. The first value of a item is its class type. An inventory
            * item of type Food starts with Food and then, separated by semicolons
            * includes all parts of the food constructor */
            StringBuilder inventoryBuilder = new StringBuilder(); //a string builder to put everythign together
            for (Item item : GameVars.inventory) { //for all items in the inventory
                if (item instanceof Food) { //if the item is food
                    inventoryBuilder.append("Food;") //append food
                            .append(item.name).append(";") //append name
                            .append(((Food) item).price).append(";") //append price
                            .append(((Food) item).hungerRestore).append(","); //append hunger restore
                } else if (item instanceof HealingMedicine) { //if the item is healing medicine
                    inventoryBuilder.append("HealingMedicine;") //append healing medicine
                            .append(item.name).append(";") //append name
                            .append(((HealingMedicine) item).price).append(";") //append price
                            .append(((HealingMedicine) item).healthAddition).append(","); //append health addition
                } else if (item instanceof SanityMedicine) { //if the item is sanity medicine
                    inventoryBuilder.append("SanityMedicine;") //append sanity medicine
                            .append(item.name).append(";") //append name
                            .append(((SanityMedicine) item).price).append(";") //append price
                            .append(((SanityMedicine) item).sanityAddition).append(","); //append sanity addition
                } else if (item instanceof Weapon) { //if the item is a weapon
                    inventoryBuilder.append("Weapon;") //append weapon
                            .append(item.name).append(";") //append name
                            .append(((Weapon) item).price).append(";") //append price
                            .append(((Weapon) item).description).append(";") //append description
                            .append(((Weapon) item).damage).append(";") //append damage
                            .append(((Weapon) item).missPercentage).append(","); //append miss percentage
                } else if (item instanceof Armour) { //if the item is armour
                    inventoryBuilder.append("Armour;") //append armour
                            .append(item.name).append(";") //append name
                            .append(((Armour) item).price).append(";") //append price
                            .append(((Armour) item).damageSubtractorPercentage).append(","); //append damage subtractor percentage
                }else if(item instanceof DungeonKey){ //if the item is a key
                    inventoryBuilder.append("Key;") //append key
                            .append(item.name).append(","); //append name
                }
            }
            writer.write(inventoryBuilder.toString()); //Convert the string builder to a string!!!
            writer.newLine(); //new line

            writer.write(Boolean.toString(GameVars.isGhost)); //write if the player is a ghost
            writer.newLine();
            writer.write(Integer.toString(GameVars.health)); //write the health
            writer.newLine();
            writer.write(Integer.toString(GameVars.sanity)); //write the sanity
            writer.newLine();
            writer.write(Integer.toString(GameVars.hunger)); //write the hunger
            writer.newLine();
            writer.write(Integer.toString(GameVars.balance)); //write the balance
            writer.newLine();
            writer.write(Integer.toString(GameVars.day)); //write the day
            writer.newLine();
            writer.write(Integer.toString(GameVars.playerAttackPower)); //write the player attack power
            writer.newLine();
            writer.write(Integer.toString(GameVars.fullAttackPower)); //write the full attack power
            writer.newLine();
            writer.write(Integer.toString(GameVars.fullDefensePower)); //write the full defense power
            writer.newLine();

            //write the current weapon in the same format as the inventory items
            writer.write("Weapon;" + GameVars.currWeapon.name + ";" + ((Weapon) GameVars.currWeapon).price + ";" + ((Weapon) GameVars.currWeapon).description + ";" + ((Weapon) GameVars.currWeapon).damage + ";" + ((Weapon) GameVars.currWeapon).missPercentage );
            writer.newLine();
            //write the current armour in the same format as the inventory items
            writer.write("Armour;" + GameVars.currArmour.name + ";" + ((Armour) GameVars.currArmour).price + ";" + ((Armour) GameVars.currArmour).damageSubtractorPercentage );
            writer.newLine();

        } catch (IOException e) { //catch so that nothing needs to be thrown
            e.printStackTrace();
        }
    }

    private void loadGameData(File file) { //load the game data
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) { //try to read the file
            //skip first 4 lines as they were already taken care of in GameSlots
            reader.readLine();
            reader.readLine();
            reader.readLine();
            reader.readLine();
            // Load Dungeon.Map data
            Dungeon.Map.map[0] = stringToArray(reader.readLine()); //load the map
            Dungeon.Map.map[1] = stringToArray(reader.readLine()); //load the map
            Dungeon.Map.map[2] = stringToArray(reader.readLine()); //load the map
            Dungeon.Map.map[3] = stringToArray(reader.readLine()); //load the map
            Dungeon.Map.map[4] = stringToArray(reader.readLine()); //load the map

            Dungeon.Map.playerRow = Integer.parseInt(reader.readLine()); //load the player row
            Dungeon.Map.playerCol = Integer.parseInt(reader.readLine()); //load the player col
            Dungeon.Map.prevPlayerRow = Integer.parseInt(reader.readLine()); //load the previous player row
            Dungeon.Map.prevPlayerCol = Integer.parseInt(reader.readLine()); //load the previous player col

            MapGUI.updateMapGUIAscii(); //update the map GUI to account for new values read in above
            //this is important

            // Load quest completion data
            Dungeon.FinalBoss.complete = Boolean.parseBoolean(reader.readLine()); //load the final boss completion
            Dungeon.Quest10.complete = Boolean.parseBoolean(reader.readLine()); //load the quest 10 completion
            Dungeon.Quest20.complete = Boolean.parseBoolean(reader.readLine()); //load the quest 20 completion
            Dungeon.Quest30.complete = Boolean.parseBoolean(reader.readLine()); //load the quest 30 completion
            Dungeon.Quest40.complete = Boolean.parseBoolean(reader.readLine()); //load the quest 40 completion
            Dungeon.Quest50.complete = Boolean.parseBoolean(reader.readLine()); //load the quest 50 completion

            // Load GameVars data
            GameVars.inventory.clear(); //clear the inventory
            String[] items = reader.readLine().split(","); //split the items
            for (String itemString : items) { //for all items
                String[] itemData = itemString.split(";"); //split the item data
                String itemType = itemData[0]; //get the item type
                switch (itemType) { //switch on the item type
                    case "Food": //if the item is food
                        //use the food constructor
                        GameVars.inventory.add(new Food(itemData[1], Integer.parseInt(itemData[2]), Integer.parseInt(itemData[3])));
                        break;
                    case "HealingMedicine": //if the item is healing medicine
                        //use the healing medicine constructor
                        GameVars.inventory.add(new HealingMedicine(itemData[1], Integer.parseInt(itemData[2]), Integer.parseInt(itemData[3])));
                        break;
                    case "SanityMedicine": //if the item is sanity medicine
                        //use the sanity medicine constructor
                        GameVars.inventory.add(new SanityMedicine(itemData[1], Integer.parseInt(itemData[2]), Integer.parseInt(itemData[3])));
                        break;
                    case "Weapon": //if the item is a weapon
                        //use the weapon constructor
                        GameVars.inventory.add(new Weapon(itemData[1], Integer.parseInt(itemData[2]), itemData[3], Integer.parseInt(itemData[4]), Integer.parseInt(itemData[5])));
                        break;
                    case "Armour": //if the item is armour
                        //use the armour constructor
                        GameVars.inventory.add(new Armour(itemData[1], Integer.parseInt(itemData[2]), Integer.parseInt(itemData[3])));
                        break;
                    case "Key": //if the item is a key
                        //use the key constructor
                        GameVars.inventory.add(new DungeonKey(itemData[1]));
                        break;
                }
            }

            GameVars.isGhost = Boolean.parseBoolean(reader.readLine()); //load if the player is a ghost
            GameVars.health = Integer.parseInt(reader.readLine()); //load the health
            GameVars.sanity = Integer.parseInt(reader.readLine()); //load the sanity
            GameVars.hunger = Integer.parseInt(reader.readLine()); //load the hunger
            GameVars.balance = Integer.parseInt(reader.readLine()); //load the balance
            GameVars.day = Integer.parseInt(reader.readLine()); //load the day
            GameVars.playerAttackPower = Integer.parseInt(reader.readLine()); //load the player attack power
            GameVars.fullAttackPower = Integer.parseInt(reader.readLine()); //load the full attack power
            GameVars.fullDefensePower = Integer.parseInt(reader.readLine()); //load the full defense power

            // Load current weapon and armour
            String[] currWeaponData = reader.readLine().split(";");
            GameVars.currWeapon = new Weapon(currWeaponData[1], Integer.parseInt(currWeaponData[2]), currWeaponData[3], Integer.parseInt(currWeaponData[4]), Integer.parseInt(currWeaponData[5]));
            //same thing:
            String[] currArmourData = reader.readLine().split(";");
            GameVars.currArmour = new Armour(currArmourData[1], Integer.parseInt(currArmourData[2]), Integer.parseInt(currArmourData[3]));

        } catch (IOException e) { //catch to avoid throwing
            e.printStackTrace(); //ok
        }
    }

    // Helper method to parse a string into a 2D int array
    public static int[] stringToArray(String str) { //convert a string to an array
         String[] line = str.split(" "); //split the string
         int[] array = new int[line.length]; //make an array
          for (int j = 0; j < line.length; j++) { //for all the elements
              array[j] = Integer.parseInt(line[j]); //parse the string to an int
          }
        return array; //return the array
    }


    // Method to convert a 1D int array into a string
    public static String arrayToString(int[] array) { //convert an array to a string THE OPPOSITE OF THE FUNCTION ABOVE
        String str = ""; //initialize the string
        for (int i = 0; i < array.length; i++) { //for all the elements
             str += array[i] + " "; //add the element to the string
        }
        return str; //return the string
    }



    public static void showHomeVillage(){ //show the home village
        homeVillageFrame.setVisible(true); //set visible
    }

    public static void hideHomeVillage(){ //hide the home village
        homeVillageFrame.setVisible(false); //set invisible
    }

    public static void loadUserBenefits(String name){ //load the user benefits

        if(name.equals("Wizard")){ //if the character is a wizard

            //Start game with sanity medicine

            GameVars.inventory.add(new SanityMedicine("Basket of Berries", 25, 25));

            //25% off all sanity potions
            for(int i = 0; i < Apothecary.medicineList.length; ++i){ //for all the medicines
                if(Apothecary.medicineList[i] instanceof SanityMedicine){ //if the medicine is sanity medicine
                    Apothecary.medicineList[i].price = (int)(Apothecary.medicineList[i].price * 0.75); //set the price
                }
            }

            //Default Attack Power: 10
            GameVars.playerAttackPower = 10; //set the player attack power
            GameVars.fullAttackPower = 10; //set the full attack power
            AnnaTools.Updater.updateAllSidePanels(); //update all side panels

        }else if(name.equals("Mime")){
            /*
            "<p>&nbsp;- Useless and weak</p>" +
            "<p>&nbsp;- Gets 1 free apple</p>" +
            "<p>&nbsp;- Default attack power: 1</p>" +
             */

            GameVars.inventory.add(new Food("Apple", 0, 1)); //add an apple
            GameVars.playerAttackPower = 1; //set the player attack power
            GameVars.fullAttackPower = 1; //set the full attack power
            AnnaTools.Updater.updateAllSidePanels(); //update all side panels

        }else if(name.equals("Warrior")){

            //Start game with axe
            GameVars.inventory.add(new Weapon("Axe",
                    15,
                    "For smashing enemies",
                    GameVars.difficultyLevel.equals("Easy") ? 15 : GameVars.difficultyLevel.equals("Medium") ? 10 : 5,
                    GameVars.difficultyLevel.equals("Easy") ? 15 : GameVars.difficultyLevel.equals("Medium") ? 25 : 30));

            //25% off all weapons
            for(int i = 0; i < WeaponShop.weaponList.length; ++i){ //for all the weapons
                WeaponShop.weaponList[i].price = (int)(WeaponShop.weaponList[i].price * 0.75); //set the price
            }

            //Default attack power: 15
            GameVars.playerAttackPower = 15; //set the player attack power
            GameVars.fullAttackPower = 15; //set the full attack power
            AnnaTools.Updater.updateAllSidePanels(); //update all side panels

        }else if(name.equals("Doctor")){
            /*
            "<p>&nbsp;- Start game with basket of berries</p>" +
            "<p>&nbsp;- 25% off all healing potions</p>" +
            "<p>&nbsp;- Default attack power: 10</p>" +
             */

            GameVars.inventory.add(new HealingMedicine("Regeneration Pill", 25, 25)); //add a healing medicine

            for(int i = 0; i < Apothecary.medicineList.length; ++i){ //for all the medicines
                if(Apothecary.medicineList[i] instanceof HealingMedicine){ //if the medicine is healing medicine
                    Apothecary.medicineList[i].price = (int)(Apothecary.medicineList[i].price * 0.75); //set the price
                }
            }

            GameVars.playerAttackPower = 10; //set the player attack power
            GameVars.fullAttackPower = 10; //set the full attack power
            AnnaTools.Updater.updateAllSidePanels(); //update all side panels

        }else if(name.equals("Farmer")) { //if the character is a farmer
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
            GameVars.playerAttackPower = 5; //set the player attack power
            GameVars.fullAttackPower = 5; //set the full attack power
            AnnaTools.Updater.updateAllSidePanels(); //update all side panels
        }
    }

    public static void runTimerThread() {
        // Ensure GUI updates happen on the Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() { //run the thread
            @Override
            public void run() {
                // Initial actions when the GUI is first shown
                GameVars.day++; // times goes by literally
                if(!isGhost){ //if not a ghost
                    GameVars.hunger += 5; // you get hungrier
                    checkGameOver(); //check if the game is over
                }
               // System.out.println("SOMETHING HAPPENENENDNNDN");
                AnnaTools.Updater.updateAllSidePanels(); // OK
            }
        });

        // Create and start the periodic task in a separate thread
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1); //create a scheduler

        Runnable task = new Runnable() {
            @Override
            public void run() {
                // Update GameVars and print message every second
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() { //run the thread
                        GameVars.day++; // times goes by literally
                        if(!isGhost){ //if not a ghost
                            GameVars.hunger += 5; // you get hungrier
                            checkGameOver(); //check if the game is over
                        }
                       // System.out.println("SOMETHING HAPPENENENDNNDN");
                        AnnaTools.Updater.updateAllSidePanels(); // OK
                    }
                });
            }
        };

        scheduler.scheduleAtFixedRate(task, 1, 1, TimeUnit.MINUTES); //run the task every minute
        /* The following code forces the schedule to shut down on any exit */
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() { //run the thread
            @Override
            public void run() { //run the thread
                //System.out.println("Shutting down scheduler..."); //print
                scheduler.shutdown(); //shut down the scheduler
                try {
                    if (!scheduler.awaitTermination(5, TimeUnit.SECONDS)) { //if the scheduler does not terminate
                        //System.out.println("Forcing shutdown..."); //print
                        scheduler.shutdownNow(); //force shut down
                    }
                } catch (InterruptedException e) { //catch so that you dont have to throw
                    e.printStackTrace(); //print
                    scheduler.shutdownNow(); //force shut down
                }
                //System.out.println("Scheduler shut down.");
            }
        }));
    }


}
