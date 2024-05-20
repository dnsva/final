package Game;

//IMPORT ALL PACKAGES -----
import java.util.*;     //-

import Items.*;         //-

//-------------------------

public class GameVars {

    public static String difficultyLevel = ""; //depends on slot selection

    public static String characterType;

    public static ArrayList<Item> inventory = new ArrayList<Item>();

    public static int WINDOWWIDTH = 500;
    public static int SIDEBARWIDTH = 180;
    public static int WINDOWHEIGHT = 610;

    //int day = 0;
    public static int health = 100;
    public static int sanity = 100;
    public static int hunger = 0;
    public static int balance = 100;
    public static int day = 0;
    public static int playerAttackPower = 0; //based on character choice
    public static int fullAttackPower = 0; //based on weapon, a percentage
    public static int fullDefensePower = 0;
    public static Items.Weapon currWeapon = new Weapon("none", 0, "", 0, 0);
    public static Items.Armour currArmour = new Armour("none", 0, 0);
}
