// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.features;

import net.minecraft.item.ItemStack;
import cheaters.get.banned.stats.MiscStats;
import net.minecraft.network.Packet;
import cheaters.get.banned.utils.NetworkUtils;
import net.minecraft.network.play.client.C09PacketHeldItemChange;
import net.minecraft.world.World;
import net.minecraft.entity.player.EntityPlayer;
import cheaters.get.banned.Shady;
import net.minecraft.item.Item;
import net.minecraft.init.Items;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import cheaters.get.banned.utils.Utils;
import cheaters.get.banned.gui.config.Config;
import cheaters.get.banned.events.ClickEvent;
import cheaters.get.banned.utils.KeybindUtils;

public class ItemMacro
{
    public ItemMacro() {
        KeybindUtils.register("Use Ice Spray", 0);
        KeybindUtils.register("Use Power Orb", 0);
        KeybindUtils.register("Use Weird Tuba", 0);
        KeybindUtils.register("Use Gyrokinetic Wand", 0);
        KeybindUtils.register("Use Pigman Sword", 0);
        KeybindUtils.register("Use Healing Wand", 0);
        KeybindUtils.register("Use Rogue Sword", 0);
        KeybindUtils.register("Use Fishing Rod", 0);
    }
    
    @SubscribeEvent
    public void onLeftCLick(final ClickEvent.Left event) {
        if (!Config.disableOutsideDungeons || Utils.inDungeon) {
            if (Config.soulWhipWithAnything) {
                useSkyBlockItem("SOUL_WHIP", true);
            }
            if (Config.aotsWithAnything) {
                useSkyBlockItem("AXE_OF_THE_SHREDDED", true);
            }
            if (Config.termWithAnything && !useSkyBlockItem("TERMINATOR", true)) {
                useSkyBlockItem("JUJU_SHORTBOW", true);
            }
        }
    }
    
    @SubscribeEvent
    public void onInput(final InputEvent.KeyInputEvent event) {
        if (Config.iceSprayHotkey && KeybindUtils.isPressed("Use Ice Spray") && !useSkyBlockItem("ICE_SPRAY_WAND", true)) {
            sendMissingItemMessage("Ice Spray Wand");
        }
        if (Config.powerOrbHotkey && KeybindUtils.isPressed("Use Power Orb") && !useSkyBlockItem("PLASMAFLUX_POWER_ORB", true) && !useSkyBlockItem("OVERFLUX_POWER_ORB", true) && !useSkyBlockItem("MANA_FLUX_POWER_ORB", true) && !useSkyBlockItem("RADIANT_POWER_ORB", true)) {
            sendMissingItemMessage("Power Orb");
        }
        if (Config.weirdTubaHotkey && KeybindUtils.isPressed("Use Weird Tuba") && !useSkyBlockItem("WEIRD_TUBA", true)) {
            sendMissingItemMessage("Weird Tuba");
        }
        if (Config.gyrokineticWandHotkey && KeybindUtils.isPressed("Use Gyrokinetic Wand") && !useSkyBlockItem("GYROKINETIC_WAND", false)) {
            sendMissingItemMessage("Gyrokinetic Wand");
        }
        if (Config.pigmanSwordHotkey && KeybindUtils.isPressed("Use Pigman Sword") && !useSkyBlockItem("PIGMAN_SWORD", true)) {
            sendMissingItemMessage("Pigman Sword");
        }
        if (Config.healingWandHotkey && KeybindUtils.isPressed("Use Healing Wand") && !useSkyBlockItem("WAND_OF_ATONEMENT", true) && !useSkyBlockItem("WAND_OF_RESTORATION", true) && !useSkyBlockItem("WAND_OF_MENDING", true) && !useSkyBlockItem("WAND_OF_HEALING", true)) {
            sendMissingItemMessage("Healing Wand");
        }
        if (Config.rogueSwordHotkey && KeybindUtils.isPressed("Use Rogue Sword") && !useRogueSword()) {
            sendMissingItemMessage("Rogue Sword");
        }
        if (Config.fishingRodHotkey && KeybindUtils.isPressed("Use Fishing Rod") && !useVanillaItem((Item)Items.field_151112_aM)) {
            sendMissingItemMessage("Fishing Rod");
        }
    }
    
    public static boolean useSkyBlockItem(final String itemId, final boolean rightClick) {
        for (int i = 0; i < 9; ++i) {
            final ItemStack item = Shady.mc.field_71439_g.field_71071_by.func_70301_a(i);
            if (itemId.equals(Utils.getSkyBlockID(item))) {
                final int previousItem = Shady.mc.field_71439_g.field_71071_by.field_70461_c;
                Shady.mc.field_71439_g.field_71071_by.field_70461_c = i;
                if (rightClick) {
                    Shady.mc.field_71442_b.func_78769_a((EntityPlayer)Shady.mc.field_71439_g, (World)Shady.mc.field_71441_e, item);
                }
                else {
                    NetworkUtils.sendPacket((Packet<?>)new C09PacketHeldItemChange(i));
                    KeybindUtils.leftClick();
                    NetworkUtils.sendPacket((Packet<?>)new C09PacketHeldItemChange(previousItem));
                }
                Shady.mc.field_71439_g.field_71071_by.field_70461_c = previousItem;
                MiscStats.add(MiscStats.Metric.ITEMS_MACROED);
                return true;
            }
        }
        return false;
    }
    
    public static boolean useRogueSword() {
        for (int i = 0; i < 9; ++i) {
            final ItemStack item = Shady.mc.field_71439_g.field_71071_by.func_70301_a(i);
            if ("ROGUE_SWORD".equals(Utils.getSkyBlockID(item))) {
                final int previousItem = Shady.mc.field_71439_g.field_71071_by.field_70461_c;
                Shady.mc.field_71439_g.field_71071_by.field_70461_c = i;
                for (int j = 0; j < 40; ++j) {
                    Shady.mc.field_71442_b.func_78769_a((EntityPlayer)Shady.mc.field_71439_g, (World)Shady.mc.field_71441_e, item);
                }
                Shady.mc.field_71439_g.field_71071_by.field_70461_c = previousItem;
                MiscStats.add(MiscStats.Metric.ITEMS_MACROED);
                return true;
            }
        }
        return false;
    }
    
    public static boolean useVanillaItem(final Item item) {
        for (int i = 0; i < 9; ++i) {
            final ItemStack itemStack = Shady.mc.field_71439_g.field_71071_by.func_70301_a(i);
            if (itemStack != null && itemStack.func_77973_b() == item) {
                final int previousItem = Shady.mc.field_71439_g.field_71071_by.field_70461_c;
                Shady.mc.field_71439_g.field_71071_by.field_70461_c = i;
                Shady.mc.field_71442_b.func_78769_a((EntityPlayer)Shady.mc.field_71439_g, (World)Shady.mc.field_71441_e, itemStack);
                Shady.mc.field_71439_g.field_71071_by.field_70461_c = previousItem;
                MiscStats.add(MiscStats.Metric.ITEMS_MACROED);
                return true;
            }
        }
        return false;
    }
    
    private static void sendMissingItemMessage(final String itemName) {
        Utils.sendModMessage("No &9" + itemName + "&r found in your hotbar");
    }
}
