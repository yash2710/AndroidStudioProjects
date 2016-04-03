// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.pdf417;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.Writer;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.pdf417.encoder.BarcodeMatrix;
import com.google.zxing.pdf417.encoder.Compaction;
import com.google.zxing.pdf417.encoder.Dimensions;
import com.google.zxing.pdf417.encoder.PDF417;
import java.lang.reflect.Array;
import java.util.Map;

public final class PDF417Writer
    implements Writer
{

    public PDF417Writer()
    {
    }

    private static BitMatrix a(byte abyte0[][])
    {
        BitMatrix bitmatrix = new BitMatrix(60 + abyte0[0].length, 60 + abyte0.length);
        bitmatrix.clear();
        int i = -30 + bitmatrix.getHeight();
        for (int j = 0; j < abyte0.length;)
        {
            for (int k = 0; k < abyte0[0].length; k++)
            {
                if (abyte0[j][k] == 1)
                {
                    bitmatrix.set(k + 30, i);
                }
            }

            j++;
            i--;
        }

        return bitmatrix;
    }

    private static byte[][] b(byte abyte0[][])
    {
        int ai[] = {
            abyte0[0].length, abyte0.length
        };
        byte abyte1[][] = (byte[][])Array.newInstance(Byte.TYPE, ai);
        for (int i = 0; i < abyte0.length; i++)
        {
            int j = -1 + (abyte0.length - i);
            for (int k = 0; k < abyte0[0].length; k++)
            {
                abyte1[k][j] = abyte0[i][k];
            }

        }

        return abyte1;
    }

    public final BitMatrix encode(String s, BarcodeFormat barcodeformat, int i, int j)
    {
        return encode(s, barcodeformat, i, j, null);
    }

    public final BitMatrix encode(String s, BarcodeFormat barcodeformat, int i, int j, Map map)
    {
        if (barcodeformat != BarcodeFormat.PDF_417)
        {
            throw new IllegalArgumentException((new StringBuilder("Can only encode PDF_417, but got ")).append(barcodeformat).toString());
        }
        PDF417 pdf417 = new PDF417();
        if (map != null)
        {
            if (map.containsKey(EncodeHintType.PDF417_COMPACT))
            {
                pdf417.setCompact(((Boolean)map.get(EncodeHintType.PDF417_COMPACT)).booleanValue());
            }
            if (map.containsKey(EncodeHintType.PDF417_COMPACTION))
            {
                pdf417.setCompaction((Compaction)map.get(EncodeHintType.PDF417_COMPACTION));
            }
            if (map.containsKey(EncodeHintType.PDF417_DIMENSIONS))
            {
                Dimensions dimensions = (Dimensions)map.get(EncodeHintType.PDF417_DIMENSIONS);
                pdf417.setDimensions(dimensions.getMaxCols(), dimensions.getMinCols(), dimensions.getMaxRows(), dimensions.getMinRows());
            }
        }
        pdf417.generateBarcodeLogic(s, 2);
        byte abyte0[][] = pdf417.getBarcodeMatrix().getScaledMatrix(2, 8);
        boolean flag;
        boolean flag1;
        boolean flag2;
        int k;
        int l;
        if (j > i)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (abyte0[0].length < abyte0.length)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (flag ^ flag1)
        {
            abyte0 = b(abyte0);
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        k = i / abyte0[0].length;
        l = j / abyte0.length;
        if (k >= l)
        {
            k = l;
        }
        if (k > 1)
        {
            byte abyte1[][] = pdf417.getBarcodeMatrix().getScaledMatrix(k << 1, k << 2 << 1);
            byte abyte2[][];
            if (flag2)
            {
                abyte2 = b(abyte1);
            } else
            {
                abyte2 = abyte1;
            }
            return a(abyte2);
        } else
        {
            return a(abyte0);
        }
    }
}
