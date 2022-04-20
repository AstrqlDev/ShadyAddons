// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.features;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import cheaters.get.banned.gui.config.Config;
import net.minecraftforge.client.event.ClientChatReceivedEvent;

public class AutoRenewCrystalHollows
{
    @SubscribeEvent
    public void onChat(final ClientChatReceivedEvent event) {
        if (Config.renewCrystalHollowsPass && event.type == 0 && event.message.func_150260_c().equals("Your pass to the Crystal Hollows will expire in 1 minute")) {
            Minecraft.func_71410_x().field_71439_g.func_71165_d("/purchasecrystallhollowspass");
        }
    }
}
