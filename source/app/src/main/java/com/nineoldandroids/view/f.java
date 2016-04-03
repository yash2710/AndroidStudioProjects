// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.nineoldandroids.view;

import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.Interpolator;
import java.lang.ref.WeakReference;

// Referenced classes of package com.nineoldandroids.view:
//            ViewPropertyAnimator, g

final class f extends com.nineoldandroids.view.ViewPropertyAnimator
{

    private final WeakReference a;

    f(View view)
    {
        a = new WeakReference(view.animate());
    }

    public final com.nineoldandroids.view.ViewPropertyAnimator alpha(float f1)
    {
        ViewPropertyAnimator viewpropertyanimator = (ViewPropertyAnimator)a.get();
        if (viewpropertyanimator != null)
        {
            viewpropertyanimator.alpha(f1);
        }
        return this;
    }

    public final com.nineoldandroids.view.ViewPropertyAnimator alphaBy(float f1)
    {
        ViewPropertyAnimator viewpropertyanimator = (ViewPropertyAnimator)a.get();
        if (viewpropertyanimator != null)
        {
            viewpropertyanimator.alphaBy(f1);
        }
        return this;
    }

    public final void cancel()
    {
        ViewPropertyAnimator viewpropertyanimator = (ViewPropertyAnimator)a.get();
        if (viewpropertyanimator != null)
        {
            viewpropertyanimator.cancel();
        }
    }

    public final long getDuration()
    {
        ViewPropertyAnimator viewpropertyanimator = (ViewPropertyAnimator)a.get();
        if (viewpropertyanimator != null)
        {
            return viewpropertyanimator.getDuration();
        } else
        {
            return -1L;
        }
    }

    public final long getStartDelay()
    {
        ViewPropertyAnimator viewpropertyanimator = (ViewPropertyAnimator)a.get();
        if (viewpropertyanimator != null)
        {
            return viewpropertyanimator.getStartDelay();
        } else
        {
            return -1L;
        }
    }

    public final com.nineoldandroids.view.ViewPropertyAnimator rotation(float f1)
    {
        ViewPropertyAnimator viewpropertyanimator = (ViewPropertyAnimator)a.get();
        if (viewpropertyanimator != null)
        {
            viewpropertyanimator.rotation(f1);
        }
        return this;
    }

    public final com.nineoldandroids.view.ViewPropertyAnimator rotationBy(float f1)
    {
        ViewPropertyAnimator viewpropertyanimator = (ViewPropertyAnimator)a.get();
        if (viewpropertyanimator != null)
        {
            viewpropertyanimator.rotationBy(f1);
        }
        return this;
    }

    public final com.nineoldandroids.view.ViewPropertyAnimator rotationX(float f1)
    {
        ViewPropertyAnimator viewpropertyanimator = (ViewPropertyAnimator)a.get();
        if (viewpropertyanimator != null)
        {
            viewpropertyanimator.rotationX(f1);
        }
        return this;
    }

    public final com.nineoldandroids.view.ViewPropertyAnimator rotationXBy(float f1)
    {
        ViewPropertyAnimator viewpropertyanimator = (ViewPropertyAnimator)a.get();
        if (viewpropertyanimator != null)
        {
            viewpropertyanimator.rotationXBy(f1);
        }
        return this;
    }

    public final com.nineoldandroids.view.ViewPropertyAnimator rotationY(float f1)
    {
        ViewPropertyAnimator viewpropertyanimator = (ViewPropertyAnimator)a.get();
        if (viewpropertyanimator != null)
        {
            viewpropertyanimator.rotationY(f1);
        }
        return this;
    }

    public final com.nineoldandroids.view.ViewPropertyAnimator rotationYBy(float f1)
    {
        ViewPropertyAnimator viewpropertyanimator = (ViewPropertyAnimator)a.get();
        if (viewpropertyanimator != null)
        {
            viewpropertyanimator.rotationYBy(f1);
        }
        return this;
    }

    public final com.nineoldandroids.view.ViewPropertyAnimator scaleX(float f1)
    {
        ViewPropertyAnimator viewpropertyanimator = (ViewPropertyAnimator)a.get();
        if (viewpropertyanimator != null)
        {
            viewpropertyanimator.scaleX(f1);
        }
        return this;
    }

    public final com.nineoldandroids.view.ViewPropertyAnimator scaleXBy(float f1)
    {
        ViewPropertyAnimator viewpropertyanimator = (ViewPropertyAnimator)a.get();
        if (viewpropertyanimator != null)
        {
            viewpropertyanimator.scaleXBy(f1);
        }
        return this;
    }

    public final com.nineoldandroids.view.ViewPropertyAnimator scaleY(float f1)
    {
        ViewPropertyAnimator viewpropertyanimator = (ViewPropertyAnimator)a.get();
        if (viewpropertyanimator != null)
        {
            viewpropertyanimator.scaleY(f1);
        }
        return this;
    }

    public final com.nineoldandroids.view.ViewPropertyAnimator scaleYBy(float f1)
    {
        ViewPropertyAnimator viewpropertyanimator = (ViewPropertyAnimator)a.get();
        if (viewpropertyanimator != null)
        {
            viewpropertyanimator.scaleYBy(f1);
        }
        return this;
    }

    public final com.nineoldandroids.view.ViewPropertyAnimator setDuration(long l)
    {
        ViewPropertyAnimator viewpropertyanimator = (ViewPropertyAnimator)a.get();
        if (viewpropertyanimator != null)
        {
            viewpropertyanimator.setDuration(l);
        }
        return this;
    }

    public final com.nineoldandroids.view.ViewPropertyAnimator setInterpolator(Interpolator interpolator)
    {
        ViewPropertyAnimator viewpropertyanimator = (ViewPropertyAnimator)a.get();
        if (viewpropertyanimator != null)
        {
            viewpropertyanimator.setInterpolator(interpolator);
        }
        return this;
    }

    public final com.nineoldandroids.view.ViewPropertyAnimator setListener(com.nineoldandroids.animation.Animator.AnimatorListener animatorlistener)
    {
        ViewPropertyAnimator viewpropertyanimator;
label0:
        {
            viewpropertyanimator = (ViewPropertyAnimator)a.get();
            if (viewpropertyanimator != null)
            {
                if (animatorlistener != null)
                {
                    break label0;
                }
                viewpropertyanimator.setListener(null);
            }
            return this;
        }
        viewpropertyanimator.setListener(new g(this, animatorlistener));
        return this;
    }

    public final com.nineoldandroids.view.ViewPropertyAnimator setStartDelay(long l)
    {
        ViewPropertyAnimator viewpropertyanimator = (ViewPropertyAnimator)a.get();
        if (viewpropertyanimator != null)
        {
            viewpropertyanimator.setStartDelay(l);
        }
        return this;
    }

    public final void start()
    {
        ViewPropertyAnimator viewpropertyanimator = (ViewPropertyAnimator)a.get();
        if (viewpropertyanimator != null)
        {
            viewpropertyanimator.start();
        }
    }

    public final com.nineoldandroids.view.ViewPropertyAnimator translationX(float f1)
    {
        ViewPropertyAnimator viewpropertyanimator = (ViewPropertyAnimator)a.get();
        if (viewpropertyanimator != null)
        {
            viewpropertyanimator.translationX(f1);
        }
        return this;
    }

    public final com.nineoldandroids.view.ViewPropertyAnimator translationXBy(float f1)
    {
        ViewPropertyAnimator viewpropertyanimator = (ViewPropertyAnimator)a.get();
        if (viewpropertyanimator != null)
        {
            viewpropertyanimator.translationXBy(f1);
        }
        return this;
    }

    public final com.nineoldandroids.view.ViewPropertyAnimator translationY(float f1)
    {
        ViewPropertyAnimator viewpropertyanimator = (ViewPropertyAnimator)a.get();
        if (viewpropertyanimator != null)
        {
            viewpropertyanimator.translationY(f1);
        }
        return this;
    }

    public final com.nineoldandroids.view.ViewPropertyAnimator translationYBy(float f1)
    {
        ViewPropertyAnimator viewpropertyanimator = (ViewPropertyAnimator)a.get();
        if (viewpropertyanimator != null)
        {
            viewpropertyanimator.translationYBy(f1);
        }
        return this;
    }

    public final com.nineoldandroids.view.ViewPropertyAnimator x(float f1)
    {
        ViewPropertyAnimator viewpropertyanimator = (ViewPropertyAnimator)a.get();
        if (viewpropertyanimator != null)
        {
            viewpropertyanimator.x(f1);
        }
        return this;
    }

    public final com.nineoldandroids.view.ViewPropertyAnimator xBy(float f1)
    {
        ViewPropertyAnimator viewpropertyanimator = (ViewPropertyAnimator)a.get();
        if (viewpropertyanimator != null)
        {
            viewpropertyanimator.xBy(f1);
        }
        return this;
    }

    public final com.nineoldandroids.view.ViewPropertyAnimator y(float f1)
    {
        ViewPropertyAnimator viewpropertyanimator = (ViewPropertyAnimator)a.get();
        if (viewpropertyanimator != null)
        {
            viewpropertyanimator.y(f1);
        }
        return this;
    }

    public final com.nineoldandroids.view.ViewPropertyAnimator yBy(float f1)
    {
        ViewPropertyAnimator viewpropertyanimator = (ViewPropertyAnimator)a.get();
        if (viewpropertyanimator != null)
        {
            viewpropertyanimator.yBy(f1);
        }
        return this;
    }
}
