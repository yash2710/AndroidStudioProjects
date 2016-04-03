// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.activity;

import android.animation.ValueAnimator;
import android.widget.TextView;

// Referenced classes of package com.flipkart.android.activity:
//            HomeFragmentHolderActivity

final class n
    implements android.animation.ValueAnimator.AnimatorUpdateListener
{

    private TextView a;
    private int b;

    n(HomeFragmentHolderActivity homefragmentholderactivity, TextView textview, int i)
    {
        a = textview;
        b = i;
        super();
    }

    public final void onAnimationUpdate(ValueAnimator valueanimator)
    {
        if (valueanimator.getCurrentPlayTime() > 600L && valueanimator.getCurrentPlayTime() < 650L)
        {
            a.setText((new StringBuilder()).append(b).toString());
        }
    }
}
