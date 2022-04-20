// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.features.autoterminals.solvers;

import net.minecraft.item.ItemStack;
import java.util.Iterator;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.init.Blocks;
import cheaters.get.banned.Shady;
import cheaters.get.banned.features.autoterminals.ClickQueue;
import net.minecraft.inventory.Slot;
import java.util.List;
import cheaters.get.banned.features.autoterminals.SolverBase;

public class CorrectPanesSolver extends SolverBase
{
    @Override
    public ClickQueue getClicks(final List<Slot> inventory, final String chestName) {
        final ClickQueue clickQueue = new ClickQueue();
        for (final Slot slot : inventory) {
            if (slot.field_75224_c == Shady.mc.field_71439_g.field_71071_by) {
                continue;
            }
            if (slot.field_75222_d < 9 || slot.field_75222_d > 35 || slot.field_75222_d % 9 <= 1) {
                continue;
            }
            if (slot.field_75222_d % 9 >= 7) {
                continue;
            }
            final ItemStack itemStack = slot.func_75211_c();
            if (itemStack == null) {
                return null;
            }
            if (itemStack.func_77973_b() != Item.func_150898_a((Block)Blocks.field_150397_co) || itemStack.func_77952_i() != 14) {
                continue;
            }
            clickQueue.middleClick(slot);
        }
        return clickQueue;
    }
}
