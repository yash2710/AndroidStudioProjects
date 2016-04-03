// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.login;

import android.os.Bundle;

// Referenced classes of package com.flipkart.android.login:
//            FacebookTokenFetcher, onFacebookTokenFetchedListener

final class b
    implements Runnable
{

    private onFacebookTokenFetchedListener.FacebookTokenFetcherEventType a;
    private Bundle b;
    private FacebookTokenFetcher c;

    b(FacebookTokenFetcher facebooktokenfetcher, onFacebookTokenFetchedListener.FacebookTokenFetcherEventType facebooktokenfetchereventtype, Bundle bundle)
    {
        c = facebooktokenfetcher;
        a = facebooktokenfetchereventtype;
        b = bundle;
        super();
    }

    public final void run()
    {
        c.c.onFacebookTokenFetcherEvent(a, b);
    }
}
