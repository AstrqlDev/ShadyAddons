// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.gui.config.components;

import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

public class Scrollbar extends GuiButton
{
    public Scrollbar(final int y, final int viewport, final int contentHeight, final int scrollOffset, final int x, final boolean hovered) {
        super(0, x, y, "");
        this.field_146129_i += Math.round(scrollOffset / (float)contentHeight * viewport);
        this.field_146120_f = 5;
        this.field_146121_g = ((contentHeight > viewport) ? Math.round(viewport / (float)contentHeight * viewport) : 0);
        this.field_146123_n = hovered;
    }
    
    public void func_146112_a(final Minecraft mc, final int mouseX, final int mouseY) {
        this.field_146123_n = this.func_146116_c(mc, mouseX, mouseY);
        func_73734_a(this.field_146128_h, this.field_146129_i, this.field_146128_h + this.field_146120_f, this.field_146129_i + this.field_146121_g, this.field_146123_n ? ConfigInput.white.getRGB() : ConfigInput.transparent.getRGB());
    }
    
    public boolean func_146116_c(final Minecraft mc, final int mouseX, final int mouseY) {
        return mouseX >= this.field_146128_h && mouseY >= this.field_146129_i && mouseX < this.field_146128_h + this.field_146120_f && mouseY < this.field_146129_i + this.field_146121_g;
    }
    
    public void func_146113_a(final SoundHandler soundHandlerIn) {
    }
}
