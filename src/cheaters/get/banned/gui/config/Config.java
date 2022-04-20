// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.gui.config;

import cheaters.get.banned.stats.RoutinesAPI;
import cheaters.get.banned.features.routines.Routines;
import cheaters.get.banned.Shady;
import cheaters.get.banned.features.commandpalette.CommandPalette;
import cheaters.get.banned.features.AutoWardrobe;
import cheaters.get.banned.utils.Utils;

public class Config
{
    @Property(type = Property.Type.BUTTON, name = "Official Discord Server", button = "Join")
    public static Runnable joinDiscord;
    @Property(type = Property.Type.BUTTON, name = "ShadyAddons Store", note = "Animated capes and more!", button = "Visit")
    public static Runnable openShop;
    @Property(type = Property.Type.SPACER)
    public static Object spacer1;
    @Property(type = Property.Type.BOOLEAN, name = "Stonkless Stonk")
    public static boolean stonklessStonk;
    @Property(type = Property.Type.BOOLEAN, name = "Always On", parent = "Stonkless Stonk")
    public static boolean alwaysOn;
    @Property(type = Property.Type.BOOLEAN, name = "Disable in Boss", parent = "Stonkless Stonk")
    public static boolean disableInBoss;
    @Property(type = Property.Type.BOOLEAN, name = "Only Enable When Holding Pickaxe", parent = "Stonkless Stonk")
    public static boolean onlyEnableWhenHoldingPickaxe;
    @Property(type = Property.Type.FOLDER, name = "Routines")
    public static boolean routinesFolder;
    @Property(type = Property.Type.BUTTON, button = "Visit", name = "Create & Share Routines", parent = "Routines")
    public static Runnable openRoutinesWebsite;
    @Property(type = Property.Type.BUTTON, button = "Open", name = "Open Routines Folder", parent = "Routines")
    public static Runnable openRoutinesFolder;
    @Property(type = Property.Type.BUTTON, button = "Reload", name = "Force Reload Routines", parent = "Routines")
    public static Runnable refreshRoutines;
    public static boolean autoSimonSays;
    public static boolean autoArrowAlign;
    @Property(type = Property.Type.BOOLEAN, name = "Royal Pigeon Pickaxe Macro")
    public static boolean royalPigeonMacro;
    @Property(type = Property.Type.FOLDER, name = "Auto-Clicker", warning = true)
    public static boolean autoClicker;
    @Property(type = Property.Type.NUMBER, name = "CPS", parent = "Auto-Clicker", min = 10, max = 500, step = 10, suffix = " CPS")
    public static int autoClickerCps;
    @Property(type = Property.Type.SELECT, name = "Mode", parent = "Auto-Clicker", options = { "Burst", "Continous" })
    public static int autoClickerMode;
    @Property(type = Property.Type.BOOLEAN, name = "Show Indicator", parent = "Auto-Clicker")
    public static boolean autoClickerIndicator;
    @Property(type = Property.Type.BOOLEAN, name = "Stop in GUI", parent = "Auto-Clicker")
    public static boolean stopAutoClickerInGui;
    @Property(type = Property.Type.BUTTON, button = "Open Palette", name = "Command Palette", note = "Command/Control + K")
    public static Runnable openCommandPalette;
    @Property(type = Property.Type.BUTTON, button = "Open Wardrobe", name = "Instant Wardrobe", note = "by RoseGold")
    public static Runnable openWardrobe;
    @Property(type = Property.Type.FOLDER, name = "Summons Features")
    public static boolean summonsFeatures;
    @Property(type = Property.Type.BOOLEAN, name = "Hide Summons", parent = "Summons Features")
    public static boolean hideSummons;
    @Property(type = Property.Type.BOOLEAN, name = "Click Through Summons", parent = "Summons Features", warning = true)
    public static boolean clickThroughSummons;
    @Property(type = Property.Type.BOOLEAN, name = "Auto-Salvage")
    public static boolean autoSalvage;
    @Property(type = Property.Type.BOOLEAN, name = "Automatically Start Salvaging", parent = "Auto-Salvage")
    public static boolean automaticallyStartSalvaging;
    @Property(type = Property.Type.BOOLEAN, name = "Auto-Sell")
    public static boolean autoSell;
    @Property(type = Property.Type.CHECKBOX, name = "Enchanted Snow & Clay", parent = "Auto-Sell")
    public static boolean autoSellMinionDrops;
    @Property(type = Property.Type.CHECKBOX, name = "Salvageable Items", parent = "Auto-Sell")
    public static boolean autoSellSalvageable;
    @Property(type = Property.Type.CHECKBOX, name = "Superboom", parent = "Auto-Sell")
    public static boolean autoSellSuperboom;
    @Property(type = Property.Type.CHECKBOX, name = "Dungeons Junk", parent = "Auto-Sell")
    public static boolean autoSellDungeonsJunk;
    @Property(type = Property.Type.CHECKBOX, name = "Speed/Weakness Potions", parent = "Auto-Sell")
    public static boolean autoSellPotions;
    public static boolean crystalReach;
    public static boolean crystalEtherwarp;
    public static int crystalSide;
    @Property(type = Property.Type.BOOLEAN, name = "Auto-Renew Crystal Hollows Pass")
    public static boolean renewCrystalHollowsPass;
    @Property(type = Property.Type.BOOLEAN, name = "Disable Block Animation")
    public static boolean disableBlockAnimation;
    @Property(type = Property.Type.FOLDER, name = "Keybinds")
    public static boolean keybinds;
    @Property(type = Property.Type.BOOLEAN, name = "Ghost Blocks", parent = "Keybinds")
    public static boolean ghostBlockKeybind;
    @Property(type = Property.Type.BOOLEAN, name = "Right-Click w/ Stonk", parent = "Ghost Blocks")
    public static boolean stonkGhostBlock;
    @Property(type = Property.Type.BOOLEAN, name = "Normal Ability", parent = "Keybinds")
    public static boolean normalAbilityKeybind;
    @Property(type = Property.Type.FOLDER, name = "Item Macros", parent = "Keybinds", warning = true)
    public static boolean itemHotkeys;
    @Property(type = Property.Type.CHECKBOX, name = "Ice Spray", parent = "Item Macros")
    public static boolean iceSprayHotkey;
    @Property(type = Property.Type.CHECKBOX, name = "Rogue Sword", parent = "Item Macros")
    public static boolean rogueSwordHotkey;
    @Property(type = Property.Type.CHECKBOX, name = "Power Orb", parent = "Item Macros")
    public static boolean powerOrbHotkey;
    @Property(type = Property.Type.CHECKBOX, name = "Weird Tuba", parent = "Item Macros")
    public static boolean weirdTubaHotkey;
    @Property(type = Property.Type.CHECKBOX, name = "Gyrokinetic Wand", parent = "Item Macros", warning = true)
    public static boolean gyrokineticWandHotkey;
    @Property(type = Property.Type.CHECKBOX, name = "Pigman Sword", parent = "Item Macros")
    public static boolean pigmanSwordHotkey;
    @Property(type = Property.Type.CHECKBOX, name = "Healing Wand", parent = "Item Macros")
    public static boolean healingWandHotkey;
    @Property(type = Property.Type.CHECKBOX, name = "Fishing Rod", parent = "Item Macros")
    public static boolean fishingRodHotkey;
    @Property(type = Property.Type.CHECKBOX, name = "Teleport w/ Anything", parent = "Item Macros")
    public static boolean teleportWithAnything;
    @Property(type = Property.Type.FOLDER, name = "Use Weapon w/ Anything", parent = "Item Macros")
    public static boolean useItemsWithAnything;
    @Property(type = Property.Type.BOOLEAN, name = "Disable Outside of Dungeons", parent = "Use Weapon w/ Anything")
    public static boolean disableOutsideDungeons;
    @Property(type = Property.Type.CHECKBOX, name = "Soul Whip w/ Anything", note = "Whip whip nae nae", parent = "Use Weapon w/ Anything")
    public static boolean soulWhipWithAnything;
    @Property(type = Property.Type.CHECKBOX, name = "Terminator w/ Anything", note = "Also works with Juju Shortbow", parent = "Use Weapon w/ Anything")
    public static boolean termWithAnything;
    @Property(type = Property.Type.CHECKBOX, name = "AOTS w/ Anything", parent = "Use Weapon w/ Anything")
    public static boolean aotsWithAnything;
    @Property(type = Property.Type.BOOLEAN, name = "Auto-Ready Up", warning = true)
    public static boolean autoReadyUp;
    @Property(type = Property.Type.FOLDER, name = "Block Abilities")
    public static boolean blockAbilities;
    @Property(type = Property.Type.CHECKBOX, name = "Cells Alignment", parent = "Block Abilities")
    public static boolean blockCellsAlignment;
    @Property(type = Property.Type.CHECKBOX, name = "Giant's Slam", parent = "Block Abilities")
    public static boolean blockGiantsSlam;
    @Property(type = Property.Type.CHECKBOX, name = "Valkyrie Wither Impact", parent = "Block Abilities")
    public static boolean blockValkyrie;
    @Property(type = Property.Type.BOOLEAN, name = "Dungeon Map")
    public static boolean dungeonMap;
    public static boolean darkenUnexplored;
    @Property(type = Property.Type.SELECT, name = "Show Room Names", parent = "Dungeon Map", options = { "None", "Important", "All" })
    public static int showRoomNames;
    @Property(type = Property.Type.BOOLEAN, name = "Show Run Information", parent = "Dungeon Map")
    public static boolean showDungeonInfo;
    @Property(type = Property.Type.SELECT, name = "Show Player Heads", parent = "Dungeon Map", options = { "None", "All", "Own" })
    public static int showMapPlayerHeads;
    @Property(type = Property.Type.SELECT, name = "Map Border", parent = "Dungeon Map", options = { "None", "Chroma", "Black", "White" })
    public static int mapBorder;
    @Property(type = Property.Type.NUMBER, name = "Horizontal Offset", parent = "Dungeon Map", suffix = "px", step = 10)
    public static int mapXOffset;
    @Property(type = Property.Type.NUMBER, name = "Vertical Offset", parent = "Dungeon Map", suffix = "px", step = 10)
    public static int mapYOffset;
    @Property(type = Property.Type.NUMBER, name = "Scale", parent = "Dungeon Map", suffix = "%", step = 10, min = 50, max = 150)
    public static int mapScale;
    @Property(type = Property.Type.NUMBER, name = "Background Opacity", parent = "Dungeon Map", max = 100, step = 10, suffix = "%")
    public static int mapBackgroundOpacity;
    @Property(type = Property.Type.BOOLEAN, name = "Quick Math(s) Solver")
    public static boolean socialQuickMathsSolver;
    @Property(type = Property.Type.NUMBER, name = "Answer Delay", parent = "Quick Math(s) Solver", suffix = "ms", min = 50, max = 3000, step = 50)
    public static int quickMathsAnswerDelay;
    @Property(type = Property.Type.BOOLEAN, name = "Enable Outside of SkyBlock", parent = "Quick Math(s) Solver")
    public static boolean enableMathsOutsideSkyBlock;
    @Property(type = Property.Type.BOOLEAN, name = "Connect Four Helper", beta = true)
    public static boolean connectFourAI;
    @Property(type = Property.Type.FOLDER, name = "Auto-Close Chests")
    public static boolean closeChests;
    @Property(type = Property.Type.BOOLEAN, name = "Secret Chests", parent = "Auto-Close Chests")
    public static boolean closeSecretChests;
    @Property(type = Property.Type.BOOLEAN, name = "Crystal Hollows Chests", parent = "Auto-Close Chests")
    public static boolean closeCrystalHollowsChests;
    @Property(type = Property.Type.BOOLEAN, name = "Auto-GG")
    public static boolean autoGg;
    @Property(type = Property.Type.FOLDER, name = "Show Hidden Mobs")
    public static boolean showHiddenMobs;
    @Property(type = Property.Type.CHECKBOX, name = "Shadow Assassins", parent = "Show Hidden Mobs")
    public static boolean showHiddenShadowAssassins;
    @Property(type = Property.Type.CHECKBOX, name = "Fels", parent = "Show Hidden Mobs")
    public static boolean showHiddenFels;
    @Property(type = Property.Type.CHECKBOX, name = "Ghosts", parent = "Show Hidden Mobs")
    public static boolean showGhosts;
    @Property(type = Property.Type.CHECKBOX, name = "Stealthy Blood Mobs", parent = "Show Hidden Mobs")
    public static boolean showStealthyBloodMobs;
    @Property(type = Property.Type.CHECKBOX, name = "Sneaky Creepers", parent = "Show Hidden Mobs")
    public static boolean showSneakyCreepers;
    @Property(type = Property.Type.FOLDER, name = "Mob ESP", note = "Disable Entity Culling in Patcher")
    public static boolean mobEsp;
    @Property(type = Property.Type.NUMBER, name = "Outline Thickness", parent = "Mob ESP", min = 3, max = 10, suffix = "px")
    public static int espThickness;
    @Property(type = Property.Type.CHECKBOX, name = "Sludges", parent = "Mob ESP")
    public static boolean sludgeEsp;
    @Property(type = Property.Type.CHECKBOX, name = "Yogs", parent = "Mob ESP")
    public static boolean yogEsp;
    @Property(type = Property.Type.CHECKBOX, name = "Corleone", parent = "Mob ESP")
    public static boolean corleoneEsp;
    @Property(type = Property.Type.CHECKBOX, name = "Starred Mobs", parent = "Mob ESP")
    public static boolean starredMobEsp;
    @Property(type = Property.Type.CHECKBOX, name = "Secret Bats", parent = "Mob ESP")
    public static boolean secretBatEsp;
    @Property(type = Property.Type.CHECKBOX, name = "Minibosses", parent = "Mob ESP")
    public static boolean minibossEsp;
    @Property(type = Property.Type.FOLDER, name = "Gemstone ESP")
    public static boolean gemstoneEsp;
    @Property(type = Property.Type.BOOLEAN, name = "Include Glass Panes", parent = "Gemstone ESP")
    public static boolean includeGlassPanes;
    @Property(type = Property.Type.SELECT, name = "Highlight Mode", parent = "Gemstone ESP", options = { "Outlined", "Filled" })
    public static int highlightMode;
    @Property(type = Property.Type.NUMBER, name = "Scan Radius", parent = "Gemstone ESP", suffix = " blocks", min = 5, max = 30)
    public static int gemstoneRadius;
    @Property(type = Property.Type.CHECKBOX, name = "Ruby", parent = "Gemstone ESP")
    public static boolean rubyEsp;
    @Property(type = Property.Type.CHECKBOX, name = "Amber", parent = "Gemstone ESP")
    public static boolean amberEsp;
    @Property(type = Property.Type.CHECKBOX, name = "Sapphire", parent = "Gemstone ESP")
    public static boolean sapphireEsp;
    @Property(type = Property.Type.CHECKBOX, name = "Jade", parent = "Gemstone ESP")
    public static boolean jadeEsp;
    @Property(type = Property.Type.CHECKBOX, name = "Amethyst", parent = "Gemstone ESP")
    public static boolean amethystEsp;
    @Property(type = Property.Type.CHECKBOX, name = "Topaz", parent = "Gemstone ESP")
    public static boolean topazEsp;
    @Property(type = Property.Type.CHECKBOX, name = "Jasper", parent = "Gemstone ESP")
    public static boolean jasperEsp;
    @Property(type = Property.Type.BOOLEAN, name = "Auto-Melody")
    public static boolean autoMelody;
    @Property(type = Property.Type.BOOLEAN, name = "No Teleport Rotation", warning = true, note = "Only in SkyBlock")
    public static boolean noRotate;
    @Property(type = Property.Type.BOOLEAN, name = "Anti-Knockback", warning = true, note = "Only in SkyBlock")
    public static boolean antiKb;
    @Property(type = Property.Type.BOOLEAN, name = "Auto-Terminals")
    public static boolean autoTerminals;
    @Property(type = Property.Type.BOOLEAN, name = "Half-Trip", note = "Attempts to send clicks before window is updated", parent = "Auto-Terminals", warning = true)
    public static boolean terminalHalfTrip;
    @Property(type = Property.Type.NUMBER, name = "Click Delay", note = "Adjust this to compensate for your ping", parent = "Auto-Terminals", step = 10, suffix = "ms", min = 50, max = 1000)
    public static int terminalClickDelay;
    @Property(type = Property.Type.BOOLEAN, name = "Use Clear Buttons", note = "Not a cheat, just cosmetic")
    public static boolean useCleanButtons;
    
    static {
        Config.joinDiscord = (() -> Utils.openUrl("https://shadyaddons.com/discord"));
        Config.openShop = (() -> Utils.openUrl("https://shadyaddons.com/store"));
        Config.stonklessStonk = false;
        Config.alwaysOn = false;
        Config.disableInBoss = false;
        Config.onlyEnableWhenHoldingPickaxe = false;
        Config.routinesFolder = false;
        Config.openRoutinesWebsite = RoutinesAPI::openAuthWebsite;
        Config.openRoutinesFolder = (() -> Utils.executeCommand("/shady routines"));
        Config.refreshRoutines = (() -> {
            Utils.sendModMessage("Reloading routines...");
            Routines.load();
            return;
        });
        Config.autoSimonSays = false;
        Config.autoArrowAlign = false;
        Config.royalPigeonMacro = false;
        Config.autoClicker = false;
        Config.autoClickerCps = 100;
        Config.autoClickerMode = 0;
        Config.autoClickerIndicator = false;
        Config.stopAutoClickerInGui = false;
        Config.openCommandPalette = (() -> {
            Shady.guiToOpen = new CommandPalette();
            Utils.sendModMessage("You can customize the shortcut in Minecraft controls (which you can open with the Command Palette!)");
            return;
        });
        Config.openWardrobe = (() -> {
            if (Utils.inSkyBlock) {
                AutoWardrobe.open(1, 0);
                Utils.sendModMessage("Use /sh wardrobe [slot] to equip a specific set!");
            }
            else {
                Utils.sendModMessage("You must be in SkyBlock to use this!");
            }
            return;
        });
        Config.summonsFeatures = false;
        Config.hideSummons = false;
        Config.clickThroughSummons = false;
        Config.autoSalvage = false;
        Config.automaticallyStartSalvaging = false;
        Config.autoSell = false;
        Config.autoSellMinionDrops = false;
        Config.autoSellSalvageable = false;
        Config.autoSellSuperboom = false;
        Config.autoSellDungeonsJunk = false;
        Config.autoSellPotions = false;
        Config.crystalReach = false;
        Config.crystalEtherwarp = false;
        Config.crystalSide = 0;
        Config.renewCrystalHollowsPass = false;
        Config.disableBlockAnimation = false;
        Config.keybinds = false;
        Config.ghostBlockKeybind = false;
        Config.stonkGhostBlock = false;
        Config.normalAbilityKeybind = false;
        Config.itemHotkeys = false;
        Config.iceSprayHotkey = false;
        Config.rogueSwordHotkey = false;
        Config.powerOrbHotkey = false;
        Config.weirdTubaHotkey = false;
        Config.gyrokineticWandHotkey = false;
        Config.pigmanSwordHotkey = false;
        Config.healingWandHotkey = false;
        Config.fishingRodHotkey = false;
        Config.teleportWithAnything = false;
        Config.disableOutsideDungeons = false;
        Config.soulWhipWithAnything = false;
        Config.termWithAnything = false;
        Config.aotsWithAnything = false;
        Config.autoReadyUp = false;
        Config.blockAbilities = false;
        Config.blockCellsAlignment = false;
        Config.blockGiantsSlam = false;
        Config.blockValkyrie = false;
        Config.dungeonMap = false;
        Config.darkenUnexplored = false;
        Config.showRoomNames = 0;
        Config.showDungeonInfo = false;
        Config.showMapPlayerHeads = 1;
        Config.mapBorder = 0;
        Config.mapXOffset = 10;
        Config.mapYOffset = 10;
        Config.mapScale = 80;
        Config.mapBackgroundOpacity = 30;
        Config.socialQuickMathsSolver = false;
        Config.quickMathsAnswerDelay = 100;
        Config.enableMathsOutsideSkyBlock = false;
        Config.connectFourAI = false;
        Config.closeChests = false;
        Config.closeSecretChests = false;
        Config.closeCrystalHollowsChests = false;
        Config.autoGg = false;
        Config.showHiddenMobs = false;
        Config.showHiddenShadowAssassins = false;
        Config.showHiddenFels = false;
        Config.showGhosts = false;
        Config.showStealthyBloodMobs = false;
        Config.showSneakyCreepers = false;
        Config.mobEsp = false;
        Config.espThickness = 5;
        Config.sludgeEsp = false;
        Config.yogEsp = false;
        Config.corleoneEsp = false;
        Config.starredMobEsp = false;
        Config.secretBatEsp = false;
        Config.minibossEsp = false;
        Config.gemstoneEsp = false;
        Config.includeGlassPanes = false;
        Config.highlightMode = 0;
        Config.gemstoneRadius = 15;
        Config.rubyEsp = false;
        Config.amberEsp = false;
        Config.sapphireEsp = false;
        Config.jadeEsp = false;
        Config.amethystEsp = false;
        Config.topazEsp = false;
        Config.jasperEsp = false;
        Config.autoMelody = false;
        Config.noRotate = false;
        Config.antiKb = false;
        Config.autoTerminals = false;
        Config.terminalHalfTrip = false;
        Config.terminalClickDelay = 100;
        Config.useCleanButtons = false;
    }
}
