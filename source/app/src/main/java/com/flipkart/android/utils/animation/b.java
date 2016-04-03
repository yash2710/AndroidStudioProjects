// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils.animation;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

final class b extends Animation
{

    private View a;
    private int b;

    b(View view, int i)
    {
        a = view;
        b = i;
        super();
    }

    protected final void applyTransformation(float f, Transformation transformation)
    {
        if (f == 1.0F)
        {
            a.setVisibility(8);
            return;
        } else
        {
            a.getLayoutParams().height = b - (int)(f * (float)b);
            a.requestLayout();
            return;
        }
    }

    public final boolean willChangeBounds()
    {
        return true;
    }
}
