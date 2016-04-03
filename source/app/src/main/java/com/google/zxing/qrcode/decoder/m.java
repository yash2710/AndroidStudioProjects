// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.qrcode.decoder;


// Referenced classes of package com.google.zxing.qrcode.decoder:
//            ErrorCorrectionLevel

final class m
{

    private static final int a[][] = {
        {
            21522, 0
        }, {
            20773, 1
        }, {
            24188, 2
        }, {
            23371, 3
        }, {
            17913, 4
        }, {
            16590, 5
        }, {
            20375, 6
        }, {
            19104, 7
        }, {
            30660, 8
        }, {
            29427, 9
        }, {
            32170, 10
        }, {
            30877, 11
        }, {
            26159, 12
        }, {
            25368, 13
        }, {
            27713, 14
        }, {
            26998, 15
        }, {
            5769, 16
        }, {
            5054, 17
        }, {
            7399, 18
        }, {
            6608, 19
        }, {
            1890, 20
        }, {
            597, 21
        }, {
            3340, 22
        }, {
            2107, 23
        }, {
            13663, 24
        }, {
            12392, 25
        }, {
            16177, 26
        }, {
            14854, 27
        }, {
            9396, 28
        }, {
            8579, 29
        }, {
            11994, 30
        }, {
            11245, 31
        }
    };
    private static final int b[] = {
        0, 1, 1, 2, 1, 2, 2, 3, 1, 2, 
        2, 3, 2, 3, 3, 4
    };
    private final ErrorCorrectionLevel c;
    private final byte d;

    private m(int i)
    {
        c = ErrorCorrectionLevel.forBits(3 & i >> 3);
        d = (byte)(i & 7);
    }

    static int a(int i, int j)
    {
        int k = i ^ j;
        return b[k & 0xf] + b[0xf & k >>> 4] + b[0xf & k >>> 8] + b[0xf & k >>> 12] + b[0xf & k >>> 16] + b[0xf & k >>> 20] + b[0xf & k >>> 24] + b[0xf & k >>> 28];
    }

    static m b(int i, int j)
    {
        m m1 = c(i, j);
        if (m1 != null)
        {
            return m1;
        } else
        {
            return c(i ^ 0x5412, j ^ 0x5412);
        }
    }

    private static m c(int i, int j)
    {
        int k;
        int ai[][];
        int l;
        int i1;
        k = 0x7fffffff;
        ai = a;
        l = 0;
        i1 = 0;
_L2:
        if (l >= 32)
        {
            break; /* Loop/switch isn't completed */
        }
        int ai1[] = ai[l];
        int j1 = ai1[0];
        if (j1 == i || j1 == j)
        {
            return new m(ai1[1]);
        }
        int k1 = a(i, j1);
        int l1;
        int i2;
        int j2;
        if (k1 < k)
        {
            l1 = ai1[1];
        } else
        {
            k1 = k;
            l1 = i1;
        }
        if (i == j)
        {
            break MISSING_BLOCK_LABEL_136;
        }
        i2 = a(j, j1);
        if (i2 >= k1)
        {
            break MISSING_BLOCK_LABEL_136;
        }
        l1 = ai1[1];
_L3:
        l++;
        j2 = l1;
        k = i2;
        i1 = j2;
        if (true) goto _L2; else goto _L1
_L1:
        if (k <= 3)
        {
            return new m(i1);
        } else
        {
            return null;
        }
        i2 = k1;
          goto _L3
    }

    final ErrorCorrectionLevel a()
    {
        return c;
    }

    final byte b()
    {
        return d;
    }

    public final boolean equals(Object obj)
    {
        m m1;
        if (obj instanceof m)
        {
            if (c == (m1 = (m)obj).c && d == m1.d)
            {
                return true;
            }
        }
        return false;
    }

    public final int hashCode()
    {
        return c.ordinal() << 3 | d;
    }

}
