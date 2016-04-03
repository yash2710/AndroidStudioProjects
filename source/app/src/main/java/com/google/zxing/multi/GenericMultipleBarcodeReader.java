// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.multi;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

// Referenced classes of package com.google.zxing.multi:
//            MultipleBarcodeReader

public final class GenericMultipleBarcodeReader
    implements MultipleBarcodeReader
{

    private final Reader a;

    public GenericMultipleBarcodeReader(Reader reader)
    {
        a = reader;
    }

    private void a(BinaryBitmap binarybitmap, Map map, List list, int i, int j, int k)
    {
        int l = j;
_L5:
        if (k <= 4) goto _L2; else goto _L1
_L1:
        return;
_L2:
        boolean flag;
        Result result;
        Iterator iterator;
        ResultPoint aresultpoint[];
        int i1;
        int j1;
        float f;
        float f1;
        int k1;
        int l1;
        int i2;
        int j2;
        int k2;
        int l2;
        BinaryBitmap binarybitmap1;
        int i3;
        int j3;
        int k3;
        int l3;
        ResultPoint resultpoint;
        ResultPoint aresultpoint1[];
        ResultPoint aresultpoint2[];
        int i4;
        Result result1;
        ResultPoint resultpoint1;
        try
        {
            result = a.decode(binarybitmap, map);
        }
        catch (ReaderException readerexception)
        {
            return;
        }
        iterator = list.iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break MISSING_BLOCK_LABEL_544;
            }
        } while (!((Result)iterator.next()).getText().equals(result.getText()));
        flag = true;
_L6:
        if (!flag)
        {
            aresultpoint1 = result.getResultPoints();
            if (aresultpoint1 == null)
            {
                result1 = result;
            } else
            {
                aresultpoint2 = new ResultPoint[aresultpoint1.length];
                for (i4 = 0; i4 < aresultpoint1.length; i4++)
                {
                    resultpoint1 = aresultpoint1[i4];
                    aresultpoint2[i4] = new ResultPoint(resultpoint1.getX() + (float)i, resultpoint1.getY() + (float)l);
                }

                result1 = new Result(result.getText(), result.getRawBytes(), aresultpoint2, result.getBarcodeFormat());
                result1.putAllMetadata(result.getResultMetadata());
            }
            list.add(result1);
        }
        aresultpoint = result.getResultPoints();
        if (aresultpoint == null || aresultpoint.length == 0) goto _L1; else goto _L3
_L3:
        i1 = binarybitmap.getWidth();
        j1 = binarybitmap.getHeight();
        f = i1;
        f1 = j1;
        float f2 = 0.0F;
        float f3 = 0.0F;
        k1 = aresultpoint.length;
        l1 = 0;
        while (l1 < k1) 
        {
            resultpoint = aresultpoint[l1];
            float f4 = resultpoint.getX();
            float f5 = resultpoint.getY();
            if (f4 < f)
            {
                f = f4;
            }
            if (f5 < f1)
            {
                f1 = f5;
            }
            if (f4 <= f2)
            {
                f4 = f2;
            }
            if (f5 <= f3)
            {
                f5 = f3;
            }
            l1++;
            f3 = f5;
            f2 = f4;
        }
        if (f > 100F)
        {
            l3 = (int)f;
            a(binarybitmap.crop(0, 0, l3, j1), map, list, i, l, k + 1);
        }
        if (f1 > 100F)
        {
            k3 = (int)f1;
            a(binarybitmap.crop(0, 0, i1, k3), map, list, i, l, k + 1);
        }
        if (f2 < (float)(i1 - 100))
        {
            k2 = (int)f2;
            l2 = i1 - (int)f2;
            binarybitmap1 = binarybitmap.crop(k2, 0, l2, j1);
            i3 = i + (int)f2;
            j3 = k + 1;
            a(binarybitmap1, map, list, i3, l, j3);
        }
        if (f3 >= (float)(j1 - 100)) goto _L1; else goto _L4
_L4:
        i2 = (int)f3;
        j2 = j1 - (int)f3;
        binarybitmap = binarybitmap.crop(0, i2, i1, j2);
        l += (int)f3;
        k++;
          goto _L5
        flag = false;
          goto _L6
    }

    public final Result[] decodeMultiple(BinaryBitmap binarybitmap)
    {
        return decodeMultiple(binarybitmap, null);
    }

    public final Result[] decodeMultiple(BinaryBitmap binarybitmap, Map map)
    {
        ArrayList arraylist = new ArrayList();
        a(binarybitmap, map, arraylist, 0, 0, 0);
        if (arraylist.isEmpty())
        {
            throw NotFoundException.getNotFoundInstance();
        } else
        {
            return (Result[])arraylist.toArray(new Result[arraylist.size()]);
        }
    }
}
