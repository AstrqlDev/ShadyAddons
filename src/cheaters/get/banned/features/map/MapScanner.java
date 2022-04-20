// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.features.map;

import org.apache.commons.lang3.StringUtils;
import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import javax.annotation.Nullable;
import cheaters.get.banned.features.map.elements.rooms.Room;
import cheaters.get.banned.features.map.elements.MapTile;
import cheaters.get.banned.features.map.elements.doors.DoorTile;
import cheaters.get.banned.features.map.elements.doors.DoorType;
import cheaters.get.banned.features.map.elements.rooms.RoomType;
import cheaters.get.banned.features.map.elements.rooms.Separator;
import cheaters.get.banned.features.map.elements.rooms.RoomTile;
import cheaters.get.banned.utils.Utils;
import cheaters.get.banned.Shady;

public class MapScanner
{
    public static final int xCorner = -200;
    public static final int zCorner = -200;
    public static final int halfRoom = 16;
    private static boolean isScanning;
    
    public static MapModel getScan() {
        final MapModel map = new MapModel();
        for (int col = 0; col <= 10; ++col) {
            for (int row = 0; row <= 10; ++row) {
                final int x = -185 + col * 16;
                final int z = -185 + row * 16;
                if (!Shady.mc.field_71441_e.func_72964_e(x >> 4, z >> 4).func_177410_o()) {
                    Utils.log("Chunk at x" + x + " z" + z + "is not loaded");
                    map.allLoaded = false;
                }
                if (!isColumnAir(x, z)) {
                    final boolean rowEven = row % 2 == 0;
                    final boolean colEven = col % 2 == 0;
                    if (rowEven && colEven) {
                        final RoomTile roomTile = getRoomTile(x, z);
                        map.elements[row][col] = roomTile;
                        if (roomTile != null && roomTile.room != null) {
                            if (!map.uniqueRooms.contains(roomTile.room)) {
                                final MapModel mapModel = map;
                                mapModel.totalSecrets += roomTile.room.secrets;
                            }
                            map.uniqueRooms.add(roomTile.room);
                        }
                        map.roomTiles.add(roomTile);
                    }
                    else if (!rowEven && !colEven) {
                        if (map.elements[row - 1][col - 1] instanceof RoomTile) {
                            map.elements[row][col] = Separator.GENERIC;
                        }
                    }
                    else if (isDoor(x, z)) {
                        map.elements[row][col] = getDoor(x, z);
                    }
                    else {
                        final MapTile tileToCheck = map.elements[rowEven ? row : (row - 1)][rowEven ? (col - 1) : col];
                        if (tileToCheck instanceof RoomTile) {
                            if (((RoomTile)tileToCheck).room.type == RoomType.ENTRANCE) {
                                map.elements[row][col] = new DoorTile(DoorType.ENTRANCE);
                            }
                            else {
                                map.elements[row][col] = Separator.GENERIC;
                            }
                        }
                    }
                }
            }
        }
        return map;
    }
    
    @Nullable
    private static RoomTile getRoomTile(final int x, final int z) {
        final int core = getCore(x, z);
        final Room room = MapController.rooms.get(core);
        if (room == null) {
            return null;
        }
        return new RoomTile(room);
    }
    
    private static boolean isDoor(final int x, final int z) {
        final boolean xPlus4 = isColumnAir(x + 4, z);
        final boolean xMinus4 = isColumnAir(x - 4, z);
        final boolean zPlus4 = isColumnAir(x, z + 4);
        final boolean zMinus4 = isColumnAir(x, z - 4);
        return (xPlus4 && xMinus4 && !zPlus4 && !zMinus4) || (!xPlus4 && !xMinus4 && zPlus4 && zMinus4);
    }
    
    private static DoorTile getDoor(final int x, final int z) {
        final IBlockState blockState = Shady.mc.field_71441_e.func_180495_p(new BlockPos(x, 69, z));
        final Block block = blockState.func_177230_c();
        DoorType type = null;
        if (block == Blocks.field_150402_ci) {
            type = DoorType.WITHER;
        }
        if (block == Blocks.field_150418_aU) {
            type = DoorType.ENTRANCE;
        }
        if (block == Blocks.field_150406_ce && Blocks.field_150406_ce.func_176201_c(blockState) == 14) {
            type = DoorType.BLOOD;
        }
        if (type == null) {
            type = DoorType.NORMAL;
        }
        return new DoorTile(type);
    }
    
    public static int getCore(final int x, final int z) {
        final ArrayList<Integer> blocks = new ArrayList<Integer>();
        for (int y = 140; y >= 12; --y) {
            final int id = Block.func_149682_b(Shady.mc.field_71441_e.func_180495_p(new BlockPos(x, y, z)).func_177230_c());
            if (id != 5 && id != 54) {
                blocks.add(id);
            }
        }
        return StringUtils.join(blocks.toArray()).hashCode();
    }
    
    private static boolean isColumnAir(final int x, final int z) {
        for (int y = 12; y < 140; ++y) {
            if (!Shady.mc.field_71441_e.func_175623_d(new BlockPos(x, y, z))) {
                return false;
            }
        }
        return true;
    }
    
    private static void setBlock(final Block block, final int x, final int z) {
        Shady.mc.field_71441_e.func_175656_a(new BlockPos(x, 255, z), block.func_176223_P());
    }
    
    static {
        MapScanner.isScanning = false;
    }
}
