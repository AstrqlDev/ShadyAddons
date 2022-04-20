// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.features.routines;

import cheaters.get.banned.utils.Utils;
import cheaters.get.banned.Shady;
import java.lang.reflect.InvocationTargetException;

public class RoutineRuntimeException extends Exception
{
    public RoutineRuntimeException(final String message) {
        super(message);
    }
    
    public static void javaException(Throwable exception) {
        if (exception instanceof InvocationTargetException) {
            exception = exception.getCause();
        }
        exception.printStackTrace();
        new RoutineRuntimeException("Java exception '" + exception.getClass().getSimpleName() + "' in routine. Is Shady up to date?").display();
    }
    
    public void display() {
        System.out.println(this.getMessage());
        if (Shady.mc.field_71441_e != null) {
            Utils.sendModMessage("§c" + this.getMessage());
        }
    }
}
