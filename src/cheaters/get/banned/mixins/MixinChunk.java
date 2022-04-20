// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.mixins;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import net.minecraftforge.fml.common.eventhandler.Event;
import cheaters.get.banned.events.BlockChangeEvent;
import net.minecraftforge.common.MinecraftForge;
import cheaters.get.banned.Shady;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.Shadow;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.chunk.Chunk;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ Chunk.class })
public abstract class MixinChunk
{
    @Shadow
    public abstract IBlockState func_177435_g(final BlockPos p0);
    
    @Inject(method = { "setBlockState" }, at = { @At("HEAD") })
    private void onBlockChange(final BlockPos position, final IBlockState newBlock, final CallbackInfoReturnable<IBlockState> callbackInfoReturnable) {
        final IBlockState oldBlock = this.func_177435_g(position);
        if (oldBlock != newBlock && Shady.mc.field_71441_e != null) {
            try {
                MinecraftForge.EVENT_BUS.post((Event)new BlockChangeEvent(position, oldBlock, newBlock));
            }
            catch (Exception ex) {}
        }
    }
}
