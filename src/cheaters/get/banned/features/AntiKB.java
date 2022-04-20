// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.features;

import cheaters.get.banned.utils.Utils;
import cheaters.get.banned.gui.config.Config;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.client.entity.EntityPlayerSP;
import cheaters.get.banned.Shady;
import net.minecraft.network.play.server.S27PacketExplosion;

public class AntiKB
{
    public static void handleExplosion(final S27PacketExplosion packet) {
        if (isEnabled()) {
            final EntityPlayerSP field_71439_g = Shady.mc.field_71439_g;
            field_71439_g.field_70159_w -= packet.func_149149_c();
            final EntityPlayerSP field_71439_g2 = Shady.mc.field_71439_g;
            field_71439_g2.field_70181_x -= packet.func_149144_d();
            final EntityPlayerSP field_71439_g3 = Shady.mc.field_71439_g;
            field_71439_g3.field_70179_y -= packet.func_149147_e();
        }
    }
    
    public static boolean handleEntityVelocity(final S12PacketEntityVelocity packet) {
        return isEnabled() && Shady.mc.field_71441_e.func_73045_a(packet.func_149412_c()) == Shady.mc.field_71439_g;
    }
    
    private static boolean isEnabled() {
        if (!Config.antiKb || !Utils.inSkyBlock) {
            return false;
        }
        if (Shady.mc.field_71439_g == null || Shady.mc.field_71439_g.func_180799_ab()) {
            return false;
        }
        if (Shady.mc.field_71439_g.func_70694_bm() != null) {
            final String itemId = Utils.getSkyBlockID(Shady.mc.field_71439_g.func_70694_bm());
            return !itemId.equals("JERRY_STAFF") && !itemId.equals("BONZO_STAFF") && !itemId.equals("STARRED_BONZO_STAFF");
        }
        return true;
    }
}
