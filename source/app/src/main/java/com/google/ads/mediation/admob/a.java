// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.ads.mediation.admob;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.mediation.MediationBannerListener;

// Referenced classes of package com.google.ads.mediation.admob:
//            AdMobAdapter

final class a extends AdListener
{

    private final AdMobAdapter a;
    private final MediationBannerListener b;

    public a(AdMobAdapter admobadapter, MediationBannerListener mediationbannerlistener)
    {
        a = admobadapter;
        b = mediationbannerlistener;
    }

    public final void onAdClosed()
    {
        b.onAdClosed(a);
    }

    public final void onAdFailedToLoad(int i)
    {
        b.onAdFailedToLoad(a, i);
    }

    public final void onAdLeftApplication()
    {
        b.onAdLeftApplication(a);
    }

    public final void onAdLoaded()
    {
        b.onAdLoaded(a);
    }

    public final void onAdOpened()
    {
        b.onAdClicked(a);
        b.onAdOpened(a);
    }
}
