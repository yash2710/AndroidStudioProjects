// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import java.util.EnumMap;
import java.util.Map;

// Referenced classes of package com.google.zxing.oned:
//            UPCEANReader

final class c
{

    private static final int a[] = {
        24, 20, 18, 17, 12, 6, 3, 10, 9, 5
    };
    private final int b[] = new int[4];
    private final StringBuilder c = new StringBuilder();

    c()
    {
    }

    final Result a(int i, BitArray bitarray, int ai[])
    {
label0:
        {
            StringBuilder stringbuilder = c;
            stringbuilder.setLength(0);
            int ai1[] = b;
            ai1[0] = 0;
            ai1[1] = 0;
            ai1[2] = 0;
            ai1[3] = 0;
            int j = bitarray.getSize();
            int k = ai[1];
            int l = 0;
            int i1 = k;
            int j1 = 0;
            while (j1 < 5 && i1 < j) 
            {
                int k3 = UPCEANReader.a(bitarray, ai1, i1, UPCEANReader.e);
                stringbuilder.append((char)(48 + k3 % 10));
                int l3 = 0;
                int i4;
                int k4;
                for (i4 = i1; l3 < 4; i4 = k4)
                {
                    k4 = i4 + ai1[l3];
                    l3++;
                }

                int k1;
                String s;
                int l1;
                int i2;
                int j2;
                int k2;
                int l2;
                String s1;
                String s2;
                Object obj;
                ResultPoint aresultpoint[];
                Result result;
                String s3;
                int i3;
                String s4;
                int j3;
                String s5;
                int j4;
                if (k3 >= 10)
                {
                    j4 = l | 1 << 4 - j1;
                } else
                {
                    j4 = l;
                }
                if (j1 != 4)
                {
                    i4 = bitarray.getNextUnset(bitarray.getNextSet(i4));
                }
                j1++;
                l = j4;
                i1 = i4;
            }
            if (stringbuilder.length() != 5)
            {
                throw NotFoundException.getNotFoundInstance();
            }
            k1 = 0;
            do
            {
                if (k1 >= 10)
                {
                    break;
                }
                if (l == a[k1])
                {
                    s = stringbuilder.toString();
                    l1 = s.length();
                    i2 = 0;
                    for (j2 = l1 - 2; j2 >= 0; j2 -= 2)
                    {
                        i2 += -48 + s.charAt(j2);
                    }

                    break label0;
                }
                k1++;
            } while (true);
            throw NotFoundException.getNotFoundInstance();
        }
        k2 = i2 * 3;
        for (l2 = l1 - 1; l2 >= 0; l2 -= 2)
        {
            k2 += -48 + s.charAt(l2);
        }

        if ((k2 * 3) % 10 != k1)
        {
            throw NotFoundException.getNotFoundInstance();
        }
        s1 = stringbuilder.toString();
        if (s1.length() == 5) goto _L2; else goto _L1
_L1:
        obj = null;
_L7:
        aresultpoint = new ResultPoint[2];
        aresultpoint[0] = new ResultPoint((float)(ai[0] + ai[1]) / 2.0F, i);
        aresultpoint[1] = new ResultPoint(i1, i);
        result = new Result(s1, null, aresultpoint, BarcodeFormat.UPC_EAN_EXTENSION);
        if (obj != null)
        {
            result.putAllMetadata(((Map) (obj)));
        }
        return result;
_L2:
        s1.charAt(0);
        JVM INSTR lookupswitch 3: default 472
    //                   48: 573
    //                   53: 580
    //                   57: 587;
           goto _L3 _L4 _L5 _L6
_L3:
        s3 = "";
_L8:
        i3 = Integer.parseInt(s1.substring(1));
        s4 = String.valueOf(i3 / 100);
        j3 = i3 % 100;
        if (j3 < 10)
        {
            s5 = (new StringBuilder("0")).append(j3).toString();
        } else
        {
            s5 = String.valueOf(j3);
        }
        s2 = (new StringBuilder()).append(s3).append(s4).append('.').append(s5).toString();
_L9:
        if (s2 == null)
        {
            obj = null;
        } else
        {
            obj = new EnumMap(com/google/zxing/ResultMetadataType);
            ((Map) (obj)).put(ResultMetadataType.SUGGESTED_PRICE, s2);
        }
          goto _L7
_L4:
        s3 = "\243";
          goto _L8
_L5:
        s3 = "$";
          goto _L8
_L6:
        if ("90000".equals(s1))
        {
            s2 = null;
        } else
        if ("99991".equals(s1))
        {
            s2 = "0.00";
        } else
        {
label1:
            {
                if (!"99990".equals(s1))
                {
                    break label1;
                }
                s2 = "Used";
            }
        }
          goto _L9
        s3 = "";
          goto _L8
    }

}
