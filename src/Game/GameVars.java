package Game;

//IMPORT ALL PACKAGES -----
import java.io.*;       //-
import java.util.*;     //-
import javax.swing.*;   //-
import java.awt.*;      //-
import Dungeon.*;       //-
import Items.*;         //-
import Monsters.*;      //-
import Shops.*;         //-
import AnnaTools.*;     //-
//-------------------------

public class GameVars {

    public static String difficulyLevel = ""; //depends on slot selection

    public static String characterType;

    public static ArrayList<Item> inventory = new ArrayList<Item>();

    public static int WINDOWWIDTH = 500;
    public static int SIDEBARWIDTH = 180;
    public static int WINDOWHEIGHT = 610;

    //int day = 0;
    public static int health = 100;
    public static int sanity = 100;
    public static int hunger = 100;
    public static int balance = 100;

    public static Items.Weapon currWeapon = new Weapon("none", 0, "", 0, 0);
    public static Items.Armour currArmour = new Armour("none", 0, 0);
}
