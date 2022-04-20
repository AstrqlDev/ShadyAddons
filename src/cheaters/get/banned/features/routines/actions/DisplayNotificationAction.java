// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.features.routines.actions;

import cheaters.get.banned.utils.Utils;
import org.apache.commons.lang3.SystemUtils;
import cheaters.get.banned.features.routines.RoutineRuntimeException;
import cheaters.get.banned.features.routines.RoutineElementData;

public class DisplayNotificationAction extends Action
{
    private String message;
    
    public DisplayNotificationAction(final RoutineElementData data) throws RoutineRuntimeException {
        super(data);
        this.message = data.keyAsString("message");
        this.message = this.message.replace("\"", "");
    }
    
    @Override
    public void doAction() throws RoutineRuntimeException {
        if (!SystemUtils.IS_OS_MAC) {
            if (!SystemUtils.IS_OS_MAC_OSX) {
                Utils.sendModMessage("§e" + this.message);
                Utils.sendMessage("§oNotifications are only supported on macOS for now");
                return;
            }
        }
        try {
            Runtime.getRuntime().exec("osascript -e 'display notification \"" + this.message + "\" with title \"ShadyAddons\"'");
        }
        catch (Exception ex) {}
    }
}
