// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.pdf417;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.multi.MultipleBarcodeReader;
import com.google.zxing.pdf417.decoder.PDF417ScanningDecoder;
import com.google.zxing.pdf417.detector.Detector;
import com.google.zxing.pdf417.detector.PDF417DetectorResult;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

// Referenced classes of package com.google.zxing.pdf417:
//            PDF417ResultMetadata

public final class PDF417Reader
    implements Reader, MultipleBarcodeReader
{

    public PDF417Reader()
    {
    }

    private static int a(ResultPoint resultpoint, ResultPoint resultpoint1)
    {
        if (resultpoint == null || resultpoint1 == null)
        {
            return 0;
        } else
        {
            return (int)Math.abs(resultpoint.getX() - resultpoint1.getX());
        }
    }

    private static Result[] a(BinaryBitmap binarybitmap, Map map, boolean flag)
    {
        ArrayList arraylist = new ArrayList();
        PDF417DetectorResult pdf417detectorresult = Detector.detect(binarybitmap, map, flag);
        Result result;
        for (Iterator iterator = pdf417detectorresult.getPoints().iterator(); iterator.hasNext(); arraylist.add(result))
        {
            ResultPoint aresultpoint[] = (ResultPoint[])iterator.next();
            DecoderResult decoderresult = PDF417ScanningDecoder.decode(pdf417detectorresult.getBits(), aresultpoint[4], aresultpoint[5], aresultpoint[6], aresultpoint[7], Math.min(Math.min(b(aresultpoint[0], aresultpoint[4]), (17 * b(aresultpoint[6], aresultpoint[2])) / 18), Math.min(b(aresultpoint[1], aresultpoint[5]), (17 * b(aresultpoint[7], aresultpoint[3])) / 18)), Math.max(Math.max(a(aresultpoint[0], aresultpoint[4]), (17 * a(aresultpoint[6], aresultpoint[2])) / 18), Math.max(a(aresultpoint[1], aresultpoint[5]), (17 * a(aresultpoint[7], aresultpoint[3])) / 18)));
            if (decoderresult == null)
            {
                throw NotFoundException.getNotFoundInstance();
            }
            result = new Result(decoderresult.getText(), decoderresult.getRawBytes(), aresultpoint, BarcodeFormat.PDF_417);
            result.putMetadata(ResultMetadataType.ERROR_CORRECTION_LEVEL, decoderresult.getECLevel());
            PDF417ResultMetadata pdf417resultmetadata = (PDF417ResultMetadata)decoderresult.getOther();
            if (pdf417resultmetadata != null)
            {
                result.putMetadata(ResultMetadataType.PDF417_EXTRA_METADATA, pdf417resultmetadata);
            }
        }

        return (Result[])arraylist.toArray(new Result[arraylist.size()]);
    }

    private static int b(ResultPoint resultpoint, ResultPoint resultpoint1)
    {
        if (resultpoint == null || resultpoint1 == null)
        {
            return 0x7fffffff;
        } else
        {
            return (int)Math.abs(resultpoint.getX() - resultpoint1.getX());
        }
    }

    public final Result decode(BinaryBitmap binarybitmap)
    {
        return decode(binarybitmap, null);
    }

    public final Result decode(BinaryBitmap binarybitmap, Map map)
    {
        Result aresult[] = a(binarybitmap, map, false);
        if (aresult == null || aresult.length == 0 || aresult[0] == null)
        {
            throw NotFoundException.getNotFoundInstance();
        } else
        {
            return aresult[0];
        }
    }

    public final Result[] decodeMultiple(BinaryBitmap binarybitmap)
    {
        return decodeMultiple(binarybitmap, null);
    }

    public final Result[] decodeMultiple(BinaryBitmap binarybitmap, Map map)
    {
        Result aresult[];
        try
        {
            aresult = a(binarybitmap, map, true);
        }
        catch (FormatException formatexception)
        {
            throw NotFoundException.getNotFoundInstance();
        }
        catch (ChecksumException checksumexception)
        {
            throw NotFoundException.getNotFoundInstance();
        }
        return aresult;
    }

    public final void reset()
    {
    }
}
