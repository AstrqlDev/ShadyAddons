// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.features;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.inventory.Container;
import cheaters.get.banned.Shady;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraftforge.client.event.GuiScreenEvent;
import cheaters.get.banned.stats.MiscStats;
import cheaters.get.banned.utils.Utils;

public class AutoWardrobe
{
    private static int page;
    private static int slot;
    private static boolean shouldOpenWardrobe;
    
    public static void open(final int page, final int slot) {
        AutoWardrobe.page = page;
        AutoWardrobe.slot = slot;
        AutoWardrobe.shouldOpenWardrobe = true;
        Utils.executeCommand("/pets");
        MiscStats.add(MiscStats.Metric.WARDROBES_OPENED);
    }
    
    @SubscribeEvent
    public void onDrawGuiBackground(final GuiScreenEvent.BackgroundDrawnEvent event) {
        if (Utils.inSkyBlock && AutoWardrobe.shouldOpenWardrobe && event.gui instanceof GuiChest) {
            final Container container = ((GuiChest)event.gui).field_147002_h;
            if (container instanceof ContainerChest) {
                final String chestName = Utils.getGuiName(event.gui);
                if (chestName.endsWith("Pets")) {
                    this.clickSlot(48, 0, 0);
                    this.clickSlot(32, 0, 1);
                    if (AutoWardrobe.page == 1) {
                        if (AutoWardrobe.slot > 0 && AutoWardrobe.slot < 10) {
                            this.clickSlot(AutoWardrobe.slot + 35, 0, 2);
                            Shady.mc.field_71439_g.func_71053_j();
                        }
                    }
                    else if (AutoWardrobe.page == 2) {
                        this.clickSlot(53, 2, 2);
                        if (AutoWardrobe.slot > 0 && AutoWardrobe.slot < 10) {
                            this.clickSlot(AutoWardrobe.slot + 35, 0, 3);
                            Shady.mc.field_71439_g.func_71053_j();
                        }
                    }
                    AutoWardrobe.shouldOpenWardrobe = false;
                }
            }
        }
    }
    
    private void clickSlot(final int slot, final int type, final int incrementWindowId) {
        Shady.mc.field_71442_b.func_78753_a(Shady.mc.field_71439_g.field_71070_bA.field_75152_c + incrementWindowId, slot, type, 0, (EntityPlayer)Shady.mc.field_71439_g);
    }
    
    static {
        AutoWardrobe.page = 0;
        AutoWardrobe.slot = 0;
        AutoWardrobe.shouldOpenWardrobe = false;
    }
}
