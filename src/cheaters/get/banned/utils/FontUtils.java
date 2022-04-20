// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.utils;

import net.minecraft.client.renderer.GlStateManager;
import java.awt.Color;
import cheaters.get.banned.Shady;

public class FontUtils
{
    public static final int LINE_HEIGHT;
    
    public static char getRainbowCode(final char fallback) {
        return (Shady.USING_SBA && (!Shady.USING_PATCHER || Shady.USING_SKYTILS)) ? 'z' : fallback;
    }
    
    public static String enforceWidth(final String text, final int width) {
        final String[] splitText = text.split(" ");
        int lineWidth = 0;
        final StringBuilder result = new StringBuilder();
        for (final String word : splitText) {
            final int wordWidth = getStringWidth(word);
            if (wordWidth + lineWidth > width) {
                result.append(word).append("\n");
                lineWidth = 0;
            }
            else {
                result.append(word).append(" ");
                lineWidth += wordWidth + getStringWidth(" ");
            }
        }
        return result.toString();
    }
    
    public static void drawCenteredString(String text, final int x, int y, final boolean shadow) {
        if (EstonianUtils.isEstoniaDay()) {
            text = EstonianUtils.replaceEstonian(text);
        }
        y -= getStringHeight(text) / 2;
        final String[] split;
        final String[] lines = split = text.split("\n");
        for (final String line : split) {
            drawString(line, x - getStringWidth(line) / 2, y, shadow);
            y += FontUtils.LINE_HEIGHT + 1;
        }
    }
    
    public static void drawCenteredString(final String text, final int x, final int y) {
        drawCenteredString(text, x, y, true);
    }
    
    public static void drawString(String text, final int x, int y, final boolean shadow) {
        if (EstonianUtils.isEstoniaDay()) {
            text = EstonianUtils.replaceEstonian(text);
        }
        final String[] split;
        final String[] lines = split = text.split("\n");
        for (final String line : split) {
            Shady.mc.field_71466_p.func_175065_a(line, (float)x, (float)y, Color.WHITE.getRGB(), shadow);
            y += FontUtils.LINE_HEIGHT + 1;
        }
    }
    
    public static int getStringHeight(String text) {
        if (EstonianUtils.isEstoniaDay()) {
            text = EstonianUtils.replaceEstonian(text);
        }
        final int lines = text.split("\n").length;
        return (lines > 1) ? (lines * (FontUtils.LINE_HEIGHT + 1) - 1) : FontUtils.LINE_HEIGHT;
    }
    
    public static int getStringWidth(String text) {
        if (EstonianUtils.isEstoniaDay()) {
            text = EstonianUtils.replaceEstonian(text);
        }
        final String[] lines = text.split("\n");
        int longestLine = 0;
        for (final String line : lines) {
            final int lineWidth = Shady.mc.field_71466_p.func_78256_a(line);
            if (lineWidth > longestLine) {
                longestLine = lineWidth;
            }
        }
        return longestLine;
    }
    
    public static void drawScaledString(final String string, final float scale, final int x, final int y, final boolean shadow) {
        GlStateManager.func_179094_E();
        GlStateManager.func_179152_a(scale, scale, scale);
        drawString(string, (int)(x / scale), (int)(y / scale), shadow);
        GlStateManager.func_179121_F();
    }
    
    public static void drawScaledCenteredString(final String string, final float scale, final int x, final int y, final boolean shadow) {
        GlStateManager.func_179094_E();
        GlStateManager.func_179152_a(scale, scale, scale);
        drawCenteredString(string, (int)(x / scale), (int)(y / scale), shadow);
        GlStateManager.func_179121_F();
    }
    
    static {
        LINE_HEIGHT = Shady.mc.field_71466_p.field_78288_b;
    }
}
