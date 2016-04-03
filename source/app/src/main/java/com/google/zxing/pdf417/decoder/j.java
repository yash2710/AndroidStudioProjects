// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.pdf417.decoder;

import com.google.zxing.ResultPoint;

// Referenced classes of package com.google.zxing.pdf417.decoder:
//            i, d, a, c, 
//            b

final class j extends i
{

    private final boolean a;

    j(c c1, boolean flag)
    {
        super(c1);
        a = flag;
    }

    private void a(d ad[], a a1)
    {
        int k = 0;
_L7:
        if (k >= ad.length) goto _L2; else goto _L1
_L1:
        d d1 = ad[k];
        if (ad[k] == null) goto _L4; else goto _L3
_L3:
        int l;
        int i1;
        l = d1.g() % 30;
        i1 = d1.h();
        if (i1 <= a1.c()) goto _L6; else goto _L5
_L5:
        ad[k] = null;
_L4:
        k++;
          goto _L7
_L6:
        if (!a)
        {
            i1 += 2;
        }
        switch (i1 % 3)
        {
        case 0: // '\0'
            if (1 + l * 3 != a1.d())
            {
                ad[k] = null;
            }
            break;

        case 1: // '\001'
            if (l / 3 != a1.b() || l % 3 != a1.e())
            {
                ad[k] = null;
            }
            break;

        case 2: // '\002'
            if (l + 1 != a1.a())
            {
                ad[k] = null;
            }
            break;
        }
        if (true) goto _L4; else goto _L2
_L2:
    }

    final int a(a a1)
    {
        d ad[] = b();
        d ad1[] = b();
        int k = ad1.length;
        for (int l = 0; l < k; l++)
        {
            d d2 = ad1[l];
            if (d2 != null)
            {
                d2.b();
            }
        }

        a(ad, a1);
        c c1 = a();
        ResultPoint resultpoint;
        ResultPoint resultpoint1;
        int i1;
        int j1;
        float f;
        byte byte0;
        int k1;
        int l1;
        int i2;
        if (a)
        {
            resultpoint = c1.e();
        } else
        {
            resultpoint = c1.f();
        }
        if (a)
        {
            resultpoint1 = c1.g();
        } else
        {
            resultpoint1 = c1.h();
        }
        i1 = b((int)resultpoint.getY());
        j1 = b((int)resultpoint1.getY());
        f = (float)(j1 - i1) / (float)a1.c();
        byte0 = -1;
        k1 = 0;
        l1 = 1;
        i2 = i1;
        while (i2 < j1) 
        {
            int j2;
            int k2;
            int l2;
            if (ad[i2] != null)
            {
                d d1 = ad[i2];
                int i3 = d1.h() - byte0;
                if (i3 == 0)
                {
                    j2 = k1 + 1;
                    k2 = l1;
                    l2 = byte0;
                } else
                if (i3 == 1)
                {
                    int i4 = Math.max(l1, k1);
                    l2 = d1.h();
                    k2 = i4;
                    j2 = 1;
                } else
                if (i3 < 0)
                {
                    ad[i2] = null;
                    j2 = k1;
                    k2 = l1;
                    l2 = byte0;
                } else
                if (d1.h() >= a1.c())
                {
                    ad[i2] = null;
                    j2 = k1;
                    k2 = l1;
                    l2 = byte0;
                } else
                if (i3 > i2)
                {
                    ad[i2] = null;
                    j2 = k1;
                    k2 = l1;
                    l2 = byte0;
                } else
                {
                    int j3;
                    boolean flag;
                    int k3;
                    if (l1 > 2)
                    {
                        j3 = i3 * (l1 - 2);
                    } else
                    {
                        j3 = i3;
                    }
                    if (j3 >= i2)
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    k3 = 1;
                    while (k3 <= j3 && !flag) 
                    {
                        if (ad[i2 - k3] != null)
                        {
                            flag = true;
                        } else
                        {
                            flag = false;
                        }
                        k3++;
                    }
                    if (flag)
                    {
                        ad[i2] = null;
                        j2 = k1;
                        k2 = l1;
                        l2 = byte0;
                    } else
                    {
                        int l3 = d1.h();
                        k2 = l1;
                        l2 = l3;
                        j2 = 1;
                    }
                }
            } else
            {
                j2 = k1;
                k2 = l1;
                l2 = byte0;
            }
            i2++;
            byte0 = l2;
            l1 = k2;
            k1 = j2;
        }
        return (int)(0.5D + (double)f);
    }

    final int[] c()
    {
        a a1 = d();
        int ai[];
        if (a1 == null)
        {
            ai = null;
        } else
        {
            c c1 = a();
            ResultPoint resultpoint;
            ResultPoint resultpoint1;
            int k;
            int l;
            float f;
            d ad[];
            int i1;
            int j1;
            int k1;
            int l1;
            if (a)
            {
                resultpoint = c1.e();
            } else
            {
                resultpoint = c1.f();
            }
            if (a)
            {
                resultpoint1 = c1.g();
            } else
            {
                resultpoint1 = c1.h();
            }
            k = b((int)resultpoint.getY());
            l = b((int)resultpoint1.getY());
            f = (float)(l - k) / (float)a1.c();
            ad = b();
            i1 = -1;
            j1 = 1;
            k1 = k;
            l1 = 0;
            while (k1 < l) 
            {
                if (ad[k1] != null)
                {
                    d d2 = ad[k1];
                    d2.b();
                    int l2 = d2.h() - i1;
                    if (l2 == 0)
                    {
                        l1++;
                    } else
                    if (l2 == 1)
                    {
                        int i3 = Math.max(j1, l1);
                        i1 = d2.h();
                        j1 = i3;
                        l1 = 1;
                    } else
                    if (d2.h() >= a1.c())
                    {
                        ad[k1] = null;
                    } else
                    {
                        i1 = d2.h();
                        l1 = 1;
                    }
                }
                k1++;
            }
            int _tmp = (int)(0.5D + (double)f);
            ai = new int[a1.c()];
            d ad1[] = b();
            int i2 = ad1.length;
            int j2 = 0;
            while (j2 < i2) 
            {
                d d1 = ad1[j2];
                if (d1 != null)
                {
                    int k2 = d1.h();
                    ai[k2] = 1 + ai[k2];
                }
                j2++;
            }
        }
        return ai;
    }

    final a d()
    {
        d ad[];
        b b1;
        b b2;
        b b3;
        b b4;
        int k;
        int l;
        ad = b();
        b1 = new b();
        b2 = new b();
        b3 = new b();
        b4 = new b();
        k = ad.length;
        l = 0;
_L8:
        d d1;
        if (l >= k)
        {
            break MISSING_BLOCK_LABEL_179;
        }
        d1 = ad[l];
        if (d1 == null) goto _L2; else goto _L1
_L1:
        int i1;
        int j1;
        d1.b();
        i1 = d1.g() % 30;
        j1 = d1.h();
        if (!a)
        {
            j1 += 2;
        }
        j1 % 3;
        JVM INSTR tableswitch 0 2: default 128
    //                   0 134
    //                   1 147
    //                   2 168;
           goto _L3 _L4 _L5 _L6
_L6:
        break MISSING_BLOCK_LABEL_168;
_L3:
        break; /* Loop/switch isn't completed */
_L4:
        break; /* Loop/switch isn't completed */
_L2:
        l++;
        if (true) goto _L8; else goto _L7
_L7:
        b2.a(1 + i1 * 3);
          goto _L2
_L5:
        b4.a(i1 / 3);
        b3.a(i1 % 3);
          goto _L2
        b1.a(i1 + 1);
          goto _L2
        if (b1.a().length == 0 || b2.a().length == 0 || b3.a().length == 0 || b4.a().length == 0 || b1.a()[0] <= 0 || b2.a()[0] + b3.a()[0] < 3 || b2.a()[0] + b3.a()[0] > 90)
        {
            return null;
        } else
        {
            a a1 = new a(b1.a()[0], b2.a()[0], b3.a()[0], b4.a()[0]);
            a(ad, a1);
            return a1;
        }
    }

    final boolean e()
    {
        return a;
    }

    public final String toString()
    {
        return (new StringBuilder("IsLeft: ")).append(a).append('\n').append(super.toString()).toString();
    }
}
