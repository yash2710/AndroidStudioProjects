// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.nineoldandroids.view;

import android.view.View;
import android.view.animation.Interpolator;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.ValueAnimator;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

// Referenced classes of package com.nineoldandroids.view:
//            ViewPropertyAnimator, d, c, a, 
//            e

final class b extends ViewPropertyAnimator
{

    private final WeakReference a;
    private long b;
    private boolean c;
    private long d;
    private boolean e;
    private Interpolator f;
    private boolean g;
    private com.nineoldandroids.animation.Animator.AnimatorListener h;
    private d i;
    private ArrayList j;
    private Runnable k;
    private HashMap l;

    b(View view)
    {
        c = false;
        d = 0L;
        e = false;
        g = false;
        h = null;
        i = new d(this, (byte)0);
        j = new ArrayList();
        k = new c(this);
        l = new HashMap();
        a = new WeakReference(view);
    }

    private float a(int i1)
    {
        View view = (View)a.get();
        if (view == null) goto _L2; else goto _L1
_L1:
        i1;
        JVM INSTR lookupswitch 10: default 108
    //                   1: 110
    //                   2: 115
    //                   4: 135
    //                   8: 140
    //                   16: 120
    //                   32: 125
    //                   64: 130
    //                   128: 145
    //                   256: 150
    //                   512: 155;
           goto _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12
_L2:
        return 0.0F;
_L3:
        return view.getTranslationX();
_L4:
        return view.getTranslationY();
_L7:
        return view.getRotation();
_L8:
        return view.getRotationX();
_L9:
        return view.getRotationY();
_L5:
        return view.getScaleX();
_L6:
        return view.getScaleY();
_L10:
        return view.getX();
_L11:
        return view.getY();
_L12:
        return view.getAlpha();
    }

    static com.nineoldandroids.animation.Animator.AnimatorListener a(b b1, com.nineoldandroids.animation.Animator.AnimatorListener animatorlistener)
    {
        b1.h = null;
        return null;
    }

    private void a()
    {
        ValueAnimator valueanimator = ValueAnimator.ofFloat(new float[] {
            1.0F
        });
        ArrayList arraylist = (ArrayList)j.clone();
        j.clear();
        int i1 = arraylist.size();
        int j1 = 0;
        int k1 = 0;
        for (; j1 < i1; j1++)
        {
            k1 |= ((a)arraylist.get(j1)).a;
        }

        l.put(valueanimator, new e(k1, arraylist));
        valueanimator.addUpdateListener(i);
        valueanimator.addListener(i);
        if (e)
        {
            valueanimator.setStartDelay(d);
        }
        if (c)
        {
            valueanimator.setDuration(b);
        }
        if (g)
        {
            valueanimator.setInterpolator(f);
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
        if (l.size() <= 0) goto _L2; else goto _L1
_L1:
        Iterator iterator = l.keySet().iterator();
_L9:
        Animator animator;
        e e1;
        if (!iterator.hasNext())
        {
            break MISSING_BLOCK_LABEL_241;
        }
        animator = (Animator)iterator.next();
        e1 = (e)l.get(animator);
        if ((i1 & e1.a) == 0 || e1.b == null) goto _L4; else goto _L3
_L3:
        int j1;
        int k1;
        j1 = e1.b.size();
        k1 = 0;
_L10:
        if (k1 >= j1) goto _L4; else goto _L5
_L5:
        if (((a)e1.b.get(k1)).a != i1) goto _L7; else goto _L6
_L6:
        boolean flag;
        e1.b.remove(k1);
        e1.a = e1.a & ~i1;
        flag = true;
_L11:
        if (!flag || e1.a != 0) goto _L9; else goto _L8
_L8:
        if (animator != null)
        {
            animator.cancel();
        }
_L2:
        a a1 = new a(i1, f1, f2);
        j.add(a1);
        View view = (View)a.get();
        if (view != null)
        {
            view.removeCallbacks(k);
            view.post(k);
        }
        return;
_L7:
        k1++;
          goto _L10
_L4:
        flag = false;
          goto _L11
        animator = null;
          goto _L8
    }

    static void a(b b1)
    {
        b1.a();
    }

    static void a(b b1, int i1, float f1)
    {
        View view = (View)b1.a.get();
        if (view == null) goto _L2; else goto _L1
_L1:
        i1;
        JVM INSTR lookupswitch 10: default 108
    //                   1: 109
    //                   2: 115
    //                   4: 139
    //                   8: 145
    //                   16: 121
    //                   32: 127
    //                   64: 133
    //                   128: 151
    //                   256: 157
    //                   512: 163;
           goto _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12
_L2:
        return;
_L3:
        view.setTranslationX(f1);
        return;
_L4:
        view.setTranslationY(f1);
        return;
_L7:
        view.setRotation(f1);
        return;
_L8:
        view.setRotationX(f1);
        return;
_L9:
        view.setRotationY(f1);
        return;
_L5:
        view.setScaleX(f1);
        return;
_L6:
        view.setScaleY(f1);
        return;
_L10:
        view.setX(f1);
        return;
_L11:
        view.setY(f1);
        return;
_L12:
        view.setAlpha(f1);
        return;
    }

    static com.nineoldandroids.animation.Animator.AnimatorListener b(b b1)
    {
        return b1.h;
    }

    private void b(int i1, float f1)
    {
        a(i1, a(i1), f1);
    }

    static HashMap c(b b1)
    {
        return b1.l;
    }

    static WeakReference d(b b1)
    {
        return b1.a;
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
        if (l.size() > 0)
        {
            for (Iterator iterator = ((HashMap)l.clone()).keySet().iterator(); iterator.hasNext(); ((Animator)iterator.next()).cancel()) { }
        }
        j.clear();
        View view = (View)a.get();
        if (view != null)
        {
            view.removeCallbacks(k);
        }
    }

    public final long getDuration()
    {
        if (c)
        {
            return b;
        } else
        {
            return (new ValueAnimator()).getDuration();
        }
    }

    public final long getStartDelay()
    {
        if (e)
        {
            return d;
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
            c = true;
            b = l1;
            return this;
        }
    }

    public final ViewPropertyAnimator setInterpolator(Interpolator interpolator)
    {
        g = true;
        f = interpolator;
        return this;
    }

    public final ViewPropertyAnimator setListener(com.nineoldandroids.animation.Animator.AnimatorListener animatorlistener)
    {
        h = animatorlistener;
        return this;
    }

    public final ViewPropertyAnimator setStartDelay(long l1)
    {
        if (l1 < 0L)
        {
            throw new IllegalArgumentException((new StringBuilder("Animators cannot have negative duration: ")).append(l1).toString());
        } else
        {
            e = true;
            d = l1;
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
