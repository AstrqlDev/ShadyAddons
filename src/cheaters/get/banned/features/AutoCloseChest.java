// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.features;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import cheaters.get.banned.stats.MiscStats;
import cheaters.get.banned.Shady;
import cheaters.get.banned.gui.config.Config;
import cheaters.get.banned.utils.Utils;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraftforge.client.event.GuiScreenEvent;

public class AutoCloseChest
{
    @SubscribeEvent
    public void onGuiBackgroundRender(final GuiScreenEvent.BackgroundDrawnEvent event) {
        if (event.gui instanceof GuiChest && Utils.inSkyBlock) {
            final String chestName = Utils.getGuiName(event.gui);
            if (Utils.inDungeon && Config.closeSecretChests && chestName.equals("Chest")) {
                Shady.mc.field_71439_g.func_71053_j();
                MiscStats.add(MiscStats.Metric.CHESTS_CLOSED);
            }
            if (Utils.inSkyBlock && Config.closeCrystalHollowsChests && (chestName.contains("Loot Chest") || chestName.contains("Treasure Chest"))) {
                Shady.mc.field_71439_g.func_71053_j();
                MiscStats.add(MiscStats.Metric.CHESTS_CLOSED);
            }
        }
    }
}
