// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.utils;

import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.entity.player.EnumPlayerModelParts;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.client.gui.Gui;
import cheaters.get.banned.Shady;
import net.minecraft.util.ResourceLocation;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.EnumChatFormatting;
import org.lwjgl.opengl.GL11;
import net.minecraft.util.Vec3;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.Entity;
import net.minecraft.client.Minecraft;
import net.minecraft.util.BlockPos;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.GlStateManager;
import java.awt.Color;
import net.minecraft.util.AxisAlignedBB;

public class RenderUtils
{
    public static void drawFilledBoundingBox(final AxisAlignedBB aabb, final Color c, final float alphaMultiplier) {
        GlStateManager.func_179147_l();
        GlStateManager.func_179140_f();
        GlStateManager.func_179120_a(770, 771, 1, 0);
        GlStateManager.func_179090_x();
        final Tessellator tessellator = Tessellator.func_178181_a();
        final WorldRenderer worldrenderer = tessellator.func_178180_c();
        GlStateManager.func_179131_c(c.getRed() / 255.0f, c.getGreen() / 255.0f, c.getBlue() / 255.0f, c.getAlpha() / 255.0f * alphaMultiplier);
        worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181705_e);
        worldrenderer.func_181662_b(aabb.field_72340_a, aabb.field_72338_b, aabb.field_72339_c).func_181675_d();
        worldrenderer.func_181662_b(aabb.field_72336_d, aabb.field_72338_b, aabb.field_72339_c).func_181675_d();
        worldrenderer.func_181662_b(aabb.field_72336_d, aabb.field_72338_b, aabb.field_72334_f).func_181675_d();
        worldrenderer.func_181662_b(aabb.field_72340_a, aabb.field_72338_b, aabb.field_72334_f).func_181675_d();
        tessellator.func_78381_a();
        worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181705_e);
        worldrenderer.func_181662_b(aabb.field_72340_a, aabb.field_72337_e, aabb.field_72334_f).func_181675_d();
        worldrenderer.func_181662_b(aabb.field_72336_d, aabb.field_72337_e, aabb.field_72334_f).func_181675_d();
        worldrenderer.func_181662_b(aabb.field_72336_d, aabb.field_72337_e, aabb.field_72339_c).func_181675_d();
        worldrenderer.func_181662_b(aabb.field_72340_a, aabb.field_72337_e, aabb.field_72339_c).func_181675_d();
        tessellator.func_78381_a();
        GlStateManager.func_179131_c(c.getRed() / 255.0f * 0.8f, c.getGreen() / 255.0f * 0.8f, c.getBlue() / 255.0f * 0.8f, c.getAlpha() / 255.0f * alphaMultiplier);
        worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181705_e);
        worldrenderer.func_181662_b(aabb.field_72340_a, aabb.field_72338_b, aabb.field_72334_f).func_181675_d();
        worldrenderer.func_181662_b(aabb.field_72340_a, aabb.field_72337_e, aabb.field_72334_f).func_181675_d();
        worldrenderer.func_181662_b(aabb.field_72340_a, aabb.field_72337_e, aabb.field_72339_c).func_181675_d();
        worldrenderer.func_181662_b(aabb.field_72340_a, aabb.field_72338_b, aabb.field_72339_c).func_181675_d();
        tessellator.func_78381_a();
        worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181705_e);
        worldrenderer.func_181662_b(aabb.field_72336_d, aabb.field_72338_b, aabb.field_72339_c).func_181675_d();
        worldrenderer.func_181662_b(aabb.field_72336_d, aabb.field_72337_e, aabb.field_72339_c).func_181675_d();
        worldrenderer.func_181662_b(aabb.field_72336_d, aabb.field_72337_e, aabb.field_72334_f).func_181675_d();
        worldrenderer.func_181662_b(aabb.field_72336_d, aabb.field_72338_b, aabb.field_72334_f).func_181675_d();
        tessellator.func_78381_a();
        GlStateManager.func_179131_c(c.getRed() / 255.0f * 0.9f, c.getGreen() / 255.0f * 0.9f, c.getBlue() / 255.0f * 0.9f, c.getAlpha() / 255.0f * alphaMultiplier);
        worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181705_e);
        worldrenderer.func_181662_b(aabb.field_72340_a, aabb.field_72337_e, aabb.field_72339_c).func_181675_d();
        worldrenderer.func_181662_b(aabb.field_72336_d, aabb.field_72337_e, aabb.field_72339_c).func_181675_d();
        worldrenderer.func_181662_b(aabb.field_72336_d, aabb.field_72338_b, aabb.field_72339_c).func_181675_d();
        worldrenderer.func_181662_b(aabb.field_72340_a, aabb.field_72338_b, aabb.field_72339_c).func_181675_d();
        tessellator.func_78381_a();
        worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181705_e);
        worldrenderer.func_181662_b(aabb.field_72340_a, aabb.field_72338_b, aabb.field_72334_f).func_181675_d();
        worldrenderer.func_181662_b(aabb.field_72336_d, aabb.field_72338_b, aabb.field_72334_f).func_181675_d();
        worldrenderer.func_181662_b(aabb.field_72336_d, aabb.field_72337_e, aabb.field_72334_f).func_181675_d();
        worldrenderer.func_181662_b(aabb.field_72340_a, aabb.field_72337_e, aabb.field_72334_f).func_181675_d();
        tessellator.func_78381_a();
        GlStateManager.func_179098_w();
        GlStateManager.func_179084_k();
    }
    
    public static void highlightBlock(final BlockPos pos, final Color color, final float partialTicks) {
        final Entity viewer = Minecraft.func_71410_x().func_175606_aa();
        final double viewerX = viewer.field_70142_S + (viewer.field_70165_t - viewer.field_70142_S) * partialTicks;
        final double viewerY = viewer.field_70137_T + (viewer.field_70163_u - viewer.field_70137_T) * partialTicks;
        final double viewerZ = viewer.field_70136_U + (viewer.field_70161_v - viewer.field_70136_U) * partialTicks;
        final double x = pos.func_177958_n() - viewerX;
        final double y = pos.func_177956_o() - viewerY;
        final double z = pos.func_177952_p() - viewerZ;
        GlStateManager.func_179097_i();
        GlStateManager.func_179129_p();
        GlStateManager.func_179140_f();
        drawFilledBoundingBox(new AxisAlignedBB(x, y, z, x + 1.0, y + 1.0, z + 1.0), color, 1.0f);
        GlStateManager.func_179145_e();
        GlStateManager.func_179126_j();
        GlStateManager.func_179089_o();
    }
    
    public static void renderItem(final ItemStack itemStack, final int x, final int y) {
        RenderHelper.func_74520_c();
        GlStateManager.func_179091_B();
        GlStateManager.func_179147_l();
        GlStateManager.func_179126_j();
        GlStateManager.func_179120_a(770, 771, 1, 0);
        Minecraft.func_71410_x().func_175599_af().func_180450_b(itemStack, x, y);
    }
    
    public static void draw3DLine(final Vec3 pos1, final Vec3 pos2, final Color color, final int lineWidth, final boolean depth, final float partialTicks) {
        final Entity render = Minecraft.func_71410_x().func_175606_aa();
        final WorldRenderer worldRenderer = Tessellator.func_178181_a().func_178180_c();
        final double realX = render.field_70142_S + (render.field_70165_t - render.field_70142_S) * partialTicks;
        final double realY = render.field_70137_T + (render.field_70163_u - render.field_70137_T) * partialTicks;
        final double realZ = render.field_70136_U + (render.field_70161_v - render.field_70136_U) * partialTicks;
        GlStateManager.func_179094_E();
        GlStateManager.func_179137_b(-realX, -realY, -realZ);
        GlStateManager.func_179090_x();
        GlStateManager.func_179147_l();
        GlStateManager.func_179118_c();
        GlStateManager.func_179120_a(770, 771, 1, 0);
        GL11.glLineWidth((float)lineWidth);
        if (!depth) {
            GL11.glDisable(2929);
            GlStateManager.func_179132_a(false);
        }
        GlStateManager.func_179131_c(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f);
        worldRenderer.func_181668_a(3, DefaultVertexFormats.field_181705_e);
        worldRenderer.func_181662_b(pos1.field_72450_a, pos1.field_72448_b, pos1.field_72449_c).func_181675_d();
        worldRenderer.func_181662_b(pos2.field_72450_a, pos2.field_72448_b, pos2.field_72449_c).func_181675_d();
        Tessellator.func_178181_a().func_78381_a();
        GlStateManager.func_179137_b(realX, realY, realZ);
        if (!depth) {
            GL11.glEnable(2929);
            GlStateManager.func_179132_a(true);
        }
        GlStateManager.func_179084_k();
        GlStateManager.func_179141_d();
        GlStateManager.func_179098_w();
        GlStateManager.func_179131_c(1.0f, 1.0f, 1.0f, 1.0f);
        GlStateManager.func_179121_F();
    }
    
    public static void renderWaypointText(final String str, final double X, final double Y, final double Z, final float partialTicks) {
        GlStateManager.func_179092_a(516, 0.1f);
        GlStateManager.func_179094_E();
        final Entity viewer = Minecraft.func_71410_x().func_175606_aa();
        final double viewerX = viewer.field_70142_S + (viewer.field_70165_t - viewer.field_70142_S) * partialTicks;
        final double viewerY = viewer.field_70137_T + (viewer.field_70163_u - viewer.field_70137_T) * partialTicks;
        final double viewerZ = viewer.field_70136_U + (viewer.field_70161_v - viewer.field_70136_U) * partialTicks;
        double x = X - viewerX;
        double y = Y - viewerY - viewer.func_70047_e();
        double z = Z - viewerZ;
        final double distSq = x * x + y * y + z * z;
        final double dist = Math.sqrt(distSq);
        if (distSq > 144.0) {
            x *= 12.0 / dist;
            y *= 12.0 / dist;
            z *= 12.0 / dist;
        }
        GlStateManager.func_179137_b(x, y, z);
        GlStateManager.func_179109_b(0.0f, viewer.func_70047_e(), 0.0f);
        drawNametag(str);
        GlStateManager.func_179114_b(-Minecraft.func_71410_x().func_175598_ae().field_78735_i, 0.0f, 1.0f, 0.0f);
        GlStateManager.func_179114_b(Minecraft.func_71410_x().func_175598_ae().field_78732_j, 1.0f, 0.0f, 0.0f);
        GlStateManager.func_179109_b(0.0f, -0.25f, 0.0f);
        GlStateManager.func_179114_b(-Minecraft.func_71410_x().func_175598_ae().field_78732_j, 1.0f, 0.0f, 0.0f);
        GlStateManager.func_179114_b(Minecraft.func_71410_x().func_175598_ae().field_78735_i, 0.0f, 1.0f, 0.0f);
        drawNametag(EnumChatFormatting.YELLOW.toString() + Math.round(dist) + " blocks");
        GlStateManager.func_179121_F();
        GlStateManager.func_179140_f();
    }
    
    public static void drawNametag(final String str) {
        final FontRenderer fontrenderer = Minecraft.func_71410_x().field_71466_p;
        final float f = 1.6f;
        final float f2 = 0.016666668f * f;
        GlStateManager.func_179094_E();
        GL11.glNormal3f(0.0f, 1.0f, 0.0f);
        GlStateManager.func_179114_b(-Minecraft.func_71410_x().func_175598_ae().field_78735_i, 0.0f, 1.0f, 0.0f);
        GlStateManager.func_179114_b(Minecraft.func_71410_x().func_175598_ae().field_78732_j, 1.0f, 0.0f, 0.0f);
        GlStateManager.func_179152_a(-f2, -f2, f2);
        GlStateManager.func_179140_f();
        GlStateManager.func_179132_a(false);
        GlStateManager.func_179097_i();
        GlStateManager.func_179147_l();
        GlStateManager.func_179120_a(770, 771, 1, 0);
        final Tessellator tessellator = Tessellator.func_178181_a();
        final WorldRenderer worldrenderer = tessellator.func_178180_c();
        final int i = 0;
        final int j = fontrenderer.func_78256_a(str) / 2;
        GlStateManager.func_179090_x();
        worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181706_f);
        worldrenderer.func_181662_b((double)(-j - 1), (double)(-1 + i), 0.0).func_181666_a(0.0f, 0.0f, 0.0f, 0.25f).func_181675_d();
        worldrenderer.func_181662_b((double)(-j - 1), (double)(8 + i), 0.0).func_181666_a(0.0f, 0.0f, 0.0f, 0.25f).func_181675_d();
        worldrenderer.func_181662_b((double)(j + 1), (double)(8 + i), 0.0).func_181666_a(0.0f, 0.0f, 0.0f, 0.25f).func_181675_d();
        worldrenderer.func_181662_b((double)(j + 1), (double)(-1 + i), 0.0).func_181666_a(0.0f, 0.0f, 0.0f, 0.25f).func_181675_d();
        tessellator.func_78381_a();
        GlStateManager.func_179098_w();
        fontrenderer.func_78276_b(str, -fontrenderer.func_78256_a(str) / 2, i, 553648127);
        GlStateManager.func_179132_a(true);
        fontrenderer.func_78276_b(str, -fontrenderer.func_78256_a(str) / 2, i, -1);
        GlStateManager.func_179126_j();
        GlStateManager.func_179147_l();
        GlStateManager.func_179131_c(1.0f, 1.0f, 1.0f, 1.0f);
        GlStateManager.func_179121_F();
    }
    
    public static void bindColor(final Color color) {
        GlStateManager.func_179131_c(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f);
    }
    
    public static void drawRotatedTexture(final ResourceLocation resourceLocation, final int x, final int y, final int width, final int height, final int angle) {
        drawRotatedTexture(resourceLocation, x, y, width, height, width, height, 0, 0, angle);
    }
    
    public static void drawRotatedTexture(final ResourceLocation resourceLocation, final int x, final int y, final int width, final int height, final int textureWidth, final int textureHeight, final int textureX, final int textureY, final int angle) {
        GlStateManager.func_179094_E();
        GlStateManager.func_179109_b(x + width / 2.0f, y + height / 2.0f, 0.0f);
        GlStateManager.func_179114_b((float)angle, 0.0f, 0.0f, 1.0f);
        GlStateManager.func_179109_b(-x - width / 2.0f, -y - height / 2.0f, 0.0f);
        drawTexture(resourceLocation, x, y, width, height, textureWidth, textureHeight, textureX, textureY);
        GlStateManager.func_179121_F();
    }
    
    public static void drawTexture(final ResourceLocation resourceLocation, final int x, final int y, final int width, final int height, final int textureWidth, final int textureHeight, final int textureX, final int textureY) {
        Shady.mc.func_110434_K().func_110577_a(resourceLocation);
        GlStateManager.func_179124_c(255.0f, 255.0f, 255.0f);
        Gui.func_146110_a(x, y, (float)textureX, (float)textureY, width, height, (float)textureWidth, (float)textureHeight);
    }
    
    public static void drawTexture(final ResourceLocation resourceLocation, final int x, final int y, final int width, final int height) {
        drawTexture(resourceLocation, x, y, width, height, width, height, 0, 0);
    }
    
    public static void drawPlayerIcon(final EntityPlayer player, final int size, final int x, final int y) {
        if (player != null) {
            Shady.mc.func_110434_K().func_110577_a(Shady.mc.func_147114_u().func_175102_a(player.func_110124_au()).func_178837_g());
            Gui.func_152125_a(x, y, 8.0f, 8.0f, 8, 8, size, size, 64.0f, 64.0f);
            if (player.func_175148_a(EnumPlayerModelParts.HAT)) {
                Gui.func_152125_a(x, y, 40.0f, 8.0f, 8, 8, size, size, 64.0f, 64.0f);
            }
        }
    }
    
    public static void drawOutlinedBoundingBox(final AxisAlignedBB aabb, final int colourInt, final float partialTicks) {
        final Entity render = Minecraft.func_71410_x().func_175606_aa();
        final Color colour = new Color(colourInt);
        final double realX = render.field_70142_S + (render.field_70165_t - render.field_70142_S) * partialTicks;
        final double realY = render.field_70137_T + (render.field_70163_u - render.field_70137_T) * partialTicks;
        final double realZ = render.field_70136_U + (render.field_70161_v - render.field_70136_U) * partialTicks;
        GlStateManager.func_179129_p();
        GlStateManager.func_179097_i();
        GlStateManager.func_179094_E();
        GlStateManager.func_179137_b(-realX, -realY, -realZ);
        GlStateManager.func_179090_x();
        GlStateManager.func_179147_l();
        GlStateManager.func_179118_c();
        GlStateManager.func_179120_a(770, 771, 1, 0);
        GL11.glLineWidth(2.0f);
        RenderGlobal.func_181563_a(aabb, colour.getRed(), colour.getGreen(), colour.getBlue(), colour.getAlpha());
        GlStateManager.func_179137_b(realX, realY, realZ);
        GlStateManager.func_179084_k();
        GlStateManager.func_179141_d();
        GlStateManager.func_179098_w();
        GlStateManager.func_179131_c(1.0f, 1.0f, 1.0f, 1.0f);
        GlStateManager.func_179121_F();
        GlStateManager.func_179126_j();
        GlStateManager.func_179089_o();
    }
    
    public static void outlineBlock(final BlockPos pos, final Color color, final float partialTicks) {
        drawOutlinedBoundingBox(new AxisAlignedBB((double)pos.func_177958_n(), (double)pos.func_177956_o(), (double)pos.func_177952_p(), (double)(pos.func_177958_n() + 1), (double)(pos.func_177956_o() + 1), (double)(pos.func_177952_p() + 1)), color.getRGB(), partialTicks);
    }
}
