import Game.HomeVillage;
import Game.SideBar;

public class MAIN_RUNNER {

    public static void main(String[] args){
        //GameSlots.SlotInfo slotInfoObject = new GameSlots.SlotInfo();
        //GameSlots.SlotInfo.showInfoFrame();

        new HomeVillage();
        Game.HomeVillage.showHomeVillage();

       // new Game.SideBar(); //this is a panel. this is added to other frames as needed


        /*
        Now, with the info from game slots, load the info into GameVars using
        maybe a constructor in GameVars. Right now there is no gameVars constructor
         */



        new HomeVillage(); //OK
        new Shops.WeaponShop();
    }
}
