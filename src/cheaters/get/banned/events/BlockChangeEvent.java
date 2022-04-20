// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.events;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraftforge.fml.common.eventhandler.Event;

public class BlockChangeEvent extends Event
{
    public BlockPos position;
    public IBlockState oldBlock;
    public IBlockState newBlock;
    
    public BlockChangeEvent(final BlockPos position, final IBlockState oldBlock, final IBlockState newBlock) {
        this.position = position;
        this.oldBlock = oldBlock;
        this.newBlock = newBlock;
    }
}
