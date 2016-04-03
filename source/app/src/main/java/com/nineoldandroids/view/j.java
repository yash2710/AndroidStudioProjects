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
//            h, l, k

final class j
    implements com.nineoldandroids.animation.Animator.AnimatorListener, com.nineoldandroids.animation.ValueAnimator.AnimatorUpdateListener
{

    private h a;

    private j(h h1)
    {
        a = h1;
        super();
    }

    j(h h1, byte byte0)
    {
        this(h1);
    }

    public final void onAnimationCancel(Animator animator)
    {
        if (h.b(a) != null)
        {
            h.b(a).onAnimationCancel(animator);
        }
    }

    public final void onAnimationEnd(Animator animator)
    {
        if (h.b(a) != null)
        {
            h.b(a).onAnimationEnd(animator);
        }
        h.c(a).remove(animator);
        if (h.c(a).isEmpty())
        {
            h.a(a, null);
        }
    }

    public final void onAnimationRepeat(Animator animator)
    {
        if (h.b(a) != null)
        {
            h.b(a).onAnimationRepeat(animator);
        }
    }

    public final void onAnimationStart(Animator animator)
    {
        if (h.b(a) != null)
        {
            h.b(a).onAnimationStart(animator);
        }
    }

    public final void onAnimationUpdate(ValueAnimator valueanimator)
    {
        float f = valueanimator.getAnimatedFraction();
        l l1 = (l)h.c(a).get(valueanimator);
        if ((0x1ff & l1.a) != 0)
        {
            View view1 = (View)h.d(a).get();
            if (view1 != null)
            {
                view1.invalidate();
            }
        }
        ArrayList arraylist = l1.b;
        if (arraylist != null)
        {
            int i = arraylist.size();
            for (int i1 = 0; i1 < i; i1++)
            {
                k k1 = (k)arraylist.get(i1);
                float f1 = k1.b + f * k1.c;
                h.a(a, k1.a, f1);
            }

        }
        View view = (View)h.d(a).get();
        if (view != null)
        {
            view.invalidate();
        }
    }
}
