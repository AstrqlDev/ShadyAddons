// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.gui.config;

import java.io.IOException;
import org.lwjgl.input.Mouse;
import java.util.Iterator;
import net.minecraft.util.MathHelper;
import net.minecraft.client.gui.GuiButton;
import cheaters.get.banned.gui.config.settings.FolderSetting;
import cheaters.get.banned.gui.config.settings.BooleanSetting;
import cheaters.get.banned.utils.RenderUtils;
import cheaters.get.banned.gui.config.components.ConfigInput;
import cheaters.get.banned.utils.FontUtils;
import cheaters.get.banned.Shady;
import net.minecraft.client.renderer.GlStateManager;
import cheaters.get.banned.gui.config.components.Scrollbar;
import net.minecraft.util.ResourceLocation;
import cheaters.get.banned.gui.config.settings.Setting;
import java.util.ArrayList;
import net.minecraft.client.gui.GuiScreen;

public class ConfigGui extends GuiScreen
{
    public static ArrayList<Setting> settings;
    private int prevMouseY;
    private int scrollOffset;
    private boolean scrolling;
    private ResourceLocation logo;
    private Scrollbar scrollbar;
    private final int columnWidth = 300;
    private final int headerHeight = 109;
    private Integer prevWidth;
    private Integer prevHeight;
    
    public ConfigGui(final ResourceLocation logo) {
        this.scrollOffset = 0;
        this.scrolling = false;
        this.prevWidth = null;
        this.prevHeight = null;
        this.logo = logo;
        ConfigGui.settings = this.getFilteredSettings();
    }
    
    public void func_73863_a(final int mouseX, final int mouseY, final float partialTicks) {
        this.func_146276_q_();
        super.func_73863_a(mouseX, mouseY, partialTicks);
        this.mouseMoved(mouseY);
        GlStateManager.func_179124_c(255.0f, 255.0f, 255.0f);
        Shady.mc.func_110434_K().func_110577_a(this.logo);
        func_146110_a(this.field_146294_l / 2 - 143, 24 - this.scrollOffset, 0.0f, 0.0f, 286, 40, 286.0f, 40.0f);
        FontUtils.drawCenteredString((Shady.BETA ? "Beta \u2726 " : "Stable \u2726 ") + "2.7.2", this.field_146294_l / 2, 67 - this.scrollOffset + FontUtils.LINE_HEIGHT);
        for (int i = 0; i < ConfigGui.settings.size(); ++i) {
            final Setting setting = ConfigGui.settings.get(i);
            int x = this.getOffset();
            final int y = 109 + i * 15 - this.scrollOffset;
            x += setting.getIndent(0);
            if (setting.parent == null && i > 0) {
                func_73734_a(x, y - 3, this.getOffset() + 300, y - 2, ConfigInput.transparent.getRGB());
            }
            if (setting.warning || setting.beta) {
                if (setting.warning) {
                    final int textureX = (int)(System.currentTimeMillis() / 1000L % 2L * 9L);
                    RenderUtils.drawTexture(new ResourceLocation("shadyaddons:warning.png"), x, y, 9, 9, 18, 9, textureX, 0);
                }
                else {
                    final int textureX = (int)(System.currentTimeMillis() / 100L % 10L * 9L);
                    RenderUtils.drawTexture(new ResourceLocation("shadyaddons:beta.png"), x, y, 9, 9, 90, 9, textureX, 0);
                }
                x += 13;
            }
            char color = 'f';
            if (setting instanceof BooleanSetting && setting.get(Boolean.class)) {
                color = 'a';
            }
            if (setting instanceof FolderSetting && ((FolderSetting)setting).isChildEnabled()) {
                color = 'a';
            }
            FontUtils.drawString("§" + color + setting.name, x, y + 1, false);
            if (setting.note != null) {
                final int settingNameWidth = FontUtils.getStringWidth(setting.name + " ");
                GlStateManager.func_179137_b(0.0, 1.8, 0.0);
                FontUtils.drawScaledString("§7" + setting.note, 0.8f, x + settingNameWidth, y + 1, false);
                GlStateManager.func_179137_b(0.0, -1.8, 0.0);
            }
        }
        if (this.prevHeight != null && this.prevWidth != null && (this.prevWidth != this.field_146294_l || this.prevHeight != this.field_146295_m)) {
            Shady.mc.func_147108_a((GuiScreen)new ConfigGui(this.logo));
        }
        this.prevWidth = this.field_146294_l;
        this.prevHeight = this.field_146295_m;
    }
    
    public void func_73866_w_() {
        this.field_146292_n.clear();
        final int x = this.getOffset() + 300;
        final int y = 109 - this.scrollOffset;
        for (int i = 0; i < ConfigGui.settings.size(); ++i) {
            final Setting setting = ConfigGui.settings.get(i);
            this.field_146292_n.add(ConfigInput.buttonFromSetting(setting, x, y + i * 15));
        }
        final int viewport = this.field_146295_m - 109 - 9;
        final int contentHeight = ConfigGui.settings.size() * 15;
        final int scrollbarX = this.getOffset() + 300 + 10;
        this.scrollbar = new Scrollbar(109, viewport, contentHeight, this.scrollOffset, scrollbarX, this.scrolling);
        this.field_146292_n.add(this.scrollbar);
    }
    
    protected void func_146284_a(final GuiButton button) {
        if (button instanceof Scrollbar) {
            this.scrolling = true;
        }
        else {
            ConfigGui.settings.clear();
            ConfigGui.settings = this.getFilteredSettings();
        }
        this.func_73866_w_();
    }
    
    protected void func_146286_b(final int mouseX, final int mouseY, final int state) {
        this.scrolling = false;
        super.func_146286_b(mouseX, mouseY, state);
    }
    
    private void scrollScreen(int scrollAmount, final boolean pixels) {
        final int viewport = this.field_146295_m - 109 - 9;
        final int contentHeight = ConfigGui.settings.size() * 15;
        if (!pixels) {
            scrollAmount = (int)(scrollAmount / (float)viewport * contentHeight);
        }
        if (contentHeight > viewport) {
            this.scrollOffset = MathHelper.func_76125_a(this.scrollOffset + scrollAmount, 0, contentHeight - viewport);
            this.func_73866_w_();
        }
    }
    
    private void mouseMoved(final int mouseY) {
        if (this.scrolling) {
            this.scrollScreen(mouseY - this.prevMouseY, false);
        }
        this.prevMouseY = mouseY;
    }
    
    private ArrayList<Setting> getFilteredSettings() {
        final ArrayList<Setting> newSettings = new ArrayList<Setting>();
        for (final Setting setting : Shady.settings) {
            if (setting.parent == null) {
                newSettings.add(setting);
            }
            else {
                if (!newSettings.contains(setting.parent) || !setting.parent.get(Boolean.class)) {
                    continue;
                }
                newSettings.add(setting);
            }
        }
        return newSettings;
    }
    
    public void func_146274_d() throws IOException {
        if (Mouse.getEventDWheel() != 0) {
            this.scrollScreen(Integer.signum(Mouse.getEventDWheel()) * -10, true);
        }
        super.func_146274_d();
    }
    
    public void func_146281_b() {
        ConfigLogic.save();
    }
    
    public boolean func_73868_f() {
        return false;
    }
    
    private int getOffset() {
        return (this.field_146294_l - 300) / 2;
    }
    
    static {
        ConfigGui.settings = new ArrayList<Setting>();
    }
}
