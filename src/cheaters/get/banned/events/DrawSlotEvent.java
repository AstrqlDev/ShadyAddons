// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.events;

import net.minecraft.inventory.Slot;
import net.minecraft.inventory.Container;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.Event;

@Cancelable
public class DrawSlotEvent extends Event
{
    public Container container;
    public Slot slot;
    
    public DrawSlotEvent(final Container container, final Slot slot) {
        this.container = container;
        this.slot = slot;
    }
}
