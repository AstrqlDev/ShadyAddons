// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.features.routines.triggers;

import cheaters.get.banned.events.TickEndEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import cheaters.get.banned.features.routines.RoutineRuntimeException;
import cheaters.get.banned.features.routines.RoutineElementData;

public class SecondIntervalTrigger extends Trigger
{
    private int seconds;
    
    public SecondIntervalTrigger(final RoutineElementData data) throws RoutineRuntimeException {
        super(data);
        this.seconds = data.keyAsInt("seconds");
    }
    
    @Override
    public boolean shouldTrigger(final Event event) {
        return event instanceof TickEndEvent && ((TickEndEvent)event).every(this.seconds * 20);
    }
}
