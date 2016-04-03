// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customviews;

import android.view.View;
import com.nineoldandroids.animation.ValueAnimator;

// Referenced classes of package com.flipkart.android.customviews:
//            EnhancedListView

final class j
    implements com.nineoldandroids.animation.ValueAnimator.AnimatorUpdateListener
{

    private android.view.ViewGroup.LayoutParams a;
    private View b;

    j(EnhancedListView enhancedlistview, android.view.ViewGroup.LayoutParams layoutparams, View view)
    {
        a = layoutparams;
        b = view;
        super();
    }

    public final void onAnimationUpdate(ValueAnimator valueanimator)
    {
        a.height = ((Integer)valueanimator.getAnimatedValue()).intValue();
        b.setLayoutParams(a);
    }
}
