// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.events;

import cheaters.get.banned.gui.config.settings.Setting;
import net.minecraftforge.fml.common.eventhandler.Event;

public class SettingChangeEvent extends Event
{
    public Setting setting;
    public Object oldValue;
    public Object newValue;
    
    public SettingChangeEvent(final Setting setting, final Object oldValue, final Object newValue) {
        this.setting = setting;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }
}
