// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.gui.routines;

import net.minecraft.client.Minecraft;
import cheaters.get.banned.features.routines.Routine;
import net.minecraft.client.gui.GuiButton;

public class RoutineComponent extends GuiButton
{
    private Routine routine;
    
    public RoutineComponent(final Routine routine, final int x, final int y) {
        super(0, x, y, 300, 9, routine.name);
        this.routine = routine;
    }
    
    public void func_146112_a(final Minecraft mc, final int mouseX, final int mouseY) {
        super.func_146112_a(mc, mouseX, mouseY);
    }
}
