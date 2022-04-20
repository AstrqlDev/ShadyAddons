// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.features.routines.triggers;

import net.minecraft.util.StringUtils;
import net.minecraft.network.play.server.S02PacketChat;
import cheaters.get.banned.events.PacketEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import cheaters.get.banned.features.routines.RoutineRuntimeException;
import cheaters.get.banned.features.routines.RoutineElementData;

public class ChatReceivedTrigger extends Trigger
{
    public String messageContains;
    
    public ChatReceivedTrigger(final RoutineElementData data) throws RoutineRuntimeException {
        super(data);
        this.messageContains = data.keyAsString("message_contains");
    }
    
    @Override
    public boolean shouldTrigger(final Event event) {
        if (event instanceof PacketEvent.ReceiveEvent) {
            final S02PacketChat packet = (S02PacketChat)((PacketEvent.ReceiveEvent)event).packet;
            String messageContent = packet.func_148915_c().func_150260_c();
            messageContent = StringUtils.func_76338_a(messageContent);
            return messageContent.contains(this.messageContains);
        }
        return false;
    }
}
