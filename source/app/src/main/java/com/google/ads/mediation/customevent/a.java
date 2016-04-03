// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.ads.mediation.customevent;

import android.view.View;
import com.google.ads.mediation.MediationBannerListener;
import com.google.android.gms.internal.eu;

// Referenced classes of package com.google.ads.mediation.customevent:
//            CustomEventBannerListener, CustomEventAdapter

final class a
    implements CustomEventBannerListener
{

    private final CustomEventAdapter a;
    private final MediationBannerListener b;

    public a(CustomEventAdapter customeventadapter, MediationBannerListener mediationbannerlistener)
    {
        a = customeventadapter;
        b = mediationbannerlistener;
    }

    public final void onClick()
    {
        eu.z("Custom event adapter called onFailedToReceiveAd.");
        b.onClick(a);
    }

    public final void onDismissScreen()
    {
        eu.z("Custom event adapter called onFailedToReceiveAd.");
        b.onDismissScreen(a);
    }

    public final void onFailedToReceiveAd()
    {
        eu.z("Custom event adapter called onFailedToReceiveAd.");
        b.onFailedToReceiveAd(a, com.google.ads.AdRequest.ErrorCode.NO_FILL);
    }

    public final void onLeaveApplication()
    {
        eu.z("Custom event adapter called onFailedToReceiveAd.");
        b.onLeaveApplication(a);
    }

    public final void onPresentScreen()
    {
        eu.z("Custom event adapter called onFailedToReceiveAd.");
        b.onPresentScreen(a);
    }

    public final void onReceivedAd(View view)
    {
        eu.z("Custom event adapter called onReceivedAd.");
        CustomEventAdapter.a(a, view);
        b.onReceivedAd(a);
    }
}
