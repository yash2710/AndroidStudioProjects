// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils.drawable;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import com.flipkart.android.utils.ScreenMathUtils;

public class GradientDrawableUtils
{

    public GradientDrawableUtils()
    {
    }

    public static GradientDrawable getGradintDrawable(int i, int j)
    {
        GradientDrawable gradientdrawable = new GradientDrawable();
        gradientdrawable.setCornerRadius(5F);
        gradientdrawable.setShape(0);
        gradientdrawable.setColor(i);
        if (j != -1)
        {
            gradientdrawable.setColor(j);
        }
        return gradientdrawable;
    }

    public static GradientDrawable getRoundedDrawable(int i, int j, Context context)
    {
        GradientDrawable gradientdrawable = new GradientDrawable();
        gradientdrawable.setShape(1);
        gradientdrawable.setColor(j);
        gradientdrawable.setStroke(ScreenMathUtils.dpToPx(4, context), i);
        return gradientdrawable;
    }
}
