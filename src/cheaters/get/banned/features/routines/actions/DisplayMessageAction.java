// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.features.routines.actions;

import cheaters.get.banned.utils.Utils;
import cheaters.get.banned.features.routines.RoutineRuntimeException;
import cheaters.get.banned.features.routines.RoutineElementData;

public class DisplayMessageAction extends Action
{
    private String message;
    
    public DisplayMessageAction(final RoutineElementData data) throws RoutineRuntimeException {
        super(data);
        this.message = data.keyAsString("message");
    }
    
    @Override
    public void doAction() throws RoutineRuntimeException {
        Utils.sendMessage(this.message);
    }
}
