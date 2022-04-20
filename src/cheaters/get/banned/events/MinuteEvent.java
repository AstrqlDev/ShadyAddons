// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.events;

import java.time.LocalDateTime;
import net.minecraftforge.fml.common.eventhandler.Event;

public class MinuteEvent extends Event
{
    public LocalDateTime dateTime;
    
    public MinuteEvent() {
        this.dateTime = LocalDateTime.now();
    }
}
