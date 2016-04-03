// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.qrcode.decoder;


// Referenced classes of package com.google.zxing.qrcode.decoder:
//            Version, ErrorCorrectionLevel

final class b
{

    private final int a;
    private final byte b[];

    private b(int i, byte abyte0[])
    {
        a = i;
        b = abyte0;
    }

    static b[] a(byte abyte0[], Version version, ErrorCorrectionLevel errorcorrectionlevel)
    {
        if (abyte0.length != version.getTotalCodewords())
        {
            throw new IllegalArgumentException();
        }
        Version.ECBlocks ecblocks = version.getECBlocksForLevel(errorcorrectionlevel);
        Version.ECB aecb[] = ecblocks.getECBlocks();
        int i = aecb.length;
        int j = 0;
        int k = 0;
        for (; j < i; j++)
        {
            k += aecb[j].getCount();
        }

        b ab[] = new b[k];
        int l = aecb.length;
        int i1 = 0;
        for (int j1 = 0; j1 < l;)
        {
            Version.ECB ecb = aecb[j1];
            int k5 = i1;
            for (int l5 = 0; l5 < ecb.getCount();)
            {
                int i6 = ecb.getDataCodewords();
                int j6 = i6 + ecblocks.getECCodewordsPerBlock();
                int k6 = k5 + 1;
                ab[k5] = new b(i6, new byte[j6]);
                l5++;
                k5 = k6;
            }

            j1++;
            i1 = k5;
        }

        int k1 = ab[0].b.length;
        int l1;
        for (l1 = -1 + ab.length; l1 >= 0 && ab[l1].b.length != k1; l1--) { }
        int i2 = l1 + 1;
        int j2 = k1 - ecblocks.getECCodewordsPerBlock();
        int k2 = 0;
        int l2;
        int l4;
        for (l2 = 0; k2 < j2; l2 = l4)
        {
            l4 = l2;
            for (int i5 = 0; i5 < i1;)
            {
                byte abyte3[] = ab[i5].b;
                int j5 = l4 + 1;
                abyte3[k2] = abyte0[l4];
                i5++;
                l4 = j5;
            }

            k2++;
        }

        for (int i3 = i2; i3 < i1;)
        {
            byte abyte2[] = ab[i3].b;
            int k4 = l2 + 1;
            abyte2[j2] = abyte0[l2];
            i3++;
            l2 = k4;
        }

        for (int j3 = ab[0].b.length; j2 < j3;)
        {
            int k3 = 0;
            int l3 = l2;
            while (k3 < i1) 
            {
                int i4;
                byte abyte1[];
                int j4;
                if (k3 < i2)
                {
                    i4 = j2;
                } else
                {
                    i4 = j2 + 1;
                }
                abyte1 = ab[k3].b;
                j4 = l3 + 1;
                abyte1[i4] = abyte0[l3];
                k3++;
                l3 = j4;
            }
            j2++;
            l2 = l3;
        }

        return ab;
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
