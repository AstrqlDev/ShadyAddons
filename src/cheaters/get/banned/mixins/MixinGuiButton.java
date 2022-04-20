// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.mixins;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import cheaters.get.banned.utils.FontUtils;
import net.minecraft.client.gui.Gui;
import java.awt.Color;
import cheaters.get.banned.gui.config.Config;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Shadow;
import net.minecraft.client.gui.GuiButton;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ GuiButton.class })
public abstract class MixinGuiButton
{
    @Shadow
    public int field_146127_k;
    @Shadow
    public int field_146128_h;
    @Shadow
    public int field_146129_i;
    @Shadow
    public String field_146126_j;
    @Shadow
    public int field_146120_f;
    @Shadow
    public int field_146121_g;
    @Shadow
    public boolean field_146125_m;
    @Shadow
    protected boolean field_146123_n;
    
    @Inject(method = { "drawButton" }, at = { @At("HEAD") }, cancellable = true)
    public void drawCleanButton(final Minecraft mc, final int mouseX, final int mouseY, final CallbackInfo callbackInfo) {
        if (Config.useCleanButtons) {
            if (this.field_146125_m) {
                this.field_146123_n = (mouseX >= this.field_146128_h && mouseY >= this.field_146129_i && mouseX < this.field_146128_h + this.field_146120_f && mouseY < this.field_146129_i + this.field_146121_g);
                final Color color = this.field_146123_n ? new Color(30, 30, 30, 64) : new Color(0, 0, 0, 64);
                Gui.func_73734_a(this.field_146128_h, this.field_146129_i, this.field_146128_h + this.field_146120_f, this.field_146129_i + this.field_146121_g, color.getRGB());
                FontUtils.drawCenteredString(this.field_146126_j, this.field_146128_h + this.field_146120_f / 2, this.field_146129_i + this.field_146121_g / 2);
            }
            callbackInfo.cancel();
        }
    }
}
