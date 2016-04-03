// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.Writer;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.encoder.ByteMatrix;
import com.google.zxing.qrcode.encoder.Encoder;
import com.google.zxing.qrcode.encoder.QRCode;
import java.util.Map;

public final class QRCodeWriter
    implements Writer
{

    public QRCodeWriter()
    {
    }

    private static BitMatrix a(QRCode qrcode, int i, int j, int k)
    {
        ByteMatrix bytematrix = qrcode.getMatrix();
        if (bytematrix == null)
        {
            throw new IllegalStateException();
        }
        int l = bytematrix.getWidth();
        int i1 = bytematrix.getHeight();
        int j1 = l + (k << 1);
        int k1 = i1 + (k << 1);
        int l1 = Math.max(i, j1);
        int i2 = Math.max(j, k1);
        int j2 = Math.min(l1 / j1, i2 / k1);
        int k2 = (l1 - l * j2) / 2;
        int l2 = (i2 - i1 * j2) / 2;
        BitMatrix bitmatrix = new BitMatrix(l1, i2);
        int i3 = l2;
        int i4;
        for (int j3 = 0; j3 < i1; j3 = i4)
        {
            int k3 = 0;
            for (int l3 = k2; k3 < l; l3 += j2)
            {
                if (bytematrix.get(k3, j3) == 1)
                {
                    bitmatrix.setRegion(l3, i3, j2, j2);
                }
                k3++;
            }

            i4 = j3 + 1;
            i3 += j2;
        }

        return bitmatrix;
    }

    public final BitMatrix encode(String s, BarcodeFormat barcodeformat, int i, int j)
    {
        return encode(s, barcodeformat, i, j, null);
    }

    public final BitMatrix encode(String s, BarcodeFormat barcodeformat, int i, int j, Map map)
    {
        ErrorCorrectionLevel errorcorrectionlevel;
        if (s.length() == 0)
        {
            throw new IllegalArgumentException("Found empty contents");
        }
        if (barcodeformat != BarcodeFormat.QR_CODE)
        {
            throw new IllegalArgumentException((new StringBuilder("Can only encode QR_CODE, but got ")).append(barcodeformat).toString());
        }
        if (i < 0 || j < 0)
        {
            throw new IllegalArgumentException((new StringBuilder("Requested dimensions are too small: ")).append(i).append('x').append(j).toString());
        }
        errorcorrectionlevel = ErrorCorrectionLevel.L;
        if (map == null) goto _L2; else goto _L1
_L1:
        Integer integer;
        ErrorCorrectionLevel errorcorrectionlevel1 = (ErrorCorrectionLevel)map.get(EncodeHintType.ERROR_CORRECTION);
        if (errorcorrectionlevel1 != null)
        {
            errorcorrectionlevel = errorcorrectionlevel1;
        }
        integer = (Integer)map.get(EncodeHintType.MARGIN);
        if (integer == null) goto _L2; else goto _L3
_L3:
        int k = integer.intValue();
_L5:
        return a(Encoder.encode(s, errorcorrectionlevel, map), i, j, k);
_L2:
        k = 4;
        if (true) goto _L5; else goto _L4
_L4:
    }
}
