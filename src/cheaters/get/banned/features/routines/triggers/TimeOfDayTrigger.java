// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.features.routines.triggers;

import cheaters.get.banned.events.MinuteEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import cheaters.get.banned.features.routines.RoutineRuntimeException;
import cheaters.get.banned.features.routines.RoutineElementData;

public class TimeOfDayTrigger extends Trigger
{
    private int hour;
    private int minute;
    
    public TimeOfDayTrigger(final RoutineElementData data) throws RoutineRuntimeException {
        super(data);
        this.hour = data.keyAsInt("hour");
        this.minute = data.keyAsInt("minute");
        if (this.hour < 0 || this.hour > 23 || this.minute < 0 || this.minute > 59) {
            throw new RoutineRuntimeException("Hour or minute is out of range");
        }
    }
    
    @Override
    public boolean shouldTrigger(final Event event) {
        return event instanceof MinuteEvent && ((MinuteEvent)event).dateTime.getHour() == this.hour && ((MinuteEvent)event).dateTime.getMinute() == this.minute;
    }
}
