// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.utils;

import net.minecraft.util.AxisAlignedBB;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.util.Vec3;
import org.lwjgl.util.vector.Vector3f;
import cheaters.get.banned.Shady;
import net.minecraft.util.BlockPos;

public class RayMarchUtils
{
    public static boolean isFacingBlock(final BlockPos block, final float range) {
        final float stepSize = 0.15f;
        if (Shady.mc.field_71439_g != null && Shady.mc.field_71441_e != null) {
            final Vector3f position = new Vector3f((float)Shady.mc.field_71439_g.field_70165_t, (float)Shady.mc.field_71439_g.field_70163_u + Shady.mc.field_71439_g.func_70047_e(), (float)Shady.mc.field_71439_g.field_70161_v);
            final Vec3 look = Shady.mc.field_71439_g.func_70676_i(0.0f);
            final Vector3f step = new Vector3f((float)look.field_72450_a, (float)look.field_72448_b, (float)look.field_72449_c);
            step.scale(stepSize / step.length());
            for (int i = 0; i < Math.floor(range / stepSize) - 2.0; ++i) {
                final BlockPos blockAtPos = new BlockPos((double)position.x, (double)position.y, (double)position.z);
                if (blockAtPos.equals((Object)block)) {
                    return true;
                }
                position.translate(step.x, step.y, step.z);
            }
        }
        return false;
    }
    
    public static <T extends Entity> List<T> getFacedEntityOfType(final Class<T> _class, final float range) {
        final float stepSize = 0.5f;
        if (Shady.mc.field_71439_g != null && Shady.mc.field_71441_e != null) {
            final Vector3f position = new Vector3f((float)Shady.mc.field_71439_g.field_70165_t, (float)Shady.mc.field_71439_g.field_70163_u + Shady.mc.field_71439_g.func_70047_e(), (float)Shady.mc.field_71439_g.field_70161_v);
            final Vec3 look = Shady.mc.field_71439_g.func_70676_i(0.0f);
            final Vector3f step = new Vector3f((float)look.field_72450_a, (float)look.field_72448_b, (float)look.field_72449_c);
            step.scale(stepSize / step.length());
            for (int i = 0; i < Math.floor(range / stepSize) - 2.0; ++i) {
                final List<T> entities = (List<T>)Shady.mc.field_71441_e.func_72872_a((Class)_class, new AxisAlignedBB(position.x - 0.5, position.y - 0.5, position.z - 0.5, position.x + 0.5, position.y + 0.5, position.z + 0.5));
                if (!entities.isEmpty()) {
                    return entities;
                }
                position.translate(step.x, step.y, step.z);
            }
        }
        return null;
    }
}
