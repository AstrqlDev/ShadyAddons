// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.mixins;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import cheaters.get.banned.remote.Capes;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.Shadow;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.client.entity.AbstractClientPlayer;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = { AbstractClientPlayer.class }, priority = Integer.MAX_VALUE)
public class MixinAbstractClientPlayer
{
    @Shadow
    private NetworkPlayerInfo field_175157_a;
    
    @Inject(method = { "getLocationCape" }, at = { @At("RETURN") }, cancellable = true)
    public void getLocationCape(final CallbackInfoReturnable<ResourceLocation> cir) {
        final ResourceLocation cape = Capes.getCape(this.field_175157_a);
        if (cape != null) {
            cir.setReturnValue(cape);
        }
    }
}
