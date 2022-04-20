// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.mixins;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import cheaters.get.banned.utils.RenderUtils;
import java.awt.Color;
import cheaters.get.banned.Shady;
import net.minecraft.client.renderer.GlStateManager;
import cheaters.get.banned.features.CrystalReach;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.entity.Entity;
import net.minecraft.client.model.ModelEnderCrystal;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ ModelEnderCrystal.class })
public abstract class MixinModelEnderCrystal
{
    @Inject(method = { "render" }, at = { @At("HEAD") })
    public void preRender(final Entity entityIn, final float p_78088_2_, final float p_78088_3_, final float p_78088_4_, final float p_78088_5_, final float p_78088_6_, final float scale, final CallbackInfo ci) {
        if (CrystalReach.isEnabled()) {
            GlStateManager.func_179090_x();
            GlStateManager.func_179097_i();
            GlStateManager.func_179129_p();
            GlStateManager.func_179140_f();
            GlStateManager.func_179147_l();
            GlStateManager.func_179120_a(770, 771, 1, 0);
            RenderUtils.bindColor((entityIn.equals((Object)CrystalReach.crystal) && Shady.mc.field_71439_g.func_70093_af()) ? Color.MAGENTA : Color.WHITE);
        }
    }
    
    @Inject(method = { "render" }, at = { @At("RETURN") })
    public void postRender(final Entity entityIn, final float p_78088_2_, final float p_78088_3_, final float p_78088_4_, final float p_78088_5_, final float p_78088_6_, final float scale, final CallbackInfo ci) {
        if (CrystalReach.isEnabled()) {
            GlStateManager.func_179098_w();
            GlStateManager.func_179126_j();
            GlStateManager.func_179089_o();
            GlStateManager.func_179145_e();
            GlStateManager.func_179084_k();
        }
    }
}
