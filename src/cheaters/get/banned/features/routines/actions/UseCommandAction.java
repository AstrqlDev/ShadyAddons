// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.features.routines.actions;

import cheaters.get.banned.utils.Utils;
import cheaters.get.banned.features.routines.RoutineRuntimeException;
import cheaters.get.banned.features.routines.RoutineElementData;

public class UseCommandAction extends Action
{
    private String command;
    
    public UseCommandAction(final RoutineElementData data) throws RoutineRuntimeException {
        super(data);
        this.command = data.keyAsString("command");
    }
    
    @Override
    public void doAction() {
        Utils.executeCommand("/" + this.command);
    }
}
