package Monsters;

public class MasterBaker{

    int health;
    String name;
    int attackPower;
    public MasterBaker() {
        health = 100;
        if(Game.GameVars.difficulyLevel.equals("Easy"))
            attackPower = 10;
        else if(Game.GameVars.difficulyLevel.equals("Medium"))
            attackPower = 20;
        else if(Game.GameVars.difficulyLevel.equals("Hard"))
            attackPower = 30;

        name = "Master Baker";
    }


}
