// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.ads;

import android.content.Context;
import com.google.android.gms.ads.purchase.InAppPurchaseListener;
import com.google.android.gms.ads.purchase.PlayStorePurchaseListener;
import com.google.android.gms.internal.av;

// Referenced classes of package com.google.android.gms.ads:
//            AdRequest, AdListener

public final class InterstitialAd
{

    private final av ky;

    public InterstitialAd(Context context)
    {
        ky = new av(context);
    }

    public final AdListener getAdListener()
    {
        return ky.getAdListener();
    }

    public final String getAdUnitId()
    {
        return ky.getAdUnitId();
    }

    public final InAppPurchaseListener getInAppPurchaseListener()
    {
        return ky.getInAppPurchaseListener();
    }

    public final boolean isLoaded()
    {
        return ky.isLoaded();
    }

    public final void loadAd(AdRequest adrequest)
    {
        ky.a(adrequest.T());
    }

    public final void setAdListener(AdListener adlistener)
    {
        ky.setAdListener(adlistener);
    }

    public final void setAdUnitId(String s)
    {
        ky.setAdUnitId(s);
    }

    public final void setInAppPurchaseListener(InAppPurchaseListener inapppurchaselistener)
    {
        ky.setInAppPurchaseListener(inapppurchaselistener);
    }

    public final void setPlayStorePurchaseParams(PlayStorePurchaseListener playstorepurchaselistener, String s)
    {
        ky.setPlayStorePurchaseParams(playstorepurchaselistener, s);
    }

    public final void show()
    {
        ky.show();
    }
}
