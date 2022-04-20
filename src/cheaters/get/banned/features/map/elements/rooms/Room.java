// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.features.map.elements.rooms;

public class Room
{
    public RoomType type;
    public String name;
    public int secrets;
    
    public Room(final RoomType type, final String name, final int secrets) {
        this.type = type;
        this.name = name;
        this.secrets = secrets;
    }
    
    @Override
    public String toString() {
        return this.name;
    }
}
