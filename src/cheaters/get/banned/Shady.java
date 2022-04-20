// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned;

import cheaters.get.banned.gui.config.settings.SelectSetting;
import cheaters.get.banned.gui.config.settings.BooleanSetting;
import cheaters.get.banned.remote.UpdateGui;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraftforge.client.event.GuiOpenEvent;
import cheaters.get.banned.features.commandpalette.CommandPalette;
import org.lwjgl.input.Keyboard;
import org.apache.commons.lang3.SystemUtils;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import java.util.regex.Matcher;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import java.util.concurrent.TimeUnit;
import net.minecraftforge.fml.common.eventhandler.Event;
import cheaters.get.banned.events.MinuteEvent;
import java.util.concurrent.Executors;
import java.time.temporal.Temporal;
import java.time.Duration;
import cheaters.get.banned.remote.Capes;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import java.util.Iterator;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraft.client.settings.KeyBinding;
import cheaters.get.banned.utils.KeybindUtils;
import cheaters.get.banned.features.map.MapView;
import cheaters.get.banned.features.AntiKB;
import cheaters.get.banned.features.NoRotate;
import cheaters.get.banned.features.AutoWardrobe;
import cheaters.get.banned.features.connectfoursolver.ConnectFourSolver;
import cheaters.get.banned.features.SocialCommandSolver;
import cheaters.get.banned.features.AutoSell;
import cheaters.get.banned.features.AutoSalvage;
import cheaters.get.banned.features.AutoReadyUp;
import cheaters.get.banned.features.AutoMelody;
import cheaters.get.banned.features.AutoTerminals;
import cheaters.get.banned.features.GemstoneESP;
import cheaters.get.banned.features.MobESP;
import cheaters.get.banned.features.ItemMacro;
import cheaters.get.banned.features.TeleportWithAnything;
import cheaters.get.banned.features.HideSummons;
import cheaters.get.banned.features.ShowHiddenEntities;
import cheaters.get.banned.features.DisableSwordAnimation;
import cheaters.get.banned.features.AutoRenewCrystalHollows;
import cheaters.get.banned.features.AutoClicker;
import cheaters.get.banned.features.AbilityKeybind;
import cheaters.get.banned.features.AutoGG;
import cheaters.get.banned.features.RoyalPigeonMacro;
import cheaters.get.banned.features.AutoCloseChest;
import cheaters.get.banned.features.GhostBlocks;
import cheaters.get.banned.features.StonklessStonk;
import cheaters.get.banned.features.BlockAbilities;
import cheaters.get.banned.features.routines.RoutineHooks;
import cheaters.get.banned.stats.MiscStats;
import cheaters.get.banned.utils.RotationUtils;
import cheaters.get.banned.utils.DungeonUtils;
import cheaters.get.banned.utils.LocationUtils;
import cheaters.get.banned.utils.Utils;
import cheaters.get.banned.events.TickEndEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import cheaters.get.banned.stats.Analytics;
import cheaters.get.banned.remote.Updater;
import cheaters.get.banned.features.map.MapController;
import cheaters.get.banned.features.routines.Routines;
import cheaters.get.banned.gui.config.ConfigLogic;
import cheaters.get.banned.remote.DisableFeatures;
import cheaters.get.banned.gui.config.Config;
import cheaters.get.banned.utils.EstonianUtils;
import net.minecraft.command.ICommand;
import cheaters.get.banned.gui.config.MainCommand;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import cheaters.get.banned.gui.config.settings.Setting;
import java.util.ArrayList;
import java.util.regex.Pattern;
import net.minecraft.client.gui.GuiScreen;
import java.time.LocalDateTime;
import java.io.File;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.Mod;

@Mod(modid = "autogg", name = "ShadyAddons", version = "4.1.3", clientSideOnly = true, acceptedMinecraftVersions = "[1.8.9]")
public class Shady
{
    public static final String MOD_NAME = "ShadyAddons";
    public static final String MOD_ID = "autogg";
    public static final String VERSION = "2.7.2";
    public static final boolean BETA;
    public static final Minecraft mc;
    public static boolean shouldCrash;
    public static final File dir;
    public static final LocalDateTime loadTime;
    public static boolean USING_SBA;
    public static boolean USING_PATCHER;
    public static boolean USING_SKYTILS;
    public static boolean USING_SBE;
    public static GuiScreen guiToOpen;
    public static boolean enabled;
    private static boolean sentPlayTimeCommand;
    private static boolean sentPlayTimeData;
    private static Pattern playTimePattern;
    public static ArrayList<Setting> settings;
    
    @Mod.EventHandler
    public void preInit(final FMLPreInitializationEvent event) {
        if (!Shady.dir.exists()) {
            Shady.dir.mkdirs();
        }
        ClientCommandHandler.instance.func_71560_a((ICommand)new MainCommand());
        EstonianUtils.loadEstonian();
        Shady.settings = ConfigLogic.collect(Config.class, DisableFeatures.load());
        ConfigLogic.load();
        Routines.load();
        MapController.loadRooms();
        Updater.check();
        Analytics.collect("version", "2.7.2");
    }
    
    @Mod.EventHandler
    public void init(final FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register((Object)new TickEndEvent());
        MinecraftForge.EVENT_BUS.register((Object)this);
        MinecraftForge.EVENT_BUS.register((Object)new Utils());
        MinecraftForge.EVENT_BUS.register((Object)new LocationUtils());
        MinecraftForge.EVENT_BUS.register((Object)new DungeonUtils());
        MinecraftForge.EVENT_BUS.register((Object)new RotationUtils());
        MinecraftForge.EVENT_BUS.register((Object)new MiscStats());
        MinecraftForge.EVENT_BUS.register((Object)new RoutineHooks());
        MinecraftForge.EVENT_BUS.register((Object)new BlockAbilities());
        MinecraftForge.EVENT_BUS.register((Object)new StonklessStonk());
        MinecraftForge.EVENT_BUS.register((Object)new GhostBlocks());
        MinecraftForge.EVENT_BUS.register((Object)new AutoCloseChest());
        MinecraftForge.EVENT_BUS.register((Object)new RoyalPigeonMacro());
        MinecraftForge.EVENT_BUS.register((Object)new AutoGG());
        MinecraftForge.EVENT_BUS.register((Object)new AbilityKeybind());
        MinecraftForge.EVENT_BUS.register((Object)new AutoClicker());
        MinecraftForge.EVENT_BUS.register((Object)new AutoRenewCrystalHollows());
        MinecraftForge.EVENT_BUS.register((Object)new DisableSwordAnimation());
        MinecraftForge.EVENT_BUS.register((Object)new ShowHiddenEntities());
        MinecraftForge.EVENT_BUS.register((Object)new HideSummons());
        MinecraftForge.EVENT_BUS.register((Object)new TeleportWithAnything());
        MinecraftForge.EVENT_BUS.register((Object)new ItemMacro());
        MinecraftForge.EVENT_BUS.register((Object)new MobESP());
        MinecraftForge.EVENT_BUS.register((Object)new GemstoneESP());
        MinecraftForge.EVENT_BUS.register((Object)new AutoTerminals());
        MinecraftForge.EVENT_BUS.register((Object)new AutoMelody());
        MinecraftForge.EVENT_BUS.register((Object)new AutoReadyUp());
        MinecraftForge.EVENT_BUS.register((Object)new AutoSalvage());
        MinecraftForge.EVENT_BUS.register((Object)new AutoSell());
        MinecraftForge.EVENT_BUS.register((Object)new SocialCommandSolver());
        MinecraftForge.EVENT_BUS.register((Object)new ConnectFourSolver());
        MinecraftForge.EVENT_BUS.register((Object)new AutoWardrobe());
        MinecraftForge.EVENT_BUS.register((Object)new NoRotate());
        MinecraftForge.EVENT_BUS.register((Object)new AntiKB());
        MinecraftForge.EVENT_BUS.register((Object)new MapController());
        MinecraftForge.EVENT_BUS.register((Object)new MapView());
        KeybindUtils.register("Command Palette", 37);
        for (final KeyBinding keyBinding : KeybindUtils.keyBindings.values()) {
            ClientRegistry.registerKeyBinding(keyBinding);
        }
    }
    
    @Mod.EventHandler
    public void postInit(final FMLPostInitializationEvent event) {
        Shady.USING_SBA = Loader.isModLoaded("skyblockaddons");
        Shady.USING_PATCHER = Loader.isModLoaded("patcher");
        Shady.USING_SKYTILS = Loader.isModLoaded("skytils");
        Shady.USING_SKYTILS = Loader.isModLoaded("skytils");
        Shady.USING_SBE = Loader.isModLoaded("skyblockextras");
        Capes.load();
        final LocalDateTime now = LocalDateTime.now();
        final LocalDateTime firstRun = now.withSecond(0).plusMinutes(1L);
        final Duration initialDelay = Duration.between(now, firstRun);
        final long initalDelaySeconds = initialDelay.getSeconds();
        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(() -> MinecraftForge.EVENT_BUS.post((Event)new MinuteEvent()), initalDelaySeconds, 60L, TimeUnit.SECONDS);
    }
    
    @SubscribeEvent
    public void onMinute(final MinuteEvent event) {
        if (MiscStats.minutesSinceLastSend == 5) {
            MiscStats.minutesSinceLastSend = 0;
            MiscStats.send();
        }
        if (EstonianUtils.isEstoniaDay() && Shady.mc.field_71441_e != null && Math.random() > 0.9) {
            EstonianUtils.playFolkSong();
        }
        ++MiscStats.minutesSinceLastSend;
    }
    
    @SubscribeEvent
    public void onTick(final TickEndEvent event) {
        if (Shady.guiToOpen != null) {
            Shady.mc.func_147108_a(Shady.guiToOpen);
            Shady.guiToOpen = null;
        }
        if (Shady.shouldCrash) {
            throw new NullPointerException("You did this to yourself! Isn't it wonderful?");
        }
        if (Utils.inSkyBlock && !Shady.sentPlayTimeCommand) {
            Utils.sendMessageAsPlayer("/playtime");
            Shady.sentPlayTimeCommand = true;
        }
    }
    
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onChat(final ClientChatReceivedEvent event) {
        if (Utils.inSkyBlock && Shady.sentPlayTimeCommand && !Shady.sentPlayTimeData && event.message.func_150260_c().contains("minutes playtime!")) {
            final Matcher matcher = Shady.playTimePattern.matcher(event.message.func_150260_c());
            if (matcher.matches()) {
                Analytics.collect("playtime", matcher.group(1));
                event.setCanceled(true);
                Shady.sentPlayTimeData = true;
            }
        }
    }
    
    @SubscribeEvent
    public void onInput(final InputEvent.KeyInputEvent event) {
        if (KeybindUtils.isPressed("Command Palette")) {
            if (SystemUtils.IS_OS_MAC || SystemUtils.IS_OS_MAC_OSX) {
                if (!Keyboard.isKeyDown(219) && !Keyboard.isKeyDown(220)) {
                    return;
                }
            }
            else if (!Keyboard.isKeyDown(29) && !Keyboard.isKeyDown(157)) {
                return;
            }
            Shady.guiToOpen = new CommandPalette();
            MiscStats.add(MiscStats.Metric.COMMAND_PALETTE_OPENS);
        }
    }
    
    @SubscribeEvent
    public void onGuiOpen(final GuiOpenEvent event) {
        if (Updater.shouldUpdate && event.gui instanceof GuiMainMenu) {
            Shady.guiToOpen = new UpdateGui();
            Updater.shouldUpdate = false;
        }
    }
    
    public static void disable() {
        Shady.enabled = false;
        for (final Setting setting : Shady.settings) {
            if (setting instanceof BooleanSetting) {
                setting.set(false);
            }
            if (setting instanceof SelectSetting) {
                setting.set(0);
            }
        }
    }
    
    static {
        BETA = ("2.7.2".contains("-pre") || "2.7.2".equals("@VERSION@"));
        mc = Minecraft.func_71410_x();
        Shady.shouldCrash = false;
        dir = new File(new File(Shady.mc.field_71412_D, "config"), "shady");
        loadTime = LocalDateTime.now();
        Shady.USING_SBA = false;
        Shady.USING_PATCHER = false;
        Shady.USING_SKYTILS = false;
        Shady.USING_SBE = false;
        Shady.guiToOpen = null;
        Shady.enabled = true;
        Shady.sentPlayTimeCommand = false;
        Shady.sentPlayTimeData = false;
        Shady.playTimePattern = Pattern.compile("You have (\\d*) hours and \\d* minutes playtime!");
        Shady.settings = new ArrayList<Setting>();
    }
}
