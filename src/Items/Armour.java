package Items;

public class Armour {

    int damageSubtractorPercentage;
    public int adjustDamage(int damage){
        return (int)(damage * (1 - (damageSubtractorPercentage / 100.0))); //double check this later
    }


}
