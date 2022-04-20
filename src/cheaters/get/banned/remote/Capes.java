// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.remote;

import java.awt.image.BufferedImage;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.ImageReader;
import javax.imageio.spi.ImageReaderSpi;
import cheaters.get.banned.utils.PatchedGIFReader;
import net.minecraft.client.renderer.texture.DynamicTexture;
import javax.imageio.ImageIO;
import cheaters.get.banned.Shady;
import java.nio.file.Files;
import java.nio.file.CopyOption;
import java.net.URL;
import java.util.Iterator;
import com.google.gson.JsonElement;
import java.util.Map;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import cheaters.get.banned.utils.HttpUtils;
import cheaters.get.banned.utils.Utils;
import org.apache.commons.codec.digest.DigestUtils;
import net.minecraft.client.network.NetworkPlayerInfo;
import java.io.File;
import java.util.ArrayList;
import net.minecraft.util.ResourceLocation;
import java.util.HashMap;

public class Capes
{
    private static HashMap<String, String> playerCapes;
    private static HashMap<String, ResourceLocation> capes;
    private static HashMap<String, ArrayList<ResourceLocation>> animatedCapes;
    public static final File capesDir;
    
    public static ResourceLocation getCape(final NetworkPlayerInfo player) {
        final String userHash = DigestUtils.md5Hex(player.func_178845_a().getId().toString().replace("-", ""));
        final String capeName = Capes.playerCapes.get(userHash);
        if (capeName == null) {
            return null;
        }
        if (capeName.endsWith("_anim")) {
            return getAnimatedCape(capeName);
        }
        return Capes.capes.get(capeName);
    }
    
    private static ResourceLocation getAnimatedCape(final String capeName) {
        final ArrayList<ResourceLocation> capeFrames = Capes.animatedCapes.get(capeName);
        if (capeFrames == null) {
            return null;
        }
        return capeFrames.get((int)(System.currentTimeMillis() / 83L % capeFrames.size()));
    }
    
    public static void load() {
        Utils.log("Downloading capes...");
        Capes.capesDir.mkdirs();
        try {
            final String capeJson = HttpUtils.get("https://shadyaddons.com/api/capes");
            final JsonObject json = (JsonObject)new Gson().fromJson(capeJson, (Class)JsonObject.class);
            final JsonObject jsonCapes = json.get("capes").getAsJsonObject();
            final JsonObject jsonOwners = json.get("purchased").getAsJsonObject();
            for (final Map.Entry<String, JsonElement> element : jsonCapes.entrySet()) {
                final String capeName = element.getKey();
                final String capeUrl = element.getValue().getAsString();
                Utils.log("Loading cape: " + capeName + " (" + capeUrl + ")");
                if (capeName.endsWith("_anim")) {
                    Capes.animatedCapes.put(capeName, animatedCapeFromFile(capeName, capeUrl));
                }
                else {
                    Capes.capes.put(capeName, capeFromFile(capeName, capeUrl));
                }
            }
            for (final Map.Entry<String, JsonElement> capeOwner : jsonOwners.entrySet()) {
                Capes.playerCapes.put(capeOwner.getKey(), capeOwner.getValue().getAsString());
            }
        }
        catch (Exception exception) {
            Utils.log("Error downloading capes");
            exception.printStackTrace();
        }
    }
    
    private static ResourceLocation capeFromFile(final String capeName, final String capeUrl) {
        try {
            final File file = new File(Capes.capesDir, capeName + ".png");
            if (!file.exists()) {
                Files.copy(new URL(capeUrl).openStream(), file.toPath(), new CopyOption[0]);
            }
            return Shady.mc.func_110434_K().func_110578_a("shadyaddons", new DynamicTexture(ImageIO.read(file)));
        }
        catch (Exception exception) {
            Utils.log("Error loading cape from file/URL");
            exception.printStackTrace();
            return null;
        }
    }
    
    private static ArrayList<ResourceLocation> animatedCapeFromFile(final String capeName, final String capeUrl) {
        try {
            final File file = new File(Capes.capesDir, capeName + ".gif");
            if (!file.exists()) {
                Files.copy(new URL(capeUrl).openStream(), file.toPath(), new CopyOption[0]);
            }
            final ImageReader reader = new PatchedGIFReader(null);
            final ImageInputStream stream = ImageIO.createImageInputStream(file);
            reader.setInput(stream);
            final ArrayList<ResourceLocation> frames = new ArrayList<ResourceLocation>();
            for (int frameCount = reader.getNumImages(true), i = 0; i < frameCount; ++i) {
                final BufferedImage frame = reader.read(i);
                final ResourceLocation frameImage = Shady.mc.func_110434_K().func_110578_a("shadyaddons", new DynamicTexture(frame));
                frames.add(frameImage);
            }
            return frames.isEmpty() ? null : frames;
        }
        catch (Exception exception) {
            Utils.log("Error loading animated cape from file/URL");
            exception.printStackTrace();
            return null;
        }
    }
    
    static {
        Capes.playerCapes = new HashMap<String, String>();
        Capes.capes = new HashMap<String, ResourceLocation>();
        Capes.animatedCapes = new HashMap<String, ArrayList<ResourceLocation>>();
        capesDir = new File(Shady.dir, "cape-cache");
    }
}
