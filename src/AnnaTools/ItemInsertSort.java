package AnnaTools;

//IMPORT ALL PACKAGES -----
import java.io.*;       //-
import java.util.*;     //-
import Dungeon.*;       //-
import Game.*;          //-
import Items.*;         //-
import Monsters.*;      //-
import Shops.*;         //-
//-------------------------
public class ItemInsertSort {


    public static void insertSort(ArrayList<Item> list){
        for (int top = 1; top < list.size(); top++){
            Item item = list.get(top); // temporary storage of item
            int i = top;
            while (i > 0 && item.toString().compareTo(list.get(i-1).toString()) < 0){
                list.set(i, list.get(i-1)); //shift larger items to the RIGHT by one
                i--; //prepare to check the next item to the left
            }
            list.set(i, item); //put sorted item in open location
        }
    }

    /*
    //TEST:
    public static void main(String[] args){
        ArrayList<Item> inventory = new ArrayList<Item>();
        inventory.add(new Items.Weapon("Sword", 10, "sdfsdf", 10, 10));
        inventory.add(new Items.Weapon("Axe", 10, "sdfsd", 10, 10));
        inventory.add(new Items.Armour("Helmet", 10, 10));

        insertSort(inventory);

        for (Item i : inventory){
            System.out.println(i);
        }

    }
    */
}
