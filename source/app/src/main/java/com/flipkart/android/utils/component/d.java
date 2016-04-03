// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils.component;

import android.view.View;
import android.view.animation.TranslateAnimation;

final class d
    implements com.flipkart.android.customwidget.OMUInfiniteListView.ScrollListListener
{

    private View a;
    private View b;
    private View c;

    d(View view)
    {
        c = view;
        super();
        a = c.getRootView();
        b = a.findViewById(0x7f0a008a);
    }

    public final void hideAboveView(boolean flag)
    {
        if (!flag)
        {
            TranslateAnimation translateanimation = new TranslateAnimation(0.0F, 0.0F, 0.0F, -b.getHeight());
            translateanimation.setDuration(1000L);
            translateanimation.setFillAfter(true);
            b.startAnimation(translateanimation);
            b.setVisibility(8);
        }
    }

    public final void showAboveView(boolean flag)
    {
        if (!flag)
        {
            TranslateAnimation translateanimation = new TranslateAnimation(0.0F, 0.0F, -b.getHeight(), 0.0F);
            translateanimation.setDuration(200L);
            translateanimation.setFillAfter(true);
            b.startAnimation(translateanimation);
            b.setVisibility(0);
        }
    }
}
