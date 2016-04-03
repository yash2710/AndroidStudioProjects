// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.login;


// Referenced classes of package com.flipkart.android.login:
//            GoogleTokenFetcher, onGoogleTokenFetchedListener

final class g
    implements Runnable
{

    private int a;
    private GoogleTokenFetcher b;

    g(GoogleTokenFetcher googletokenfetcher, int i)
    {
        b = googletokenfetcher;
        a = i;
        super();
    }

    public final void run()
    {
        GoogleTokenFetcher.g(b).onGoogleTokenFetchError(a);
    }
}
