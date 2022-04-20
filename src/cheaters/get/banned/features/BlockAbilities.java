// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.features;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import cheaters.get.banned.gui.config.Config;
import cheaters.get.banned.utils.Utils;
import cheaters.get.banned.Shady;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public class BlockAbilities
{
    @SubscribeEvent
    public void onInteract(final PlayerInteractEvent event) {
        if (event.action == PlayerInteractEvent.Action.RIGHT_CLICK_AIR || (event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK && Shady.mc.field_71439_g.func_70694_bm() != null)) {
            final String skyBlockID = Utils.getSkyBlockID(Shady.mc.field_71439_g.func_70694_bm());
            if ((Config.blockCellsAlignment && skyBlockID.equals("GYROKINETIC_WAND")) || (Config.blockGiantsSlam && skyBlockID.equals("GIANTS_SWORD")) || (Config.blockValkyrie && Utils.inDungeon && skyBlockID.equals("VALKYRIE"))) {
                event.setCanceled(true);
            }
        }
    }
}
