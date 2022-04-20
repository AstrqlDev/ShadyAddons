// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.features.autoterminals;

import cheaters.get.banned.gui.config.Config;
import cheaters.get.banned.Shady;
import java.util.Iterator;
import net.minecraft.inventory.Slot;
import java.util.ArrayList;

public class ClickQueue implements Iterable<SlotClick>
{
    private ArrayList<SlotClick> clicks;
    
    public ClickQueue() {
        this.clicks = new ArrayList<SlotClick>();
    }
    
    public void add(final SlotClick.ClickType type, final Slot slot) {
        this.clicks.add(new SlotClick(type, slot));
    }
    
    public void rightClick(final Slot slot) {
        this.add(SlotClick.ClickType.RIGHT_CLICK, slot);
    }
    
    public void leftClick(final Slot slot) {
        this.add(SlotClick.ClickType.LEFT_CLICK, slot);
    }
    
    public void middleClick(final Slot slot) {
        this.add(SlotClick.ClickType.MIDDLE_CLICK, slot);
    }
    
    public void clear() {
        this.clicks.clear();
    }
    
    @Override
    public Iterator<SlotClick> iterator() {
        return this.clicks.iterator();
    }
    
    public int clickFirst(int windowClicks) {
        final SlotClick click = this.clicks.get(0);
        click.click(Shady.mc.field_71439_g.field_71070_bA.field_75152_c + windowClicks);
        if (Config.terminalHalfTrip) {
            ++windowClicks;
            this.clicks.remove(0);
        }
        return windowClicks;
    }
    
    public int size() {
        return this.clicks.size();
    }
    
    public void debug() {
        for (final SlotClick click : this.clicks) {
            System.out.println(click.toString());
        }
    }
}
