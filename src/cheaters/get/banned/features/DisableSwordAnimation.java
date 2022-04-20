// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.features;

import net.minecraft.network.Packet;
import cheaters.get.banned.utils.NetworkUtils;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.util.BlockPos;
import cheaters.get.banned.utils.Utils;
import cheaters.get.banned.gui.config.Config;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import cheaters.get.banned.Shady;
import cheaters.get.banned.events.TickEndEvent;
import java.util.Collection;
import java.util.Arrays;
import java.util.ArrayList;

public class DisableSwordAnimation
{
    private final ArrayList<String> swords;
    private static boolean isRightClickKeyDown;
    
    public DisableSwordAnimation() {
        this.swords = new ArrayList<String>(Arrays.asList("HYPERION", "VALKYRIE", "SCYLLA", "ASTRAEA", "ASPECT_OF_THE_END", "ROGUE_SWORD"));
    }
    
    @SubscribeEvent
    public void onTick(final TickEndEvent event) {
        DisableSwordAnimation.isRightClickKeyDown = Shady.mc.field_71474_y.field_74313_G.func_151470_d();
    }
    
    @SubscribeEvent
    public void onInteract(final PlayerInteractEvent event) {
        if (Config.disableBlockAnimation && event.action == PlayerInteractEvent.Action.RIGHT_CLICK_AIR && Shady.mc.field_71439_g.func_70694_bm() != null) {
            final String itemID = Utils.getSkyBlockID(Shady.mc.field_71439_g.func_70694_bm());
            if (this.swords.contains(itemID)) {
                event.setCanceled(true);
                if (!DisableSwordAnimation.isRightClickKeyDown) {
                    NetworkUtils.sendPacket((Packet<?>)new C08PacketPlayerBlockPlacement(new BlockPos(-1, -1, -1), 255, Shady.mc.field_71439_g.func_70694_bm(), 0.0f, 0.0f, 0.0f));
                }
            }
        }
    }
    
    static {
        DisableSwordAnimation.isRightClickKeyDown = false;
    }
}
