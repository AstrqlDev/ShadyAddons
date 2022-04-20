// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.features.map;

import java.util.Collection;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class RoomLists
{
    public static final HashMap<String, String> shortNames;
    public static final ArrayList<String> slowRooms;
    
    static {
        shortNames = new HashMap<String, String>() {
            {
                this.put("Old Trap", "Old");
                this.put("New Trap", "New");
                this.put("Boulder", "Box");
                this.put("Creeper Beams", "Beams");
                this.put("Teleport Maze", "Maze");
                this.put("Ice Path", "S.Fish");
                this.put("Ice Fill", "Fill");
                this.put("Tic Tac Toe", "TTT");
                this.put("Water Board", "Water");
                this.put("Bomb Defuse", "Bomb");
                this.put("Three Weirdos", "3 Men");
                this.put("King Midas", "Midas");
                this.put("Shadow Assassin", "SA");
            }
        };
        slowRooms = new ArrayList<String>(Arrays.asList("Mines", "Spider", "Pit", "Crypt", "Wizard", "Cathedral"));
    }
}
