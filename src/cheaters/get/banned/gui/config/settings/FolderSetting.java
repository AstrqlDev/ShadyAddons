// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.gui.config.settings;

import cheaters.get.banned.gui.config.ConfigLogic;
import cheaters.get.banned.Shady;
import java.util.Iterator;
import java.lang.reflect.Field;
import cheaters.get.banned.gui.config.Property;

public class FolderSetting extends ParentSetting implements DoNotSave
{
    public FolderSetting(final Property annotation, final Field field) {
        super(annotation, field);
    }
    
    public boolean isChildEnabled() {
        for (final Setting child : this.children) {
            if (child instanceof BooleanSetting && child.get(Boolean.class)) {
                return true;
            }
            if (child instanceof FolderSetting && ((FolderSetting)child).isChildEnabled()) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean isEnabled(final String name) {
        final Setting setting = ConfigLogic.getSettingByName(name, Shady.settings);
        return setting != null && ((FolderSetting)setting).isChildEnabled();
    }
}
