// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.gui.config;

import net.minecraft.command.CommandException;
import java.util.Iterator;
import net.minecraft.util.ResourceLocation;
import cheaters.get.banned.features.map.MapController;
import cheaters.get.banned.features.map.MapScanner;
import cheaters.get.banned.utils.EstonianUtils;
import cheaters.get.banned.features.commandpalette.CommandPalette;
import org.apache.commons.lang3.StringUtils;
import cheaters.get.banned.features.routines.Routine;
import cheaters.get.banned.utils.DungeonUtils;
import cheaters.get.banned.utils.MathUtils;
import cheaters.get.banned.features.AutoWardrobe;
import java.io.IOException;
import cheaters.get.banned.features.routines.Routines;
import java.awt.Desktop;
import cheaters.get.banned.stats.RoutinesAPI;
import cheaters.get.banned.utils.Utils;
import org.apache.commons.lang3.RandomStringUtils;
import cheaters.get.banned.Shady;
import net.minecraft.command.ICommandSender;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.command.CommandBase;

public class MainCommand extends CommandBase
{
    private static final String UNKNOWN_COMMAND = "Unrecognized command!";
    private static final String INVALID_ARGUMENTS = "Invalid arguments!";
    
    public String func_71517_b() {
        return "sh";
    }
    
    public List<String> func_71514_a() {
        return new ArrayList<String>() {
            {
                this.add("shady");
                this.add("shadyaddons");
            }
        };
    }
    
    public String func_71518_a(final ICommandSender sender) {
        return "/" + this.func_71517_b();
    }
    
    public void func_71515_b(final ICommandSender sender, final String[] args) throws CommandException {
        if (!Shady.enabled) {
            Utils.sendMessageAsPlayer("/" + RandomStringUtils.random(10, true, false));
            return;
        }
        if (args.length > 0) {
            final String s = args[0];
            switch (s) {
                case "install": {
                    if (args.length == 2) {
                        RoutinesAPI.download(args[1]);
                        break;
                    }
                    Utils.sendModMessage("No routine ID specified");
                    break;
                }
                case "routines": {
                    try {
                        Desktop.getDesktop().open(Routines.routinesDir);
                    }
                    catch (IOException e) {
                        Utils.sendModMessage("Unable to open directory");
                        Utils.sendModMessage("Find it manually at .../minecraft/config/shady/routines");
                    }
                    break;
                }
                case "wardrobe": {
                    if (!Utils.inSkyBlock) {
                        Utils.sendModMessage("You must be in SkyBlock to use this command!");
                        return;
                    }
                    if (args.length == 1) {
                        AutoWardrobe.open(1, 0);
                        return;
                    }
                    if (!MathUtils.isNumber(args[1])) {
                        Utils.sendModMessage("Invalid arguments!");
                        return;
                    }
                    if (args.length == 2) {
                        final int slot = Integer.parseInt(args[1]);
                        if (slot > 0 && slot <= 9) {
                            AutoWardrobe.open(1, slot);
                            return;
                        }
                        if (slot < 18) {
                            AutoWardrobe.open(2, slot % 9);
                            return;
                        }
                        if (slot == 18) {
                            AutoWardrobe.open(2, 9);
                            return;
                        }
                        Utils.sendModMessage("Invalid arguments!");
                        return;
                    }
                    else {
                        if (args.length != 3) {
                            break;
                        }
                        if (!MathUtils.isNumber(args[2])) {
                            Utils.sendModMessage("Invalid arguments!");
                            return;
                        }
                        int page = Integer.parseInt(args[1]);
                        final int slot2 = Integer.parseInt(args[2]);
                        if (page > 2) {
                            page = 2;
                        }
                        AutoWardrobe.open(page, slot2 % 9);
                        break;
                    }
                    break;
                }
                case "force_dungeon": {
                    Utils.forceDungeon = !Utils.forceDungeon;
                    Utils.sendModMessage("Toggled forcing dungeon to " + Utils.forceDungeon);
                    break;
                }
                case "force_skyblock": {
                    Utils.forceSkyBlock = !Utils.forceSkyBlock;
                    Utils.sendModMessage("Toggled forcing SkyBlock to " + Utils.forceSkyBlock);
                    break;
                }
                case "debug": {
                    if (args.length > 1) {
                        final String s2 = args[1];
                        switch (s2) {
                            case "dungeon": {
                                if (Utils.inDungeon) {
                                    DungeonUtils.debug();
                                    break;
                                }
                                break;
                            }
                            case "routines": {
                                for (final Routine routine : Routines.routines.values()) {
                                    Utils.sendModMessage(StringUtils.rightPad(routine.name + ' ', 40, '-'));
                                    Utils.sendModMessage("Concurrent: " + (routine.allowConcurrent ? "true" : "false"));
                                    Utils.sendModMessage("Trigger: " + routine.trigger.getClass().getSimpleName());
                                    Utils.sendModMessage("Actions: " + routine.actions.size());
                                }
                                break;
                            }
                            case "palette": {
                                Shady.guiToOpen = new CommandPalette();
                                break;
                            }
                            case "estonia": {
                                EstonianUtils.playFolkSong();
                                break;
                            }
                            case "crash": {
                                Shady.shouldCrash = true;
                                break;
                            }
                            case "copy_look": {
                                if (Shady.mc.field_71476_x != null) {
                                    Utils.copyToClipboard(Shady.mc.field_71476_x.func_178782_a().func_177958_n() + ", " + Shady.mc.field_71476_x.func_178782_a().func_177956_o() + ", " + Shady.mc.field_71476_x.func_178782_a().func_177952_p());
                                    break;
                                }
                                break;
                            }
                            case "scan": {
                                MapController.scannedMap = MapScanner.getScan();
                                Utils.sendModMessage("Forced scan, check logs for any errors");
                                if (MapController.scannedMap == null) {
                                    Utils.sendModMessage("Map is null");
                                    break;
                                }
                                break;
                            }
                            case "core": {
                                Utils.sendModMessage("Core: " + MapScanner.getCore(Shady.mc.field_71439_g.func_180425_c().func_177958_n(), Shady.mc.field_71439_g.func_180425_c().func_177952_p()));
                                break;
                            }
                            default: {
                                Utils.sendModMessage("&cDebug command not found");
                                break;
                            }
                        }
                        break;
                    }
                    break;
                }
                case "disable": {
                    Shady.disable();
                    break;
                }
                default: {
                    Utils.sendModMessage("Unrecognized command!");
                    break;
                }
            }
        }
        else {
            Shady.guiToOpen = new ConfigGui(new ResourceLocation("shadyaddons:" + Utils.getLogo() + ".png"));
        }
    }
    
    public int func_82362_a() {
        return 0;
    }
}
