// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.features;

import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import java.util.Iterator;
import java.util.List;
import cheaters.get.banned.stats.MiscStats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Slot;
import cheaters.get.banned.Shady;
import net.minecraft.client.gui.inventory.GuiChest;
import cheaters.get.banned.gui.config.Config;
import cheaters.get.banned.utils.Utils;
import cheaters.get.banned.events.TickEndEvent;

public class AutoSell
{
    private boolean inTradeMenu;
    private int tickCount;
    private static final String[] dungeonJunk;
    
    public AutoSell() {
        this.inTradeMenu = false;
        this.tickCount = 0;
    }
    
    @SubscribeEvent
    public void onTick(final TickEndEvent event) {
        if (this.tickCount % 3 == 0 && Utils.inSkyBlock && Config.autoSell && this.inTradeMenu && Shady.mc.field_71462_r instanceof GuiChest) {
            final List<Slot> chestInventory = (List<Slot>)((GuiChest)Shady.mc.field_71462_r).field_147002_h.field_75151_b;
            if (chestInventory.get(49).func_75211_c() != null && chestInventory.get(49).func_75211_c().func_77973_b() != Item.func_150898_a(Blocks.field_180401_cv)) {
                for (final Slot slot : Shady.mc.field_71439_g.field_71069_bz.field_75151_b) {
                    if (this.shouldSell(slot.func_75211_c())) {
                        Shady.mc.field_71442_b.func_78753_a(Shady.mc.field_71439_g.field_71070_bA.field_75152_c, 45 + slot.field_75222_d, 2, 0, (EntityPlayer)Shady.mc.field_71439_g);
                        MiscStats.add(MiscStats.Metric.ITEMS_SOLD);
                        break;
                    }
                }
            }
        }
        ++this.tickCount;
    }
    
    @SubscribeEvent
    public void onBackgroundRender(final GuiScreenEvent.BackgroundDrawnEvent event) {
        final String chestName = Utils.getGuiName(event.gui);
        this.inTradeMenu = chestName.equals("Trades");
    }
    
    private boolean shouldSell(final ItemStack item) {
        if (item != null) {
            if (Config.autoSellSalvageable && AutoSalvage.shouldSalvage(item)) {
                return true;
            }
            if (Config.autoSellSuperboom && Utils.getSkyBlockID(item).equals("SUPERBOOM_TNT")) {
                return true;
            }
            if (Config.autoSellPotions && item.func_82833_r().contains("Potion") && (item.func_82833_r().contains("Speed") || item.func_82833_r().contains("Weakness"))) {
                return true;
            }
            if (Config.autoSellMinionDrops && (item.func_82833_r().contains("Enchanted Snow") || item.func_82833_r().contains("Enchanted Clay"))) {
                return true;
            }
            if (Config.autoSellDungeonsJunk) {
                for (final String name : AutoSell.dungeonJunk) {
                    if (item.func_82833_r().contains(name)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    static {
        dungeonJunk = new String[] { "Training Weight", "Health Potion VIII Splash Potion", "Healing Potion 8 Slash Potion", "Beating Heart", "Premium Flesh", "Mimic Fragment", "Enchanted Rotten Flesh", "Enchanted Bone", "Defuse Kit", "Enchanted Ice", "Optic Lense", "Tripwire Hook", "Button", "Carpet", "Lever", "Rune", "Journal Entry", "Sign" };
    }
}
