// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.facebook.android.Facebook;

// Referenced classes of package com.flipkart.android.login:
//            a, FacebookConstants, c, b, 
//            onFacebookTokenFetchedListener

public abstract class FacebookTokenFetcher
{

    public static String KeyAccessExpires = "access_expires";
    public static String KeyAccessToken = "access_token";
    Facebook a;
    Activity b;
    onFacebookTokenFetchedListener c;
    com.facebook.android.Facebook.DialogListener d;

    public FacebookTokenFetcher(Activity activity, onFacebookTokenFetchedListener onfacebooktokenfetchedlistener)
    {
        d = new a(this);
        b = activity;
        c = onfacebooktokenfetchedlistener;
        a = new Facebook(FacebookConstants.AppId);
    }

    static void a(FacebookTokenFetcher facebooktokenfetcher, onFacebookTokenFetchedListener.FacebookTokenFetcherErrorType facebooktokenfetchererrortype, String s)
    {
        if (facebooktokenfetcher.c != null)
        {
            facebooktokenfetcher.b.runOnUiThread(new c(facebooktokenfetcher, facebooktokenfetchererrortype, s));
        }
    }

    static void a(FacebookTokenFetcher facebooktokenfetcher, onFacebookTokenFetchedListener.FacebookTokenFetcherEventType facebooktokenfetchereventtype, Bundle bundle)
    {
        if (facebooktokenfetcher.c != null)
        {
            facebooktokenfetcher.b.runOnUiThread(new b(facebooktokenfetcher, facebooktokenfetchereventtype, bundle));
        }
    }

    public void authorize(int i, String as[])
    {
        doAuthorize(i, as);
    }

    public void authorizeCallback(int i, int j, Intent intent)
    {
        a.authorizeCallback(i, j, intent);
    }

    public void doAuthorize(int i, String as[])
    {
        a.authorize(b, as, i, d);
    }

    public long getAccessExpires()
    {
        return a.getAccessExpires();
    }

    public String getAccessToken()
    {
        return a.getAccessToken();
    }

    public Facebook getFacebookObj()
    {
        return a;
    }

    public boolean isSessionValid()
    {
        return a.isSessionValid();
    }

    public void setAccessExpires(long l)
    {
        a.setAccessExpires(l);
    }

    public void setAccessToken(String s)
    {
        if (s != null)
        {
            a.setAccessToken(s);
        }
    }

    static 
    {
        com/flipkart/android/login/FacebookTokenFetcher.getSimpleName();
    }
}
