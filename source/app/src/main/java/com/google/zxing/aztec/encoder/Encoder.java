// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.aztec.encoder;

import com.google.zxing.common.BitArray;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.reedsolomon.GenericGF;
import com.google.zxing.common.reedsolomon.ReedSolomonEncoder;
import java.lang.reflect.Array;
import java.util.Arrays;

// Referenced classes of package com.google.zxing.aztec.encoder:
//            AztecCode

public final class Encoder
{

    public static final int DEFAULT_EC_PERCENT = 33;
    private static final int a[][];
    private static final int b[][];
    private static final int c[][];
    private static final int d[];
    private static final int e[];
    private static final int f[];

    private Encoder()
    {
    }

    private static BitArray a(BitArray bitarray, int i)
    {
        BitArray bitarray1 = new BitArray();
        int j = bitarray.getSize();
        int k = -2 + (1 << i);
        int l = 0;
        while (l < j) 
        {
            int i1 = 0;
            int j1 = 0;
            for (; i1 < i; i1++)
            {
                if (l + i1 >= j || bitarray.get(l + i1))
                {
                    j1 |= 1 << i - 1 - i1;
                }
            }

            int k1;
            if ((j1 & k) == k)
            {
                bitarray1.appendBits(j1 & k, i);
                k1 = l - 1;
            } else
            if ((j1 & k) == 0)
            {
                bitarray1.appendBits(j1 | 1, i);
                k1 = l - 1;
            } else
            {
                bitarray1.appendBits(j1, i);
                k1 = l;
            }
            l = k1 + i;
        }
        return bitarray1;
    }

    private static BitArray a(BitArray bitarray, int i, int j)
    {
        int k = 0;
        int l = (-1 + (4 + bitarray.getSize())) / 4;
        for (int i1 = (l << 2) - bitarray.getSize(); i1 > 0; i1--)
        {
            bitarray.appendBit(true);
        }

        ReedSolomonEncoder reedsolomonencoder = new ReedSolomonEncoder(a(4));
        int j1 = i / 4;
        int ai[] = b(bitarray, 4, j1);
        reedsolomonencoder.encode(ai, j1 - l);
        int k1 = i % 4;
        BitArray bitarray1 = new BitArray();
        bitarray1.appendBits(0, k1);
        for (int l1 = ai.length; k < l1; k++)
        {
            bitarray1.appendBits(ai[k], 4);
        }

        return bitarray1;
    }

    private static GenericGF a(int i)
    {
        switch (i)
        {
        case 5: // '\005'
        case 7: // '\007'
        case 9: // '\t'
        case 11: // '\013'
        default:
            return null;

        case 4: // '\004'
            return GenericGF.AZTEC_PARAM;

        case 6: // '\006'
            return GenericGF.AZTEC_DATA_6;

        case 8: // '\b'
            return GenericGF.AZTEC_DATA_8;

        case 10: // '\n'
            return GenericGF.AZTEC_DATA_10;

        case 12: // '\f'
            return GenericGF.AZTEC_DATA_12;
        }
    }

    private static void a(BitMatrix bitmatrix, int i, int j)
    {
        for (int k = 0; k < j; k += 2)
        {
            for (int l = i - k; l <= i + k; l++)
            {
                bitmatrix.set(l, i - k);
                bitmatrix.set(l, i + k);
                bitmatrix.set(i - k, l);
                bitmatrix.set(i + k, l);
            }

        }

        bitmatrix.set(i - j, i - j);
        bitmatrix.set(1 + (i - j), i - j);
        bitmatrix.set(i - j, 1 + (i - j));
        bitmatrix.set(i + j, i - j);
        bitmatrix.set(i + j, 1 + (i - j));
        bitmatrix.set(i + j, -1 + (i + j));
    }

    private static void a(BitMatrix bitmatrix, boolean flag, int i, BitArray bitarray)
    {
        int j = 0;
        if (flag)
        {
            for (; j < 7; j++)
            {
                if (bitarray.get(j))
                {
                    bitmatrix.set(j + (-3 + i / 2), -5 + i / 2);
                }
                if (bitarray.get(j + 7))
                {
                    bitmatrix.set(5 + i / 2, j + (-3 + i / 2));
                }
                if (bitarray.get(20 - j))
                {
                    bitmatrix.set(j + (-3 + i / 2), 5 + i / 2);
                }
                if (bitarray.get(27 - j))
                {
                    bitmatrix.set(-5 + i / 2, j + (-3 + i / 2));
                }
            }

        } else
        {
            for (; j < 10; j++)
            {
                if (bitarray.get(j))
                {
                    bitmatrix.set(j + (-5 + i / 2) + j / 5, -7 + i / 2);
                }
                if (bitarray.get(j + 10))
                {
                    bitmatrix.set(7 + i / 2, j + (-5 + i / 2) + j / 5);
                }
                if (bitarray.get(29 - j))
                {
                    bitmatrix.set(j + (-5 + i / 2) + j / 5, 7 + i / 2);
                }
                if (bitarray.get(39 - j))
                {
                    bitmatrix.set(-7 + i / 2, j + (-5 + i / 2) + j / 5);
                }
            }

        }
    }

    private static int[] b(BitArray bitarray, int i, int j)
    {
        int ai[] = new int[j];
        int k = bitarray.getSize() / i;
        for (int l = 0; l < k; l++)
        {
            int i1 = 0;
            int j1 = 0;
            while (i1 < i) 
            {
                int k1;
                if (bitarray.get(i1 + l * i))
                {
                    k1 = 1 << -1 + (i - i1);
                } else
                {
                    k1 = 0;
                }
                j1 |= k1;
                i1++;
            }
            ai[l] = j1;
        }

        return ai;
    }

    private static void c(BitArray bitarray, int i, int j)
    {
        if (i == 2)
        {
            bitarray.appendBits(j, 4);
            return;
        }
        if (i < 5)
        {
            bitarray.appendBits(j, 5);
            return;
        } else
        {
            bitarray.appendBits(j, 8);
            return;
        }
    }

    public static AztecCode encode(byte abyte0[])
    {
        return encode(abyte0, 33);
    }

    public static AztecCode encode(byte abyte0[], int i)
    {
        BitArray bitarray;
        int j;
        int ai[];
        int ai1[];
        int k;
        bitarray = new BitArray();
        j = 0;
        ai = new int[5];
        ai1 = new int[5];
        k = 0;
_L33:
        int j9;
        int k9;
        byte byte0;
        if (k >= abyte0.length)
        {
            break MISSING_BLOCK_LABEL_862;
        }
        j9 = 0xff & abyte0[k];
        if (k < -1 + abyte0.length)
        {
            k9 = 0xff & abyte0[k + 1];
        } else
        {
            k9 = 0;
        }
        if (j9 == 13 && k9 == 10)
        {
            byte0 = 2;
        } else
        if (j9 == 46 && k9 == 32)
        {
            byte0 = 3;
        } else
        if (j9 == 44 && k9 == 32)
        {
            byte0 = 4;
        } else
        {
            byte0 = 0;
            if (j9 == 58)
            {
                byte0 = 0;
                if (k9 == 32)
                {
                    byte0 = 5;
                }
            }
        }
        if (byte0 <= 0) goto _L2; else goto _L1
_L1:
        if (j == 4)
        {
            c(bitarray, 4, byte0);
            k++;
        } else
        {
label0:
            {
                if (b[j][4] < 0)
                {
                    break label0;
                }
                c(bitarray, j, b[j][4]);
                c(bitarray, 4, byte0);
                k++;
            }
        }
_L4:
        k++;
        continue; /* Loop/switch isn't completed */
        if (c[j][4] < 0) goto _L2; else goto _L3
_L3:
        c(bitarray, j, c[j][4]);
        c(bitarray, 4, byte0);
        j = 4;
        k++;
          goto _L4
_L2:
        int l9;
        int i10;
        int j10;
        l9 = -1;
        i10 = -1;
        j10 = -1;
        for (int k10 = 0; k10 < 5; k10++)
        {
            ai[k10] = a[k10][j9];
            if (ai[k10] > 0 && l9 < 0)
            {
                l9 = k10;
            }
            if (i10 < 0 && ai[k10] > 0 && b[j][k10] >= 0)
            {
                i10 = k10;
            }
            ai1[k10] = a[k10][k9];
            if (j10 < 0 && ai[k10] > 0 && (k9 == 0 || ai1[k10] > 0) && c[j][k10] >= 0)
            {
                j10 = k10;
            }
        }

        if (i10 >= 0 || j10 >= 0) goto _L6; else goto _L5
_L5:
        int i12 = 0;
_L7:
        if (i12 < 5)
        {
            if (ai[i12] <= 0 || c[j][i12] < 0)
            {
                break MISSING_BLOCK_LABEL_447;
            }
            j10 = i12;
        }
_L6:
        if (ai[j] > 0)
        {
            c(bitarray, j, ai[j]);
        } else
        if (j10 >= 0)
        {
            c(bitarray, j, c[j][j10]);
            c(bitarray, j10, ai[j10]);
            j = j10;
        } else
        {
label1:
            {
                if (i10 < 0)
                {
                    break label1;
                }
                c(bitarray, j, b[j][i10]);
                c(bitarray, i10, ai[i10]);
            }
        }
          goto _L4
        i12++;
          goto _L7
        if (l9 < 0) goto _L9; else goto _L8
_L8:
        if (j != 4) goto _L11; else goto _L10
_L10:
        c(bitarray, 4, c[4][0]);
        j = 0;
_L13:
        k--;
          goto _L4
_L11:
        if (j != 2) goto _L9; else goto _L12
_L12:
        c(bitarray, 2, c[2][0]);
        j = 0;
          goto _L13
_L9:
        int l10;
        int i11;
        l10 = k + 1;
        i11 = 0;
_L22:
        if (l10 >= abyte0.length) goto _L15; else goto _L14
_L14:
        int k11;
        boolean flag2;
        int l11;
        k11 = 0xff & abyte0[l10];
        flag2 = true;
        l11 = 0;
_L23:
        if (l11 >= 5) goto _L17; else goto _L16
_L16:
        if (a[l11][k11] <= 0) goto _L19; else goto _L18
_L18:
        flag2 = false;
_L17:
        if (!flag2) goto _L21; else goto _L20
_L20:
        i11 = 0;
_L30:
        l10++;
          goto _L22
_L19:
        l11++;
          goto _L23
_L21:
        if (i11 <= 0) goto _L25; else goto _L24
_L24:
        l10 -= i11;
_L15:
        int j11 = l10 - k;
        j;
        JVM INSTR tableswitch 0 4: default 700
    //                   0 775
    //                   1 775
    //                   2 790
    //                   3 775
    //                   4 819;
           goto _L26 _L27 _L27 _L28 _L27 _L29
_L26:
        break; /* Loop/switch isn't completed */
_L29:
        break MISSING_BLOCK_LABEL_819;
_L31:
        if (j11 >= 32 && j11 < 63)
        {
            j11 = 31;
        }
        if (j11 > 542)
        {
            j11 = 542;
        }
        if (j11 < 32)
        {
            bitarray.appendBits(j11, 5);
        } else
        {
            bitarray.appendBits(j11 - 31, 16);
        }
        while (j11 > 0) 
        {
            bitarray.appendBits(abyte0[k], 8);
            j11--;
            k++;
        }
          goto _L13
_L25:
        i11++;
          goto _L30
_L27:
        c(bitarray, j, b[j][5]);
          goto _L31
_L28:
        c(bitarray, j, c[j][0]);
        c(bitarray, 0, b[0][5]);
        j = 0;
          goto _L31
        c(bitarray, j, c[j][0]);
        c(bitarray, 0, b[0][5]);
        j = 0;
          goto _L31
        int l = 11 + (i * bitarray.getSize()) / 100;
        int i1 = l + bitarray.getSize();
        int j1 = 0;
        int k1 = 0;
        BitArray bitarray1 = null;
        int l1 = 1;
        do
        {
            e;
            if (l1 >= 5)
            {
                break;
            }
            if (e[l1] >= i1)
            {
                if (j1 != f[l1])
                {
                    j1 = f[l1];
                    bitarray1 = a(bitarray, j1);
                }
                k1 = e[l1];
                if (l + bitarray1.getSize() <= e[l1])
                {
                    break;
                }
            }
            int l8 = k1;
            int i9 = j1;
            l1++;
            j1 = i9;
            k1 = l8;
        } while (true);
        boolean flag = true;
        e;
        if (l1 == 5)
        {
            l1 = 1;
            do
            {
                d;
                flag = false;
                if (l1 >= 33)
                {
                    break;
                }
                if (d[l1] >= i1)
                {
                    if (j1 != f[l1])
                    {
                        j1 = f[l1];
                        bitarray1 = a(bitarray, j1);
                    }
                    k1 = d[l1];
                    int j8 = l + bitarray1.getSize();
                    int k8 = d[l1];
                    flag = false;
                    if (j8 <= k8)
                    {
                        break;
                    }
                }
                l1++;
            } while (true);
        }
        boolean flag1 = flag;
        int i2 = l1;
        int j2 = j1;
        BitArray bitarray2 = bitarray1;
        d;
        if (i2 == 33)
        {
            throw new IllegalArgumentException("Data too large for an Aztec code");
        }
        int k2 = (-1 + (j2 + bitarray2.getSize())) / j2;
        ReedSolomonEncoder reedsolomonencoder = new ReedSolomonEncoder(a(j2));
        int l2 = k1 / j2;
        int ai2[] = b(bitarray2, j2, l2);
        reedsolomonencoder.encode(ai2, l2 - k2);
        int i3 = k1 % j2;
        BitArray bitarray3 = new BitArray();
        bitarray3.appendBits(0, i3);
        int j3 = ai2.length;
        for (int k3 = 0; k3 < j3; k3++)
        {
            bitarray3.appendBits(ai2[k3], j2);
        }

        BitArray bitarray4 = new BitArray();
        BitArray bitarray5;
        int l3;
        int ai3[];
        if (flag1)
        {
            bitarray4.appendBits(i2 - 1, 2);
            bitarray4.appendBits(k2 - 1, 6);
            bitarray5 = a(bitarray4, 28, 4);
        } else
        {
            bitarray4.appendBits(i2 - 1, 5);
            bitarray4.appendBits(k2 - 1, 11);
            bitarray5 = a(bitarray4, 40, 4);
        }
        if (flag1)
        {
            l3 = 11 + (i2 << 2);
        } else
        {
            l3 = 14 + (i2 << 2);
        }
        ai3 = new int[l3];
        int i4;
        BitMatrix bitmatrix;
        int j5;
        if (flag1)
        {
            for (int i8 = 0; i8 < ai3.length; i8++)
            {
                ai3[i8] = i8;
            }

            i4 = l3;
        } else
        {
            i4 = l3 + 1 + 2 * ((-1 + l3 / 2) / 15);
            int j4 = l3 / 2;
            int k4 = i4 / 2;
            int l4 = 0;
            while (l4 < j4) 
            {
                int i5 = l4 + l4 / 15;
                ai3[-1 + (j4 - l4)] = -1 + (k4 - i5);
                ai3[j4 + l4] = 1 + (i5 + k4);
                l4++;
            }
        }
        bitmatrix = new BitMatrix(i4);
        j5 = 0;
        int j7;
        for (int k5 = 0; k5 < i2; k5 = j7)
        {
            int k6;
            int l6;
            if (flag1)
            {
                k6 = 9 + (i2 - k5 << 2);
            } else
            {
                k6 = 12 + (i2 - k5 << 2);
            }
            for (l6 = 0; l6 < k6; l6++)
            {
                int k7 = l6 << 1;
                for (int l7 = 0; l7 < 2; l7++)
                {
                    if (bitarray3.get(l7 + (j5 + k7)))
                    {
                        bitmatrix.set(ai3[l7 + (k5 << 1)], ai3[l6 + (k5 << 1)]);
                    }
                    if (bitarray3.get(l7 + (k7 + (j5 + (k6 << 1)))))
                    {
                        bitmatrix.set(ai3[l6 + (k5 << 1)], ai3[l3 - 1 - (k5 << 1) - l7]);
                    }
                    if (bitarray3.get(l7 + (k7 + (j5 + (k6 << 2)))))
                    {
                        bitmatrix.set(ai3[l3 - 1 - (k5 << 1) - l7], ai3[l3 - 1 - (k5 << 1) - l6]);
                    }
                    if (bitarray3.get(l7 + (k7 + (j5 + k6 * 6))))
                    {
                        bitmatrix.set(ai3[l3 - 1 - (k5 << 1) - l6], ai3[l7 + (k5 << 1)]);
                    }
                }

            }

            int i7 = j5 + (k6 << 3);
            j7 = k5 + 1;
            j5 = i7;
        }

        a(bitmatrix, flag1, i4, bitarray5);
        AztecCode azteccode;
        if (flag1)
        {
            a(bitmatrix, i4 / 2, 5);
        } else
        {
            a(bitmatrix, i4 / 2, 7);
            int l5 = 0;
            int i6 = 0;
            while (l5 < -1 + l3 / 2) 
            {
                for (int j6 = 1 & i4 / 2; j6 < i4; j6 += 2)
                {
                    bitmatrix.set(i4 / 2 - i6, j6);
                    bitmatrix.set(i6 + i4 / 2, j6);
                    bitmatrix.set(j6, i4 / 2 - i6);
                    bitmatrix.set(j6, i6 + i4 / 2);
                }

                l5 += 15;
                i6 += 16;
            }
        }
        azteccode = new AztecCode();
        azteccode.setCompact(flag1);
        azteccode.setSize(i4);
        azteccode.setLayers(i2);
        azteccode.setCodeWords(k2);
        azteccode.setMatrix(bitmatrix);
        return azteccode;
        if (true) goto _L33; else goto _L32
_L32:
    }

    static 
    {
        int i = 1;
        int ai[] = {
            5, 256
        };
        a = (int[][])Array.newInstance(Integer.TYPE, ai);
        int ai1[] = {
            6, 6
        };
        b = (int[][])Array.newInstance(Integer.TYPE, ai1);
        int ai2[] = {
            6, 6
        };
        c = (int[][])Array.newInstance(Integer.TYPE, ai2);
        a[0][32] = i;
        for (int j = 65; j <= 90; j++)
        {
            a[0][j] = 2 + (j - 65);
        }

        a[i][32] = i;
        for (int k = 97; k <= 122; k++)
        {
            a[i][k] = 2 + (k - 97);
        }

        a[2][32] = i;
        for (int l = 48; l <= 57; l++)
        {
            a[2][l] = 2 + (l - 48);
        }

        a[2][44] = 12;
        a[2][46] = 13;
        int ai3[] = {
            0, 32, 1, 2, 3, 4, 5, 6, 7, 8, 
            9, 10, 11, 12, 13, 27, 28, 29, 30, 31, 
            64, 92, 94, 95, 96, 124, 126, 127
        };
        for (int i1 = 0; i1 < 28; i1++)
        {
            a[3][ai3[i1]] = i1;
        }

        int ai4[] = {
            0, 13, 0, 0, 0, 0, 33, 39, 35, 36, 
            37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 
            47, 58, 59, 60, 61, 62, 63, 91, 93, 123, 
            125
        };
        for (int j1 = 0; j1 < 31; j1++)
        {
            if (ai4[j1] > 0)
            {
                a[4][ai4[j1]] = j1;
            }
        }

        int ai5[][] = b;
        int k1 = ai5.length;
        for (int l1 = 0; l1 < k1; l1++)
        {
            Arrays.fill(ai5[l1], -1);
        }

        int ai6[][] = c;
        int i2 = ai6.length;
        for (int j2 = 0; j2 < i2; j2++)
        {
            Arrays.fill(ai6[j2], -1);
        }

        b[0][4] = 0;
        c[0][i] = 28;
        c[0][3] = 29;
        c[0][2] = 30;
        b[0][5] = 31;
        b[i][4] = 0;
        b[i][0] = 28;
        c[i][3] = 29;
        c[i][2] = 30;
        b[i][5] = 31;
        b[3][4] = 0;
        c[3][i] = 28;
        c[3][0] = 29;
        c[3][4] = 30;
        b[3][5] = 31;
        c[4][0] = 31;
        b[2][4] = 0;
        c[2][0] = 30;
        b[2][0] = 31;
        e = new int[5];
        int k2 = i;
        do
        {
            int[] _tmp = e;
            if (k2 >= 5)
            {
                break;
            }
            e[k2] = k2 * (88 + k2 * 16);
            k2++;
        } while (true);
        d = new int[33];
        do
        {
            int[] _tmp1 = d;
            if (i < 33)
            {
                d[i] = i * (112 + i * 16);
                i++;
            } else
            {
                f = (new int[] {
                    4, 6, 6, 8, 8, 8, 8, 8, 8, 10, 
                    10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 
                    10, 10, 10, 12, 12, 12, 12, 12, 12, 12, 
                    12, 12, 12
                });
            }
        } while (true);
    }
}
