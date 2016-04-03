// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customviews;

import android.content.Context;
import android.util.AttributeSet;

public class weight extends android.view.Layout.LayoutParams
{

    private float a;
    private float b;
    public float weight;

    static float a(weight weight1)
    {
        return weight1.a;
    }

    static float a(a a1, float f)
    {
        a1.a = f;
        return f;
    }

    static float b(a a1)
    {
        return a1.b;
    }

    static float b(b b1, float f)
    {
        b1.b = f;
        return f;
    }

    public (int i, int j)
    {
        super(i, j);
        weight = 1.0F;
    }

    public weight(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        weight = 1.0F;
    }
}
