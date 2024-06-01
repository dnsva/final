package AnnaTools;

/*
name: Anna
date: Jan 31, 2024
title: ItemInsertSort
description: This class sorts items
 */

//IMPORT ALL PACKAGES -----
import java.util.*;     //-
import Items.*;         //-
//-------------------------
public class ItemInsertSort {
    public static void insertSort(ArrayList<Item> list){ //all items are of type Item
        for (int top = 1; top < list.size(); top++){ //for loop
            Item item = list.get(top); // temporary storage of item
            int i = top; //ok
            while (i > 0 && item.toString().compareTo(list.get(i-1).toString()) < 0){ //while loop
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
