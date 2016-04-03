// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.nineoldandroids.view;

import android.animation.Animator;

// Referenced classes of package com.nineoldandroids.view:
//            f

final class g
    implements android.animation.Animator.AnimatorListener
{

    private com.nineoldandroids.animation.Animator.AnimatorListener a;

    g(f f, com.nineoldandroids.animation.Animator.AnimatorListener animatorlistener)
    {
        a = animatorlistener;
        super();
    }

    public final void onAnimationCancel(Animator animator)
    {
        a.onAnimationCancel(null);
    }

    public final void onAnimationEnd(Animator animator)
    {
        a.onAnimationEnd(null);
    }

    public final void onAnimationRepeat(Animator animator)
    {
        a.onAnimationRepeat(null);
    }

    public final void onAnimationStart(Animator animator)
    {
        a.onAnimationStart(null);
    }
}
