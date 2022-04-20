// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.features;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import java.util.Iterator;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.StringUtils;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.function.Predicate;
import java.util.Objects;
import java.util.Arrays;
import java.util.Collection;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.init.Blocks;
import cheaters.get.banned.Shady;
import cheaters.get.banned.events.TickEndEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import java.util.List;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.client.gui.inventory.GuiChest;
import cheaters.get.banned.utils.Utils;
import cheaters.get.banned.gui.config.Config;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraft.inventory.Slot;
import java.util.ArrayList;

public class AutoTerminals
{
    private static final ArrayList<Slot> clickQueue;
    private static final int[] mazeDirection;
    private static TerminalType currentTerminal;
    private static int targetColorIndex;
    private static long lastClickTime;
    private static int windowId;
    private static int windowClicks;
    private static boolean recalculate;
    public static boolean testing;
    private static final ArrayList<Integer> colorOrder;
    
    @SubscribeEvent
    public void onGuiDraw(final GuiScreenEvent.BackgroundDrawnEvent event) {
        if (!Config.autoTerminals || !Utils.inDungeon) {
            return;
        }
        if (event.gui instanceof GuiChest) {
            final Container container = ((GuiChest)event.gui).field_147002_h;
            if (container instanceof ContainerChest) {
                final List<Slot> invSlots = (List<Slot>)container.field_75151_b;
                if (AutoTerminals.currentTerminal == TerminalType.NONE) {
                    final String chestName = ((ContainerChest)container).func_85151_d().func_145748_c_().func_150260_c();
                    Utils.log("Chest name: " + chestName);
                    if (chestName.equals("Navigate the maze!")) {
                        AutoTerminals.currentTerminal = TerminalType.MAZE;
                    }
                    else if (chestName.equals("Click in order!")) {
                        AutoTerminals.currentTerminal = TerminalType.NUMBERS;
                    }
                    else if (chestName.equals("Correct all the panes!")) {
                        AutoTerminals.currentTerminal = TerminalType.CORRECT_ALL;
                    }
                    else if (chestName.startsWith("What starts with: '")) {
                        AutoTerminals.currentTerminal = TerminalType.LETTER;
                    }
                    else if (chestName.startsWith("Select all the")) {
                        AutoTerminals.currentTerminal = TerminalType.COLOR;
                    }
                    else if (chestName.equals("Change all to same color!")) {
                        AutoTerminals.currentTerminal = TerminalType.TOGGLE_COLOR;
                    }
                }
                if (AutoTerminals.currentTerminal != TerminalType.NONE) {
                    if (AutoTerminals.clickQueue.isEmpty() || AutoTerminals.recalculate) {
                        AutoTerminals.recalculate = this.getClicks((ContainerChest)container);
                    }
                    else {
                        switch (AutoTerminals.currentTerminal) {
                            case MAZE:
                            case NUMBERS:
                            case CORRECT_ALL: {
                                final List<Slot> list;
                                AutoTerminals.clickQueue.removeIf(slot -> list.get(slot.field_75222_d).func_75216_d() && list.get(slot.field_75222_d).func_75211_c().func_77952_i() == 5);
                                break;
                            }
                            case LETTER:
                            case COLOR: {
                                final List<Slot> list2;
                                AutoTerminals.clickQueue.removeIf(slot -> list2.get(slot.field_75222_d).func_75216_d() && list2.get(slot.field_75222_d).func_75211_c().func_77948_v());
                                break;
                            }
                            case TOGGLE_COLOR: {
                                final List<Slot> list3;
                                AutoTerminals.clickQueue.removeIf(slot -> list3.get(slot.field_75222_d).func_75216_d() && list3.get(slot.field_75222_d).func_75211_c().func_77952_i() == AutoTerminals.targetColorIndex);
                                break;
                            }
                        }
                    }
                    if (!AutoTerminals.clickQueue.isEmpty() && Config.autoTerminals && System.currentTimeMillis() - AutoTerminals.lastClickTime > Config.terminalClickDelay) {
                        this.clickSlot(AutoTerminals.clickQueue.get(0));
                    }
                }
            }
        }
    }
    
    @SubscribeEvent
    public void onTick(final TickEndEvent event) {
        if (!Config.autoTerminals || !Utils.inDungeon) {
            return;
        }
        if (Shady.mc.field_71439_g == null || Shady.mc.field_71441_e == null) {
            return;
        }
        if (!(Shady.mc.field_71462_r instanceof GuiChest)) {
            reset();
        }
    }
    
    private static void reset() {
        AutoTerminals.currentTerminal = TerminalType.NONE;
        AutoTerminals.clickQueue.clear();
        AutoTerminals.windowClicks = 0;
        AutoTerminals.targetColorIndex = -1;
    }
    
    private boolean getClicks(final ContainerChest container) {
        final List<Slot> invSlots = (List<Slot>)container.field_75151_b;
        final String chestName = container.func_85151_d().func_145748_c_().func_150260_c();
        AutoTerminals.clickQueue.clear();
        switch (AutoTerminals.currentTerminal) {
            case MAZE: {
                final int[] mazeDirection = { -9, -1, 1, 9 };
                final boolean[] isStartSlot = new boolean[54];
                int endSlot = -1;
                for (final Slot slot : invSlots) {
                    if (slot.field_75224_c == Shady.mc.field_71439_g.field_71071_by) {
                        continue;
                    }
                    final ItemStack itemStack = slot.func_75211_c();
                    if (itemStack == null) {
                        continue;
                    }
                    if (itemStack.func_77973_b() != Item.func_150898_a((Block)Blocks.field_150397_co)) {
                        continue;
                    }
                    if (itemStack.func_77952_i() == 5) {
                        isStartSlot[slot.field_75222_d] = true;
                    }
                    else {
                        if (itemStack.func_77952_i() != 14) {
                            continue;
                        }
                        endSlot = slot.field_75222_d;
                    }
                }
                for (int slot2 = 0; slot2 < 54; ++slot2) {
                    if (isStartSlot[slot2]) {
                        final boolean[] mazeVisited = new boolean[54];
                        int startSlot = slot2;
                        while (startSlot != endSlot) {
                            boolean newSlotChosen = false;
                            for (final int i : mazeDirection) {
                                final int nextSlot = startSlot + i;
                                if (nextSlot >= 0 && nextSlot <= 53 && (i != -1 || startSlot % 9 != 0)) {
                                    if (i != 1 || startSlot % 9 != 8) {
                                        if (nextSlot == endSlot) {
                                            return false;
                                        }
                                        if (!mazeVisited[nextSlot]) {
                                            final ItemStack itemStack2 = invSlots.get(nextSlot).func_75211_c();
                                            if (itemStack2 != null) {
                                                if (itemStack2.func_77973_b() == Item.func_150898_a((Block)Blocks.field_150397_co) && itemStack2.func_77952_i() == 0) {
                                                    AutoTerminals.clickQueue.add(invSlots.get(nextSlot));
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
                                System.out.println("Maze calculation aborted");
                                return true;
                            }
                        }
                    }
                }
                return true;
            }
            case NUMBERS: {
                int min = 0;
                final Slot[] temp = new Slot[14];
                for (int j = 10; j <= 25; ++j) {
                    if (j != 17) {
                        if (j != 18) {
                            final ItemStack itemStack3 = invSlots.get(j).func_75211_c();
                            if (itemStack3 != null) {
                                if (itemStack3.func_77973_b() == Item.func_150898_a((Block)Blocks.field_150397_co) && itemStack3.field_77994_a < 15) {
                                    if (itemStack3.func_77952_i() == 14) {
                                        temp[itemStack3.field_77994_a - 1] = invSlots.get(j);
                                    }
                                    else if (itemStack3.func_77952_i() == 5 && min < itemStack3.field_77994_a) {
                                        min = itemStack3.field_77994_a;
                                    }
                                }
                            }
                        }
                    }
                }
                AutoTerminals.clickQueue.addAll(Arrays.stream(temp).filter(Objects::nonNull).collect((Collector<? super Slot, ?, Collection<? extends Slot>>)Collectors.toList()));
                if (AutoTerminals.clickQueue.size() != 14 - min) {
                    return true;
                }
                break;
            }
            case CORRECT_ALL: {
                for (final Slot slot3 : invSlots) {
                    if (slot3.field_75224_c == Shady.mc.field_71439_g.field_71071_by) {
                        continue;
                    }
                    if (slot3.field_75222_d < 9 || slot3.field_75222_d > 35 || slot3.field_75222_d % 9 <= 1) {
                        continue;
                    }
                    if (slot3.field_75222_d % 9 >= 7) {
                        continue;
                    }
                    final ItemStack itemStack4 = slot3.func_75211_c();
                    if (itemStack4 == null) {
                        return true;
                    }
                    if (itemStack4.func_77973_b() != Item.func_150898_a((Block)Blocks.field_150397_co) || itemStack4.func_77952_i() != 14) {
                        continue;
                    }
                    AutoTerminals.clickQueue.add(slot3);
                }
                break;
            }
            case LETTER: {
                if (chestName.length() > chestName.indexOf("'") + 1) {
                    final char letterNeeded = chestName.charAt(chestName.indexOf("'") + 1);
                    for (final Slot slot4 : invSlots) {
                        if (slot4.field_75224_c == Shady.mc.field_71439_g.field_71071_by) {
                            continue;
                        }
                        if (slot4.field_75222_d < 9 || slot4.field_75222_d > 44 || slot4.field_75222_d % 9 == 0) {
                            continue;
                        }
                        if (slot4.field_75222_d % 9 == 8) {
                            continue;
                        }
                        final ItemStack itemStack5 = slot4.func_75211_c();
                        if (itemStack5 == null) {
                            return true;
                        }
                        if (itemStack5.func_77948_v()) {
                            continue;
                        }
                        if (StringUtils.func_76338_a(itemStack5.func_82833_r()).charAt(0) != letterNeeded) {
                            continue;
                        }
                        AutoTerminals.clickQueue.add(slot4);
                    }
                    break;
                }
                break;
            }
            case COLOR: {
                String colorNeeded = null;
                for (final EnumDyeColor color : EnumDyeColor.values()) {
                    final String colorName = color.func_176610_l().replaceAll("_", " ").toUpperCase();
                    if (chestName.contains(colorName)) {
                        colorNeeded = color.func_176762_d();
                        break;
                    }
                }
                if (colorNeeded != null) {
                    for (final Slot slot4 : invSlots) {
                        if (slot4.field_75224_c == Shady.mc.field_71439_g.field_71071_by) {
                            continue;
                        }
                        if (slot4.field_75222_d < 9 || slot4.field_75222_d > 44 || slot4.field_75222_d % 9 == 0) {
                            continue;
                        }
                        if (slot4.field_75222_d % 9 == 8) {
                            continue;
                        }
                        final ItemStack itemStack5 = slot4.func_75211_c();
                        if (itemStack5 == null) {
                            return true;
                        }
                        if (itemStack5.func_77948_v()) {
                            continue;
                        }
                        if (!itemStack5.func_77977_a().contains(colorNeeded)) {
                            continue;
                        }
                        AutoTerminals.clickQueue.add(slot4);
                    }
                    break;
                }
                break;
            }
            case TOGGLE_COLOR: {
                for (final Slot slot4 : invSlots) {
                    if (slot4.field_75224_c == Shady.mc.field_71439_g.field_71071_by) {
                        continue;
                    }
                    if (AutoTerminals.targetColorIndex == -1) {
                        AutoTerminals.targetColorIndex = getTargetColorIndex(Shady.mc.field_71439_g.field_71070_bA.field_75151_b);
                    }
                    final ItemStack itemStack5 = slot4.func_75211_c();
                    if (itemStack5 == null) {
                        continue;
                    }
                    if (!AutoTerminals.colorOrder.contains(itemStack5.func_77952_i())) {
                        continue;
                    }
                    final boolean leftClick = (AutoTerminals.colorOrder.indexOf(AutoTerminals.targetColorIndex) - AutoTerminals.colorOrder.indexOf(itemStack5.func_77952_i()) + AutoTerminals.colorOrder.size()) % AutoTerminals.colorOrder.size() < Math.round(AutoTerminals.colorOrder.size() / 2.0f);
                    if (leftClick) {
                        AutoTerminals.clickQueue.add(slot4);
                    }
                    else {
                        this.clickSlot(slot4, 1, 0);
                    }
                }
                if (AutoTerminals.targetColorIndex != -1) {
                    Utils.log(AutoTerminals.targetColorIndex);
                    break;
                }
                break;
            }
        }
        return false;
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
            sum += AutoTerminals.colorOrder.indexOf(stack.func_77952_i());
        }
        final int index = Math.round(sum / slots.size());
        return (AutoTerminals.colorOrder.size() > index) ? index : -1;
    }
    
    private void clickSlot(final Slot slot) {
        if (AutoTerminals.testing) {
            this.clickSlot(slot, 0, 1);
        }
        else {
            this.clickSlot(slot, 2, 0);
        }
    }
    
    private void clickSlot(final Slot slot, final int clickButton, final int clickMode) {
        if (AutoTerminals.windowClicks == 0) {
            AutoTerminals.windowId = Shady.mc.field_71439_g.field_71070_bA.field_75152_c;
        }
        Shady.mc.field_71442_b.func_78753_a(AutoTerminals.windowId + AutoTerminals.windowClicks, slot.field_75222_d, clickButton, clickMode, (EntityPlayer)Shady.mc.field_71439_g);
        AutoTerminals.lastClickTime = System.currentTimeMillis();
        if (Config.terminalHalfTrip) {
            ++AutoTerminals.windowClicks;
            AutoTerminals.clickQueue.remove(slot);
        }
    }
    
    static {
        clickQueue = new ArrayList<Slot>(28);
        mazeDirection = new int[] { -9, -1, 1, 9 };
        AutoTerminals.currentTerminal = TerminalType.NONE;
        AutoTerminals.targetColorIndex = -1;
        AutoTerminals.lastClickTime = 0L;
        AutoTerminals.windowId = 0;
        AutoTerminals.windowClicks = 0;
        AutoTerminals.recalculate = false;
        AutoTerminals.testing = false;
        colorOrder = new ArrayList<Integer>(Arrays.asList(14, 1, 4, 13, 11));
    }
    
    private enum TerminalType
    {
        MAZE, 
        NUMBERS, 
        CORRECT_ALL, 
        LETTER, 
        COLOR, 
        NONE, 
        TOGGLE_COLOR, 
        TIMING;
    }
}
