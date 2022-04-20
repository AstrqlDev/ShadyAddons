// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.features.routines;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class RoutineElementData
{
    private JsonObject json;
    
    public RoutineElementData(final JsonObject json) {
        this.json = json;
    }
    
    public String keyAsString(final String key) throws RoutineRuntimeException {
        final String value = this.keyAsString_noError(key);
        if (value == null) {
            throw new RoutineRuntimeException("Value for '" + key + "' is invalid");
        }
        return value;
    }
    
    public String keyAsString_noError(final String key) {
        final JsonElement element = this.json.get(key);
        if (element == null) {
            return null;
        }
        return element.getAsString();
    }
    
    public Integer keyAsInt(final String key) throws RoutineRuntimeException {
        final Integer value = this.keyAsInt_noError(key);
        if (value == null) {
            throw new RoutineRuntimeException("Value for '" + key + "' is invalid");
        }
        return value;
    }
    
    public Integer keyAsInt_noError(final String key) {
        final JsonElement element = this.json.get(key);
        if (element == null) {
            return null;
        }
        return element.getAsInt();
    }
    
    public Boolean keyAsBool_noError(final String key) {
        final JsonElement element = this.json.get(key);
        if (element == null) {
            return null;
        }
        return element.getAsBoolean();
    }
    
    public Boolean keyAsBool(final String key) throws RoutineRuntimeException {
        final Boolean value = this.keyAsBool_noError(key);
        if (value == null) {
            throw new RoutineRuntimeException("Value for '" + key + "' is invalid");
        }
        return value;
    }
}
