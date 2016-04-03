// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.ads.mediation.customevent;

import com.google.ads.mediation.MediationInterstitialListener;
import com.google.android.gms.internal.eu;

// Referenced classes of package com.google.ads.mediation.customevent:
//            CustomEventInterstitialListener, CustomEventAdapter

final class b
    implements CustomEventInterstitialListener
{

    private final CustomEventAdapter a;
    private final MediationInterstitialListener b;
    private CustomEventAdapter c;

    public b(CustomEventAdapter customeventadapter, CustomEventAdapter customeventadapter1, MediationInterstitialListener mediationinterstitiallistener)
    {
        c = customeventadapter;
        super();
        a = customeventadapter1;
        b = mediationinterstitiallistener;
    }

    public final void onDismissScreen()
    {
        eu.z("Custom event adapter called onDismissScreen.");
        b.onDismissScreen(a);
    }

    public final void onFailedToReceiveAd()
    {
        eu.z("Custom event adapter called onFailedToReceiveAd.");
        b.onFailedToReceiveAd(a, com.google.ads.AdRequest.ErrorCode.NO_FILL);
    }

    public final void onLeaveApplication()
    {
        eu.z("Custom event adapter called onLeaveApplication.");
        b.onLeaveApplication(a);
    }

    public final void onPresentScreen()
    {
        eu.z("Custom event adapter called onPresentScreen.");
        b.onPresentScreen(a);
    }

    public final void onReceivedAd()
    {
        eu.z("Custom event adapter called onReceivedAd.");
        b.onReceivedAd(c);
    }
}
