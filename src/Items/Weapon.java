package Items;

public class Weapon extends Item {
    //inherits price and name
    public String description;
    public int damage;
    public int missPercentage;
   // int usesRemaining; implement later

    public Weapon(String name, int price, String description, int damage, int missPercentage){
        this.name = name;
        this.price = price;
        this.description = description;
        this.damage = damage;
        this.missPercentage = missPercentage;
    }

    public boolean attackMiss(){
        int random = (int)(Math.random() * 100) + 1;
        if(random <= missPercentage){
            return true;
        }
        return false;
    }

}
