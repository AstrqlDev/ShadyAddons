// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.features.routines.triggers;

import cheaters.get.banned.Shady;
import cheaters.get.banned.events.TickEndEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import cheaters.get.banned.features.routines.RoutineRuntimeException;
import cheaters.get.banned.features.routines.RoutineElementData;
import net.minecraft.util.BlockPos;

public class PositionTrigger extends Trigger
{
    private BlockPos position;
    private static BlockPos prevPosition;
    
    public PositionTrigger(final RoutineElementData data) throws RoutineRuntimeException {
        super(data);
        this.position = new BlockPos((int)data.keyAsInt("x"), (int)data.keyAsInt("y"), (int)data.keyAsInt("z"));
    }
    
    @Override
    public boolean shouldTrigger(final Event event) {
        final boolean flag = event instanceof TickEndEvent && Shady.mc.field_71439_g != null && !Shady.mc.field_71439_g.func_180425_c().equals((Object)PositionTrigger.prevPosition) && Shady.mc.field_71439_g.func_180425_c().equals((Object)this.position);
        if (flag) {
            PositionTrigger.prevPosition = this.position;
        }
        return flag;
    }
}
