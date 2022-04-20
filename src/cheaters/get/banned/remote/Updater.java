// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.remote;

import com.google.gson.Gson;
import cheaters.get.banned.utils.HttpUtils;

public class Updater
{
    public static boolean shouldUpdate;
    public static Update update;
    
    public static void check() {
        final String url;
        String response;
        new Thread(() -> {
            url = "https://shadyaddons.com/api/updates";
            response = null;
            try {
                response = HttpUtils.get(url);
            }
            catch (Exception ex) {}
            if (response != null) {
                Updater.update = (Update)new Gson().fromJson(response, (Class)Update.class);
                Updater.shouldUpdate = !Updater.update.version.equals("2.7.2");
            }
            else {
                System.out.println("Error checking for updates");
            }
        }, "ShadyAddons-Updater").start();
    }
    
    static {
        Updater.shouldUpdate = false;
        Updater.update = null;
    }
    
    public static class Update
    {
        public String version;
        public String download;
        public String description;
        public int users;
        
        public Update(final String version, final String download, final String description, final int users) {
            this.version = version;
            this.download = download;
            this.description = description;
            this.users = users;
        }
    }
}
