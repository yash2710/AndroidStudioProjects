// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.nineoldandroids.animation;

import android.view.animation.Interpolator;
import java.util.ArrayList;
import java.util.Arrays;

// Referenced classes of package com.nineoldandroids.animation:
//            Keyframe, h, f, i, 
//            g, j, TypeEvaluator

class k
{

    int a;
    Interpolator b;
    ArrayList c;
    TypeEvaluator d;
    private Keyframe e;
    private Keyframe f;

    public transient k(Keyframe akeyframe[])
    {
        a = akeyframe.length;
        c = new ArrayList();
        c.addAll(Arrays.asList(akeyframe));
        e = (Keyframe)c.get(0);
        f = (Keyframe)c.get(-1 + a);
        b = f.getInterpolator();
    }

    public static transient k ofFloat(float af[])
    {
        int l = 1;
        int i1 = af.length;
        h ah[] = new h[Math.max(i1, 2)];
        if (i1 == l)
        {
            ah[0] = (h)Keyframe.ofFloat(0.0F);
            ah[l] = (h)Keyframe.ofFloat(1.0F, af[0]);
        } else
        {
            ah[0] = (h)Keyframe.ofFloat(0.0F, af[0]);
            while (l < i1) 
            {
                ah[l] = (h)Keyframe.ofFloat((float)l / (float)(i1 - 1), af[l]);
                l++;
            }
        }
        return new f(ah);
    }

    public static transient k ofInt(int ai[])
    {
        int l = 1;
        int i1 = ai.length;
        i ai1[] = new i[Math.max(i1, 2)];
        if (i1 == l)
        {
            ai1[0] = (i)Keyframe.ofInt(0.0F);
            ai1[l] = (i)Keyframe.ofInt(1.0F, ai[0]);
        } else
        {
            ai1[0] = (i)Keyframe.ofInt(0.0F, ai[0]);
            while (l < i1) 
            {
                ai1[l] = (i)Keyframe.ofInt((float)l / (float)(i1 - 1), ai[l]);
                l++;
            }
        }
        return new g(ai1);
    }

    public static transient k ofKeyframe(Keyframe akeyframe[])
    {
        int l = 0;
        int i1 = akeyframe.length;
        int j1 = 0;
        boolean flag = false;
        boolean flag1 = false;
        boolean flag2 = false;
        while (j1 < i1) 
        {
            if (akeyframe[j1] instanceof h)
            {
                flag2 = true;
            } else
            if (akeyframe[j1] instanceof i)
            {
                flag1 = true;
            } else
            {
                flag = true;
            }
            j1++;
        }
        if (flag2 && !flag1 && !flag)
        {
            h ah[] = new h[i1];
            for (; l < i1; l++)
            {
                ah[l] = (h)akeyframe[l];
            }

            return new f(ah);
        }
        if (flag1 && !flag2 && !flag)
        {
            i ai[] = new i[i1];
            for (int k1 = 0; k1 < i1; k1++)
            {
                ai[k1] = (i)akeyframe[k1];
            }

            return new g(ai);
        } else
        {
            return new k(akeyframe);
        }
    }

    public static transient k ofObject(Object aobj[])
    {
        int l = 1;
        int i1 = aobj.length;
        j aj[] = new j[Math.max(i1, 2)];
        if (i1 == l)
        {
            aj[0] = (j)Keyframe.ofObject(0.0F);
            aj[l] = (j)Keyframe.ofObject(1.0F, aobj[0]);
        } else
        {
            aj[0] = (j)Keyframe.ofObject(0.0F, aobj[0]);
            while (l < i1) 
            {
                aj[l] = (j)Keyframe.ofObject((float)l / (float)(i1 - 1), aobj[l]);
                l++;
            }
        }
        return new k(aj);
    }

    public k clone()
    {
        ArrayList arraylist = c;
        int l = c.size();
        Keyframe akeyframe[] = new Keyframe[l];
        for (int i1 = 0; i1 < l; i1++)
        {
            akeyframe[i1] = ((Keyframe)arraylist.get(i1)).clone();
        }

        return new k(akeyframe);
    }

    public volatile Object clone()
    {
        return clone();
    }

    public Object getValue(float f1)
    {
        if (a == 2)
        {
            if (b != null)
            {
                f1 = b.getInterpolation(f1);
            }
            return d.evaluate(f1, e.getValue(), f.getValue());
        }
        if (f1 <= 0.0F)
        {
            Keyframe keyframe3 = (Keyframe)c.get(1);
            Interpolator interpolator2 = keyframe3.getInterpolator();
            if (interpolator2 != null)
            {
                f1 = interpolator2.getInterpolation(f1);
            }
            float f6 = e.getFraction();
            float f7 = (f1 - f6) / (keyframe3.getFraction() - f6);
            return d.evaluate(f7, e.getValue(), keyframe3.getValue());
        }
        if (f1 >= 1.0F)
        {
            Keyframe keyframe2 = (Keyframe)c.get(-2 + a);
            Interpolator interpolator1 = f.getInterpolator();
            if (interpolator1 != null)
            {
                f1 = interpolator1.getInterpolation(f1);
            }
            float f4 = keyframe2.getFraction();
            float f5 = (f1 - f4) / (f.getFraction() - f4);
            return d.evaluate(f5, keyframe2.getValue(), f.getValue());
        }
        Keyframe keyframe = e;
        for (int l = 1; l < a;)
        {
            Keyframe keyframe1 = (Keyframe)c.get(l);
            if (f1 < keyframe1.getFraction())
            {
                Interpolator interpolator = keyframe1.getInterpolator();
                if (interpolator != null)
                {
                    f1 = interpolator.getInterpolation(f1);
                }
                float f2 = keyframe.getFraction();
                float f3 = (f1 - f2) / (keyframe1.getFraction() - f2);
                return d.evaluate(f3, keyframe.getValue(), keyframe1.getValue());
            }
            l++;
            keyframe = keyframe1;
        }

        return f.getValue();
    }

    public void setEvaluator(TypeEvaluator typeevaluator)
    {
        d = typeevaluator;
    }

    public String toString()
    {
        String s = " ";
        for (int l = 0; l < a;)
        {
            String s1 = (new StringBuilder()).append(s).append(((Keyframe)c.get(l)).getValue()).append("  ").toString();
            l++;
            s = s1;
        }

        return s;
    }
}
