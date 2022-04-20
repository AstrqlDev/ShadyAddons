// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.utils;

import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.world.WorldSettings;
import com.google.common.collect.ComparisonChain;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.Collections;
import net.minecraft.client.Minecraft;
import java.util.List;
import net.minecraft.client.network.NetworkPlayerInfo;
import com.google.common.collect.Ordering;

public class TabUtils
{
    public static final Ordering<NetworkPlayerInfo> playerInfoOrdering;
    
    public static List<NetworkPlayerInfo> getTabEntries() {
        if (Minecraft.func_71410_x().field_71439_g == null) {
            return Collections.emptyList();
        }
        return (List<NetworkPlayerInfo>)TabUtils.playerInfoOrdering.sortedCopy((Iterable)Minecraft.func_71410_x().field_71439_g.field_71174_a.func_175106_d());
    }
    
    public static List<String> getTabList() {
        return getTabEntries().stream().map(playerInfo -> Minecraft.func_71410_x().field_71456_v.func_175181_h().func_175243_a(playerInfo)).collect((Collector<? super Object, ?, List<String>>)Collectors.toList());
    }
    
    static {
        playerInfoOrdering = new Ordering<NetworkPlayerInfo>() {
            public int compare(final NetworkPlayerInfo p_compare_1_, final NetworkPlayerInfo p_compare_2_) {
                final ScorePlayerTeam scoreplayerteam = p_compare_1_.func_178850_i();
                final ScorePlayerTeam scoreplayerteam2 = p_compare_2_.func_178850_i();
                return ComparisonChain.start().compareTrueFirst(p_compare_1_.func_178848_b() != WorldSettings.GameType.SPECTATOR, p_compare_2_.func_178848_b() != WorldSettings.GameType.SPECTATOR).compare((Comparable)((scoreplayerteam != null) ? scoreplayerteam.func_96661_b() : ""), (Comparable)((scoreplayerteam2 != null) ? scoreplayerteam2.func_96661_b() : "")).compare((Comparable)p_compare_1_.func_178845_a().getName(), (Comparable)p_compare_2_.func_178845_a().getName()).result();
            }
        };
    }
}
