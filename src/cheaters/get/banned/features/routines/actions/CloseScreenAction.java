// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.features.routines.actions;

import cheaters.get.banned.features.routines.RoutineRuntimeException;
import cheaters.get.banned.Shady;
import cheaters.get.banned.features.routines.RoutineElementData;

public class CloseScreenAction extends Action
{
    public CloseScreenAction(final RoutineElementData data) {
        super(data);
    }
    
    @Override
    public void doAction() throws RoutineRuntimeException {
        Shady.mc.field_71439_g.func_71053_j();
    }
}
