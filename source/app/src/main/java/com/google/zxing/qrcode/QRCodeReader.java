// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.DetectorResult;
import com.google.zxing.qrcode.decoder.Decoder;
import com.google.zxing.qrcode.detector.Detector;
import java.util.Map;

public class QRCodeReader
    implements Reader
{

    private static final ResultPoint a[] = new ResultPoint[0];
    private final Decoder b = new Decoder();

    public QRCodeReader()
    {
    }

    public Result decode(BinaryBitmap binarybitmap)
    {
        return decode(binarybitmap, null);
    }

    public final Result decode(BinaryBitmap binarybitmap, Map map)
    {
        if (map == null || !map.containsKey(DecodeHintType.PURE_BARCODE)) goto _L2; else goto _L1
_L1:
        BitMatrix bitmatrix;
        int ai[];
        int ai1[];
        int i;
        int j;
        boolean flag;
        int i1;
        int j1;
        int k1;
        bitmatrix = binarybitmap.getBlackMatrix();
        ai = bitmatrix.getTopLeftOnBit();
        ai1 = bitmatrix.getBottomRightOnBit();
        if (ai == null || ai1 == null)
        {
            throw NotFoundException.getNotFoundInstance();
        }
        i = bitmatrix.getHeight();
        j = bitmatrix.getWidth();
        int k = ai[0];
        int l = ai[1];
        flag = true;
        i1 = l;
        j1 = k;
        k1 = 0;
_L4:
        boolean flag1;
        if (j1 >= j || i1 >= i)
        {
            break; /* Loop/switch isn't completed */
        }
        if (flag == bitmatrix.get(j1, i1))
        {
            break MISSING_BLOCK_LABEL_628;
        }
        int l5 = k1 + 1;
        if (l5 == 5)
        {
            break; /* Loop/switch isn't completed */
        }
        boolean flag2;
        if (!flag)
        {
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        flag1 = flag2;
        k1 = l5;
_L5:
        j1++;
        i1++;
        flag = flag1;
        if (true) goto _L4; else goto _L3
_L3:
        if (j1 == j || i1 == i)
        {
            throw NotFoundException.getNotFoundInstance();
        }
        float f = (float)(j1 - ai[0]) / 7F;
        int l1 = ai[1];
        int i2 = ai1[1];
        int j2 = ai[0];
        int k2 = ai1[0];
        if (j2 >= k2 || l1 >= i2)
        {
            throw NotFoundException.getNotFoundInstance();
        }
        if (i2 - l1 != k2 - j2)
        {
            k2 = j2 + (i2 - l1);
        }
        int l2 = Math.round((float)(1 + (k2 - j2)) / f);
        int i3 = Math.round((float)(1 + (i2 - l1)) / f);
        if (l2 <= 0 || i3 <= 0)
        {
            throw NotFoundException.getNotFoundInstance();
        }
        if (i3 != l2)
        {
            throw NotFoundException.getNotFoundInstance();
        }
        int j3 = (int)(f / 2.0F);
        int k3 = l1 + j3;
        int l3 = j2 + j3;
        int i4 = (l3 + (int)(f * (float)(l2 - 1))) - (k2 - 1);
        DetectorResult detectorresult;
        DecoderResult decoderresult;
        ResultPoint aresultpoint[];
        Result result;
        java.util.List list;
        String s;
        int j4;
        int k4;
        int l4;
        BitMatrix bitmatrix1;
        if (i4 > 0)
        {
            j4 = l3 - i4;
        } else
        {
            j4 = l3;
        }
        k4 = (k3 + (int)(f * (float)(i3 - 1))) - (i2 - 1);
        if (k4 > 0)
        {
            l4 = k3 - k4;
        } else
        {
            l4 = k3;
        }
        bitmatrix1 = new BitMatrix(l2, i3);
        for (int i5 = 0; i5 < i3; i5++)
        {
            int j5 = l4 + (int)(f * (float)i5);
            for (int k5 = 0; k5 < l2; k5++)
            {
                if (bitmatrix.get(j4 + (int)(f * (float)k5), j5))
                {
                    bitmatrix1.set(k5, i5);
                }
            }

        }

        decoderresult = b.decode(bitmatrix1, map);
        aresultpoint = a;
        result = new Result(decoderresult.getText(), decoderresult.getRawBytes(), aresultpoint, BarcodeFormat.QR_CODE);
        list = decoderresult.getByteSegments();
        if (list != null)
        {
            result.putMetadata(ResultMetadataType.BYTE_SEGMENTS, list);
        }
        s = decoderresult.getECLevel();
        if (s != null)
        {
            result.putMetadata(ResultMetadataType.ERROR_CORRECTION_LEVEL, s);
        }
        return result;
_L2:
        detectorresult = (new Detector(binarybitmap.getBlackMatrix())).detect(map);
        decoderresult = b.decode(detectorresult.getBits(), map);
        aresultpoint = detectorresult.getPoints();
        if (true)
        {
            break MISSING_BLOCK_LABEL_504;
        }
        flag1 = flag;
          goto _L5
    }

    protected final Decoder getDecoder()
    {
        return b;
    }

    public void reset()
    {
    }

}
