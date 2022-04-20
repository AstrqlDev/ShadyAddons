// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.features.routines.triggers;

import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import cheaters.get.banned.features.routines.RoutineRuntimeException;
import org.lwjgl.input.Keyboard;
import cheaters.get.banned.features.routines.RoutineElementData;

public class KeybindTrigger extends Trigger
{
    private int keyCode;
    
    public KeybindTrigger(final RoutineElementData data) throws RoutineRuntimeException {
        super(data);
        final String letter = data.keyAsString("key").toUpperCase().substring(0, 1);
        this.keyCode = Keyboard.getKeyIndex(letter);
        if (this.keyCode == 0) {
            throw new RoutineRuntimeException("Invalid key character '" + letter + "' in trigger");
        }
    }
    
    @Override
    public boolean shouldTrigger(final Event event) {
        return event instanceof InputEvent.KeyInputEvent && Keyboard.getEventKey() == this.keyCode && Keyboard.getEventKeyState();
    }
}
