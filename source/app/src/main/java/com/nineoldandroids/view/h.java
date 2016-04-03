// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.nineoldandroids.view;

import android.view.View;
import android.view.animation.Interpolator;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.ValueAnimator;
import com.nineoldandroids.view.animation.AnimatorProxy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

// Referenced classes of package com.nineoldandroids.view:
//            ViewPropertyAnimator, j, i, k, 
//            l

final class h extends ViewPropertyAnimator
{

    private final AnimatorProxy a;
    private final WeakReference b;
    private long c;
    private boolean d;
    private long e;
    private boolean f;
    private Interpolator g;
    private boolean h;
    private com.nineoldandroids.animation.Animator.AnimatorListener i;
    private j j;
    private ArrayList k;
    private Runnable l;
    private HashMap m;

    h(View view)
    {
        d = false;
        e = 0L;
        f = false;
        h = false;
        i = null;
        j = new j(this, (byte)0);
        k = new ArrayList();
        l = new i(this);
        m = new HashMap();
        b = new WeakReference(view);
        a = AnimatorProxy.wrap(view);
    }

    private float a(int i1)
    {
        switch (i1)
        {
        default:
            return 0.0F;

        case 1: // '\001'
            return a.getTranslationX();

        case 2: // '\002'
            return a.getTranslationY();

        case 16: // '\020'
            return a.getRotation();

        case 32: // ' '
            return a.getRotationX();

        case 64: // '@'
            return a.getRotationY();

        case 4: // '\004'
            return a.getScaleX();

        case 8: // '\b'
            return a.getScaleY();

        case 128: 
            return a.getX();

        case 256: 
            return a.getY();

        case 512: 
            return a.getAlpha();
        }
    }

    static com.nineoldandroids.animation.Animator.AnimatorListener a(h h1, com.nineoldandroids.animation.Animator.AnimatorListener animatorlistener)
    {
        h1.i = null;
        return null;
    }

    private void a()
    {
        ValueAnimator valueanimator = ValueAnimator.ofFloat(new float[] {
            1.0F
        });
        ArrayList arraylist = (ArrayList)k.clone();
        k.clear();
        int i1 = arraylist.size();
        int j1 = 0;
        int k1 = 0;
        for (; j1 < i1; j1++)
        {
            k1 |= ((k)arraylist.get(j1)).a;
        }

        m.put(valueanimator, new l(k1, arraylist));
        valueanimator.addUpdateListener(j);
        valueanimator.addListener(j);
        if (f)
        {
            valueanimator.setStartDelay(e);
        }
        if (d)
        {
            valueanimator.setDuration(c);
        }
        if (h)
        {
            valueanimator.setInterpolator(g);
        }
        valueanimator.start();
    }

    private void a(int i1, float f1)
    {
        float f2 = a(i1);
        a(i1, f2, f1 - f2);
    }

    private void a(int i1, float f1, float f2)
    {
        if (m.size() <= 0) goto _L2; else goto _L1
_L1:
        Iterator iterator = m.keySet().iterator();
_L9:
        Animator animator;
        l l1;
        if (!iterator.hasNext())
        {
            break MISSING_BLOCK_LABEL_241;
        }
        animator = (Animator)iterator.next();
        l1 = (l)m.get(animator);
        if ((i1 & l1.a) == 0 || l1.b == null) goto _L4; else goto _L3
_L3:
        int j1;
        int i2;
        j1 = l1.b.size();
        i2 = 0;
_L10:
        if (i2 >= j1) goto _L4; else goto _L5
_L5:
        if (((k)l1.b.get(i2)).a != i1) goto _L7; else goto _L6
_L6:
        boolean flag;
        l1.b.remove(i2);
        l1.a = l1.a & ~i1;
        flag = true;
_L11:
        if (!flag || l1.a != 0) goto _L9; else goto _L8
_L8:
        if (animator != null)
        {
            animator.cancel();
        }
_L2:
        k k1 = new k(i1, f1, f2);
        k.add(k1);
        View view = (View)b.get();
        if (view != null)
        {
            view.removeCallbacks(l);
            view.post(l);
        }
        return;
_L7:
        i2++;
          goto _L10
_L4:
        flag = false;
          goto _L11
        animator = null;
          goto _L8
    }

    static void a(h h1)
    {
        h1.a();
    }

    static void a(h h1, int i1, float f1)
    {
        switch (i1)
        {
        default:
            return;

        case 1: // '\001'
            h1.a.setTranslationX(f1);
            return;

        case 2: // '\002'
            h1.a.setTranslationY(f1);
            return;

        case 16: // '\020'
            h1.a.setRotation(f1);
            return;

        case 32: // ' '
            h1.a.setRotationX(f1);
            return;

        case 64: // '@'
            h1.a.setRotationY(f1);
            return;

        case 4: // '\004'
            h1.a.setScaleX(f1);
            return;

        case 8: // '\b'
            h1.a.setScaleY(f1);
            return;

        case 128: 
            h1.a.setX(f1);
            return;

        case 256: 
            h1.a.setY(f1);
            return;

        case 512: 
            h1.a.setAlpha(f1);
            break;
        }
    }

    static com.nineoldandroids.animation.Animator.AnimatorListener b(h h1)
    {
        return h1.i;
    }

    private void b(int i1, float f1)
    {
        a(i1, a(i1), f1);
    }

    static HashMap c(h h1)
    {
        return h1.m;
    }

    static WeakReference d(h h1)
    {
        return h1.b;
    }

    public final ViewPropertyAnimator alpha(float f1)
    {
        a(512, f1);
        return this;
    }

    public final ViewPropertyAnimator alphaBy(float f1)
    {
        b(512, f1);
        return this;
    }

    public final void cancel()
    {
        if (m.size() > 0)
        {
            for (Iterator iterator = ((HashMap)m.clone()).keySet().iterator(); iterator.hasNext(); ((Animator)iterator.next()).cancel()) { }
        }
        k.clear();
        View view = (View)b.get();
        if (view != null)
        {
            view.removeCallbacks(l);
        }
    }

    public final long getDuration()
    {
        if (d)
        {
            return c;
        } else
        {
            return (new ValueAnimator()).getDuration();
        }
    }

    public final long getStartDelay()
    {
        if (f)
        {
            return e;
        } else
        {
            return 0L;
        }
    }

    public final ViewPropertyAnimator rotation(float f1)
    {
        a(16, f1);
        return this;
    }

    public final ViewPropertyAnimator rotationBy(float f1)
    {
        b(16, f1);
        return this;
    }

    public final ViewPropertyAnimator rotationX(float f1)
    {
        a(32, f1);
        return this;
    }

    public final ViewPropertyAnimator rotationXBy(float f1)
    {
        b(32, f1);
        return this;
    }

    public final ViewPropertyAnimator rotationY(float f1)
    {
        a(64, f1);
        return this;
    }

    public final ViewPropertyAnimator rotationYBy(float f1)
    {
        b(64, f1);
        return this;
    }

    public final ViewPropertyAnimator scaleX(float f1)
    {
        a(4, f1);
        return this;
    }

    public final ViewPropertyAnimator scaleXBy(float f1)
    {
        b(4, f1);
        return this;
    }

    public final ViewPropertyAnimator scaleY(float f1)
    {
        a(8, f1);
        return this;
    }

    public final ViewPropertyAnimator scaleYBy(float f1)
    {
        b(8, f1);
        return this;
    }

    public final ViewPropertyAnimator setDuration(long l1)
    {
        if (l1 < 0L)
        {
            throw new IllegalArgumentException((new StringBuilder("Animators cannot have negative duration: ")).append(l1).toString());
        } else
        {
            d = true;
            c = l1;
            return this;
        }
    }

    public final ViewPropertyAnimator setInterpolator(Interpolator interpolator)
    {
        h = true;
        g = interpolator;
        return this;
    }

    public final ViewPropertyAnimator setListener(com.nineoldandroids.animation.Animator.AnimatorListener animatorlistener)
    {
        i = animatorlistener;
        return this;
    }

    public final ViewPropertyAnimator setStartDelay(long l1)
    {
        if (l1 < 0L)
        {
            throw new IllegalArgumentException((new StringBuilder("Animators cannot have negative duration: ")).append(l1).toString());
        } else
        {
            f = true;
            e = l1;
            return this;
        }
    }

    public final void start()
    {
        a();
    }

    public final ViewPropertyAnimator translationX(float f1)
    {
        a(1, f1);
        return this;
    }

    public final ViewPropertyAnimator translationXBy(float f1)
    {
        b(1, f1);
        return this;
    }

    public final ViewPropertyAnimator translationY(float f1)
    {
        a(2, f1);
        return this;
    }

    public final ViewPropertyAnimator translationYBy(float f1)
    {
        b(2, f1);
        return this;
    }

    public final ViewPropertyAnimator x(float f1)
    {
        a(128, f1);
        return this;
    }

    public final ViewPropertyAnimator xBy(float f1)
    {
        b(128, f1);
        return this;
    }

    public final ViewPropertyAnimator y(float f1)
    {
        a(256, f1);
        return this;
    }

    public final ViewPropertyAnimator yBy(float f1)
    {
        b(256, f1);
        return this;
    }
}
