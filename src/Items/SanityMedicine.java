package Items;

import Game.GameVars;

public class SanityMedicine extends Item{

    int sanityAddition;

    public SanityMedicine(String name, int price, int sanityAddition){
        super(name, price);
        this.sanityAddition = sanityAddition;
    }

    public void use(){
        //add to sanity
        GameVars.sanity += Math.min(100, sanityAddition + GameVars.sanity);
    }

}
