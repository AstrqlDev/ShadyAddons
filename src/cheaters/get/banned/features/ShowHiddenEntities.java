// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.features;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import cheaters.get.banned.utils.LocationUtils;
import cheaters.get.banned.utils.ScoreboardUtils;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.player.EntityPlayer;
import cheaters.get.banned.utils.Utils;
import net.minecraft.entity.monster.EntityEnderman;
import cheaters.get.banned.gui.config.Config;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.client.event.RenderLivingEvent;

public class ShowHiddenEntities
{
    @SubscribeEvent
    public void onBeforeRenderEntity(final RenderLivingEvent.Pre<EntityLivingBase> event) {
        if (event.entity.func_82150_aj()) {
            if (Config.showHiddenFels && event.entity instanceof EntityEnderman) {
                event.entity.func_82142_c(false);
            }
            if (Utils.inDungeon && event.entity instanceof EntityPlayer) {
                if (Config.showHiddenShadowAssassins && event.entity.func_70005_c_().contains("Shadow Assassin")) {
                    event.entity.func_82142_c(false);
                }
                if (Config.showStealthyBloodMobs) {
                    for (final String name : new String[] { "Revoker", "Psycho", "Reaper", "Cannibal", "Mute", "Ooze", "Putrid", "Freak", "Leech", "Tear", "Parasite", "Flamer", "Skull", "Mr. Dead", "Vader", "Frost", "Walker", "Wandering Soul", "Bonzo", "Scarf", "Livid" }) {
                        if (event.entity.func_70005_c_().contains(name)) {
                            event.entity.func_82142_c(false);
                            break;
                        }
                    }
                }
            }
            if (Config.showGhosts && event.entity instanceof EntityCreeper && ScoreboardUtils.scoreboardContains("The Mist")) {
                event.entity.func_82142_c(false);
            }
            if (Config.showSneakyCreepers && event.entity instanceof EntityCreeper && LocationUtils.onIsland(LocationUtils.Island.DEEP_CAVERNS)) {
                event.entity.func_82142_c(false);
            }
        }
    }
}
