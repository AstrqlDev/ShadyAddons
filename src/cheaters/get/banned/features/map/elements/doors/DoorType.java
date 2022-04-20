// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.features.map.elements.doors;

import cheaters.get.banned.features.map.elements.DungeonColors;
import java.awt.Color;

public enum DoorType
{
    WITHER(DungeonColors.WITHER_DOOR), 
    BLOOD(DungeonColors.RED), 
    ENTRANCE(DungeonColors.GREEN), 
    NORMAL(DungeonColors.BROWN);
    
    public final Color color;
    
    private DoorType(final Color color) {
        this.color = color;
    }
}
