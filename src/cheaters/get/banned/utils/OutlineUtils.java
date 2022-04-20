// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.utils;

import org.lwjgl.opengl.EXTFramebufferObject;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import org.lwjgl.opengl.GL11;
import cheaters.get.banned.events.RenderEntityModelEvent;
import cheaters.get.banned.gui.config.Config;
import net.minecraft.client.renderer.GlStateManager;
import cheaters.get.banned.Shady;
import java.awt.Color;
import net.minecraft.entity.Entity;
import net.minecraft.client.model.ModelBase;

public class OutlineUtils
{
    public static void outlineEntity(final ModelBase model, final Entity entity, final float limbSwing, final float limbSwingAmount, final float ageInTicks, final float headYaw, final float headPitch, final float scaleFactor, final Color color) {
        final boolean fancyGraphics = Shady.mc.field_71474_y.field_74347_j;
        final float gamma = Shady.mc.field_71474_y.field_74333_Y;
        Shady.mc.field_71474_y.field_74347_j = false;
        Shady.mc.field_71474_y.field_74333_Y = Float.MAX_VALUE;
        GlStateManager.func_179117_G();
        setColor(color);
        renderOne((float)Config.espThickness);
        model.func_78088_a(entity, limbSwing, limbSwingAmount, ageInTicks, headYaw, headPitch, scaleFactor);
        setColor(color);
        renderTwo();
        model.func_78088_a(entity, limbSwing, limbSwingAmount, ageInTicks, headYaw, headPitch, scaleFactor);
        setColor(color);
        renderThree();
        model.func_78088_a(entity, limbSwing, limbSwingAmount, ageInTicks, headYaw, headPitch, scaleFactor);
        setColor(color);
        renderFour(color);
        model.func_78088_a(entity, limbSwing, limbSwingAmount, ageInTicks, headYaw, headPitch, scaleFactor);
        setColor(color);
        renderFive();
        setColor(Color.WHITE);
        Shady.mc.field_71474_y.field_74347_j = fancyGraphics;
        Shady.mc.field_71474_y.field_74333_Y = gamma;
    }
    
    public static void outlineEntity(final RenderEntityModelEvent event, final Color color) {
        outlineEntity(event.model, (Entity)event.entity, event.limbSwing, event.limbSwingAmount, event.ageInTicks, event.headYaw, event.headPitch, event.scaleFactor, color);
    }
    
    private static void renderOne(final float lineWidth) {
        checkSetupFBO();
        GL11.glPushAttrib(1048575);
        GL11.glDisable(3008);
        GL11.glDisable(3553);
        GL11.glDisable(2896);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glLineWidth(lineWidth);
        GL11.glEnable(2848);
        GL11.glEnable(2960);
        GL11.glClear(1024);
        GL11.glClearStencil(15);
        GL11.glStencilFunc(512, 1, 15);
        GL11.glStencilOp(7681, 7681, 7681);
        GL11.glPolygonMode(1032, 6913);
    }
    
    private static void renderTwo() {
        GL11.glStencilFunc(512, 0, 15);
        GL11.glStencilOp(7681, 7681, 7681);
        GL11.glPolygonMode(1032, 6914);
    }
    
    private static void renderThree() {
        GL11.glStencilFunc(514, 1, 15);
        GL11.glStencilOp(7680, 7680, 7680);
        GL11.glPolygonMode(1032, 6913);
    }
    
    private static void renderFour(final Color color) {
        setColor(color);
        GL11.glDepthMask(false);
        GL11.glDisable(2929);
        GL11.glEnable(10754);
        GL11.glPolygonOffset(1.0f, -2000000.0f);
        OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, 240.0f, 240.0f);
    }
    
    private static void renderFive() {
        GL11.glPolygonOffset(1.0f, 2000000.0f);
        GL11.glDisable(10754);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(2960);
        GL11.glDisable(2848);
        GL11.glHint(3154, 4352);
        GL11.glEnable(3042);
        GL11.glEnable(2896);
        GL11.glEnable(3553);
        GL11.glEnable(3008);
        GL11.glPopAttrib();
    }
    
    private static void setColor(final Color color) {
        GL11.glColor4d((double)(color.getRed() / 255.0f), (double)(color.getGreen() / 255.0f), (double)(color.getBlue() / 255.0f), (double)(color.getAlpha() / 255.0f));
    }
    
    private static void checkSetupFBO() {
        final Framebuffer fbo = Minecraft.func_71410_x().func_147110_a();
        if (fbo != null && fbo.field_147624_h > -1) {
            setupFBO(fbo);
            fbo.field_147624_h = -1;
        }
    }
    
    private static void setupFBO(final Framebuffer fbo) {
        EXTFramebufferObject.glDeleteRenderbuffersEXT(fbo.field_147624_h);
        final int stencilDepthBufferId = EXTFramebufferObject.glGenRenderbuffersEXT();
        EXTFramebufferObject.glBindRenderbufferEXT(36161, stencilDepthBufferId);
        EXTFramebufferObject.glRenderbufferStorageEXT(36161, 34041, Shady.mc.field_71443_c, Shady.mc.field_71440_d);
        EXTFramebufferObject.glFramebufferRenderbufferEXT(36160, 36128, 36161, stencilDepthBufferId);
        EXTFramebufferObject.glFramebufferRenderbufferEXT(36160, 36096, 36161, stencilDepthBufferId);
    }
}
