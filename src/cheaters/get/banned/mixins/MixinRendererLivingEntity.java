// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.mixins;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import net.minecraftforge.fml.common.eventhandler.Event;
import cheaters.get.banned.events.RenderEntityModelEvent;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.entity.EntityLivingBase;
import org.spongepowered.asm.mixin.Shadow;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ RendererLivingEntity.class })
public abstract class MixinRendererLivingEntity
{
    @Shadow
    protected ModelBase field_77045_g;
    
    @Inject(method = { "renderModel" }, at = { @At("HEAD") }, cancellable = true)
    private void renderModel(final EntityLivingBase entity, final float limbSwing, final float limbSwingAmount, final float ageInTicks, final float netHeadYaw, final float headPitch, final float scaleFactor, final CallbackInfo callbackInfo) {
        if (MinecraftForge.EVENT_BUS.post((Event)new RenderEntityModelEvent(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, this.field_77045_g))) {
            callbackInfo.cancel();
        }
    }
}
