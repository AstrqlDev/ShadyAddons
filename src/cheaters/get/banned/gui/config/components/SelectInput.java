// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.gui.config.components;

import net.minecraft.client.Minecraft;
import cheaters.get.banned.utils.FontUtils;
import cheaters.get.banned.gui.config.settings.Setting;
import cheaters.get.banned.gui.config.settings.SelectSetting;

public class SelectInput extends ConfigInput
{
    private int leftWidth;
    private int rightWidth;
    private int gap;
    private boolean leftHovered;
    private boolean rightHovered;
    public SelectSetting setting;
    
    public SelectInput(final SelectSetting setting, final int x, final int y) {
        super(setting, x, y);
        this.leftWidth = FontUtils.getStringWidth("<");
        this.rightWidth = FontUtils.getStringWidth(">");
        this.gap = 3;
        this.leftHovered = false;
        this.rightHovered = false;
        this.setting = setting;
        this.field_146121_g = 10;
        this.updateText();
    }
    
    public void func_146112_a(final Minecraft mc, final int mouseX, final int mouseY) {
        this.rightHovered = (mouseX >= this.field_146128_h - this.rightWidth - this.gap && mouseY >= this.field_146129_i && mouseX < this.field_146128_h && mouseY < this.field_146129_i + this.field_146121_g);
        this.leftHovered = (mouseX >= this.field_146128_h - this.field_146120_f && mouseY >= this.field_146129_i && mouseX < this.field_146128_h - this.field_146120_f + this.leftWidth + this.gap && mouseY < this.field_146129_i + this.field_146121_g);
        FontUtils.drawString((this.leftHovered ? "§a" : "§7") + "<", this.field_146128_h - this.field_146120_f, this.field_146129_i, false);
        FontUtils.drawString(this.field_146126_j, this.field_146128_h - this.field_146120_f + this.leftWidth + this.gap, this.field_146129_i, false);
        FontUtils.drawString((this.rightHovered ? "§a" : "§7") + ">", this.field_146128_h - this.rightWidth, this.field_146129_i, false);
    }
    
    public boolean func_146116_c(final Minecraft mc, final int mouseX, final int mouseY) {
        if (this.rightHovered || this.leftHovered) {
            if (this.rightHovered) {
                this.setting.set(this.setting.get(Integer.class) + 1);
            }
            if (this.leftHovered) {
                this.setting.set(this.setting.get(Integer.class) - 1);
            }
            this.updateText();
            return true;
        }
        return false;
    }
    
    public void updateText() {
        this.field_146126_j = this.setting.options[this.setting.get(Integer.class)];
        this.field_146120_f = FontUtils.getStringWidth(this.field_146126_j) + this.rightWidth + this.leftWidth + this.gap * 2;
    }
}
