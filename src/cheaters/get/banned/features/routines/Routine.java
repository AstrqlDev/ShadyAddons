// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.features.routines;

import java.util.Iterator;
import cheaters.get.banned.features.routines.actions.Action;
import java.util.ArrayList;
import cheaters.get.banned.features.routines.triggers.Trigger;

public class Routine
{
    public String name;
    public boolean allowConcurrent;
    public Trigger trigger;
    public ArrayList<Action> actions;
    private boolean isRunning;
    
    public Routine() {
        this.actions = new ArrayList<Action>();
        this.isRunning = false;
    }
    
    public void doActions() {
        if (!this.isRunning || this.allowConcurrent) {
            this.isRunning = true;
            final Iterator<Action> iterator;
            Action action;
            int times;
            int i;
            new Thread(() -> {
                this.actions.iterator();
                while (iterator.hasNext()) {
                    action = iterator.next();
                    try {
                        times = action.getRepeat();
                        if (times == 1) {
                            action.doAction();
                        }
                        else {
                            for (i = 0; i < times; ++i) {
                                action.doAction();
                            }
                        }
                    }
                    catch (RoutineRuntimeException exception) {
                        exception.display();
                    }
                    catch (Exception exception2) {
                        RoutineRuntimeException.javaException(exception2);
                    }
                }
                this.isRunning = false;
            }, "ShadyAddons-Routine-" + this.name).start();
        }
    }
}
