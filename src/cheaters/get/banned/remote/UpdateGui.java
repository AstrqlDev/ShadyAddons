// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.remote;

import java.awt.Color;
import cheaters.get.banned.utils.FontUtils;
import cheaters.get.banned.utils.RenderUtils;
import cheaters.get.banned.Shady;
import net.minecraft.client.gui.GuiMainMenu;
import cheaters.get.banned.utils.Utils;
import net.minecraft.client.gui.GuiButton;
import cheaters.get.banned.gui.ClearButton;
import net.minecraft.util.ResourceLocation;
import net.minecraft.client.gui.GuiScreen;

public class UpdateGui extends GuiScreen
{
    private static final ResourceLocation backgroundImage;
    
    public void func_73866_w_() {
        this.field_146292_n.add(new ClearButton(1, this.field_146294_l / 2 - 105, this.field_146295_m / 2 + 10, 100, 20, "Dismiss"));
        this.field_146292_n.add(new ClearButton(0, this.field_146294_l / 2 + 5, this.field_146295_m / 2 + 10, 100, 20, "Download"));
    }
    
    public void func_146284_a(final GuiButton button) {
        if (button.field_146127_k == 0) {
            Utils.openUrl("https://shadyaddons.com/download/latest");
        }
        Shady.guiToOpen = (GuiScreen)new GuiMainMenu();
    }
    
    public void func_73863_a(final int mouseX, final int mouseY, final float partialTicks) {
        super.func_146276_q_();
        final float ratio = 1.7727273f;
        float bgWidth = (float)this.field_146294_l;
        float bgHeight = (float)this.field_146295_m;
        if (this.field_146294_l / ratio < this.field_146295_m) {
            bgHeight = (float)this.field_146295_m;
            bgWidth = this.field_146295_m * ratio;
        }
        else {
            bgHeight = this.field_146294_l / ratio;
            bgWidth = (float)this.field_146294_l;
        }
        RenderUtils.drawTexture(UpdateGui.backgroundImage, 0, 0, Math.round(bgWidth), Math.round(bgHeight));
        final String title = "ShadyAddons §" + FontUtils.getRainbowCode('c') + Updater.update.version + "§f is available!";
        FontUtils.drawScaledCenteredString(title, 1.5f, this.field_146294_l / 2, this.field_146295_m / 2 - 15 - 5, true);
        if (Updater.update.description == null) {
            Updater.update.description = "§7§oNo update description";
        }
        final int descriptionWidth = this.field_146297_k.field_71466_p.func_78256_a(Updater.update.description);
        this.field_146297_k.field_71466_p.func_175063_a(Updater.update.description, (this.field_146294_l - descriptionWidth) / 2.0f, this.field_146295_m / 2.0f - 7.0f, Color.WHITE.getRGB());
        super.func_73863_a(mouseX, mouseY, partialTicks);
    }
    
    static {
        backgroundImage = new ResourceLocation("shadyaddons:background.jpg");
    }
}
