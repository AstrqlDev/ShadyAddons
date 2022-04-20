// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.gui.config.settings;

import java.util.Iterator;
import java.lang.reflect.Field;
import cheaters.get.banned.gui.config.Property;
import java.util.ArrayList;

public abstract class ParentSetting extends Setting
{
    public ArrayList<Setting> children;
    
    public ParentSetting(final Property annotation, final Field field) {
        super(annotation, field);
        this.children = new ArrayList<Setting>();
    }
    
    public ArrayList<Setting> getChildren(final ArrayList<Setting> settings) {
        final ArrayList<Setting> children = new ArrayList<Setting>();
        for (final Setting setting : settings) {
            if (setting.parent == this) {
                children.add(setting);
            }
        }
        return children;
    }
}
