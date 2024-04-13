
import Game.*;
import GameSlots.*;
import Shops.*;

import java.util.ArrayList;

public class MAIN_RUNNER {

    public static void main(String[] args){

      //  SlotInfo slotInfoObject = new GameSlots.SlotInfo();

        //the folllowing code creates and initializes an arraylist with strings "a", "b", and "c"
        ArrayList<String> hi = new ArrayList<String>();
        hi.add("a");
        hi.add("b");
        hi.add("c");

        ArrayList<String> hi2 = new ArrayList<String>();
        hi2.add("a2");
        hi2.add("b2");
        hi2.add("c2");


        new Game.Inventory();

       // new Game.SideBar(); //has to be created before HomeVillage or anything that uses it

      //  new Game.HomeVillage();

      //  WeaponShop weaponShopObject = new Shops.WeaponShop();

        //Game.HomeVillage.showHomeVillage();
     //   GameSlots.SlotInfo.showInfoFrame();
    }

}
