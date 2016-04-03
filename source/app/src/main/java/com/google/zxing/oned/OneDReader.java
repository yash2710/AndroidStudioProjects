// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.oned;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

public abstract class OneDReader
    implements Reader
{

    protected static final int INTEGER_MATH_SHIFT = 8;
    protected static final int PATTERN_MATCH_RESULT_SCALE_FACTOR = 256;

    public OneDReader()
    {
    }

    private Result a(BinaryBitmap binarybitmap, Map map)
    {
        int j1;
        Object obj;
        int i2;
        Object obj1;
        int i = binarybitmap.getWidth();
        int j = binarybitmap.getHeight();
        BitArray bitarray = new BitArray(i);
        int k = j >> 1;
        boolean flag;
        byte byte0;
        int l;
        int i1;
        BitArray bitarray1;
        int l1;
        BitArray bitarray2;
        Result result;
        ResultPoint aresultpoint[];
        if (map != null && map.containsKey(DecodeHintType.TRY_HARDER))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            byte0 = 8;
        } else
        {
            byte0 = 5;
        }
        l = Math.max(1, j >> byte0);
        if (flag)
        {
            i1 = j;
        } else
        {
            i1 = 15;
        }
        j1 = 0;
        bitarray1 = bitarray;
        obj = map;
_L4:
        if (j1 >= i1) goto _L2; else goto _L1
_L1:
        int k1 = j1 + 1 >> 1;
        boolean flag1;
        if ((j1 & 1) == 0)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (!flag1)
        {
            k1 = -k1;
        }
        l1 = k + k1 * l;
        if (l1 < 0 || l1 >= j)
        {
            break; /* Loop/switch isn't completed */
        }
        bitarray2 = binarybitmap.getBlackRow(l1, bitarray1);
        bitarray1 = bitarray2;
        i2 = 0;
_L3:
        if (i2 >= 2)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (i2 != 1)
        {
            break MISSING_BLOCK_LABEL_384;
        }
        bitarray1.reverse();
        if (obj == null || !((Map) (obj)).containsKey(DecodeHintType.NEED_RESULT_POINT_CALLBACK))
        {
            break MISSING_BLOCK_LABEL_384;
        }
        obj1 = new EnumMap(com/google/zxing/DecodeHintType);
        ((Map) (obj1)).putAll(((Map) (obj)));
        ((Map) (obj1)).remove(DecodeHintType.NEED_RESULT_POINT_CALLBACK);
_L5:
        result = decodeRow(l1, bitarray1, ((Map) (obj1)));
        if (i2 != 1)
        {
            break MISSING_BLOCK_LABEL_324;
        }
        result.putMetadata(ResultMetadataType.ORIENTATION, Integer.valueOf(180));
        aresultpoint = result.getResultPoints();
        if (aresultpoint == null)
        {
            break MISSING_BLOCK_LABEL_324;
        }
        aresultpoint[0] = new ResultPoint((float)i - aresultpoint[0].getX() - 1.0F, aresultpoint[0].getY());
        aresultpoint[1] = new ResultPoint((float)i - aresultpoint[1].getX() - 1.0F, aresultpoint[1].getY());
        return result;
        ReaderException readerexception;
        readerexception;
        i2++;
        obj = obj1;
          goto _L3
        NotFoundException notfoundexception;
        notfoundexception;
        j1++;
          goto _L4
_L2:
        throw NotFoundException.getNotFoundInstance();
        obj1 = obj;
          goto _L5
    }

    protected static int patternMatchVariance(int ai[], int ai1[], int i)
    {
        int j;
        int l;
        int i1;
        j = ai.length;
        int k = 0;
        l = 0;
        i1 = 0;
        for (; k < j; k++)
        {
            i1 += ai[k];
            l += ai1[k];
        }

        if (i1 >= l) goto _L2; else goto _L1
_L1:
        return 0x7fffffff;
_L2:
        int i2;
        int j1 = (i1 << 8) / l;
        int k1 = i * j1 >> 8;
        int l1 = 0;
        i2 = 0;
        do
        {
label0:
            {
                if (l1 >= j)
                {
                    break label0;
                }
                int j2 = ai[l1] << 8;
                int k2 = j1 * ai1[l1];
                int l2;
                if (j2 > k2)
                {
                    l2 = j2 - k2;
                } else
                {
                    l2 = k2 - j2;
                }
                if (l2 > k1)
                {
                    continue; /* Loop/switch isn't completed */
                }
                i2 += l2;
                l1++;
            }
        } while (true);
        if (true) goto _L1; else goto _L3
_L3:
        return i2 / i1;
    }

    protected static void recordPattern(BitArray bitarray, int i, int ai[])
    {
        int j;
        int k;
        int i1;
label0:
        {
            j = ai.length;
            Arrays.fill(ai, 0, j, 0);
            k = bitarray.getSize();
            if (i >= k)
            {
                throw NotFoundException.getNotFoundInstance();
            }
            boolean flag;
            boolean flag1;
            int l;
            if (!bitarray.get(i))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            flag1 = flag;
            l = 0;
            while (i < k) 
            {
                boolean flag3;
                if (flag1 ^ bitarray.get(i))
                {
                    ai[l] = 1 + ai[l];
                    flag3 = flag1;
                } else
                {
                    i1 = l + 1;
                    if (i1 == j)
                    {
                        break label0;
                    }
                    ai[i1] = 1;
                    boolean flag2;
                    int j1;
                    if (!flag1)
                    {
                        flag2 = true;
                    } else
                    {
                        flag2 = false;
                    }
                    j1 = i1;
                    flag3 = flag2;
                    l = j1;
                }
                i++;
                flag1 = flag3;
            }
            i1 = l;
        }
        if (i1 != j && (i1 != j - 1 || i != k))
        {
            throw NotFoundException.getNotFoundInstance();
        } else
        {
            return;
        }
    }

    protected static void recordPatternInReverse(BitArray bitarray, int i, int ai[])
    {
        int j = ai.length;
        boolean flag = bitarray.get(i);
        do
        {
            if (i <= 0 || j < 0)
            {
                break;
            }
            i--;
            if (bitarray.get(i) != flag)
            {
                j--;
                if (!flag)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
            }
        } while (true);
        if (j >= 0)
        {
            throw NotFoundException.getNotFoundInstance();
        } else
        {
            recordPattern(bitarray, i + 1, ai);
            return;
        }
    }

    public Result decode(BinaryBitmap binarybitmap)
    {
        return decode(binarybitmap, null);
    }

    public Result decode(BinaryBitmap binarybitmap, Map map)
    {
        Result result1;
        try
        {
            result1 = a(binarybitmap, map);
        }
        catch (NotFoundException notfoundexception)
        {
            boolean flag;
            if (map != null && map.containsKey(DecodeHintType.TRY_HARDER))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag && binarybitmap.isRotateSupported())
            {
                BinaryBitmap binarybitmap1 = binarybitmap.rotateCounterClockwise();
                Result result = a(binarybitmap1, map);
                Map map1 = result.getResultMetadata();
                int i = 270;
                if (map1 != null && map1.containsKey(ResultMetadataType.ORIENTATION))
                {
                    i = (270 + ((Integer)map1.get(ResultMetadataType.ORIENTATION)).intValue()) % 360;
                }
                result.putMetadata(ResultMetadataType.ORIENTATION, Integer.valueOf(i));
                ResultPoint aresultpoint[] = result.getResultPoints();
                if (aresultpoint != null)
                {
                    int j = binarybitmap1.getHeight();
                    for (int k = 0; k < aresultpoint.length; k++)
                    {
                        aresultpoint[k] = new ResultPoint((float)j - aresultpoint[k].getY() - 1.0F, aresultpoint[k].getX());
                    }

                }
                return result;
            } else
            {
                throw notfoundexception;
            }
        }
        return result1;
    }

    public abstract Result decodeRow(int i, BitArray bitarray, Map map);

    public void reset()
    {
    }
}
