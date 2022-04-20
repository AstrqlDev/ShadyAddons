// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.gui.config.settings;

import java.lang.reflect.Field;
import cheaters.get.banned.gui.config.Property;

public class SelectSetting extends Setting
{
    public String[] options;
    
    public SelectSetting(final Property annotation, final Field field) {
        super(annotation, field);
        this.options = annotation.options();
    }
    
    @Override
    public boolean set(final Object value) {
        if (((Number)value).intValue() > this.options.length - 1) {
            return super.set(0);
        }
        if (((Number)value).intValue() < 0) {
            return super.set(this.options.length - 1);
        }
        return super.set(value);
    }
}
