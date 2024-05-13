package Items;

public class Armour extends Item{

    int damageSubtractorPercentage;
    String type;

    public Armour(String name, int price, int damageSubtractorPercentage){
        super(name, price);
        this.damageSubtractorPercentage = damageSubtractorPercentage;
    }
    public int adjustDamage(int damage){
        return (int)(damage * (1 - (damageSubtractorPercentage / 100.0))); //double check this later
    }


}
