// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.features;

import net.minecraftforge.event.world.WorldEvent;
import cheaters.get.banned.utils.ArrayUtils;
import com.mojang.authlib.properties.Property;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.Vec3i;
import cheaters.get.banned.events.BlockChangeEvent;
import net.minecraft.util.Vec3;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import java.util.Iterator;
import cheaters.get.banned.utils.RenderUtils;
import net.minecraft.block.BlockChest;
import net.minecraft.block.BlockLever;
import net.minecraft.block.BlockSkull;
import java.awt.Color;
import cheaters.get.banned.utils.RayMarchUtils;
import java.util.Map;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import cheaters.get.banned.events.TickEndEvent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cheaters.get.banned.utils.DungeonUtils;
import net.minecraft.init.Items;
import cheaters.get.banned.gui.config.Config;
import cheaters.get.banned.Shady;
import cheaters.get.banned.utils.Utils;
import java.util.HashSet;
import net.minecraft.block.Block;
import net.minecraft.util.BlockPos;
import java.util.HashMap;

public class StonklessStonk
{
    private static HashMap<BlockPos, Block> blockList;
    private static BlockPos selectedBlock;
    private static BlockPos lastCheckedPosition;
    private static HashSet<BlockPos> usedBlocks;
    private static float range;
    private static final String witherEssenceSkin = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzRkYjRhZGZhOWJmNDhmZjVkNDE3MDdhZTM0ZWE3OGJkMjM3MTY1OWZjZDhjZDg5MzQ3NDlhZjRjY2U5YiJ9fX0=";
    private static final int essenceSkinHash;
    
    private static boolean isEnabled() {
        boolean isEnabled = Utils.inDungeon && Shady.mc.field_71439_g != null;
        if (Config.onlyEnableWhenHoldingPickaxe && isEnabled) {
            final ItemStack currentItemStack = Shady.mc.field_71439_g.field_71071_by.func_70301_a(Shady.mc.field_71439_g.field_71071_by.field_70461_c);
            isEnabled = (currentItemStack != null);
            if (isEnabled) {
                final Item currentItem = currentItemStack.func_77973_b();
                isEnabled = (currentItem == Items.field_151046_w || currentItem == Items.field_151035_b || currentItem == Items.field_151005_D || currentItem == Items.field_151050_s || currentItem == Items.field_151039_o);
            }
        }
        if (!Config.alwaysOn && isEnabled) {
            isEnabled = (Config.stonklessStonk && Shady.mc.field_71439_g.func_70093_af());
        }
        if (Config.disableInBoss && isEnabled) {
            isEnabled = !DungeonUtils.inBoss;
        }
        return isEnabled;
    }
    
    @SubscribeEvent
    public void onTick(final TickEndEvent event) {
        if (Shady.mc.field_71439_g == null) {
            return;
        }
        final BlockPos playerPosition = Shady.mc.field_71439_g.func_180425_c();
        if (isEnabled() && (StonklessStonk.lastCheckedPosition == null || !StonklessStonk.lastCheckedPosition.equals((Object)playerPosition))) {
            StonklessStonk.blockList.clear();
            StonklessStonk.lastCheckedPosition = playerPosition;
            for (int x = playerPosition.func_177958_n() - 6; x < playerPosition.func_177958_n() + 6; ++x) {
                for (int y = playerPosition.func_177956_o() - 6; y < playerPosition.func_177956_o() + 6; ++y) {
                    for (int z = playerPosition.func_177952_p() - 6; z < playerPosition.func_177952_p() + 6; ++z) {
                        final BlockPos position = new BlockPos(x, y, z);
                        final Block block = Shady.mc.field_71441_e.func_180495_p(position).func_177230_c();
                        if (shouldEspBlock(block, position)) {
                            StonklessStonk.blockList.put(position, block);
                        }
                    }
                }
            }
        }
    }
    
    @SubscribeEvent
    public void onRenderWorld(final RenderWorldLastEvent event) {
        if (isEnabled()) {
            for (final Map.Entry<BlockPos, Block> block : StonklessStonk.blockList.entrySet()) {
                if (StonklessStonk.usedBlocks.contains(block.getKey())) {
                    continue;
                }
                if (StonklessStonk.selectedBlock == null) {
                    if (RayMarchUtils.isFacingBlock(block.getKey(), StonklessStonk.range)) {
                        StonklessStonk.selectedBlock = block.getKey();
                    }
                }
                else if (!RayMarchUtils.isFacingBlock(StonklessStonk.selectedBlock, StonklessStonk.range)) {
                    StonklessStonk.selectedBlock = null;
                }
                Color color = Utils.addAlpha(Color.WHITE, 51);
                if (block.getValue() instanceof BlockSkull) {
                    color = Utils.addAlpha(Color.BLACK, 51);
                }
                if (block.getValue() instanceof BlockLever) {
                    color = Utils.addAlpha(Color.LIGHT_GRAY, 51);
                }
                if (block.getValue() instanceof BlockChest && ((BlockChest)block.getValue()).field_149956_a == 1) {
                    color = Utils.addAlpha(Color.RED, 51);
                }
                if (block.getKey().equals((Object)StonklessStonk.selectedBlock)) {
                    color = Utils.addAlpha(Color.GREEN, 51);
                }
                RenderUtils.highlightBlock(block.getKey(), color, event.partialTicks);
            }
        }
    }
    
    @SubscribeEvent
    public void onInteract(final PlayerInteractEvent event) {
        if (isEnabled() && StonklessStonk.selectedBlock != null && !StonklessStonk.usedBlocks.contains(StonklessStonk.selectedBlock)) {
            if (Shady.mc.field_71476_x != null && StonklessStonk.selectedBlock.equals((Object)Shady.mc.field_71476_x.func_178782_a())) {
                return;
            }
            if (event.action == PlayerInteractEvent.Action.RIGHT_CLICK_AIR || event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK) {
                StonklessStonk.usedBlocks.add(StonklessStonk.selectedBlock);
                Shady.mc.field_71439_g.func_70095_a(false);
                if (Shady.mc.field_71442_b.func_178890_a(Shady.mc.field_71439_g, Shady.mc.field_71441_e, Shady.mc.field_71439_g.field_71071_by.func_70448_g(), StonklessStonk.selectedBlock, EnumFacing.func_176733_a((double)Shady.mc.field_71439_g.field_70177_z), new Vec3(Math.random(), Math.random(), Math.random()))) {
                    Shady.mc.field_71439_g.func_71038_i();
                }
            }
        }
    }
    
    @SubscribeEvent
    public void onBlockChange(final BlockChangeEvent event) {
        if (Shady.mc.field_71441_e == null || Shady.mc.field_71439_g == null) {
            return;
        }
        if (event.position.func_177951_i((Vec3i)Shady.mc.field_71439_g.func_180425_c()) > StonklessStonk.range) {
            return;
        }
        if (StonklessStonk.usedBlocks.contains(event.position)) {
            return;
        }
        if (!shouldEspBlock(event.newBlock.func_177230_c(), event.position)) {
            return;
        }
        StonklessStonk.blockList.put(event.position, event.newBlock.func_177230_c());
    }
    
    public static boolean shouldEspBlock(final Block block, final BlockPos position) {
        if (block instanceof BlockChest || block instanceof BlockLever) {
            return true;
        }
        if (block instanceof BlockSkull) {
            final TileEntitySkull tileEntity = (TileEntitySkull)Shady.mc.field_71441_e.func_175625_s(position);
            if (tileEntity.func_145904_a() == 3) {
                final Property property = ArrayUtils.firstOrNull((Iterable<Property>)tileEntity.func_152108_a().getProperties().get((Object)"textures"));
                return property != null && property.getValue().hashCode() == StonklessStonk.essenceSkinHash;
            }
        }
        return false;
    }
    
    @SubscribeEvent
    public void onWorldLoad(final WorldEvent.Load event) {
        StonklessStonk.blockList.clear();
        StonklessStonk.usedBlocks.clear();
        StonklessStonk.selectedBlock = null;
        StonklessStonk.lastCheckedPosition = null;
    }
    
    static {
        StonklessStonk.blockList = new HashMap<BlockPos, Block>();
        StonklessStonk.selectedBlock = null;
        StonklessStonk.lastCheckedPosition = null;
        StonklessStonk.usedBlocks = new HashSet<BlockPos>();
        StonklessStonk.range = 5.0f;
        essenceSkinHash = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzRkYjRhZGZhOWJmNDhmZjVkNDE3MDdhZTM0ZWE3OGJkMjM3MTY1OWZjZDhjZDg5MzQ3NDlhZjRjY2U5YiJ9fX0=".hashCode();
    }
}
