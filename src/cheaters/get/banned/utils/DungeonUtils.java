// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.utils;

import java.util.Arrays;
import net.minecraftforge.event.world.WorldEvent;
import java.util.Iterator;
import cheaters.get.banned.Shady;
import java.util.Objects;
import java.util.regex.Matcher;
import cheaters.get.banned.events.TickEndEvent;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.util.StringUtils;
import net.minecraft.network.play.server.S02PacketChat;
import cheaters.get.banned.events.PacketEvent;
import java.util.List;
import java.util.regex.Pattern;
import net.minecraft.entity.player.EntityPlayer;
import java.util.ArrayList;

public class DungeonUtils
{
    public static Floor floor;
    public static int secretsFound;
    public static int cryptsFound;
    public static boolean inBoss;
    public static boolean foundMimic;
    public static int deaths;
    public static ArrayList<EntityPlayer> teammates;
    public static boolean activeRun;
    public static int score;
    private static final Pattern scorePattern;
    private static final List<String> entryMessages;
    private static final String[] mimicMessages;
    
    public static void reset() {
        DungeonUtils.floor = null;
        DungeonUtils.secretsFound = 0;
        DungeonUtils.cryptsFound = 0;
        DungeonUtils.inBoss = false;
        DungeonUtils.foundMimic = false;
        DungeonUtils.deaths = 0;
        DungeonUtils.teammates.clear();
        DungeonUtils.activeRun = false;
        DungeonUtils.score = 0;
    }
    
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onChatPacket(final PacketEvent.ReceiveEvent event) {
        if (Utils.inDungeon && event.packet instanceof S02PacketChat && ((S02PacketChat)event.packet).func_179841_c() != 2) {
            final String text = StringUtils.func_76338_a(((S02PacketChat)event.packet).func_148915_c().func_150260_c());
            if ("[NPC] Mort: Here, I found this map when I first entered the dungeon.".equals(text)) {
                updateTeammates(getTabList());
            }
            else if (DungeonUtils.entryMessages.contains(text)) {
                DungeonUtils.inBoss = true;
            }
            else {
                for (final String message : DungeonUtils.mimicMessages) {
                    if (text.contains(message)) {
                        DungeonUtils.foundMimic = true;
                        break;
                    }
                }
            }
        }
    }
    
    @SubscribeEvent
    public void onEntityDeath(final LivingDeathEvent event) {
        if (Utils.inDungeon && !DungeonUtils.foundMimic && event.entity.getClass() == EntityZombie.class) {
            final EntityZombie entity = (EntityZombie)event.entity;
            if (entity.func_70631_g_() && entity.func_82169_q(0) == null && entity.func_82169_q(1) == null && entity.func_82169_q(2) == null && entity.func_82169_q(3) == null) {
                DungeonUtils.foundMimic = true;
            }
        }
    }
    
    @SubscribeEvent
    public void onTick(final TickEndEvent event) {
        if (!Utils.inDungeon || !event.every(10)) {
            return;
        }
        if (DungeonUtils.floor == null && !(DungeonUtils.activeRun = updateFloor())) {
            return;
        }
        final List<String> tabList = getTabList();
        if (tabList != null) {
            updateStats(tabList);
            if (DungeonUtils.teammates.isEmpty()) {
                updateTeammates(tabList);
            }
        }
        final String scoreLine = ScoreboardUtils.getLineThatContains("Cleared: ");
        if (scoreLine != null) {
            final Matcher matcher = DungeonUtils.scorePattern.matcher(scoreLine);
            if (matcher.matches()) {
                final String scoreString = matcher.group("score");
                DungeonUtils.score = Integer.parseInt(scoreString);
            }
        }
    }
    
    private static void updateTeammates(final List<String> tabList) {
        DungeonUtils.teammates.clear();
        for (int i = 0; i < 4; ++i) {
            final String text = StringUtils.func_76338_a((String)tabList.get(1 + i * 4)).trim();
            final String username = text.split(" ")[0];
            if (!Objects.equals(username, "")) {
                for (final EntityPlayer playerEntity : Shady.mc.field_71441_e.field_73010_i) {
                    if (playerEntity.func_70005_c_().equals(username)) {
                        DungeonUtils.teammates.add(playerEntity);
                    }
                }
            }
        }
    }
    
    private static boolean updateFloor() {
        final String cataLine = ScoreboardUtils.getLineThatContains("The Catacombs");
        if (cataLine != null) {
            for (final Floor floorOption : Floor.values()) {
                if (cataLine.contains(floorOption.name)) {
                    DungeonUtils.floor = floorOption;
                    return true;
                }
            }
        }
        return false;
    }
    
    private static void updateStats(final List<String> tabList) {
        try {
            for (String item : tabList) {
                if (item.contains("Deaths: ")) {
                    item = StringUtils.func_76338_a(item);
                    final String justNumbers = Utils.removeAllExceptNumbersAndPeriods(item);
                    if (justNumbers.isEmpty()) {
                        continue;
                    }
                    DungeonUtils.deaths = Integer.parseInt(justNumbers);
                }
                else if (item.contains("Secrets Found: ") && !item.contains("%")) {
                    item = StringUtils.func_76338_a(item);
                    final String justNumbers = Utils.removeAllExceptNumbersAndPeriods(item);
                    if (justNumbers.isEmpty()) {
                        continue;
                    }
                    DungeonUtils.secretsFound = Integer.parseInt(justNumbers);
                }
                else {
                    if (!item.contains("Crypts: ")) {
                        continue;
                    }
                    item = StringUtils.func_76338_a(item);
                    final String justNumbers = Utils.removeAllExceptNumbersAndPeriods(item);
                    if (justNumbers.isEmpty()) {
                        continue;
                    }
                    DungeonUtils.cryptsFound = Integer.parseInt(justNumbers);
                }
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
            Utils.sendModMessage("&cException in class DungeonUtils");
        }
    }
    
    private static List<String> getTabList() {
        final List<String> tabList = TabUtils.getTabList();
        if (tabList.size() < 18 || !tabList.get(0).contains("§r§b§lParty §r§f(")) {
            return null;
        }
        return tabList;
    }
    
    public static boolean inFloor(final Floor... floors) {
        for (final Floor floorToCheck : floors) {
            if (floorToCheck == DungeonUtils.floor) {
                return true;
            }
        }
        return false;
    }
    
    public static void debug() {
        if (Utils.inDungeon) {
            Utils.sendModMessage("Floor: " + DungeonUtils.floor.name());
            Utils.sendModMessage("In Boss: " + DungeonUtils.inBoss);
            Utils.sendModMessage("Secrets Found: " + DungeonUtils.secretsFound);
            Utils.sendModMessage("Crypts Found: " + DungeonUtils.cryptsFound);
            Utils.sendModMessage("Team:");
            for (final EntityPlayer teammate : DungeonUtils.teammates) {
                Utils.sendModMessage("- " + teammate.func_70005_c_());
            }
        }
        else {
            Utils.sendMessage("You must be in a dungeon to debug a dungeon!");
        }
    }
    
    @SubscribeEvent
    public void onWorldLoad(final WorldEvent.Load event) {
        reset();
    }
    
    static {
        DungeonUtils.floor = null;
        DungeonUtils.secretsFound = 0;
        DungeonUtils.cryptsFound = 0;
        DungeonUtils.inBoss = false;
        DungeonUtils.foundMimic = false;
        DungeonUtils.deaths = 0;
        DungeonUtils.teammates = new ArrayList<EntityPlayer>();
        DungeonUtils.activeRun = false;
        DungeonUtils.score = 0;
        scorePattern = Pattern.compile("Cleared: [0-9]{1,3}% \\((?<score>[0-9]+)\\)");
        entryMessages = Arrays.asList("[BOSS] Bonzo: Gratz for making it this far, but I\u2019m basically unbeatable.", "[BOSS] Scarf: This is where the journey ends for you, Adventurers.", "[BOSS] The Professor: I was burdened with terrible news recently...", "[BOSS] Thorn: Welcome Adventurers! I am Thorn, the Spirit! And host of the Vegan Trials!", "[BOSS] Livid: Welcome, you arrive right on time. I am Livid, the Master of Shadows.", "[BOSS] Sadan: So you made it all the way here...and you wish to defy me? Sadan?!", "[BOSS] Maxor: WELL WELL WELL LOOK WHO\u2019S HERE!");
        mimicMessages = new String[] { "Mimic Dead!", "$SKYTILS-DUNGEON-SCORE-MIMIC$", "Child Destroyed!", "Mimic Obliterated!", "Mimic Exorcised!", "Mimic Destroyed!", "Mimic Annhilated!" };
    }
    
    public enum Floor
    {
        ENTERANCE("(E)"), 
        FLOOR_1("(F1)"), 
        FLOOR_2("(F2)"), 
        FLOOR_3("(F3)"), 
        FLOOR_4("(F4)"), 
        FLOOR_5("(F5)"), 
        FLOOR_6("(F6)"), 
        FLOOR_7("(F7)"), 
        MASTER_1("(M1)"), 
        MASTER_2("(M2)"), 
        MASTER_3("(M3)"), 
        MASTER_4("(M4)"), 
        MASTER_5("(M5)"), 
        MASTER_6("(M6)"), 
        MASTER_7("(M7)");
        
        public final String name;
        
        private Floor(final String name) {
            this.name = name;
        }
    }
}
