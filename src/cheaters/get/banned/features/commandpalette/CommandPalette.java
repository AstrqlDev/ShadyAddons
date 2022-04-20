// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.features.commandpalette;

import java.awt.Color;
import java.io.IOException;
import net.minecraft.util.ChatAllowedCharacters;
import org.apache.commons.lang3.StringUtils;
import org.lwjgl.input.Keyboard;
import cheaters.get.banned.utils.FontUtils;
import java.util.ArrayList;
import net.minecraft.client.gui.GuiScreen;

public class CommandPalette extends GuiScreen
{
    public String search;
    private ArrayList<Result> results;
    private int selected;
    public static final int MAX_RESULTS = 5;
    private static final int ROW_WIDTH = 300;
    private static final int HALF_ROW_WIDTH = 150;
    private static final int ROW_HEIGHT = 20;
    private static final int ROW_COLOR;
    
    public CommandPalette() {
        this.search = "";
        this.selected = 0;
        ResultList.generateList();
        this.results = ResultList.getResults(this.search);
    }
    
    public void func_73863_a(final int mouseX, final int mouseY, final float partialTicks) {
        this.func_146276_q_();
        final int searchY = this.getBoxY() + (20 - FontUtils.LINE_HEIGHT) / 2;
        FontUtils.drawString((this.search.length() == 0) ? "§7Type and press enter..." : this.search, this.getBoxX() + 4 + 3, searchY, false);
        if (System.currentTimeMillis() / 500L % 2L == 0L) {
            final int cursorX = this.getBoxX() + 4 + FontUtils.getStringWidth(this.search) + 1;
            func_73734_a(cursorX, searchY - 1, cursorX + 1, searchY + FontUtils.LINE_HEIGHT - 1, -1);
        }
        if (this.results.size() > 0) {
            for (int i = 0; i < this.results.size(); ++i) {
                final Result result = this.results.get(i);
                int y = this.getBoxY() + 25 * (i + 1);
                int x = this.getBoxX();
                func_73734_a(x, y - 4, x + 300, y + 20, CommandPalette.ROW_COLOR);
                result.icon.render(this.getBoxX() + 4, y);
                y += (20 - FontUtils.LINE_HEIGHT) / 2;
                x += 24;
                if (result.description != null) {
                    y -= (int)((0.75f * FontUtils.LINE_HEIGHT + 2.0f) / 2.0f);
                    FontUtils.drawScaledString("§7" + result.description, 0.75f, x, y + FontUtils.LINE_HEIGHT, false);
                }
                FontUtils.drawString(((this.selected == i) ? "§e" : "") + result.name, x, y, false);
            }
        }
        else {
            FontUtils.drawCenteredString("No results", this.getBoxX() + 150, (int)(this.getBoxY() + 50.0), false);
        }
    }
    
    private int getBoxX() {
        return this.field_146294_l / 2 - 150;
    }
    
    private int getBoxY() {
        return this.field_146295_m / 2 - 60;
    }
    
    protected void func_73869_a(final char typedChar, final int keyCode) throws IOException {
        switch (keyCode) {
            case 1: {
                if (this.search == null || this.search.equals("") || this.results.size() == 0) {
                    super.func_73869_a(typedChar, keyCode);
                    return;
                }
                this.search = "";
                break;
            }
            case 200: {
                --this.selected;
                if (this.selected < 0) {
                    this.selected = this.results.size() - 1;
                }
                return;
            }
            case 208: {
                ++this.selected;
                if (this.selected + 1 > this.results.size()) {
                    this.selected = 0;
                }
                return;
            }
            case 28: {
                if (this.results.size() > 0) {
                    this.results.get(this.selected).action.execute();
                    this.closeGui();
                    break;
                }
                break;
            }
        }
        this.selected = 0;
        if (keyCode == 14) {
            if (Keyboard.isKeyDown(219)) {
                this.search = "";
            }
            else {
                this.search = StringUtils.chop(this.search);
            }
        }
        if (ChatAllowedCharacters.func_71566_a(typedChar)) {
            this.search += typedChar;
        }
        if (this.search.equals(" ")) {
            this.search = "";
        }
        if (this.search.endsWith("  ")) {
            this.search = this.search.substring(0, this.search.length() - 1);
        }
        this.results = ResultList.getResults(this.search);
    }
    
    public boolean func_73868_f() {
        return false;
    }
    
    private void closeGui() throws IOException {
        super.func_73869_a(' ', 1);
    }
    
    static {
        ROW_COLOR = new Color(54, 57, 63).getRGB();
    }
}
