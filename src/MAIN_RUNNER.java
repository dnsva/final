import Game.GameVars;
import Game.HomeVillage;
import GameSlots.*;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.*; //for health and day timers
public class MAIN_RUNNER {

    public static void main(String[] args) {

        new SlotInfo();
        SlotInfo.showInfoFrame();

    }
}
