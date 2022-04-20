// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.features;

import net.minecraftforge.event.world.WorldEvent;
import net.minecraft.client.gui.Gui;
import cheaters.get.banned.gui.config.components.ConfigInput;
import net.minecraft.client.gui.ScaledResolution;
import cheaters.get.banned.Shady;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import cheaters.get.banned.utils.ThreadUtils;
import cheaters.get.banned.gui.config.Config;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import cheaters.get.banned.utils.KeybindUtils;

public class AutoClicker
{
    private static boolean toggled;
    private static boolean burstActive;
    
    public AutoClicker() {
        KeybindUtils.register("Auto Clicker", 21);
    }
    
    @SubscribeEvent
    public void onKeyInput(final InputEvent.KeyInputEvent event) {
        if (KeybindUtils.get("Auto Clicker").func_151468_f()) {
            if (Config.autoClickerMode == 0 && !AutoClicker.burstActive) {
                AutoClicker.burstActive = true;
                int i;
                new Thread(() -> {
                    for (i = 0; i < 25 && AutoClicker.burstActive; ++i) {
                        KeybindUtils.rightClick();
                        ThreadUtils.sleep(1000 / Config.autoClickerCps);
                    }
                    AutoClicker.burstActive = false;
                }, "ShadyAddons-Autoclicker").start();
            }
            else if (Config.autoClickerMode == 1) {
                AutoClicker.toggled = !AutoClicker.toggled;
                if (AutoClicker.toggled) {
                    new Thread(() -> {
                        while (AutoClicker.toggled) {
                            KeybindUtils.rightClick();
                            ThreadUtils.sleep(1000 / Config.autoClickerCps);
                        }
                    }, "ShadyAddons-Autoclicker").start();
                }
            }
        }
    }
    
    @SubscribeEvent
    public void onOpenGui(final GuiOpenEvent event) {
        if (Config.stopAutoClickerInGui) {
            AutoClicker.toggled = false;
            AutoClicker.burstActive = false;
        }
    }
    
    @SubscribeEvent
    public void onRenderOverlay(final RenderGameOverlayEvent.Post event) {
        if (AutoClicker.toggled && Config.autoClickerIndicator && event.type == RenderGameOverlayEvent.ElementType.CROSSHAIRS) {
            final int x = new ScaledResolution(Shady.mc).func_78326_a() / 2 + 10;
            final int y = new ScaledResolution(Shady.mc).func_78328_b() / 2 - 2;
            Gui.func_73734_a(x, y, x + 5, y + 5, ConfigInput.red.getRGB());
        }
    }
    
    @SubscribeEvent
    public void onWorldLoad(final WorldEvent.Load event) {
        AutoClicker.toggled = false;
        AutoClicker.burstActive = false;
    }
    
    static {
        AutoClicker.toggled = false;
        AutoClicker.burstActive = false;
    }
}
