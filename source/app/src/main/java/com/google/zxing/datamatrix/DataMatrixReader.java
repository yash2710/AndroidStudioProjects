// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.datamatrix;

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
import com.google.zxing.datamatrix.decoder.Decoder;
import com.google.zxing.datamatrix.detector.Detector;
import java.util.Map;

public final class DataMatrixReader
    implements Reader
{

    private static final ResultPoint a[] = new ResultPoint[0];
    private final Decoder b = new Decoder();

    public DataMatrixReader()
    {
    }

    public final Result decode(BinaryBitmap binarybitmap)
    {
        return decode(binarybitmap, null);
    }

    public final Result decode(BinaryBitmap binarybitmap, Map map)
    {
        DecoderResult decoderresult;
        ResultPoint aresultpoint[];
        Result result;
        java.util.List list;
        String s;
        if (map != null && map.containsKey(DecodeHintType.PURE_BARCODE))
        {
            BitMatrix bitmatrix = binarybitmap.getBlackMatrix();
            int ai[] = bitmatrix.getTopLeftOnBit();
            int ai1[] = bitmatrix.getBottomRightOnBit();
            if (ai == null || ai1 == null)
            {
                throw NotFoundException.getNotFoundInstance();
            }
            int i = bitmatrix.getWidth();
            int j = ai[0];
            for (int k = ai[1]; j < i && bitmatrix.get(j, k); j++) { }
            if (j == i)
            {
                throw NotFoundException.getNotFoundInstance();
            }
            int l = j - ai[0];
            if (l == 0)
            {
                throw NotFoundException.getNotFoundInstance();
            }
            int i1 = ai[1];
            int j1 = ai1[1];
            int k1 = ai[0];
            int l1 = (1 + (ai1[0] - k1)) / l;
            int i2 = (1 + (j1 - i1)) / l;
            if (l1 <= 0 || i2 <= 0)
            {
                throw NotFoundException.getNotFoundInstance();
            }
            int j2 = l >> 1;
            int k2 = i1 + j2;
            int l2 = j2 + k1;
            BitMatrix bitmatrix1 = new BitMatrix(l1, i2);
            for (int i3 = 0; i3 < i2; i3++)
            {
                int j3 = k2 + i3 * l;
                for (int k3 = 0; k3 < l1; k3++)
                {
                    if (bitmatrix.get(l2 + k3 * l, j3))
                    {
                        bitmatrix1.set(k3, i3);
                    }
                }

            }

            decoderresult = b.decode(bitmatrix1);
            aresultpoint = a;
        } else
        {
            DetectorResult detectorresult = (new Detector(binarybitmap.getBlackMatrix())).detect();
            decoderresult = b.decode(detectorresult.getBits());
            aresultpoint = detectorresult.getPoints();
        }
        result = new Result(decoderresult.getText(), decoderresult.getRawBytes(), aresultpoint, BarcodeFormat.DATA_MATRIX);
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
    }

    public final void reset()
    {
    }

}
