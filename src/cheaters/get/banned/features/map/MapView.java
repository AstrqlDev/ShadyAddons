// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.features.map;

import cheaters.get.banned.features.map.elements.rooms.RoomType;
import cheaters.get.banned.utils.RenderUtils;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import java.util.Iterator;
import cheaters.get.banned.features.map.elements.MapTile;
import cheaters.get.banned.Shady;
import net.minecraft.entity.player.EntityPlayer;
import cheaters.get.banned.features.map.elements.rooms.Separator;
import cheaters.get.banned.features.map.elements.doors.DoorTile;
import cheaters.get.banned.features.map.elements.rooms.RoomTile;
import cheaters.get.banned.utils.FontUtils;
import net.minecraft.client.gui.Gui;
import java.awt.Color;
import net.minecraft.client.renderer.GlStateManager;
import cheaters.get.banned.utils.DungeonUtils;
import cheaters.get.banned.gui.config.Config;
import cheaters.get.banned.utils.Utils;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraft.util.ResourceLocation;
import java.util.ArrayList;

public class MapView
{
    public static int tileSize;
    public static final int maxMapPx;
    public static final int maxMapBlocks = 197;
    private static final int borderSize = 3;
    private static ArrayList<String> roomNamesDrawn;
    private static final ResourceLocation icon_cross;
    private static final ResourceLocation icon_whiteCheck;
    private static final ResourceLocation icon_check;
    
    @SubscribeEvent
    public void onRenderOverlay(final RenderGameOverlayEvent event) {
        if (event.type != RenderGameOverlayEvent.ElementType.HOTBAR) {
            return;
        }
        if (MapController.scannedMap == null || !Utils.inDungeon || !Config.dungeonMap || DungeonUtils.inBoss) {
            return;
        }
        final float scale = Config.mapScale / 100.0f;
        GlStateManager.func_179094_E();
        GlStateManager.func_179152_a(scale, scale, scale);
        GlStateManager.func_179109_b((float)(Config.mapXOffset + ((Config.mapBorder > 0) ? 3 : 0)), (float)(Config.mapYOffset + ((Config.mapBorder > 0) ? 3 : 0)), 0.0f);
        Gui.func_73734_a(0, 0, MapView.maxMapPx + MapView.tileSize * 2, MapView.maxMapPx + MapView.tileSize * 2 + (Config.showDungeonInfo ? 30 : 0), Utils.addAlphaPct(Color.BLACK, Config.mapBackgroundOpacity / 100.0f).getRGB());
        if (Config.mapBorder > 0) {
            final int borderColor = (new int[] { 3, getChroma(), Color.BLACK.getRGB(), Color.WHITE.getRGB() })[Config.mapBorder];
            final int mapWidth = MapView.maxMapPx + MapView.tileSize * 2;
            final int mapHeight = mapWidth + (Config.showDungeonInfo ? 30 : 0);
            Gui.func_73734_a(-3, -3, mapWidth + 3, 0, borderColor);
            Gui.func_73734_a(-3, -3, 0, mapHeight + 3, borderColor);
            Gui.func_73734_a(-3, mapHeight, mapWidth + 3, mapHeight + 3, borderColor);
            Gui.func_73734_a(mapWidth, -3, mapWidth + 3, mapHeight + 3, borderColor);
        }
        if (Config.showDungeonInfo) {
            FontUtils.drawCenteredString(String.format("Secrets: §a%d§7/%d §f   Crypts: §a%d\n§fDeaths: §%c%d §f   Score: §%c%d", DungeonUtils.secretsFound, MapController.scannedMap.totalSecrets, DungeonUtils.cryptsFound, (DungeonUtils.deaths > 4) ? 'c' : 'a', DungeonUtils.deaths, getColorCode(DungeonUtils.score, 269, 300), DungeonUtils.score), MapView.maxMapPx / 2 + 10, MapView.maxMapPx + 10 + 15);
        }
        GlStateManager.func_179141_d();
        GlStateManager.func_179109_b((float)MapView.tileSize, (float)MapView.tileSize, 0.0f);
        final MapTile[][] elements = MapController.scannedMap.elements;
        for (int rowNum = 0; rowNum < elements.length; ++rowNum) {
            final MapTile[] row = elements[rowNum];
            for (int colNum = 0; colNum < row.length; ++colNum) {
                final MapTile tile = row[colNum];
                if (tile != null) {
                    final int x = (int)(Math.ceil(colNum / 2.0f) * MapView.tileSize * 3.0 + Math.floor(colNum / 2.0f) * MapView.tileSize);
                    final int y = (int)(Math.ceil(rowNum / 2.0f) * MapView.tileSize * 3.0 + Math.floor(rowNum / 2.0f) * MapView.tileSize);
                    final boolean colEven = colNum % 2 == 0;
                    final boolean rowEven = rowNum % 2 == 0;
                    if (colEven && rowEven) {
                        Gui.func_73734_a(x, y, x + MapView.tileSize * 3, y + MapView.tileSize * 3, tile.color);
                        if (!drawCheckmark((RoomTile)tile, x, y)) {
                            drawRoomName((RoomTile)tile, x, y);
                        }
                    }
                    else if (tile instanceof DoorTile) {
                        if (colEven) {
                            Gui.func_73734_a(x + MapView.tileSize, y, x + MapView.tileSize * 2, y + MapView.tileSize, tile.color);
                        }
                        else {
                            Gui.func_73734_a(x, y + MapView.tileSize, x + MapView.tileSize, y + MapView.tileSize * 2, tile.color);
                        }
                    }
                    else if (tile instanceof Separator) {
                        if (colEven) {
                            Gui.func_73734_a(x, y, x + MapView.tileSize * 3, y + MapView.tileSize, tile.color);
                        }
                        else if (rowEven) {
                            Gui.func_73734_a(x, y, x + MapView.tileSize, y + MapView.tileSize * 3, tile.color);
                        }
                        else {
                            Gui.func_73734_a(x, y, x + MapView.tileSize, y + MapView.tileSize, tile.color);
                        }
                    }
                }
            }
        }
        if (Config.showMapPlayerHeads > 0) {
            final int headSize = 14;
            for (final EntityPlayer teammate : DungeonUtils.teammates) {
                if (Config.showMapPlayerHeads != 2 || teammate == Shady.mc.field_71439_g) {
                    if (teammate.field_70128_L) {
                        continue;
                    }
                    final int playerX = (int)((teammate.func_180425_c().func_177958_n() + 200) / 197.0f * MapView.maxMapPx);
                    final int playerZ = (int)((teammate.func_180425_c().func_177952_p() + 200) / 197.0f * MapView.maxMapPx);
                    final int playerRotation = (int)(teammate.func_70079_am() - 180.0f);
                    drawPlayerIcon(teammate, headSize, playerX - headSize / 2, playerZ - headSize / 2, playerRotation);
                }
            }
        }
        GlStateManager.func_179109_b((float)(-MapView.tileSize), (float)(-MapView.tileSize), 0.0f);
        GlStateManager.func_179109_b((float)(-Config.mapXOffset - ((Config.mapBorder > 0) ? 3 : 0)), (float)(-Config.mapYOffset - ((Config.mapBorder > 0) ? 3 : 0)), 0.0f);
        GlStateManager.func_179121_F();
        MapView.roomNamesDrawn.clear();
    }
    
    private static void drawPlayerIcon(final EntityPlayer player, final int size, final int x, final int y, final int angle) {
        GlStateManager.func_179094_E();
        GlStateManager.func_179109_b(x + size / 2.0f, y + size / 2.0f, 0.0f);
        GlStateManager.func_179114_b((float)angle, 0.0f, 0.0f, 1.0f);
        GlStateManager.func_179109_b(-x - size / 2.0f, -y - size / 2.0f, 0.0f);
        Gui.func_73734_a(x, y, x + size, y + size, Color.BLACK.getRGB());
        GlStateManager.func_179124_c(255.0f, 255.0f, 255.0f);
        GlStateManager.func_179109_b(0.0f, 0.0f, 200.0f);
        RenderUtils.drawPlayerIcon(player, size - 2, x + 1, y + 1);
        GlStateManager.func_179109_b(0.0f, 0.0f, -200.0f);
        GlStateManager.func_179121_F();
    }
    
    private static boolean drawCheckmark(final RoomTile room, final int x, final int y) {
        ResourceLocation resourceLocation = null;
        switch (room.status) {
            case FAILED: {
                resourceLocation = MapView.icon_cross;
                break;
            }
            case CLEARED: {
                resourceLocation = MapView.icon_whiteCheck;
                break;
            }
            case GREEN: {
                resourceLocation = MapView.icon_check;
                break;
            }
            default: {
                return false;
            }
        }
        RenderUtils.drawTexture(resourceLocation, x + (int)(MapView.tileSize * 0.75), y + (int)(MapView.tileSize * 0.75), (int)(MapView.tileSize * 1.5), (int)(MapView.tileSize * 1.5));
        return true;
    }
    
    private static void drawRoomName(final RoomTile roomTile, final int x, final int y) {
        if (Config.showRoomNames == 0) {
            return;
        }
        String name = null;
        if (Config.showRoomNames == 1) {
            if (roomTile.room.type == RoomType.YELLOW || roomTile.room.type == RoomType.PUZZLE || roomTile.room.type == RoomType.TRAP) {
                name = RoomLists.shortNames.get(roomTile.room.name);
                if (name == null) {
                    name = roomTile.room.name.replace(" ", "\n");
                }
            }
        }
        else if (Config.showRoomNames == 2) {
            if (MapView.roomNamesDrawn.contains(roomTile.room.name)) {
                return;
            }
            name = roomTile.room.name.replace(" ", "\n");
            MapView.roomNamesDrawn.add(roomTile.room.name);
        }
        if (name == null) {
            return;
        }
        GlStateManager.func_179109_b(0.0f, 0.0f, 100.0f);
        FontUtils.drawCenteredString(name, (int)(x + MapView.tileSize * 1.5), (int)(y + MapView.tileSize * 1.5));
        GlStateManager.func_179109_b(0.0f, 0.0f, -100.0f);
    }
    
    private static char getColorCode(final int value, final int orangeAfter, final int greenAfter) {
        if (value < orangeAfter) {
            return 'c';
        }
        if (value < greenAfter) {
            return '6';
        }
        return 'a';
    }
    
    private static int getChroma() {
        final float hue = System.currentTimeMillis() % 3000L / 3000.0f;
        return Color.getHSBColor(hue, 0.75f, 1.0f).getRGB();
    }
    
    static {
        MapView.tileSize = 8;
        maxMapPx = MapView.tileSize * 23;
        MapView.roomNamesDrawn = new ArrayList<String>();
        icon_cross = new ResourceLocation("shadyaddons:dungeonscanner/cross.png");
        icon_whiteCheck = new ResourceLocation("shadyaddons:dungeonscanner/white_check.png");
        icon_check = new ResourceLocation("shadyaddons:dungeonscanner/check.png");
    }
}
