// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.features.map.elements.rooms;

import cheaters.get.banned.features.map.elements.MapTile;

public class RoomTile extends MapTile
{
    public Room room;
    
    public RoomTile(final Room room) {
        super(room.type.color);
        this.room = room;
    }
}
