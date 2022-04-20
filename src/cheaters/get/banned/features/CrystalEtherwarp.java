// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.features;

import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import cheaters.get.banned.utils.KeybindUtils;
import net.minecraft.network.Packet;
import cheaters.get.banned.utils.NetworkUtils;
import net.minecraft.entity.Entity;
import net.minecraft.network.play.client.C0BPacketEntityAction;
import cheaters.get.banned.utils.RotationUtils;
import net.minecraft.util.BlockPos;
import cheaters.get.banned.Shady;
import cheaters.get.banned.utils.DungeonUtils;
import cheaters.get.banned.utils.Utils;
import cheaters.get.banned.gui.config.Config;
import cheaters.get.banned.events.TickEndEvent;

public class CrystalEtherwarp
{
    private static boolean teleported;
    private static boolean sentSneak;
    
    @SubscribeEvent
    public void onTick(final TickEndEvent event) {
        if (Config.crystalEtherwarp && !CrystalEtherwarp.teleported && Utils.inDungeon && DungeonUtils.inFloor(DungeonUtils.Floor.FLOOR_7) && Shady.mc.field_71439_g.field_70165_t == 272.5 && Shady.mc.field_71439_g.field_70161_v == 213.5) {
            final RotationUtils.Rotation rotation = RotationUtils.getRotationToBlock(new BlockPos((Config.crystalSide == 0) ? 280 : 264, 237, 248));
            if (!CrystalEtherwarp.sentSneak) {
                Shady.mc.field_71439_g.field_71158_b.field_78899_d = true;
                NetworkUtils.sendPacket((Packet<?>)new C0BPacketEntityAction((Entity)Shady.mc.field_71439_g, C0BPacketEntityAction.Action.START_SNEAKING));
                RotationUtils.look(rotation);
                CrystalEtherwarp.sentSneak = true;
            }
            CrystalEtherwarp.teleported = true;
        }
        if (CrystalEtherwarp.sentSneak) {
            CrystalEtherwarp.sentSneak = false;
            KeybindUtils.rightClick();
            Shady.mc.field_71439_g.field_71158_b.field_78899_d = false;
            NetworkUtils.sendPacket((Packet<?>)new C0BPacketEntityAction((Entity)Shady.mc.field_71439_g, C0BPacketEntityAction.Action.STOP_SNEAKING));
        }
    }
    
    @SubscribeEvent
    public void onWorldLoad(final WorldEvent.Load event) {
        CrystalEtherwarp.teleported = false;
    }
    
    static {
        CrystalEtherwarp.teleported = false;
        CrystalEtherwarp.sentSneak = false;
    }
}
