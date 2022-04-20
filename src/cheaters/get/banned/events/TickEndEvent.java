// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.events;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.eventhandler.Event;

public class TickEndEvent extends Event
{
    private static int staticCount;
    public int count;
    
    public TickEndEvent() {
        this.count = TickEndEvent.staticCount;
    }
    
    public boolean every(final int ticks) {
        return this.count % ticks == 0;
    }
    
    @SubscribeEvent
    public void onTick(final TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            MinecraftForge.EVENT_BUS.post((Event)new TickEndEvent());
            ++TickEndEvent.staticCount;
        }
    }
    
    static {
        TickEndEvent.staticCount = 0;
    }
}
