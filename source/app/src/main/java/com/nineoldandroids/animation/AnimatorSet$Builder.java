// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.nineoldandroids.animation;

import java.util.ArrayList;
import java.util.HashMap;

// Referenced classes of package com.nineoldandroids.animation:
//            AnimatorSet, e, ValueAnimator, c, 
//            Animator

public class a
{

    private e a;
    private AnimatorSet b;

    public a after(long l)
    {
        ValueAnimator valueanimator = ValueAnimator.ofFloat(new float[] {
            0.0F, 1.0F
        });
        valueanimator.setDuration(l);
        after(((Animator) (valueanimator)));
        return this;
    }

    public after after(Animator animator)
    {
        e e1 = (e)AnimatorSet.b(b).get(animator);
        if (e1 == null)
        {
            e1 = new e(animator);
            AnimatorSet.b(b).put(animator, e1);
            AnimatorSet.d(b).add(e1);
        }
        c c1 = new c(e1, 1);
        a.addDependency(c1);
        return this;
    }

    public a before(Animator animator)
    {
        e e1 = (e)AnimatorSet.b(b).get(animator);
        if (e1 == null)
        {
            e1 = new e(animator);
            AnimatorSet.b(b).put(animator, e1);
            AnimatorSet.d(b).add(e1);
        }
        e1.addDependency(new c(a, 1));
        return this;
    }

    public a with(Animator animator)
    {
        e e1 = (e)AnimatorSet.b(b).get(animator);
        if (e1 == null)
        {
            e1 = new e(animator);
            AnimatorSet.b(b).put(animator, e1);
            AnimatorSet.d(b).add(e1);
        }
        e1.addDependency(new c(a, 0));
        return this;
    }

    (AnimatorSet animatorset, Animator animator)
    {
        b = animatorset;
        super();
        a = (e)AnimatorSet.b(animatorset).get(animator);
        if (a == null)
        {
            a = new e(animator);
            AnimatorSet.b(animatorset).put(animator, a);
            AnimatorSet.d(animatorset).add(a);
        }
    }
}
