// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.gui.config;

import java.lang.reflect.Type;
import java.io.Reader;
import java.util.Map;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import com.google.gson.Gson;
import cheaters.get.banned.gui.config.settings.DoNotSave;
import cheaters.get.banned.Shady;
import java.util.HashMap;
import java.util.Iterator;
import java.lang.reflect.Field;
import java.util.Collection;
import cheaters.get.banned.gui.config.settings.ParentSetting;
import cheaters.get.banned.gui.config.settings.SpacerSetting;
import cheaters.get.banned.gui.config.settings.FolderSetting;
import cheaters.get.banned.gui.config.settings.ButtonSetting;
import cheaters.get.banned.gui.config.settings.SelectSetting;
import cheaters.get.banned.gui.config.settings.NumberSetting;
import cheaters.get.banned.gui.config.settings.BooleanSetting;
import cheaters.get.banned.gui.config.settings.Setting;
import java.util.ArrayList;
import java.util.List;

public class ConfigLogic
{
    private static String fileName;
    
    public static ArrayList<Setting> collect(final Class<Config> instance, final List<String> disabledFeatures) {
        final ArrayList<Setting> settings = new ArrayList<Setting>();
        final Field[] declaredFields;
        final Field[] fields = declaredFields = instance.getDeclaredFields();
        for (final Field field : declaredFields) {
            final Property annotation = field.getAnnotation(Property.class);
            if (annotation != null) {
                switch (annotation.type()) {
                    case BOOLEAN:
                    case CHECKBOX: {
                        if (field.getType() == Boolean.TYPE || field.getType() == Boolean.class) {
                            settings.add(new BooleanSetting(annotation, field, annotation.type()));
                            break;
                        }
                        throw new TypeMismatchException("type boolean or Boolean", field.getType().getName());
                    }
                    case NUMBER: {
                        if (field.getType() == Integer.TYPE || field.getType() == Integer.class) {
                            settings.add(new NumberSetting(annotation, field));
                            break;
                        }
                        throw new TypeMismatchException("type int or Integer", field.getType().getName());
                    }
                    case SELECT: {
                        if (field.getType() == Integer.TYPE || field.getType() == Integer.class) {
                            settings.add(new SelectSetting(annotation, field));
                            break;
                        }
                        throw new TypeMismatchException("type int or Integer", field.getType().getName());
                    }
                    case BUTTON: {
                        if (field.getType() == Runnable.class) {
                            settings.add(new ButtonSetting(annotation, field));
                            break;
                        }
                        throw new TypeMismatchException("type Runnable", field.getType().getName());
                    }
                    case FOLDER: {
                        if (field.getType() == Boolean.TYPE || field.getType() == Boolean.class) {
                            settings.add(new FolderSetting(annotation, field));
                            break;
                        }
                        throw new TypeMismatchException("type boolean or Boolean", field.getType().getName());
                    }
                    case SPACER: {
                        settings.add(new SpacerSetting(annotation, field));
                        break;
                    }
                }
            }
        }
        final ArrayList<Setting> settingsToRemove = new ArrayList<Setting>();
        for (final Setting setting : settings) {
            if (disabledFeatures.contains(setting.name)) {
                settingsToRemove.add(setting);
            }
            else if (settingsToRemove.contains(setting.parent)) {
                settingsToRemove.add(setting);
            }
            else {
                if (setting.annotation.parent().equals("")) {
                    continue;
                }
                setting.parent = (ParentSetting)getSettingByName(setting.annotation.parent(), settings);
                if (setting.parent == null) {
                    continue;
                }
                setting.parent.children.add(setting);
            }
        }
        settings.removeAll(settingsToRemove);
        return settings;
    }
    
    public static Setting getSettingByName(final String name, final ArrayList<Setting> settings) {
        for (final Setting setting : settings) {
            if (setting.name.equals(name)) {
                return setting;
            }
        }
        return null;
    }
    
    public static Setting getSettingByFieldName(final String fieldName, final ArrayList<Setting> settings) {
        for (final Setting setting : settings) {
            if (!(setting instanceof ButtonSetting) && setting.field.getName().equals(fieldName)) {
                return setting;
            }
        }
        return null;
    }
    
    public static void save() {
        try {
            final HashMap<String, Object> settingsToSave = new HashMap<String, Object>();
            for (final Setting setting : Shady.settings) {
                if (setting instanceof DoNotSave) {
                    continue;
                }
                settingsToSave.put(setting.field.getName(), setting.get(Object.class));
            }
            final String json = new Gson().toJson((Object)settingsToSave);
            Files.write(Paths.get(ConfigLogic.fileName, new String[0]), json.getBytes(StandardCharsets.UTF_8), new OpenOption[0]);
        }
        catch (Exception error) {
            System.out.println("Error saving config file");
            error.printStackTrace();
        }
    }
    
    public static void load() {
        try {
            final File file = new File(ConfigLogic.fileName);
            if (file.exists()) {
                final Reader reader = Files.newBufferedReader(Paths.get(ConfigLogic.fileName, new String[0]));
                final Type type = new TypeToken<HashMap<String, Object>>() {}.getType();
                final HashMap<String, Object> settingsFromConfig = (HashMap<String, Object>)new Gson().fromJson(reader, type);
                for (final Map.Entry<String, Object> fromConfig : settingsFromConfig.entrySet()) {
                    Setting beingUpdated = getSettingByFieldName(fromConfig.getKey(), Shady.settings);
                    if (beingUpdated != null) {
                        if (beingUpdated instanceof NumberSetting || beingUpdated instanceof SelectSetting) {
                            beingUpdated.set(fromConfig.getValue().intValue());
                        }
                        else {
                            beingUpdated.forceSet(fromConfig.getValue());
                        }
                    }
                    else {
                        beingUpdated = getSettingByName(fromConfig.getKey(), Shady.settings);
                        if (beingUpdated == null) {
                            continue;
                        }
                        if (beingUpdated instanceof NumberSetting || beingUpdated instanceof SelectSetting) {
                            beingUpdated.set(fromConfig.getValue().intValue());
                        }
                        else {
                            beingUpdated.forceSet(fromConfig.getValue());
                        }
                    }
                }
            }
        }
        catch (Exception error) {
            System.out.println("Error while loading config file");
            error.printStackTrace();
        }
    }
    
    static {
        ConfigLogic.fileName = "config/ShadyAddons.cfg";
    }
}
