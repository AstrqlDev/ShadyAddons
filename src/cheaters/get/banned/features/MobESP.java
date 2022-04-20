// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.features;

import net.minecraftforge.event.world.WorldEvent;
import java.util.List;
import cheaters.get.banned.utils.OutlineUtils;
import cheaters.get.banned.gui.config.settings.FolderSetting;
import net.minecraft.entity.item.EntityArmorStand;
import cheaters.get.banned.events.RenderEntityModelEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.monster.EntitySlime;
import cheaters.get.banned.utils.LocationUtils;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.player.EntityPlayer;
import cheaters.get.banned.gui.config.Config;
import cheaters.get.banned.utils.Utils;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import java.util.HashSet;
import java.awt.Color;
import net.minecraft.entity.Entity;
import java.util.HashMap;

public class MobESP
{
    private static HashMap<Entity, Color> highlightedEntities;
    private static HashSet<Entity> checkedStarNameTags;
    
    private static void highlightEntity(final Entity entity, final Color color) {
        MobESP.highlightedEntities.put(entity, color);
    }
    
    @SubscribeEvent
    public void onEntityJoinWorld(final EntityJoinWorldEvent event) {
        if (Utils.inDungeon) {
            if (Config.minibossEsp && event.entity instanceof EntityPlayer) {
                final String func_70005_c_;
                final String name = func_70005_c_ = event.entity.func_70005_c_();
                switch (func_70005_c_) {
                    case "Shadow Assassin": {
                        event.entity.func_82142_c(false);
                        highlightEntity(event.entity, Color.MAGENTA);
                        break;
                    }
                    case "Lost Adventurer": {
                        highlightEntity(event.entity, Color.BLUE);
                        break;
                    }
                    case "Diamond Guy": {
                        highlightEntity(event.entity, Color.CYAN);
                        break;
                    }
                }
            }
            if (Config.secretBatEsp && event.entity instanceof EntityBat) {
                highlightEntity(event.entity, Color.RED);
            }
        }
        if (Utils.inSkyBlock && LocationUtils.onIsland(LocationUtils.Island.CRYSTAL_HOLLOWS)) {
            if (Config.sludgeEsp && event.entity instanceof EntitySlime && !(event.entity instanceof EntityMagmaCube)) {
                highlightEntity(event.entity, Color.GREEN);
            }
            if (Config.yogEsp && event.entity instanceof EntityMagmaCube) {
                highlightEntity(event.entity, Color.RED);
            }
            if (Config.corleoneEsp && event.entity instanceof EntityOtherPlayerMP && event.entity.func_70005_c_().equals("Team Treasurite")) {
                final float health = ((EntityOtherPlayerMP)event.entity).func_110138_aP();
                if (health == 1000000.0f || health == 2000000.0f) {
                    highlightEntity(event.entity, Color.PINK);
                }
            }
        }
    }
    
    @SubscribeEvent
    public void onRenderEntityModel(final RenderEntityModelEvent event) {
        if (Utils.inDungeon && !MobESP.checkedStarNameTags.contains(event.entity) && Config.starredMobEsp && event.entity instanceof EntityArmorStand && event.entity.func_145818_k_() && event.entity.func_95999_t().contains("\u272f")) {
            final List<Entity> possibleEntities = (List<Entity>)event.entity.func_130014_f_().func_175674_a((Entity)event.entity, event.entity.func_174813_aQ().func_72314_b(0.0, 3.0, 0.0), entity -> !(entity instanceof EntityArmorStand));
            if (!possibleEntities.isEmpty()) {
                highlightEntity(possibleEntities.get(0), Color.ORANGE);
            }
            MobESP.checkedStarNameTags.add((Entity)event.entity);
        }
        if (FolderSetting.isEnabled("Mob ESP") && !MobESP.highlightedEntities.isEmpty() && MobESP.highlightedEntities.containsKey(event.entity)) {
            OutlineUtils.outlineEntity(event, MobESP.highlightedEntities.get(event.entity));
        }
    }
    
    @SubscribeEvent
    public void onWorldLoad(final WorldEvent.Load event) {
        MobESP.highlightedEntities.clear();
        MobESP.checkedStarNameTags.clear();
    }
    
    static {
        MobESP.highlightedEntities = new HashMap<Entity, Color>();
        MobESP.checkedStarNameTags = new HashSet<Entity>();
    }
}
