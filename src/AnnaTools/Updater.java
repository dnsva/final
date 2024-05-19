package AnnaTools;

import static Dungeon.MapGUI.mapGUISideBar;
import static Game.HomeVillage.homeVillageSideBar;
import static Shops.Apothecary.apothecarySideBar;
import static Shops.ArmourShop.armourShopSideBar;
import static Shops.WeaponShop.weaponShopSideBar;
import static Shops.FoodMarket.foodMarketSideBar;

public class Updater {

    public static void updateAllSidePanels(){
        homeVillageSideBar.updatePanel();
        weaponShopSideBar.updatePanel();
        mapGUISideBar.updatePanel();
        apothecarySideBar.updatePanel();
        foodMarketSideBar.updatePanel();
        armourShopSideBar.updatePanel();
    }
}
