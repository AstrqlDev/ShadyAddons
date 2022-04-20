// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.features.autoterminals;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.inventory.Slot;
import java.util.List;
import net.minecraft.inventory.Container;
import cheaters.get.banned.features.autoterminals.solvers.ToggleColorSolver;
import cheaters.get.banned.features.autoterminals.solvers.ColorSolver;
import cheaters.get.banned.features.autoterminals.solvers.LetterSolver;
import cheaters.get.banned.features.autoterminals.solvers.CorrectPanesSolver;
import cheaters.get.banned.features.autoterminals.solvers.NumbersSolver;
import cheaters.get.banned.features.autoterminals.solvers.MazeSolver;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.client.gui.inventory.GuiChest;
import cheaters.get.banned.utils.Utils;
import cheaters.get.banned.gui.config.Config;
import net.minecraftforge.client.event.GuiScreenEvent;

public class NewAutoTerminals
{
    private static long lastClick;
    public static int windowClicks;
    
    @SubscribeEvent
    public void onGuiDraw(final GuiScreenEvent.BackgroundDrawnEvent event) {
        if (!Config.autoTerminals || !Utils.inDungeon || !(event.gui instanceof GuiChest)) {
            return;
        }
        final Container container = ((GuiChest)event.gui).field_147002_h;
        if (!(container instanceof ContainerChest)) {
            return;
        }
        final List<Slot> slots = (List<Slot>)container.field_75151_b;
        if (slots.isEmpty()) {
            return;
        }
        final String chestName = Utils.getGuiName(event.gui);
        Utils.log("Chest Name: " + chestName);
        ClickQueue clickQueue;
        if (chestName.equals("Navigate the maze!")) {
            clickQueue = new MazeSolver().getClicks(slots, chestName);
        }
        else if (chestName.equals("Click in order!")) {
            clickQueue = new NumbersSolver().getClicks(slots, chestName);
        }
        else if (chestName.equals("Correct all the panes!")) {
            clickQueue = new CorrectPanesSolver().getClicks(slots, chestName);
        }
        else if (chestName.startsWith("What starts with: '")) {
            clickQueue = new LetterSolver().getClicks(slots, chestName);
        }
        else if (chestName.startsWith("Select all the")) {
            clickQueue = new ColorSolver().getClicks(slots, chestName);
        }
        else {
            if (!chestName.equals("Change all to same color!")) {
                return;
            }
            clickQueue = new ToggleColorSolver().getClicks(slots, chestName);
        }
        if (clickQueue == null) {
            Utils.log("Something is wrong with the click queue");
            return;
        }
        if (System.currentTimeMillis() - NewAutoTerminals.lastClick >= Config.terminalClickDelay) {
            NewAutoTerminals.windowClicks = clickQueue.clickFirst(NewAutoTerminals.windowClicks);
            NewAutoTerminals.lastClick = System.currentTimeMillis();
        }
    }
    
    static {
        NewAutoTerminals.lastClick = 0L;
        NewAutoTerminals.windowClicks = 0;
    }
}
