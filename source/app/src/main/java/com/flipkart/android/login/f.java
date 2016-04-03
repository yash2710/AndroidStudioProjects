// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.login;


// Referenced classes of package com.flipkart.android.login:
//            GoogleTokenFetcher, onGoogleTokenFetchedListener

final class f
    implements Runnable
{

    private int a;
    private String b;
    private GoogleTokenFetcher c;

    f(GoogleTokenFetcher googletokenfetcher, int i, String s)
    {
        c = googletokenfetcher;
        a = i;
        b = s;
        super();
    }

    public final void run()
    {
        GoogleTokenFetcher.g(c).onGoogleTokenFetcherEvent(a, b);
    }
}
