// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import com.google.zxing.ResultPointCallback;
import com.google.zxing.common.BitArray;
import java.util.Arrays;
import java.util.Map;

// Referenced classes of package com.google.zxing.oned:
//            OneDReader, d, a

public abstract class UPCEANReader extends OneDReader
{

    static final int b[] = {
        1, 1, 1
    };
    static final int c[] = {
        1, 1, 1, 1, 1
    };
    static final int d[][] = {
        {
            3, 2, 1, 1
        }, {
            2, 2, 2, 1
        }, {
            2, 1, 2, 2
        }, {
            1, 4, 1, 1
        }, {
            1, 1, 3, 2
        }, {
            1, 2, 3, 1
        }, {
            1, 1, 1, 4
        }, {
            1, 3, 1, 2
        }, {
            1, 2, 1, 3
        }, {
            3, 1, 1, 2
        }
    };
    static final int e[][];
    private final StringBuilder a = new StringBuilder(20);
    private final d f = new d();
    private final a g = new a();

    protected UPCEANReader()
    {
    }

    static int a(BitArray bitarray, int ai[], int i, int ai1[][])
    {
        recordPattern(bitarray, i, ai);
        int j = 122;
        int k = -1;
        int l = ai1.length;
        int i1 = 0;
        while (i1 < l) 
        {
            int j1 = patternMatchVariance(ai, ai1[i1], 179);
            if (j1 < j)
            {
                k = i1;
            } else
            {
                j1 = j;
            }
            i1++;
            j = j1;
        }
        if (k >= 0)
        {
            return k;
        } else
        {
            throw NotFoundException.getNotFoundInstance();
        }
    }

    static boolean a(CharSequence charsequence)
    {
        int i = charsequence.length();
        if (i != 0)
        {
            int j = i - 2;
            int k = 0;
            for (; j >= 0; j -= 2)
            {
                int k1 = -48 + charsequence.charAt(j);
                if (k1 < 0 || k1 > 9)
                {
                    throw FormatException.getFormatInstance();
                }
                k += k1;
            }

            int l = k * 3;
            for (int i1 = i - 1; i1 >= 0; i1 -= 2)
            {
                int j1 = -48 + charsequence.charAt(i1);
                if (j1 < 0 || j1 > 9)
                {
                    throw FormatException.getFormatInstance();
                }
                l += j1;
            }

            if (l % 10 == 0)
            {
                return true;
            }
        }
        return false;
    }

    static int[] a(BitArray bitarray)
    {
        int[] _tmp = b;
        int ai[] = new int[3];
        int i = 0;
        int ai1[] = null;
        boolean flag = false;
        do
        {
            if (flag)
            {
                break;
            }
            int[] _tmp1 = b;
            Arrays.fill(ai, 0, 3, 0);
            ai1 = a(bitarray, i, false, b, ai);
            int j = ai1[0];
            i = ai1[1];
            int k = j - (i - j);
            if (k >= 0)
            {
                flag = bitarray.isRange(k, j, false);
            }
        } while (true);
        return ai1;
    }

    static int[] a(BitArray bitarray, int i, boolean flag, int ai[])
    {
        return a(bitarray, i, flag, ai, new int[ai.length]);
    }

    private static int[] a(BitArray bitarray, int i, boolean flag, int ai[], int ai1[])
    {
        int j = ai.length;
        int k = bitarray.getSize();
        int l;
        boolean flag1;
        int i1;
        int j1;
        if (flag)
        {
            l = bitarray.getNextUnset(i);
        } else
        {
            l = bitarray.getNextSet(i);
        }
        flag1 = flag;
        i1 = 0;
        j1 = l;
        while (j1 < k) 
        {
            if (flag1 ^ bitarray.get(j1))
            {
                ai1[i1] = 1 + ai1[i1];
            } else
            {
                if (i1 == j - 1)
                {
                    if (patternMatchVariance(ai1, ai, 179) < 122)
                    {
                        return (new int[] {
                            l, j1
                        });
                    }
                    l += ai1[0] + ai1[1];
                    System.arraycopy(ai1, 2, ai1, 0, j - 2);
                    ai1[j - 2] = 0;
                    ai1[j - 1] = 0;
                    i1--;
                } else
                {
                    i1++;
                }
                ai1[i1] = 1;
                if (!flag1)
                {
                    flag1 = true;
                } else
                {
                    flag1 = false;
                }
            }
            j1++;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    abstract BarcodeFormat a();

    boolean checkChecksum(String s)
    {
        return a(s);
    }

    int[] decodeEnd(BitArray bitarray, int i)
    {
        return a(bitarray, i, false, b);
    }

    protected abstract int decodeMiddle(BitArray bitarray, int ai[], StringBuilder stringbuilder);

    public Result decodeRow(int i, BitArray bitarray, Map map)
    {
        return decodeRow(i, bitarray, a(bitarray), map);
    }

    public Result decodeRow(int i, BitArray bitarray, int ai[], Map map)
    {
        ResultPointCallback resultpointcallback;
        StringBuilder stringbuilder;
        int j;
        int ai1[];
        int k;
        int l;
        if (map == null)
        {
            resultpointcallback = null;
        } else
        {
            resultpointcallback = (ResultPointCallback)map.get(DecodeHintType.NEED_RESULT_POINT_CALLBACK);
        }
        if (resultpointcallback != null)
        {
            resultpointcallback.foundPossibleResultPoint(new ResultPoint((float)(ai[0] + ai[1]) / 2.0F, i));
        }
        stringbuilder = a;
        stringbuilder.setLength(0);
        j = decodeMiddle(bitarray, ai, stringbuilder);
        if (resultpointcallback != null)
        {
            resultpointcallback.foundPossibleResultPoint(new ResultPoint(j, i));
        }
        ai1 = decodeEnd(bitarray, j);
        if (resultpointcallback != null)
        {
            resultpointcallback.foundPossibleResultPoint(new ResultPoint((float)(ai1[0] + ai1[1]) / 2.0F, i));
        }
        k = ai1[1];
        l = k + (k - ai1[0]);
        if (l >= bitarray.getSize() || !bitarray.isRange(k, l, false))
        {
            throw NotFoundException.getNotFoundInstance();
        }
        String s = stringbuilder.toString();
        if (!checkChecksum(s))
        {
            throw ChecksumException.getChecksumInstance();
        }
        float f1 = (float)(ai[1] + ai[0]) / 2.0F;
        float f2 = (float)(ai1[1] + ai1[0]) / 2.0F;
        BarcodeFormat barcodeformat = a();
        ResultPoint aresultpoint[] = new ResultPoint[2];
        aresultpoint[0] = new ResultPoint(f1, i);
        aresultpoint[1] = new ResultPoint(f2, i);
        Result result = new Result(s, null, aresultpoint, barcodeformat);
        try
        {
            Result result1 = f.a(i, bitarray, ai1[1]);
            result.putMetadata(ResultMetadataType.UPC_EAN_EXTENSION, result1.getText());
            result.putAllMetadata(result1.getResultMetadata());
            result.addResultPoints(result1.getResultPoints());
        }
        catch (ReaderException readerexception) { }
        if (barcodeformat == BarcodeFormat.EAN_13 || barcodeformat == BarcodeFormat.UPC_A)
        {
            String s1 = g.a(s);
            if (s1 != null)
            {
                result.putMetadata(ResultMetadataType.POSSIBLE_COUNTRY, s1);
            }
        }
        return result;
    }

    static 
    {
        e = new int[20][];
        System.arraycopy(d, 0, e, 0, 10);
        for (int i = 10; i < 20; i++)
        {
            int ai[] = d[i - 10];
            int ai1[] = new int[ai.length];
            for (int j = 0; j < ai.length; j++)
            {
                ai1[j] = ai[-1 + (ai.length - j)];
            }

            e[i] = ai1;
        }

    }
}
