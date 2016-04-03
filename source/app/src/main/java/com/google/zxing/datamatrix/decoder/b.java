// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.datamatrix.decoder;


// Referenced classes of package com.google.zxing.datamatrix.decoder:
//            Version, g, f

final class b
{

    private final int a;
    private final byte b[];

    private b(int i, byte abyte0[])
    {
        a = i;
        b = abyte0;
    }

    static b[] a(byte abyte0[], Version version)
    {
        g g1 = version.a();
        f af[] = g1.b();
        int i = af.length;
        int j = 0;
        int k = 0;
        for (; j < i; j++)
        {
            k += af[j].a();
        }

        b ab[] = new b[k];
        int l = af.length;
        int i1 = 0;
        for (int j1 = 0; j1 < l;)
        {
            f f1 = af[j1];
            int i6 = i1;
            for (int j6 = 0; j6 < f1.a();)
            {
                int k6 = f1.b();
                int l6 = k6 + g1.a();
                int i7 = i6 + 1;
                ab[i6] = new b(k6, new byte[l6]);
                j6++;
                i6 = i7;
            }

            j1++;
            i1 = i6;
        }

        int k1 = ab[0].b.length - g1.a();
        int l1 = k1 - 1;
        int i2 = 0;
        int j2 = 0;
        for (; i2 < l1; i2++)
        {
            for (int k5 = 0; k5 < i1;)
            {
                byte abyte3[] = ab[k5].b;
                int l5 = j2 + 1;
                abyte3[i2] = abyte0[j2];
                k5++;
                j2 = l5;
            }

        }

        boolean flag;
        int k2;
        int l2;
        if (version.getVersionNumber() == 24)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            k2 = 8;
        } else
        {
            k2 = i1;
        }
        for (l2 = 0; l2 < k2;)
        {
            byte abyte2[] = ab[l2].b;
            int i5 = k1 - 1;
            int j5 = j2 + 1;
            abyte2[i5] = abyte0[j2];
            l2++;
            j2 = j5;
        }

        int i3 = ab[0].b.length;
        int j3 = j2;
        for (int k3 = k1; k3 < i3;)
        {
            int l3 = j3;
            int i4 = 0;
            while (i4 < i1) 
            {
                int j4;
                byte abyte1[];
                int k4;
                int l4;
                if (flag && i4 > 7)
                {
                    j4 = k3 - 1;
                } else
                {
                    j4 = k3;
                }
                abyte1 = ab[i4].b;
                k4 = l3 + 1;
                abyte1[j4] = abyte0[l3];
                l4 = i4 + 1;
                l3 = k4;
                i4 = l4;
            }
            k3++;
            j3 = l3;
        }

        if (j3 != abyte0.length)
        {
            throw new IllegalArgumentException();
        } else
        {
            return ab;
        }
    }

    final int a()
    {
        return a;
    }

    final byte[] b()
    {
        return b;
    }
}
