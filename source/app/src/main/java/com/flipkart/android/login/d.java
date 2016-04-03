// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.login;

import com.newrelic.agent.android.instrumentation.HttpInstrumentation;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

// Referenced classes of package com.flipkart.android.login:
//            GoogleTokenFetcher, GoogleLoginConstants

final class d extends Thread
{

    private String a;
    private GoogleTokenFetcher b;
    private GoogleTokenFetcher c;

    d(GoogleTokenFetcher googletokenfetcher, String s, GoogleTokenFetcher googletokenfetcher1)
    {
        c = googletokenfetcher;
        a = s;
        b = googletokenfetcher1;
        super();
    }

    public final void run()
    {
        ArrayList arraylist;
        GoogleTokenFetcher.a(c, 1, "");
        arraylist = new ArrayList(2);
        arraylist.add(new BasicNameValuePair(GoogleLoginConstants.KKeyCode, a));
        arraylist.add(new BasicNameValuePair(GoogleLoginConstants.KKeyClientId, GoogleLoginConstants.KValueClientId));
        arraylist.add(new BasicNameValuePair(GoogleLoginConstants.KKeyClientSecret, GoogleLoginConstants.KValueClientSecret));
        arraylist.add(new BasicNameValuePair(GoogleLoginConstants.KKeyRedirectUri, GoogleLoginConstants.KValueRedirectUriLocalhost));
        arraylist.add(new BasicNameValuePair(GoogleLoginConstants.KKeyGrantType, GoogleLoginConstants.KValueGrantTypeAuthorizationCode));
        DefaultHttpClient defaulthttpclient;
        HttpPost httppost;
        BasicResponseHandler basicresponsehandler;
        Object obj1;
        defaulthttpclient = new DefaultHttpClient();
        httppost = new HttpPost(GoogleLoginConstants.KValueAuthTokenBaseUrl);
        httppost.setEntity(new UrlEncodedFormEntity(arraylist));
        basicresponsehandler = new BasicResponseHandler();
        if (defaulthttpclient instanceof HttpClient)
        {
            break MISSING_BLOCK_LABEL_213;
        }
        obj1 = defaulthttpclient.execute(httppost, basicresponsehandler);
_L1:
        String s = (String)obj1;
        String s1 = GoogleTokenFetcher.a(c, s);
        GoogleTokenFetcher.a(b, 0, s1);
        return;
        Object obj;
        try
        {
            obj = HttpInstrumentation.execute((HttpClient)defaulthttpclient, httppost, basicresponsehandler);
        }
        catch (Exception exception)
        {
            GoogleTokenFetcher.a(b, 0);
            return;
        }
        obj1 = obj;
          goto _L1
    }
}
