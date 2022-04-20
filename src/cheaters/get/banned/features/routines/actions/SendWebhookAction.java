// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.features.routines.actions;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import cheaters.get.banned.features.routines.RoutineRuntimeException;
import cheaters.get.banned.utils.HttpUtils;
import cheaters.get.banned.features.routines.RoutineElementData;

public class SendWebhookAction extends Action
{
    private static final String note = "Calm your tits, this is for the Routines action that sends a STATIC webhook message.";
    private String url;
    private String message;
    
    public SendWebhookAction(final RoutineElementData data) throws RoutineRuntimeException {
        super(data);
        this.url = data.keyAsString("url");
        this.message = data.keyAsString("message");
        if (!HttpUtils.isValidURL(this.url) || !this.url.startsWith("https://discord.com/api/webhooks/")) {
            throw new RoutineRuntimeException("Invalid Discord webhook URL in SendWebhookAction");
        }
    }
    
    @Override
    public void doAction() throws RoutineRuntimeException {
        final JsonObject payload = new JsonObject();
        final JsonArray embeds = new JsonArray();
        final JsonObject embed = new JsonObject();
        embed.addProperty("description", this.message);
        final JsonObject footer = new JsonObject();
        footer.addProperty("text", "Sent with ShadyAddons Routines");
        footer.addProperty("icon_url", "https://shadyaddons.com/assets/chester.png");
        embed.add("footer", (JsonElement)footer);
        embeds.add((JsonElement)embed);
        payload.add("embeds", (JsonElement)embeds);
        final String json = new Gson().toJson((JsonElement)payload);
        final String response = HttpUtils.post(this.url, json);
        if (response == null) {
            throw new RoutineRuntimeException("Error sending webhook message '" + this.message + "'");
        }
    }
}
