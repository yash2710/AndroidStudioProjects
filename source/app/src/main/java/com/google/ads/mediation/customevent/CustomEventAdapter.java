// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.ads.mediation.customevent;

import android.app.Activity;
import android.view.View;
import com.google.ads.AdSize;
import com.google.ads.mediation.MediationAdRequest;
import com.google.ads.mediation.MediationBannerAdapter;
import com.google.ads.mediation.MediationBannerListener;
import com.google.ads.mediation.MediationInterstitialAdapter;
import com.google.ads.mediation.MediationInterstitialListener;
import com.google.ads.mediation.MediationServerParameters;
import com.google.ads.mediation.NetworkExtras;
import com.google.android.gms.ads.mediation.customevent.CustomEventExtras;
import com.google.android.gms.internal.eu;

// Referenced classes of package com.google.ads.mediation.customevent:
//            CustomEventBanner, CustomEventInterstitial, CustomEventServerParameters, a, 
//            b

public final class CustomEventAdapter
    implements MediationBannerAdapter, MediationInterstitialAdapter
{

    private View a;
    private CustomEventBanner b;
    private CustomEventInterstitial c;

    public CustomEventAdapter()
    {
    }

    private static Object a(String s)
    {
        Object obj;
        try
        {
            obj = Class.forName(s).newInstance();
        }
        catch (Throwable throwable)
        {
            eu.D((new StringBuilder("Could not instantiate custom event adapter: ")).append(s).append(". ").append(throwable.getMessage()).toString());
            return null;
        }
        return obj;
    }

    static void a(CustomEventAdapter customeventadapter, View view)
    {
        customeventadapter.a = view;
    }

    public final void destroy()
    {
        if (b != null)
        {
            b.destroy();
        }
        if (c != null)
        {
            c.destroy();
        }
    }

    public final Class getAdditionalParametersType()
    {
        return com/google/android/gms/ads/mediation/customevent/CustomEventExtras;
    }

    public final View getBannerView()
    {
        return a;
    }

    public final Class getServerParametersType()
    {
        return com/google/ads/mediation/customevent/CustomEventServerParameters;
    }

    public final volatile void requestBannerAd(MediationBannerListener mediationbannerlistener, Activity activity, MediationServerParameters mediationserverparameters, AdSize adsize, MediationAdRequest mediationadrequest, NetworkExtras networkextras)
    {
        requestBannerAd(mediationbannerlistener, activity, (CustomEventServerParameters)mediationserverparameters, adsize, mediationadrequest, (CustomEventExtras)networkextras);
    }

    public final void requestBannerAd(MediationBannerListener mediationbannerlistener, Activity activity, CustomEventServerParameters customeventserverparameters, AdSize adsize, MediationAdRequest mediationadrequest, CustomEventExtras customeventextras)
    {
        b = (CustomEventBanner)a(customeventserverparameters.className);
        if (b == null)
        {
            mediationbannerlistener.onFailedToReceiveAd(this, com.google.ads.AdRequest.ErrorCode.INTERNAL_ERROR);
            return;
        }
        Object obj;
        if (customeventextras == null)
        {
            obj = null;
        } else
        {
            obj = customeventextras.getExtra(customeventserverparameters.label);
        }
        b.requestBannerAd(new a(this, mediationbannerlistener), activity, customeventserverparameters.label, customeventserverparameters.parameter, adsize, mediationadrequest, obj);
    }

    public final volatile void requestInterstitialAd(MediationInterstitialListener mediationinterstitiallistener, Activity activity, MediationServerParameters mediationserverparameters, MediationAdRequest mediationadrequest, NetworkExtras networkextras)
    {
        requestInterstitialAd(mediationinterstitiallistener, activity, (CustomEventServerParameters)mediationserverparameters, mediationadrequest, (CustomEventExtras)networkextras);
    }

    public final void requestInterstitialAd(MediationInterstitialListener mediationinterstitiallistener, Activity activity, CustomEventServerParameters customeventserverparameters, MediationAdRequest mediationadrequest, CustomEventExtras customeventextras)
    {
        c = (CustomEventInterstitial)a(customeventserverparameters.className);
        if (c == null)
        {
            mediationinterstitiallistener.onFailedToReceiveAd(this, com.google.ads.AdRequest.ErrorCode.INTERNAL_ERROR);
            return;
        }
        Object obj;
        if (customeventextras == null)
        {
            obj = null;
        } else
        {
            obj = customeventextras.getExtra(customeventserverparameters.label);
        }
        c.requestInterstitialAd(new b(this, this, mediationinterstitiallistener), activity, customeventserverparameters.label, customeventserverparameters.parameter, mediationadrequest, obj);
    }

    public final void showInterstitial()
    {
        c.showInterstitial();
    }
}
