// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.features;

import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import java.util.List;
import java.util.Iterator;
import net.minecraft.inventory.Slot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.Entity;
import cheaters.get.banned.Shady;
import cheaters.get.banned.utils.Utils;
import cheaters.get.banned.gui.config.Config;
import cheaters.get.banned.events.TickEndEvent;

public class AutoReadyUp
{
    private static boolean readyUp;
    private static boolean dungeonStarted;
    
    @SubscribeEvent
    public void onTick(final TickEndEvent event) {
        if (Config.autoReadyUp && Utils.inDungeon && !AutoReadyUp.dungeonStarted) {
            if (!AutoReadyUp.readyUp) {
                for (final Entity entity : Shady.mc.field_71441_e.func_72910_y()) {
                    if (entity instanceof EntityArmorStand && entity.func_145818_k_() && entity.func_95999_t().equals("§bMort")) {
                        final List<Entity> possibleEntities = (List<Entity>)entity.func_130014_f_().func_175674_a(entity, entity.func_174813_aQ().func_72314_b(0.0, 3.0, 0.0), e -> e instanceof EntityPlayer);
                        if (possibleEntities.isEmpty()) {
                            continue;
                        }
                        Shady.mc.field_71442_b.func_78768_b((EntityPlayer)Shady.mc.field_71439_g, (Entity)possibleEntities.get(0));
                        AutoReadyUp.readyUp = true;
                    }
                }
            }
            final String chestName = Utils.getInventoryName();
            if (AutoReadyUp.readyUp && chestName != null) {
                if (chestName.equals("Start Dungeon?")) {
                    Shady.mc.field_71442_b.func_78753_a(Shady.mc.field_71439_g.field_71070_bA.field_75152_c, 13, 2, 0, (EntityPlayer)Shady.mc.field_71439_g);
                    return;
                }
                if (chestName.startsWith("Catacombs - ")) {
                    for (final Slot slot : Shady.mc.field_71439_g.field_71070_bA.field_75151_b) {
                        if (slot.func_75211_c() != null && slot.func_75211_c().func_82833_r().contains(Shady.mc.field_71439_g.func_70005_c_())) {
                            Shady.mc.field_71442_b.func_78753_a(Shady.mc.field_71439_g.field_71070_bA.field_75152_c, slot.field_75222_d, 2, 0, (EntityPlayer)Shady.mc.field_71439_g);
                            Shady.mc.field_71439_g.func_71053_j();
                            break;
                        }
                    }
                }
            }
        }
    }
    
    @SubscribeEvent
    public void onChatMessage(final ClientChatReceivedEvent event) {
        if (!AutoReadyUp.dungeonStarted && event.message.func_150260_c().contains("Dungeon starts in 4 seconds.")) {
            AutoReadyUp.dungeonStarted = true;
        }
    }
    
    @SubscribeEvent
    public void onJoinWorld(final WorldEvent.Load event) {
        AutoReadyUp.readyUp = false;
        AutoReadyUp.dungeonStarted = false;
    }
    
    static {
        AutoReadyUp.readyUp = false;
        AutoReadyUp.dungeonStarted = false;
    }
}
