// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.datamatrix.encoder;

import java.util.Arrays;

public class DefaultPlacement
{

    private final String a;
    private final int b;
    private final int c;
    private final byte d[];

    public DefaultPlacement(String s, int i, int j)
    {
        a = s;
        c = i;
        b = j;
        d = new byte[i * j];
        Arrays.fill(d, (byte)-1);
    }

    private void a(int i, int j, int k)
    {
        a(i - 2, j - 2, k, 1);
        a(i - 2, j - 1, k, 2);
        a(i - 1, j - 2, k, 3);
        a(i - 1, j - 1, k, 4);
        a(i - 1, j, k, 5);
        a(i, j - 2, k, 6);
        a(i, j - 1, k, 7);
        a(i, j, k, 8);
    }

    private void a(int i, int j, int k, int l)
    {
        boolean flag = true;
        int i1;
        int j1;
        if (i < 0)
        {
            j1 = i + b;
            i1 = j + (4 - (4 + b) % 8);
        } else
        {
            i1 = j;
            j1 = i;
        }
        if (i1 < 0)
        {
            i1 += c;
            j1 += 4 - (4 + c) % 8;
        }
        if ((a.charAt(k) & flag << 8 - l) == 0)
        {
            flag = false;
        }
        a(i1, j1, flag);
    }

    private void a(int i, int j, boolean flag)
    {
        byte abyte0[] = d;
        int k = i + j * c;
        byte byte0;
        if (flag)
        {
            byte0 = 1;
        } else
        {
            byte0 = 0;
        }
        abyte0[k] = byte0;
    }

    private boolean a(int i, int j)
    {
        return d[i + j * c] >= 0;
    }

    public final boolean getBit(int i, int j)
    {
        return d[i + j * c] == 1;
    }

    public final void place()
    {
        int i;
        int j;
        int k;
        i = 0;
        j = 4;
        k = 0;
_L2:
        int j1;
        int k1;
        int l1;
        if (j == b && i == 0)
        {
            int j3 = k + 1;
            a(-1 + b, 0, k, 1);
            a(-1 + b, 1, k, 2);
            a(-1 + b, 2, k, 3);
            a(0, -2 + c, k, 4);
            a(0, -1 + c, k, 5);
            a(1, -1 + c, k, 6);
            a(2, -1 + c, k, 7);
            a(3, -1 + c, k, 8);
            k = j3;
        }
        if (j == -2 + b && i == 0 && c % 4 != 0)
        {
            int i3 = k + 1;
            a(-3 + b, 0, k, 1);
            a(-2 + b, 0, k, 2);
            a(-1 + b, 0, k, 3);
            a(0, -4 + c, k, 4);
            a(0, -3 + c, k, 5);
            a(0, -2 + c, k, 6);
            a(0, -1 + c, k, 7);
            a(1, -1 + c, k, 8);
            k = i3;
        }
        if (j == -2 + b && i == 0 && c % 8 == 4)
        {
            int l2 = k + 1;
            a(-3 + b, 0, k, 1);
            a(-2 + b, 0, k, 2);
            a(-1 + b, 0, k, 3);
            a(0, -2 + c, k, 4);
            a(0, -1 + c, k, 5);
            a(1, -1 + c, k, 6);
            a(2, -1 + c, k, 7);
            a(3, -1 + c, k, 8);
            k = l2;
        }
        if (j == 4 + b && i == 2 && c % 8 == 0)
        {
            int k2 = k + 1;
            a(-1 + b, 0, k, 1);
            a(-1 + b, -1 + c, k, 2);
            a(0, -3 + c, k, 3);
            a(0, -2 + c, k, 4);
            a(0, -1 + c, k, 5);
            a(1, -3 + c, k, 6);
            a(1, -2 + c, k, 7);
            a(1, -1 + c, k, 8);
            k = k2;
        }
        do
        {
            if (j < b && i >= 0 && !a(i, j))
            {
                int j2 = k + 1;
                a(j, i, k);
                k = j2;
            }
            j -= 2;
            i += 2;
        } while (j >= 0 && i < c);
        int l = j + 1;
        int i1 = i + 3;
        j1 = l;
        k1 = i1;
        l1 = k;
_L3:
label0:
        {
            int i2;
            if (j1 >= 0 && k1 < c && !a(k1, j1))
            {
                k = l1 + 1;
                a(j1, k1, l1);
            } else
            {
                k = l1;
            }
            j1 += 2;
            i2 = k1 - 2;
            if (j1 < b && i2 >= 0)
            {
                break label0;
            }
            j = j1 + 3;
            i = i2 + 1;
            if (j >= b && i >= c)
            {
                if (!a(-1 + c, -1 + b))
                {
                    a(-1 + c, -1 + b, true);
                    a(-2 + c, -2 + b, true);
                }
                return;
            }
        }
        if (true) goto _L2; else goto _L1
_L1:
        k1 = i2;
        l1 = k;
          goto _L3
    }
}
