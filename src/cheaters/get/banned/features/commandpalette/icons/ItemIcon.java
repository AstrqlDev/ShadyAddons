// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.features.commandpalette.icons;

import cheaters.get.banned.utils.RenderUtils;
import net.minecraft.item.ItemStack;

public class ItemIcon implements IIcon
{
    private ItemStack item;
    
    public ItemIcon(final ItemStack item) {
        this.item = item;
    }
    
    @Override
    public void render(final int x, final int y) {
        RenderUtils.renderItem(this.item, x, y);
    }
}
