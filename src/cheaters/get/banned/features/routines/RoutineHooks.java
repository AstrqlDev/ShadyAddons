// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.features.routines;

import java.util.Iterator;
import java.util.Map;
import cheaters.get.banned.Shady;
import cheaters.get.banned.features.routines.triggers.SecondIntervalTrigger;
import cheaters.get.banned.features.routines.triggers.PositionTrigger;
import cheaters.get.banned.events.TickEndEvent;
import cheaters.get.banned.features.routines.triggers.TimeOfDayTrigger;
import cheaters.get.banned.events.MinuteEvent;
import cheaters.get.banned.features.routines.triggers.WorldLoadTrigger;
import net.minecraftforge.event.world.WorldEvent;
import cheaters.get.banned.features.routines.triggers.ChatReceivedTrigger;
import net.minecraft.network.play.server.S02PacketChat;
import cheaters.get.banned.events.PacketEvent;
import cheaters.get.banned.features.routines.triggers.CommandTrigger;
import cheaters.get.banned.events.SendChatMessageEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import cheaters.get.banned.features.routines.triggers.Trigger;
import cheaters.get.banned.features.routines.triggers.KeybindTrigger;
import net.minecraftforge.fml.common.gameevent.InputEvent;

public class RoutineHooks
{
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onKeyPress(final InputEvent.KeyInputEvent event) {
        runTrigger(KeybindTrigger.class, (Event)event);
    }
    
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onChatSent(final SendChatMessageEvent event) {
        runTrigger(CommandTrigger.class, event);
    }
    
    @SubscribeEvent
    public void onChatReceived(final PacketEvent.ReceiveEvent event) {
        if (event.packet instanceof S02PacketChat) {
            runTrigger(ChatReceivedTrigger.class, event);
        }
    }
    
    @SubscribeEvent
    public void onWorldLoad(final WorldEvent.Load event) {
        runTrigger(WorldLoadTrigger.class, (Event)event);
    }
    
    @SubscribeEvent
    public void onMinute(final MinuteEvent event) {
        runTrigger(TimeOfDayTrigger.class, event);
    }
    
    @SubscribeEvent
    public void onTick(final TickEndEvent event) {
        if (event.every(3)) {
            runTrigger(PositionTrigger.class, event);
        }
        if (event.every(20)) {
            runTrigger(SecondIntervalTrigger.class, event);
        }
    }
    
    private static void runTrigger(final Class<? extends Trigger> triggerClass, final Event event) {
        if (Shady.mc.field_71441_e == null) {
            return;
        }
        for (final Map.Entry<Trigger, Routine> routine : Routines.routines.entrySet()) {
            if (routine.getKey().getClass() == triggerClass && routine.getKey().shouldTrigger(event)) {
                routine.getValue().doActions();
                if (!routine.getKey().shouldCancelEvent) {
                    continue;
                }
                event.setCanceled(true);
            }
        }
    }
}
