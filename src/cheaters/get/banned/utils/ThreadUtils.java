// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.utils;

public class ThreadUtils
{
    public static void sleep(final int ms) {
        try {
            Thread.sleep(ms);
        }
        catch (InterruptedException ex) {}
    }
}
