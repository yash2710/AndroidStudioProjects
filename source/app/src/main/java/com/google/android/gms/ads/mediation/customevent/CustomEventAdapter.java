// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.ads.mediation.customevent;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.mediation.MediationBannerAdapter;
import com.google.android.gms.ads.mediation.MediationBannerListener;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.MediationInterstitialListener;
import com.google.android.gms.internal.eu;

// Referenced classes of package com.google.android.gms.ads.mediation.customevent:
//            CustomEventBanner, CustomEventInterstitial

public final class CustomEventAdapter
    implements MediationBannerAdapter, MediationInterstitialAdapter
{

    private View n;
    private CustomEventBanner sW;
    private CustomEventInterstitial sX;

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

    private void a(View view)
    {
        n = view;
    }

    static void a(CustomEventAdapter customeventadapter, View view)
    {
        customeventadapter.a(view);
    }

    public final View getBannerView()
    {
        return n;
    }

    public final void onDestroy()
    {
        if (sW != null)
        {
            sW.onDestroy();
        }
        if (sX != null)
        {
            sX.onDestroy();
        }
    }

    public final void onPause()
    {
        if (sW != null)
        {
            sW.onPause();
        }
        if (sX != null)
        {
            sX.onPause();
        }
    }

    public final void onResume()
    {
        if (sW != null)
        {
            sW.onResume();
        }
        if (sX != null)
        {
            sX.onResume();
        }
    }

    public final void requestBannerAd(Context context, MediationBannerListener mediationbannerlistener, Bundle bundle, AdSize adsize, MediationAdRequest mediationadrequest, Bundle bundle1)
    {
        sW = (CustomEventBanner)a(bundle.getString("class_name"));
        if (sW == null)
        {
            mediationbannerlistener.onAdFailedToLoad(this, 0);
            return;
        }
        Bundle bundle2;
        if (bundle1 == null)
        {
            bundle2 = null;
        } else
        {
            bundle2 = bundle1.getBundle(bundle.getString("class_name"));
        }
        sW.requestBannerAd(context, new a(mediationbannerlistener), bundle.getString("parameter"), adsize, mediationadrequest, bundle2);
    }

    public final void requestInterstitialAd(Context context, MediationInterstitialListener mediationinterstitiallistener, Bundle bundle, MediationAdRequest mediationadrequest, Bundle bundle1)
    {
        sX = (CustomEventInterstitial)a(bundle.getString("class_name"));
        if (sX == null)
        {
            mediationinterstitiallistener.onAdFailedToLoad(this, 0);
            return;
        }
        Bundle bundle2;
        if (bundle1 == null)
        {
            bundle2 = null;
        } else
        {
            bundle2 = bundle1.getBundle(bundle.getString("class_name"));
        }
        sX.requestInterstitialAd(context, new b(this, mediationinterstitiallistener), bundle.getString("parameter"), mediationadrequest, bundle2);
    }

    public final void showInterstitial()
    {
        sX.showInterstitial();
    }

    private class a
        implements CustomEventBannerListener
    {

        private final MediationBannerListener l;
        private final CustomEventAdapter sY;

        public final void onAdClicked()
        {
            eu.z("Custom event adapter called onAdClicked.");
            l.onAdClicked(sY);
        }

        public final void onAdClosed()
        {
            eu.z("Custom event adapter called onAdClosed.");
            l.onAdClosed(sY);
        }

        public final void onAdFailedToLoad(int i)
        {
            eu.z("Custom event adapter called onAdFailedToLoad.");
            l.onAdFailedToLoad(sY, i);
        }

        public final void onAdLeftApplication()
        {
            eu.z("Custom event adapter called onAdLeftApplication.");
            l.onAdLeftApplication(sY);
        }

        public final void onAdLoaded(View view)
        {
            eu.z("Custom event adapter called onAdLoaded.");
            CustomEventAdapter.a(sY, view);
            l.onAdLoaded(sY);
        }

        public final void onAdOpened()
        {
            eu.z("Custom event adapter called onAdOpened.");
            l.onAdOpened(sY);
        }

        public a(MediationBannerListener mediationbannerlistener)
        {
            sY = CustomEventAdapter.this;
            l = mediationbannerlistener;
        }
    }


    private class b
        implements CustomEventInterstitialListener
    {

        private final MediationInterstitialListener m;
        private final CustomEventAdapter sY;
        final CustomEventAdapter sZ;

        public void onAdClicked()
        {
            eu.z("Custom event adapter called onAdClicked.");
            m.onAdClicked(sY);
        }

        public void onAdClosed()
        {
            eu.z("Custom event adapter called onAdClosed.");
            m.onAdClosed(sY);
        }

        public void onAdFailedToLoad(int i)
        {
            eu.z("Custom event adapter called onFailedToReceiveAd.");
            m.onAdFailedToLoad(sY, i);
        }

        public void onAdLeftApplication()
        {
            eu.z("Custom event adapter called onAdLeftApplication.");
            m.onAdLeftApplication(sY);
        }

        public void onAdLoaded()
        {
            eu.z("Custom event adapter called onReceivedAd.");
            m.onAdLoaded(sZ);
        }

        public void onAdOpened()
        {
            eu.z("Custom event adapter called onAdOpened.");
            m.onAdOpened(sY);
        }

        public b(CustomEventAdapter customeventadapter1, MediationInterstitialListener mediationinterstitiallistener)
        {
            sZ = CustomEventAdapter.this;
            super();
            sY = customeventadapter1;
            m = mediationinterstitiallistener;
        }
    }

}
