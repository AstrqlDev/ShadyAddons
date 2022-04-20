// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.stats;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import cheaters.get.banned.events.ShutdownEvent;
import com.google.gson.Gson;
import java.util.HashMap;

public class MiscStats
{
    private static HashMap<Metric, Integer> data;
    public static int minutesSinceLastSend;
    
    private static String toJson() {
        return new Gson().toJson((Object)MiscStats.data);
    }
    
    public static void add(final Metric metric) {
        final Integer oldValue = MiscStats.data.get(metric);
        MiscStats.data.put(metric, ((oldValue == null) ? 0 : oldValue) + 1);
    }
    
    public static void send() {
        Analytics.collect("stats", toJson());
        MiscStats.data.clear();
    }
    
    @SubscribeEvent
    public void onShutdown(final ShutdownEvent event) {
        send();
    }
    
    static {
        MiscStats.data = new HashMap<Metric, Integer>();
        MiscStats.minutesSinceLastSend = 0;
    }
    
    public enum Metric
    {
        BLOCKS_GHOSTED, 
        CRYSTALS_REACHED, 
        CONNECT_FOUR_WINS, 
        CONNECT_FOUR_LOSSES, 
        CHESTS_CLOSED, 
        ITEMS_SOLD, 
        ITEMS_SALVAGED, 
        MATH_PROBLEMS_SOLVED, 
        ITEMS_MACROED, 
        WARDROBES_OPENED, 
        COMMAND_PALETTE_OPENS;
    }
}
