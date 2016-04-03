// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// Referenced classes of package com.google.zxing.oned:
//            OneDReader

public final class Code128Reader extends OneDReader
{

    static final int a[][] = {
        {
            2, 1, 2, 2, 2, 2
        }, {
            2, 2, 2, 1, 2, 2
        }, {
            2, 2, 2, 2, 2, 1
        }, {
            1, 2, 1, 2, 2, 3
        }, {
            1, 2, 1, 3, 2, 2
        }, {
            1, 3, 1, 2, 2, 2
        }, {
            1, 2, 2, 2, 1, 3
        }, {
            1, 2, 2, 3, 1, 2
        }, {
            1, 3, 2, 2, 1, 2
        }, {
            2, 2, 1, 2, 1, 3
        }, {
            2, 2, 1, 3, 1, 2
        }, {
            2, 3, 1, 2, 1, 2
        }, {
            1, 1, 2, 2, 3, 2
        }, {
            1, 2, 2, 1, 3, 2
        }, {
            1, 2, 2, 2, 3, 1
        }, {
            1, 1, 3, 2, 2, 2
        }, {
            1, 2, 3, 1, 2, 2
        }, {
            1, 2, 3, 2, 2, 1
        }, {
            2, 2, 3, 2, 1, 1
        }, {
            2, 2, 1, 1, 3, 2
        }, {
            2, 2, 1, 2, 3, 1
        }, {
            2, 1, 3, 2, 1, 2
        }, {
            2, 2, 3, 1, 1, 2
        }, {
            3, 1, 2, 1, 3, 1
        }, {
            3, 1, 1, 2, 2, 2
        }, {
            3, 2, 1, 1, 2, 2
        }, {
            3, 2, 1, 2, 2, 1
        }, {
            3, 1, 2, 2, 1, 2
        }, {
            3, 2, 2, 1, 1, 2
        }, {
            3, 2, 2, 2, 1, 1
        }, {
            2, 1, 2, 1, 2, 3
        }, {
            2, 1, 2, 3, 2, 1
        }, {
            2, 3, 2, 1, 2, 1
        }, {
            1, 1, 1, 3, 2, 3
        }, {
            1, 3, 1, 1, 2, 3
        }, {
            1, 3, 1, 3, 2, 1
        }, {
            1, 1, 2, 3, 1, 3
        }, {
            1, 3, 2, 1, 1, 3
        }, {
            1, 3, 2, 3, 1, 1
        }, {
            2, 1, 1, 3, 1, 3
        }, {
            2, 3, 1, 1, 1, 3
        }, {
            2, 3, 1, 3, 1, 1
        }, {
            1, 1, 2, 1, 3, 3
        }, {
            1, 1, 2, 3, 3, 1
        }, {
            1, 3, 2, 1, 3, 1
        }, {
            1, 1, 3, 1, 2, 3
        }, {
            1, 1, 3, 3, 2, 1
        }, {
            1, 3, 3, 1, 2, 1
        }, {
            3, 1, 3, 1, 2, 1
        }, {
            2, 1, 1, 3, 3, 1
        }, {
            2, 3, 1, 1, 3, 1
        }, {
            2, 1, 3, 1, 1, 3
        }, {
            2, 1, 3, 3, 1, 1
        }, {
            2, 1, 3, 1, 3, 1
        }, {
            3, 1, 1, 1, 2, 3
        }, {
            3, 1, 1, 3, 2, 1
        }, {
            3, 3, 1, 1, 2, 1
        }, {
            3, 1, 2, 1, 1, 3
        }, {
            3, 1, 2, 3, 1, 1
        }, {
            3, 3, 2, 1, 1, 1
        }, {
            3, 1, 4, 1, 1, 1
        }, {
            2, 2, 1, 4, 1, 1
        }, {
            4, 3, 1, 1, 1, 1
        }, {
            1, 1, 1, 2, 2, 4
        }, {
            1, 1, 1, 4, 2, 2
        }, {
            1, 2, 1, 1, 2, 4
        }, {
            1, 2, 1, 4, 2, 1
        }, {
            1, 4, 1, 1, 2, 2
        }, {
            1, 4, 1, 2, 2, 1
        }, {
            1, 1, 2, 2, 1, 4
        }, {
            1, 1, 2, 4, 1, 2
        }, {
            1, 2, 2, 1, 1, 4
        }, {
            1, 2, 2, 4, 1, 1
        }, {
            1, 4, 2, 1, 1, 2
        }, {
            1, 4, 2, 2, 1, 1
        }, {
            2, 4, 1, 2, 1, 1
        }, {
            2, 2, 1, 1, 1, 4
        }, {
            4, 1, 3, 1, 1, 1
        }, {
            2, 4, 1, 1, 1, 2
        }, {
            1, 3, 4, 1, 1, 1
        }, {
            1, 1, 1, 2, 4, 2
        }, {
            1, 2, 1, 1, 4, 2
        }, {
            1, 2, 1, 2, 4, 1
        }, {
            1, 1, 4, 2, 1, 2
        }, {
            1, 2, 4, 1, 1, 2
        }, {
            1, 2, 4, 2, 1, 1
        }, {
            4, 1, 1, 2, 1, 2
        }, {
            4, 2, 1, 1, 1, 2
        }, {
            4, 2, 1, 2, 1, 1
        }, {
            2, 1, 2, 1, 4, 1
        }, {
            2, 1, 4, 1, 2, 1
        }, {
            4, 1, 2, 1, 2, 1
        }, {
            1, 1, 1, 1, 4, 3
        }, {
            1, 1, 1, 3, 4, 1
        }, {
            1, 3, 1, 1, 4, 1
        }, {
            1, 1, 4, 1, 1, 3
        }, {
            1, 1, 4, 3, 1, 1
        }, {
            4, 1, 1, 1, 1, 3
        }, {
            4, 1, 1, 3, 1, 1
        }, {
            1, 1, 3, 1, 4, 1
        }, {
            1, 1, 4, 1, 3, 1
        }, {
            3, 1, 1, 1, 4, 1
        }, {
            4, 1, 1, 1, 3, 1
        }, {
            2, 1, 1, 4, 1, 2
        }, {
            2, 1, 1, 2, 1, 4
        }, {
            2, 1, 1, 2, 3, 2
        }, {
            2, 3, 3, 1, 1, 1, 2
        }
    };

    public Code128Reader()
    {
    }

    private static int a(BitArray bitarray, int ai[], int i)
    {
        recordPattern(bitarray, i, ai);
        int j = 64;
        int k = -1;
        int l = 0;
        do
        {
            int[][] _tmp = a;
            if (l >= 107)
            {
                break;
            }
            int i1 = patternMatchVariance(ai, a[l], 179);
            if (i1 < j)
            {
                k = l;
                j = i1;
            }
            l++;
        } while (true);
        if (k >= 0)
        {
            return k;
        } else
        {
            throw NotFoundException.getNotFoundInstance();
        }
    }

    public final Result decodeRow(int i, BitArray bitarray, Map map)
    {
        boolean flag;
        int k;
        int l;
        int ai[];
        boolean flag1;
        int i1;
        int j1;
        boolean flag2;
        int j;
        int l1;
        if (map != null && map.containsKey(DecodeHintType.ASSUME_GS1))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        j = bitarray.getSize();
        k = bitarray.getNextSet(0);
        l = 0;
        ai = new int[6];
        flag1 = false;
        i1 = k;
        if (i1 >= j) goto _L2; else goto _L1
_L1:
        if (!(flag1 ^ bitarray.get(i1))) goto _L4; else goto _L3
_L3:
        ai[l] = 1 + ai[l];
        flag2 = flag1;
        j1 = l;
_L13:
        l1 = i1 + 1;
        flag1 = flag2;
        l = j1;
        i1 = l1;
        break MISSING_BLOCK_LABEL_48;
_L4:
        if (l != 5) goto _L6; else goto _L5
_L5:
        int i2 = 64;
        int j2 = -1;
        int k2 = 103;
        while (k2 <= 105) 
        {
            int k6 = patternMatchVariance(ai, a[k2], 179);
            int k1;
            int ai1[];
            int l2;
            byte byte0;
            StringBuilder stringbuilder;
            ArrayList arraylist;
            int i3;
            int j3;
            int ai2[];
            boolean flag3;
            boolean flag4;
            byte byte1;
            int k3;
            int l3;
            int i4;
            boolean flag5;
            int j4;
            int k4;
            int l4;
            int i5;
            float f;
            float f1;
            int j5;
            byte abyte0[];
            int k5;
            String s;
            ResultPoint aresultpoint[];
            boolean flag6;
            int l5;
            int i6;
            int j6;
            byte byte2;
            boolean flag7;
            boolean flag8;
            boolean flag9;
            boolean flag10;
            boolean flag11;
            boolean flag12;
            boolean flag13;
            boolean flag14;
            boolean flag15;
            boolean flag16;
            boolean flag17;
            boolean flag18;
            boolean flag19;
            boolean flag20;
            boolean flag21;
            boolean flag22;
            boolean flag23;
            boolean flag24;
            boolean flag25;
            boolean flag26;
            boolean flag27;
            boolean flag28;
            boolean flag29;
            boolean flag30;
            boolean flag31;
            if (k6 < i2)
            {
                j2 = k2;
            } else
            {
                k6 = i2;
            }
            k2++;
            i2 = k6;
        }
        if (j2 < 0 || !bitarray.isRange(Math.max(0, k - (i1 - k) / 2), k, false)) goto _L8; else goto _L7
_L7:
        ai1 = (new int[] {
            k, i1, j2
        });
        l2 = ai1[2];
        l2;
        JVM INSTR tableswitch 103 105: default 260
    //                   103 348
    //                   104 514
    //                   105 521;
           goto _L9 _L10 _L11 _L12
_L9:
        throw FormatException.getFormatInstance();
_L8:
        k1 = k + (ai[0] + ai[1]);
        System.arraycopy(ai, 2, ai, 0, 4);
        ai[4] = 0;
        ai[5] = 0;
        j1 = l - 1;
_L14:
        ai[j1] = 1;
        if (!flag1)
        {
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        k = k1;
        if (true) goto _L13; else goto _L6
_L6:
        j1 = l + 1;
        k1 = k;
          goto _L14
_L2:
        throw NotFoundException.getNotFoundInstance();
_L10:
        byte0 = 101;
_L56:
        stringbuilder = new StringBuilder(20);
        arraylist = new ArrayList(20);
        i3 = ai1[0];
        j3 = ai1[1];
        ai2 = new int[6];
        flag3 = true;
        flag4 = false;
        byte1 = byte0;
        k3 = 0;
        l3 = l2;
        i4 = 0;
        flag5 = false;
        j4 = i3;
        k4 = 0;
_L24:
        if (flag5) goto _L16; else goto _L15
_L15:
        flag6 = false;
        l5 = a(bitarray, ai2, j3);
        arraylist.add(Byte.valueOf((byte)l5));
        if (l5 != 106)
        {
            flag3 = true;
        }
        if (l5 != 106)
        {
            k3++;
            l3 += k3 * l5;
        }
        i6 = 0;
        j6 = j3;
        for (; i6 < 6; i6++)
        {
            j6 += ai2[i6];
        }

          goto _L17
_L11:
        byte0 = 100;
        continue; /* Loop/switch isn't completed */
_L12:
        byte0 = 99;
        continue; /* Loop/switch isn't completed */
_L17:
        l5;
        JVM INSTR tableswitch 103 105: default 556
    //                   103 647
    //                   104 647
    //                   105 647;
           goto _L18 _L19 _L19 _L19
_L18:
        byte1;
        JVM INSTR tableswitch 99 101: default 584
    //                   99 1225
    //                   100 957
    //                   101 651;
           goto _L20 _L21 _L22 _L23
_L20:
        flag12 = flag5;
        byte2 = byte1;
        flag7 = flag12;
_L27:
        if (flag4)
        {
            if (byte2 == 101)
            {
                byte2 = 100;
            } else
            {
                byte2 = 101;
            }
        }
        j4 = j3;
        flag4 = flag6;
        j3 = j6;
        k4 = i4;
        i4 = l5;
        flag8 = flag7;
        byte1 = byte2;
        flag5 = flag8;
          goto _L24
_L19:
        throw FormatException.getFormatInstance();
_L23:
        if (l5 >= 64) goto _L26; else goto _L25
_L25:
        stringbuilder.append((char)(l5 + 32));
        flag31 = flag5;
        byte2 = byte1;
        flag7 = flag31;
        flag6 = false;
          goto _L27
_L26:
        if (l5 >= 96) goto _L29; else goto _L28
_L28:
        stringbuilder.append((char)(l5 - 64));
        flag30 = flag5;
        byte2 = byte1;
        flag7 = flag30;
        flag6 = false;
          goto _L27
_L29:
        if (l5 != 106)
        {
            flag3 = false;
        }
        l5;
        JVM INSTR tableswitch 96 106: default 796
    //                   96 879
    //                   97 879
    //                   98 897
    //                   99 933
    //                   100 915
    //                   101 879
    //                   102 814
    //                   103 796
    //                   104 796
    //                   105 796
    //                   106 951;
           goto _L30 _L31 _L31 _L32 _L33 _L34 _L31 _L35 _L30 _L30 _L30 _L36
_L30:
        flag23 = flag5;
        byte2 = byte1;
        flag7 = flag23;
        flag6 = false;
          goto _L27
_L35:
        if (!flag) goto _L30; else goto _L37
_L37:
        if (stringbuilder.length() == 0)
        {
            stringbuilder.append("]C1");
            flag29 = flag5;
            byte2 = byte1;
            flag7 = flag29;
            flag6 = false;
        } else
        {
            stringbuilder.append('\035');
            flag28 = flag5;
            byte2 = byte1;
            flag7 = flag28;
            flag6 = false;
        }
          goto _L27
_L31:
        flag27 = flag5;
        byte2 = byte1;
        flag7 = flag27;
        flag6 = false;
          goto _L27
_L32:
        flag6 = true;
        flag26 = flag5;
        byte2 = 100;
        flag7 = flag26;
          goto _L27
_L34:
        flag25 = flag5;
        byte2 = 100;
        flag7 = flag25;
        flag6 = false;
          goto _L27
_L33:
        flag24 = flag5;
        byte2 = 99;
        flag7 = flag24;
        flag6 = false;
          goto _L27
_L36:
        flag5 = true;
          goto _L30
_L22:
        if (l5 >= 96) goto _L39; else goto _L38
_L38:
        stringbuilder.append((char)(l5 + 32));
        flag22 = flag5;
        byte2 = byte1;
        flag7 = flag22;
        flag6 = false;
          goto _L27
_L39:
        if (l5 != 106)
        {
            flag3 = false;
        }
        l5;
        JVM INSTR tableswitch 96 106: default 1064
    //                   96 1147
    //                   97 1147
    //                   98 1165
    //                   99 1201
    //                   100 1147
    //                   101 1183
    //                   102 1082
    //                   103 1064
    //                   104 1064
    //                   105 1064
    //                   106 1219;
           goto _L40 _L41 _L41 _L42 _L43 _L41 _L44 _L45 _L40 _L40 _L40 _L46
_L40:
        flag15 = flag5;
        byte2 = byte1;
        flag7 = flag15;
        flag6 = false;
          goto _L27
_L45:
        if (!flag) goto _L40; else goto _L47
_L47:
        if (stringbuilder.length() == 0)
        {
            stringbuilder.append("]C1");
            flag21 = flag5;
            byte2 = byte1;
            flag7 = flag21;
            flag6 = false;
        } else
        {
            stringbuilder.append('\035');
            flag20 = flag5;
            byte2 = byte1;
            flag7 = flag20;
            flag6 = false;
        }
          goto _L27
_L41:
        flag19 = flag5;
        byte2 = byte1;
        flag7 = flag19;
        flag6 = false;
          goto _L27
_L42:
        flag6 = true;
        flag18 = flag5;
        byte2 = 101;
        flag7 = flag18;
          goto _L27
_L44:
        flag17 = flag5;
        byte2 = 101;
        flag7 = flag17;
        flag6 = false;
          goto _L27
_L43:
        flag16 = flag5;
        byte2 = 99;
        flag7 = flag16;
        flag6 = false;
          goto _L27
_L46:
        flag5 = true;
          goto _L40
_L21:
        if (l5 >= 100) goto _L49; else goto _L48
_L48:
        if (l5 < 10)
        {
            stringbuilder.append('0');
        }
        stringbuilder.append(l5);
        flag14 = flag5;
        byte2 = byte1;
        flag7 = flag14;
        flag6 = false;
          goto _L27
_L49:
        if (l5 != 106)
        {
            flag3 = false;
        }
        l5;
        JVM INSTR tableswitch 100 106: default 1328
    //                   100 1331
    //                   101 1414
    //                   102 1349
    //                   103 1328
    //                   104 1328
    //                   105 1328
    //                   106 1432;
           goto _L20 _L50 _L51 _L52 _L20 _L20 _L20 _L53
_L50:
        flag13 = flag5;
        byte2 = 100;
        flag7 = flag13;
        flag6 = false;
          goto _L27
_L52:
        if (!flag) goto _L20; else goto _L54
_L54:
        if (stringbuilder.length() == 0)
        {
            stringbuilder.append("]C1");
            flag11 = flag5;
            byte2 = byte1;
            flag7 = flag11;
            flag6 = false;
        } else
        {
            stringbuilder.append('\035');
            flag10 = flag5;
            byte2 = byte1;
            flag7 = flag10;
            flag6 = false;
        }
          goto _L27
_L51:
        flag9 = flag5;
        byte2 = 101;
        flag7 = flag9;
        flag6 = false;
          goto _L27
_L53:
        byte2 = byte1;
        flag7 = true;
        flag6 = false;
          goto _L27
_L16:
        l4 = bitarray.getNextUnset(j3);
        if (!bitarray.isRange(l4, Math.min(bitarray.getSize(), l4 + (l4 - j4) / 2), false))
        {
            throw NotFoundException.getNotFoundInstance();
        }
        if ((l3 - k3 * k4) % 103 != k4)
        {
            throw ChecksumException.getChecksumInstance();
        }
        i5 = stringbuilder.length();
        if (i5 == 0)
        {
            throw NotFoundException.getNotFoundInstance();
        }
        if (i5 > 0 && flag3)
        {
            if (byte1 == 99)
            {
                stringbuilder.delete(i5 - 2, i5);
            } else
            {
                stringbuilder.delete(i5 - 1, i5);
            }
        }
        f = (float)(ai1[1] + ai1[0]) / 2.0F;
        f1 = (float)(l4 + j4) / 2.0F;
        j5 = arraylist.size();
        abyte0 = new byte[j5];
        for (k5 = 0; k5 < j5; k5++)
        {
            abyte0[k5] = ((Byte)arraylist.get(k5)).byteValue();
        }

        s = stringbuilder.toString();
        aresultpoint = new ResultPoint[2];
        aresultpoint[0] = new ResultPoint(f, i);
        aresultpoint[1] = new ResultPoint(f1, i);
        return new Result(s, abyte0, aresultpoint, BarcodeFormat.CODE_128);
        if (true) goto _L56; else goto _L55
_L55:
    }

}
