package Game;

//IMPORT ALL PACKAGES -----
import java.util.*;     //-

import Items.*;         //-

//-------------------------

public class GameVars {
    public static String slotNameLocal = ""; //depends on slot selection

    public static String difficultyLevel = ""; //depends on slot selection

    public static String characterType; //depends on slot selection

    public static ArrayList<Item> inventory = new ArrayList<Item>(); //inventory

    public static int WINDOWWIDTH = 500; //500 normal
    public static int SIDEBARWIDTH = 180; //180 normal
    public static int WINDOWHEIGHT = 610; //610 normal

    public static boolean isGhost = false; //if the player is a ghost

    public static int health = 100; //player health
    public static int sanity = 100; //player sanity
    public static int hunger = 0; //player hunger

    public static int balance = 100; //player balance
    public static int day = 0; //day counter
    public static int playerAttackPower = 0; //based on character choice
    public static int fullAttackPower = 0; //based on weapon
    public static int fullDefensePower = 0; //based on armour
    public static Items.Weapon currWeapon = new Weapon("none", 0, " ", 0, 0);
    public static Items.Armour currArmour = new Armour("none", 0, 0); //current armour
}
