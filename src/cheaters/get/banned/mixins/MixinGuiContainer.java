// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.mixins;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import net.minecraftforge.fml.common.eventhandler.Event;
import cheaters.get.banned.events.DrawSlotEvent;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.inventory.Slot;
import org.spongepowered.asm.mixin.Shadow;
import net.minecraft.inventory.Container;
import net.minecraft.client.gui.inventory.GuiContainer;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ GuiContainer.class })
public abstract class MixinGuiContainer
{
    @Shadow
    public Container field_147002_h;
    
    @Inject(method = { "drawSlot" }, at = { @At("HEAD") }, cancellable = true)
    private void beforeDrawSlot(final Slot slot, final CallbackInfo callbackInfo) {
        if (MinecraftForge.EVENT_BUS.post((Event)new DrawSlotEvent(this.field_147002_h, slot))) {
            callbackInfo.cancel();
        }
    }
}
