package Items;

public class Curse extends Item{

    String type; //silence - no spells, weakness - reduce physical by 50%, misfortune - ???

    public Curse(String name, int price, String type){
        super(name, price);
        this.type = type;
    }


}
