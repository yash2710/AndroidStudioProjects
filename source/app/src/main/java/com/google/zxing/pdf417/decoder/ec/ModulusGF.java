// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.pdf417.decoder.ec;


// Referenced classes of package com.google.zxing.pdf417.decoder.ec:
//            a

public final class ModulusGF
{

    public static final ModulusGF PDF417_GF = new ModulusGF(929, 3);
    private final int a[] = new int[929];
    private final int b[] = new int[929];
    private final a c = new a(this, new int[] {
        0
    });
    private final a d = new a(this, new int[] {
        1
    });
    private final int e = 929;

    private ModulusGF(int i, int j)
    {
        int k = 0;
        int l = 1;
        for (; k < 929; k++)
        {
            a[k] = l;
            l = (l * 3) % 929;
        }

        for (int i1 = 0; i1 < 928; i1++)
        {
            b[a[i1]] = i1;
        }

    }

    final int a(int i)
    {
        return a[i];
    }

    final a a()
    {
        return c;
    }

    final a a(int i, int j)
    {
        if (i < 0)
        {
            throw new IllegalArgumentException();
        }
        if (j == 0)
        {
            return c;
        } else
        {
            int ai[] = new int[i + 1];
            ai[0] = j;
            return new a(this, ai);
        }
    }

    final int b(int i)
    {
        if (i == 0)
        {
            throw new IllegalArgumentException();
        } else
        {
            return b[i];
        }
    }

    final int b(int i, int j)
    {
        return (i + j) % e;
    }

    final a b()
    {
        return d;
    }

    final int c()
    {
        return e;
    }

    final int c(int i)
    {
        if (i == 0)
        {
            throw new ArithmeticException();
        } else
        {
            return a[-1 + (e - b[i])];
        }
    }

    final int c(int i, int j)
    {
        return ((i + e) - j) % e;
    }

    final int d(int i, int j)
    {
        if (i == 0 || j == 0)
        {
            return 0;
        } else
        {
            return a[(b[i] + b[j]) % (-1 + e)];
        }
    }

}
