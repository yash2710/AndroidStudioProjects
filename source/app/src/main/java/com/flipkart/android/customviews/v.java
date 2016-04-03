// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customviews;

import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.LinearLayout;

// Referenced classes of package com.flipkart.android.customviews:
//            OfferExpandablePanel

final class v extends Animation
{

    private final int a;
    private final int b;
    private OfferExpandablePanel c;

    public v(OfferExpandablePanel offerexpandablepanel, int i, int j)
    {
        c = offerexpandablepanel;
        super();
        a = i;
        b = j - i;
    }

    protected final void applyTransformation(float f, Transformation transformation)
    {
        android.view.ViewGroup.LayoutParams layoutparams = OfferExpandablePanel.f(c).getLayoutParams();
        layoutparams.height = (int)((float)a + f * (float)b);
        OfferExpandablePanel.f(c).setLayoutParams(layoutparams);
    }

    public final boolean willChangeBounds()
    {
        return true;
    }
}
