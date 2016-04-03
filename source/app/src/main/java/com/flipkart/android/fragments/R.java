// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments;

import android.os.Handler;
import android.view.animation.Animation;

// Referenced classes of package com.flipkart.android.fragments:
//            ProductListFragment, S

final class R
    implements android.view.animation.Animation.AnimationListener
{

    final ProductListFragment a;

    R(ProductListFragment productlistfragment)
    {
        a = productlistfragment;
        super();
    }

    public final void onAnimationEnd(Animation animation)
    {
        if (a.a != null)
        {
            a.a.post(new S(this));
        }
    }

    public final void onAnimationRepeat(Animation animation)
    {
    }

    public final void onAnimationStart(Animation animation)
    {
    }
}
