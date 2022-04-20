// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.features.routines.triggers;

import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import cheaters.get.banned.features.routines.RoutineElementData;

public class WorldLoadTrigger extends Trigger
{
    public WorldLoadTrigger(final RoutineElementData data) {
        super(data);
    }
    
    @Override
    public boolean shouldTrigger(final Event event) {
        return event instanceof WorldEvent.Load;
    }
}
