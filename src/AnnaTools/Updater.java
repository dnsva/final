package AnnaTools;

import static Dungeon.FinalBoss.finalBossSideBar;
import static Dungeon.MapGUI.mapGUISideBar;
import static Dungeon.Quest50.quest50SideBar;
import static Dungeon.Quest40.quest40SideBar;
import static Dungeon.Quest30.quest30SideBar;
import static Dungeon.Quest20.quest20SideBar;
import static Dungeon.Quest10.quest10SideBar;
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
        quest50SideBar.updatePanel();
        quest40SideBar.updatePanel();
        quest30SideBar.updatePanel();
        quest20SideBar.updatePanel();
        quest10SideBar.updatePanel();
        finalBossSideBar.updatePanel();
    }
}
