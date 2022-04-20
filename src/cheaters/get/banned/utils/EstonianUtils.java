// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.utils;

import java.nio.file.Files;
import java.nio.file.CopyOption;
import java.net.URL;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.util.Iterator;
import com.google.gson.JsonObject;
import com.google.gson.JsonElement;
import java.util.Map;
import com.google.gson.JsonParser;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import cheaters.get.banned.Shady;
import net.minecraft.util.ResourceLocation;
import java.util.Calendar;
import java.io.File;
import java.util.HashMap;

public class EstonianUtils
{
    private static HashMap<String, String> englishToEstonian;
    private static final File folkSongFile;
    
    public static boolean isEstoniaDay() {
        final int month = Calendar.getInstance().get(2);
        final int day = Calendar.getInstance().get(5);
        return month == 3 && day == 1;
    }
    
    public static void loadEstonian() {
        try {
            downloadFolkSong();
            final ResourceLocation estonianFile = new ResourceLocation("shadyaddons:estonian.json");
            final BufferedReader estonianReader = new BufferedReader(new InputStreamReader(Shady.mc.func_110442_L().func_110536_a(estonianFile).func_110527_b()));
            final JsonObject estonianJson = new JsonParser().parse((Reader)estonianReader).getAsJsonObject();
            for (final Map.Entry<String, JsonElement> pair : estonianJson.entrySet()) {
                EstonianUtils.englishToEstonian.put(pair.getKey(), pair.getValue().getAsString());
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }
    
    public static String replaceEstonian(final String notEstonian) {
        if (notEstonian == null) {
            return null;
        }
        for (final Map.Entry<String, String> pair : EstonianUtils.englishToEstonian.entrySet()) {
            final String estonian = notEstonian.replace(pair.getKey(), pair.getValue());
            if (!estonian.equals(notEstonian)) {
                return estonian;
            }
        }
        return notEstonian;
    }
    
    public static void playFolkSong() {
        try {
            if (!EstonianUtils.folkSongFile.exists()) {
                return;
            }
            final AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(EstonianUtils.folkSongFile);
            final Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }
    
    public static void downloadFolkSong() {
        if (EstonianUtils.folkSongFile.exists()) {
            return;
        }
        try {
            Files.copy(new URL("https://shadyaddons.com/assets/folk-music.wav").openStream(), EstonianUtils.folkSongFile.toPath(), new CopyOption[0]);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }
    
    static {
        EstonianUtils.englishToEstonian = new HashMap<String, String>();
        folkSongFile = new File(Shady.dir, "folk-music.wav");
    }
}
