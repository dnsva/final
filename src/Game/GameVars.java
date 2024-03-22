package Game;

import java.util.ArrayList;
import Items.Item;

public class GameVars {

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

    public static String currWeapon = "None";
    public static String currArmour = "None";

}
