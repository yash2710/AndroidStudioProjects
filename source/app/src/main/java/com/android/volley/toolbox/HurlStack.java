// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.volley.toolbox;

import com.android.volley.Request;
import com.newrelic.agent.android.instrumentation.HttpInstrumentation;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicStatusLine;

// Referenced classes of package com.android.volley.toolbox:
//            HttpStack, d

public class HurlStack
    implements HttpStack
{

    private final UrlRewriter a;
    private final SSLSocketFactory b;

    public HurlStack()
    {
        this(null);
    }

    public HurlStack(UrlRewriter urlrewriter)
    {
        this(urlrewriter, null);
    }

    public HurlStack(UrlRewriter urlrewriter, SSLSocketFactory sslsocketfactory)
    {
        a = urlrewriter;
        if (android.os.Build.VERSION.SDK_INT > 10)
        {
            b = sslsocketfactory;
            return;
        } else
        {
            b = a();
            return;
        }
    }

    private static SSLSocketFactory a()
    {
        TrustManager atrustmanager[] = new TrustManager[1];
        atrustmanager[0] = new d();
        SSLSocketFactory sslsocketfactory;
        try
        {
            SSLContext sslcontext = SSLContext.getInstance("SSL");
            sslcontext.init(null, atrustmanager, new SecureRandom());
            sslsocketfactory = sslcontext.getSocketFactory();
        }
        catch (Exception exception)
        {
            return null;
        }
        return sslsocketfactory;
    }

    private static HttpEntity a(HttpURLConnection httpurlconnection)
    {
        BasicHttpEntity basichttpentity = new BasicHttpEntity();
        java.io.InputStream inputstream1 = httpurlconnection.getInputStream();
        java.io.InputStream inputstream = inputstream1;
_L2:
        basichttpentity.setContent(inputstream);
        basichttpentity.setContentLength(httpurlconnection.getContentLength());
        basichttpentity.setContentEncoding(httpurlconnection.getContentEncoding());
        basichttpentity.setContentType(httpurlconnection.getContentType());
        return basichttpentity;
        IOException ioexception;
        ioexception;
        inputstream = httpurlconnection.getErrorStream();
        if (true) goto _L2; else goto _L1
_L1:
    }

    private static void a(HttpURLConnection httpurlconnection, Request request)
    {
        byte abyte0[] = request.getBody();
        if (abyte0 != null)
        {
            httpurlconnection.setDoOutput(true);
            httpurlconnection.addRequestProperty("Content-Type", request.getBodyContentType());
            DataOutputStream dataoutputstream = new DataOutputStream(httpurlconnection.getOutputStream());
            dataoutputstream.write(abyte0);
            dataoutputstream.close();
        }
    }

    protected HttpURLConnection createConnection(URL url)
    {
        return (HttpURLConnection)HttpInstrumentation.openConnection(url.openConnection());
    }

    public HttpResponse performRequest(Request request, Map map)
    {
        HttpURLConnection httpurlconnection;
        String s = request.getUrl();
        HashMap hashmap = new HashMap();
        hashmap.putAll(request.getHeaders());
        hashmap.putAll(map);
        String s1;
        if (a != null)
        {
            s1 = a.rewriteUrl(s);
            if (s1 == null)
            {
                throw new IOException((new StringBuilder("URL blocked by rewriter: ")).append(s).toString());
            }
        } else
        {
            s1 = s;
        }
        URL url = new URL(s1);
        httpurlconnection = createConnection(url);
        int i = request.getTimeoutMs();
        httpurlconnection.setConnectTimeout(i);
        httpurlconnection.setReadTimeout(i);
        httpurlconnection.setUseCaches(false);
        httpurlconnection.setDoInput(true);
        if ("https".equals(url.getProtocol()) && b != null)
        {
            ((HttpsURLConnection)httpurlconnection).setSSLSocketFactory(b);
        }
        String s2;
        for (Iterator iterator = hashmap.keySet().iterator(); iterator.hasNext(); httpurlconnection.addRequestProperty(s2, (String)hashmap.get(s2)))
        {
            s2 = (String)iterator.next();
        }

        request.getMethod();
        JVM INSTR tableswitch -1 3: default 256
    //                   -1 266
    //                   0 362
    //                   1 384
    //                   2 401
    //                   3 373;
           goto _L1 _L2 _L3 _L4 _L5 _L6
_L1:
        throw new IllegalStateException("Unknown method type.");
_L2:
        byte abyte0[] = request.getPostBody();
        if (abyte0 != null)
        {
            httpurlconnection.setDoOutput(true);
            httpurlconnection.setRequestMethod("POST");
            httpurlconnection.addRequestProperty("Content-Type", request.getPostBodyContentType());
            DataOutputStream dataoutputstream = new DataOutputStream(httpurlconnection.getOutputStream());
            dataoutputstream.write(abyte0);
            dataoutputstream.close();
        }
_L8:
        ProtocolVersion protocolversion;
        protocolversion = new ProtocolVersion("HTTP", 1, 1);
        if (httpurlconnection.getResponseCode() == -1)
        {
            throw new IOException("Could not retrieve response code from HttpUrlConnection.");
        }
        break; /* Loop/switch isn't completed */
_L3:
        httpurlconnection.setRequestMethod("GET");
        continue; /* Loop/switch isn't completed */
_L6:
        httpurlconnection.setRequestMethod("DELETE");
        continue; /* Loop/switch isn't completed */
_L4:
        httpurlconnection.setRequestMethod("POST");
        a(httpurlconnection, request);
        continue; /* Loop/switch isn't completed */
_L5:
        httpurlconnection.setRequestMethod("PUT");
        a(httpurlconnection, request);
        if (true) goto _L8; else goto _L7
_L7:
        BasicHttpResponse basichttpresponse = new BasicHttpResponse(new BasicStatusLine(protocolversion, httpurlconnection.getResponseCode(), httpurlconnection.getResponseMessage()));
        basichttpresponse.setEntity(a(httpurlconnection));
        Iterator iterator1 = httpurlconnection.getHeaderFields().entrySet().iterator();
        do
        {
            if (!iterator1.hasNext())
            {
                break;
            }
            java.util.Map.Entry entry = (java.util.Map.Entry)iterator1.next();
            if (entry.getKey() != null)
            {
                basichttpresponse.addHeader(new BasicHeader((String)entry.getKey(), (String)((List)entry.getValue()).get(0)));
            }
        } while (true);
        return basichttpresponse;
    }

    private class UrlRewriter
    {

        public abstract String rewriteUrl(String s);
    }

}
