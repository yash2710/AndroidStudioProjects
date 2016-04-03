// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.adobe.adms.measurement;

import com.newrelic.agent.android.instrumentation.HttpInstrumentation;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;
import javax.net.ssl.HttpsURLConnection;

// Referenced classes of package com.adobe.adms.measurement:
//            f, ADMS_Measurement, ADMS_DefaultValues

public final class ADMS_RequestHandler
{

    public ADMS_RequestHandler()
    {
    }

    private static HttpURLConnection a(String s)
    {
        URL url;
        HttpsURLConnection httpsurlconnection;
        url = new URL(s);
        if (s.indexOf("https://") < 0)
        {
            break MISSING_BLOCK_LABEL_45;
        }
        httpsurlconnection = (HttpsURLConnection)HttpInstrumentation.openConnection(url.openConnection());
        httpsurlconnection.setHostnameVerifier(new f());
        return httpsurlconnection;
        HttpURLConnection httpurlconnection;
        try
        {
            httpurlconnection = (HttpURLConnection)HttpInstrumentation.openConnection(url.openConnection());
        }
        catch (Exception exception)
        {
            ADMS_Measurement.sharedInstance().debugLog((new StringBuilder("Exception opening URL : ")).append(exception.getMessage()).toString());
            ADMS_DefaultValues.exceptionLog(exception);
            return null;
        }
        return httpurlconnection;
    }

    protected static boolean sendRequest(String s, Hashtable hashtable)
    {
        boolean flag;
        if (s == null)
        {
            return false;
        }
        HttpURLConnection httpurlconnection;
        Iterator iterator;
        java.util.Map.Entry entry;
        String s1;
        try
        {
            httpurlconnection = a(s);
        }
        catch (SocketTimeoutException sockettimeoutexception)
        {
            ADMS_Measurement.sharedInstance().debugLog("Timed out sending request.");
            return false;
        }
        catch (IOException ioexception)
        {
            ADMS_Measurement.sharedInstance().debugLog((new StringBuilder("IOException while sending request, retrying. Exception: ")).append(ioexception.getMessage()).toString());
            return false;
        }
        catch (Exception exception)
        {
            ADMS_Measurement.sharedInstance().debugLog((new StringBuilder("Exception while attempting to send hit : ")).append(exception.getMessage()).toString());
            return true;
        }
        if (httpurlconnection == null) goto _L2; else goto _L1
_L1:
        httpurlconnection.setConnectTimeout(5000);
        httpurlconnection.setReadTimeout(5000);
        if (hashtable == null)
        {
            break MISSING_BLOCK_LABEL_125;
        }
        iterator = hashtable.entrySet().iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            entry = (java.util.Map.Entry)iterator.next();
            s1 = (String)entry.getValue();
            if (s1.trim().length() > 0)
            {
                httpurlconnection.setRequestProperty((String)entry.getKey(), s1);
            }
        } while (true);
        ADMS_Measurement.sharedInstance().debugLog((new StringBuilder("Request Sent : ")).append(s).toString());
        httpurlconnection.getResponseCode();
        httpurlconnection.getInputStream().close();
        httpurlconnection.disconnect();
        flag = true;
_L4:
        return flag;
_L2:
        flag = false;
        if (true) goto _L4; else goto _L3
_L3:
    }
}
