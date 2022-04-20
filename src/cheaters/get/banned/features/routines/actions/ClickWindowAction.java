// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.features.routines.actions;

import net.minecraft.entity.player.EntityPlayer;
import cheaters.get.banned.Shady;
import cheaters.get.banned.features.routines.RoutineRuntimeException;
import cheaters.get.banned.features.routines.RoutineElementData;

public class ClickWindowAction extends Action
{
    private int slot;
    private int mouseButton;
    private int mode;
    private int windowIdOffset;
    
    public ClickWindowAction(final RoutineElementData data) throws RoutineRuntimeException {
        super(data);
        this.slot = data.keyAsInt("slot");
        this.mouseButton = data.keyAsInt("mouse_button");
        this.mode = data.keyAsInt("mode");
        this.windowIdOffset = data.keyAsInt("window_id_offset");
    }
    
    @Override
    public void doAction() {
        Shady.mc.field_71442_b.func_78753_a(Shady.mc.field_71439_g.field_71070_bA.field_75152_c + this.windowIdOffset, this.slot, this.mouseButton, this.mode, (EntityPlayer)Shady.mc.field_71439_g);
    }
}
