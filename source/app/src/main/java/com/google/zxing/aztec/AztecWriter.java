// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.aztec;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.Writer;
import com.google.zxing.aztec.encoder.AztecCode;
import com.google.zxing.aztec.encoder.Encoder;
import com.google.zxing.common.BitMatrix;
import java.nio.charset.Charset;
import java.util.Map;

public final class AztecWriter
    implements Writer
{

    private static final Charset a = Charset.forName("ISO-8859-1");

    public AztecWriter()
    {
    }

    private static BitMatrix a(String s, BarcodeFormat barcodeformat, Charset charset, int i)
    {
        if (barcodeformat != BarcodeFormat.AZTEC)
        {
            throw new IllegalArgumentException((new StringBuilder("Can only encode AZTEC, but got ")).append(barcodeformat).toString());
        } else
        {
            return Encoder.encode(s.getBytes(charset), i).getMatrix();
        }
    }

    public final BitMatrix encode(String s, BarcodeFormat barcodeformat, int i, int j)
    {
        return a(s, barcodeformat, a, 33);
    }

    public final BitMatrix encode(String s, BarcodeFormat barcodeformat, int i, int j, Map map)
    {
        String s1 = (String)map.get(EncodeHintType.CHARACTER_SET);
        Number number = (Number)map.get(EncodeHintType.ERROR_CORRECTION);
        Charset charset;
        int k;
        if (s1 == null)
        {
            charset = a;
        } else
        {
            charset = Charset.forName(s1);
        }
        if (number == null)
        {
            k = 33;
        } else
        {
            k = number.intValue();
        }
        return a(s, barcodeformat, charset, k);
    }

}
