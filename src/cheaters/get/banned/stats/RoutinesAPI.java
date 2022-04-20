// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.stats;

import org.apache.http.client.utils.URIBuilder;
import java.util.UUID;
import cheaters.get.banned.utils.Utils;
import cheaters.get.banned.Shady;
import org.apache.commons.io.FileUtils;
import cheaters.get.banned.features.routines.Routines;
import java.io.File;
import cheaters.get.banned.utils.HttpUtils;

public class RoutinesAPI
{
    public static void download(final String id) {
        String response;
        final File file2;
        File file;
        new Thread(() -> {
            try {
                response = HttpUtils.get("https://shadyaddons.com/api/routines/download?id=" + id);
                if (response != null) {
                    new File(Routines.routinesDir, id + ".json");
                    file = file2;
                    FileUtils.writeStringToFile(file, response);
                    Routines.load();
                    if (Shady.mc.field_71441_e != null) {
                        Utils.sendModMessage("&aInstalled and loaded routine!");
                    }
                }
                else if (Shady.mc.field_71441_e != null) {
                    Utils.sendModMessage("&cA routine with that ID does not exist!");
                }
            }
            catch (Exception ex) {}
        }, "ShadyAddons-InstallRoutineAPI").start();
    }
    
    public static void openAuthWebsite() {
        try {
            final String serverId = UUID.randomUUID().toString().replace("-", "");
            Shady.mc.func_152347_ac().joinServer(Shady.mc.func_110432_I().func_148256_e(), Shady.mc.func_110432_I().func_148254_d(), serverId);
            final URIBuilder url = new URIBuilder("https://shadyaddons.com/auth").addParameter("username", Shady.mc.func_110432_I().func_111285_a()).addParameter("redirect", "routines").addParameter("server_id", serverId);
            Utils.openUrl(url.toString());
        }
        catch (Exception ex) {}
    }
}
