// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.gui.config;

public class TypeMismatchException extends RuntimeException
{
    public TypeMismatchException(final String expected, final String given) {
        super("Expected " + expected + ", got " + given);
    }
}
