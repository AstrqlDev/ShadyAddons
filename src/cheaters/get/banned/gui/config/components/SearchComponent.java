// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.gui.config.components;

import net.minecraft.util.ChatAllowedCharacters;
import cheaters.get.banned.utils.FontUtils;
import cheaters.get.banned.utils.RenderUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

public class SearchComponent extends GuiButton
{
    public String text;
    
    public SearchComponent(final int x, final int y, final int width, final String initText) {
        super(0, x, y, width, 18, "");
        this.text = initText;
    }
    
    public void func_146112_a(final Minecraft mc, final int mouseX, final int mouseY) {
        RenderUtils.drawTexture(new ResourceLocation("shadyaddons:search.png"), this.field_146128_h, this.field_146129_i + 2, 14, 14);
        func_73734_a(this.field_146128_h + 20, this.field_146129_i, this.field_146128_h + this.field_146120_f + 15, this.field_146129_i + this.field_146121_g, ConfigInput.transparent.getRGB());
        FontUtils.drawScaledString(this.text, 1.25f, this.field_146128_h + 26, this.field_146129_i + 4, false);
        final int textWidth = (int)(FontUtils.getStringWidth(this.text) * 1.25f);
        if (System.currentTimeMillis() / 500L % 2L == 0L) {
            func_73734_a(this.field_146128_h + textWidth + 20 + 1, this.field_146129_i + 2, this.field_146128_h + textWidth + 26 + 2, this.field_146129_i + this.field_146121_g - 2, ConfigInput.white.getRGB());
        }
    }
    
    public void onKeyTyped(final char letter, final int code) {
        if (code == 14) {
            this.text = "";
        }
        else if (ChatAllowedCharacters.func_71566_a(letter) && FontUtils.getStringWidth(this.text + letter) * 1.25 <= this.field_146120_f - 2) {
            this.text += Character.toLowerCase(letter);
        }
    }
}
