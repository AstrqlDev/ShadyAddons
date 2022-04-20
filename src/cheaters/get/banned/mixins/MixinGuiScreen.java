// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.mixins;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.common.MinecraftForge;
import cheaters.get.banned.events.SendChatMessageEvent;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.client.gui.GuiScreen;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ GuiScreen.class })
public abstract class MixinGuiScreen
{
    @Inject(method = { "sendChatMessage(Ljava/lang/String;Z)V" }, at = { @At("HEAD") }, cancellable = true)
    private void onSendChatMessage(final String message, final boolean addToChat, final CallbackInfo ci) {
        try {
            final SendChatMessageEvent event = new SendChatMessageEvent(message, addToChat);
            if (MinecraftForge.EVENT_BUS.post((Event)event)) {
                ci.cancel();
            }
        }
        catch (Exception ex) {}
    }
}
