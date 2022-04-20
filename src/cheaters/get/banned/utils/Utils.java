// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.utils;

import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.scoreboard.ScoreObjective;
import cheaters.get.banned.events.TickEndEvent;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import net.minecraft.init.Blocks;
import net.minecraft.block.Block;
import java.awt.Color;
import net.minecraft.command.ICommandSender;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.inventory.Slot;
import net.minecraft.entity.player.EntityPlayer;
import cheaters.get.banned.Shady;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.item.ItemStack;
import java.net.URI;
import java.awt.Desktop;
import java.util.List;
import java.util.Calendar;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import net.minecraft.launchwrapper.Launch;
import java.util.regex.Pattern;

public class Utils
{
    public static boolean inSkyBlock;
    public static boolean inDungeon;
    public static boolean forceSkyBlock;
    public static boolean forceDungeon;
    private static final Pattern numberPattern;
    private int ticks;
    
    public Utils() {
        this.ticks = 0;
    }
    
    public static void log(final Object content) {
        if (Launch.blackboard.get("fml.deobfuscatedEnvironment")) {
            System.out.println(content);
        }
    }
    
    public static int romanToInt(final String s) {
        final Map<Character, Integer> numerals = new HashMap<Character, Integer>();
        numerals.put('I', 1);
        numerals.put('V', 5);
        numerals.put('X', 10);
        numerals.put('L', 50);
        numerals.put('C', 100);
        numerals.put('D', 500);
        numerals.put('M', 1000);
        int result = 0;
        for (int i = 0; i < s.length(); ++i) {
            int add = numerals.get(s.charAt(i));
            if (i < s.length() - 1) {
                final int next = numerals.get(s.charAt(i + 1));
                if (next / add == 5 || next / add == 10) {
                    add = next - add;
                    ++i;
                }
            }
            result += add;
        }
        return result;
    }
    
    public static String getLogo() {
        final ArrayList<String> logos = new ArrayList<String>(Arrays.asList("logo-fsr", "logo-sbe", "logo-pride"));
        final int month = Calendar.getInstance().get(2);
        final int day = Calendar.getInstance().get(5);
        if (EstonianUtils.isEstoniaDay()) {
            return "logo-estonian";
        }
        if (month == 11 && day > 20) {
            return "logo-christmas";
        }
        if (month == 9 && day > 25) {
            return "logo-halloween";
        }
        if (month == 9 && day == 11) {
            return "logo-pride";
        }
        if (Math.random() < 0.8) {
            return "logo";
        }
        return (String)ArrayUtils.getRandomItem(logos);
    }
    
    public static void openUrl(final String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        }
        catch (Exception ex) {}
    }
    
    public static String removeFormatting(final String input) {
        return input.replaceAll("§[0-9a-fk-or]", "");
    }
    
    public static String getSkyBlockID(final ItemStack item) {
        if (item != null) {
            final NBTTagCompound extraAttributes = item.func_179543_a("ExtraAttributes", false);
            if (extraAttributes != null && extraAttributes.func_74764_b("id")) {
                return extraAttributes.func_74779_i("id");
            }
        }
        return "";
    }
    
    public static List<String> getLore(final ItemStack item) {
        if (item != null) {
            return (List<String>)item.func_82840_a((EntityPlayer)Shady.mc.field_71439_g, false);
        }
        return null;
    }
    
    public static String getInventoryName() {
        if (Shady.mc.field_71439_g == null || Shady.mc.field_71441_e == null) {
            return "null";
        }
        return Shady.mc.field_71439_g.field_71070_bA.field_75151_b.get(0).field_75224_c.func_70005_c_();
    }
    
    public static String getGuiName(final GuiScreen gui) {
        if (gui instanceof GuiChest) {
            return ((ContainerChest)((GuiChest)gui).field_147002_h).func_85151_d().func_145748_c_().func_150260_c();
        }
        return "";
    }
    
    public static void sendMessage(String message) {
        if (Shady.mc.field_71439_g != null && Shady.mc.field_71441_e != null) {
            if (!message.contains("§")) {
                message = message.replace("&", "§");
            }
            Shady.mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText(message));
        }
    }
    
    public static void sendMessageAsPlayer(final String message) {
        Shady.mc.field_71439_g.func_71165_d(message);
    }
    
    public static void executeCommand(final String command) {
        if (ClientCommandHandler.instance.func_71556_a((ICommandSender)Shady.mc.field_71439_g, command) == 0) {
            sendMessageAsPlayer(command);
        }
    }
    
    public static void sendModMessage(final String message) {
        if (message.contains("§")) {
            sendMessage("§" + FontUtils.getRainbowCode('7') + "ShadyAddons > §f" + message);
        }
        else {
            sendMessage("&" + FontUtils.getRainbowCode('7') + "ShadyAddons > &f" + message);
        }
    }
    
    public static String removeAllExceptNumbersAndPeriods(final String text) {
        return Utils.numberPattern.matcher(text).replaceAll("");
    }
    
    public static Color addAlpha(final Color color, final int alpha) {
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha);
    }
    
    public static Color addAlphaPct(final Color color, final float alpha) {
        final int alphaNum = Math.round(255.0f * alpha);
        return addAlpha(color, alphaNum);
    }
    
    public static boolean isInteractable(final Block block) {
        return new ArrayList(Arrays.asList((Block)Blocks.field_150486_ae, Blocks.field_150442_at, Blocks.field_150447_bR, Blocks.field_150471_bO, Blocks.field_150430_aB, (Block)Blocks.field_150465_bP)).contains(block);
    }
    
    public static void copyToClipboard(final String text) {
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(text), null);
    }
    
    @SafeVarargs
    public static <T> T firstNotNull(final T... args) {
        for (final T arg : args) {
            if (arg != null) {
                return arg;
            }
        }
        return null;
    }
    
    @SubscribeEvent
    public void onTick(final TickEndEvent event) {
        if (Utils.forceDungeon || Utils.forceSkyBlock) {
            if (Utils.forceSkyBlock) {
                Utils.inSkyBlock = true;
            }
            if (Utils.forceDungeon) {
                Utils.inSkyBlock = true;
            }
            Utils.inDungeon = true;
            return;
        }
        if (this.ticks % 20 == 0) {
            if (Shady.mc.field_71439_g != null && Shady.mc.field_71441_e != null) {
                final ScoreObjective scoreboardObj = Shady.mc.field_71441_e.func_96441_U().func_96539_a(1);
                if (scoreboardObj != null) {
                    Utils.inSkyBlock = removeFormatting(scoreboardObj.func_96678_d()).contains("SKYBLOCK");
                }
                Utils.inDungeon = ((Utils.inSkyBlock && ScoreboardUtils.scoreboardContains("The Catacombs") && !ScoreboardUtils.scoreboardContains("Queue")) || ScoreboardUtils.scoreboardContains("Dungeon Cleared:"));
            }
            this.ticks = 0;
        }
        ++this.ticks;
    }
    
    @SubscribeEvent
    public void onWorldLoad(final WorldEvent.Load event) {
        Utils.forceDungeon = false;
        Utils.forceSkyBlock = false;
    }
    
    static {
        Utils.inSkyBlock = false;
        Utils.inDungeon = false;
        Utils.forceSkyBlock = false;
        Utils.forceDungeon = false;
        numberPattern = Pattern.compile("[^0-9.]");
    }
}
