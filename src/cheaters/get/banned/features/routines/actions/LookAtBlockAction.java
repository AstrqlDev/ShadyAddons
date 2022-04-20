// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.features.routines.actions;

import cheaters.get.banned.utils.RotationUtils;
import net.minecraft.util.BlockPos;
import cheaters.get.banned.features.routines.RoutineRuntimeException;
import cheaters.get.banned.features.routines.RoutineElementData;

public class LookAtBlockAction extends Action
{
    private int x;
    private int y;
    private int z;
    private int ticks;
    
    public LookAtBlockAction(final RoutineElementData data) throws RoutineRuntimeException {
        super(data);
        this.x = data.keyAsInt("x");
        this.y = data.keyAsInt("y");
        this.z = data.keyAsInt("z");
        this.ticks = data.keyAsInt("ticks_per_180");
    }
    
    @Override
    public void doAction() throws RoutineRuntimeException {
        final RotationUtils.Rotation rotation = RotationUtils.getRotationToBlock(new BlockPos(this.x, this.y, this.z));
        RotationUtils.smartLook(rotation, this.ticks, null);
    }
}
