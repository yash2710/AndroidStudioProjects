// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.RemoteException;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.doubleclick.AppEventListener;
import com.google.android.gms.ads.purchase.InAppPurchaseListener;
import com.google.android.gms.ads.purchase.PlayStorePurchaseListener;
import com.google.android.gms.dynamic.e;

// Referenced classes of package com.google.android.gms.internal:
//            ak, bs, ao, al, 
//            et, aq, eu, ah, 
//            ag, an, dh, dl, 
//            at

public final class au
{

    private AdListener lQ;
    private ViewGroup mA;
    private InAppPurchaseListener mB;
    private PlayStorePurchaseListener mC;
    private AppEventListener mh;
    private AdSize mi[];
    private String mj;
    private final bs mw;
    private final ak mx;
    private aq my;
    private String mz;

    public au(ViewGroup viewgroup)
    {
        this(viewgroup, null, false, ak.aF());
    }

    public au(ViewGroup viewgroup, AttributeSet attributeset, boolean flag)
    {
        this(viewgroup, attributeset, flag, ak.aF());
    }

    au(ViewGroup viewgroup, AttributeSet attributeset, boolean flag, ak ak1)
    {
        mw = new bs();
        mA = viewgroup;
        mx = ak1;
        if (attributeset != null)
        {
            android.content.Context context = viewgroup.getContext();
            try
            {
                ao ao1 = new ao(context, attributeset);
                mi = ao1.f(flag);
                mj = ao1.getAdUnitId();
            }
            catch (IllegalArgumentException illegalargumentexception)
            {
                et.a(viewgroup, new al(context, AdSize.BANNER), illegalargumentexception.getMessage(), illegalargumentexception.getMessage());
                return;
            }
            if (viewgroup.isInEditMode())
            {
                et.a(viewgroup, new al(context, mi[0]), "Ads by Google");
            }
        }
    }

    private void aL()
    {
        com.google.android.gms.dynamic.d d = my.U();
        if (d == null)
        {
            return;
        }
        try
        {
            mA.addView((View)e.e(d));
            return;
        }
        catch (RemoteException remoteexception)
        {
            eu.c("Failed to get an ad frame.", remoteexception);
        }
        return;
    }

    private void aM()
    {
        if ((mi == null || mj == null) && my == null)
        {
            throw new IllegalStateException("The ad size and ad unit ID must be set before loadAd is called.");
        }
        android.content.Context context = mA.getContext();
        my = ah.a(context, new al(context, mi), mj, mw);
        if (lQ != null)
        {
            my.a(new ag(lQ));
        }
        if (mh != null)
        {
            my.a(new an(mh));
        }
        if (mB != null)
        {
            my.a(new dh(mB));
        }
        if (mC != null)
        {
            my.a(new dl(mC), mz);
        }
        aL();
    }

    public final void a(at at1)
    {
        try
        {
            if (my == null)
            {
                aM();
            }
            if (my.a(mx.a(mA.getContext(), at1)))
            {
                mw.c(at1.aI());
            }
            return;
        }
        catch (RemoteException remoteexception)
        {
            eu.c("Failed to load ad.", remoteexception);
        }
    }

    public final transient void a(AdSize aadsize[])
    {
        mi = aadsize;
        try
        {
            if (my != null)
            {
                my.a(new al(mA.getContext(), mi));
            }
        }
        catch (RemoteException remoteexception)
        {
            eu.c("Failed to set the ad size.", remoteexception);
        }
        mA.requestLayout();
    }

    public final void destroy()
    {
        try
        {
            if (my != null)
            {
                my.destroy();
            }
            return;
        }
        catch (RemoteException remoteexception)
        {
            eu.c("Failed to destroy AdView.", remoteexception);
        }
    }

    public final AdListener getAdListener()
    {
        return lQ;
    }

    public final AdSize getAdSize()
    {
        AdSize adsize;
        if (my == null)
        {
            break MISSING_BLOCK_LABEL_29;
        }
        adsize = my.V().aG();
        return adsize;
        RemoteException remoteexception;
        remoteexception;
        eu.c("Failed to get the current AdSize.", remoteexception);
        if (mi != null)
        {
            return mi[0];
        } else
        {
            return null;
        }
    }

    public final AdSize[] getAdSizes()
    {
        return mi;
    }

    public final String getAdUnitId()
    {
        return mj;
    }

    public final AppEventListener getAppEventListener()
    {
        return mh;
    }

    public final InAppPurchaseListener getInAppPurchaseListener()
    {
        return mB;
    }

    public final void pause()
    {
        try
        {
            if (my != null)
            {
                my.pause();
            }
            return;
        }
        catch (RemoteException remoteexception)
        {
            eu.c("Failed to call pause.", remoteexception);
        }
    }

    public final void recordManualImpression()
    {
        try
        {
            my.ag();
            return;
        }
        catch (RemoteException remoteexception)
        {
            eu.c("Failed to record impression.", remoteexception);
        }
    }

    public final void resume()
    {
        try
        {
            if (my != null)
            {
                my.resume();
            }
            return;
        }
        catch (RemoteException remoteexception)
        {
            eu.c("Failed to call resume.", remoteexception);
        }
    }

    public final void setAdListener(AdListener adlistener)
    {
        lQ = adlistener;
        if (my == null) goto _L2; else goto _L1
_L1:
        aq aq1 = my;
        if (adlistener == null)
        {
            break MISSING_BLOCK_LABEL_40;
        }
        ag ag1 = new ag(adlistener);
_L3:
        aq1.a(ag1);
_L2:
        return;
        ag1 = null;
          goto _L3
        RemoteException remoteexception;
        remoteexception;
        eu.c("Failed to set the AdListener.", remoteexception);
        return;
    }

    public final transient void setAdSizes(AdSize aadsize[])
    {
        if (mi != null)
        {
            throw new IllegalStateException("The ad size can only be set once on AdView.");
        } else
        {
            a(aadsize);
            return;
        }
    }

    public final void setAdUnitId(String s)
    {
        if (mj != null)
        {
            throw new IllegalStateException("The ad unit ID can only be set once on AdView.");
        } else
        {
            mj = s;
            return;
        }
    }

    public final void setAppEventListener(AppEventListener appeventlistener)
    {
        mh = appeventlistener;
        if (my == null) goto _L2; else goto _L1
_L1:
        aq aq1 = my;
        if (appeventlistener == null)
        {
            break MISSING_BLOCK_LABEL_40;
        }
        an an1 = new an(appeventlistener);
_L3:
        aq1.a(an1);
_L2:
        return;
        an1 = null;
          goto _L3
        RemoteException remoteexception;
        remoteexception;
        eu.c("Failed to set the AppEventListener.", remoteexception);
        return;
    }

    public final void setInAppPurchaseListener(InAppPurchaseListener inapppurchaselistener)
    {
        if (mC != null)
        {
            throw new IllegalStateException("Play store purchase parameter has already been set.");
        }
        mB = inapppurchaselistener;
        if (my == null) goto _L2; else goto _L1
_L1:
        aq aq1 = my;
        if (inapppurchaselistener == null)
        {
            break MISSING_BLOCK_LABEL_58;
        }
        dh dh1 = new dh(inapppurchaselistener);
_L3:
        aq1.a(dh1);
_L2:
        return;
        dh1 = null;
          goto _L3
        RemoteException remoteexception;
        remoteexception;
        eu.c("Failed to set the InAppPurchaseListener.", remoteexception);
        return;
    }

    public final void setPlayStorePurchaseParams(PlayStorePurchaseListener playstorepurchaselistener, String s)
    {
        if (mB != null)
        {
            throw new IllegalStateException("InAppPurchaseListener has already been set.");
        }
        mC = playstorepurchaselistener;
        mz = s;
        if (my == null) goto _L2; else goto _L1
_L1:
        aq aq1 = my;
        if (playstorepurchaselistener == null)
        {
            break MISSING_BLOCK_LABEL_66;
        }
        dl dl1 = new dl(playstorepurchaselistener);
_L3:
        aq1.a(dl1, s);
_L2:
        return;
        dl1 = null;
          goto _L3
        RemoteException remoteexception;
        remoteexception;
        eu.c("Failed to set the play store purchase parameter.", remoteexception);
        return;
    }
}
