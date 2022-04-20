// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.features.autoterminals.solvers;

import java.util.Collection;
import java.util.Arrays;
import net.minecraft.item.ItemStack;
import java.util.Iterator;
import cheaters.get.banned.features.autoterminals.ClickQueue;
import net.minecraft.inventory.Slot;
import java.util.List;
import java.util.ArrayList;
import cheaters.get.banned.features.autoterminals.SolverBase;

public class ToggleColorSolver extends SolverBase
{
    private static final ArrayList<Integer> colorOrder;
    
    @Override
    public ClickQueue getClicks(final List<Slot> inventory, final String chestName) {
        return null;
    }
    
    private static int getTargetColorIndex(final List<Slot> slots) {
        if (slots.isEmpty()) {
            return 15;
        }
        float sum = 0.0f;
        for (final Slot slot : slots) {
            if (slot == null) {
                continue;
            }
            final ItemStack stack = slot.func_75211_c();
            if (stack == null) {
                continue;
            }
            sum += ToggleColorSolver.colorOrder.indexOf(stack.func_77952_i());
        }
        final int index = Math.round(sum / slots.size());
        return (ToggleColorSolver.colorOrder.size() > index) ? index : -1;
    }
    
    static {
        colorOrder = new ArrayList<Integer>(Arrays.asList(14, 1, 4, 13, 11));
    }
}
