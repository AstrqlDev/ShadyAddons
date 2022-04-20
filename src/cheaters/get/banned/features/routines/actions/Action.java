// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.features.routines.actions;

import cheaters.get.banned.features.routines.RoutineRuntimeException;
import cheaters.get.banned.features.routines.RoutineElementData;

public abstract class Action
{
    protected RoutineElementData data;
    
    public Action(final RoutineElementData data) {
        this.data = data;
    }
    
    public abstract void doAction() throws RoutineRuntimeException;
    
    public int getRepeat() {
        final Integer times = this.data.keyAsInt_noError("repeat");
        if (times == null) {
            return 1;
        }
        return times;
    }
}
