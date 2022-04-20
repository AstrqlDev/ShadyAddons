// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.features.autoterminals.solvers;

import net.minecraft.item.ItemStack;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.init.Blocks;
import cheaters.get.banned.features.autoterminals.ClickQueue;
import net.minecraft.inventory.Slot;
import java.util.List;
import cheaters.get.banned.features.autoterminals.SolverBase;

public class NumbersSolver extends SolverBase
{
    @Override
    public ClickQueue getClicks(final List<Slot> inventory, final String chestName) {
        final ClickQueue clickQueue = new ClickQueue();
        int min = 0;
        final Slot[] temp = new Slot[14];
        for (int i = 10; i <= 25; ++i) {
            if (i != 17) {
                if (i != 18) {
                    final ItemStack itemStack = inventory.get(i).func_75211_c();
                    if (itemStack != null) {
                        if (itemStack.func_77973_b() == Item.func_150898_a((Block)Blocks.field_150397_co) && itemStack.field_77994_a < 15) {
                            if (itemStack.func_77952_i() == 14) {
                                temp[itemStack.field_77994_a - 1] = inventory.get(i);
                            }
                            else if (itemStack.func_77952_i() == 5 && min < itemStack.field_77994_a) {
                                min = itemStack.field_77994_a;
                            }
                        }
                    }
                }
            }
        }
        for (final Slot slot : temp) {
            if (slot != null) {
                clickQueue.middleClick(slot);
            }
        }
        if (clickQueue.size() != 14 - min) {
            return null;
        }
        return clickQueue;
    }
}
