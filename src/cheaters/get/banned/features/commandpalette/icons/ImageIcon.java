// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.features.commandpalette.icons;

import cheaters.get.banned.utils.RenderUtils;
import net.minecraft.util.ResourceLocation;

public class ImageIcon implements IIcon
{
    private ResourceLocation resource;
    
    public ImageIcon(final ResourceLocation resource) {
        this.resource = resource;
    }
    
    public ImageIcon(final String fileName) {
        this.resource = new ResourceLocation("shadyaddons:commandpalette/" + fileName);
    }
    
    @Override
    public void render(final int x, final int y) {
        RenderUtils.drawTexture(this.resource, x, y, 16, 16);
    }
}
