// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.mixins;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import cheaters.get.banned.utils.FontUtils;
import cheaters.get.banned.remote.Updater;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.Shadow;
import net.minecraft.client.gui.GuiMainMenu;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ GuiMainMenu.class })
public abstract class MixinGuiMainMenu
{
    @Shadow
    private String field_73975_c;
    
    @Inject(method = { "<init>" }, at = { @At("RETURN") })
    public void initMainMenu(final CallbackInfo callbackInfo) {
        if (Updater.update != null && !Updater.update.version.equals("2.7.2")) {
            this.field_73975_c = "Update to §" + FontUtils.getRainbowCode('e') + Updater.update.version + "§e!";
        }
    }
}
