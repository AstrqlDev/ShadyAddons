// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.features.routines.actions;

import cheaters.get.banned.utils.KeybindUtils;
import cheaters.get.banned.features.routines.RoutineElementData;

public class LeftClickAction extends Action
{
    public LeftClickAction(final RoutineElementData data) {
        super(data);
    }
    
    @Override
    public void doAction() {
        KeybindUtils.leftClick();
    }
}
