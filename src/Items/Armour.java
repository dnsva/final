package Items;

public class Armour extends Item{

    public int damageSubtractorPercentage; //damage subtractor percentage
    String type; //type of armour

    public Armour(String name, int price, int damageSubtractorPercentage){ //constructor
        super(name, price); //super
        this.damageSubtractorPercentage = damageSubtractorPercentage; //set the damage subtractor percentage
    }
    public int adjustDamage(int damage){ //adjust the damage
        return (int)(damage * (1 - (damageSubtractorPercentage / 100.0))); //adjusts damage
    }

}
