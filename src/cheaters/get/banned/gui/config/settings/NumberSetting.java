// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.gui.config.settings;

import net.minecraft.util.MathHelper;
import java.lang.reflect.Field;
import cheaters.get.banned.gui.config.Property;

public class NumberSetting extends Setting implements Comparable<Integer>
{
    public int step;
    public int min;
    public int max;
    public String prefix;
    public String suffix;
    
    public NumberSetting(final Property annotation, final Field field) {
        super(annotation, field);
        this.step = annotation.step();
        this.min = annotation.min();
        this.max = annotation.max();
        this.prefix = annotation.prefix();
        this.suffix = annotation.suffix();
    }
    
    @Override
    public boolean set(final Object value) {
        return super.set(MathHelper.func_76125_a((int)value, this.min, this.max));
    }
    
    @Override
    public int compareTo(final Integer other) {
        try {
            return Integer.compare(this.get(Integer.TYPE), other);
        }
        catch (Exception ex) {
            return 0;
        }
    }
}
