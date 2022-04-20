// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.utils;

import cheaters.get.banned.Shady;
import net.minecraft.network.Packet;

public class NetworkUtils
{
    public static void sendPacket(final Packet<?> packet) {
        Shady.mc.func_147114_u().func_147297_a((Packet)packet);
    }
}
