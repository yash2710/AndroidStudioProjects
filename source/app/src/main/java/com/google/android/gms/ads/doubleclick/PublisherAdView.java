// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.ads.doubleclick;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.internal.au;

// Referenced classes of package com.google.android.gms.ads.doubleclick:
//            PublisherAdRequest, AppEventListener

public final class PublisherAdView extends ViewGroup
{

    private final au kx;

    public PublisherAdView(Context context)
    {
        super(context);
        kx = new au(this);
    }

    public PublisherAdView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        kx = new au(this, attributeset, true);
    }

    public PublisherAdView(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        kx = new au(this, attributeset, true);
    }

    public final void destroy()
    {
        kx.destroy();
    }

    public final AdListener getAdListener()
    {
        return kx.getAdListener();
    }

    public final AdSize getAdSize()
    {
        return kx.getAdSize();
    }

    public final AdSize[] getAdSizes()
    {
        return kx.getAdSizes();
    }

    public final String getAdUnitId()
    {
        return kx.getAdUnitId();
    }

    public final AppEventListener getAppEventListener()
    {
        return kx.getAppEventListener();
    }

    public final void loadAd(PublisherAdRequest publisheradrequest)
    {
        kx.a(publisheradrequest.T());
    }

    protected final void onLayout(boolean flag, int i, int j, int k, int l)
    {
        View view = getChildAt(0);
        if (view != null && view.getVisibility() != 8)
        {
            int i1 = view.getMeasuredWidth();
            int j1 = view.getMeasuredHeight();
            int k1 = (k - i - i1) / 2;
            int l1 = (l - j - j1) / 2;
            view.layout(k1, l1, i1 + k1, j1 + l1);
        }
    }

    protected final void onMeasure(int i, int j)
    {
        View view = getChildAt(0);
        AdSize adsize = getAdSize();
        int k;
        int l;
        int i1;
        int j1;
        if (view != null && view.getVisibility() != 8)
        {
            measureChild(view, i, j);
            l = view.getMeasuredWidth();
            k = view.getMeasuredHeight();
        } else
        if (adsize != null)
        {
            Context context = getContext();
            l = adsize.getWidthInPixels(context);
            k = adsize.getHeightInPixels(context);
        } else
        {
            k = 0;
            l = 0;
        }
        i1 = Math.max(l, getSuggestedMinimumWidth());
        j1 = Math.max(k, getSuggestedMinimumHeight());
        setMeasuredDimension(View.resolveSize(i1, i), View.resolveSize(j1, j));
    }

    public final void pause()
    {
        kx.pause();
    }

    public final void recordManualImpression()
    {
        kx.recordManualImpression();
    }

    public final void resume()
    {
        kx.resume();
    }

    public final void setAdListener(AdListener adlistener)
    {
        kx.setAdListener(adlistener);
    }

    public final transient void setAdSizes(AdSize aadsize[])
    {
        if (aadsize == null || aadsize.length <= 0)
        {
            throw new IllegalArgumentException("The supported ad sizes must contain at least one valid ad size.");
        } else
        {
            kx.a(aadsize);
            return;
        }
    }

    public final void setAdUnitId(String s)
    {
        kx.setAdUnitId(s);
    }

    public final void setAppEventListener(AppEventListener appeventlistener)
    {
        kx.setAppEventListener(appeventlistener);
    }
}
