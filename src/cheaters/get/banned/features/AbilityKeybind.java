// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.features;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import cheaters.get.banned.Shady;
import cheaters.get.banned.utils.Utils;
import cheaters.get.banned.gui.config.Config;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import cheaters.get.banned.utils.KeybindUtils;

public class AbilityKeybind
{
    public AbilityKeybind() {
        KeybindUtils.register("Use Normal Ability", 45);
    }
    
    @SubscribeEvent
    public void onKeyInput(final InputEvent.KeyInputEvent event) {
        if (Config.normalAbilityKeybind && KeybindUtils.get("Use Normal Ability").func_151468_f() && Utils.inDungeon) {
            Shady.mc.field_71439_g.func_71040_bB(true);
        }
    }
}
