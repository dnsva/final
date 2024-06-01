package Items;

/*
name: Anna
date: May 31, 2024
title: Item
description: The SUPERCLASS
*/

public class Item {

    //SUPERCLASS OF ANY PURCHASABLE ITEM

    public String name; //the name
    public int price; //the price

    public Item(String name){ //constructor
        this.name = name;
        this.price = 0;
    }

    public Item(String name, int price){ //constructor
        this.name = name;
        this.price = price;
    }

    public Item(){ //empty constructor
        this.name = "No name";
        this.price = 0;
    }

    public void use(){
        //to be overriden by medicine classes :)
    }

    public String toString(){ //of course we need a tostring
        return name;
    }

}
