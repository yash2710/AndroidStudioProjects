// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.nineoldandroids.view;

import android.view.View;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.ValueAnimator;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;

// Referenced classes of package com.nineoldandroids.view:
//            b, e, a

final class d
    implements com.nineoldandroids.animation.Animator.AnimatorListener, com.nineoldandroids.animation.ValueAnimator.AnimatorUpdateListener
{

    private b a;

    private d(b b1)
    {
        a = b1;
        super();
    }

    d(b b1, byte byte0)
    {
        this(b1);
    }

    public final void onAnimationCancel(Animator animator)
    {
        if (b.b(a) != null)
        {
            b.b(a).onAnimationCancel(animator);
        }
    }

    public final void onAnimationEnd(Animator animator)
    {
        if (b.b(a) != null)
        {
            b.b(a).onAnimationEnd(animator);
        }
        b.c(a).remove(animator);
        if (b.c(a).isEmpty())
        {
            b.a(a, null);
        }
    }

    public final void onAnimationRepeat(Animator animator)
    {
        if (b.b(a) != null)
        {
            b.b(a).onAnimationRepeat(animator);
        }
    }

    public final void onAnimationStart(Animator animator)
    {
        if (b.b(a) != null)
        {
            b.b(a).onAnimationStart(animator);
        }
    }

    public final void onAnimationUpdate(ValueAnimator valueanimator)
    {
        float f = valueanimator.getAnimatedFraction();
        e e1 = (e)b.c(a).get(valueanimator);
        if ((0x1ff & e1.a) != 0)
        {
            View view1 = (View)b.d(a).get();
            if (view1 != null)
            {
                view1.invalidate();
            }
        }
        ArrayList arraylist = e1.b;
        if (arraylist != null)
        {
            int i = arraylist.size();
            for (int j = 0; j < i; j++)
            {
                a a1 = (a)arraylist.get(j);
                float f1 = a1.b + f * a1.c;
                b.a(a, a1.a, f1);
            }

        }
        View view = (View)b.d(a).get();
        if (view != null)
        {
            view.invalidate();
        }
    }
}
