// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.qrcode.encoder;

import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitArray;
import com.google.zxing.common.CharacterSetECI;
import com.google.zxing.common.reedsolomon.GenericGF;
import com.google.zxing.common.reedsolomon.ReedSolomonEncoder;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.decoder.Mode;
import com.google.zxing.qrcode.decoder.Version;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

// Referenced classes of package com.google.zxing.qrcode.encoder:
//            c, a, ByteMatrix, b, 
//            QRCode

public final class Encoder
{

    private static final int a[] = {
        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
        -1, -1, 36, -1, -1, -1, 37, 38, -1, -1, 
        -1, -1, 39, 40, -1, 41, 42, 43, 0, 1, 
        2, 3, 4, 5, 6, 7, 8, 9, 44, -1, 
        -1, -1, -1, -1, -1, 10, 11, 12, 13, 14, 
        15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 
        25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 
        35, -1, -1, -1, -1, -1
    };

    private Encoder()
    {
    }

    private static int a(int i)
    {
        int[] _tmp = a;
        if (i < 96)
        {
            return a[i];
        } else
        {
            return -1;
        }
    }

    private static int a(BitArray bitarray, ErrorCorrectionLevel errorcorrectionlevel, Version version, ByteMatrix bytematrix)
    {
        int i = 0x7fffffff;
        int j = -1;
        int k = 0;
        while (k < 8) 
        {
            c.a(bitarray, errorcorrectionlevel, version, k, bytematrix);
            int l = com.google.zxing.qrcode.encoder.a.a(bytematrix);
            int i1 = 0;
            byte abyte0[][] = bytematrix.getArray();
            int j1 = bytematrix.getWidth();
            int k1 = bytematrix.getHeight();
            for (int l1 = 0; l1 < k1 - 1;)
            {
                int k6 = i1;
                for (int l6 = 0; l6 < j1 - 1; l6++)
                {
                    byte byte0 = abyte0[l1][l6];
                    if (byte0 == abyte0[l1][l6 + 1] && byte0 == abyte0[l1 + 1][l6] && byte0 == abyte0[l1 + 1][l6 + 1])
                    {
                        k6++;
                    }
                }

                l1++;
                i1 = k6;
            }

            int i2 = l + i1 * 3;
            int j2 = 0;
            byte abyte1[][] = bytematrix.getArray();
            int k2 = bytematrix.getWidth();
            int l2 = bytematrix.getHeight();
            for (int i3 = 0; i3 < l2;)
            {
                int i6 = j2;
                for (int j6 = 0; j6 < k2; j6++)
                {
                    if (j6 + 6 < k2 && abyte1[i3][j6] == 1 && abyte1[i3][j6 + 1] == 0 && abyte1[i3][j6 + 2] == 1 && abyte1[i3][j6 + 3] == 1 && abyte1[i3][j6 + 4] == 1 && abyte1[i3][j6 + 5] == 0 && abyte1[i3][j6 + 6] == 1 && (j6 + 10 < k2 && abyte1[i3][j6 + 7] == 0 && abyte1[i3][j6 + 8] == 0 && abyte1[i3][j6 + 9] == 0 && abyte1[i3][j6 + 10] == 0 || j6 - 4 >= 0 && abyte1[i3][j6 - 1] == 0 && abyte1[i3][j6 - 2] == 0 && abyte1[i3][j6 - 3] == 0 && abyte1[i3][j6 - 4] == 0))
                    {
                        i6 += 40;
                    }
                    if (i3 + 6 < l2 && abyte1[i3][j6] == 1 && abyte1[i3 + 1][j6] == 0 && abyte1[i3 + 2][j6] == 1 && abyte1[i3 + 3][j6] == 1 && abyte1[i3 + 4][j6] == 1 && abyte1[i3 + 5][j6] == 0 && abyte1[i3 + 6][j6] == 1 && (i3 + 10 < l2 && abyte1[i3 + 7][j6] == 0 && abyte1[i3 + 8][j6] == 0 && abyte1[i3 + 9][j6] == 0 && abyte1[i3 + 10][j6] == 0 || i3 - 4 >= 0 && abyte1[i3 - 1][j6] == 0 && abyte1[i3 - 2][j6] == 0 && abyte1[i3 - 3][j6] == 0 && abyte1[i3 - 4][j6] == 0))
                    {
                        i6 += 40;
                    }
                }

                i3++;
                j2 = i6;
            }

            int j3 = i2 + j2;
            int k3 = 0;
            byte abyte2[][] = bytematrix.getArray();
            int l3 = bytematrix.getWidth();
            int i4 = bytematrix.getHeight();
            for (int j4 = 0; j4 < i4;)
            {
                byte abyte3[] = abyte2[j4];
                int k5 = k3;
                for (int l5 = 0; l5 < l3; l5++)
                {
                    if (abyte3[l5] == 1)
                    {
                        k5++;
                    }
                }

                j4++;
                k3 = k5;
            }

            int k4 = bytematrix.getHeight() * bytematrix.getWidth();
            int l4 = j3 + 10 * (int)(20D * Math.abs((double)k3 / (double)k4 - 0.5D));
            int i5;
            int j5;
            if (l4 < i)
            {
                j5 = l4;
                i5 = k;
            } else
            {
                i5 = j;
                j5 = i;
            }
            k++;
            i = j5;
            j = i5;
        }
        return j;
    }

    private static BitArray a(BitArray bitarray, int i, int j, int k)
    {
        if (bitarray.getSizeInBytes() != j)
        {
            throw new WriterException("Number of bits and data bytes does not match");
        }
        ArrayList arraylist = new ArrayList(k);
        int l = 0;
        int i1 = 0;
        int j1 = 0;
        int k1 = 0;
        while (l < k) 
        {
            int ai[] = new int[1];
            int ai1[] = new int[1];
            if (l >= k)
            {
                throw new WriterException("Block ID too large");
            }
            int j2 = i % k;
            int k2 = k - j2;
            int l2 = i / k;
            int i3 = l2 + 1;
            int j3 = j / k;
            int k3 = j3 + 1;
            int l3 = l2 - j3;
            int i4 = i3 - k3;
            if (l3 != i4)
            {
                throw new WriterException("EC bytes mismatch");
            }
            if (k != k2 + j2)
            {
                throw new WriterException("RS blocks mismatch");
            }
            if (i != k2 * (j3 + l3) + j2 * (k3 + i4))
            {
                throw new WriterException("Total bytes mismatch");
            }
            int j4;
            byte abyte2[];
            byte abyte3[];
            int k4;
            int l4;
            if (l < k2)
            {
                ai[0] = j3;
                ai1[0] = l3;
            } else
            {
                ai[0] = k3;
                ai1[0] = i4;
            }
            j4 = ai[0];
            abyte2 = new byte[j4];
            bitarray.toBytes(i1 * 8, abyte2, 0, j4);
            abyte3 = a(abyte2, ai1[0]);
            arraylist.add(new a(abyte2, abyte3));
            j1 = Math.max(j1, j4);
            k4 = Math.max(k1, abyte3.length);
            l4 = i1 + ai[0];
            l++;
            i1 = l4;
            k1 = k4;
        }
        if (j != i1)
        {
            throw new WriterException("Data bytes does not match offset");
        }
        BitArray bitarray1 = new BitArray();
label0:
        for (int l1 = 0; l1 < j1; l1++)
        {
            Iterator iterator1 = arraylist.iterator();
            do
            {
                if (!iterator1.hasNext())
                {
                    continue label0;
                }
                byte abyte1[] = ((a)iterator1.next()).getDataBytes();
                if (l1 < abyte1.length)
                {
                    bitarray1.appendBits(abyte1[l1], 8);
                }
            } while (true);
        }

label1:
        for (int i2 = 0; i2 < k1; i2++)
        {
            Iterator iterator = arraylist.iterator();
            do
            {
                if (!iterator.hasNext())
                {
                    continue label1;
                }
                byte abyte0[] = ((a)iterator.next()).getErrorCorrectionBytes();
                if (i2 < abyte0.length)
                {
                    bitarray1.appendBits(abyte0[i2], 8);
                }
            } while (true);
        }

        if (i != bitarray1.getSizeInBytes())
        {
            throw new WriterException((new StringBuilder("Interleaving error: ")).append(i).append(" and ").append(bitarray1.getSizeInBytes()).append(" differ.").toString());
        } else
        {
            return bitarray1;
        }
    }

    private static Mode a(String s, String s1)
    {
        int i = 0;
        if ("Shift_JIS".equals(s1))
        {
            if (a(s))
            {
                return Mode.KANJI;
            } else
            {
                return Mode.BYTE;
            }
        }
        boolean flag = false;
        boolean flag1 = false;
        while (i < s.length()) 
        {
            char c1 = s.charAt(i);
            if (c1 >= '0' && c1 <= '9')
            {
                flag1 = true;
            } else
            if (a(c1) != -1)
            {
                flag = true;
            } else
            {
                return Mode.BYTE;
            }
            i++;
        }
        if (flag)
        {
            return Mode.ALPHANUMERIC;
        }
        if (flag1)
        {
            return Mode.NUMERIC;
        } else
        {
            return Mode.BYTE;
        }
    }

    private static Version a(int i, ErrorCorrectionLevel errorcorrectionlevel)
    {
        for (int j = 1; j <= 40; j++)
        {
            Version version = Version.getVersionForNumber(j);
            if (version.getTotalCodewords() - version.getECBlocksForLevel(errorcorrectionlevel).getTotalECCodewords() >= (i + 7) / 8)
            {
                return version;
            }
        }

        throw new WriterException("Data too big");
    }

    private static void a(int i, BitArray bitarray)
    {
        int j = i << 3;
        if (bitarray.getSize() > j)
        {
            throw new WriterException((new StringBuilder("data bits cannot fit in the QR Code")).append(bitarray.getSize()).append(" > ").append(j).toString());
        }
        for (int k = 0; k < 4 && bitarray.getSize() < j; k++)
        {
            bitarray.appendBit(false);
        }

        int l = 7 & bitarray.getSize();
        if (l > 0)
        {
            for (; l < 8; l++)
            {
                bitarray.appendBit(false);
            }

        }
        int i1 = i - bitarray.getSizeInBytes();
        int j1 = 0;
        while (j1 < i1) 
        {
            char c1;
            if ((j1 & 1) == 0)
            {
                c1 = '\354';
            } else
            {
                c1 = '\021';
            }
            bitarray.appendBits(c1, 8);
            j1++;
        }
        if (bitarray.getSize() != j)
        {
            throw new WriterException("Bits size does not equal capacity");
        } else
        {
            return;
        }
    }

    private static void a(String s, BitArray bitarray)
    {
        byte abyte0[];
        int i;
        int j;
        try
        {
            abyte0 = s.getBytes("Shift_JIS");
        }
        catch (UnsupportedEncodingException unsupportedencodingexception)
        {
            throw new WriterException(unsupportedencodingexception);
        }
        i = abyte0.length;
        j = 0;
        while (j < i) 
        {
            int k = 0xff & abyte0[j];
            int l = 0xff & abyte0[j + 1] | k << 8;
            int i1;
            if (l >= 33088 && l <= 40956)
            {
                i1 = l - 33088;
            } else
            if (l >= 57408 && l <= 60351)
            {
                i1 = l - 49472;
            } else
            {
                i1 = -1;
            }
            if (i1 == -1)
            {
                throw new WriterException("Invalid byte sequence");
            }
            bitarray.appendBits(192 * (i1 >> 8) + (i1 & 0xff), 13);
            j += 2;
        }
    }

    private static void a(String s, BitArray bitarray, String s1)
    {
        byte abyte0[];
        int i;
        try
        {
            abyte0 = s.getBytes(s1);
        }
        catch (UnsupportedEncodingException unsupportedencodingexception)
        {
            throw new WriterException(unsupportedencodingexception);
        }
        i = abyte0.length;
        for (int j = 0; j < i; j++)
        {
            bitarray.appendBits(abyte0[j], 8);
        }

    }

    private static boolean a(String s)
    {
        byte abyte0[];
        int i;
        int j;
        int k;
        try
        {
            abyte0 = s.getBytes("Shift_JIS");
        }
        catch (UnsupportedEncodingException unsupportedencodingexception)
        {
            return false;
        }
        i = abyte0.length;
        if (i % 2 == 0) goto _L2; else goto _L1
_L1:
        return false;
_L2:
        j = 0;
label0:
        do
        {
label1:
            {
                if (j >= i)
                {
                    break label1;
                }
                k = 0xff & abyte0[j];
                if ((k < 129 || k > 159) && (k < 224 || k > 235))
                {
                    break label0;
                }
                j += 2;
            }
        } while (true);
        if (true) goto _L1; else goto _L3
_L3:
        return true;
    }

    private static byte[] a(byte abyte0[], int i)
    {
        int j = 0;
        int k = abyte0.length;
        int ai[] = new int[k + i];
        for (int l = 0; l < k; l++)
        {
            ai[l] = 0xff & abyte0[l];
        }

        (new ReedSolomonEncoder(GenericGF.QR_CODE_FIELD_256)).encode(ai, i);
        byte abyte1[] = new byte[i];
        for (; j < i; j++)
        {
            abyte1[j] = (byte)ai[k + j];
        }

        return abyte1;
    }

    public static Mode chooseMode(String s)
    {
        return a(s, ((String) (null)));
    }

    public static QRCode encode(String s, ErrorCorrectionLevel errorcorrectionlevel)
    {
        return encode(s, errorcorrectionlevel, null);
    }

    public static QRCode encode(String s, ErrorCorrectionLevel errorcorrectionlevel, Map map)
    {
        Mode mode;
        BitArray bitarray;
        BitArray bitarray1;
        int i = 0;
        String s1;
        if (map == null)
        {
            s1 = null;
        } else
        {
            s1 = (String)map.get(EncodeHintType.CHARACTER_SET);
        }
        if (s1 == null)
        {
            s1 = "ISO-8859-1";
        }
        mode = a(s, s1);
        bitarray = new BitArray();
        if (mode == Mode.BYTE && !"ISO-8859-1".equals(s1))
        {
            CharacterSetECI characterseteci = CharacterSetECI.getCharacterSetECIByName(s1);
            if (characterseteci != null)
            {
                bitarray.appendBits(Mode.ECI.getBits(), 4);
                bitarray.appendBits(characterseteci.getValue(), 8);
            }
        }
        bitarray.appendBits(mode.getBits(), 4);
        bitarray1 = new BitArray();
label0:
        switch (b.a[mode.ordinal()])
        {
        default:
            throw new WriterException((new StringBuilder("Invalid mode: ")).append(mode).toString());

        case 4: // '\004'
            break MISSING_BLOCK_LABEL_599;

        case 1: // '\001'
            int k2 = s.length();
            do
            {
                if (i >= k2)
                {
                    break label0;
                }
                int l2 = -48 + s.charAt(i);
                if (i + 2 < k2)
                {
                    int i3 = -48 + s.charAt(i + 1);
                    bitarray1.appendBits(-48 + s.charAt(i + 2) + (l2 * 100 + i3 * 10), 10);
                    i += 3;
                } else
                if (i + 1 < k2)
                {
                    bitarray1.appendBits(-48 + s.charAt(i + 1) + l2 * 10, 7);
                    i += 2;
                } else
                {
                    bitarray1.appendBits(l2, 4);
                    i++;
                }
            } while (true);

        case 2: // '\002'
            int k1 = s.length();
            int l1 = 0;
            do
            {
                if (l1 >= k1)
                {
                    break label0;
                }
                int i2 = a(s.charAt(l1));
                if (i2 == -1)
                {
                    throw new WriterException();
                }
                if (l1 + 1 < k1)
                {
                    int j2 = a(s.charAt(l1 + 1));
                    if (j2 == -1)
                    {
                        throw new WriterException();
                    }
                    bitarray1.appendBits(j2 + i2 * 45, 11);
                    l1 += 2;
                } else
                {
                    bitarray1.appendBits(i2, 6);
                    l1++;
                }
            } while (true);

        case 3: // '\003'
            a(s, bitarray1, s1);
            break;
        }
_L1:
        Version version = a(bitarray.getSize() + mode.getCharacterCountBits(Version.getVersionForNumber(1)) + bitarray1.getSize(), errorcorrectionlevel);
        Version version1 = a(bitarray.getSize() + mode.getCharacterCountBits(version) + bitarray1.getSize(), errorcorrectionlevel);
        BitArray bitarray2 = new BitArray();
        bitarray2.appendBitArray(bitarray);
        int j;
        int k;
        if (mode == Mode.BYTE)
        {
            j = bitarray1.getSizeInBytes();
        } else
        {
            j = s.length();
        }
        k = mode.getCharacterCountBits(version1);
        if (j >= 1 << k)
        {
            throw new WriterException((new StringBuilder()).append(j).append(" is bigger than ").append(-1 + (1 << k)).toString());
        } else
        {
            bitarray2.appendBits(j, k);
            bitarray2.appendBitArray(bitarray1);
            com.google.zxing.qrcode.decoder.Version.ECBlocks ecblocks = version1.getECBlocksForLevel(errorcorrectionlevel);
            int l = version1.getTotalCodewords() - ecblocks.getTotalECCodewords();
            a(l, bitarray2);
            BitArray bitarray3 = a(bitarray2, version1.getTotalCodewords(), l, ecblocks.getNumBlocks());
            QRCode qrcode = new QRCode();
            qrcode.setECLevel(errorcorrectionlevel);
            qrcode.setMode(mode);
            qrcode.setVersion(version1);
            int i1 = version1.getDimensionForVersion();
            ByteMatrix bytematrix = new ByteMatrix(i1, i1);
            int j1 = a(bitarray3, errorcorrectionlevel, version1, bytematrix);
            qrcode.setMaskPattern(j1);
            c.a(bitarray3, errorcorrectionlevel, version1, j1, bytematrix);
            qrcode.setMatrix(bytematrix);
            return qrcode;
        }
        a(s, bitarray1);
          goto _L1
    }

}
