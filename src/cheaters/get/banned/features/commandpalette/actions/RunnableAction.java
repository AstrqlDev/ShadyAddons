// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.features.commandpalette.actions;

public class RunnableAction implements IAction
{
    private Runnable runnable;
    
    public RunnableAction(final Runnable runnable) {
        this.runnable = runnable;
    }
    
    @Override
    public void execute() {
        this.runnable.run();
    }
}
