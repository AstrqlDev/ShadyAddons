// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.events;

import net.minecraft.network.Packet;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.Event;

@Cancelable
public class PacketEvent extends Event
{
    public Direction direction;
    public Packet<?> packet;
    
    public PacketEvent(final Packet<?> packet) {
        this.packet = packet;
    }
    
    public static class ReceiveEvent extends PacketEvent
    {
        public ReceiveEvent(final Packet<?> packet) {
            super(packet);
            this.direction = Direction.INBOUND;
        }
    }
    
    public static class SendEvent extends PacketEvent
    {
        public SendEvent(final Packet<?> packet) {
            super(packet);
            this.direction = Direction.OUTBOUND;
        }
    }
    
    enum Direction
    {
        INBOUND, 
        OUTBOUND;
    }
}
