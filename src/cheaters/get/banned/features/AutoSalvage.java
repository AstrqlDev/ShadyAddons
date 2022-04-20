// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.features;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.item.ItemStack;
import org.lwjgl.input.Mouse;
import net.minecraft.client.gui.Gui;
import java.awt.Color;
import cheaters.get.banned.events.DrawSlotEvent;
import net.minecraft.client.gui.ScaledResolution;
import cheaters.get.banned.utils.Utils;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import java.util.List;
import java.util.Collection;
import java.util.ArrayList;
import cheaters.get.banned.stats.MiscStats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Slot;
import cheaters.get.banned.Shady;
import net.minecraft.client.gui.inventory.GuiChest;
import cheaters.get.banned.gui.config.Config;
import cheaters.get.banned.events.TickEndEvent;
import cheaters.get.banned.gui.ClearButton;

public class AutoSalvage
{
    private static final ClearButton button;
    private boolean inSalvageGui;
    private boolean salvaging;
    private int tickCount;
    
    public AutoSalvage() {
        this.inSalvageGui = false;
        this.salvaging = false;
        this.tickCount = 0;
    }
    
    @SubscribeEvent
    public void onTick(final TickEndEvent event) {
        if (this.tickCount % 5 == 0 && Config.autoSalvage && this.inSalvageGui && this.salvaging && Shady.mc.field_71462_r instanceof GuiChest) {
            final List<Slot> chestInventory = (List<Slot>)((GuiChest)Shady.mc.field_71462_r).field_147002_h.field_75151_b;
            if (chestInventory != null && chestInventory.get(31).func_75211_c() != null && chestInventory.get(31).func_75211_c().func_77973_b() == Items.field_151144_bL) {
                if (chestInventory.get(22) != null && (chestInventory.get(22).func_75211_c() != null & chestInventory.get(22).func_75211_c().func_77973_b() == Item.func_150898_a(Blocks.field_150406_ce))) {
                    Shady.mc.field_71442_b.func_78753_a(Shady.mc.field_71439_g.field_71070_bA.field_75152_c, 22, 2, 0, (EntityPlayer)Shady.mc.field_71439_g);
                }
                if (chestInventory.get(13) != null && chestInventory.get(13).func_75211_c() != null && chestInventory.get(22) != null && chestInventory.get(22).func_75211_c() != null && chestInventory.get(22).func_75211_c().func_77973_b() == Item.func_150898_a(Blocks.field_150467_bQ)) {
                    Shady.mc.field_71442_b.func_78753_a(Shady.mc.field_71439_g.field_71070_bA.field_75152_c, 22, 2, 0, (EntityPlayer)Shady.mc.field_71439_g);
                    MiscStats.add(MiscStats.Metric.ITEMS_SALVAGED);
                }
            }
            else if (chestInventory.get(13) != null && chestInventory.get(13).func_75211_c() == null) {
                final ArrayList<Slot> itemsToSalvage = new ArrayList<Slot>(Shady.mc.field_71439_g.field_71069_bz.field_75151_b);
                itemsToSalvage.removeIf(slot -> !shouldSalvage(slot.func_75211_c()));
                if (itemsToSalvage.isEmpty()) {
                    this.salvaging = false;
                }
                else {
                    Shady.mc.field_71442_b.func_78753_a(Shady.mc.field_71439_g.field_71070_bA.field_75152_c, 45 + itemsToSalvage.get(0).field_75222_d, 0, 1, (EntityPlayer)Shady.mc.field_71439_g);
                }
            }
        }
        ++this.tickCount;
    }
    
    @SubscribeEvent
    public void onBackgroundRender(final GuiScreenEvent.BackgroundDrawnEvent event) {
        final String chestName = Utils.getGuiName(event.gui);
        this.inSalvageGui = chestName.equals("Salvage Dungeon Item");
        if (Config.autoSalvage && this.inSalvageGui) {
            if (Config.automaticallyStartSalvaging) {
                this.salvaging = true;
            }
            else {
                final ScaledResolution scaledResolution = new ScaledResolution(Shady.mc);
                AutoSalvage.button.field_146128_h = (scaledResolution.func_78326_a() - AutoSalvage.button.field_146120_f) / 2;
                AutoSalvage.button.field_146129_i = scaledResolution.func_78328_b() / 2 - 145;
                AutoSalvage.button.field_146126_j = (this.salvaging ? "§cStop" : "§aStart");
                AutoSalvage.button.func_146112_a(event.gui.field_146297_k, event.getMouseX(), event.getMouseY());
            }
        }
        else {
            this.salvaging = false;
        }
    }
    
    @SubscribeEvent
    public void onDrawSlot(final DrawSlotEvent event) {
        if (Config.autoSalvage && this.inSalvageGui && AutoSalvage.button.func_146115_a() && shouldSalvage(event.slot.func_75211_c())) {
            final int x = event.slot.field_75223_e;
            final int y = event.slot.field_75221_f;
            Gui.func_73734_a(x, y, x + 16, y + 16, Utils.addAlpha(Color.RED, 128).getRGB());
        }
    }
    
    @SubscribeEvent
    public void onMouseInput(final GuiScreenEvent.MouseInputEvent.Pre event) {
        if (Utils.inSkyBlock && Config.autoSalvage && this.inSalvageGui && AutoSalvage.button.func_146115_a() && !Config.automaticallyStartSalvaging && Mouse.isButtonDown(0)) {
            this.salvaging = !this.salvaging;
        }
    }
    
    public static boolean shouldSalvage(final ItemStack item) {
        if (item == null) {
            return false;
        }
        final NBTTagCompound attributes = item.func_179543_a("ExtraAttributes", false);
        return attributes != null && attributes.func_74764_b("baseStatBoostPercentage") && !attributes.func_74764_b("dungeon_item_level") && !Utils.getSkyBlockID(item).equals("ICE_SPRAY_WAND");
    }
    
    static {
        button = new ClearButton(0, 0, 0, 71, 20, "§aStart");
    }
}
