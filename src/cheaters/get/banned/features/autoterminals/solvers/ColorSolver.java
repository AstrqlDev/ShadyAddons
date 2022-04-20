// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.features.autoterminals.solvers;

import net.minecraft.item.ItemStack;
import java.util.Iterator;
import cheaters.get.banned.Shady;
import net.minecraft.item.EnumDyeColor;
import cheaters.get.banned.features.autoterminals.ClickQueue;
import net.minecraft.inventory.Slot;
import java.util.List;
import cheaters.get.banned.features.autoterminals.SolverBase;

public class ColorSolver extends SolverBase
{
    @Override
    public ClickQueue getClicks(final List<Slot> inventory, final String chestName) {
        final ClickQueue clickQueue = new ClickQueue();
        String colorNeeded = null;
        for (final EnumDyeColor color : EnumDyeColor.values()) {
            final String colorName = color.func_176610_l().replaceAll("_", " ").toUpperCase();
            if (chestName.contains(colorName)) {
                colorNeeded = color.func_176762_d();
                break;
            }
        }
        if (colorNeeded != null) {
            for (final Slot slot : inventory) {
                if (slot.field_75224_c == Shady.mc.field_71439_g.field_71071_by) {
                    continue;
                }
                if (slot.field_75222_d < 9 || slot.field_75222_d > 44 || slot.field_75222_d % 9 == 0) {
                    continue;
                }
                if (slot.field_75222_d % 9 == 8) {
                    continue;
                }
                final ItemStack itemStack = slot.func_75211_c();
                if (itemStack == null) {
                    return null;
                }
                if (itemStack.func_77948_v()) {
                    continue;
                }
                if (!itemStack.func_77977_a().contains(colorNeeded)) {
                    continue;
                }
                clickQueue.middleClick(slot);
            }
        }
        return clickQueue;
    }
}
