// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.gui.routines;

import java.awt.Color;
import net.minecraft.client.gui.GuiButton;
import java.util.Iterator;
import cheaters.get.banned.Shady;
import cheaters.get.banned.features.routines.Routine;
import cheaters.get.banned.features.routines.Routines;
import net.minecraft.client.gui.GuiScreen;

public class RoutinesGui extends GuiScreen
{
    private static final int wth = 300;
    private static final int borderColor;
    
    public void func_73863_a(final int mouseX, final int mouseY, final float partialTicks) {
        super.func_73863_a(mouseX, mouseY, partialTicks);
        for (final Routine value : Routines.routines.values()) {
            new RoutineComponent(value, 0, 0).func_146112_a(Shady.mc, mouseX, mouseY);
        }
    }
    
    public void func_73866_w_() {
        int yOffset = 50;
        final int xOffset = (this.field_146294_l - 300) / 2;
        for (final Routine routine : Routines.routines.values()) {
            this.field_146292_n.add(new RoutineComponent(routine, xOffset, yOffset));
            yOffset += 9;
            func_73734_a(xOffset, yOffset, yOffset + 300, yOffset + 1, RoutinesGui.borderColor);
            ++yOffset;
        }
    }
    
    protected void func_146284_a(final GuiButton button) {
    }
    
    public boolean func_73868_f() {
        return false;
    }
    
    static {
        borderColor = new Color(255, 255, 255, 64).getRGB();
    }
}
