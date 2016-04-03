// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.a;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.ads.mediation.MediationBannerAdapter;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.dynamic.d;
import com.google.android.gms.dynamic.e;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import org.json.JSONObject;

// Referenced classes of package com.google.android.gms.internal:
//            eu, ai, bw, by, 
//            al, bv

public final class bx extends bu.a
{

    private final MediationAdapter nS;

    public bx(MediationAdapter mediationadapter)
    {
        nS = mediationadapter;
    }

    private Bundle a(String s, int i, String s1)
    {
        Bundle bundle;
        Bundle bundle1;
        eu.D((new StringBuilder("Server parameters: ")).append(s).toString());
        JSONObject jsonobject;
        Iterator iterator;
        String s2;
        try
        {
            bundle = new Bundle();
        }
        catch (Throwable throwable)
        {
            eu.c("Could not get Server Parameters Bundle.", throwable);
            throw new RemoteException();
        }
        if (s == null)
        {
            break MISSING_BLOCK_LABEL_118;
        }
        JVM INSTR new #42  <Class JSONObject>;
        jsonobject = JSONObjectInstrumentation.init(s);
        bundle1 = new Bundle();
        for (iterator = jsonobject.keys(); iterator.hasNext(); bundle1.putString(s2, jsonobject.getString(s2)))
        {
            s2 = (String)iterator.next();
        }

        bundle = bundle1;
        if (nS instanceof AdMobAdapter)
        {
            bundle.putString("adJson", s1);
            bundle.putInt("tagForChildDirectedTreatment", i);
        }
        return bundle;
    }

    public final void a(d d, ai ai1, String s, bv bv)
    {
        a(d, ai1, s, null, bv);
    }

    public final void a(d d, ai ai1, String s, String s1, bv bv)
    {
        if (!(nS instanceof MediationInterstitialAdapter))
        {
            eu.D((new StringBuilder("MediationAdapter is not a MediationInterstitialAdapter: ")).append(nS.getClass().getCanonicalName()).toString());
            throw new RemoteException();
        }
        eu.z("Requesting interstitial ad from adapter.");
        MediationInterstitialAdapter mediationinterstitialadapter;
        HashSet hashset;
        mediationinterstitialadapter = (MediationInterstitialAdapter)nS;
        if (ai1.lU == null)
        {
            break MISSING_BLOCK_LABEL_184;
        }
        hashset = new HashSet(ai1.lU);
_L1:
        bw bw1;
        Bundle bundle;
        bw1 = new bw(new Date(ai1.lS), ai1.lT, hashset, ai1.lV, ai1.lW);
        bundle = ai1.mc;
        Bundle bundle1;
        bundle1 = null;
        if (bundle == null)
        {
            break MISSING_BLOCK_LABEL_145;
        }
        bundle1 = ai1.mc.getBundle(mediationinterstitialadapter.getClass().getName());
        mediationinterstitialadapter.requestInterstitialAd((Context)e.e(d), new by(bv), a(s, ai1.lW, s1), bw1, bundle1);
        return;
        hashset = null;
          goto _L1
        Throwable throwable;
        throwable;
        eu.c("Could not request interstitial ad from adapter.", throwable);
        throw new RemoteException();
    }

    public final void a(d d, al al1, ai ai1, String s, bv bv)
    {
        a(d, al1, ai1, s, null, bv);
    }

    public final void a(d d, al al1, ai ai1, String s, String s1, bv bv)
    {
        if (!(nS instanceof MediationBannerAdapter))
        {
            eu.D((new StringBuilder("MediationAdapter is not a MediationBannerAdapter: ")).append(nS.getClass().getCanonicalName()).toString());
            throw new RemoteException();
        }
        eu.z("Requesting banner ad from adapter.");
        MediationBannerAdapter mediationbanneradapter;
        HashSet hashset;
        mediationbanneradapter = (MediationBannerAdapter)nS;
        if (ai1.lU == null)
        {
            break MISSING_BLOCK_LABEL_200;
        }
        hashset = new HashSet(ai1.lU);
_L1:
        bw bw1;
        Bundle bundle;
        bw1 = new bw(new Date(ai1.lS), ai1.lT, hashset, ai1.lV, ai1.lW);
        bundle = ai1.mc;
        Bundle bundle1;
        bundle1 = null;
        if (bundle == null)
        {
            break MISSING_BLOCK_LABEL_145;
        }
        bundle1 = ai1.mc.getBundle(mediationbanneradapter.getClass().getName());
        mediationbanneradapter.requestBannerAd((Context)e.e(d), new by(bv), a(s, ai1.lW, s1), com.google.android.gms.ads.a.a(al1.width, al1.height, al1.me), bw1, bundle1);
        return;
        hashset = null;
          goto _L1
        Throwable throwable;
        throwable;
        eu.c("Could not request banner ad from adapter.", throwable);
        throw new RemoteException();
    }

    public final void destroy()
    {
        try
        {
            nS.onDestroy();
            return;
        }
        catch (Throwable throwable)
        {
            eu.c("Could not destroy adapter.", throwable);
        }
        throw new RemoteException();
    }

    public final d getView()
    {
        if (!(nS instanceof MediationBannerAdapter))
        {
            eu.D((new StringBuilder("MediationAdapter is not a MediationBannerAdapter: ")).append(nS.getClass().getCanonicalName()).toString());
            throw new RemoteException();
        }
        d d;
        try
        {
            d = e.h(((MediationBannerAdapter)nS).getBannerView());
        }
        catch (Throwable throwable)
        {
            eu.c("Could not get banner view from adapter.", throwable);
            throw new RemoteException();
        }
        return d;
    }

    public final void pause()
    {
        try
        {
            nS.onPause();
            return;
        }
        catch (Throwable throwable)
        {
            eu.c("Could not pause adapter.", throwable);
        }
        throw new RemoteException();
    }

    public final void resume()
    {
        try
        {
            nS.onResume();
            return;
        }
        catch (Throwable throwable)
        {
            eu.c("Could not resume adapter.", throwable);
        }
        throw new RemoteException();
    }

    public final void showInterstitial()
    {
        if (!(nS instanceof MediationInterstitialAdapter))
        {
            eu.D((new StringBuilder("MediationAdapter is not a MediationInterstitialAdapter: ")).append(nS.getClass().getCanonicalName()).toString());
            throw new RemoteException();
        }
        eu.z("Showing interstitial from adapter.");
        try
        {
            ((MediationInterstitialAdapter)nS).showInterstitial();
            return;
        }
        catch (Throwable throwable)
        {
            eu.c("Could not show interstitial from adapter.", throwable);
        }
        throw new RemoteException();
    }
}
