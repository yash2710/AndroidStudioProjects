// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing;

import com.google.zxing.aztec.AztecWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.datamatrix.DataMatrixWriter;
import com.google.zxing.oned.CodaBarWriter;
import com.google.zxing.oned.Code128Writer;
import com.google.zxing.oned.Code39Writer;
import com.google.zxing.oned.EAN13Writer;
import com.google.zxing.oned.EAN8Writer;
import com.google.zxing.oned.ITFWriter;
import com.google.zxing.oned.UPCAWriter;
import com.google.zxing.pdf417.PDF417Writer;
import com.google.zxing.qrcode.QRCodeWriter;
import java.util.Map;

// Referenced classes of package com.google.zxing:
//            Writer, a, BarcodeFormat

public final class MultiFormatWriter
    implements Writer
{

    public MultiFormatWriter()
    {
    }

    public final BitMatrix encode(String s, BarcodeFormat barcodeformat, int i, int j)
    {
        return encode(s, barcodeformat, i, j, null);
    }

    public final BitMatrix encode(String s, BarcodeFormat barcodeformat, int i, int j, Map map)
    {
        a.a[barcodeformat.ordinal()];
        JVM INSTR tableswitch 1 11: default 68
    //                   1 92
    //                   2 116
    //                   3 128
    //                   4 140
    //                   5 152
    //                   6 164
    //                   7 176
    //                   8 188
    //                   9 200
    //                   10 212
    //                   11 224;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12
_L1:
        throw new IllegalArgumentException((new StringBuilder("No encoder available for format ")).append(barcodeformat).toString());
_L2:
        Object obj = new EAN8Writer();
_L14:
        return ((Writer) (obj)).encode(s, barcodeformat, i, j, map);
_L3:
        obj = new EAN13Writer();
        continue; /* Loop/switch isn't completed */
_L4:
        obj = new UPCAWriter();
        continue; /* Loop/switch isn't completed */
_L5:
        obj = new QRCodeWriter();
        continue; /* Loop/switch isn't completed */
_L6:
        obj = new Code39Writer();
        continue; /* Loop/switch isn't completed */
_L7:
        obj = new Code128Writer();
        continue; /* Loop/switch isn't completed */
_L8:
        obj = new ITFWriter();
        continue; /* Loop/switch isn't completed */
_L9:
        obj = new PDF417Writer();
        continue; /* Loop/switch isn't completed */
_L10:
        obj = new CodaBarWriter();
        continue; /* Loop/switch isn't completed */
_L11:
        obj = new DataMatrixWriter();
        continue; /* Loop/switch isn't completed */
_L12:
        obj = new AztecWriter();
        if (true) goto _L14; else goto _L13
_L13:
    }
}
