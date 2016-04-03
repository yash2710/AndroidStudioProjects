// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils.animation;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

final class a extends Animation
{

    private android.view.ViewGroup.LayoutParams a;
    private int b;
    private View c;

    a(android.view.ViewGroup.LayoutParams layoutparams, int i, View view)
    {
        a = layoutparams;
        b = i;
        c = view;
        super();
    }

    protected final void applyTransformation(float f, Transformation transformation)
    {
        android.view.ViewGroup.LayoutParams layoutparams = a;
        int i;
        if (f == 1.0F)
        {
            i = -2;
        } else
        {
            i = (int)(f * (float)b);
        }
        layoutparams.height = i;
        if (a.height < b)
        {
            c.setLayoutParams(a);
        }
    }

    public final boolean willChangeBounds()
    {
        return true;
    }
}
