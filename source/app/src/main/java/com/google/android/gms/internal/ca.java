// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.Handler;
import android.os.RemoteException;
import com.google.ads.mediation.MediationBannerAdapter;
import com.google.ads.mediation.MediationBannerListener;
import com.google.ads.mediation.MediationInterstitialAdapter;
import com.google.ads.mediation.MediationInterstitialListener;

// Referenced classes of package com.google.android.gms.internal:
//            eu, et, bv, cb

public final class ca
    implements MediationBannerListener, MediationInterstitialListener
{

    private final bv nT;

    public ca(bv bv1)
    {
        nT = bv1;
    }

    static bv a(ca ca1)
    {
        return ca1.nT;
    }

    public final void onClick(MediationBannerAdapter mediationbanneradapter)
    {
        eu.z("Adapter called onClick.");
        if (!et.bW())
        {
            eu.D("onClick must be called on the main UI thread.");
            et.sv.post(new _cls1());
            return;
        }
        try
        {
            nT.onAdClicked();
            return;
        }
        catch (RemoteException remoteexception)
        {
            eu.c("Could not call onAdClicked.", remoteexception);
        }
    }

    public final void onDismissScreen(MediationBannerAdapter mediationbanneradapter)
    {
        eu.z("Adapter called onDismissScreen.");
        if (!et.bW())
        {
            eu.D("onDismissScreen must be called on the main UI thread.");
            et.sv.post(new _cls4());
            return;
        }
        try
        {
            nT.onAdClosed();
            return;
        }
        catch (RemoteException remoteexception)
        {
            eu.c("Could not call onAdClosed.", remoteexception);
        }
    }

    public final void onDismissScreen(MediationInterstitialAdapter mediationinterstitialadapter)
    {
        eu.z("Adapter called onDismissScreen.");
        if (!et.bW())
        {
            eu.D("onDismissScreen must be called on the main UI thread.");
            et.sv.post(new _cls9());
            return;
        }
        try
        {
            nT.onAdClosed();
            return;
        }
        catch (RemoteException remoteexception)
        {
            eu.c("Could not call onAdClosed.", remoteexception);
        }
    }

    public final void onFailedToReceiveAd(MediationBannerAdapter mediationbanneradapter, com.google.ads.AdRequest.ErrorCode errorcode)
    {
        eu.z((new StringBuilder("Adapter called onFailedToReceiveAd with error. ")).append(errorcode).toString());
        if (!et.bW())
        {
            eu.D("onFailedToReceiveAd must be called on the main UI thread.");
            et.sv.post(new _cls5(errorcode));
            return;
        }
        try
        {
            nT.onAdFailedToLoad(cb.a(errorcode));
            return;
        }
        catch (RemoteException remoteexception)
        {
            eu.c("Could not call onAdFailedToLoad.", remoteexception);
        }
    }

    public final void onFailedToReceiveAd(MediationInterstitialAdapter mediationinterstitialadapter, com.google.ads.AdRequest.ErrorCode errorcode)
    {
        eu.z((new StringBuilder("Adapter called onFailedToReceiveAd with error ")).append(errorcode).append(".").toString());
        if (!et.bW())
        {
            eu.D("onFailedToReceiveAd must be called on the main UI thread.");
            et.sv.post(new _cls10(errorcode));
            return;
        }
        try
        {
            nT.onAdFailedToLoad(cb.a(errorcode));
            return;
        }
        catch (RemoteException remoteexception)
        {
            eu.c("Could not call onAdFailedToLoad.", remoteexception);
        }
    }

    public final void onLeaveApplication(MediationBannerAdapter mediationbanneradapter)
    {
        eu.z("Adapter called onLeaveApplication.");
        if (!et.bW())
        {
            eu.D("onLeaveApplication must be called on the main UI thread.");
            et.sv.post(new _cls6());
            return;
        }
        try
        {
            nT.onAdLeftApplication();
            return;
        }
        catch (RemoteException remoteexception)
        {
            eu.c("Could not call onAdLeftApplication.", remoteexception);
        }
    }

    public final void onLeaveApplication(MediationInterstitialAdapter mediationinterstitialadapter)
    {
        eu.z("Adapter called onLeaveApplication.");
        if (!et.bW())
        {
            eu.D("onLeaveApplication must be called on the main UI thread.");
            et.sv.post(new _cls11());
            return;
        }
        try
        {
            nT.onAdLeftApplication();
            return;
        }
        catch (RemoteException remoteexception)
        {
            eu.c("Could not call onAdLeftApplication.", remoteexception);
        }
    }

    public final void onPresentScreen(MediationBannerAdapter mediationbanneradapter)
    {
        eu.z("Adapter called onPresentScreen.");
        if (!et.bW())
        {
            eu.D("onPresentScreen must be called on the main UI thread.");
            et.sv.post(new _cls7());
            return;
        }
        try
        {
            nT.onAdOpened();
            return;
        }
        catch (RemoteException remoteexception)
        {
            eu.c("Could not call onAdOpened.", remoteexception);
        }
    }

    public final void onPresentScreen(MediationInterstitialAdapter mediationinterstitialadapter)
    {
        eu.z("Adapter called onPresentScreen.");
        if (!et.bW())
        {
            eu.D("onPresentScreen must be called on the main UI thread.");
            et.sv.post(new _cls2());
            return;
        }
        try
        {
            nT.onAdOpened();
            return;
        }
        catch (RemoteException remoteexception)
        {
            eu.c("Could not call onAdOpened.", remoteexception);
        }
    }

    public final void onReceivedAd(MediationBannerAdapter mediationbanneradapter)
    {
        eu.z("Adapter called onReceivedAd.");
        if (!et.bW())
        {
            eu.D("onReceivedAd must be called on the main UI thread.");
            et.sv.post(new _cls8());
            return;
        }
        try
        {
            nT.onAdLoaded();
            return;
        }
        catch (RemoteException remoteexception)
        {
            eu.c("Could not call onAdLoaded.", remoteexception);
        }
    }

    public final void onReceivedAd(MediationInterstitialAdapter mediationinterstitialadapter)
    {
        eu.z("Adapter called onReceivedAd.");
        if (!et.bW())
        {
            eu.D("onReceivedAd must be called on the main UI thread.");
            et.sv.post(new _cls3());
            return;
        }
        try
        {
            nT.onAdLoaded();
            return;
        }
        catch (RemoteException remoteexception)
        {
            eu.c("Could not call onAdLoaded.", remoteexception);
        }
    }

    private class _cls1
        implements Runnable
    {

        final ca nW;

        public void run()
        {
            try
            {
                ca.a(nW).onAdClicked();
                return;
            }
            catch (RemoteException remoteexception)
            {
                eu.c("Could not call onAdClicked.", remoteexception);
            }
        }

        _cls1()
        {
            nW = ca.this;
            super();
        }
    }


    private class _cls4
        implements Runnable
    {

        final ca nW;

        public void run()
        {
            try
            {
                ca.a(nW).onAdClosed();
                return;
            }
            catch (RemoteException remoteexception)
            {
                eu.c("Could not call onAdClosed.", remoteexception);
            }
        }

        _cls4()
        {
            nW = ca.this;
            super();
        }
    }


    private class _cls9
        implements Runnable
    {

        final ca nW;

        public void run()
        {
            try
            {
                ca.a(nW).onAdClosed();
                return;
            }
            catch (RemoteException remoteexception)
            {
                eu.c("Could not call onAdClosed.", remoteexception);
            }
        }

        _cls9()
        {
            nW = ca.this;
            super();
        }
    }


    private class _cls5
        implements Runnable
    {

        final ca nW;
        final com.google.ads.AdRequest.ErrorCode nX;

        public void run()
        {
            try
            {
                ca.a(nW).onAdFailedToLoad(cb.a(nX));
                return;
            }
            catch (RemoteException remoteexception)
            {
                eu.c("Could not call onAdFailedToLoad.", remoteexception);
            }
        }

        _cls5(com.google.ads.AdRequest.ErrorCode errorcode)
        {
            nW = ca.this;
            nX = errorcode;
            super();
        }
    }


    private class _cls10
        implements Runnable
    {

        final ca nW;
        final com.google.ads.AdRequest.ErrorCode nX;

        public void run()
        {
            try
            {
                ca.a(nW).onAdFailedToLoad(cb.a(nX));
                return;
            }
            catch (RemoteException remoteexception)
            {
                eu.c("Could not call onAdFailedToLoad.", remoteexception);
            }
        }

        _cls10(com.google.ads.AdRequest.ErrorCode errorcode)
        {
            nW = ca.this;
            nX = errorcode;
            super();
        }
    }


    private class _cls6
        implements Runnable
    {

        final ca nW;

        public void run()
        {
            try
            {
                ca.a(nW).onAdLeftApplication();
                return;
            }
            catch (RemoteException remoteexception)
            {
                eu.c("Could not call onAdLeftApplication.", remoteexception);
            }
        }

        _cls6()
        {
            nW = ca.this;
            super();
        }
    }


    private class _cls11
        implements Runnable
    {

        final ca nW;

        public void run()
        {
            try
            {
                ca.a(nW).onAdLeftApplication();
                return;
            }
            catch (RemoteException remoteexception)
            {
                eu.c("Could not call onAdLeftApplication.", remoteexception);
            }
        }

        _cls11()
        {
            nW = ca.this;
            super();
        }
    }


    private class _cls7
        implements Runnable
    {

        final ca nW;

        public void run()
        {
            try
            {
                ca.a(nW).onAdOpened();
                return;
            }
            catch (RemoteException remoteexception)
            {
                eu.c("Could not call onAdOpened.", remoteexception);
            }
        }

        _cls7()
        {
            nW = ca.this;
            super();
        }
    }


    private class _cls2
        implements Runnable
    {

        final ca nW;

        public void run()
        {
            try
            {
                ca.a(nW).onAdOpened();
                return;
            }
            catch (RemoteException remoteexception)
            {
                eu.c("Could not call onAdOpened.", remoteexception);
            }
        }

        _cls2()
        {
            nW = ca.this;
            super();
        }
    }


    private class _cls8
        implements Runnable
    {

        final ca nW;

        public void run()
        {
            try
            {
                ca.a(nW).onAdLoaded();
                return;
            }
            catch (RemoteException remoteexception)
            {
                eu.c("Could not call onAdLoaded.", remoteexception);
            }
        }

        _cls8()
        {
            nW = ca.this;
            super();
        }
    }


    private class _cls3
        implements Runnable
    {

        final ca nW;

        public void run()
        {
            try
            {
                ca.a(nW).onAdLoaded();
                return;
            }
            catch (RemoteException remoteexception)
            {
                eu.c("Could not call onAdLoaded.", remoteexception);
            }
        }

        _cls3()
        {
            nW = ca.this;
            super();
        }
    }

}
