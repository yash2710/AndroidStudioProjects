// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments;

import android.view.MotionEvent;
import android.view.View;

// Referenced classes of package com.flipkart.android.fragments:
//            ProductListFragment

final class Q
    implements android.view.View.OnTouchListener
{

    private ProductListFragment a;

    Q(ProductListFragment productlistfragment)
    {
        a = productlistfragment;
        super();
    }

    public final boolean onTouch(View view, MotionEvent motionevent)
    {
        int i = motionevent.getAction();
        if (!ProductListFragment.c(a))
        {
            return false;
        }
        if (i != 0 && i == 2 && ProductListFragment.d(a) != 0.0F && Math.abs(ProductListFragment.d(a) - motionevent.getY()) > 2.0F)
        {
            if (motionevent.getY() < ProductListFragment.d(a))
            {
                ProductListFragment.e(a);
            } else
            if (motionevent.getY() > ProductListFragment.d(a))
            {
                ProductListFragment.f(a);
            }
        }
        if (i == 1 || i == 3)
        {
            ProductListFragment.a(a, 0.0F);
            return false;
        } else
        {
            ProductListFragment.a(a, motionevent.getY());
            return false;
        }
    }
}
