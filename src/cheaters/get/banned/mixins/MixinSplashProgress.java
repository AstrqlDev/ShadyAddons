// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.mixins;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.SplashProgress;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ SplashProgress.class })
public abstract class MixinSplashProgress
{
    @ModifyVariable(method = { "start" }, at = @At("STORE"), ordinal = 1, remap = false)
    private static ResourceLocation setSplash(final ResourceLocation resourceLocation) {
        return new ResourceLocation("shadyaddons:splash.png");
    }
}
