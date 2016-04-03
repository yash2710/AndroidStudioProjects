// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.pdf417.decoder;

import java.util.Formatter;

// Referenced classes of package com.google.zxing.pdf417.decoder:
//            a, i, d, j, 
//            c

final class h
{

    private final a a;
    private final i b[];
    private c c;
    private final int d;

    h(a a1, c c1)
    {
        a = a1;
        d = a1.a();
        c = c1;
        b = new i[2 + d];
    }

    private static int a(int k, int l, d d1)
    {
        while (d1 == null || d1.a()) 
        {
            return l;
        }
        if (d1.a(k))
        {
            d1.b(k);
            return 0;
        } else
        {
            return l + 1;
        }
    }

    private void a(i k)
    {
        if (k != null)
        {
            ((j)k).a(a);
        }
    }

    final i a(int k)
    {
        return b[k];
    }

    final void a(int k, i l)
    {
        b[k] = l;
    }

    final i[] a()
    {
        a(b[0]);
        a(b[1 + d]);
        int k = 928;
        do
        {
            int i1;
            int k2;
            int k3;
            int i4;
            if (b[0] != null && b[1 + d] != null)
            {
                d ad6[] = b[0].b();
                d ad7[] = b[1 + d].b();
                int l4 = 0;
                while (l4 < ad6.length) 
                {
                    if (ad6[l4] != null && ad7[l4] != null && ad6[l4].h() == ad7[l4].h())
                    {
                        for (int i5 = 1; i5 <= d; i5++)
                        {
                            d d5 = b[i5].b()[l4];
                            if (d5 == null)
                            {
                                continue;
                            }
                            d5.b(ad6[l4].h());
                            if (!d5.a())
                            {
                                b[i5].b()[l4] = null;
                            }
                        }

                    }
                    l4++;
                }
            }
            if (b[0] == null)
            {
                i1 = 0;
            } else
            {
                d ad[] = b[0].b();
                int l = 0;
                i1 = 0;
                while (l < ad.length) 
                {
                    if (ad[l] != null)
                    {
                        int j1 = ad[l].h();
                        int k1 = i1;
                        int l1 = 0;
                        for (int i2 = 1; i2 < 1 + d && l1 < 2; i2++)
                        {
                            d d1 = b[i2].b()[l];
                            if (d1 == null)
                            {
                                continue;
                            }
                            l1 = a(j1, l1, d1);
                            if (!d1.a())
                            {
                                k1++;
                            }
                        }

                        i1 = k1;
                    }
                    l++;
                }
            }
            if (b[1 + d] == null)
            {
                k2 = 0;
            } else
            {
                d ad1[] = b[1 + d].b();
                int j2 = 0;
                k2 = 0;
                while (j2 < ad1.length) 
                {
                    if (ad1[j2] != null)
                    {
                        int l2 = ad1[j2].h();
                        int i3 = 1 + d;
                        for (int j3 = 0; i3 > 0 && j3 < 2; i3--)
                        {
                            d d2 = b[i3].b()[j2];
                            if (d2 == null)
                            {
                                continue;
                            }
                            j3 = a(l2, j3, d2);
                            if (!d2.a())
                            {
                                k2++;
                            }
                        }

                    }
                    j2++;
                }
            }
            k3 = i1 + k2;
            if (k3 == 0)
            {
                i4 = 0;
            } else
            {
                int l3 = 1;
                while (l3 < 1 + d) 
                {
                    d ad2[] = b[l3].b();
label0:
                    for (int j4 = 0; j4 < ad2.length; j4++)
                    {
                        if (ad2[j4] == null || ad2[j4].a())
                        {
                            continue;
                        }
                        d d3 = ad2[j4];
                        d ad3[] = b[l3 - 1].b();
                        d ad4[];
                        d ad5[];
                        int k4;
                        if (b[l3 + 1] != null)
                        {
                            ad4 = b[l3 + 1].b();
                        } else
                        {
                            ad4 = ad3;
                        }
                        ad5 = new d[14];
                        ad5[2] = ad3[j4];
                        ad5[3] = ad4[j4];
                        if (j4 > 0)
                        {
                            ad5[0] = ad2[j4 - 1];
                            ad5[4] = ad3[j4 - 1];
                            ad5[5] = ad4[j4 - 1];
                        }
                        if (j4 > 1)
                        {
                            ad5[8] = ad2[j4 - 2];
                            ad5[10] = ad3[j4 - 2];
                            ad5[11] = ad4[j4 - 2];
                        }
                        if (j4 < -1 + ad2.length)
                        {
                            ad5[1] = ad2[j4 + 1];
                            ad5[6] = ad3[j4 + 1];
                            ad5[7] = ad4[j4 + 1];
                        }
                        if (j4 < -2 + ad2.length)
                        {
                            ad5[9] = ad2[j4 + 2];
                            ad5[12] = ad3[j4 + 2];
                            ad5[13] = ad4[j4 + 2];
                        }
                        k4 = 0;
                        do
                        {
                            if (k4 >= 14)
                            {
                                continue label0;
                            }
                            d d4 = ad5[k4];
                            boolean flag;
                            if (d4 != null && d4.a() && d4.f() == d3.f())
                            {
                                d3.b(d4.h());
                                flag = true;
                            } else
                            {
                                flag = false;
                            }
                            if (flag)
                            {
                                continue label0;
                            }
                            k4++;
                        } while (true);
                    }

                    l3++;
                }
                i4 = k3;
            }
            if (i4 <= 0 || i4 >= k)
            {
                return b;
            }
            k = i4;
        } while (true);
    }

    final int b()
    {
        return d;
    }

    final int c()
    {
        return a.c();
    }

    final int d()
    {
        return a.b();
    }

    final c e()
    {
        return c;
    }

    public final void setBoundingBox(c c1)
    {
        c = c1;
    }

    public final String toString()
    {
        i k = b[0];
        if (k == null)
        {
            k = b[1 + d];
        }
        Formatter formatter = new Formatter();
        for (int l = 0; l < k.b().length; l++)
        {
            Object aobj[] = new Object[1];
            aobj[0] = Integer.valueOf(l);
            formatter.format("CW %3d:", aobj);
            int i1 = 0;
            while (i1 < 2 + d) 
            {
                if (b[i1] == null)
                {
                    formatter.format("    |   ", new Object[0]);
                } else
                {
                    d d1 = b[i1].b()[l];
                    if (d1 == null)
                    {
                        formatter.format("    |   ", new Object[0]);
                    } else
                    {
                        Object aobj1[] = new Object[2];
                        aobj1[0] = Integer.valueOf(d1.h());
                        aobj1[1] = Integer.valueOf(d1.g());
                        formatter.format(" %3d|%3d", aobj1);
                    }
                }
                i1++;
            }
            formatter.format("\n", new Object[0]);
        }

        String s = formatter.toString();
        formatter.close();
        return s;
    }
}
