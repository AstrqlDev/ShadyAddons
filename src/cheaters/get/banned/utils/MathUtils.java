// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.utils;

import java.util.Random;

public class MathUtils
{
    private static final Random random;
    
    public static int random(final int min, final int max) {
        return MathUtils.random.nextInt(max - min + 1) + min;
    }
    
    public static boolean isNumber(final String string) {
        try {
            Double.parseDouble(string);
            return true;
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    static {
        random = new Random();
    }
}
