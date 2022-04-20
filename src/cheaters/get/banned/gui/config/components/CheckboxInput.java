// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.gui.config.components;

import cheaters.get.banned.utils.FontUtils;
import net.minecraft.client.Minecraft;
import cheaters.get.banned.gui.config.settings.Setting;
import cheaters.get.banned.gui.config.settings.BooleanSetting;

public class CheckboxInput extends ConfigInput
{
    public BooleanSetting setting;
    
    public CheckboxInput(final BooleanSetting setting, final int x, final int y) {
        super(setting, x, y);
        this.setting = setting;
        super.field_146120_f = 9;
        super.field_146121_g = 9;
        super.field_146128_h -= 9;
    }
    
    public void func_146112_a(final Minecraft mc, final int mouseX, final int mouseY) {
        this.field_146123_n = (mouseX >= this.field_146128_h && mouseY >= this.field_146129_i && mouseX < this.field_146128_h + this.field_146120_f && mouseY < this.field_146129_i + this.field_146121_g);
        func_73734_a(this.field_146128_h, this.field_146129_i, this.field_146128_h + this.field_146120_f, this.field_146129_i + this.field_146121_g, CheckboxInput.white.getRGB());
        if (this.setting.get(Boolean.class)) {
            FontUtils.drawString("§0x", this.field_146128_h + 2, this.field_146129_i, false);
        }
        else if (this.field_146123_n) {
            FontUtils.drawString("§7x", this.field_146128_h + 2, this.field_146129_i, false);
        }
    }
    
    public boolean func_146116_c(final Minecraft mc, final int mouseX, final int mouseY) {
        if (this.field_146123_n) {
            this.setting.set(!this.setting.get(Boolean.class));
            return true;
        }
        return false;
    }
}
