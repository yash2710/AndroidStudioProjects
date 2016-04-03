// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import com.google.android.gms.ads.AdListener;

public final class ag extends ap.a
{

    private final AdListener lQ;

    public ag(AdListener adlistener)
    {
        lQ = adlistener;
    }

    public final void onAdClosed()
    {
        lQ.onAdClosed();
    }

    public final void onAdFailedToLoad(int i)
    {
        lQ.onAdFailedToLoad(i);
    }

    public final void onAdLeftApplication()
    {
        lQ.onAdLeftApplication();
    }

    public final void onAdLoaded()
    {
        lQ.onAdLoaded();
    }

    public final void onAdOpened()
    {
        lQ.onAdOpened();
    }
}
