// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.features;

import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.util.Vec3;
import net.minecraft.util.AxisAlignedBB;
import org.lwjgl.util.vector.Vector3f;
import cheaters.get.banned.stats.MiscStats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.Entity;
import cheaters.get.banned.events.ClickEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import java.util.List;
import cheaters.get.banned.utils.RayMarchUtils;
import cheaters.get.banned.events.TickEndEvent;
import cheaters.get.banned.utils.DungeonUtils;
import cheaters.get.banned.Shady;
import cheaters.get.banned.utils.Utils;
import cheaters.get.banned.gui.config.Config;
import net.minecraft.entity.item.EntityEnderCrystal;

public class CrystalReach
{
    public static EntityEnderCrystal crystal;
    
    public static boolean isEnabled() {
        return Config.crystalReach && Utils.inDungeon && Shady.mc.field_71439_g != null && Shady.mc.field_71439_g.func_180425_c().func_177956_o() > 215 && DungeonUtils.inBoss && DungeonUtils.inFloor(DungeonUtils.Floor.FLOOR_7, DungeonUtils.Floor.MASTER_7);
    }
    
    @SubscribeEvent
    public void onTick(final TickEndEvent event) {
        if (isEnabled() && Shady.mc.field_71439_g.func_70093_af()) {
            final List<EntityEnderCrystal> crystals = RayMarchUtils.getFacedEntityOfType(EntityEnderCrystal.class, 32.0f);
            if (crystals != null) {
                CrystalReach.crystal = crystals.get(0);
            }
            else {
                CrystalReach.crystal = null;
            }
        }
        else {
            CrystalReach.crystal = null;
        }
    }
    
    @SubscribeEvent
    public void onRightClick(final ClickEvent.Right event) {
        if (isEnabled() && Shady.mc.field_71439_g.func_70093_af() && CrystalReach.crystal != null) {
            final List<Entity> armorStand = (List<Entity>)Shady.mc.field_71441_e.func_175674_a((Entity)CrystalReach.crystal, CrystalReach.crystal.func_174813_aQ(), entity -> entity instanceof EntityArmorStand && entity.func_95999_t().contains("CLICK HERE"));
            if (!armorStand.isEmpty() && armorStand.get(0) != null) {
                Shady.mc.field_71442_b.func_78768_b((EntityPlayer)Shady.mc.field_71439_g, (Entity)armorStand.get(0));
                MiscStats.add(MiscStats.Metric.CRYSTALS_REACHED);
                event.setCanceled(true);
            }
        }
    }
    
    private static EntityEnderCrystal lookingAtCrystal() {
        final float range = 32.0f;
        final float stepSize = 0.5f;
        if (Shady.mc.field_71439_g != null && Shady.mc.field_71441_e != null) {
            final Vector3f position = new Vector3f((float)Shady.mc.field_71439_g.field_70165_t, (float)Shady.mc.field_71439_g.field_70163_u + Shady.mc.field_71439_g.func_70047_e(), (float)Shady.mc.field_71439_g.field_70161_v);
            final Vec3 look = Shady.mc.field_71439_g.func_70676_i(0.0f);
            final Vector3f step = new Vector3f((float)look.field_72450_a, (float)look.field_72448_b, (float)look.field_72449_c);
            step.scale(stepSize / step.length());
            for (int i = 0; i < Math.floor(range / stepSize) - 2.0; ++i) {
                final List<EntityEnderCrystal> entities = (List<EntityEnderCrystal>)Shady.mc.field_71441_e.func_72872_a((Class)EntityEnderCrystal.class, new AxisAlignedBB(position.x - 0.5, position.y - 0.5, position.z - 0.5, position.x + 0.5, position.y + 0.5, position.z + 0.5));
                if (!entities.isEmpty()) {
                    return entities.get(0);
                }
                position.translate(step.x, step.y, step.z);
            }
        }
        return null;
    }
    
    static {
        CrystalReach.crystal = null;
    }
}
