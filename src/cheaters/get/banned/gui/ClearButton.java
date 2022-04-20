// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.gui;

import cheaters.get.banned.utils.FontUtils;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

public class ClearButton extends GuiButton
{
    public boolean shadow;
    
    public ClearButton(final int buttonId, final int x, final int y, final String buttonText) {
        super(buttonId, x, y, buttonText);
        this.shadow = true;
    }
    
    public ClearButton(final int buttonId, final int x, final int y, final int widthIn, final int heightIn, final String buttonText) {
        super(buttonId, x, y, widthIn, heightIn, buttonText);
        this.shadow = true;
    }
    
    public void func_146112_a(final Minecraft mc, final int mouseX, final int mouseY) {
        if (this.field_146125_m) {
            this.field_146123_n = (mouseX >= this.field_146128_h && mouseY >= this.field_146129_i && mouseX < this.field_146128_h + this.field_146120_f && mouseY < this.field_146129_i + this.field_146121_g);
            final Color color = this.field_146123_n ? new Color(15, 15, 15, 64) : new Color(0, 0, 0, 64);
            func_73734_a(this.field_146128_h, this.field_146129_i, this.field_146128_h + this.field_146120_f, this.field_146129_i + this.field_146121_g, color.getRGB());
            FontUtils.drawCenteredString(this.field_146126_j, this.field_146128_h + this.field_146120_f / 2, this.field_146129_i + this.field_146121_g / 2, this.shadow);
        }
    }
}
