// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.features;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import cheaters.get.banned.utils.Utils;
import cheaters.get.banned.gui.config.Config;
import net.minecraftforge.client.event.ClientChatReceivedEvent;

public class AutoGG
{
    @SubscribeEvent
    public void onChat(final ClientChatReceivedEvent event) {
        if (Config.autoGg && !Utils.inSkyBlock && event.type == 0) {
            final String message = event.message.func_150254_d();
            if (message.contains("Reward Summary") && !message.contains(":") && !message.contains("]")) {
                Minecraft.func_71410_x().field_71439_g.func_71165_d("/ac gg");
            }
        }
    }
}
