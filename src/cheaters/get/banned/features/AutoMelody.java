// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.features;

import java.util.Iterator;
import java.util.Collection;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.inventory.Slot;
import cheaters.get.banned.Shady;
import cheaters.get.banned.events.TickEndEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import cheaters.get.banned.gui.config.Config;
import cheaters.get.banned.utils.Utils;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraft.item.Item;
import java.util.ArrayList;

public class AutoMelody
{
    private boolean inHarp;
    private ArrayList<Item> lastInventory;
    
    public AutoMelody() {
        this.inHarp = false;
        this.lastInventory = new ArrayList<Item>();
    }
    
    @SubscribeEvent
    public void onGuiOpen(final GuiOpenEvent event) {
        if (event.gui instanceof GuiChest && Utils.inSkyBlock && Config.autoMelody && Utils.getGuiName(event.gui).startsWith("Harp -")) {
            this.lastInventory.clear();
            this.inHarp = true;
        }
    }
    
    @SubscribeEvent
    public void onTick(final TickEndEvent event) {
        if (!this.inHarp || !Config.autoMelody || Shady.mc.field_71439_g == null) {
            return;
        }
        final String inventoryName = Utils.getInventoryName();
        if (inventoryName == null || !inventoryName.startsWith("Harp -")) {
            this.inHarp = false;
        }
        final ArrayList<Item> thisInventory = new ArrayList<Item>();
        for (final Slot slot : Shady.mc.field_71439_g.field_71070_bA.field_75151_b) {
            if (slot.func_75211_c() != null) {
                thisInventory.add(slot.func_75211_c().func_77973_b());
            }
        }
        if (!this.lastInventory.toString().equals(thisInventory.toString())) {
            for (final Slot slot : Shady.mc.field_71439_g.field_71070_bA.field_75151_b) {
                if (slot.func_75211_c() != null && slot.func_75211_c().func_77973_b() instanceof ItemBlock && ((ItemBlock)slot.func_75211_c().func_77973_b()).func_179223_d() == Blocks.field_150371_ca) {
                    Shady.mc.field_71442_b.func_78753_a(Shady.mc.field_71439_g.field_71070_bA.field_75152_c, slot.field_75222_d, 2, 0, (EntityPlayer)Shady.mc.field_71439_g);
                    break;
                }
            }
        }
        this.lastInventory.clear();
        this.lastInventory.addAll(thisInventory);
    }
}
