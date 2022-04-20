// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.features.autoterminals;

import net.minecraft.entity.player.EntityPlayer;
import cheaters.get.banned.Shady;
import net.minecraft.inventory.Slot;

public class SlotClick
{
    private ClickType type;
    private Slot slot;
    
    public SlotClick(final ClickType type, final Slot slot) {
        this.type = type;
        this.slot = slot;
    }
    
    public void click(final int windowId) {
        Shady.mc.field_71442_b.func_78753_a(windowId, this.slot.field_75222_d, this.type.button, this.type.mode, (EntityPlayer)Shady.mc.field_71439_g);
    }
    
    @Override
    public String toString() {
        return "Slot#" + this.slot.field_75222_d + ", " + this.type.toString();
    }
    
    public enum ClickType
    {
        RIGHT_CLICK(0, 1), 
        LEFT_CLICK(0, 0), 
        MIDDLE_CLICK(3, 2);
        
        public final int mode;
        public final int button;
        
        private ClickType(final int mode, final int button) {
            this.mode = mode;
            this.button = button;
        }
        
        @Override
        public String toString() {
            return "ClickType." + this.name() + "(" + this.mode + ", " + this.button + ")";
        }
    }
}
