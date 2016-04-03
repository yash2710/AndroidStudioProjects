// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.instrumentation;

import java.net.HttpURLConnection;
import javax.net.ssl.HttpsURLConnection;

// Referenced classes of package com.newrelic.agent.android.instrumentation:
//            HttpsURLConnectionExtension, HttpURLConnectionExtension

public class OkHttpInstrumentation
{

    public OkHttpInstrumentation()
    {
    }

    public static HttpURLConnection open(HttpURLConnection httpurlconnection)
    {
        if (httpurlconnection instanceof HttpsURLConnection)
        {
            return new HttpsURLConnectionExtension((HttpsURLConnection)httpurlconnection);
        }
        if (httpurlconnection != null)
        {
            return new HttpURLConnectionExtension(httpurlconnection);
        } else
        {
            return null;
        }
    }

    public static HttpURLConnection openWithProxy(HttpURLConnection httpurlconnection)
    {
        if (httpurlconnection instanceof HttpsURLConnection)
        {
            return new HttpsURLConnectionExtension((HttpsURLConnection)httpurlconnection);
        }
        if (httpurlconnection != null)
        {
            return new HttpURLConnectionExtension(httpurlconnection);
        } else
        {
            return null;
        }
    }
}
