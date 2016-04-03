// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.datamatrix.decoder;

import com.google.zxing.ChecksumException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.reedsolomon.GenericGF;
import com.google.zxing.common.reedsolomon.ReedSolomonDecoder;
import com.google.zxing.common.reedsolomon.ReedSolomonException;

// Referenced classes of package com.google.zxing.datamatrix.decoder:
//            a, b, c

public final class Decoder
{

    private final ReedSolomonDecoder a;

    public Decoder()
    {
        a = new ReedSolomonDecoder(GenericGF.DATA_MATRIX_FIELD_256);
    }

    public final DecoderResult decode(BitMatrix bitmatrix)
    {
        a a1 = new a(bitmatrix);
        Version version = a1.a();
        b ab[] = b.a(a1.b(), version);
        int i = ab.length;
        int j = ab.length;
        int k = 0;
        int l = 0;
        for (; k < j; k++)
        {
            l += ab[k].a();
        }

        byte abyte0[] = new byte[l];
        for (int i1 = 0; i1 < i; i1++)
        {
            b b1 = ab[i1];
            byte abyte1[] = b1.b();
            int j1 = b1.a();
            int k1 = abyte1.length;
            int ai[] = new int[k1];
            for (int l1 = 0; l1 < k1; l1++)
            {
                ai[l1] = 0xff & abyte1[l1];
            }

            int i2 = abyte1.length - j1;
            int j2;
            try
            {
                a.decode(ai, i2);
            }
            catch (ReedSolomonException reedsolomonexception)
            {
                throw ChecksumException.getChecksumInstance();
            }
            for (j2 = 0; j2 < j1; j2++)
            {
                abyte1[j2] = (byte)ai[j2];
            }

            for (int k2 = 0; k2 < j1; k2++)
            {
                abyte0[i1 + k2 * i] = abyte1[k2];
            }

        }

        return c.a(abyte0);
    }

    public final DecoderResult decode(boolean aflag[][])
    {
        int i = aflag.length;
        BitMatrix bitmatrix = new BitMatrix(i);
        for (int j = 0; j < i; j++)
        {
            for (int k = 0; k < i; k++)
            {
                if (aflag[j][k])
                {
                    bitmatrix.set(k, j);
                }
            }

        }

        return decode(bitmatrix);
    }
}
