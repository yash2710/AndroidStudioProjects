// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.nineoldandroids.view;

import android.view.View;
import android.view.animation.Interpolator;
import java.util.WeakHashMap;

// Referenced classes of package com.nineoldandroids.view:
//            f, b, h

public abstract class ViewPropertyAnimator
{

    private static final WeakHashMap a = new WeakHashMap(0);

    public ViewPropertyAnimator()
    {
    }

    public static ViewPropertyAnimator animate(View view)
    {
        Object obj = (ViewPropertyAnimator)a.get(view);
        if (obj == null)
        {
            int i = Integer.valueOf(android.os.Build.VERSION.SDK).intValue();
            if (i >= 14)
            {
                obj = new f(view);
            } else
            if (i >= 11)
            {
                obj = new b(view);
            } else
            {
                obj = new h(view);
            }
            a.put(view, obj);
        }
        return ((ViewPropertyAnimator) (obj));
    }

    public abstract ViewPropertyAnimator alpha(float f1);

    public abstract ViewPropertyAnimator alphaBy(float f1);

    public abstract void cancel();

    public abstract long getDuration();

    public abstract long getStartDelay();

    public abstract ViewPropertyAnimator rotation(float f1);

    public abstract ViewPropertyAnimator rotationBy(float f1);

    public abstract ViewPropertyAnimator rotationX(float f1);

    public abstract ViewPropertyAnimator rotationXBy(float f1);

    public abstract ViewPropertyAnimator rotationY(float f1);

    public abstract ViewPropertyAnimator rotationYBy(float f1);

    public abstract ViewPropertyAnimator scaleX(float f1);

    public abstract ViewPropertyAnimator scaleXBy(float f1);

    public abstract ViewPropertyAnimator scaleY(float f1);

    public abstract ViewPropertyAnimator scaleYBy(float f1);

    public abstract ViewPropertyAnimator setDuration(long l);

    public abstract ViewPropertyAnimator setInterpolator(Interpolator interpolator);

    public abstract ViewPropertyAnimator setListener(com.nineoldandroids.animation.Animator.AnimatorListener animatorlistener);

    public abstract ViewPropertyAnimator setStartDelay(long l);

    public abstract void start();

    public abstract ViewPropertyAnimator translationX(float f1);

    public abstract ViewPropertyAnimator translationXBy(float f1);

    public abstract ViewPropertyAnimator translationY(float f1);

    public abstract ViewPropertyAnimator translationYBy(float f1);

    public abstract ViewPropertyAnimator x(float f1);

    public abstract ViewPropertyAnimator xBy(float f1);

    public abstract ViewPropertyAnimator y(float f1);

    public abstract ViewPropertyAnimator yBy(float f1);

}
