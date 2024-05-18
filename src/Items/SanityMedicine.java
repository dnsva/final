package Items;

import Game.GameVars;

public class SanityMedicine extends Item{

    public int sanityAddition;

    public SanityMedicine(String name, int price, int sanityAddition){
        super(name, price);
        this.sanityAddition = sanityAddition;
    }

    public void use(){
        //add to sanity
        GameVars.sanity += sanityAddition;
        if(GameVars.sanity > 100){
            GameVars.sanity = 100;
        }
    }

}
