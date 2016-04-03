// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.ads.mediation.admob;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.mediation.MediationBannerAdapter;
import com.google.android.gms.ads.mediation.MediationBannerListener;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.MediationInterstitialListener;
import com.google.android.gms.internal.et;
import java.util.Iterator;
import java.util.Set;

// Referenced classes of package com.google.ads.mediation.admob:
//            a, b

public final class AdMobAdapter
    implements MediationBannerAdapter, MediationInterstitialAdapter
{

    private AdView a;
    private InterstitialAd b;

    public AdMobAdapter()
    {
    }

    private static AdRequest a(Context context, MediationAdRequest mediationadrequest, Bundle bundle, Bundle bundle1)
    {
        com.google.android.gms.ads.AdRequest.Builder builder = new com.google.android.gms.ads.AdRequest.Builder();
        java.util.Date date = mediationadrequest.getBirthday();
        if (date != null)
        {
            builder.setBirthday(date);
        }
        int i = mediationadrequest.getGender();
        if (i != 0)
        {
            builder.setGender(i);
        }
        Set set = mediationadrequest.getKeywords();
        if (set != null)
        {
            for (Iterator iterator = set.iterator(); iterator.hasNext(); builder.addKeyword((String)iterator.next())) { }
        }
        if (mediationadrequest.isTesting())
        {
            builder.addTestDevice(et.r(context));
        }
        if (bundle1.getInt("tagForChildDirectedTreatment") != -1)
        {
            boolean flag;
            if (bundle1.getInt("tagForChildDirectedTreatment") == 1)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            builder.tagForChildDirectedTreatment(flag);
        }
        if (bundle == null)
        {
            bundle = new Bundle();
        }
        bundle.putInt("gw", 1);
        bundle.putString("mad_hac", bundle1.getString("mad_hac"));
        if (!TextUtils.isEmpty(bundle1.getString("adJson")))
        {
            bundle.putString("_ad", bundle1.getString("adJson"));
        }
        bundle.putBoolean("_noRefresh", true);
        builder.addNetworkExtrasBundle(com/google/ads/mediation/admob/AdMobAdapter, bundle);
        return builder.build();
    }

    public final View getBannerView()
    {
        return a;
    }

    public final void onDestroy()
    {
        if (a != null)
        {
            a.destroy();
            a = null;
        }
        if (b != null)
        {
            b = null;
        }
    }

    public final void onPause()
    {
        if (a != null)
        {
            a.pause();
        }
    }

    public final void onResume()
    {
        if (a != null)
        {
            a.resume();
        }
    }

    public final void requestBannerAd(Context context, MediationBannerListener mediationbannerlistener, Bundle bundle, AdSize adsize, MediationAdRequest mediationadrequest, Bundle bundle1)
    {
        a = new AdView(context);
        a.setAdSize(new AdSize(adsize.getWidth(), adsize.getHeight()));
        a.setAdUnitId(bundle.getString("pubid"));
        a.setAdListener(new a(this, mediationbannerlistener));
        a.loadAd(a(context, mediationadrequest, bundle1, bundle));
    }

    public final void requestInterstitialAd(Context context, MediationInterstitialListener mediationinterstitiallistener, Bundle bundle, MediationAdRequest mediationadrequest, Bundle bundle1)
    {
        b = new InterstitialAd(context);
        b.setAdUnitId(bundle.getString("pubid"));
        b.setAdListener(new b(this, mediationinterstitiallistener));
        b.loadAd(a(context, mediationadrequest, bundle1, bundle));
    }

    public final void showInterstitial()
    {
        b.show();
    }
}
