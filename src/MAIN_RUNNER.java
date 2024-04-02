
import Game.*;
import GameSlots.*;
import Shops.*;

public class MAIN_RUNNER {

    public static void main(String[] args){

        //SlotInfo slotInfoObject = new GameSlots.SlotInfo();


        new Game.SideBar(); //has to be created before HomeVillage or anything that uses it

        new Game.HomeVillage();

        //WeaponShop weaponShopObject = new Shops.WeaponShop();

        Game.HomeVillage.showHomeVillage();
        //GameSlots.SlotInfo.showInfoFrame();
    }

}
