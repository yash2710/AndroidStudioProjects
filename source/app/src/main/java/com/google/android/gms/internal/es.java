// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.content.Context;
import com.newrelic.agent.android.instrumentation.HttpInstrumentation;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

// Referenced classes of package com.google.android.gms.internal:
//            em, eu, eo

public final class es extends em
{

    private final String lr;
    private final Context mContext;
    private final String qY;

    public es(Context context, String s, String s1)
    {
        mContext = context;
        lr = s;
        qY = s1;
    }

    public final void bh()
    {
        HttpURLConnection httpurlconnection;
        eu.C((new StringBuilder("Pinging URL: ")).append(qY).toString());
        httpurlconnection = (HttpURLConnection)HttpInstrumentation.openConnection((new URL(qY)).openConnection());
        int i;
        eo.a(mContext, lr, true, httpurlconnection);
        i = httpurlconnection.getResponseCode();
        if (i >= 200 && i < 300)
        {
            break MISSING_BLOCK_LABEL_110;
        }
        eu.D((new StringBuilder("Received non-success response code ")).append(i).append(" from pinging URL: ").append(qY).toString());
        Exception exception;
        try
        {
            httpurlconnection.disconnect();
            return;
        }
        catch (IndexOutOfBoundsException indexoutofboundsexception)
        {
            eu.D((new StringBuilder("Error while parsing ping URL: ")).append(qY).append(". ").append(indexoutofboundsexception.getMessage()).toString());
            return;
        }
        catch (IOException ioexception)
        {
            eu.D((new StringBuilder("Error while pinging URL: ")).append(qY).append(". ").append(ioexception.getMessage()).toString());
        }
        break MISSING_BLOCK_LABEL_195;
        exception;
        httpurlconnection.disconnect();
        throw exception;
    }

    public final void onStop()
    {
    }
}
