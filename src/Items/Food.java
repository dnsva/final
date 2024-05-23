package Items;

import Game.GameVars;
public class Food extends Item {

    //name and price inhereted from item

    public int hungerRestore; //the amount of hunger it restores

    //add to hunger

    public Food(String name, int price, int hungerRestore){
        super(name, price);
        this.hungerRestore = hungerRestore;
    }

    public void eat(){
        //add to hunger
        GameVars.hunger -= hungerRestore;
        if(GameVars.hunger < 0){
            GameVars.hunger = 0;
        }
    }

}
