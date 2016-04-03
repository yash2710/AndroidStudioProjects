// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.nineoldandroids.animation;

import java.util.ArrayList;
import java.util.HashMap;

// Referenced classes of package com.nineoldandroids.animation:
//            AnimatorSet, Animator, e

final class b
    implements Animator.AnimatorListener
{

    private AnimatorSet a;
    private AnimatorSet b;

    b(AnimatorSet animatorset, AnimatorSet animatorset1)
    {
        b = animatorset;
        super();
        a = animatorset1;
    }

    public final void onAnimationCancel(Animator animator)
    {
        if (!b.b && AnimatorSet.a(b).size() == 0 && b.a != null)
        {
            int i = b.a.size();
            for (int j = 0; j < i; j++)
            {
                ((Animator.AnimatorListener)b.a.get(j)).onAnimationCancel(a);
            }

        }
    }

    public final void onAnimationEnd(Animator animator)
    {
        animator.removeListener(this);
        AnimatorSet.a(b).remove(animator);
        ((e)AnimatorSet.b(a).get(animator)).f = true;
        if (b.b) goto _L2; else goto _L1
_L1:
        ArrayList arraylist;
        int i;
        int j;
        arraylist = AnimatorSet.c(a);
        i = arraylist.size();
        j = 0;
_L5:
        if (j >= i)
        {
            break MISSING_BLOCK_LABEL_175;
        }
        if (((e)arraylist.get(j)).f) goto _L4; else goto _L3
_L3:
        boolean flag = false;
_L6:
        if (flag)
        {
            if (b.a != null)
            {
                ArrayList arraylist1 = (ArrayList)b.a.clone();
                int k = arraylist1.size();
                for (int l = 0; l < k; l++)
                {
                    ((Animator.AnimatorListener)arraylist1.get(l)).onAnimationEnd(a);
                }

            }
            AnimatorSet.a(a, false);
        }
          goto _L2
_L4:
        j++;
          goto _L5
_L2:
        return;
        flag = true;
          goto _L6
    }

    public final void onAnimationRepeat(Animator animator)
    {
    }

    public final void onAnimationStart(Animator animator)
    {
    }
}
