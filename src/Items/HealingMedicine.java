package Items;

import Game.GameVars;

public class HealingMedicine extends Item{

    int healthAddition;

    public HealingMedicine(String name, int price, int healthAddition){
        this.name = name;
        this.price = price;
        this.healthAddition = healthAddition;
    }

    public void use(){
        //add to health
        GameVars.health += Math.min(100, healthAddition + GameVars.health);
    }

}
