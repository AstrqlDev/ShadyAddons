// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.gui.config.components;

import java.awt.Color;
import net.minecraft.client.Minecraft;
import cheaters.get.banned.utils.FontUtils;
import cheaters.get.banned.gui.config.settings.Setting;
import cheaters.get.banned.gui.config.settings.ButtonSetting;

public class ButtonInput extends ConfigInput
{
    public ButtonSetting setting;
    
    public ButtonInput(final ButtonSetting setting, final int x, final int y) {
        super(setting, x, y);
        this.setting = setting;
        super.field_146120_f = Math.round(FontUtils.getStringWidth(setting.buttonText) * 0.9f) + 6;
        super.field_146121_g = Math.round(8.099999f) + 2;
        super.field_146128_h -= this.field_146120_f;
    }
    
    public void func_146112_a(final Minecraft mc, final int mouseX, final int mouseY) {
        this.field_146123_n = (mouseX >= this.field_146128_h && mouseY >= this.field_146129_i && mouseX < this.field_146128_h + this.field_146120_f && mouseY < this.field_146129_i + this.field_146121_g);
        final Color color = this.field_146123_n ? new Color(231, 231, 231, 64) : new Color(182, 182, 182, 64);
        func_73734_a(this.field_146128_h, this.field_146129_i, this.field_146128_h + this.field_146120_f, this.field_146129_i + this.field_146121_g, color.getRGB());
        FontUtils.drawScaledCenteredString(this.setting.buttonText, 0.9f, this.field_146128_h + this.field_146120_f / 2, this.field_146129_i + this.field_146121_g / 2 + 1, false);
    }
    
    public boolean func_146116_c(final Minecraft mc, final int mouseX, final int mouseY) {
        if (this.field_146123_n) {
            this.setting.run();
            return true;
        }
        return false;
    }
}
