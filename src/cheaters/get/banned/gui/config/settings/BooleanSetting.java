// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.gui.config.settings;

import java.util.Iterator;
import java.lang.reflect.Field;
import cheaters.get.banned.gui.config.Property;

public class BooleanSetting extends ParentSetting
{
    public Property.Type type;
    
    public BooleanSetting(final Property annotation, final Field field, final Property.Type type) {
        super(annotation, field);
        this.type = type;
    }
    
    @Override
    public boolean set(final Object value) {
        try {
            for (final Setting child : this.children) {
                if (child instanceof ParentSetting) {
                    child.set(false);
                }
            }
            return super.set(value);
        }
        catch (Exception exception) {
            System.out.println("Failed to set " + this.name + " to " + value);
            exception.printStackTrace();
            return false;
        }
    }
}
