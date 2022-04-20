// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionUtils
{
    public static boolean invoke(final Object object, final String methodName) {
        try {
            final Method method = object.getClass().getDeclaredMethod(methodName, (Class<?>[])new Class[0]);
            method.setAccessible(true);
            method.invoke(object, new Object[0]);
            return true;
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    public static Object field(final Object object, final String name) {
        try {
            final Field field = object.getClass().getDeclaredField(name);
            field.setAccessible(true);
            return field.get(object);
        }
        catch (Exception ex) {
            return null;
        }
    }
}
