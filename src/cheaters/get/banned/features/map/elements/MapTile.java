// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.features.map.elements;

import java.awt.Color;
import cheaters.get.banned.features.map.elements.rooms.RoomStatus;

public abstract class MapTile
{
    public int color;
    public RoomStatus status;
    
    public MapTile(final Color color) {
        this.status = RoomStatus.UNDISCOVERED;
        this.color = color.getRGB();
    }
}
