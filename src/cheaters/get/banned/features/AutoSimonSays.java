// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.features;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import cheaters.get.banned.utils.KeybindUtils;
import net.minecraft.init.Blocks;
import cheaters.get.banned.Shady;
import cheaters.get.banned.utils.Utils;
import cheaters.get.banned.gui.config.Config;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public class AutoSimonSays
{
    private boolean clicking;
    
    public AutoSimonSays() {
        this.clicking = false;
    }
    
    @SubscribeEvent
    public void onInteract(final PlayerInteractEvent event) {
        if (Config.autoSimonSays && !this.clicking && Utils.inDungeon && event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK && Shady.mc.field_71441_e.func_180495_p(event.pos).func_177230_c() == Blocks.field_150430_aB) {
            final int x = event.pos.func_177958_n();
            final int y = event.pos.func_177956_o();
            final int z = event.pos.func_177952_p();
            if (x == 109 && y >= 120 && y <= 123 && z >= 91 && z <= 94) {
                this.clicking = true;
                for (int i = 0; i < 4; ++i) {
                    KeybindUtils.rightClick();
                }
                this.clicking = false;
            }
        }
    }
}
