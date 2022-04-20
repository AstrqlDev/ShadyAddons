// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.features;

import java.awt.Color;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraft.block.BlockStainedGlassPane;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.BlockStainedGlass;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.block.state.IBlockState;
import cheaters.get.banned.utils.LocationUtils;
import cheaters.get.banned.utils.Utils;
import cheaters.get.banned.gui.config.settings.FolderSetting;
import java.util.Iterator;
import cheaters.get.banned.utils.RenderUtils;
import java.util.Map;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraft.init.Blocks;
import cheaters.get.banned.events.BlockChangeEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import cheaters.get.banned.gui.config.Config;
import cheaters.get.banned.Shady;
import cheaters.get.banned.events.TickEndEvent;
import java.util.HashSet;
import net.minecraft.util.BlockPos;
import java.util.concurrent.ConcurrentHashMap;

public class GemstoneESP
{
    private ConcurrentHashMap<BlockPos, Gemstone> gemstones;
    private HashSet<BlockPos> checked;
    private BlockPos lastChecked;
    private boolean isScanning;
    
    public GemstoneESP() {
        this.gemstones = new ConcurrentHashMap<BlockPos, Gemstone>();
        this.checked = new HashSet<BlockPos>();
        this.lastChecked = null;
        this.isScanning = false;
    }
    
    @SubscribeEvent
    public void onTick(final TickEndEvent event) {
        if (isEnabled() && !this.isScanning && (this.lastChecked == null || !this.lastChecked.equals((Object)Shady.mc.field_71439_g.field_71081_bT))) {
            this.isScanning = true;
            final BlockPos playerPosition;
            int x;
            int y;
            int z;
            BlockPos position;
            Gemstone gemstone;
            new Thread(() -> {
                playerPosition = Shady.mc.field_71439_g.func_180425_c();
                this.lastChecked = playerPosition;
                for (x = playerPosition.func_177958_n() - Config.gemstoneRadius; x < playerPosition.func_177958_n() + Config.gemstoneRadius; ++x) {
                    for (y = playerPosition.func_177956_o() - Config.gemstoneRadius; y < playerPosition.func_177956_o() + Config.gemstoneRadius; ++y) {
                        for (z = playerPosition.func_177952_p() - Config.gemstoneRadius; z < playerPosition.func_177952_p() + Config.gemstoneRadius; ++z) {
                            position = new BlockPos(x, y, z);
                            if (!this.checked.contains(position) && !Shady.mc.field_71441_e.func_175623_d(position)) {
                                gemstone = getGemstone(Shady.mc.field_71441_e.func_180495_p(position));
                                if (gemstone != null) {
                                    this.gemstones.put(position, gemstone);
                                }
                            }
                            this.checked.add(position);
                        }
                    }
                }
                this.isScanning = false;
            }, "ShadyAddons-GemstoneScanner").start();
        }
    }
    
    @SubscribeEvent
    public void onBlockChange(final BlockChangeEvent event) {
        if (event.newBlock.func_177230_c() == Blocks.field_150350_a) {
            this.gemstones.remove(event.position);
        }
    }
    
    @SubscribeEvent
    public void onRenderWorld(final RenderWorldLastEvent event) {
        if (isEnabled()) {
            for (final Map.Entry<BlockPos, Gemstone> gemstone : this.gemstones.entrySet()) {
                if (!isGemstoneEnabled(gemstone.getValue())) {
                    continue;
                }
                final double distanceSq = gemstone.getKey().func_177954_c(Shady.mc.field_71439_g.field_70165_t, Shady.mc.field_71439_g.field_70163_u, Shady.mc.field_71439_g.field_70161_v);
                if (distanceSq > Math.pow(Config.gemstoneRadius + 2, 2.0)) {
                    continue;
                }
                if (Config.highlightMode == 0) {
                    RenderUtils.outlineBlock(gemstone.getKey(), gemstone.getValue().color, event.partialTicks);
                }
                else {
                    RenderUtils.highlightBlock(gemstone.getKey(), gemstone.getValue().color, event.partialTicks);
                }
            }
        }
    }
    
    private static boolean isEnabled() {
        return Shady.mc.field_71439_g != null && Shady.mc.field_71441_e != null && FolderSetting.isEnabled("Gemstone ESP") && Utils.inSkyBlock && LocationUtils.onIsland(LocationUtils.Island.CRYSTAL_HOLLOWS);
    }
    
    private static Gemstone getGemstone(final IBlockState block) {
        if (block.func_177230_c() != Blocks.field_150399_cn && block.func_177230_c() != Blocks.field_150397_co) {
            return null;
        }
        final EnumDyeColor color = Utils.firstNotNull((EnumDyeColor)block.func_177229_b((IProperty)BlockStainedGlass.field_176547_a), (EnumDyeColor)block.func_177229_b((IProperty)BlockStainedGlassPane.field_176245_a));
        if (color == Gemstone.RUBY.dyeColor) {
            return Gemstone.RUBY;
        }
        if (color == Gemstone.AMETHYST.dyeColor) {
            return Gemstone.AMETHYST;
        }
        if (color == Gemstone.JADE.dyeColor) {
            return Gemstone.JADE;
        }
        if (color == Gemstone.SAPPHIRE.dyeColor) {
            return Gemstone.SAPPHIRE;
        }
        if (color == Gemstone.AMBER.dyeColor) {
            return Gemstone.AMBER;
        }
        if (color == Gemstone.TOPAZ.dyeColor) {
            return Gemstone.TOPAZ;
        }
        if (color == Gemstone.JASPER.dyeColor) {
            return Gemstone.JASPER;
        }
        return null;
    }
    
    private static boolean isGemstoneEnabled(final Gemstone gemstone) {
        if (Config.includeGlassPanes) {
            switch (gemstone) {
                case RUBY_SHARD: {
                    return Config.rubyEsp;
                }
                case AMETHYST_SHARD: {
                    return Config.amethystEsp;
                }
                case JADE_SHARD: {
                    return Config.jadeEsp;
                }
                case SAPPHIRE_SHARD: {
                    return Config.sapphireEsp;
                }
                case AMBER_SHARD: {
                    return Config.amberEsp;
                }
                case TOPAZ_SHARD: {
                    return Config.topazEsp;
                }
                case JASPER_SHARD: {
                    return Config.jasperEsp;
                }
            }
        }
        switch (gemstone) {
            case RUBY: {
                return Config.rubyEsp;
            }
            case AMETHYST: {
                return Config.amethystEsp;
            }
            case JADE: {
                return Config.jadeEsp;
            }
            case SAPPHIRE: {
                return Config.sapphireEsp;
            }
            case AMBER: {
                return Config.amberEsp;
            }
            case TOPAZ: {
                return Config.topazEsp;
            }
            case JASPER: {
                return Config.jasperEsp;
            }
            default: {
                return false;
            }
        }
    }
    
    @SubscribeEvent
    public void onWorldChange(final WorldEvent.Load event) {
        this.gemstones.clear();
        this.checked.clear();
        this.lastChecked = null;
    }
    
    enum Gemstone
    {
        RUBY(new Color(188, 3, 29), EnumDyeColor.RED), 
        AMETHYST(new Color(137, 0, 201), EnumDyeColor.PURPLE), 
        JADE(new Color(157, 249, 32), EnumDyeColor.LIME), 
        SAPPHIRE(new Color(60, 121, 224), EnumDyeColor.LIGHT_BLUE), 
        AMBER(new Color(237, 139, 35), EnumDyeColor.ORANGE), 
        TOPAZ(new Color(249, 215, 36), EnumDyeColor.YELLOW), 
        JASPER(new Color(214, 15, 150), EnumDyeColor.MAGENTA), 
        RUBY_SHARD(Gemstone.RUBY), 
        AMETHYST_SHARD(Gemstone.AMETHYST), 
        JADE_SHARD(Gemstone.JADE), 
        SAPPHIRE_SHARD(Gemstone.SAPPHIRE), 
        AMBER_SHARD(Gemstone.AMBER), 
        TOPAZ_SHARD(Gemstone.TOPAZ), 
        JASPER_SHARD(Gemstone.JASPER);
        
        public Color color;
        public EnumDyeColor dyeColor;
        
        private Gemstone(final Color color, final EnumDyeColor dyeColor) {
            this.color = color;
            this.dyeColor = dyeColor;
        }
        
        private Gemstone(final Gemstone gemstone) {
            this.color = gemstone.color;
            this.dyeColor = gemstone.dyeColor;
        }
    }
}
