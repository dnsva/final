package Items;

import Game.GameVars;

public class HealingMedicine extends Item{

    public int healthAddition; //the amount of health it restores

    public HealingMedicine(String name, int price, int healthAddition){ //constructor
        super(name, price); //super
        this.healthAddition = healthAddition; //set the health restore
    }

    public void use(){
        //add to health
        GameVars.health += healthAddition; //add to health
        if(GameVars.health > 100){ //dont go over 100
            GameVars.health = 100;
        }
    }

}
