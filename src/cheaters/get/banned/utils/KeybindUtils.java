// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.utils;

import cheaters.get.banned.Shady;
import net.minecraft.client.settings.KeyBinding;
import java.util.HashMap;

public class KeybindUtils
{
    public static HashMap<String, KeyBinding> keyBindings;
    
    public static void register(final String name, final int key) {
        KeybindUtils.keyBindings.put(name, new KeyBinding(name, key, "ShadyAddons"));
    }
    
    public static boolean isPressed(final String name) {
        return get(name).func_151468_f();
    }
    
    public static KeyBinding get(final String name) {
        return KeybindUtils.keyBindings.get(name);
    }
    
    public static void rightClick() {
        if (!ReflectionUtils.invoke(Shady.mc, "func_147121_ag")) {
            ReflectionUtils.invoke(Shady.mc, "rightClickMouse");
        }
    }
    
    public static void leftClick() {
        if (!ReflectionUtils.invoke(Shady.mc, "func_147116_af")) {
            ReflectionUtils.invoke(Shady.mc, "clickMouse");
        }
    }
    
    public static void middleClick() {
        if (!ReflectionUtils.invoke(Shady.mc, "func_147112_ai")) {
            ReflectionUtils.invoke(Shady.mc, "middleClickMouse");
        }
    }
    
    static {
        KeybindUtils.keyBindings = new HashMap<String, KeyBinding>();
    }
}
