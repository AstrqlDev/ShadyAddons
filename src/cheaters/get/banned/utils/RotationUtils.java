// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.utils;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import cheaters.get.banned.Shady;
import net.minecraft.util.BlockPos;

public class RotationUtils
{
    private static float pitchDifference;
    public static float yawDifference;
    private static int ticks;
    private static int tickCounter;
    private static Runnable callback;
    
    private static double wrapAngleTo180(final double angle) {
        return angle - Math.floor(angle / 360.0 + 0.5) * 360.0;
    }
    
    public static Rotation getRotationToBlock(final BlockPos block) {
        final double diffX = block.func_177958_n() - Shady.mc.field_71439_g.field_70165_t + 0.5;
        final double diffY = block.func_177956_o() - Shady.mc.field_71439_g.field_70163_u + 0.5 - Shady.mc.field_71439_g.func_70047_e();
        final double diffZ = block.func_177952_p() - Shady.mc.field_71439_g.field_70161_v + 0.5;
        final double dist = Math.sqrt(diffX * diffX + diffZ * diffZ);
        float pitch = (float)(-Math.atan2(dist, diffY));
        float yaw = (float)Math.atan2(diffZ, diffX);
        pitch = (float)wrapAngleTo180((pitch * 180.0f / 3.141592653589793 + 90.0) * -1.0);
        yaw = (float)wrapAngleTo180(yaw * 180.0f / 3.141592653589793 - 90.0);
        return new Rotation(pitch, yaw);
    }
    
    public static void smoothLook(final Rotation rotation, final int ticks, final Runnable callback) {
        if (ticks == 0) {
            look(rotation);
            if (callback != null) {
                callback.run();
            }
            return;
        }
        RotationUtils.callback = callback;
        RotationUtils.pitchDifference = rotation.pitch - Shady.mc.field_71439_g.field_70125_A;
        RotationUtils.yawDifference = rotation.yaw - Shady.mc.field_71439_g.field_70177_z;
        RotationUtils.ticks = ticks * 20;
        RotationUtils.tickCounter = 0;
    }
    
    public static void smartLook(final Rotation rotation, final int ticksPer180, final Runnable callback) {
        final float rotationDifference = Math.max(Math.abs(rotation.pitch - Shady.mc.field_71439_g.field_70125_A), Math.abs(rotation.yaw - Shady.mc.field_71439_g.field_70177_z));
        smoothLook(rotation, (int)(rotationDifference / 180.0f * ticksPer180), callback);
    }
    
    public static void look(final Rotation rotation) {
        Shady.mc.field_71439_g.field_70125_A = rotation.pitch;
        Shady.mc.field_71439_g.field_70177_z = rotation.yaw;
    }
    
    @SubscribeEvent
    public void onTick(final TickEvent event) {
        if (RotationUtils.tickCounter < RotationUtils.ticks) {
            final EntityPlayerSP field_71439_g = Shady.mc.field_71439_g;
            field_71439_g.field_70125_A += RotationUtils.pitchDifference / RotationUtils.ticks;
            final EntityPlayerSP field_71439_g2 = Shady.mc.field_71439_g;
            field_71439_g2.field_70177_z += RotationUtils.yawDifference / RotationUtils.ticks;
            ++RotationUtils.tickCounter;
        }
        else if (RotationUtils.callback != null) {
            RotationUtils.callback.run();
            RotationUtils.callback = null;
        }
    }
    
    static {
        RotationUtils.ticks = -1;
        RotationUtils.tickCounter = 0;
        RotationUtils.callback = null;
    }
    
    public static class Rotation
    {
        public float pitch;
        public float yaw;
        
        public Rotation(final float pitch, final float yaw) {
            this.pitch = pitch;
            this.yaw = yaw;
        }
    }
}
