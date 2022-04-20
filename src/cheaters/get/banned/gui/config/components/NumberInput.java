// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.gui.config.components;

import net.minecraft.client.Minecraft;
import cheaters.get.banned.utils.FontUtils;
import cheaters.get.banned.gui.config.settings.Setting;
import cheaters.get.banned.gui.config.settings.NumberSetting;

public class NumberInput extends ConfigInput
{
    private int minusWidth;
    private int plusWidth;
    private int gap;
    private boolean minusHovered;
    private boolean plusHovered;
    public NumberSetting setting;
    
    public NumberInput(final NumberSetting setting, final int x, final int y) {
        super(setting, x, y);
        this.minusWidth = FontUtils.getStringWidth("-");
        this.plusWidth = FontUtils.getStringWidth("+");
        this.gap = 3;
        this.minusHovered = false;
        this.plusHovered = false;
        this.setting = setting;
        this.field_146121_g = 10;
        this.updateText();
    }
    
    public void func_146112_a(final Minecraft mc, final int mouseX, final int mouseY) {
        this.plusHovered = (mouseX >= this.field_146128_h - this.plusWidth - this.gap && mouseY >= this.field_146129_i && mouseX < this.field_146128_h && mouseY < this.field_146129_i + this.field_146121_g);
        this.minusHovered = (mouseX >= this.field_146128_h - this.field_146120_f && mouseY >= this.field_146129_i && mouseX < this.field_146128_h - this.field_146120_f + this.minusWidth + this.gap && mouseY < this.field_146129_i + this.field_146121_g);
        FontUtils.drawString((this.minusHovered ? "§c" : "§7") + "-", this.field_146128_h - this.field_146120_f, this.field_146129_i, false);
        FontUtils.drawString(this.field_146126_j, this.field_146128_h - this.field_146120_f + this.minusWidth + this.gap, this.field_146129_i, false);
        FontUtils.drawString((this.plusHovered ? "§a" : "§7") + "+", this.field_146128_h - this.plusWidth, this.field_146129_i, false);
    }
    
    public boolean func_146116_c(final Minecraft mc, final int mouseX, final int mouseY) {
        if (this.plusHovered || this.minusHovered) {
            if (this.plusHovered) {
                this.setting.set(this.setting.get(Integer.class) + this.setting.step);
            }
            if (this.minusHovered) {
                this.setting.set(this.setting.get(Integer.class) - this.setting.step);
            }
            this.updateText();
            return true;
        }
        return false;
    }
    
    public void updateText() {
        this.field_146126_j = ((this.setting.prefix == null) ? "" : this.setting.prefix) + this.setting.get(Integer.class) + ((this.setting.suffix == null) ? "" : this.setting.suffix);
        this.field_146120_f = FontUtils.getStringWidth(this.field_146126_j) + this.plusWidth + this.minusWidth + this.gap * 2;
    }
}
