package Items;

public class Item {

    //SUPERCLASS OF ANY PURCHASEABLE ITEM

    public String name;
    public int price;

    public Item(String name, int price){
        this.name = name;
        this.price = price;
    }

    public Item(){
        this.name = "No name";
        this.price = 0;
    }

    public String toString(){
        return name;
    }

}
