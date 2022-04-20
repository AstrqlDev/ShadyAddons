// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.utils;

import java.io.IOException;
import org.apache.http.HttpException;
import org.apache.http.protocol.HttpContext;
import org.apache.http.HttpRequest;
import java.net.URL;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.HttpEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;

public class HttpUtils
{
    public static String get(final String url) {
        String response = null;
        final HttpClientBuilder client = HttpClients.custom().addInterceptorFirst((request, context) -> {
            if (!request.containsHeader("Pragma")) {
                request.addHeader("Pragma", "no-cache");
            }
            if (!request.containsHeader("Cache-Control")) {
                request.addHeader("Cache-Control", "no-cache");
            }
        });
        client.setUserAgent("ShadyAddons/2.7.2");
        try {
            final HttpGet request = new HttpGet(url);
            final CloseableHttpResponse httpResponse = client.build().execute((HttpUriRequest)request);
            if (httpResponse.getStatusLine().getStatusCode() < 299) {
                response = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
            }
            else {
                Utils.log("Error code " + httpResponse.getStatusLine().getStatusCode());
            }
        }
        catch (Exception ex) {}
        return response;
    }
    
    public static String post(final String url, final String jsonData) {
        String response = null;
        final HttpClient client = (HttpClient)HttpClientBuilder.create().build();
        try {
            final HttpPost request = new HttpPost(url);
            final StringEntity params = new StringEntity(jsonData);
            request.setEntity((HttpEntity)params);
            request.setHeader("Content-Type", "application/json");
            request.setHeader("User-Agent", "ShadyAddons/2.7.2");
            final HttpResponse httpResponse = client.execute((HttpUriRequest)request);
            response = EntityUtils.toString(httpResponse.getEntity());
        }
        catch (Exception ex) {}
        return response;
    }
    
    public static boolean isValidURL(final String url) {
        try {
            new URL(url).toURI();
        }
        catch (Exception e) {
            return false;
        }
        return true;
    }
}
