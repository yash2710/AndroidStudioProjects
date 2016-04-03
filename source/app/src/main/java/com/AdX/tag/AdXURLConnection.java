// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.AdX.tag;

import android.util.Log;
import com.newrelic.agent.android.instrumentation.HttpInstrumentation;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;

// Referenced classes of package com.AdX.tag:
//            AdXConnect

public class AdXURLConnection
{

    static int a = 0;

    public AdXURLConnection()
    {
    }

    public static String connectToURL(String s, String s1)
    {
        HttpGet httpget;
        DefaultHttpClient defaulthttpclient;
        httpget = new HttpGet((new StringBuilder(String.valueOf(s))).append(s1).toString().replaceAll(" ", "%20"));
        BasicHttpParams basichttpparams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(basichttpparams, 15000);
        HttpConnectionParams.setSoTimeout(basichttpparams, 30000);
        defaulthttpclient = new DefaultHttpClient(basichttpparams);
        if (defaulthttpclient instanceof HttpClient) goto _L2; else goto _L1
_L1:
        HttpResponse httpresponse1 = defaulthttpclient.execute(httpget);
_L3:
        String s3 = EntityUtils.toString(httpresponse1.getEntity());
        String s2 = s3;
        a = httpresponse1.getStatusLine().getStatusCode();
        if (AdXConnect.DEBUG)
        {
            Log.i("AdXAppTracker", "--------------------");
            Log.i("AdXAppTracker", (new StringBuilder("response status: ")).append(httpresponse1.getStatusLine().getStatusCode()).toString());
            Log.i("AdXAppTracker", (new StringBuilder("response size: ")).append(s2.length()).toString());
            Log.i("AdXAppTracker", "response: ");
            Log.i("AdXAppTracker", s2);
            Log.i("AdXAppTracker", "--------------------");
        }
        return s2;
_L2:
        HttpResponse httpresponse = HttpInstrumentation.execute((HttpClient)defaulthttpclient, httpget);
        httpresponse1 = httpresponse;
          goto _L3
        Exception exception;
        exception;
        Exception exception1;
        s2 = null;
        exception1 = exception;
_L5:
        Log.e("AdXAppTracker", (new StringBuilder("Exception: ")).append(exception1.toString()).toString());
        return s2;
        exception1;
        if (true) goto _L5; else goto _L4
_L4:
    }

    public static String postToURL(String s, String s1, String s2, String s3)
    {
        HttpPost httppost;
        DefaultHttpClient defaulthttpclient;
        httppost = new HttpPost((new StringBuilder(String.valueOf(s))).append(s1).toString().replaceAll(" ", "%20"));
        BasicHttpParams basichttpparams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(basichttpparams, 15000);
        HttpConnectionParams.setSoTimeout(basichttpparams, 30000);
        defaulthttpclient = new DefaultHttpClient(basichttpparams);
        ArrayList arraylist = new ArrayList();
        arraylist.add(new BasicNameValuePair(s2, s3));
        httppost.setEntity(new UrlEncodedFormEntity(arraylist, "UTF-8"));
        if (defaulthttpclient instanceof HttpClient) goto _L2; else goto _L1
_L1:
        HttpResponse httpresponse1 = defaulthttpclient.execute(httppost);
_L3:
        String s5 = EntityUtils.toString(httpresponse1.getEntity());
        String s4 = s5;
        a = httpresponse1.getStatusLine().getStatusCode();
        if (AdXConnect.DEBUG)
        {
            Log.i("AdXAppTracker", "--------------------");
            Log.i("AdXAppTracker", (new StringBuilder("response status: ")).append(httpresponse1.getStatusLine().getStatusCode()).toString());
            Log.i("AdXAppTracker", (new StringBuilder("response size: ")).append(s4.length()).toString());
            Log.i("AdXAppTracker", "response: ");
            Log.i("AdXAppTracker", s4);
            Log.i("AdXAppTracker", "--------------------");
        }
        return s4;
_L2:
        HttpResponse httpresponse = HttpInstrumentation.execute((HttpClient)defaulthttpclient, httppost);
        httpresponse1 = httpresponse;
          goto _L3
        Exception exception;
        exception;
        Exception exception1;
        s4 = null;
        exception1 = exception;
_L5:
        Log.e("AdXAppTracker", (new StringBuilder("Exception: ")).append(exception1.toString()).toString());
        return s4;
        exception1;
        if (true) goto _L5; else goto _L4
_L4:
    }

}
