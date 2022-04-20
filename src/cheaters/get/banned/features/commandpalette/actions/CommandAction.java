// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.features.commandpalette.actions;

import cheaters.get.banned.utils.Utils;

public class CommandAction implements IAction
{
    private String command;
    
    public CommandAction(final String command) {
        this.command = command;
    }
    
    @Override
    public void execute() {
        Utils.executeCommand(this.command);
    }
    
    public String getCommand() {
        return this.command;
    }
}
