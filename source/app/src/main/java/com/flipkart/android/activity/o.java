// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.activity;

import android.animation.Animator;
import android.widget.TextView;

// Referenced classes of package com.flipkart.android.activity:
//            HomeFragmentHolderActivity

final class o
    implements android.animation.Animator.AnimatorListener
{

    private TextView a;
    private int b;

    o(HomeFragmentHolderActivity homefragmentholderactivity, TextView textview, int i)
    {
        a = textview;
        b = i;
        super();
    }

    public final void onAnimationCancel(Animator animator)
    {
    }

    public final void onAnimationEnd(Animator animator)
    {
        if (!a.getText().equals(Integer.valueOf(b)))
        {
            a.setText((new StringBuilder()).append(b).toString());
        }
    }

    public final void onAnimationRepeat(Animator animator)
    {
    }

    public final void onAnimationStart(Animator animator)
    {
    }
}
