// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.common.reedsolomon;


// Referenced classes of package com.google.zxing.common.reedsolomon:
//            a

public final class GenericGF
{

    public static final GenericGF AZTEC_DATA_10 = new GenericGF(1033, 1024, 1);
    public static final GenericGF AZTEC_DATA_12 = new GenericGF(4201, 4096, 1);
    public static final GenericGF AZTEC_DATA_6;
    public static final GenericGF AZTEC_DATA_8;
    public static final GenericGF AZTEC_PARAM = new GenericGF(19, 16, 1);
    public static final GenericGF DATA_MATRIX_FIELD_256;
    public static final GenericGF MAXICODE_FIELD_64;
    public static final GenericGF QR_CODE_FIELD_256 = new GenericGF(285, 256, 0);
    private int a[];
    private int b[];
    private a c;
    private a d;
    private final int e;
    private final int f;
    private final int g;
    private boolean h;

    public GenericGF(int i, int j, int k)
    {
        h = false;
        f = i;
        e = j;
        g = k;
        if (j <= 0)
        {
            c();
        }
    }

    static int b(int i, int j)
    {
        return i ^ j;
    }

    private void c()
    {
        a = new int[e];
        b = new int[e];
        int i = 0;
        int j = 1;
        for (; i < e; i++)
        {
            a[i] = j;
            j <<= 1;
            if (j >= e)
            {
                j = (j ^ f) & -1 + e;
            }
        }

        for (int k = 0; k < -1 + e; k++)
        {
            b[a[k]] = k;
        }

        c = new a(this, new int[] {
            0
        });
        d = new a(this, new int[] {
            1
        });
        h = true;
    }

    private void d()
    {
        if (!h)
        {
            c();
        }
    }

    final int a(int i)
    {
        d();
        return a[i];
    }

    final a a()
    {
        d();
        return c;
    }

    final a a(int i, int j)
    {
        d();
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
        d();
        if (i == 0)
        {
            throw new IllegalArgumentException();
        } else
        {
            return b[i];
        }
    }

    final a b()
    {
        d();
        return d;
    }

    final int c(int i)
    {
        d();
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
        d();
        if (i == 0 || j == 0)
        {
            return 0;
        } else
        {
            return a[(b[i] + b[j]) % (-1 + e)];
        }
    }

    public final int getGeneratorBase()
    {
        return g;
    }

    public final int getSize()
    {
        return e;
    }

    public final String toString()
    {
        return (new StringBuilder("GF(0x")).append(Integer.toHexString(f)).append(',').append(e).append(')').toString();
    }

    static 
    {
        AZTEC_DATA_6 = new GenericGF(67, 64, 1);
        GenericGF genericgf = new GenericGF(301, 256, 1);
        DATA_MATRIX_FIELD_256 = genericgf;
        AZTEC_DATA_8 = genericgf;
        MAXICODE_FIELD_64 = AZTEC_DATA_6;
    }
}
