// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.features;

import java.util.Collection;
import java.util.Arrays;
import java.util.List;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;
import java.util.function.Predicate;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import cheaters.get.banned.Shady;
import cheaters.get.banned.gui.config.Config;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraft.item.ItemStack;
import cheaters.get.banned.utils.Utils;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.Entity;
import java.util.ArrayList;

public class HideSummons
{
    private static final ArrayList<String> summonItemIDs;
    
    public static boolean isSummon(final Entity entity) {
        if (entity instanceof EntityPlayer) {
            return entity.func_70005_c_().equals("Lost Adventurer");
        }
        if (entity instanceof EntityZombie || entity instanceof EntitySkeleton) {
            for (int i = 0; i < 5; ++i) {
                final ItemStack item = ((EntityMob)entity).func_71124_b(i);
                if (HideSummons.summonItemIDs.contains(Utils.getSkyBlockID(item))) {
                    return true;
                }
            }
        }
        return false;
    }
    
    @SubscribeEvent
    public void onPreRenderEntity(final RenderLivingEvent.Pre<EntityLivingBase> event) {
        if (Utils.inSkyBlock && !Utils.inDungeon && isSummon((Entity)event.entity) && Config.hideSummons && event.entity.func_70032_d((Entity)Shady.mc.field_71439_g) < 5.0f) {
            event.setCanceled(true);
        }
    }
    
    @SubscribeEvent
    public void onAttack(final AttackEntityEvent event) {
        if (Config.clickThroughSummons && Utils.inSkyBlock && !Utils.inDungeon && isSummon(event.target)) {
            final Entity excludedEntity = Shady.mc.func_175606_aa();
            final double reach = Shady.mc.field_71442_b.func_78757_d();
            final Vec3 look = excludedEntity.func_70676_i(0.0f);
            final AxisAlignedBB boundingBox = excludedEntity.func_174813_aQ().func_72321_a(look.field_72450_a * reach, look.field_72448_b * reach, look.field_72449_c * reach).func_72314_b(1.0, 1.0, 1.0);
            final List<Entity> entitiesInRange = (List<Entity>)Shady.mc.field_71441_e.func_72839_b(excludedEntity, boundingBox);
            entitiesInRange.removeIf(entity -> !entity.func_70067_L());
            entitiesInRange.removeIf(HideSummons::isSummon);
            if (entitiesInRange.size() > 0) {
                event.setCanceled(true);
                Shady.mc.field_71439_g.func_71038_i();
                Shady.mc.field_71442_b.func_78764_a((EntityPlayer)Shady.mc.field_71439_g, (Entity)entitiesInRange.get(0));
            }
        }
    }
    
    static {
        summonItemIDs = new ArrayList<String>(Arrays.asList("HEAVY_HELMET", "ZOMBIE_KNIGHT_HELMET", "SKELETOR_HELMET"));
    }
}
