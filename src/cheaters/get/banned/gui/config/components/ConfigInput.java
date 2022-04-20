// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.gui.config.components;

import cheaters.get.banned.gui.config.settings.SpacerSetting;
import cheaters.get.banned.gui.config.settings.ButtonSetting;
import cheaters.get.banned.gui.config.settings.SelectSetting;
import cheaters.get.banned.gui.config.settings.NumberSetting;
import cheaters.get.banned.gui.config.settings.FolderSetting;
import cheaters.get.banned.gui.config.settings.BooleanSetting;
import cheaters.get.banned.gui.config.settings.Setting;
import java.awt.Color;
import net.minecraft.client.gui.GuiButton;

public abstract class ConfigInput extends GuiButton
{
    public static Color white;
    public static Color green;
    public static Color red;
    public static Color transparent;
    public Setting setting;
    
    public ConfigInput(final Setting setting, final int x, final int y) {
        super(0, x, y, "");
        this.setting = setting;
    }
    
    public static ConfigInput buttonFromSetting(final Setting setting, final int x, final int y) {
        switch (setting.annotation.type()) {
            case BOOLEAN: {
                return new SwitchInput((BooleanSetting)setting, x, y);
            }
            case CHECKBOX: {
                return new CheckboxInput((BooleanSetting)setting, x, y);
            }
            case FOLDER: {
                return new FolderComponent((FolderSetting)setting, x, y);
            }
            case NUMBER: {
                return new NumberInput((NumberSetting)setting, x, y);
            }
            case SELECT: {
                return new SelectInput((SelectSetting)setting, x, y);
            }
            case BUTTON: {
                return new ButtonInput((ButtonSetting)setting, x, y);
            }
            case SPACER: {
                return new SpacerComponent((SpacerSetting)setting, x, y);
            }
            default: {
                return null;
            }
        }
    }
    
    static {
        ConfigInput.white = new Color(255, 255, 255);
        ConfigInput.green = new Color(85, 255, 85);
        ConfigInput.red = new Color(255, 85, 85);
        ConfigInput.transparent = new Color(255, 255, 255, 64);
    }
}
