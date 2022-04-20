// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.utils;

import java.util.function.Predicate;
import com.google.common.collect.Iterables;
import java.util.Random;
import java.util.List;

public class ArrayUtils
{
    public static Object getRandomItem(final List<?> list) {
        return list.get(new Random().nextInt(list.size()));
    }
    
    public static <T> T firstOrNull(final Iterable<T> iterable) {
        return (T)Iterables.getFirst((Iterable)iterable, (Object)null);
    }
    
    public static <T> T getFirstMatch(final List<T> list, final Predicate<? super T> predicate) {
        return list.stream().filter(predicate).findFirst().orElse(null);
    }
}
