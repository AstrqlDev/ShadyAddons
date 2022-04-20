// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.features;

import cheaters.get.banned.utils.Utils;
import cheaters.get.banned.gui.config.Config;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import cheaters.get.banned.Shady;

public class NoRotate
{
    private static float prevPitch;
    private static float prevYaw;
    
    public static void handlePlayerPosLookPre() {
        if (Shady.mc.field_71439_g == null || Shady.mc.field_71439_g.field_70125_A % 1.0f == 0.0f || Shady.mc.field_71439_g.field_70177_z % 1.0f == 0.0f) {
            return;
        }
        NoRotate.prevPitch = Shady.mc.field_71439_g.field_70125_A % 360.0f;
        NoRotate.prevYaw = Shady.mc.field_71439_g.field_70177_z % 360.0f;
    }
    
    public static void handlePlayerPosLook(final S08PacketPlayerPosLook packet) {
        if (!Config.noRotate || !Utils.inSkyBlock || packet.func_148930_g() % 1.0f == 0.0f || packet.func_148931_f() % 1.0f == 0.0f) {
            return;
        }
        final float prevPitch1 = Shady.mc.field_71439_g.field_70125_A;
        final float prevYaw1 = Shady.mc.field_71439_g.field_70177_z;
        Shady.mc.field_71439_g.field_70125_A = NoRotate.prevPitch;
        Shady.mc.field_71439_g.field_70177_z = NoRotate.prevYaw;
        NoRotate.prevPitch = prevPitch1;
        NoRotate.prevYaw = prevYaw1;
    }
    
    static {
        NoRotate.prevPitch = 0.0f;
        NoRotate.prevYaw = 0.0f;
    }
}
