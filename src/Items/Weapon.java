package Items;

public class Weapon extends Item {
    //inherits price and name
    public String description; //description of the weapon
    public int damage; //the amount of damage the weapon does
    public int missPercentage; //the percentage chance of missing
   // int usesRemaining; implement later

    public Weapon(String name, int price, String description, int damage, int missPercentage){ //constructor
        //set everything accordingly:
        this.name = name;
        this.price = price;
        this.description = description;
        this.damage = damage;
        this.missPercentage = missPercentage;
    }

    public boolean attackMiss(){ //returns true if the attack misses
        int random = (int)(Math.random() * 100) + 1; //random number between 1 and 100
        if(random <= missPercentage){ //if the random number is less than or equal to the miss percentage
            return true;
        }
        return false;
    }

}
