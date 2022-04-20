// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.features.commandpalette;

import java.util.Iterator;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import cheaters.get.banned.utils.ExpressionParser;
import java.util.Map;
import java.util.stream.Collector;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.ArrayList;
import net.minecraft.item.Item;
import cheaters.get.banned.gui.config.Config;
import net.minecraft.init.Items;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiControls;
import cheaters.get.banned.Shady;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.init.Blocks;
import cheaters.get.banned.features.commandpalette.actions.RunnableAction;
import cheaters.get.banned.features.commandpalette.icons.ImageIcon;
import cheaters.get.banned.features.commandpalette.actions.IAction;
import cheaters.get.banned.features.commandpalette.icons.IIcon;
import cheaters.get.banned.features.commandpalette.actions.CommandAction;
import cheaters.get.banned.features.commandpalette.icons.ItemIcon;
import cheaters.get.banned.utils.ItemUtils;
import cheaters.get.banned.utils.Utils;
import java.util.LinkedHashMap;

public class ResultList
{
    private static LinkedHashMap<String, Result> results;
    
    public static void generateList() {
        ResultList.results.clear();
        if (!Utils.inSkyBlock) {
            ResultList.results.put("join skyblock sb", new Result("Join SkyBlock", new ItemIcon(ItemUtils.getSkullItemStack("f26a2360-0158-4f76-8a34-f487883f2b04", "c9c8881e42915a9d29bb61a16fb26d059913204d265df5b439b3d792acd56")), new CommandAction("/skyblock")));
        }
        ResultList.results.put("shadyaddons bug report", new Result("Report a Bug", new ImageIcon("chester.png"), new RunnableAction(() -> Utils.openUrl("https://shadyaddons.com/bug-report/?version=2.7.2"))));
        ResultList.results.put("controls keybinds", new Result("Open Minecraft Controls", new ItemIcon(new ItemStack((Block)Blocks.field_150349_c)), new RunnableAction(() -> Shady.guiToOpen = (GuiScreen)new GuiControls((GuiScreen)null, Shady.mc.field_71474_y))));
        if (Utils.inSkyBlock) {
            ResultList.results.put("/ah auction house claim auctions flip cookie", new Result("Auction House", new ItemIcon(ItemUtils.getSkullItemStack("04049c90-d3e9-4621-9caf-00000aaa3038", "452dca68c8f8af533fb737faeeacbe717b968767fc18824dc2d37ac789fc77")), new CommandAction("/ah")));
            ResultList.results.put("/bz bazaar flip cookie", new Result("Bazaar", new ItemIcon(ItemUtils.getSkullItemStack("41d3abc2-d749-400c-9090-d5434d03831b", "c232e3820897429157619b0ee099fec0628f602fff12b695de54aef11d923ad7")), new CommandAction("/bz")));
            ResultList.results.put("anvil /av cookie", new Result("Anvil", new ItemIcon(new ItemStack(Blocks.field_150467_bQ)), new CommandAction("/av")));
            ResultList.results.put("enchanting table enchantment /et cookie", new Result("Enchanting Table", new ItemIcon(new ItemStack(Blocks.field_150381_bn)), new CommandAction("/et")));
            ResultList.results.put("island home warp", new Result("Warp: Island", new ItemIcon(ItemUtils.getSkullByName(Shady.mc.func_110432_I().func_111285_a())), new CommandAction("/warp home")));
            ResultList.results.put("hub warp griffin burrow", new Result("Warp: Hub", new ItemIcon(ItemUtils.getSkullItemStack("f26a2360-0158-4f76-8a34-f487883f2b04", "c9c8881e42915a9d29bb61a16fb26d059913204d265df5b439b3d792acd56")), new CommandAction("/warp hub")));
            ResultList.results.put("dungeon hub dh warp", new Result("Warp: Dungeon Hub", new ItemIcon(ItemUtils.getSkullItemStack("3549f087-6655-4e1b-9b71-ecc1c59e59b7", "e27372809b5694646f44d7a837d4fe66e5ef62ae72701171651b3a780cb1f9c")), new CommandAction("/warp dungeon_hub")));
            ResultList.results.put("mines dwarven warp", new Result("Warp: Dwarven Mines", new ItemIcon(ItemUtils.getSkullItemStack("61ebeb8a-d9a9-4b5e-ac3e-cbc9eeee7d46", "172850906b7f0d952c0e508073cc439fd3374ccf5b889c06f7e8d90cc0cc255c")), new CommandAction("/warp mines")));
            ResultList.results.put("crystal hollows mines warp gemstone", new Result("Warp: Crystal Hollows", new ItemIcon(ItemUtils.getSkullItemStack("2b00f544-854e-48c1-86ab-210e03e34edd", "21dbe30b027acbceb612563bd877cd7ebb719ea6ed1399027dcee58bb9049d4a")), new CommandAction("/warp crystals")));
            ResultList.results.put("da dark auction warp griffin burrow", new Result("Warp: Dark Auction", new ItemIcon(ItemUtils.getSkullItemStack("59ae04a3-7995-440e-b16f-10e4d87cf63d", "7ab83858ebc8ee85c3e54ab13aabfcc1ef2ad446d6a900e471c3f33b78906a5b")), new CommandAction("/warp da")));
            ResultList.results.put("museum high level higher level warp griffin burrow", new Result("Warp: Museum", new ItemIcon(new ItemStack(Items.field_151159_an)), new CommandAction("/warp museum")));
            ResultList.results.put("forge dwarven mines warp", new Result("Warp: Dwarven Forge", new ItemIcon(new ItemStack(Items.field_151129_at)), new CommandAction("/warp mines")));
            ResultList.results.put("park trees foraging forest warp", new Result("Warp: The Park", new ItemIcon(new ItemStack(Blocks.field_150364_r)), new CommandAction("/warp park")));
            ResultList.results.put("sven howling caves slayer park foraging pond warp", new Result("Warp: Howling Cave", new ItemIcon(ItemUtils.getSkullItemStack("a3585839-876f-4c6d-bd25-b5a4750d428b", "adc6429cfabacf211dd3db26c5ca7b5942dd82599fbb1d537cf72e4952e2c7b")), new CommandAction("/warp howl")));
            ResultList.results.put("crypt zombie slayer hub warp", new Result("Warp: Hub Crypts", new ItemIcon(new ItemStack(Items.field_151078_bh)), new CommandAction("/warp crypt")));
            ResultList.results.put("blazing fortress nether magma boss warp", new Result("Warp: Blazing Fortress", new ItemIcon(new ItemStack(Blocks.field_150424_aL)), new CommandAction("/warp fortress")));
            ResultList.results.put("magma fields blazing fortress nether magma boss warp", new Result("Warp: Magma Fields", new ItemIcon(new ItemStack(Items.field_151064_bs)), new CommandAction("/warp magma")));
            ResultList.results.put("deep caverns mine warp", new Result("Warp: Deep Caverns", new ItemIcon(new ItemStack(Blocks.field_150482_ag)), new CommandAction("/warp deep")));
            ResultList.results.put("rusty gold mine warp", new Result("Warp: Gold Mine", new ItemIcon(new ItemStack(Items.field_151043_k)), new CommandAction("/warp gold")));
            ResultList.results.put("spider's den spiders den spider den spider slayer pond fishing warp", new Result("Warp: Spider's Den", new ItemIcon(new ItemStack(Items.field_151007_F)), new CommandAction("/warp spider")));
            ResultList.results.put("top of nest spider's den spiders den spider den spider slayer warp", new Result("Warp: Top of Nest", new ItemIcon(new ItemStack(Items.field_151070_bp)), new CommandAction("/warp nest")));
            ResultList.results.put("barn farming islands fishing warp", new Result("Warp: The Barn", new ItemIcon(new ItemStack(Items.field_151172_bF)), new CommandAction("/warp barn")));
            ResultList.results.put("mushroom desert trapper jake farming islands warp", new Result("Warp: Mushroom Desert", new ItemIcon(new ItemStack((Block)Blocks.field_150337_Q)), new CommandAction("/warp desert")));
            ResultList.results.put("castle hub warp griffin burrows philosopher lonely", new Result("Warp: Hub Castle", new ItemIcon(ItemUtils.getSkullItemStack("bcccae77-0ac7-4cd0-8126-c900727c2223", "49c1832e4ef5c4ad9c519d194b1985030d257914334aaf2745c9dfd611d6d61d")), new CommandAction("/warp castle")));
            ResultList.results.put("jerry fishing winter island yeti christmas workshop warp", new Result("Warp: Jerry's Workshop", new ItemIcon(ItemUtils.getSkullItemStack("0f7e2dc4-3319-41d6-85dc-cc7a9bae89bb", "ab126814fc3fa846dad934c349628a7a1de5b415021a03ef4211d62514d5")), new CommandAction("/savethejerrys")));
            ResultList.results.put("dragon end warp", new Result("Warp: Dragon's Nest", new ItemIcon(ItemUtils.getSkullItemStack("36122cdc-6c97-4b97-990a-ef4df57db922", "daa8fc8de6417b48d48c80b443cf5326e3d9da4dbe9b25fcd49549d96168fc0")), new CommandAction("/warp drag"), "Rawr! Fight dragons!"));
            ResultList.results.put("enderman sepulture void slayer end warp", new Result("Warp: Void Sepulture", new ItemIcon(ItemUtils.getSkullItemStack("d9c0c394-598f-49fe-a9cf-e15e779da667", "eb07594e2df273921a77c101d0bfdfa1115abed5b9b2029eb496ceba9bdbb4b3")), new CommandAction("/warp void")));
            ResultList.results.put("the end enderman dragon pearl warp", new Result("Warp: The End", new ItemIcon(new ItemStack(Items.field_151061_bv)), new CommandAction("/warp end")));
            ResultList.results.put("jungle island romero romeo juliette quest the park warp", new Result("Warp: Jungle Island", new ItemIcon(ItemUtils.getSkullItemStack("41d3abc2-d749-400c-9090-d5434d03831b", "b3351f22f63b43f40fa8e28def66b73b17ba265773fe9980971da2f1515032d9")), new CommandAction("/warp jungle")));
            ResultList.results.put("f7 floor 7 dungeon join", new Result("Join Dungeon: F7", new ImageIcon("f7.png"), new CommandAction("/joindungeon catacombs 7")));
            ResultList.results.put("m3 master floor 3 dungeon join", new Result("Join Dungeon: M3", new ImageIcon("m3.png"), new CommandAction("/joindungeon master_catacombs 3")));
            ResultList.results.put("m4 master floor 4 dungeon join", new Result("Join Dungeon: M4", new ImageIcon("m4.png"), new CommandAction("/joindungeon master_catacombs 4")));
            ResultList.results.put("m5 master floor 5 dungeon join", new Result("Join Dungeon: M5", new ImageIcon("m5.png"), new CommandAction("/joindungeon master_catacombs 5")));
            ResultList.results.put("m6 master floor 6 dungeon join", new Result("Join Dungeon: M6", new ImageIcon("m6.png"), new CommandAction("/joindungeon master_catacombs 6")));
            ResultList.results.put("pets", new Result("Open Menu: Pets", new ItemIcon(new ItemStack(Items.field_151103_aS)), new CommandAction("/pets")));
            ResultList.results.put("sacks", new Result("Open Menu: Sacks", new ItemIcon(new ItemStack(Items.field_151103_aS)), new CommandAction("/sacks")));
            ResultList.results.put("profile switcher manager", new Result("Open Menu: Profile Switcher", new ItemIcon(new ItemStack(Items.field_151057_cb)), new CommandAction("/profiles")));
        }
        ResultList.results.put("toggle enable disable aots with anything axe of the shredded", new Result((Config.aotsWithAnything ? "Disable" : "Enable") + ": AOTS w/ Anything", new ItemIcon(new ItemStack(Items.field_151056_x)), new RunnableAction(() -> Config.aotsWithAnything = !Config.aotsWithAnything)));
        ResultList.results.put("toggle enable disable teleport with anything aspect of the end aspect of the end aotv aote", new Result((Config.teleportWithAnything ? "Disable" : "Enable") + ": Teleport w/ Anything", new ItemIcon(new ItemStack(Items.field_151048_u)), new RunnableAction(() -> Config.teleportWithAnything = !Config.teleportWithAnything)));
        ResultList.results.put("toggle enable disable soul whip with anything", new Result((Config.soulWhipWithAnything ? "Disable" : "Enable") + ": Soul Whip w/ Anything", new ItemIcon(new ItemStack((Item)Items.field_151112_aM)), new RunnableAction(() -> Config.soulWhipWithAnything = !Config.soulWhipWithAnything)));
        ResultList.results.put("toggle enable disable terminator with anything", new Result((Config.termWithAnything ? "Disable" : "Enable") + ": Terinator w/ Anything", new ItemIcon(new ItemStack((Item)Items.field_151031_f)), new RunnableAction(() -> Config.termWithAnything = !Config.termWithAnything)));
        ResultList.results.put("shadyaddons settings config options", new Result("Open ShadyAddons Settings", new ImageIcon("chester.png"), new CommandAction("/sh")));
        if (Shady.USING_SBA) {
            ResultList.results.put("skyblockaddons sba settings config options", new Result("Open SkyBlockAddons Settings", new ItemIcon(new ItemStack(Items.field_151106_aX)), new CommandAction("/sba")));
        }
        if (Shady.USING_SBE) {
            ResultList.results.put("skyblockextras sbe settings config options", new Result("Open SkyBlockExtras Settings", new ImageIcon("sbe.png"), new CommandAction("/sbe")));
        }
        if (Shady.USING_SKYTILS) {
            ResultList.results.put("skytils st config settings options", new Result("Open Skytils Settings", new ImageIcon("skytils.png"), new CommandAction("/st config")));
            ResultList.results.put("griffin refresh burrows skytils st", new Result("Refresh Griffin Burrows", new ItemIcon(ItemUtils.getSkullItemStack("9197f09e-5cbb-464f-9b8f-7f374d531504", "4c27e3cb52a64968e60c861ef1ab84e0a0cb5f07be103ac78da67761731f00c8")), new CommandAction("/st griffin refresh")));
        }
        if (Shady.USING_SKYTILS || Shady.USING_SBE) {
            ResultList.results.put("reparty rp", new Result("Reparty", new ImageIcon("reparty.png"), new CommandAction("/rp")));
        }
    }
    
    public static ArrayList<Result> getResults(String filter) {
        ArrayList<Result> filtered = new ArrayList<Result>();
        filter = filter.toLowerCase();
        if (filter.equals("")) {
            filtered = ResultList.results.values().stream().limit(5L).collect((Collector<? super Result, ?, ArrayList<Result>>)Collectors.toCollection((Supplier<R>)ArrayList::new));
        }
        else {
            for (final Map.Entry<String, Result> result : ResultList.results.entrySet()) {
                if (result.getKey().toLowerCase().contains(filter) || result.getValue().name.toLowerCase().contains(filter)) {
                    filtered.add(result.getValue());
                }
                if (filtered.size() == 5) {
                    break;
                }
            }
        }
        if (filtered.isEmpty()) {
            Double result2 = null;
            try {
                result2 = ExpressionParser.eval(filter);
            }
            catch (Exception ex) {}
            if (result2 != null) {
                final DecimalFormat formattter = new DecimalFormat("0", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
                formattter.setMaximumFractionDigits(340);
                final String resultString = formattter.format(result2);
                final String s;
                filtered.add(new Result(filter + " = " + resultString, new ImageIcon("calc.png"), new RunnableAction(() -> {
                    Utils.copyToClipboard(s);
                    Utils.sendModMessage("Copied §e" + s + "§f to your clipboard!");
                    return;
                }), "Press enter to copy"));
            }
            else {
                try {
                    final String encodedSearch = URLEncoder.encode(filter.trim(), StandardCharsets.UTF_8.toString());
                    filtered.add(new Result('\"' + filter + '\"', new ImageIcon("google.png"), new RunnableAction(() -> Utils.openUrl("https://www.google.com/search?q=" + encodedSearch)), "Search on Google"));
                    filtered.add(new Result("\u201c" + filter + "\u201d", new ItemIcon(ItemUtils.getSkullItemStack("f26a2360-0158-4f76-8a34-f487883f2b04", "c9c8881e42915a9d29bb61a16fb26d059913204d265df5b439b3d792acd56")), new RunnableAction(() -> Utils.openUrl("https://hypixel-skyblock.fandom.com/wiki/Special:Search?search=" + encodedSearch)), "Search on the SkyBlock Wiki"));
                    filtered.add(new Result("\u201c" + filter + "\u201d", new ImageIcon("hypixel.png"), new RunnableAction(() -> Utils.openUrl("https://hypixel.net/search/7211960/?q=" + encodedSearch)), "Search on the Hypixel Forums"));
                }
                catch (Exception ex2) {}
            }
        }
        return filtered;
    }
    
    static {
        ResultList.results = new LinkedHashMap<String, Result>();
    }
}
