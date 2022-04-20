// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.features.routines.actions;

import cheaters.get.banned.utils.Utils;
import cheaters.get.banned.features.routines.RoutineRuntimeException;
import cheaters.get.banned.features.routines.RoutineElementData;

public class OpenWebsiteAction extends Action
{
    private String url;
    
    public OpenWebsiteAction(final RoutineElementData data) throws RoutineRuntimeException {
        super(data);
        this.url = data.keyAsString("url");
    }
    
    @Override
    public void doAction() {
        Utils.openUrl(this.url);
    }
}
