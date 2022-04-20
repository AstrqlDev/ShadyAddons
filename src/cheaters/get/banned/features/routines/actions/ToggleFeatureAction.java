// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.features.routines.actions;

import cheaters.get.banned.utils.Utils;
import cheaters.get.banned.features.routines.RoutineRuntimeException;
import cheaters.get.banned.gui.config.settings.BooleanSetting;
import cheaters.get.banned.gui.config.ConfigLogic;
import cheaters.get.banned.Shady;
import cheaters.get.banned.features.routines.RoutineElementData;
import cheaters.get.banned.gui.config.settings.Setting;

public class ToggleFeatureAction extends Action
{
    private Setting setting;
    private boolean sendMessage;
    
    public ToggleFeatureAction(final RoutineElementData data) throws RoutineRuntimeException {
        super(data);
        this.sendMessage = data.keyAsBool("send_message");
        final String name = data.keyAsString("feature_name");
        this.setting = ConfigLogic.getSettingByName(name, Shady.settings);
        if (!(this.setting instanceof BooleanSetting)) {
            throw new RoutineRuntimeException("Invalid setting, or setting is not toggleable");
        }
    }
    
    @Override
    public void doAction() throws RoutineRuntimeException {
        final Boolean value = this.setting.get(Boolean.class);
        if (value == null) {
            this.setting.set(this.setting.get(Boolean.class));
        }
        else {
            this.setting.set(!value);
        }
        if (this.sendMessage) {
            Utils.sendMessage("&7ShadyAddons Routines > &fTurned \"" + this.setting.name + "\" &l" + (this.setting.get(Boolean.class) ? "&aON" : "&cOFF"));
        }
        ConfigLogic.save();
    }
}
