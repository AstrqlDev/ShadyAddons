// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.features.routines.actions;

import net.minecraft.item.ItemStack;
import java.util.function.Predicate;
import net.minecraft.network.Packet;
import cheaters.get.banned.utils.NetworkUtils;
import net.minecraft.network.play.client.C09PacketHeldItemChange;
import cheaters.get.banned.Shady;
import cheaters.get.banned.utils.Utils;
import cheaters.get.banned.features.routines.RoutineRuntimeException;
import cheaters.get.banned.features.routines.RoutineElementData;

public class SwapHotbarAction extends Action
{
    private Integer slot;
    private String skyblockId;
    private String nameContains;
    
    public SwapHotbarAction(final RoutineElementData data) throws RoutineRuntimeException {
        super(data);
        this.slot = data.keyAsInt_noError("slot_number");
        if (this.slot != null) {
            return;
        }
        this.skyblockId = data.keyAsString_noError("skyblock_id");
        if (this.skyblockId != null) {
            return;
        }
        this.nameContains = data.keyAsString_noError("name_contains");
        if (this.nameContains == null) {
            throw new RoutineRuntimeException("Invalid value(s) for SwapHotbarAction");
        }
    }
    
    @Override
    public void doAction() {
        int finalSlot = -1;
        if (this.slot != null) {
            finalSlot = this.slot;
        }
        if (this.skyblockId != null) {
            finalSlot = findHotbarSlot(itemStack -> Utils.getSkyBlockID(itemStack).equals(this.skyblockId));
        }
        if (this.nameContains != null) {
            finalSlot = findHotbarSlot(itemStack -> itemStack.func_82833_r().contains(this.nameContains));
        }
        if (finalSlot > -1 && finalSlot < 9) {
            SwapPreviousHotbarAction.slot = Shady.mc.field_71439_g.field_71071_by.field_70461_c;
            NetworkUtils.sendPacket((Packet<?>)new C09PacketHeldItemChange(finalSlot));
            Shady.mc.field_71439_g.field_71071_by.field_70461_c = finalSlot;
        }
    }
    
    public static int findHotbarSlot(final Predicate<ItemStack> predicate) {
        for (int i = 0; i < 9; ++i) {
            final ItemStack item = Shady.mc.field_71439_g.field_71071_by.func_70301_a(i);
            if (item != null) {
                if (predicate.test(item)) {
                    return i;
                }
            }
        }
        return -1;
    }
}
