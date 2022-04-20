// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.gui.config.settings;

import net.minecraftforge.fml.common.eventhandler.Event;
import cheaters.get.banned.events.SettingChangeEvent;
import net.minecraftforge.common.MinecraftForge;
import cheaters.get.banned.gui.config.Property;
import java.lang.reflect.Field;

public abstract class Setting
{
    public String name;
    public ParentSetting parent;
    public String note;
    public boolean warning;
    public boolean beta;
    public Field field;
    public Property annotation;
    
    public Setting(final Property annotation, final Field field) {
        this.parent = null;
        this.annotation = annotation;
        this.name = annotation.name();
        this.warning = annotation.warning();
        this.beta = annotation.beta();
        if (!annotation.note().equals("")) {
            this.note = annotation.note();
        }
        this.field = field;
    }
    
    public int getIndent(final int startingIndent) {
        return this.getIndent(startingIndent, this);
    }
    
    public int getIndent(int startingIndent, final Setting setting) {
        if (setting.parent != null) {
            startingIndent += 10;
            return setting.getIndent(startingIndent, setting.parent);
        }
        return startingIndent;
    }
    
    public <T> T get(final Class<T> type) {
        try {
            return type.cast(this.field.get(Object.class));
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public boolean set(final Object value) {
        try {
            final Object oldValue = this.get(Object.class);
            this.field.set(value.getClass(), value);
            MinecraftForge.EVENT_BUS.post((Event)new SettingChangeEvent(this, oldValue, value));
            return true;
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    public boolean forceSet(final Object value) {
        try {
            this.field.set(value.getClass(), value);
            return true;
        }
        catch (Exception ex) {
            return false;
        }
    }
}
