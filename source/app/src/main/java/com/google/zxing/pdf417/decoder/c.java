// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.pdf417.decoder;

import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;

final class c
{

    private BitMatrix a;
    private ResultPoint b;
    private ResultPoint c;
    private ResultPoint d;
    private ResultPoint e;
    private int f;
    private int g;
    private int h;
    private int i;

    c(BitMatrix bitmatrix, ResultPoint resultpoint, ResultPoint resultpoint1, ResultPoint resultpoint2, ResultPoint resultpoint3)
    {
        if (resultpoint == null && resultpoint2 == null || resultpoint1 == null && resultpoint3 == null || resultpoint != null && resultpoint1 == null || resultpoint2 != null && resultpoint3 == null)
        {
            throw NotFoundException.getNotFoundInstance();
        } else
        {
            a(bitmatrix, resultpoint, resultpoint1, resultpoint2, resultpoint3);
            return;
        }
    }

    c(c c1)
    {
        a(c1.a, c1.b, c1.c, c1.d, c1.e);
    }

    static c a(c c1, c c2)
    {
        if (c1 == null)
        {
            return c2;
        }
        if (c2 == null)
        {
            return c1;
        } else
        {
            return new c(c1.a, c1.b, c1.c, c2.d, c2.e);
        }
    }

    private void a(BitMatrix bitmatrix, ResultPoint resultpoint, ResultPoint resultpoint1, ResultPoint resultpoint2, ResultPoint resultpoint3)
    {
        a = bitmatrix;
        b = resultpoint;
        c = resultpoint1;
        d = resultpoint2;
        e = resultpoint3;
        i();
    }

    private void i()
    {
        if (b != null) goto _L2; else goto _L1
_L1:
        b = new ResultPoint(0.0F, d.getY());
        c = new ResultPoint(0.0F, e.getY());
_L4:
        f = (int)Math.min(b.getX(), c.getX());
        g = (int)Math.max(d.getX(), e.getX());
        h = (int)Math.min(b.getY(), d.getY());
        i = (int)Math.max(c.getY(), e.getY());
        return;
_L2:
        if (d == null)
        {
            d = new ResultPoint(-1 + a.getWidth(), b.getY());
            e = new ResultPoint(-1 + a.getWidth(), c.getY());
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    final int a()
    {
        return f;
    }

    final c a(int j, int k, boolean flag)
    {
        ResultPoint resultpoint = b;
        ResultPoint resultpoint1 = c;
        ResultPoint resultpoint2 = d;
        ResultPoint resultpoint3 = e;
        ResultPoint resultpoint4;
        ResultPoint resultpoint5;
        if (j > 0)
        {
            int l;
            ResultPoint resultpoint7;
            int i1;
            if (flag)
            {
                resultpoint7 = b;
            } else
            {
                resultpoint7 = d;
            }
            i1 = (int)resultpoint7.getY() - j;
            if (i1 < 0)
            {
                i1 = 0;
            }
            resultpoint4 = new ResultPoint(resultpoint7.getX(), i1);
            if (!flag)
            {
                resultpoint2 = resultpoint4;
                resultpoint4 = resultpoint;
            }
        } else
        {
            resultpoint4 = resultpoint;
        }
        if (k > 0)
        {
            ResultPoint resultpoint6;
            if (flag)
            {
                resultpoint6 = c;
            } else
            {
                resultpoint6 = e;
            }
            l = k + (int)resultpoint6.getY();
            if (l >= a.getHeight())
            {
                l = -1 + a.getHeight();
            }
            resultpoint5 = new ResultPoint(resultpoint6.getX(), l);
            if (!flag)
            {
                resultpoint3 = resultpoint5;
                resultpoint5 = resultpoint1;
            }
        } else
        {
            resultpoint5 = resultpoint1;
        }
        i();
        return new c(a, resultpoint4, resultpoint5, resultpoint2, resultpoint3);
    }

    final int b()
    {
        return g;
    }

    final int c()
    {
        return h;
    }

    final int d()
    {
        return i;
    }

    final ResultPoint e()
    {
        return b;
    }

    final ResultPoint f()
    {
        return d;
    }

    final ResultPoint g()
    {
        return c;
    }

    final ResultPoint h()
    {
        return e;
    }
}
