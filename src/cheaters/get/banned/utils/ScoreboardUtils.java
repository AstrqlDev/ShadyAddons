// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.utils;

import java.util.Iterator;
import java.util.Collection;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.Team;
import net.minecraft.scoreboard.ScorePlayerTeam;
import com.google.common.collect.Lists;
import com.google.common.collect.Iterables;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import net.minecraft.scoreboard.Score;
import net.minecraft.client.Minecraft;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.util.StringUtils;

public class ScoreboardUtils
{
    public static String cleanSB(final String scoreboard) {
        final char[] nvString = StringUtils.func_76338_a(scoreboard).toCharArray();
        final StringBuilder cleaned = new StringBuilder();
        for (final char c : nvString) {
            if (c > '\u0014' && c < '\u007f') {
                cleaned.append(c);
            }
        }
        return cleaned.toString();
    }
    
    public static List<String> getScoreboard() {
        final List<String> lines = new ArrayList<String>();
        if (Minecraft.func_71410_x().field_71441_e == null) {
            return lines;
        }
        final Scoreboard scoreboard = Minecraft.func_71410_x().field_71441_e.func_96441_U();
        if (scoreboard == null) {
            return lines;
        }
        final ScoreObjective objective = scoreboard.func_96539_a(1);
        if (objective == null) {
            return lines;
        }
        Collection<Score> scores = (Collection<Score>)scoreboard.func_96534_i(objective);
        final List<Score> list = scores.stream().filter(input -> input != null && input.func_96653_e() != null && !input.func_96653_e().startsWith("#")).collect((Collector<? super Score, ?, List<Score>>)Collectors.toList());
        if (list.size() > 15) {
            scores = (Collection<Score>)Lists.newArrayList(Iterables.skip((Iterable)list, scores.size() - 15));
        }
        else {
            scores = list;
        }
        for (final Score score : scores) {
            final ScorePlayerTeam team = scoreboard.func_96509_i(score.func_96653_e());
            lines.add(ScorePlayerTeam.func_96667_a((Team)team, score.func_96653_e()));
        }
        return lines;
    }
    
    public static boolean scoreboardContains(final String string) {
        boolean result = false;
        final List<String> scoreboard = getScoreboard();
        for (String line : scoreboard) {
            line = cleanSB(line);
            line = Utils.removeFormatting(line);
            if (line.contains(string)) {
                result = true;
                break;
            }
        }
        return result;
    }
    
    public static String getLineThatContains(final String string) {
        for (String line : getScoreboard()) {
            line = cleanSB(line);
            if (line.contains(string)) {
                return line;
            }
        }
        return null;
    }
}
