// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.utils;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import java.util.Iterator;
import cheaters.get.banned.events.TickEndEvent;
import java.util.HashMap;

public class LocationUtils
{
    private static String prevIsland;
    private static Island island;
    public static HashMap<String, Island> islandLookup;
    
    @SubscribeEvent
    public void onTick(final TickEndEvent event) {
        if (Utils.inSkyBlock) {
            for (String name : TabUtils.getTabList()) {
                if (!name.equals(LocationUtils.prevIsland) && name.contains("Area:")) {
                    LocationUtils.prevIsland = name;
                    name = Utils.removeFormatting(name).replace("Area: ", "");
                    LocationUtils.island = LocationUtils.islandLookup.get(name);
                }
            }
        }
        else {
            LocationUtils.island = null;
        }
    }
    
    public static Island getIsland() {
        return LocationUtils.island;
    }
    
    public static boolean onIsland(final Island island) {
        return getIsland() != null && getIsland().equals(island);
    }
    
    static {
        LocationUtils.prevIsland = null;
        LocationUtils.island = null;
        LocationUtils.islandLookup = new HashMap<String, Island>() {
            {
                for (final Island island : Island.values()) {
                    this.put(island.getName(), island);
                }
            }
        };
    }
    
    public enum Island
    {
        PRIVATE_ISLAND("Private Island"), 
        HUB("Hub"), 
        SPIDERS_DEN("Spider's Den"), 
        BLAZING_FORTRESS("Blazing Fortress"), 
        THE_END("The End"), 
        THE_PARK("The Park"), 
        GOLD_MINE("Gold Mine"), 
        DEEP_CAVERNS("Deep Caverns"), 
        DWARVEN_MINES("Dwarven Mines"), 
        FARMING_ISLANDS("The Farming Islands"), 
        DUNGEON_HUB("Dungeon Hub"), 
        CRYSTAL_HOLLOWS("Crystal Hollows"), 
        JERRYS_WORKSHOP("Jerry's Workshop");
        
        private String tabName;
        
        private Island(final String tabName) {
            this.tabName = tabName;
        }
        
        public String getName() {
            return this.tabName;
        }
    }
}
