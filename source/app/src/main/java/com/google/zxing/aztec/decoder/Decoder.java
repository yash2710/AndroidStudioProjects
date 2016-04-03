// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.aztec.decoder;

import com.google.zxing.FormatException;
import com.google.zxing.aztec.AztecDetectorResult;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.reedsolomon.GenericGF;
import com.google.zxing.common.reedsolomon.ReedSolomonDecoder;
import com.google.zxing.common.reedsolomon.ReedSolomonException;

// Referenced classes of package com.google.zxing.aztec.decoder:
//            b, a

public final class Decoder
{

    private static final int a[] = {
        0, 104, 240, 408, 608
    };
    private static final int b[] = {
        0, 128, 288, 480, 704, 960, 1248, 1568, 1920, 2304, 
        2720, 3168, 3648, 4160, 4704, 5280, 5888, 6528, 7200, 7904, 
        8640, 9408, 10208, 11040, 11904, 12800, 13728, 14688, 15680, 16704, 
        17760, 18848, 19968
    };
    private static final int c[] = {
        0, 17, 40, 51, 76
    };
    private static final int d[] = {
        0, 21, 48, 60, 88, 120, 156, 196, 240, 230, 
        272, 316, 364, 416, 470, 528, 588, 652, 720, 790, 
        864, 940, 1020, 920, 992, 1066, 1144, 1224, 1306, 1392, 
        1480, 1570, 1664
    };
    private static final String e[] = {
        "CTRL_PS", " ", "A", "B", "C", "D", "E", "F", "G", "H", 
        "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", 
        "S", "T", "U", "V", "W", "X", "Y", "Z", "CTRL_LL", "CTRL_ML", 
        "CTRL_DL", "CTRL_BS"
    };
    private static final String f[] = {
        "CTRL_PS", " ", "a", "b", "c", "d", "e", "f", "g", "h", 
        "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", 
        "s", "t", "u", "v", "w", "x", "y", "z", "CTRL_US", "CTRL_ML", 
        "CTRL_DL", "CTRL_BS"
    };
    private static final String g[] = {
        "CTRL_PS", " ", "\001", "\002", "\003", "\004", "\005", "\006", "\007", "\b", 
        "\t", "\n", "\013", "\f", "\r", "\033", "\034", "\035", "\036", "\037", 
        "@", "\\", "^", "_", "`", "|", "~", "\177", "CTRL_LL", "CTRL_UL", 
        "CTRL_PL", "CTRL_BS"
    };
    private static final String h[] = {
        "", "\r", "\r\n", ". ", ", ", ": ", "!", "\"", "#", "$", 
        "%", "&", "'", "(", ")", "*", "+", ",", "-", ".", 
        "/", ":", ";", "<", "=", ">", "?", "[", "]", "{", 
        "}", "CTRL_UL"
    };
    private static final String i[] = {
        "CTRL_PS", " ", "0", "1", "2", "3", "4", "5", "6", "7", 
        "8", "9", ",", ".", "CTRL_UL", "CTRL_US"
    };
    private int j;
    private int k;
    private AztecDetectorResult l;
    private int m;

    public Decoder()
    {
    }

    private static int a(boolean aflag[], int i1, int j1)
    {
        int k1 = 0;
        for (int l1 = i1; l1 < i1 + j1; l1++)
        {
            k1 <<= 1;
            if (aflag[l1])
            {
                k1++;
            }
        }

        return k1;
    }

    private boolean[] a(boolean aflag[])
    {
        GenericGF genericgf;
        int i1;
        int j1;
        int k1;
        int ai[];
        if (l.getNbLayers() <= 2)
        {
            k = 6;
            genericgf = GenericGF.AZTEC_DATA_6;
        } else
        if (l.getNbLayers() <= 8)
        {
            k = 8;
            genericgf = GenericGF.AZTEC_DATA_8;
        } else
        if (l.getNbLayers() <= 22)
        {
            k = 10;
            genericgf = GenericGF.AZTEC_DATA_10;
        } else
        {
            k = 12;
            genericgf = GenericGF.AZTEC_DATA_12;
        }
        i1 = l.getNbDatablocks();
        if (l.isCompact())
        {
            j1 = a[l.getNbLayers()] - j * k;
            k1 = c[l.getNbLayers()] - i1;
        } else
        {
            j1 = b[l.getNbLayers()] - j * k;
            k1 = d[l.getNbLayers()] - i1;
        }
        ai = new int[j];
        for (int l1 = 0; l1 < j; l1++)
        {
            int l3 = 1;
            int i4 = 1;
            for (; l3 <= k; l3++)
            {
                if (aflag[j1 + ((l1 * k + k) - l3)])
                {
                    ai[l1] = i4 + ai[l1];
                }
                i4 <<= 1;
            }

        }

        boolean aflag1[];
        int i2;
        try
        {
            (new ReedSolomonDecoder(genericgf)).decode(ai, k1);
        }
        catch (ReedSolomonException reedsolomonexception)
        {
            throw FormatException.getFormatInstance();
        }
        m = 0;
        aflag1 = new boolean[i1 * k];
        i2 = 0;
        int k3;
        for (int j2 = 0; i2 < i1; j2 = k3)
        {
            int k2 = 1 << -1 + k;
            int l2 = 0;
            int i3 = k2;
            int j3 = 0;
            k3 = j2;
            boolean flag = false;
            while (l2 < k) 
            {
                boolean flag1;
                if ((i3 & ai[i2]) == i3)
                {
                    flag1 = true;
                } else
                {
                    flag1 = false;
                }
                if (j3 == -1 + k)
                {
                    if (flag1 == flag)
                    {
                        throw FormatException.getFormatInstance();
                    }
                    k3++;
                    m = 1 + m;
                    flag = false;
                    j3 = 0;
                } else
                {
                    if (flag == flag1)
                    {
                        j3++;
                    } else
                    {
                        flag = flag1;
                        j3 = 1;
                    }
                    aflag1[(l2 + i2 * k) - k3] = flag1;
                }
                i3 >>>= 1;
                l2++;
            }
            i2++;
        }

        return aflag1;
    }

    public final DecoderResult decode(AztecDetectorResult aztecdetectorresult)
    {
        boolean aflag1[];
        int j2;
        b b2;
        StringBuilder stringbuilder;
        boolean flag;
        boolean flag1;
        int k2;
        boolean flag2;
        boolean flag3;
        b b3;
        l = aztecdetectorresult;
        BitMatrix bitmatrix = aztecdetectorresult.getBits();
        if (!l.isCompact())
        {
            BitMatrix bitmatrix1 = l.getBits();
            int j6 = 1 + 2 * ((-1 + bitmatrix1.getWidth()) / 2 / 16);
            BitMatrix bitmatrix2 = new BitMatrix(bitmatrix1.getWidth() - j6, bitmatrix1.getHeight() - j6);
            int k6 = 0;
            for (int l6 = 0; l6 < bitmatrix1.getWidth(); l6++)
            {
                if ((bitmatrix1.getWidth() / 2 - l6) % 16 == 0)
                {
                    continue;
                }
                int i7 = 0;
                for (int j7 = 0; j7 < bitmatrix1.getHeight(); j7++)
                {
                    if ((bitmatrix1.getWidth() / 2 - j7) % 16 == 0)
                    {
                        continue;
                    }
                    if (bitmatrix1.get(l6, j7))
                    {
                        bitmatrix2.set(k6, i7);
                    }
                    i7++;
                }

                k6++;
            }

            bitmatrix = bitmatrix2;
        }
        boolean aflag[];
        int j1;
        int k1;
        int l1;
        int i2;
        if (l.isCompact())
        {
            int i6 = l.getNbLayers();
            a;
            if (i6 > 5)
            {
                throw FormatException.getFormatInstance();
            }
            aflag = new boolean[a[l.getNbLayers()]];
            j = c[l.getNbLayers()];
        } else
        {
            int i1 = l.getNbLayers();
            b;
            if (i1 > 33)
            {
                throw FormatException.getFormatInstance();
            }
            aflag = new boolean[b[l.getNbLayers()]];
            j = d[l.getNbLayers()];
        }
        j1 = l.getNbLayers();
        k1 = bitmatrix.getHeight();
        l1 = 0;
        i2 = 0;
        while (j1 != 0) 
        {
            int i5 = 0;
            for (int j5 = 0; j5 < -4 + k1 * 2; j5++)
            {
                aflag[l1 + j5] = bitmatrix.get(i2 + i5, i2 + j5 / 2);
                aflag[j5 + (-4 + (l1 + k1 * 2))] = bitmatrix.get(i2 + j5 / 2, (-1 + (i2 + k1)) - i5);
                i5 = (i5 + 1) % 2;
            }

            int k5 = 0;
            for (int l5 = 1 + k1 * 2; l5 > 5; l5--)
            {
                aflag[1 + (-8 + (l1 + k1 * 4) + (k1 * 2 - l5))] = bitmatrix.get((-1 + (i2 + k1)) - k5, -1 + (i2 + l5 / 2));
                aflag[1 + (-12 + (l1 + k1 * 6) + (k1 * 2 - l5))] = bitmatrix.get(-1 + (i2 + l5 / 2), i2 + k5);
                k5 = (k5 + 1) % 2;
            }

            i2 += 2;
            l1 += -16 + k1 * 8;
            j1--;
            k1 -= 4;
        }
        aflag1 = a(aflag);
        j2 = k * l.getNbDatablocks() - m;
        if (j2 > aflag1.length)
        {
            throw FormatException.getFormatInstance();
        }
        b b1 = b.a;
        b2 = b.a;
        stringbuilder = new StringBuilder(20);
        flag = false;
        flag1 = false;
        k2 = 0;
        flag2 = false;
        flag3 = false;
        b3 = b1;
_L13:
        if (flag3) goto _L2; else goto _L1
_L1:
        boolean flag4;
        b b4;
        boolean flag5;
        boolean flag6;
        b b5;
        boolean flag7;
        int j3;
        int i4;
        int j4;
        int k4;
        int l4;
        boolean flag8;
        if (flag)
        {
            flag4 = true;
            b4 = b3;
        } else
        {
            flag4 = flag2;
            b4 = b2;
        }
        if (!flag1) goto _L4; else goto _L3
_L3:
        if (j2 - k2 < 5) goto _L2; else goto _L5
_L5:
        i4 = a(aflag1, k2, 5);
        j4 = k2 + 5;
        if (i4 != 0) goto _L7; else goto _L6
_L6:
        if (j2 - j4 < 11) goto _L2; else goto _L8
_L8:
        i4 = 31 + a(aflag1, j4, 11);
        j4 += 11;
_L7:
        k4 = j4;
        l4 = 0;
_L14:
        if (l4 >= i4) goto _L10; else goto _L9
_L9:
        if (j2 - k4 >= 8) goto _L12; else goto _L11
_L11:
        flag8 = true;
_L39:
        j3 = k4;
        b5 = b2;
        flag6 = flag;
        flag7 = flag8;
        flag5 = false;
_L18:
        byte byte0;
        int l2;
        int i3;
        String s;
        b b6;
        if (flag4)
        {
            flag1 = flag5;
            k2 = j3;
            b2 = b4;
            flag3 = flag7;
            b3 = b4;
            flag2 = false;
            flag = false;
        } else
        {
            k2 = j3;
            b2 = b5;
            flag1 = flag5;
            flag3 = flag7;
            flag2 = flag4;
            flag = flag6;
            b3 = b4;
        }
          goto _L13
_L12:
        stringbuilder.append((char)a(aflag1, k4, 8));
        k4 += 8;
        l4++;
          goto _L14
_L4:
        if (b2 != b.f) goto _L16; else goto _L15
_L15:
        if (j2 - k2 < 8) goto _L2; else goto _L17
_L17:
        int k3 = a(aflag1, k2, 8);
        int l3 = k2 + 8;
        stringbuilder.append((char)k3);
        flag6 = flag;
        flag7 = flag3;
        j3 = l3;
        flag5 = flag1;
        b5 = b2;
          goto _L18
_L16:
        byte0 = 5;
        if (b2 == b.d)
        {
            byte0 = 4;
        }
        if (j2 - k2 < byte0) goto _L2; else goto _L19
_L19:
        l2 = a(aflag1, k2, byte0);
        i3 = k2 + byte0;
        a.a[b2.ordinal()];
        JVM INSTR tableswitch 1 5: default 988
    //                   1 1113
    //                   2 1124
    //                   3 1135
    //                   4 1146
    //                   5 1157;
           goto _L20 _L21 _L22 _L23 _L24 _L25
_L20:
        s = "";
_L37:
        if (!s.startsWith("CTRL_")) goto _L27; else goto _L26
_L26:
        s.charAt(5);
        JVM INSTR lookupswitch 5: default 1060
    //                   66: 1200
    //                   68: 1192
    //                   76: 1168
    //                   77: 1184
    //                   80: 1176;
           goto _L28 _L29 _L30 _L31 _L32 _L33
_L28:
        b6 = b.a;
_L38:
        if (s.charAt(6) != 'S') goto _L35; else goto _L34
_L34:
        flag = true;
        if (s.charAt(5) != 'B') goto _L35; else goto _L36
_L36:
        flag5 = true;
        b5 = b6;
        flag6 = flag;
        flag7 = flag3;
        j3 = i3;
          goto _L18
_L21:
        s = e[l2];
          goto _L37
_L22:
        s = f[l2];
          goto _L37
_L23:
        s = g[l2];
          goto _L37
_L24:
        s = h[l2];
          goto _L37
_L25:
        s = i[l2];
          goto _L37
_L31:
        b6 = b.b;
          goto _L38
_L33:
        b6 = b.e;
          goto _L38
_L32:
        b6 = b.c;
          goto _L38
_L30:
        b6 = b.d;
          goto _L38
_L29:
        b6 = b.f;
          goto _L38
_L27:
        stringbuilder.append(s);
        flag5 = flag1;
        flag6 = flag;
        b5 = b2;
        flag7 = flag3;
        j3 = i3;
          goto _L18
_L2:
        return new DecoderResult(null, stringbuilder.toString(), null, null);
_L35:
        flag5 = flag1;
        b5 = b6;
        flag6 = flag;
        flag7 = flag3;
        j3 = i3;
          goto _L18
_L10:
        flag8 = flag3;
          goto _L39
    }

}
