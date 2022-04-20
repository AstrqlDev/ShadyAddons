// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.features.routines.actions;

import cheaters.get.banned.utils.ThreadUtils;
import cheaters.get.banned.features.routines.RoutineRuntimeException;
import cheaters.get.banned.features.routines.RoutineElementData;

public class WaitAction extends Action
{
    private int ms;
    
    public WaitAction(final RoutineElementData data) throws RoutineRuntimeException {
        super(data);
        this.ms = data.keyAsInt("ms");
    }
    
    @Override
    public void doAction() {
        ThreadUtils.sleep(this.ms);
    }
}
