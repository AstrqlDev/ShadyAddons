// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.features.autoterminals.solvers;

import net.minecraft.item.ItemStack;
import java.util.Iterator;
import cheaters.get.banned.utils.Utils;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.init.Blocks;
import cheaters.get.banned.Shady;
import cheaters.get.banned.features.autoterminals.ClickQueue;
import net.minecraft.inventory.Slot;
import java.util.List;
import cheaters.get.banned.features.autoterminals.SolverBase;

public class MazeSolver extends SolverBase
{
    @Override
    public ClickQueue getClicks(final List<Slot> inventory, final String chestName) {
        final ClickQueue clickQueue = new ClickQueue();
        for (final Slot slot : inventory) {
            if (slot != null) {
                if (slot.func_75211_c() == null) {
                    continue;
                }
                if (slot.field_75224_c == Shady.mc.field_71439_g.field_71071_by) {
                    continue;
                }
                if (slot.func_75211_c().func_77952_i() == 5) {
                    continue;
                }
                final int[] mazeDirection = { -9, -1, 1, 9 };
                final boolean[] isStartSlot = new boolean[54];
                int endSlot = -1;
                if (slot.func_75211_c().func_77973_b() == Item.func_150898_a((Block)Blocks.field_150397_co)) {
                    if (slot.func_75211_c().func_77952_i() == 5) {
                        isStartSlot[slot.field_75222_d] = true;
                    }
                    else if (slot.func_75211_c().func_77952_i() == 14) {
                        endSlot = slot.field_75222_d;
                    }
                }
                for (int slotIndex = 0; slotIndex < 54; ++slotIndex) {
                    if (isStartSlot[slotIndex]) {
                        final boolean[] mazeVisited = new boolean[54];
                        int startSlot = slotIndex;
                        while (startSlot != endSlot) {
                            boolean newSlotChosen = false;
                            for (final int i : mazeDirection) {
                                final int nextSlot = startSlot + i;
                                if (nextSlot >= 0 && nextSlot <= 53 && (i != -1 || startSlot % 9 != 0)) {
                                    if (i != 1 || startSlot % 9 != 8) {
                                        if (nextSlot == endSlot) {
                                            return null;
                                        }
                                        if (!mazeVisited[nextSlot]) {
                                            final ItemStack itemStack = inventory.get(nextSlot).func_75211_c();
                                            if (itemStack != null) {
                                                if (itemStack.func_77973_b() == Item.func_150898_a((Block)Blocks.field_150397_co) && itemStack.func_77952_i() == 0) {
                                                    clickQueue.middleClick(inventory.get(nextSlot));
                                                    startSlot = nextSlot;
                                                    mazeVisited[nextSlot] = true;
                                                    newSlotChosen = true;
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            if (!newSlotChosen) {
                                Utils.log("Maze calculation aborted");
                            }
                        }
                    }
                }
            }
        }
        return clickQueue;
    }
}
