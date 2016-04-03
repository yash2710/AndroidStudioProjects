// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.qrcode.decoder;

import com.google.zxing.ChecksumException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.reedsolomon.GenericGF;
import com.google.zxing.common.reedsolomon.ReedSolomonDecoder;
import com.google.zxing.common.reedsolomon.ReedSolomonException;
import java.util.Map;

// Referenced classes of package com.google.zxing.qrcode.decoder:
//            a, m, b, l

public final class Decoder
{

    private final ReedSolomonDecoder a;

    public Decoder()
    {
        a = new ReedSolomonDecoder(GenericGF.QR_CODE_FIELD_256);
    }

    public final DecoderResult decode(BitMatrix bitmatrix)
    {
        return decode(bitmatrix, null);
    }

    public final DecoderResult decode(BitMatrix bitmatrix, Map map)
    {
        a a1 = new a(bitmatrix);
        Version version = a1.b();
        ErrorCorrectionLevel errorcorrectionlevel = a1.a().a();
        b ab[] = b.a(a1.c(), version, errorcorrectionlevel);
        int i = 0;
        int j = ab.length;
        for (int k = 0; k < j; k++)
        {
            i += ab[k].a();
        }

        byte abyte0[] = new byte[i];
        int i1 = 0;
        int j1 = ab.length;
        for (int k1 = 0; k1 < j1; k1++)
        {
            b b1 = ab[k1];
            byte abyte1[] = b1.b();
            int l1 = b1.a();
            int i2 = abyte1.length;
            int ai[] = new int[i2];
            for (int j2 = 0; j2 < i2; j2++)
            {
                ai[j2] = 0xff & abyte1[j2];
            }

            int k2 = abyte1.length - l1;
            int l2;
            try
            {
                a.decode(ai, k2);
            }
            catch (ReedSolomonException reedsolomonexception)
            {
                throw ChecksumException.getChecksumInstance();
            }
            for (l2 = 0; l2 < l1; l2++)
            {
                abyte1[l2] = (byte)ai[l2];
            }

            for (int i3 = 0; i3 < l1;)
            {
                int j3 = i1 + 1;
                abyte0[i1] = abyte1[i3];
                i3++;
                i1 = j3;
            }

        }

        return l.a(abyte0, version, errorcorrectionlevel, map);
    }

    public final DecoderResult decode(boolean aflag[][])
    {
        return decode(aflag, null);
    }

    public final DecoderResult decode(boolean aflag[][], Map map)
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

        return decode(bitmatrix, map);
    }
}
