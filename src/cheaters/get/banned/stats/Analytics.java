// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.stats;

import java.io.InputStream;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModContainer;
import cheaters.get.banned.utils.HttpUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.client.utils.URIBuilder;
import cheaters.get.banned.Shady;
import java.util.UUID;

public class Analytics
{
    private static final String whyCollectAnalyics = "I collect analytics for my own personal data projects. All data is stored anonymously. Your username is only sent to verify your data with Mojang's servers. See https://wiki.vg/Protocol_Encryption#Authentication for more information.";
    private static final String howAreTheySent = "This system allows Shady to verify the authenticity of your data WITHOUT your session ID. This is the same process that Minecraft servers (net.minecraft.client.network.NetHandlerLoginClient) use to make sure you are who you say you are. Optifine (net.optifine.gui.GuiScreenCapeOF) and Skytils (skytils.skytilsmod.features.impl.handlers.MayorInfo.kt) do the exact same thing.";
    private static final String pleaseDontCollectAnalytics = "In exchange for your safe, free, and relatively high-quality block game cheats, I'd like to collect a little information for personal data science projects. Sorry.";
    
    public static void collect(final String key, final String value) {
        final String serverId;
        URIBuilder url;
        new Thread(() -> {
            serverId = UUID.randomUUID().toString().replace("-", "");
            try {
                Shady.mc.func_152347_ac().joinServer(Shady.mc.func_110432_I().func_148256_e(), Shady.mc.func_110432_I().func_148254_d(), serverId);
            }
            catch (Exception ignored) {
                return;
            }
            try {
                url = new URIBuilder("https://shadyaddons.com/api/analytics").addParameter("username", Shady.mc.func_110432_I().func_111285_a()).addParameter("server_id", serverId).addParameter("hashed_uuid", DigestUtils.sha256Hex(Shady.mc.func_110432_I().func_148256_e().getId().toString().replace("-", ""))).addParameter(key, value);
                HttpUtils.get(url.toString());
            }
            catch (Exception ex) {}
        }, "ShadyAddons-Analytics").start();
    }
    
    public static String hashMod() {
        final ModContainer mod = Loader.instance().getIndexedModList().get("autogg");
        if (mod != null) {
            final File file = mod.getSource();
            if (file != null) {
                try {
                    final InputStream inputStream = Files.newInputStream(file.toPath(), new OpenOption[0]);
                    return DigestUtils.sha256Hex(inputStream);
                }
                catch (Exception ex) {}
            }
        }
        return null;
    }
}
