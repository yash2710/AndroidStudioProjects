// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customviews;

import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;

// Referenced classes of package com.flipkart.android.customviews:
//            Panel, D

final class A
    implements Runnable
{

    private Panel a;

    A(Panel panel)
    {
        a = panel;
        super();
    }

    public final void run()
    {
        boolean flag = true;
        if (Panel.a(a) == D.e)
        {
            Panel panel2 = a;
            int i4;
            int j4;
            int k4;
            int l4;
            Panel panel1;
            boolean flag1;
            boolean flag2;
            if (Panel.e(a) == 0 || Panel.e(a) == 2)
            {
                flag1 = flag;
            } else
            {
                flag1 = false;
            }
            if (Panel.j(a) > 0.0F)
            {
                flag2 = flag;
            } else
            {
                flag2 = false;
            }
            Panel.a(panel2, flag1 ^ flag2);
        }
        if (Panel.d(a) == flag)
        {
            int j3 = Panel.g(a);
            int i2;
            if (!Panel.k(a))
            {
                if (Panel.e(a) == 0)
                {
                    j3 = -j3;
                }
                i2 = j3;
                j3 = 0;
            } else
            {
                if (Panel.e(a) == 0)
                {
                    j3 = -j3;
                }
                i2 = 0;
            }
            if (Panel.a(a) == D.d)
            {
                int i;
                int j;
                int k;
                int l;
                int i1;
                int j1;
                int k1;
                int l1;
                TranslateAnimation translateanimation;
                int j2;
                int k2;
                int l2;
                int i3;
                Panel panel;
                int k3;
                int l3;
                if (Math.abs(Panel.l(a) - (float)i2) < Math.abs(Panel.l(a) - (float)j3))
                {
                    panel1 = a;
                    if (Panel.k(a))
                    {
                        flag = false;
                    }
                    Panel.a(panel1, flag);
                } else
                {
                    i2 = j3;
                }
                k4 = (int)Panel.l(a);
                l4 = i2;
                i2 = k4;
                j3 = l4;
            } else
            if (Panel.a(a) == D.e)
            {
                i2 = (int)Panel.l(a);
            }
            if (Panel.a(a) == D.e && Panel.m(a))
            {
                i4 = Math.max((int)(1000F * Math.abs((float)(j3 - i2) / Panel.j(a))), 20);
                l = 0;
                j4 = j3;
                j1 = i4;
                k1 = 0;
                l1 = j4;
            } else
            {
                k3 = (Panel.n(a) * Math.abs(j3 - i2)) / Panel.g(a);
                l3 = j3;
                j1 = k3;
                l1 = l3;
                k1 = 0;
                l = 0;
            }
        } else
        {
            i = Panel.f(a);
            if (!Panel.k(a))
            {
                if (Panel.e(a) == 2)
                {
                    i = -i;
                }
                j = i;
                i = 0;
            } else
            {
                if (Panel.e(a) == 2)
                {
                    i = -i;
                }
                j = 0;
            }
            if (Panel.a(a) == D.d)
            {
                if (Math.abs(Panel.o(a) - (float)j) < Math.abs(Panel.o(a) - (float)i))
                {
                    panel = a;
                    if (Panel.k(a))
                    {
                        flag = false;
                    }
                    Panel.a(panel, flag);
                } else
                {
                    j = i;
                }
                l2 = (int)Panel.o(a);
                i3 = j;
                j = l2;
                i = i3;
            } else
            if (Panel.a(a) == D.e)
            {
                j = (int)Panel.o(a);
            }
            if (Panel.a(a) == D.e && Panel.m(a))
            {
                j2 = Math.max((int)(1000F * Math.abs((float)(i - j) / Panel.j(a))), 20);
                l = j;
                k2 = i;
                j1 = j2;
                k1 = k2;
                l1 = 0;
                i2 = 0;
            } else
            {
                k = (Panel.n(a) * Math.abs(i - j)) / Panel.f(a);
                l = j;
                i1 = i;
                j1 = k;
                k1 = i1;
                l1 = 0;
                i2 = 0;
            }
        }
        Panel.a(a, Panel.b(a, 0.0F));
        if (j1 == 0)
        {
            Panel.a(a, D.c);
            if (Panel.k(a))
            {
                Panel.c(a).setVisibility(8);
            }
            Panel.p(a);
            return;
        }
        translateanimation = new TranslateAnimation(l, k1, i2, l1);
        translateanimation.setDuration(j1);
        translateanimation.setAnimationListener(Panel.q(a));
        if (Panel.a(a) != D.e || !Panel.m(a)) goto _L2; else goto _L1
_L1:
        translateanimation.setInterpolator(new LinearInterpolator());
_L4:
        a.startAnimation(translateanimation);
        return;
_L2:
        if (Panel.r(a) != null)
        {
            translateanimation.setInterpolator(Panel.r(a));
        }
        if (true) goto _L4; else goto _L3
_L3:
    }
}
