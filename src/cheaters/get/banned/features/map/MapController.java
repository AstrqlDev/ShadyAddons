// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.features.map;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.item.ItemMap;
import cheaters.get.banned.features.map.elements.MapTile;
import net.minecraft.world.storage.MapData;
import cheaters.get.banned.features.map.elements.rooms.RoomTile;
import cheaters.get.banned.features.map.elements.rooms.RoomStatus;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import cheaters.get.banned.events.TickEndEvent;
import java.util.Iterator;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.InputStream;
import cheaters.get.banned.features.map.elements.rooms.RoomType;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import cheaters.get.banned.Shady;
import net.minecraft.util.ResourceLocation;
import java.util.Arrays;
import cheaters.get.banned.utils.DungeonUtils;
import cheaters.get.banned.utils.Utils;
import java.util.HashSet;
import cheaters.get.banned.features.map.elements.rooms.Room;
import java.util.HashMap;

public class MapController
{
    public static HashMap<Integer, Room> rooms;
    public static HashSet<Room> uniqueRooms;
    public static MapModel scannedMap;
    public static boolean isScanning;
    private static long lastScan;
    public static int[] startCorner;
    public static float multiplier;
    public static int roomSize;
    
    public static void calibrate() {
        Utils.log("Beginning calibration");
        switch (DungeonUtils.floor) {
            case FLOOR_1: {
                MapController.startCorner[0] = 22;
                MapController.startCorner[1] = 11;
                break;
            }
            case FLOOR_2:
            case FLOOR_3: {
                MapController.startCorner[0] = 11;
                MapController.startCorner[1] = 11;
                break;
            }
            case FLOOR_4: {
                if (MapController.scannedMap.roomTiles.size() > 25) {
                    MapController.startCorner[0] = 5;
                    MapController.startCorner[1] = 16;
                    break;
                }
                break;
            }
            default: {
                if (MapController.scannedMap.roomTiles.size() == 30) {
                    MapController.startCorner[0] = 16;
                    MapController.startCorner[1] = 5;
                    break;
                }
                if (MapController.scannedMap.roomTiles.size() == 25) {
                    MapController.startCorner[0] = 11;
                    MapController.startCorner[1] = 11;
                    break;
                }
                MapController.startCorner[0] = 5;
                MapController.startCorner[1] = 5;
                break;
            }
        }
        if (DungeonUtils.inFloor(DungeonUtils.Floor.FLOOR_1, DungeonUtils.Floor.FLOOR_2, DungeonUtils.Floor.FLOOR_3, DungeonUtils.Floor.MASTER_1, DungeonUtils.Floor.MASTER_2, DungeonUtils.Floor.MASTER_3) || MapController.scannedMap.roomTiles.size() == 24) {
            MapController.roomSize = 18;
        }
        else {
            MapController.roomSize = 16;
        }
        MapController.multiplier = 32.0f / (MapController.roomSize + 4.0f);
        Utils.log("Calibration finished");
        Utils.log("  startCorner: " + Arrays.toString(MapController.startCorner));
        Utils.log("  roomSize: " + MapController.roomSize);
        Utils.log("  multiplier: " + MapController.multiplier);
    }
    
    public static void loadRooms() {
        try {
            final ResourceLocation roomsResource = new ResourceLocation("shadyaddons:dungeonscanner/new-rooms.json");
            final InputStream inputStream = Shady.mc.func_110442_L().func_110536_a(roomsResource).func_110527_b();
            final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            final JsonObject json = new JsonParser().parse((Reader)reader).getAsJsonObject();
            final JsonArray roomsArray = json.getAsJsonArray("rooms");
            for (final JsonElement roomElement : roomsArray) {
                final JsonObject roomObject = roomElement.getAsJsonObject();
                final String type = roomObject.get("type").getAsString().toUpperCase();
                final String name = roomObject.get("name").getAsString();
                final int secrets = roomObject.get("secrets").getAsInt();
                final JsonArray cores = roomObject.get("cores").getAsJsonArray();
                final Room room = new Room(RoomType.valueOf(type), name, secrets);
                for (final JsonElement core : cores) {
                    final int coreNumber = core.getAsInt();
                    MapController.rooms.put(coreNumber, room);
                }
                MapController.uniqueRooms.add(room);
            }
        }
        catch (Exception exception) {
            System.out.println("Error loading dungeon rooms");
            exception.printStackTrace();
        }
    }
    
    public static void printRooms() {
        for (final Room roomTile : MapController.uniqueRooms) {
            System.out.println("Name: " + roomTile.name);
            System.out.println("Secrets: " + roomTile.secrets);
            System.out.println("Type: " + roomTile.type.name());
            System.out.println();
        }
    }
    
    public static void scan() {
        Utils.log("Beginning scan");
        try {
            MapController.lastScan = System.currentTimeMillis();
            new Thread(() -> {
                MapController.isScanning = true;
                MapController.scannedMap = MapScanner.getScan();
                if (MapController.scannedMap.allLoaded) {
                    calibrate();
                }
                MapController.isScanning = false;
            }, "ShadyAddons-DungeonScan").start();
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }
    
    private static boolean shouldScan() {
        return Utils.inDungeon && !MapController.isScanning && System.currentTimeMillis() - MapController.lastScan >= 250L && DungeonUtils.floor != null && (MapController.scannedMap == null || !MapController.scannedMap.allLoaded);
    }
    
    @SubscribeEvent
    public void onTick(final TickEndEvent event) {
        if (shouldScan()) {
            scan();
        }
        if (!Utils.inDungeon) {
            MapController.scannedMap = null;
        }
        if (event.every(10) && MapController.scannedMap != null && MapController.scannedMap.allLoaded) {
            new Thread(MapController::updateRoomStatuses).start();
        }
    }
    
    public static void updateRoomStatuses() {
        final MapData mapData = getMapData();
        if (mapData == null) {
            return;
        }
        final byte[] mapColors = mapData.field_76198_e;
        Utils.log("Updating room statuses");
        final int startX = MapController.startCorner[0] + Math.floorDiv(MapController.roomSize, 2);
        final int startZ = MapController.startCorner[1] + Math.floorDiv(MapController.roomSize, 2);
        final int increment = Math.floorDiv(MapController.roomSize, 2) + 2;
        for (int x = 0; x <= 10; ++x) {
            for (int y = 0; y <= 10; ++y) {
                final int mapX = startX + x * increment;
                final int mapY = startZ + y * increment;
                if (mapX < 128) {
                    if (mapY < 128) {
                        final MapTile tile = MapController.scannedMap.elements[y][x];
                        if (tile != null) {
                            final int color = Byte.toUnsignedInt(mapColors[(mapY << 7) + mapX]);
                            switch (color) {
                                case 0:
                                case 85:
                                case 119: {
                                    tile.status = RoomStatus.UNDISCOVERED;
                                    break;
                                }
                                case 18: {
                                    if (!(tile instanceof RoomTile)) {
                                        tile.status = RoomStatus.DISCOVERED;
                                        break;
                                    }
                                    if (((RoomTile)tile).room.type == RoomType.BLOOD) {
                                        tile.status = RoomStatus.DISCOVERED;
                                        break;
                                    }
                                    if (((RoomTile)tile).room.type == RoomType.PUZZLE) {
                                        tile.status = RoomStatus.FAILED;
                                        break;
                                    }
                                    break;
                                }
                                case 30: {
                                    if (!(tile instanceof RoomTile)) {
                                        break;
                                    }
                                    if (((RoomTile)tile).room.type == RoomType.ENTRANCE) {
                                        tile.status = RoomStatus.DISCOVERED;
                                        break;
                                    }
                                    tile.status = RoomStatus.GREEN;
                                    break;
                                }
                                case 34: {
                                    tile.status = RoomStatus.CLEARED;
                                    break;
                                }
                                default: {
                                    tile.status = RoomStatus.DISCOVERED;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    private static MapData getMapData() {
        if (Shady.mc.field_71439_g == null) {
            return null;
        }
        if (Shady.mc.field_71439_g.field_71071_by == null) {
            return null;
        }
        final ItemStack handheldMap = Shady.mc.field_71439_g.field_71071_by.func_70301_a(8);
        if (handheldMap == null || !(handheldMap.func_77973_b() instanceof ItemMap) || !handheldMap.func_82833_r().contains("Magical Map")) {
            return null;
        }
        return ((ItemMap)handheldMap.func_77973_b()).func_77873_a(handheldMap, (World)Shady.mc.field_71441_e);
    }
    
    static {
        MapController.rooms = new HashMap<Integer, Room>();
        MapController.uniqueRooms = new HashSet<Room>();
        MapController.isScanning = false;
        MapController.lastScan = 0L;
        MapController.startCorner = new int[] { 5, 5 };
        MapController.multiplier = 1.6f;
        MapController.roomSize = 16;
    }
}
