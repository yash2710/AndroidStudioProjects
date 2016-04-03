// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.qrcode.encoder;

import com.google.zxing.WriterException;
import com.google.zxing.common.BitArray;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.decoder.Version;

// Referenced classes of package com.google.zxing.qrcode.encoder:
//            ByteMatrix, QRCode

final class c
{

    private static final int a[][] = {
        {
            1, 1, 1, 1, 1, 1, 1
        }, {
            1, 0, 0, 0, 0, 0, 1
        }, {
            1, 0, 1, 1, 1, 0, 1
        }, {
            1, 0, 1, 1, 1, 0, 1
        }, {
            1, 0, 1, 1, 1, 0, 1
        }, {
            1, 0, 0, 0, 0, 0, 1
        }, {
            1, 1, 1, 1, 1, 1, 1
        }
    };
    private static final int b[][] = {
        {
            1, 1, 1, 1, 1
        }, {
            1, 0, 0, 0, 1
        }, {
            1, 0, 1, 0, 1
        }, {
            1, 0, 0, 0, 1
        }, {
            1, 1, 1, 1, 1
        }
    };
    private static final int c[][] = {
        {
            -1, -1, -1, -1, -1, -1, -1
        }, {
            6, 18, -1, -1, -1, -1, -1
        }, {
            6, 22, -1, -1, -1, -1, -1
        }, {
            6, 26, -1, -1, -1, -1, -1
        }, {
            6, 30, -1, -1, -1, -1, -1
        }, {
            6, 34, -1, -1, -1, -1, -1
        }, {
            6, 22, 38, -1, -1, -1, -1
        }, {
            6, 24, 42, -1, -1, -1, -1
        }, {
            6, 26, 46, -1, -1, -1, -1
        }, {
            6, 28, 50, -1, -1, -1, -1
        }, {
            6, 30, 54, -1, -1, -1, -1
        }, {
            6, 32, 58, -1, -1, -1, -1
        }, {
            6, 34, 62, -1, -1, -1, -1
        }, {
            6, 26, 46, 66, -1, -1, -1
        }, {
            6, 26, 48, 70, -1, -1, -1
        }, {
            6, 26, 50, 74, -1, -1, -1
        }, {
            6, 30, 54, 78, -1, -1, -1
        }, {
            6, 30, 56, 82, -1, -1, -1
        }, {
            6, 30, 58, 86, -1, -1, -1
        }, {
            6, 34, 62, 90, -1, -1, -1
        }, {
            6, 28, 50, 72, 94, -1, -1
        }, {
            6, 26, 50, 74, 98, -1, -1
        }, {
            6, 30, 54, 78, 102, -1, -1
        }, {
            6, 28, 54, 80, 106, -1, -1
        }, {
            6, 32, 58, 84, 110, -1, -1
        }, {
            6, 30, 58, 86, 114, -1, -1
        }, {
            6, 34, 62, 90, 118, -1, -1
        }, {
            6, 26, 50, 74, 98, 122, -1
        }, {
            6, 30, 54, 78, 102, 126, -1
        }, {
            6, 26, 52, 78, 104, 130, -1
        }, {
            6, 30, 56, 82, 108, 134, -1
        }, {
            6, 34, 60, 86, 112, 138, -1
        }, {
            6, 30, 58, 86, 114, 142, -1
        }, {
            6, 34, 62, 90, 118, 146, -1
        }, {
            6, 30, 54, 78, 102, 126, 150
        }, {
            6, 24, 50, 76, 102, 128, 154
        }, {
            6, 28, 54, 80, 106, 132, 158
        }, {
            6, 32, 58, 84, 110, 136, 162
        }, {
            6, 26, 54, 82, 110, 138, 166
        }, {
            6, 30, 58, 86, 114, 142, 170
        }
    };
    private static final int d[][] = {
        {
            8, 0
        }, {
            8, 1
        }, {
            8, 2
        }, {
            8, 3
        }, {
            8, 4
        }, {
            8, 5
        }, {
            8, 7
        }, {
            8, 8
        }, {
            7, 8
        }, {
            5, 8
        }, {
            4, 8
        }, {
            3, 8
        }, {
            2, 8
        }, {
            1, 8
        }, {
            0, 8
        }
    };

    private static int a(int i)
    {
        int j;
        for (j = 0; i != 0; j++)
        {
            i >>>= 1;
        }

        return j;
    }

    private static int a(int i, int j)
    {
        int k = a(j);
        int l;
        for (l = i << k - 1; a(l) >= k; l ^= j << a(l) - k) { }
        return l;
    }

    private static void a(int i, int j, ByteMatrix bytematrix)
    {
        for (int k = 0; k < 8; k++)
        {
            if (!b(bytematrix.get(i + k, j)))
            {
                throw new WriterException();
            }
            bytematrix.set(i + k, j, 0);
        }

    }

    private static void a(BitArray bitarray, int i, ByteMatrix bytematrix)
    {
        int j;
        int k;
        int l;
        int i1;
        j = -1 + bytematrix.getWidth();
        k = -1 + bytematrix.getHeight();
        l = -1;
        i1 = 0;
_L18:
        if (j <= 0) goto _L2; else goto _L1
_L1:
        int j1;
        int k1;
        int l1;
        int l2;
        int i3;
        int j3;
        boolean flag;
        int k3;
        if (j == 6)
        {
            int j4 = j - 1;
            j1 = k;
            k1 = j4;
            l1 = i1;
        } else
        {
            j1 = k;
            k1 = j;
            l1 = i1;
        }
        if (j1 < 0 || j1 >= bytematrix.getHeight())
        {
            break;
        }
        l2 = 0;
_L16:
        if (l2 >= 2)
        {
            break MISSING_BLOCK_LABEL_380;
        }
        i3 = k1 - l2;
        if (!b(bytematrix.get(i3, j1))) goto _L4; else goto _L3
_L3:
        if (l1 < bitarray.getSize())
        {
            boolean flag2 = bitarray.get(l1);
            j3 = l1 + 1;
            flag = flag2;
        } else
        {
            j3 = l1;
            flag = false;
        }
        if (i == -1) goto _L6; else goto _L5
_L5:
        i;
        JVM INSTR tableswitch 0 7: default 172
    //                   0 206
    //                   1 256
    //                   2 265
    //                   3 274
    //                   4 286
    //                   5 302
    //                   6 323
    //                   7 346;
           goto _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15
_L15:
        break MISSING_BLOCK_LABEL_346;
_L7:
        throw new IllegalArgumentException((new StringBuilder("Invalid mask pattern: ")).append(i).toString());
_L8:
        k3 = 1 & j1 + i3;
_L17:
        boolean flag1;
        int l3;
        int i4;
        if (k3 == 0)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (flag1)
        {
            if (!flag)
            {
                flag = true;
            } else
            {
                flag = false;
            }
        }
_L6:
        bytematrix.set(i3, j1, flag);
        l1 = j3;
_L4:
        l2++;
          goto _L16
_L9:
        k3 = j1 & 1;
          goto _L17
_L10:
        k3 = i3 % 3;
          goto _L17
_L11:
        k3 = (j1 + i3) % 3;
          goto _L17
_L12:
        k3 = 1 & (j1 >>> 1) + i3 / 3;
          goto _L17
_L13:
        i4 = j1 * i3;
        k3 = (i4 & 1) + i4 % 3;
          goto _L17
_L14:
        l3 = j1 * i3;
        k3 = 1 & (l3 & 1) + l3 % 3;
          goto _L17
        k3 = 1 & (j1 * i3) % 3 + (1 & j1 + i3);
          goto _L17
        for (j1 += l; true;)
        {
            break MISSING_BLOCK_LABEL_48;
        }

        int i2 = -l;
        int j2 = j1 + i2;
        int k2 = k1 - 2;
        l = i2;
        i1 = l1;
        k = j2;
        j = k2;
          goto _L18
_L2:
        if (i1 != bitarray.getSize())
        {
            throw new WriterException((new StringBuilder("Not all bits consumed: ")).append(i1).append('/').append(bitarray.getSize()).toString());
        } else
        {
            return;
        }
    }

    static void a(BitArray bitarray, ErrorCorrectionLevel errorcorrectionlevel, Version version, int i, ByteMatrix bytematrix)
    {
        bytematrix.clear((byte)-1);
        int j = a[0].length;
        c(0, 0, bytematrix);
        c(bytematrix.getWidth() - j, 0, bytematrix);
        c(0, bytematrix.getWidth() - j, bytematrix);
        a(0, 7, bytematrix);
        a(-8 + bytematrix.getWidth(), 7, bytematrix);
        a(0, -8 + bytematrix.getWidth(), bytematrix);
        b(7, 0, bytematrix);
        b(-1 + (-7 + bytematrix.getHeight()), 0, bytematrix);
        b(7, -7 + bytematrix.getHeight(), bytematrix);
        if (bytematrix.get(8, -8 + bytematrix.getHeight()) == 0)
        {
            throw new WriterException();
        }
        bytematrix.set(8, -8 + bytematrix.getHeight(), 1);
        if (version.getVersionNumber() >= 2)
        {
            int k1 = -1 + version.getVersionNumber();
            int ai[] = c[k1];
            int l1 = c[k1].length;
            for (int i2 = 0; i2 < l1; i2++)
            {
label0:
                for (int j2 = 0; j2 < l1; j2++)
                {
                    int k2 = ai[i2];
                    int l2 = ai[j2];
                    if (l2 == -1 || k2 == -1 || !b(bytematrix.get(l2, k2)))
                    {
                        continue;
                    }
                    int i3 = l2 - 2;
                    int j3 = k2 - 2;
                    int k3 = 0;
                    do
                    {
                        if (k3 >= 5)
                        {
                            continue label0;
                        }
                        for (int l3 = 0; l3 < 5; l3++)
                        {
                            bytematrix.set(i3 + l3, j3 + k3, b[k3][l3]);
                        }

                        k3++;
                    } while (true);
                }

            }

        }
        for (int k = 8; k < -8 + bytematrix.getWidth(); k++)
        {
            int j1 = (k + 1) % 2;
            if (b(bytematrix.get(k, 6)))
            {
                bytematrix.set(k, 6, j1);
            }
            if (b(bytematrix.get(6, k)))
            {
                bytematrix.set(6, k, j1);
            }
        }

        BitArray bitarray1 = new BitArray();
        if (!QRCode.isValidMaskPattern(i))
        {
            throw new WriterException("Invalid mask pattern");
        }
        int l = i | errorcorrectionlevel.getBits() << 3;
        bitarray1.appendBits(l, 5);
        bitarray1.appendBits(a(l, 1335), 10);
        BitArray bitarray2 = new BitArray();
        bitarray2.appendBits(21522, 15);
        bitarray1.xor(bitarray2);
        if (bitarray1.getSize() != 15)
        {
            throw new WriterException((new StringBuilder("should not happen but we got: ")).append(bitarray1.getSize()).toString());
        }
        int i1 = 0;
        while (i1 < bitarray1.getSize()) 
        {
            boolean flag = bitarray1.get((-1 + bitarray1.getSize()) - i1);
            bytematrix.set(d[i1][0], d[i1][1], flag);
            if (i1 < 8)
            {
                bytematrix.set(-1 + (bytematrix.getWidth() - i1), 8, flag);
            } else
            {
                bytematrix.set(8, -7 + bytematrix.getHeight() + (i1 - 8), flag);
            }
            i1++;
        }
        a(version, bytematrix);
        a(bitarray, i, bytematrix);
    }

    private static void a(Version version, ByteMatrix bytematrix)
    {
        if (version.getVersionNumber() >= 7)
        {
            BitArray bitarray = new BitArray();
            bitarray.appendBits(version.getVersionNumber(), 6);
            bitarray.appendBits(a(version.getVersionNumber(), 7973), 12);
            if (bitarray.getSize() != 18)
            {
                throw new WriterException((new StringBuilder("should not happen but we got: ")).append(bitarray.getSize()).toString());
            }
            int i = 17;
            int j = 0;
            while (j < 6) 
            {
                int k = i;
                for (int l = 0; l < 3; l++)
                {
                    boolean flag = bitarray.get(k);
                    k--;
                    bytematrix.set(j, l + (-11 + bytematrix.getHeight()), flag);
                    bytematrix.set(l + (-11 + bytematrix.getHeight()), j, flag);
                }

                j++;
                i = k;
            }
        }
    }

    private static void b(int i, int j, ByteMatrix bytematrix)
    {
        for (int k = 0; k < 7; k++)
        {
            if (!b(bytematrix.get(i, j + k)))
            {
                throw new WriterException();
            }
            bytematrix.set(i, j + k, 0);
        }

    }

    private static boolean b(int i)
    {
        return i == -1;
    }

    private static void c(int i, int j, ByteMatrix bytematrix)
    {
        for (int k = 0; k < 7; k++)
        {
            for (int l = 0; l < 7; l++)
            {
                bytematrix.set(i + l, j + k, a[k][l]);
            }

        }

    }

}
