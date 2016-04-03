// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.login;


// Referenced classes of package com.flipkart.android.login:
//            FacebookTokenFetcher, onFacebookTokenFetchedListener

final class c
    implements Runnable
{

    private onFacebookTokenFetchedListener.FacebookTokenFetcherErrorType a;
    private String b;
    private FacebookTokenFetcher c;

    c(FacebookTokenFetcher facebooktokenfetcher, onFacebookTokenFetchedListener.FacebookTokenFetcherErrorType facebooktokenfetchererrortype, String s)
    {
        c = facebooktokenfetcher;
        a = facebooktokenfetchererrortype;
        b = s;
        super();
    }

    public final void run()
    {
        c.c.onFacebookTokenFetchError(a, b);
    }
}
