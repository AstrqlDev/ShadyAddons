// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.features;

import cheaters.get.banned.events.ClickEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.util.MovingObjectPosition;
import cheaters.get.banned.stats.MiscStats;
import net.minecraft.init.Blocks;
import cheaters.get.banned.utils.Utils;
import cheaters.get.banned.Shady;
import cheaters.get.banned.gui.config.Config;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import cheaters.get.banned.utils.KeybindUtils;

public class GhostBlocks
{
    public GhostBlocks() {
        KeybindUtils.register("Create Ghost Block", 34);
    }
    
    @SubscribeEvent
    public void onRenderWorld(final RenderWorldLastEvent event) {
        if (Config.ghostBlockKeybind && KeybindUtils.get("Create Ghost Block").func_151470_d()) {
            final MovingObjectPosition object = Shady.mc.field_71439_g.func_174822_a((double)Shady.mc.field_71442_b.func_78757_d(), 1.0f);
            if (object != null && object.func_178782_a() != null) {
                final Block lookingAtblock = Shady.mc.field_71441_e.func_180495_p(object.func_178782_a()).func_177230_c();
                if (!Utils.isInteractable(lookingAtblock) && lookingAtblock != Blocks.field_150350_a) {
                    Shady.mc.field_71441_e.func_175698_g(object.func_178782_a());
                    MiscStats.add(MiscStats.Metric.BLOCKS_GHOSTED);
                }
            }
        }
    }
    
    @SubscribeEvent
    public void onRightClick(final ClickEvent.Right event) {
        if (Utils.inSkyBlock && Config.stonkGhostBlock && Shady.mc.field_71476_x != null && Shady.mc.field_71476_x.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK && !Utils.isInteractable(Shady.mc.field_71441_e.func_180495_p(Shady.mc.field_71476_x.func_178782_a()).func_177230_c())) {
            final String itemId = Utils.getSkyBlockID(Shady.mc.field_71439_g.func_70694_bm());
            if (itemId.equals("STONK_PICKAXE") || itemId.equals("GOLD_PICKAXE")) {
                Shady.mc.field_71441_e.func_175698_g(Shady.mc.field_71476_x.func_178782_a());
                event.setCanceled(true);
            }
        }
    }
}
