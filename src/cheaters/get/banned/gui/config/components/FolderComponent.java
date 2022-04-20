// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.gui.config.components;

import cheaters.get.banned.utils.RenderUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.client.Minecraft;
import cheaters.get.banned.gui.config.settings.Setting;
import cheaters.get.banned.gui.config.settings.FolderSetting;

public class FolderComponent extends ConfigInput
{
    public FolderSetting setting;
    
    public FolderComponent(final FolderSetting setting, final int x, final int y) {
        super(setting, x, y);
        this.setting = setting;
        super.field_146120_f = 300;
        super.field_146121_g = 9;
        super.field_146128_h -= this.field_146120_f;
    }
    
    public void func_146112_a(final Minecraft mc, final int mouseX, final int mouseY) {
        this.field_146123_n = (mouseX >= this.field_146128_h && mouseY >= this.field_146129_i && mouseX < this.field_146128_h + this.field_146120_f && mouseY < this.field_146129_i + this.field_146121_g);
        RenderUtils.drawRotatedTexture(new ResourceLocation("shadyaddons:chevron.png"), this.field_146128_h + this.field_146120_f - this.field_146121_g, this.field_146129_i, this.field_146121_g, this.field_146121_g, ((boolean)this.setting.get(Boolean.class)) ? 180 : 0);
    }
    
    public boolean func_146116_c(final Minecraft mc, final int mouseX, final int mouseY) {
        if (this.field_146123_n) {
            this.setting.set(!this.setting.get(Boolean.class));
            return true;
        }
        return false;
    }
}
