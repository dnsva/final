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

/* This is a helper class. It is called from different classes to update sidebars. */
public class Updater {
    public static void updateAllSidePanels(){ //Function
        homeVillageSideBar.updatePanel(); //update home village
        weaponShopSideBar.updatePanel();  //update weapon shop
        mapGUISideBar.updatePanel();      //update map
        apothecarySideBar.updatePanel();  //update apothecary
        foodMarketSideBar.updatePanel();  //update food market
        armourShopSideBar.updatePanel();  //update armour shop
        quest50SideBar.updatePanel();     //update quest 50
        quest40SideBar.updatePanel();     //update quest 40
        quest30SideBar.updatePanel();     //update quest 30
        quest20SideBar.updatePanel();     //update quest 20
        quest10SideBar.updatePanel();     //update quest 10
        finalBossSideBar.updatePanel();   //update final boss
    }
}
