// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.utils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.tileentity.TileEntitySkull;
import java.util.UUID;
import com.mojang.authlib.GameProfile;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ItemUtils
{
    public static ItemStack getSkullByName(final String username) {
        final ItemStack itemStack = new ItemStack(Items.field_151144_bL, 1, 3);
        final NBTTagCompound compound = new NBTTagCompound();
        final GameProfile profile = TileEntitySkull.func_174884_b(new GameProfile((UUID)null, username));
        compound.func_82580_o("SkullOwner");
        compound.func_74782_a("SkullOwner", (NBTBase)NBTUtil.func_180708_a(new NBTTagCompound(), profile));
        itemStack.func_77982_d(compound);
        return itemStack;
    }
    
    public static ItemStack getSkullItemStack(final String ownerId, final String textureId) {
        final ItemStack itemStack = new ItemStack(Items.field_151144_bL, 1, 3);
        return setSkullSkin(itemStack, ownerId, textureId);
    }
    
    public static ItemStack setSkullSkin(final ItemStack itemStack, final String skullId, final String skullValue) {
        final NBTTagCompound compound = new NBTTagCompound();
        final NBTTagCompound properties = new NBTTagCompound();
        properties.func_74778_a("Id", skullId);
        final NBTTagCompound texture = new NBTTagCompound();
        final NBTTagList list = new NBTTagList();
        final NBTTagCompound value = new NBTTagCompound();
        value.func_74778_a("Value", toSkullURL(skullValue));
        list.func_74742_a((NBTBase)value);
        texture.func_74782_a("textures", (NBTBase)list);
        properties.func_74782_a("Properties", (NBTBase)texture);
        if (!itemStack.func_77942_o()) {
            compound.func_74782_a("SkullOwner", (NBTBase)properties);
            itemStack.func_77982_d(compound);
        }
        else {
            itemStack.func_77978_p().func_74782_a("SkullOwner", (NBTBase)properties);
        }
        return itemStack;
    }
    
    private static String toSkullURL(final String url) {
        return Base64.getEncoder().encodeToString(("{\"textures\":{\"SKIN\":{\"url\":\"http://textures.minecraft.net/texture/" + url + "\"}}}").getBytes(StandardCharsets.UTF_8));
    }
}
