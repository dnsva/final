package Items;

import Game.GameVars;

public class HealingMedicine extends Item{

    public int healthAddition;

    public HealingMedicine(String name, int price, int healthAddition){
        super(name, price);
        this.healthAddition = healthAddition;
    }

    public void use(){
        //add to health
        GameVars.health += healthAddition;
        if(GameVars.health > 100){
            GameVars.health = 100;
        }
    }

}
