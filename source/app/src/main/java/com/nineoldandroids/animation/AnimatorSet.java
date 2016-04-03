// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.nineoldandroids.animation;

import android.view.animation.Interpolator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.nineoldandroids.animation:
//            Animator, e, c, ValueAnimator, 
//            b, ObjectAnimator, d, a

public final class AnimatorSet extends Animator
{

    boolean b;
    private ArrayList c;
    private HashMap d;
    private ArrayList e;
    private ArrayList f;
    private boolean g;
    private b h;
    private boolean i;
    private long j;
    private ValueAnimator k;
    private long l;

    public AnimatorSet()
    {
        c = new ArrayList();
        d = new HashMap();
        e = new ArrayList();
        f = new ArrayList();
        g = true;
        h = null;
        b = false;
        i = false;
        j = 0L;
        k = null;
        l = -1L;
    }

    static ArrayList a(AnimatorSet animatorset)
    {
        return animatorset.c;
    }

    private void a()
    {
        if (g)
        {
            f.clear();
            ArrayList arraylist = new ArrayList();
            int i2 = e.size();
            for (int j2 = 0; j2 < i2; j2++)
            {
                e e4 = (e)e.get(j2);
                if (e4.b == null || e4.b.size() == 0)
                {
                    arraylist.add(e4);
                }
            }

            ArrayList arraylist1 = new ArrayList();
            for (; arraylist.size() > 0; arraylist1.clear())
            {
                int k2 = arraylist.size();
                for (int l2 = 0; l2 < k2; l2++)
                {
                    e e2 = (e)arraylist.get(l2);
                    f.add(e2);
                    if (e2.e == null)
                    {
                        continue;
                    }
                    int i3 = e2.e.size();
                    for (int j3 = 0; j3 < i3; j3++)
                    {
                        e e3 = (e)e2.e.get(j3);
                        e3.d.remove(e2);
                        if (e3.d.size() == 0)
                        {
                            arraylist1.add(e3);
                        }
                    }

                }

                arraylist.clear();
                arraylist.addAll(arraylist1);
            }

            g = false;
            if (f.size() != e.size())
            {
                throw new IllegalStateException("Circular dependencies cannot exist in AnimatorSet");
            }
        } else
        {
            int i1 = e.size();
            for (int j1 = 0; j1 < i1; j1++)
            {
                e e1 = (e)e.get(j1);
                if (e1.b != null && e1.b.size() > 0)
                {
                    int k1 = e1.b.size();
                    for (int l1 = 0; l1 < k1; l1++)
                    {
                        c c1 = (c)e1.b.get(l1);
                        if (e1.d == null)
                        {
                            e1.d = new ArrayList();
                        }
                        if (!e1.d.contains(c1.a))
                        {
                            e1.d.add(c1.a);
                        }
                    }

                }
                e1.f = false;
            }

        }
    }

    static boolean a(AnimatorSet animatorset, boolean flag)
    {
        animatorset.i = false;
        return false;
    }

    static HashMap b(AnimatorSet animatorset)
    {
        return animatorset.d;
    }

    static ArrayList c(AnimatorSet animatorset)
    {
        return animatorset.f;
    }

    static ArrayList d(AnimatorSet animatorset)
    {
        return animatorset.e;
    }

    public final void cancel()
    {
        b = true;
        if (!isStarted())
        {
            break MISSING_BLOCK_LABEL_180;
        }
        ArrayList arraylist;
        Iterator iterator;
        Iterator iterator1;
        if (a != null)
        {
            ArrayList arraylist1 = (ArrayList)a.clone();
            for (Iterator iterator2 = arraylist1.iterator(); iterator2.hasNext(); ((Animator.AnimatorListener)iterator2.next()).onAnimationCancel(this)) { }
            arraylist = arraylist1;
        } else
        {
            arraylist = null;
        }
        if (k == null || !k.isRunning()) goto _L2; else goto _L1
_L1:
        k.cancel();
_L4:
        if (arraylist != null)
        {
            for (iterator1 = arraylist.iterator(); iterator1.hasNext(); ((Animator.AnimatorListener)iterator1.next()).onAnimationEnd(this)) { }
        }
        break; /* Loop/switch isn't completed */
_L2:
        if (f.size() > 0)
        {
            iterator = f.iterator();
            while (iterator.hasNext()) 
            {
                ((e)iterator.next()).a.cancel();
            }
        }
        if (true) goto _L4; else goto _L3
_L3:
        i = false;
    }

    public final volatile Animator clone()
    {
        return clone();
    }

    public final AnimatorSet clone()
    {
        AnimatorSet animatorset = (AnimatorSet)super.clone();
        animatorset.g = true;
        animatorset.b = false;
        animatorset.i = false;
        animatorset.c = new ArrayList();
        animatorset.d = new HashMap();
        animatorset.e = new ArrayList();
        animatorset.f = new ArrayList();
        HashMap hashmap = new HashMap();
        Iterator iterator = e.iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            e e3 = (e)iterator.next();
            e e4 = e3.clone();
            hashmap.put(e3, e4);
            animatorset.e.add(e4);
            animatorset.d.put(e4.a, e4);
            e4.b = null;
            e4.c = null;
            e4.e = null;
            e4.d = null;
            ArrayList arraylist = e4.a.getListeners();
            if (arraylist != null)
            {
                Iterator iterator3 = arraylist.iterator();
                ArrayList arraylist1 = null;
                do
                {
                    if (!iterator3.hasNext())
                    {
                        break;
                    }
                    Animator.AnimatorListener animatorlistener = (Animator.AnimatorListener)iterator3.next();
                    if (animatorlistener instanceof b)
                    {
                        if (arraylist1 == null)
                        {
                            arraylist1 = new ArrayList();
                        }
                        arraylist1.add(animatorlistener);
                    }
                } while (true);
                if (arraylist1 != null)
                {
                    Iterator iterator4 = arraylist1.iterator();
                    while (iterator4.hasNext()) 
                    {
                        arraylist.remove((Animator.AnimatorListener)iterator4.next());
                    }
                }
            }
        } while (true);
        Iterator iterator1 = e.iterator();
        do
        {
            if (!iterator1.hasNext())
            {
                break;
            }
            e e1 = (e)iterator1.next();
            e e2 = (e)hashmap.get(e1);
            if (e1.b != null)
            {
                Iterator iterator2 = e1.b.iterator();
                while (iterator2.hasNext()) 
                {
                    c c1 = (c)iterator2.next();
                    e2.addDependency(new c((e)hashmap.get(c1.a), c1.b));
                }
            }
        } while (true);
        return animatorset;
    }

    public final volatile Object clone()
    {
        return clone();
    }

    public final void end()
    {
        b = true;
        if (isStarted())
        {
            if (f.size() != e.size())
            {
                a();
                e e1;
                for (Iterator iterator2 = f.iterator(); iterator2.hasNext(); e1.a.addListener(h))
                {
                    e1 = (e)iterator2.next();
                    if (h == null)
                    {
                        h = new b(this, this);
                    }
                }

            }
            if (k != null)
            {
                k.cancel();
            }
            if (f.size() > 0)
            {
                for (Iterator iterator1 = f.iterator(); iterator1.hasNext(); ((e)iterator1.next()).a.end()) { }
            }
            if (a != null)
            {
                for (Iterator iterator = ((ArrayList)a.clone()).iterator(); iterator.hasNext(); ((Animator.AnimatorListener)iterator.next()).onAnimationEnd(this)) { }
            }
            i = false;
        }
    }

    public final ArrayList getChildAnimations()
    {
        ArrayList arraylist = new ArrayList();
        for (Iterator iterator = e.iterator(); iterator.hasNext(); arraylist.add(((e)iterator.next()).a)) { }
        return arraylist;
    }

    public final long getDuration()
    {
        return l;
    }

    public final long getStartDelay()
    {
        return j;
    }

    public final boolean isRunning()
    {
        for (Iterator iterator = e.iterator(); iterator.hasNext();)
        {
            if (((e)iterator.next()).a.isRunning())
            {
                return true;
            }
        }

        return false;
    }

    public final boolean isStarted()
    {
        return i;
    }

    public final Builder play(Animator animator)
    {
        if (animator != null)
        {
            g = true;
            return new Builder(animator);
        } else
        {
            return null;
        }
    }

    public final void playSequentially(List list)
    {
        if (list != null && list.size() > 0)
        {
            g = true;
            if (list.size() == 1)
            {
                play((Animator)list.get(0));
            } else
            {
                int i1 = 0;
                while (i1 < -1 + list.size()) 
                {
                    play((Animator)list.get(i1)).before((Animator)list.get(i1 + 1));
                    i1++;
                }
            }
        }
    }

    public final transient void playSequentially(Animator aanimator[])
    {
        if (aanimator != null)
        {
            g = true;
            int i1 = aanimator.length;
            int j1 = 0;
            if (i1 == 1)
            {
                play(aanimator[0]);
            } else
            {
                while (j1 < -1 + aanimator.length) 
                {
                    play(aanimator[j1]).before(aanimator[j1 + 1]);
                    j1++;
                }
            }
        }
    }

    public final void playTogether(Collection collection)
    {
        if (collection != null && collection.size() > 0)
        {
            g = true;
            Iterator iterator = collection.iterator();
            Builder builder = null;
            while (iterator.hasNext()) 
            {
                Animator animator = (Animator)iterator.next();
                if (builder == null)
                {
                    builder = play(animator);
                } else
                {
                    builder.with(animator);
                }
            }
        }
    }

    public final transient void playTogether(Animator aanimator[])
    {
        boolean flag = true;
        if (aanimator != null)
        {
            g = flag;
            Builder builder = play(aanimator[0]);
            for (; flag < aanimator.length; flag++)
            {
                builder.with(aanimator[flag]);
            }

        }
    }

    public final volatile Animator setDuration(long l1)
    {
        return setDuration(l1);
    }

    public final AnimatorSet setDuration(long l1)
    {
        if (l1 < 0L)
        {
            throw new IllegalArgumentException("duration must be a value of zero or greater");
        }
        for (Iterator iterator = e.iterator(); iterator.hasNext(); ((e)iterator.next()).a.setDuration(l1)) { }
        l = l1;
        return this;
    }

    public final void setInterpolator(Interpolator interpolator)
    {
        for (Iterator iterator = e.iterator(); iterator.hasNext(); ((e)iterator.next()).a.setInterpolator(interpolator)) { }
    }

    public final void setStartDelay(long l1)
    {
        j = l1;
    }

    public final void setTarget(Object obj)
    {
        Iterator iterator = e.iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            Animator animator = ((e)iterator.next()).a;
            if (animator instanceof AnimatorSet)
            {
                ((AnimatorSet)animator).setTarget(obj);
            } else
            if (animator instanceof ObjectAnimator)
            {
                ((ObjectAnimator)animator).setTarget(obj);
            }
        } while (true);
    }

    public final void setupEndValues()
    {
        for (Iterator iterator = e.iterator(); iterator.hasNext(); ((e)iterator.next()).a.setupEndValues()) { }
    }

    public final void setupStartValues()
    {
        for (Iterator iterator = e.iterator(); iterator.hasNext(); ((e)iterator.next()).a.setupStartValues()) { }
    }

    public final void start()
    {
        int i1 = 0;
        b = false;
        i = true;
        a();
        int j1 = f.size();
label0:
        for (int k1 = 0; k1 < j1; k1++)
        {
            e e3 = (e)f.get(k1);
            ArrayList arraylist3 = e3.a.getListeners();
            if (arraylist3 == null || arraylist3.size() <= 0)
            {
                continue;
            }
            Iterator iterator1 = (new ArrayList(arraylist3)).iterator();
            do
            {
                Animator.AnimatorListener animatorlistener;
                do
                {
                    if (!iterator1.hasNext())
                    {
                        continue label0;
                    }
                    animatorlistener = (Animator.AnimatorListener)iterator1.next();
                } while (!(animatorlistener instanceof d) && !(animatorlistener instanceof b));
                e3.a.removeListener(animatorlistener);
            } while (true);
        }

        ArrayList arraylist = new ArrayList();
        int l1 = 0;
        while (l1 < j1) 
        {
            e e2 = (e)f.get(l1);
            if (h == null)
            {
                h = new b(this, this);
            }
            if (e2.b == null || e2.b.size() == 0)
            {
                arraylist.add(e2);
            } else
            {
                int l2 = e2.b.size();
                for (int i3 = 0; i3 < l2; i3++)
                {
                    c c1 = (c)e2.b.get(i3);
                    c1.a.a.addListener(new d(this, e2, c1.b));
                }

                e2.c = (ArrayList)e2.b.clone();
            }
            e2.a.addListener(h);
            l1++;
        }
        if (j <= 0L)
        {
            e e1;
            for (Iterator iterator = arraylist.iterator(); iterator.hasNext(); c.add(e1.a))
            {
                e1 = (e)iterator.next();
                e1.a.start();
            }

        } else
        {
            k = ValueAnimator.ofFloat(new float[] {
                0.0F, 1.0F
            });
            k.setDuration(j);
            k.addListener(new a(this, arraylist));
            k.start();
        }
        if (a != null)
        {
            ArrayList arraylist2 = (ArrayList)a.clone();
            int j2 = arraylist2.size();
            for (int k2 = 0; k2 < j2; k2++)
            {
                ((Animator.AnimatorListener)arraylist2.get(k2)).onAnimationStart(this);
            }

        }
        if (e.size() == 0 && j == 0L)
        {
            i = false;
            if (a != null)
            {
                ArrayList arraylist1 = (ArrayList)a.clone();
                for (int i2 = arraylist1.size(); i1 < i2; i1++)
                {
                    ((Animator.AnimatorListener)arraylist1.get(i1)).onAnimationEnd(this);
                }

            }
        }
    }

    private class Builder
    {

        private e a;
        private AnimatorSet b;

        public Builder after(long l1)
        {
            ValueAnimator valueanimator = ValueAnimator.ofFloat(new float[] {
                0.0F, 1.0F
            });
            valueanimator.setDuration(l1);
            after(((Animator) (valueanimator)));
            return this;
        }

        public Builder after(Animator animator)
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

        public Builder before(Animator animator)
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

        public Builder with(Animator animator)
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

        Builder(Animator animator)
        {
            b = AnimatorSet.this;
            super();
            a = (e)AnimatorSet.b(AnimatorSet.this).get(animator);
            if (a == null)
            {
                a = new e(animator);
                AnimatorSet.b(AnimatorSet.this).put(animator, a);
                AnimatorSet.d(AnimatorSet.this).add(a);
            }
        }
    }

}
