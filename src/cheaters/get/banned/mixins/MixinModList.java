// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.mixins;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import cheaters.get.banned.Shady;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraftforge.fml.common.ModContainer;
import java.util.List;
import org.spongepowered.asm.mixin.Shadow;
import java.util.Map;
import net.minecraftforge.fml.common.network.handshake.FMLHandshakeMessage;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = { FMLHandshakeMessage.ModList.class }, remap = false)
public abstract class MixinModList
{
    @Shadow
    private Map<String, String> modTags;
    
    @Inject(method = { "<init>(Ljava/util/List;)V" }, at = { @At("RETURN") }, remap = false)
    private void hideModId(final List<ModContainer> modList, final CallbackInfo ci) {
        if (!Shady.mc.func_71387_A()) {
            this.modTags.remove("autogg");
        }
    }
}
