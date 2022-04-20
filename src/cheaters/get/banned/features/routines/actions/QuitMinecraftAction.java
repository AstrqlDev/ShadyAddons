// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.features.routines.actions;

import cheaters.get.banned.Shady;
import cheaters.get.banned.features.routines.RoutineElementData;

public class QuitMinecraftAction extends Action
{
    public QuitMinecraftAction(final RoutineElementData data) {
        super(data);
    }
    
    @Override
    public void doAction() {
        Shady.mc.func_71400_g();
    }
}
