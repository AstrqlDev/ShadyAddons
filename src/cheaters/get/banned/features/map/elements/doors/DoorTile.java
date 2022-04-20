// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.features.map.elements.doors;

import cheaters.get.banned.features.map.elements.MapTile;

public class DoorTile extends MapTile
{
    public DoorType type;
    
    public DoorTile(final DoorType type) {
        super(type.color);
        this.type = type;
    }
    
    @Override
    public String toString() {
        return "Door (" + this.type.name() + ")";
    }
}
