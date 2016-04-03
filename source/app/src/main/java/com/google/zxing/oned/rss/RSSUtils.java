// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.oned.rss;


public final class RSSUtils
{

    private RSSUtils()
    {
    }

    private static int a(int i, int j)
    {
_L2:
        int k1;
        for (; k <= j; k++)
        {
            k1 /= k;
        }

        return k1;
        int k = 1;
        int i1;
        int j1;
        if (i - j > j)
        {
            i1 = i - j;
        } else
        {
            int l = i - j;
            i1 = j;
            j = l;
        }
        j1 = k;
        for (; i > i1; i--)
        {
            j1 *= i;
            if (k <= j)
            {
                j1 /= k;
                k++;
            }
        }

        k1 = j1;
        if (true) goto _L2; else goto _L1
_L1:
    }

    public static int getRSSvalue(int ai[], int i, boolean flag)
    {
        int j = ai.length;
        int k = ai.length;
        int l = 0;
        int i1;
        int j4;
        for (i1 = 0; l < k; i1 = j4)
        {
            j4 = i1 + ai[l];
            l++;
        }

        int j1 = 0;
        int k1 = 0;
        int l1 = i1;
        int i2 = 0;
        do
        {
            if (j1 >= j - 1)
            {
                break;
            }
            int j2 = i2 | 1 << j1;
            int k2 = k1;
            int l2 = j2;
            int i3 = 1;
            while (i3 < ai[j1]) 
            {
                int k3 = a(-1 + (l1 - i3), -2 + (j - j1));
                if (flag && l2 == 0 && l1 - i3 - (-1 + (j - j1)) >= -1 + (j - j1))
                {
                    k3 -= a(l1 - i3 - (j - j1), -2 + (j - j1));
                }
                if (-1 + (j - j1) > 1)
                {
                    int l3 = l1 - i3 - (-2 + (j - j1));
                    int i4 = 0;
                    for (; l3 > i; l3--)
                    {
                        i4 += a(-1 + (l1 - i3 - l3), -3 + (j - j1));
                    }

                    k3 -= i4 * (j - 1 - j1);
                } else
                if (l1 - i3 > i)
                {
                    k3--;
                }
                k2 += k3;
                i3++;
                l2 &= -1 ^ 1 << j1;
            }
            int j3 = l1 - i3;
            j1++;
            l1 = j3;
            i2 = l2;
            k1 = k2;
        } while (true);
        return k1;
    }
}
