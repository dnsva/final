package Game;

import java.util.*;
import Items.*;

public class GameVars {

    public static String difficulyLevel = "Easy"; //depends on slot selection

    public static String characterType;

    public static ArrayList<Item> inventory = new ArrayList<Item>();

    public static int WINDOWWIDTH = 500;
    public static int SIDEBARWIDTH = 300;
    public static int WINDOWHEIGHT = 300;

    //int day = 0;
    public static int health = 100;
    public static int sanity = 100;
    public static int hunger = 100;
    public static int balance = 100;

    public static boolean isPoisoned = false;
    public static boolean isParalyzed = false;
    public static boolean isConfused = false;

    public static Items.Weapon currWeapon = null;
    public static Items.Armour currArmour = null;

    //Temp code:
    public GameVars(){
        inventory.add(new Items.Weapon("Sword", 10, "sdfsdf", 10, 10));
        inventory.add(new Items.Weapon("Axe", 10, "sdfsd", 10, 10));
        inventory.add(new Items.Armour("Helmet", 10, 10));
    }
}
