// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.pdf417.decoder.ec;

import com.google.zxing.ChecksumException;

// Referenced classes of package com.google.zxing.pdf417.decoder.ec:
//            ModulusGF, a

public final class ErrorCorrection
{

    private final ModulusGF a;

    public ErrorCorrection()
    {
        a = ModulusGF.PDF417_GF;
    }

    public final int decode(int ai[], int i, int ai1[])
    {
        a a1 = new a(a, ai);
        int ai2[] = new int[i];
        boolean flag = false;
        for (int j = i; j > 0; j--)
        {
            int i6 = a1.b(a.a(j));
            ai2[i - j] = i6;
            if (i6 != 0)
            {
                flag = true;
            }
        }

        if (!flag)
        {
            return 0;
        }
        a a2 = a.b();
        int k = ai1.length;
        for (int l = 0; l < k; l++)
        {
            int k5 = ai1[l];
            int l5 = a.a((-1 + ai.length) - k5);
            ModulusGF modulusgf = a;
            int ai6[] = new int[2];
            ai6[0] = a.c(0, l5);
            ai6[1] = 1;
            a2 = a2.c(new a(modulusgf, ai6));
        }

        a a3 = new a(a, ai2);
        a a4 = a.a(i, 1);
        a a6;
        a a7;
        a a8;
        a a9;
        a a10;
        int i1;
        int j1;
        a aa[];
        a a11;
        a a12;
        int k1;
        int ai3[];
        int l1;
        int j2;
        int ai4[];
        a a13;
        int l2;
        int ai5[];
        if (a4.a() >= a3.a())
        {
            a a5 = a3;
            a3 = a4;
            a4 = a5;
        }
        a6 = a.a();
        a7 = a.b();
        a8 = a6;
        a9 = a7;
        for (a10 = a4; a10.a() >= i / 2;)
        {
            if (a10.b())
            {
                throw ChecksumException.getChecksumInstance();
            }
            a a14 = a.a();
            int k4 = a10.a(a10.a());
            int l4 = a.c(k4);
            int i5;
            int j5;
            for (; a3.a() >= a10.a() && !a3.b(); a3 = a3.b(a10.a(i5, j5)))
            {
                i5 = a3.a() - a10.a();
                j5 = a.d(a3.a(a3.a()), l4);
                a14 = a14.a(a.a(i5, j5));
            }

            a a15 = a14.c(a9).b(a8).c();
            a8 = a9;
            a9 = a15;
            a a16 = a10;
            a10 = a3;
            a3 = a16;
        }

        i1 = a9.a(0);
        if (i1 == 0)
        {
            throw ChecksumException.getChecksumInstance();
        }
        j1 = a.c(i1);
        aa = (new a[] {
            a9.c(j1), a10.c(j1)
        });
        a11 = aa[0];
        a12 = aa[1];
        k1 = a11.a();
        ai3 = new int[k1];
        l1 = 0;
        for (int i2 = 1; i2 < a.c() && l1 < k1; i2++)
        {
            if (a11.b(i2) == 0)
            {
                ai3[l1] = a.c(i2);
                l1++;
            }
        }

        if (l1 != k1)
        {
            throw ChecksumException.getChecksumInstance();
        }
        j2 = a11.a();
        ai4 = new int[j2];
        for (int k2 = 1; k2 <= j2; k2++)
        {
            ai4[j2 - k2] = a.d(k2, a11.a(k2));
        }

        a13 = new a(a, ai4);
        l2 = ai3.length;
        ai5 = new int[l2];
        for (int i3 = 0; i3 < l2; i3++)
        {
            int l3 = a.c(ai3[i3]);
            int i4 = a.c(0, a12.b(l3));
            int j4 = a.c(a13.b(l3));
            ai5[i3] = a.d(i4, j4);
        }

        for (int j3 = 0; j3 < ai3.length; j3++)
        {
            int k3 = (-1 + ai.length) - a.b(ai3[j3]);
            if (k3 < 0)
            {
                throw ChecksumException.getChecksumInstance();
            }
            ai[k3] = a.c(ai[k3], ai5[j3]);
        }

        return ai3.length;
    }
}
