// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.features.routines;

import cheaters.get.banned.Shady;
import java.util.Iterator;
import com.google.gson.JsonObject;
import com.google.gson.JsonElement;
import org.apache.commons.io.FileUtils;
import com.google.gson.JsonParser;
import java.io.File;
import java.util.ArrayList;
import cheaters.get.banned.features.routines.triggers.Trigger;
import java.util.HashMap;

public class Routines
{
    public static HashMap<Trigger, Routine> routines;
    public static ArrayList<String> invalidRoutines;
    public static ArrayList<String> routinesJson;
    public static final File routinesDir;
    
    public static void load() {
        if (!Routines.routinesDir.exists()) {
            Routines.routinesDir.mkdirs();
        }
        Routines.routines.clear();
        Routines.routinesJson.clear();
        Routines.invalidRoutines.clear();
        final JsonParser parser = new JsonParser();
        final File[] files = Routines.routinesDir.listFiles(name -> name.isFile() && name.getName().endsWith(".json"));
        if (files == null) {
            return;
        }
        for (final File file : files) {
            Label_0339: {
                String jsonString;
                try {
                    jsonString = FileUtils.readFileToString(file);
                }
                catch (Exception exception) {
                    RoutineRuntimeException.javaException(exception);
                    break Label_0339;
                }
                final JsonObject json = parser.parse(jsonString).getAsJsonObject();
                if (json.get("enabled").getAsBoolean()) {
                    final Routine routine = new Routine();
                    final JsonElement routineNameJson = json.get("name");
                    if (routineNameJson != null) {
                        routine.name = routineNameJson.getAsString();
                        try {
                            routine.allowConcurrent = json.get("allow_concurrent").getAsBoolean();
                            final JsonObject triggerObject = json.get("trigger").getAsJsonObject();
                            routine.trigger = RoutineElementFactory.createTrigger(triggerObject.get("name").getAsString(), new RoutineElementData(triggerObject));
                            for (final JsonElement action : json.get("actions").getAsJsonArray()) {
                                final JsonObject actionObject = action.getAsJsonObject();
                                routine.actions.add(RoutineElementFactory.createAction(actionObject.get("name").getAsString(), new RoutineElementData(actionObject)));
                            }
                            Routines.routinesJson.add(jsonString);
                            Routines.routines.put(routine.trigger, routine);
                        }
                        catch (Exception exception2) {
                            Routines.invalidRoutines.add(routine.name);
                            exception2.printStackTrace();
                        }
                    }
                }
            }
        }
    }
    
    static {
        Routines.routines = new HashMap<Trigger, Routine>();
        Routines.invalidRoutines = new ArrayList<String>();
        Routines.routinesJson = new ArrayList<String>();
        routinesDir = new File(Shady.dir, "routines");
    }
}
