// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.features.map.elements.rooms;

import cheaters.get.banned.features.map.elements.DungeonColors;
import cheaters.get.banned.features.map.elements.MapTile;

public class Separator extends MapTile
{
    public static final Separator GENERIC;
    
    public Separator() {
        super(DungeonColors.BROWN);
    }
    
    @Override
    public String toString() {
        return "Room Separator";
    }
    
    static {
        GENERIC = new Separator();
    }
}
