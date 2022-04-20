// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.mixins;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import net.minecraftforge.fml.common.eventhandler.Event;
import cheaters.get.banned.events.PacketEvent;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.network.NetworkManager;
import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.network.Packet;
import io.netty.channel.SimpleChannelInboundHandler;

@Mixin({ NetworkManager.class })
public abstract class MixinNetworkManager extends SimpleChannelInboundHandler<Packet<?>>
{
    @Inject(method = { "channelRead0*" }, at = { @At("HEAD") }, cancellable = true)
    private void onReceivePacket(final ChannelHandlerContext context, final Packet<?> packet, final CallbackInfo callbackInfo) {
        if (MinecraftForge.EVENT_BUS.post((Event)new PacketEvent.ReceiveEvent(packet))) {
            callbackInfo.cancel();
        }
    }
}
