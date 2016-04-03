// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customviews;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

// Referenced classes of package com.flipkart.android.customviews:
//            ExpandablePanel

final class p extends Animation
{

    private final int a;
    private final int b;
    private ExpandablePanel c;

    public p(ExpandablePanel expandablepanel, int i, int j)
    {
        c = expandablepanel;
        super();
        a = i;
        b = j - i;
    }

    protected final void applyTransformation(float f, Transformation transformation)
    {
        android.view.ViewGroup.LayoutParams layoutparams = ExpandablePanel.e(c).getLayoutParams();
        layoutparams.height = (int)((float)a + f * (float)b);
        ExpandablePanel.e(c).setLayoutParams(layoutparams);
    }

    public final boolean willChangeBounds()
    {
        return true;
    }
}
