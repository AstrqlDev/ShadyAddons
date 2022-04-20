// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.gui.config.settings;

import java.lang.reflect.Field;
import cheaters.get.banned.gui.config.Property;

public class ButtonSetting extends Setting implements DoNotSave
{
    public String buttonText;
    private Runnable runnable;
    
    public ButtonSetting(final Property annotation, final Field field) {
        super(annotation, field);
        this.buttonText = "Click";
        if (!annotation.button().equals("")) {
            this.buttonText = annotation.button();
        }
        this.runnable = this.get(Runnable.class);
    }
    
    public void run() {
        this.runnable.run();
    }
}
