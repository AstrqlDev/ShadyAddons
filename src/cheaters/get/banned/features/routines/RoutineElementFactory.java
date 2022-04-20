// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.features.routines;

import cheaters.get.banned.features.routines.actions.Action;
import cheaters.get.banned.features.routines.triggers.Trigger;

public class RoutineElementFactory
{
    public static Trigger createTrigger(final String name, final RoutineElementData data) throws RoutineRuntimeException {
        try {
            return (Trigger)Class.forName("cheaters.get.banned.features.routines.triggers." + name + "Trigger").getConstructor(RoutineElementData.class).newInstance(data);
        }
        catch (Exception exception) {
            RoutineRuntimeException.javaException(exception);
            throw new RoutineRuntimeException("Error creating trigger with name '" + name + "Trigger'");
        }
    }
    
    public static Action createAction(final String name, final RoutineElementData data) throws RoutineRuntimeException {
        try {
            return (Action)Class.forName("cheaters.get.banned.features.routines.actions." + name + "Action").getConstructor(RoutineElementData.class).newInstance(data);
        }
        catch (Exception exception) {
            RoutineRuntimeException.javaException(exception);
            throw new RoutineRuntimeException("Error creating action with name '" + name + "Action'");
        }
    }
}
