// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.features;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.entity.player.EntityPlayer;
import cheaters.get.banned.Shady;
import cheaters.get.banned.utils.Utils;
import cheaters.get.banned.gui.config.Config;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public class TeleportWithAnything
{
    @SubscribeEvent
    public void onInteract(final PlayerInteractEvent event) {
        if (Config.teleportWithAnything && Utils.inSkyBlock && Shady.mc.field_71439_g.field_71071_by.field_70461_c == 0 && event.action == PlayerInteractEvent.Action.RIGHT_CLICK_AIR) {
            for (int i = 0; i < 9; ++i) {
                final ItemStack item = Shady.mc.field_71439_g.field_71071_by.func_70301_a(i);
                final String itemID = Utils.getSkyBlockID(item);
                if (itemID.equals("ASPECT_OF_THE_END") || itemID.equals("ASPECT_OF_THE_VOID")) {
                    event.setCanceled(true);
                    Shady.mc.field_71439_g.field_71071_by.field_70461_c = i;
                    Shady.mc.field_71442_b.func_78769_a((EntityPlayer)Shady.mc.field_71439_g, (World)Shady.mc.field_71441_e, item);
                    Shady.mc.field_71439_g.field_71071_by.field_70461_c = 0;
                    break;
                }
            }
        }
    }
}
