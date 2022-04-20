// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.features.commandpalette;

import cheaters.get.banned.features.commandpalette.actions.CommandAction;
import cheaters.get.banned.features.commandpalette.actions.IAction;
import cheaters.get.banned.features.commandpalette.icons.IIcon;

public class Result
{
    public String name;
    public IIcon icon;
    public IAction action;
    public String description;
    public boolean pin;
    
    public Result(final String name, final IIcon icon, final IAction action) {
        this.description = null;
        this.pin = false;
        this.name = name;
        this.icon = icon;
        this.action = action;
        if (action instanceof CommandAction) {
            this.description = ((CommandAction)action).getCommand();
        }
    }
    
    public Result(final String name, final IIcon icon, final IAction action, final String description) {
        this.description = null;
        this.pin = false;
        this.name = name;
        this.icon = icon;
        this.action = action;
        this.description = description;
    }
}
