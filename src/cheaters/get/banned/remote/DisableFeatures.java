// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.remote;

import java.util.Collections;
import java.util.Arrays;
import com.google.gson.Gson;
import cheaters.get.banned.utils.HttpUtils;
import java.util.List;

public class DisableFeatures
{
    public static List<String> load() {
        final String url = "https://shadyaddons.com/api/disabled-features.json";
        String response = null;
        try {
            response = HttpUtils.get(url);
        }
        catch (Exception ex) {}
        if (response != null) {
            try {
                return Arrays.asList((String[])new Gson().fromJson(response, (Class)String[].class));
            }
            catch (Exception ex2) {}
        }
        return Collections.emptyList();
    }
}
