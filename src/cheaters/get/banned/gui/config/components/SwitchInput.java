// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.gui.config.components;

import net.minecraft.client.Minecraft;
import cheaters.get.banned.gui.config.settings.Setting;
import cheaters.get.banned.gui.config.settings.BooleanSetting;

public class SwitchInput extends ConfigInput
{
    public BooleanSetting setting;
    
    public SwitchInput(final BooleanSetting setting, final int x, final int y) {
        super(setting, x, y);
        this.setting = setting;
        super.field_146120_f = 25;
        super.field_146121_g = 9;
        super.field_146128_h -= this.field_146120_f;
    }
    
    public void func_146112_a(final Minecraft mc, final int mouseX, final int mouseY) {
        func_73734_a(this.field_146128_h, this.field_146129_i + 3, this.field_146128_h + this.field_146120_f, this.field_146129_i + 6, SwitchInput.white.getRGB());
        func_73734_a(((boolean)this.setting.get(Boolean.class)) ? (this.field_146128_h + this.field_146120_f - this.field_146121_g) : this.field_146128_h, this.field_146129_i, ((boolean)this.setting.get(Boolean.class)) ? (this.field_146128_h + this.field_146120_f) : (this.field_146128_h + this.field_146121_g), this.field_146129_i + this.field_146121_g, ((boolean)this.setting.get(Boolean.class)) ? SwitchInput.green.getRGB() : SwitchInput.red.getRGB());
    }
    
    public boolean func_146116_c(final Minecraft mc, final int mouseX, final int mouseY) {
        if (mouseX >= this.field_146128_h && mouseY >= this.field_146129_i && mouseX < this.field_146128_h + this.field_146120_f && mouseY < this.field_146129_i + this.field_146121_g) {
            this.setting.set(!this.setting.get(Boolean.class));
            return true;
        }
        return false;
    }
}
