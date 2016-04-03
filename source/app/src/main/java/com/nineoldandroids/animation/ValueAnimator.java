// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.nineoldandroids.animation;

import android.os.Looper;
import android.util.AndroidRuntimeException;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

// Referenced classes of package com.nineoldandroids.animation:
//            Animator, C, D, E, 
//            F, G, IntEvaluator, FloatEvaluator, 
//            H, PropertyValuesHolder, TypeEvaluator

public class ValueAnimator extends Animator
{

    public static final int INFINITE = -1;
    public static final int RESTART = 1;
    public static final int REVERSE = 2;
    private static ThreadLocal h = new ThreadLocal();
    private static final ThreadLocal i = new C();
    private static final ThreadLocal j = new D();
    private static final ThreadLocal k = new E();
    private static final ThreadLocal l = new F();
    private static final ThreadLocal m = new G();
    private static final Interpolator n = new AccelerateDecelerateInterpolator();
    private static long x = 10L;
    private Interpolator A;
    private ArrayList B;
    long b;
    long c;
    int d;
    boolean e;
    PropertyValuesHolder f[];
    HashMap g;
    private boolean o;
    private int p;
    private float q;
    private boolean r;
    private long s;
    private boolean t;
    private boolean u;
    private long v;
    private long w;
    private int y;
    private int z;

    public ValueAnimator()
    {
        c = -1L;
        o = false;
        p = 0;
        q = 0.0F;
        r = false;
        d = 0;
        t = false;
        u = false;
        e = false;
        v = 300L;
        w = 0L;
        y = 0;
        z = 1;
        A = n;
        B = null;
    }

    static long a(ValueAnimator valueanimator)
    {
        return valueanimator.w;
    }

    private void a(boolean flag)
    {
        if (Looper.myLooper() == null)
        {
            throw new AndroidRuntimeException("Animators may only be run on Looper threads");
        }
        o = flag;
        p = 0;
        d = 0;
        u = true;
        r = false;
        ((ArrayList)j.get()).add(this);
        if (w == 0L)
        {
            setCurrentPlayTime(getCurrentPlayTime());
            d = 0;
            t = true;
            if (a != null)
            {
                ArrayList arraylist = (ArrayList)a.clone();
                int i1 = arraylist.size();
                for (int j1 = 0; j1 < i1; j1++)
                {
                    ((Animator.AnimatorListener)arraylist.get(j1)).onAnimationStart(this);
                }

            }
        }
        H h1 = (H)h.get();
        if (h1 == null)
        {
            h1 = new H((byte)0);
            h.set(h1);
        }
        h1.sendEmptyMessage(0);
    }

    static boolean a(ValueAnimator valueanimator, long l1)
    {
        if (!valueanimator.r)
        {
            valueanimator.r = true;
            valueanimator.s = l1;
        } else
        {
            long l2 = l1 - valueanimator.s;
            if (l2 > valueanimator.w)
            {
                valueanimator.b = l1 - (l2 - valueanimator.w);
                valueanimator.d = 1;
                return true;
            }
        }
        return false;
    }

    static boolean a(ValueAnimator valueanimator, boolean flag)
    {
        valueanimator.t = true;
        return true;
    }

    static ThreadLocal b()
    {
        return i;
    }

    static void b(ValueAnimator valueanimator)
    {
        valueanimator.i();
    }

    static ThreadLocal c()
    {
        return k;
    }

    static void c(ValueAnimator valueanimator)
    {
        valueanimator.h();
    }

    public static void clearAllAnimations()
    {
        ((ArrayList)i.get()).clear();
        ((ArrayList)j.get()).clear();
        ((ArrayList)k.get()).clear();
    }

    static ThreadLocal d()
    {
        return j;
    }

    static ThreadLocal e()
    {
        return m;
    }

    static ThreadLocal f()
    {
        return l;
    }

    static long g()
    {
        return x;
    }

    public static int getCurrentAnimationsCount()
    {
        return ((ArrayList)i.get()).size();
    }

    public static long getFrameDelay()
    {
        return x;
    }

    private void h()
    {
        ((ArrayList)i.get()).remove(this);
        ((ArrayList)j.get()).remove(this);
        ((ArrayList)k.get()).remove(this);
        d = 0;
        if (t && a != null)
        {
            ArrayList arraylist = (ArrayList)a.clone();
            int i1 = arraylist.size();
            for (int j1 = 0; j1 < i1; j1++)
            {
                ((Animator.AnimatorListener)arraylist.get(j1)).onAnimationEnd(this);
            }

        }
        t = false;
        u = false;
    }

    private void i()
    {
        a();
        ((ArrayList)i.get()).add(this);
        if (w > 0L && a != null)
        {
            ArrayList arraylist = (ArrayList)a.clone();
            int i1 = arraylist.size();
            for (int j1 = 0; j1 < i1; j1++)
            {
                ((Animator.AnimatorListener)arraylist.get(j1)).onAnimationStart(this);
            }

        }
    }

    public static transient ValueAnimator ofFloat(float af[])
    {
        ValueAnimator valueanimator = new ValueAnimator();
        valueanimator.setFloatValues(af);
        return valueanimator;
    }

    public static transient ValueAnimator ofInt(int ai[])
    {
        ValueAnimator valueanimator = new ValueAnimator();
        valueanimator.setIntValues(ai);
        return valueanimator;
    }

    public static transient ValueAnimator ofObject(TypeEvaluator typeevaluator, Object aobj[])
    {
        ValueAnimator valueanimator = new ValueAnimator();
        valueanimator.setObjectValues(aobj);
        valueanimator.setEvaluator(typeevaluator);
        return valueanimator;
    }

    public static transient ValueAnimator ofPropertyValuesHolder(PropertyValuesHolder apropertyvaluesholder[])
    {
        ValueAnimator valueanimator = new ValueAnimator();
        valueanimator.setValues(apropertyvaluesholder);
        return valueanimator;
    }

    public static void setFrameDelay(long l1)
    {
        x = l1;
    }

    void a()
    {
        if (!e)
        {
            int i1 = f.length;
            for (int j1 = 0; j1 < i1; j1++)
            {
                f[j1].a();
            }

            e = true;
        }
    }

    void a(float f1)
    {
        float f2 = A.getInterpolation(f1);
        q = f2;
        int i1 = f.length;
        for (int j1 = 0; j1 < i1; j1++)
        {
            f[j1].a(f2);
        }

        if (B != null)
        {
            int k1 = B.size();
            for (int l1 = 0; l1 < k1; l1++)
            {
                ((AnimatorUpdateListener)B.get(l1)).onAnimationUpdate(this);
            }

        }
    }

    boolean a(long l1)
    {
        boolean flag = false;
        if (d == 0)
        {
            d = 1;
            if (c < 0L)
            {
                b = l1;
            } else
            {
                b = l1 - c;
                c = -1L;
            }
        }
        switch (d)
        {
        default:
            return false;

        case 1: // '\001'
        case 2: // '\002'
            break;
        }
        float f1;
        if (v > 0L)
        {
            f1 = (float)(l1 - b) / (float)v;
        } else
        {
            f1 = 1.0F;
        }
        float f2;
        if (f1 >= 1.0F)
        {
            if (p < y || y == -1)
            {
                if (a != null)
                {
                    int i1 = a.size();
                    for (int j1 = 0; j1 < i1; j1++)
                    {
                        ((Animator.AnimatorListener)a.get(j1)).onAnimationRepeat(this);
                    }

                }
                if (z == 2)
                {
                    boolean flag1;
                    if (o)
                    {
                        flag1 = false;
                    } else
                    {
                        flag1 = true;
                    }
                    o = flag1;
                }
                p = p + (int)f1;
                f2 = f1 % 1.0F;
                b = b + v;
            } else
            {
                f2 = Math.min(f1, 1.0F);
                flag = true;
            }
        } else
        {
            f2 = f1;
            flag = false;
        }
        if (o)
        {
            f2 = 1.0F - f2;
        }
        a(f2);
        return flag;
    }

    public void addUpdateListener(AnimatorUpdateListener animatorupdatelistener)
    {
        if (B == null)
        {
            B = new ArrayList();
        }
        B.add(animatorupdatelistener);
    }

    public void cancel()
    {
        if (d != 0 || ((ArrayList)j.get()).contains(this) || ((ArrayList)k.get()).contains(this))
        {
            if (t && a != null)
            {
                for (Iterator iterator = ((ArrayList)a.clone()).iterator(); iterator.hasNext(); ((Animator.AnimatorListener)iterator.next()).onAnimationCancel(this)) { }
            }
            h();
        }
    }

    public volatile Animator clone()
    {
        return clone();
    }

    public ValueAnimator clone()
    {
        int i1 = 0;
        ValueAnimator valueanimator = (ValueAnimator)super.clone();
        if (B != null)
        {
            ArrayList arraylist = B;
            valueanimator.B = new ArrayList();
            int k1 = arraylist.size();
            for (int l1 = 0; l1 < k1; l1++)
            {
                valueanimator.B.add(arraylist.get(l1));
            }

        }
        valueanimator.c = -1L;
        valueanimator.o = false;
        valueanimator.p = 0;
        valueanimator.e = false;
        valueanimator.d = 0;
        valueanimator.r = false;
        PropertyValuesHolder apropertyvaluesholder[] = f;
        if (apropertyvaluesholder != null)
        {
            int j1 = apropertyvaluesholder.length;
            valueanimator.f = new PropertyValuesHolder[j1];
            valueanimator.g = new HashMap(j1);
            for (; i1 < j1; i1++)
            {
                PropertyValuesHolder propertyvaluesholder = apropertyvaluesholder[i1].clone();
                valueanimator.f[i1] = propertyvaluesholder;
                valueanimator.g.put(propertyvaluesholder.getPropertyName(), propertyvaluesholder);
            }

        }
        return valueanimator;
    }

    public volatile Object clone()
    {
        return clone();
    }

    public void end()
    {
        if (!((ArrayList)i.get()).contains(this) && !((ArrayList)j.get()).contains(this))
        {
            r = false;
            i();
        } else
        if (!e)
        {
            a();
        }
        if (y > 0 && (1 & y) == 1)
        {
            a(0.0F);
        } else
        {
            a(1.0F);
        }
        h();
    }

    public float getAnimatedFraction()
    {
        return q;
    }

    public Object getAnimatedValue()
    {
        if (f != null && f.length > 0)
        {
            return f[0].b();
        } else
        {
            return null;
        }
    }

    public Object getAnimatedValue(String s1)
    {
        PropertyValuesHolder propertyvaluesholder = (PropertyValuesHolder)g.get(s1);
        if (propertyvaluesholder != null)
        {
            return propertyvaluesholder.b();
        } else
        {
            return null;
        }
    }

    public long getCurrentPlayTime()
    {
        if (!e || d == 0)
        {
            return 0L;
        } else
        {
            return AnimationUtils.currentAnimationTimeMillis() - b;
        }
    }

    public long getDuration()
    {
        return v;
    }

    public Interpolator getInterpolator()
    {
        return A;
    }

    public int getRepeatCount()
    {
        return y;
    }

    public int getRepeatMode()
    {
        return z;
    }

    public long getStartDelay()
    {
        return w;
    }

    public PropertyValuesHolder[] getValues()
    {
        return f;
    }

    public boolean isRunning()
    {
        return d == 1 || t;
    }

    public boolean isStarted()
    {
        return u;
    }

    public void removeAllUpdateListeners()
    {
        if (B == null)
        {
            return;
        } else
        {
            B.clear();
            B = null;
            return;
        }
    }

    public void removeUpdateListener(AnimatorUpdateListener animatorupdatelistener)
    {
        if (B != null)
        {
            B.remove(animatorupdatelistener);
            if (B.size() == 0)
            {
                B = null;
                return;
            }
        }
    }

    public void reverse()
    {
        boolean flag;
        if (!o)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        o = flag;
        if (d == 1)
        {
            long l1 = AnimationUtils.currentAnimationTimeMillis();
            long l2 = l1 - b;
            b = l1 - (v - l2);
            return;
        } else
        {
            a(true);
            return;
        }
    }

    public void setCurrentPlayTime(long l1)
    {
        a();
        long l2 = AnimationUtils.currentAnimationTimeMillis();
        if (d != 1)
        {
            c = l1;
            d = 2;
        }
        b = l2 - l1;
        a(l2);
    }

    public volatile Animator setDuration(long l1)
    {
        return setDuration(l1);
    }

    public ValueAnimator setDuration(long l1)
    {
        if (l1 < 0L)
        {
            throw new IllegalArgumentException((new StringBuilder("Animators cannot have negative duration: ")).append(l1).toString());
        } else
        {
            v = l1;
            return this;
        }
    }

    public void setEvaluator(TypeEvaluator typeevaluator)
    {
        if (typeevaluator != null && f != null && f.length > 0)
        {
            f[0].setEvaluator(typeevaluator);
        }
    }

    public transient void setFloatValues(float af[])
    {
        if (af == null || af.length == 0)
        {
            return;
        }
        if (f == null || f.length == 0)
        {
            PropertyValuesHolder apropertyvaluesholder[] = new PropertyValuesHolder[1];
            apropertyvaluesholder[0] = PropertyValuesHolder.ofFloat("", af);
            setValues(apropertyvaluesholder);
        } else
        {
            f[0].setFloatValues(af);
        }
        e = false;
    }

    public transient void setIntValues(int ai[])
    {
        if (ai == null || ai.length == 0)
        {
            return;
        }
        if (f == null || f.length == 0)
        {
            PropertyValuesHolder apropertyvaluesholder[] = new PropertyValuesHolder[1];
            apropertyvaluesholder[0] = PropertyValuesHolder.ofInt("", ai);
            setValues(apropertyvaluesholder);
        } else
        {
            f[0].setIntValues(ai);
        }
        e = false;
    }

    public void setInterpolator(Interpolator interpolator)
    {
        if (interpolator != null)
        {
            A = interpolator;
            return;
        } else
        {
            A = new LinearInterpolator();
            return;
        }
    }

    public transient void setObjectValues(Object aobj[])
    {
        if (aobj == null || aobj.length == 0)
        {
            return;
        }
        if (f == null || f.length == 0)
        {
            PropertyValuesHolder apropertyvaluesholder[] = new PropertyValuesHolder[1];
            apropertyvaluesholder[0] = PropertyValuesHolder.ofObject("", null, aobj);
            setValues(apropertyvaluesholder);
        } else
        {
            f[0].setObjectValues(aobj);
        }
        e = false;
    }

    public void setRepeatCount(int i1)
    {
        y = i1;
    }

    public void setRepeatMode(int i1)
    {
        z = i1;
    }

    public void setStartDelay(long l1)
    {
        w = l1;
    }

    public transient void setValues(PropertyValuesHolder apropertyvaluesholder[])
    {
        int i1 = apropertyvaluesholder.length;
        f = apropertyvaluesholder;
        g = new HashMap(i1);
        for (int j1 = 0; j1 < i1; j1++)
        {
            PropertyValuesHolder propertyvaluesholder = apropertyvaluesholder[j1];
            g.put(propertyvaluesholder.getPropertyName(), propertyvaluesholder);
        }

        e = false;
    }

    public void start()
    {
        a(false);
    }

    public String toString()
    {
        String s1 = (new StringBuilder("ValueAnimator@")).append(Integer.toHexString(hashCode())).toString();
        if (f != null)
        {
            for (int i1 = 0; i1 < f.length; i1++)
            {
                s1 = (new StringBuilder()).append(s1).append("\n    ").append(f[i1].toString()).toString();
            }

        }
        return s1;
    }

    static 
    {
        new IntEvaluator();
        new FloatEvaluator();
    }

    private class AnimatorUpdateListener
    {

        public abstract void onAnimationUpdate(ValueAnimator valueanimator);
    }

}
