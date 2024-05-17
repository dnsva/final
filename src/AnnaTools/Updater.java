package AnnaTools;

import static Dungeon.MapGUI.mapGUISideBar;
import static Game.HomeVillage.homeVillageSideBar;
import static Shops.WeaponShop.weaponShopSideBar;

public class Updater {

    public static void updateAllSidePanels(){
        homeVillageSideBar.updatePanel();
        weaponShopSideBar.updatePanel();
        mapGUISideBar.updatePanel();
    }
}
