// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils.animation;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

// Referenced classes of package com.flipkart.android.utils.animation:
//            b, a

public class FkAnimationUtils
{

    private static int a = 800;

    public FkAnimationUtils()
    {
    }

    public static void collapseViewAnim(View view)
    {
        int i = view.getMeasuredHeight();
        b b1 = new b(view, i);
        b1.setDuration((int)((float)i / view.getContext().getResources().getDisplayMetrics().density));
        view.startAnimation(b1);
    }

    public static void expandViewAnim(View view)
    {
        view.measure(-1, -2);
        view.setPivotY(view.getY() + (float)(view.getMeasuredHeight() / 2));
        int i = view.getMeasuredHeight();
        android.view.ViewGroup.LayoutParams layoutparams = view.getLayoutParams();
        layoutparams.height = 0;
        view.setLayoutParams(layoutparams);
        view.requestLayout();
        view.setVisibility(0);
        a a1 = new a(layoutparams, i, view);
        a1.setDuration(a);
        view.startAnimation(a1);
    }

    public static void performAlphaAnimationWithCustomTime(int i, View view, Context context)
    {
        Animation animation = AnimationUtils.loadAnimation(context, 0x7f040008);
        animation.setDuration(i);
        view.startAnimation(animation);
    }

    public static void performRightToLeftSwipeAnim(View view, Context context)
    {
        view.startAnimation(AnimationUtils.loadAnimation(context, 0x7f04000b));
    }

    public static void performScaleWithCustomTime(int i, View view, Context context)
    {
        Animation animation = AnimationUtils.loadAnimation(context, 0x7f04000c);
        animation.setDuration(i);
        view.startAnimation(animation);
    }

    public static void performScaleXWithCustomTime(int i, View view, Context context)
    {
        Animation animation = AnimationUtils.loadAnimation(context, 0x7f04000d);
        animation.setDuration(i);
        view.startAnimation(animation);
    }

    public static void performScaleYAnimation(Context context, View view)
    {
        view.startAnimation(AnimationUtils.loadAnimation(context, 0x7f04000c));
    }

}
