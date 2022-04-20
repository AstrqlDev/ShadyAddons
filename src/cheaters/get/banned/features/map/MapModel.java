// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.features.map;

import cheaters.get.banned.features.map.elements.rooms.Room;
import java.util.HashSet;
import cheaters.get.banned.features.map.elements.rooms.RoomTile;
import java.util.ArrayList;
import cheaters.get.banned.features.map.elements.MapTile;

public class MapModel
{
    public MapTile[][] elements;
    public ArrayList<RoomTile> roomTiles;
    public HashSet<Room> uniqueRooms;
    public boolean allLoaded;
    public int totalSecrets;
    
    public MapModel() {
        this.elements = new MapTile[11][11];
        this.roomTiles = new ArrayList<RoomTile>();
        this.uniqueRooms = new HashSet<Room>();
        this.allLoaded = true;
        this.totalSecrets = 0;
    }
}
