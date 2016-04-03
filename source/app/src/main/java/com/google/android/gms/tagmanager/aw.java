// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.tagmanager;

import com.newrelic.agent.android.instrumentation.HttpInstrumentation;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

// Referenced classes of package com.google.android.gms.tagmanager:
//            bl

class aw
    implements bl
{

    private HttpURLConnection afP;

    aw()
    {
    }

    private InputStream a(HttpURLConnection httpurlconnection)
    {
        int i = httpurlconnection.getResponseCode();
        if (i == 200)
        {
            return httpurlconnection.getInputStream();
        }
        String s = (new StringBuilder("Bad response: ")).append(i).toString();
        if (i == 404)
        {
            throw new FileNotFoundException(s);
        } else
        {
            throw new IOException(s);
        }
    }

    private void b(HttpURLConnection httpurlconnection)
    {
        if (httpurlconnection != null)
        {
            httpurlconnection.disconnect();
        }
    }

    public InputStream bV(String s)
    {
        afP = bW(s);
        return a(afP);
    }

    HttpURLConnection bW(String s)
    {
        HttpURLConnection httpurlconnection = (HttpURLConnection)HttpInstrumentation.openConnection((new URL(s)).openConnection());
        httpurlconnection.setReadTimeout(20000);
        httpurlconnection.setConnectTimeout(20000);
        return httpurlconnection;
    }

    public void close()
    {
        b(afP);
    }
}
