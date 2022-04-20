// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.features;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.item.ItemStack;
import cheaters.get.banned.Shady;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.client.gui.inventory.GuiChest;
import cheaters.get.banned.gui.config.Config;
import cheaters.get.banned.utils.Utils;
import net.minecraftforge.client.event.GuiOpenEvent;

public class RoyalPigeonMacro
{
    @SubscribeEvent
    public void onGuiOpen(final GuiOpenEvent event) {
        if (Utils.inSkyBlock && Config.royalPigeonMacro && event.gui instanceof GuiChest) {
            final String chestName = ((ContainerChest)((GuiChest)event.gui).field_147002_h).func_85151_d().func_145748_c_().func_150260_c();
            if (chestName.contains("Commissions") && Shady.mc.field_71439_g.func_70694_bm().func_82833_r().contains("Royal Pigeon")) {
                for (int i = 0; i < 9; ++i) {
                    final ItemStack item = Shady.mc.field_71439_g.field_71071_by.func_70301_a(i);
                    if (item != null && item.func_82833_r().contains("Refined")) {
                        Shady.mc.field_71439_g.field_71071_by.field_70461_c = i;
                        break;
                    }
                }
            }
        }
    }
}
