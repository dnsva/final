package Items;

import Game.GameVars;

public class SanityMedicine extends Item{

    public int sanityAddition; //the amount of sanity it restores

    public SanityMedicine(String name, int price, int sanityAddition){ //constructor
        super(name, price); //super
        this.sanityAddition = sanityAddition; //set the sanity restore
    }

    public void use(){ //use the medicine
        //add to sanity
        GameVars.sanity += sanityAddition; //add to sanity
        if(GameVars.sanity > 100){ //dont go over 100
            GameVars.sanity = 100; //set to 100
        }
    }

}
