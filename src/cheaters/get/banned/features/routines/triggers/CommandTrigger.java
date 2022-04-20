// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.features.routines.triggers;

import cheaters.get.banned.events.SendChatMessageEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import cheaters.get.banned.features.routines.RoutineRuntimeException;
import cheaters.get.banned.features.routines.RoutineElementData;

public class CommandTrigger extends Trigger
{
    private String name;
    
    public CommandTrigger(final RoutineElementData data) throws RoutineRuntimeException {
        super(data);
        super.shouldCancelEvent = true;
        this.name = data.keyAsString("command");
    }
    
    @Override
    public boolean shouldTrigger(final Event event) {
        return event instanceof SendChatMessageEvent && ((SendChatMessageEvent)event).message.equals("/" + this.name);
    }
}
