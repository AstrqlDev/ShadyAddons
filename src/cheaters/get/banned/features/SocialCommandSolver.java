// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.features;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import cheaters.get.banned.utils.ThreadUtils;
import cheaters.get.banned.stats.MiscStats;
import cheaters.get.banned.utils.ExpressionParser;
import cheaters.get.banned.utils.LocationUtils;
import cheaters.get.banned.utils.Utils;
import cheaters.get.banned.gui.config.Config;
import net.minecraftforge.client.event.ClientChatReceivedEvent;

public class SocialCommandSolver
{
    @SubscribeEvent
    public void onChat(final ClientChatReceivedEvent event) {
        if (Config.socialQuickMathsSolver && event.type == 0 && ((Utils.inSkyBlock && LocationUtils.onIsland(LocationUtils.Island.PRIVATE_ISLAND)) || Config.enableMathsOutsideSkyBlock)) {
            String message = event.message.func_150260_c();
            if (message.startsWith("QUICK MATHS! Solve: ")) {
                message = message.replace("QUICK MATHS! Solve: ", "").replace("x", "*");
                final int answer = (int)ExpressionParser.eval(message);
                MiscStats.add(MiscStats.Metric.MATH_PROBLEMS_SOLVED);
                final int n;
                new Thread(() -> {
                    ThreadUtils.sleep(Config.quickMathsAnswerDelay);
                    Utils.sendModMessage("The answer is " + n);
                    Utils.sendMessageAsPlayer("/ac " + n);
                }).start();
            }
        }
    }
}
