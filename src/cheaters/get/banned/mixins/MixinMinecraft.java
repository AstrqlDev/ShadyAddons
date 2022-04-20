// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.mixins;

import cheaters.get.banned.events.ClickEvent;
import cheaters.get.banned.events.ResourcePackRefreshEvent;
import net.minecraft.client.renderer.texture.TextureManager;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import net.minecraftforge.fml.common.eventhandler.Event;
import cheaters.get.banned.events.ShutdownEvent;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Mutable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ Minecraft.class })
public abstract class MixinMinecraft
{
    @Mutable
    @Shadow
    @Final
    private static ResourceLocation field_110444_H;
    
    @Inject(method = { "shutdown" }, at = { @At("HEAD") })
    public void onShutdown(final CallbackInfo ci) {
        MinecraftForge.EVENT_BUS.post((Event)new ShutdownEvent());
    }
    
    @Inject(method = { "drawSplashScreen" }, at = { @At("HEAD") })
    public void modifyMojangLogo(final TextureManager textureManagerInstance, final CallbackInfo ci) {
        MixinMinecraft.field_110444_H = new ResourceLocation("shadyaddons:splash.png");
    }
    
    @Inject(method = { "refreshResources" }, at = { @At("HEAD") }, cancellable = true)
    public void refreshResourcesPre(final CallbackInfo ci) {
        if (MinecraftForge.EVENT_BUS.post((Event)new ResourcePackRefreshEvent.Pre())) {
            ci.cancel();
        }
    }
    
    @Inject(method = { "refreshResources" }, at = { @At("RETURN") })
    public void refreshResourcesPost(final CallbackInfo ci) {
        MinecraftForge.EVENT_BUS.post((Event)new ResourcePackRefreshEvent.Post());
    }
    
    @Inject(method = { "rightClickMouse" }, at = { @At("HEAD") }, cancellable = true)
    public void rightClickEvent(final CallbackInfo ci) {
        if (MinecraftForge.EVENT_BUS.post((Event)new ClickEvent.Right())) {
            ci.cancel();
        }
    }
    
    @Inject(method = { "clickMouse" }, at = { @At("HEAD") }, cancellable = true)
    public void leftClickEvent(final CallbackInfo ci) {
        if (MinecraftForge.EVENT_BUS.post((Event)new ClickEvent.Left())) {
            ci.cancel();
        }
    }
    
    @Inject(method = { "middleClickMouse" }, at = { @At("HEAD") }, cancellable = true)
    public void middleClickEvent(final CallbackInfo ci) {
        if (MinecraftForge.EVENT_BUS.post((Event)new ClickEvent.Middle())) {
            ci.cancel();
        }
    }
}
