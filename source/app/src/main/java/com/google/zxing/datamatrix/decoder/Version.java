// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.datamatrix.decoder;

import com.google.zxing.FormatException;

// Referenced classes of package com.google.zxing.datamatrix.decoder:
//            g, f

public final class Version
{

    private static final Version a[];
    private final int b;
    private final int c;
    private final int d;
    private final int e;
    private final int f;
    private final g g;
    private final int h;

    private Version(int i, int j, int k, int l, int i1, g g1)
    {
        int j1 = 0;
        super();
        b = i;
        c = j;
        d = k;
        e = l;
        f = i1;
        g = g1;
        int k1 = g1.a();
        f af[] = g1.b();
        int l1 = af.length;
        int i2 = 0;
        for (; j1 < l1; j1++)
        {
            f f1 = af[j1];
            i2 += f1.a() * (k1 + f1.b());
        }

        h = i2;
    }

    public static Version getVersionForDimensions(int i, int j)
    {
        if ((i & 1) != 0 || (j & 1) != 0)
        {
            throw FormatException.getFormatInstance();
        }
        Version aversion[] = a;
        int k = aversion.length;
        for (int l = 0; l < k; l++)
        {
            Version version = aversion[l];
            if (version.c == i && version.d == j)
            {
                return version;
            }
        }

        throw FormatException.getFormatInstance();
    }

    final g a()
    {
        return g;
    }

    public final int getDataRegionSizeColumns()
    {
        return f;
    }

    public final int getDataRegionSizeRows()
    {
        return e;
    }

    public final int getSymbolSizeColumns()
    {
        return d;
    }

    public final int getSymbolSizeRows()
    {
        return c;
    }

    public final int getTotalCodewords()
    {
        return h;
    }

    public final int getVersionNumber()
    {
        return b;
    }

    public final String toString()
    {
        return String.valueOf(b);
    }

    static 
    {
        Version aversion[] = new Version[30];
        aversion[0] = new Version(1, 10, 10, 8, 8, new g(5, new f(1, 3, (byte)0), (byte)0));
        aversion[1] = new Version(2, 12, 12, 10, 10, new g(7, new f(1, 5, (byte)0), (byte)0));
        aversion[2] = new Version(3, 14, 14, 12, 12, new g(10, new f(1, 8, (byte)0), (byte)0));
        aversion[3] = new Version(4, 16, 16, 14, 14, new g(12, new f(1, 12, (byte)0), (byte)0));
        aversion[4] = new Version(5, 18, 18, 16, 16, new g(14, new f(1, 18, (byte)0), (byte)0));
        aversion[5] = new Version(6, 20, 20, 18, 18, new g(18, new f(1, 22, (byte)0), (byte)0));
        aversion[6] = new Version(7, 22, 22, 20, 20, new g(20, new f(1, 30, (byte)0), (byte)0));
        aversion[7] = new Version(8, 24, 24, 22, 22, new g(24, new f(1, 36, (byte)0), (byte)0));
        aversion[8] = new Version(9, 26, 26, 24, 24, new g(28, new f(1, 44, (byte)0), (byte)0));
        aversion[9] = new Version(10, 32, 32, 14, 14, new g(36, new f(1, 62, (byte)0), (byte)0));
        aversion[10] = new Version(11, 36, 36, 16, 16, new g(42, new f(1, 86, (byte)0), (byte)0));
        aversion[11] = new Version(12, 40, 40, 18, 18, new g(48, new f(1, 114, (byte)0), (byte)0));
        aversion[12] = new Version(13, 44, 44, 20, 20, new g(56, new f(1, 144, (byte)0), (byte)0));
        aversion[13] = new Version(14, 48, 48, 22, 22, new g(68, new f(1, 174, (byte)0), (byte)0));
        aversion[14] = new Version(15, 52, 52, 24, 24, new g(42, new f(2, 102, (byte)0), (byte)0));
        aversion[15] = new Version(16, 64, 64, 14, 14, new g(56, new f(2, 140, (byte)0), (byte)0));
        aversion[16] = new Version(17, 72, 72, 16, 16, new g(36, new f(4, 92, (byte)0), (byte)0));
        aversion[17] = new Version(18, 80, 80, 18, 18, new g(48, new f(4, 114, (byte)0), (byte)0));
        aversion[18] = new Version(19, 88, 88, 20, 20, new g(56, new f(4, 144, (byte)0), (byte)0));
        aversion[19] = new Version(20, 96, 96, 22, 22, new g(68, new f(4, 174, (byte)0), (byte)0));
        aversion[20] = new Version(21, 104, 104, 24, 24, new g(56, new f(6, 136, (byte)0), (byte)0));
        aversion[21] = new Version(22, 120, 120, 18, 18, new g(68, new f(6, 175, (byte)0), (byte)0));
        aversion[22] = new Version(23, 132, 132, 20, 20, new g(62, new f(8, 163, (byte)0), (byte)0));
        aversion[23] = new Version(24, 144, 144, 22, 22, new g(62, new f(8, 156, (byte)0), new f(2, 155, (byte)0), (byte)0));
        aversion[24] = new Version(25, 8, 18, 6, 16, new g(7, new f(1, 5, (byte)0), (byte)0));
        aversion[25] = new Version(26, 8, 32, 6, 14, new g(11, new f(1, 10, (byte)0), (byte)0));
        aversion[26] = new Version(27, 12, 26, 10, 24, new g(14, new f(1, 16, (byte)0), (byte)0));
        aversion[27] = new Version(28, 12, 36, 10, 16, new g(18, new f(1, 22, (byte)0), (byte)0));
        aversion[28] = new Version(29, 16, 36, 14, 16, new g(24, new f(1, 32, (byte)0), (byte)0));
        aversion[29] = new Version(30, 16, 48, 14, 22, new g(28, new f(1, 49, (byte)0), (byte)0));
        a = aversion;
    }
}
