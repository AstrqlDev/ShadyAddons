// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.features.routines.triggers;

import net.minecraftforge.fml.common.eventhandler.Event;
import cheaters.get.banned.features.routines.RoutineElementData;

public abstract class Trigger
{
    public RoutineElementData data;
    public boolean shouldCancelEvent;
    
    public Trigger(final RoutineElementData data) {
        this.shouldCancelEvent = false;
        this.data = data;
    }
    
    public abstract boolean shouldTrigger(final Event p0);
}
