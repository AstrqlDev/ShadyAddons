// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.events;

import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.Event;

@Cancelable
public class SendChatMessageEvent extends Event
{
    public String message;
    public boolean addToChat;
    
    public SendChatMessageEvent(final String message, final boolean addToChat) {
        this.message = message;
        this.addToChat = addToChat;
    }
}
