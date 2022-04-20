// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.mixins;

import net.minecraft.network.play.server.S12PacketEntityVelocity;
import cheaters.get.banned.features.AntiKB;
import net.minecraft.network.play.server.S27PacketExplosion;
import cheaters.get.banned.features.NoRotate;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import net.minecraftforge.fml.common.eventhandler.Event;
import cheaters.get.banned.events.PacketEvent;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.network.Packet;
import net.minecraft.client.network.NetHandlerPlayClient;
import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.network.play.INetHandlerPlayClient;

@Mixin({ NetHandlerPlayClient.class })
public abstract class MixinNetHandlerPlayClient implements INetHandlerPlayClient
{
    @Inject(method = { "addToSendQueue" }, at = { @At("HEAD") }, cancellable = true)
    private void onSendPacket(final Packet<?> packet, final CallbackInfo callbackInfo) {
        if (MinecraftForge.EVENT_BUS.post((Event)new PacketEvent.SendEvent(packet))) {
            callbackInfo.cancel();
        }
    }
    
    @Inject(method = { "handlePlayerPosLook" }, at = { @At("HEAD") })
    public void handlePlayerPosLookPre(final S08PacketPlayerPosLook packetIn, final CallbackInfo ci) {
        NoRotate.handlePlayerPosLookPre();
    }
    
    @Inject(method = { "handlePlayerPosLook" }, at = { @At("RETURN") })
    public void handlePlayerPosLook(final S08PacketPlayerPosLook packetIn, final CallbackInfo ci) {
        NoRotate.handlePlayerPosLook(packetIn);
    }
    
    @Inject(method = { "handleExplosion" }, at = { @At("RETURN") })
    private void handleExplosion(final S27PacketExplosion packet, final CallbackInfo ci) {
        AntiKB.handleExplosion(packet);
    }
    
    @Inject(method = { "handleEntityVelocity" }, at = { @At("HEAD") }, cancellable = true)
    public void handleEntityVelocity(final S12PacketEntityVelocity packetIn, final CallbackInfo ci) {
        if (AntiKB.handleEntityVelocity(packetIn)) {
            ci.cancel();
        }
    }
}
