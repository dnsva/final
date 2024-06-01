package Items;

import Game.GameVars;
public class Food extends Item {

    //name and price inherited from item

    public int hungerRestore; //the amount of hunger it restores

    //add to hunger

    public Food(String name, int price, int hungerRestore){ //constructor
        super(name, price); //super
        this.hungerRestore = hungerRestore; //set the hunger restore
    }

    public void eat(){
        //add to hunger
        GameVars.hunger -= hungerRestore; //add to hunger
        if(GameVars.hunger < 0){ //dont go negative
            GameVars.hunger = 0;
        }
    }

}
