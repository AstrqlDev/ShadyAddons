// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.features.routines.actions;

import net.minecraft.network.Packet;
import cheaters.get.banned.utils.NetworkUtils;
import net.minecraft.network.play.client.C09PacketHeldItemChange;
import cheaters.get.banned.Shady;
import cheaters.get.banned.features.routines.RoutineElementData;

public class SwapPreviousHotbarAction extends Action
{
    public static int slot;
    
    public SwapPreviousHotbarAction(final RoutineElementData data) {
        super(data);
    }
    
    @Override
    public void doAction() {
        if (SwapPreviousHotbarAction.slot > -1 && SwapPreviousHotbarAction.slot < 9) {
            Shady.mc.field_71439_g.field_71071_by.field_70461_c = SwapPreviousHotbarAction.slot;
            NetworkUtils.sendPacket((Packet<?>)new C09PacketHeldItemChange(SwapPreviousHotbarAction.slot));
        }
    }
    
    static {
        SwapPreviousHotbarAction.slot = -1;
    }
}
