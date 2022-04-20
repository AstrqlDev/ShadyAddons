// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.features.connectfoursolver;

import net.minecraft.client.gui.Gui;
import java.awt.Color;
import cheaters.get.banned.events.DrawSlotEvent;
import cheaters.get.banned.utils.RenderUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.client.event.GuiScreenEvent;
import java.util.Iterator;
import net.minecraft.item.Item;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.init.Items;
import net.minecraft.inventory.Slot;
import cheaters.get.banned.Shady;
import net.minecraft.client.gui.inventory.GuiChest;
import cheaters.get.banned.events.TickEndEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import cheaters.get.banned.stats.MiscStats;
import cheaters.get.banned.utils.Utils;
import cheaters.get.banned.gui.config.Config;
import net.minecraftforge.client.event.ClientChatReceivedEvent;

public class ConnectFourSolver
{
    private boolean calculating;
    private int solutionSlot;
    private boolean isOurTurn;
    private boolean inGame;
    private Thread thread;
    private static final int EMPTY = 0;
    private static final int COMPUTER = 1;
    private static final int OPPONENT = 2;
    
    public ConnectFourSolver() {
        this.calculating = false;
        this.solutionSlot = -1;
        this.isOurTurn = false;
        this.inGame = false;
        this.thread = null;
    }
    
    @SubscribeEvent
    public void onChat(final ClientChatReceivedEvent event) {
        if (Config.connectFourAI && Utils.inSkyBlock && event.type == 0) {
            final String message = event.message.func_150260_c();
            if (message.endsWith(" defeated you in Four in a Row!")) {
                MiscStats.add(MiscStats.Metric.CONNECT_FOUR_LOSSES);
            }
            else if (message.startsWith("You defeated ") && message.endsWith(" in Four in a Row!")) {
                MiscStats.add(MiscStats.Metric.CONNECT_FOUR_WINS);
            }
        }
    }
    
    @SubscribeEvent
    public void onTick(final TickEndEvent event) {
        if (Config.connectFourAI && Utils.inSkyBlock) {
            this.inGame = (Shady.mc.field_71462_r instanceof GuiChest && Utils.getInventoryName().startsWith("Four in a Row - "));
            if (this.inGame && Shady.mc.field_71439_g.field_71070_bA.field_75151_b.get(1) != null && Shady.mc.field_71439_g.field_71070_bA.field_75151_b.get(1).func_75211_c() != null) {
                this.isOurTurn = (Shady.mc.field_71439_g.field_71070_bA.field_75151_b.get(1).func_75211_c().func_77973_b() == Items.field_151160_bD);
            }
            if (!this.isOurTurn || !this.inGame) {
                this.solutionSlot = -1;
            }
            if (!this.inGame && this.thread != null && this.thread.isAlive()) {
                this.thread.stop();
            }
            if (this.isOurTurn && !this.calculating && this.inGame && this.solutionSlot == -1) {
                this.calculating = true;
                final ConnectFourAlgorithm.Board board;
                final ConnectFourAlgorithm algorithm;
                final int column;
                int row;
                (this.thread = new Thread(() -> {
                    board = this.serializeInventory(Shady.mc.field_71439_g.field_71070_bA);
                    algorithm = new ConnectFourAlgorithm(board);
                    column = algorithm.getAIMove();
                    algorithm.board.placeMove(column, 1);
                    row = 5;
                    while (row >= 0) {
                        if (board.board[row][column] == 0) {
                            this.solutionSlot = row * 9 + column + 1 + 9;
                            break;
                        }
                        else {
                            --row;
                        }
                    }
                    this.calculating = false;
                }, "ShadyAddons-ConnectFour")).start();
            }
        }
    }
    
    private ConnectFourAlgorithm.Board serializeInventory(final Container chest) {
        final ConnectFourAlgorithm.Board board = new ConnectFourAlgorithm.Board();
        for (final Slot slot : chest.field_75151_b) {
            if (slot.field_75222_d <= 53 && slot.func_75211_c() != null && slot.field_75222_d % 9 != 0) {
                if ((slot.field_75222_d + 1) % 9 == 0) {
                    continue;
                }
                final int row = slot.field_75222_d / 9;
                final int column = (slot.field_75222_d - 1) % 9;
                final Item item = slot.func_75211_c().func_77973_b();
                if (item == Items.field_151160_bD || item == Items.field_151159_an) {
                    board.board[row][column] = 0;
                }
                if (item != Item.func_150898_a(Blocks.field_150406_ce)) {
                    continue;
                }
                if (slot.func_75211_c().func_77952_i() == 1) {
                    board.board[row][column] = 2;
                }
                else {
                    board.board[row][column] = 1;
                }
            }
        }
        return board;
    }
    
    @SubscribeEvent
    public void onBackgroundRender(final GuiScreenEvent.DrawScreenEvent.Pre event) {
        if (Config.connectFourAI && Utils.inSkyBlock && this.inGame && this.calculating) {
            final int y = event.gui.field_146295_m / 2 - 105;
            final int x = event.gui.field_146294_l / 2 + 73;
            final int textureX = (int)(System.currentTimeMillis() / 10L % 20L * 8L);
            GlStateManager.func_179094_E();
            GlStateManager.func_179109_b(0.0f, 0.0f, 1000.0f);
            GlStateManager.func_179124_c(64.0f, 64.0f, 64.0f);
            RenderUtils.drawTexture(new ResourceLocation("shadyaddons:loader.png"), x, y, 8, 8, 160, 8, textureX, 0);
            GlStateManager.func_179121_F();
        }
    }
    
    @SubscribeEvent
    public void onDrawSlot(final DrawSlotEvent event) {
        if (Config.connectFourAI && Utils.inSkyBlock && this.inGame && this.solutionSlot == event.slot.field_75222_d) {
            final int x = event.slot.field_75223_e;
            final int y = event.slot.field_75221_f;
            Gui.func_73734_a(x, y, x + 16, y + 16, Color.GREEN.getRGB());
        }
    }
}
