package Items;

public class Item {

    //SUPERCLASS OF ANY PURCHASEABLE ITEM

    public String name;
    public int price;

    public Item(String name){
        this.name = name;
        this.price = 0;
    }

    public Item(String name, int price){
        this.name = name;
        this.price = price;
    }

    public Item(){
        this.name = "No name";
        this.price = 0;
    }

    public void use(){
        //to be overriden by medicine classes :)

    }
    public String toString(){
        return name;
    }

}
